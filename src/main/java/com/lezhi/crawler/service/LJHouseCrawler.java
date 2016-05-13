package com.lezhi.crawler.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lezhi.crawler.dto.VResidenceInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;

import com.lezhi.crawler.dto.HouseQuotation;
import com.lezhi.crawler.mapper.HouseQuotationMapper;
import com.lezhi.crawler.mapper.VResidenceInfoModelMapper;
import com.lezhi.crawler.util.AddressUtil;

/**
 * Created by Cuill on 2016/5/9.
 */
@Service
public class LJHouseCrawler {

    protected static final Log LOGGER = LogFactory.getLog(LJHouseCrawler.class);

    private static SimpleDateFormat SD = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private HttpService httpService;

    @Autowired
    private HouseQuotationMapper houseQuotationMapper;

    @Autowired
    private VResidenceInfoModelMapper vResidenceInfoModelMapper;

    private static final String BASE_URL = "http://sh.lianjia.com{param}";

    public void start() throws Exception {
        String startUrl = "http://sh.lianjia.com/ershoufang";
        String html = httpService.doGet(startUrl);
        Document root = Jsoup.parse(html);
        Elements elements = root.select(".con-box");
        Integer num = null;
        for (Element e : elements) {
            num = Integer.valueOf(e.child(0).child(0).text().split("\\D+")[1]);
        }
        Integer page = num / 20 + 1;
        System.out.println(page);
        for (int i = 1; i <= 20; i++) {
            LOGGER.debug("正在请求第" + i + "页的数据");
            // 分页请求
            String url = StringUtils.replace(BASE_URL, "{param}", "/ershoufang/d" + i);
            String html1 = httpService.doGet(url);
            Document root1 = Jsoup.parse(html1);
            Elements elements1 = root1.select("#house-lst li");
            if (null != elements1 && elements1.size() == 0) {
                return;
            }
            for (Element li : elements1) {
                String address = null;
                String residenceName = null;
                String huxing = null;
                String districtName = null;
                String con = null;
                HouseQuotation houseQuotation = new HouseQuotation();
                Element div = li.child(1);
                String title = div.child(0).text();
                // 房源编号
                String href = div.child(0).child(0).attr("href");
                Long ljId = Long.valueOf(href.split("\\D+")[1]);
                houseQuotation.setId(ljId);
                Elements div2 = li.select(".col-1");
                for (Element e : div2) {
                    residenceName = e.child(0).child(0).text();
                    // System.out.println("小区id====" + jedisCluster.get(residenceName));
                    houseQuotation.setQresidencename(residenceName);// 小区名称
                    huxing = e.child(0).child(1).text();
                    String[] floor = huxing.split("\\D+");
                    Long propertyRoom = Long.valueOf(floor[0]);
                    houseQuotation.setPropertyroom(propertyRoom);// 房间数
                    Long propertyTing = Long.valueOf(floor[1]);
                    houseQuotation.setPropertyting(propertyTing);// 厅数
                    Double propertyArea = Double
                            .valueOf(e.child(0).child(2).text().split("[\\u4e00-\\u9fa5]")[0]);
                    houseQuotation.setPropertyarea(propertyArea);
                    districtName = e.child(1).child(0).child(0).text();
                    Integer districtId = getDistrictId(districtName);
                    System.out.println("区域名称==========" + districtName);
                    houseQuotation.setDistrictid(districtId);
                    con = e.child(1).child(0).text();
                    String[] strs = e.child(1).child(0).text().split("\\|");
                    Long totalFloor = Long.valueOf(strs[1].split("\\D+")[1]);
                    houseQuotation.setTotalfloor(totalFloor);
                    if (strs.length > 3) {
                        String towards = strs[2];
                        houseQuotation.setTowards(towards);
                    } else
                        houseQuotation.setTowards(null);

                    Elements div3 = li.select(".col-3");
                    for (Element e1 : div3) {
                        String quotationTotalPrice = e1.select(".price").text().split("\\D+")[0];
                        houseQuotation.setQuotationtotalprice(Double.valueOf(quotationTotalPrice));
                        String quotationUnitPrice = e1.select(".price-pre").text().split("\\D+")[0];
                        houseQuotation.setQuotationunitprice(Double.valueOf(quotationUnitPrice));
                    }
                    houseQuotation.setIsavailable(1);
                }
                // 获取房屋其它信息
                // 详情页面url
                String detailUrl = StringUtils.replace(BASE_URL, "{param}", href);
                String detailhtml = httpService.doGet(detailUrl);
                Document detailroot = Jsoup.parse(detailhtml);
                Elements transaction = detailroot.select(".transaction");
                for (Element e : transaction) {
                    String propertyType_text = e.child(1).child(0).child(1).text();
                    String propertyType = StringUtils.substringAfterLast(propertyType_text, "：");
                    houseQuotation.setPropertytype(propertyType);
                }
                // 房屋地址
                Elements aroundInfo = detailroot.select(".aroundInfo");
                for (Element e : aroundInfo) {
                    address = e.child(0).child(5).child(1).text();
                }
                String s = address + residenceName;
                String add_resid = AddressUtil.analyzeAddress(s);
                try {
                    // 设置关联小区id
                    String residenceId_str = jedisCluster.get(add_resid);
                    if (null == residenceId_str) {
                        if (residenceName.contains("（")) {
                            residenceName = StringUtils.substringBefore(residenceName, "（");
                        }
                        if (address.contains("（")) {
                            address = StringUtils.substringBefore(address, "（");
                        }
                        List<VResidenceInfoModel> vResidenceInfoModels = vResidenceInfoModelMapper
                                .selectResidenceIdByNameLike(residenceName, address);
                        if (null != vResidenceInfoModels && 0 != vResidenceInfoModels.size()) {
                            houseQuotation.setResidenceid(
                                    Long.valueOf(vResidenceInfoModels.get(0).getResidenceId()));
                        }
                    } else
                        houseQuotation.setResidenceid(null);
                } catch (Exception e) {
                    LOGGER.error("关联小区id异常");
                }
                Date date = new Date();
                SD.format(date);
                System.out.println(date);
                houseQuotation.setQuotationDate(date);
                // System.out.println(houseQuotation);
                // houseQuotationMapper.insert(houseQuotation);
                // houseQuotationMapper.saveOrupdate(houseQuotation);
                System.out.println(
                        houseQuotation.getId() + "|" + huxing + "|" + houseQuotation.getPropertyarea() + "|"
                                + con + "|" + houseQuotation.getQuotationtotalprice() + "|"
                                + houseQuotation.getQuotationunitprice() + "|"
                                + houseQuotation.getPropertytype() + "|" + address);
            }
        }

    }

    private static Integer getDistrictId(String districtName) {
        if ("黄埔".equals(districtName)) {
            return 310101;
        } else if ("徐汇".equals(districtName)) {
            return 310104;
        } else if ("长宁".equals(districtName)) {
            return 310105;
        } else if ("静安".equals(districtName)) {
            return 310106;
        } else if ("普陀".equals(districtName)) {
            return 310107;
        } else if ("闸北".equals(districtName)) {
            return 310108;
        } else if ("虹口".equals(districtName)) {
            return 310109;
        } else if ("杨浦".equals(districtName)) {
            return 310110;
        } else if ("闵行".equals(districtName)) {
            return 310112;
        } else if ("宝山".equals(districtName)) {
            return 310113;
        } else if ("嘉定".equals(districtName)) {
            return 310114;
        } else if ("浦东".equals(districtName)) {
            return 310115;
        } else if ("金山".equals(districtName)) {
            return 310116;
        } else if ("松江".equals(districtName)) {
            return 310117;
        } else if ("青浦".equals(districtName)) {
            return 310118;
        } else if ("南汇".equals(districtName)) {
            return 310119;
        } else if ("奉贤".equals(districtName)) {
            return 310120;
        } else if ("崇明".equals(districtName)) {
            return 310230;
        } else {
            return 999999;
        }
    }

    public static void main(String[] args) throws Exception {
        new LJHouseCrawler().start();
        String str = "海上国际花园（二期）";
        System.out.println(str.contains("（"));
        String s = StringUtils.substringBefore("海上国际花园（二期）", "（");
        System.out.println(s);
    }
}
