package com.lezhi.crawler.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lezhi.crawler.util.CrawlerUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lezhi.crawler.dto.DealCase;
import com.lezhi.crawler.dto.Residence;

/**
 * Created by Cuill on 2016/5/11.
 */
@Service
public class LJResidenceCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LJResidenceCrawler.class);

    private static SimpleDateFormat SD = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private HttpService httpService;

    private List<DealCase> dealCases = new ArrayList<DealCase>();

    @Value("${lianjia.base.url}")
    private String BAER_URL;

    /**
     * 抓取小区数据
     *
     * @throws Exception
     */
    // @PostConstruct
    public void ljResidCrawlerMethod() throws Exception {
        String html = httpService.doGet(BAER_URL + "/xiaoqu");
        Document root = Jsoup.parse(html);
        Integer totalPage = getTotalPage(root) / 20 + 1;
        List<Residence> residences = new ArrayList<Residence>();// 小区集合
        for (int i = 1; i <= 2; i++) {
            LOGGER.info("正在解析第" + i + "页小区信息");
            // 每次请求url
            String url = BAER_URL + "/xiaoqu/d" + i + "";
            LOGGER.info("小区url=" + url);
            String house_list = httpService.doGet(url);
            Document parse = Jsoup.parse(house_list);
            Elements elements = parse.select("#house-lst li");
            if (elements.size() == 0) {
                return;
            }
            for (Element e : elements) {// 小区列表
                Residence residence = new Residence();
                Elements info_panel = e.select(".info-panel");
                for (Element e1 : info_panel) {
                    String residenceName = e1.select("h2 a").text();// 小区名称
                    residence.setResidenceName(residenceName);
                    String residenceUrl = BAER_URL + e1.select("h2 a").attr("href");// 小区链接url
                    Long residenceId = Long.valueOf(CrawlerUtils.getNumber(residenceUrl)[1]);// 小区id
                    residence.setResidenceId(residenceId);
                    residence.setResidenceUrl(residenceUrl);
                    Elements select = e1.select(".col-1").select(".other").select(".con a");
                    residence.setDistrict(select.get(0).text());// 区域
                    residence.setBlock(select.get(1).text());// 板块
                    residence.setAccomplishDate(CrawlerUtils
                            .getNumber(e1.select(".col-1").select(".other").select(".con").text())[1]);
                    // 小区均价
                    String avagePrice = e1.select(".col-3").select(".price span").text();
                    residence.setAvagePrice(avagePrice);
                    // 距离地铁描述
                    String subwayDesc = e1.select(".fang-subway-ex").text();
                    residence.setSubWayDesc(subwayDesc);
                }
                // 解析小区其它信息
                doParseOtherInfo(residence);
                // 解析小区交易案例
                dealCases = getDealCaseList(residence.getResidenceId());
                residence.setDealCaseList(dealCases);
                residences.add(residence);
            }
        }
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = null;

        for (Residence residence : residences) {
            sb.append(residence.getResidenceId() + "|" + residence.getResidenceName() + "|"
                    + residence.getAddress() + "|" + residence.getDistrict() + "|" + residence.getBlock()
                    + "|" + residence.getAvagePrice() + "|" + residence.getAccomplishDate() + "|"
                    + residence.getBuildingNum() + "|" + residence.getTotalHouse() + "|"
                    + residence.getVolumeRate() + "|" + residence.getGreeningRate() + "|"
                    + residence.getLongitude() + "|" + residence.getLatitude() + "|"
                    + residence.getSubWayDesc() + "|" + residence.getResidenceUrl() + "\n");
            sb1 = new StringBuffer();
            if (residence.getDealCaseList() != null) {
                for (DealCase dealCase : residence.getDealCaseList()) {
                    sb1.append("  " + dealCase.getId() + "|" + dealCase.getResidenceId() + "|"
                            + dealCase.getHouseType() + "|" + dealCase.getHouseAera() + "|"
                            + dealCase.getFloorInfo() + "|" + dealCase.getTowards() + "|"
                            + dealCase.getSignDate() + "|" + dealCase.getSignUnitPrice() + "|"
                            + dealCase.getSignTotalPrice() + "\n");
                }
            } else
                continue;
            sb.append(sb1);
        }
        File file = new File("F:\\" + System.currentTimeMillis() + ".txt");
        FileUtils.writeStringToFile(file, sb.toString(), "UTF-8");
        LOGGER.info("小区信息写入文件成功！！");
    }

    /**
     * 获取小区交易案例数据
     *
     * @param residenceId 小区id
     * @return
     */
    private List<DealCase> getDealCaseList(Long residenceId) throws Exception {
        String start_url = BAER_URL + "/chengjiao/q" + residenceId.toString();
        String s = httpService.doGet(start_url);
        Document document = Jsoup.parse(s);
        Integer totalPage = getTotalPage(document);
        int wNum = totalPage / 20 + 1;
        dealCases = new ArrayList<DealCase>();
        for (int i = 1; i <= wNum; i++) {
            LOGGER.info("正在解析第" + i + "页小区交易案例，小区id=" + residenceId);
            String url = BAER_URL + "/chengjiao/d" + i + "q" + residenceId.toString();
            try {
                String html = httpService.doGet(url);
                Document document1 = Jsoup.parse(html);
                Elements elements = document1.select(".clinch-list li");
                if (elements.size() == 0) {
                    return null;
                }
                for (Element li : elements) {
                    try {
                        DealCase dealCase = new DealCase();
                        dealCase.setResidenceId(residenceId);
                        String href = li.select(".info-panel h2 a").attr("href");
                        dealCase.setId(Long.valueOf(CrawlerUtils.getNumber(href)[1]));// 房屋编号
                        String info = li.select(".info-panel h2 a").text();
                        String[] infos = StringUtils.split(info, " ");
                        dealCase.setHouseType(infos[1]);// 房型
                        String area = StringUtils.substringBeforeLast(infos[2], "平米");
                        dealCase.setHouseAera(Double.valueOf(area));// 房屋面积
                        String[] other = li.select(".other").text().split("\\|");// 楼层和朝向
                        if (other.length < 2) {
                            dealCase.setFloorInfo(null);
                            dealCase.setTowards(null);
                        } else if (other.length == 2) {
                            dealCase.setFloorInfo(other[1]);
                            dealCase.setTowards(null);
                        } else if (other.length > 2) {
                            dealCase.setFloorInfo(other[1]);
                            dealCase.setTowards(other[2]);
                        }
                        String dealType = li.select(".dealType").text();
                        String[] dealType_ = dealType.split(" ");
                        String signDate = dealType_[0];
                        Date date = SD.parse(signDate);
                        dealCase.setSignDate(date);// 签约日期
                        dealCase.setSignUnitPrice(Double.valueOf(CrawlerUtils.getNumber(dealType_[2])[0]));// 签约单价
                        dealCase.setSignTotalPrice(Double.valueOf(CrawlerUtils.getNumber(dealType_[4])[0]));// 签约总价

                        dealCases.add(dealCase);
                    } catch (Exception e) {
                        LOGGER.error("交易案例解析失败li=" + li);
                    }
                }
            } catch (Exception e) {
                LOGGER.error(i + "页面二手房交易数据抓取失败,小区id={}", residenceId.toString(), e);
            }
        }
        return dealCases;
    }

    /**
     * 获取小区其它信息
     *
     * @param residence
     * @throws Exception
     */
    private void doParseOtherInfo(Residence residence) throws Exception {
        String html = null;
        try {
            html = httpService.doGet(residence.getResidenceUrl());
        } catch (Exception e) {
            LOGGER.error("解析小区其它信息失败", e);
        }
        Document root = Jsoup.parse(html);
        String address = root.select(".adr").text();
        residence.setAddress(address);
        Elements li = root.select("ol li");
        String buildingNum = CrawlerUtils.getNumber(li.get(4).text())[1];
        residence.setBuildingNum(buildingNum);// 楼栋
        String totalHouse = CrawlerUtils.getNumber(li.get(5).text())[1];
        residence.setTotalHouse(totalHouse);
        String volumeRate = StringUtils.substringAfterLast(li.get(6).text(), "：");
        residence.setVolumeRate(volumeRate);// 容积率
        String greeningRate = StringUtils.substringAfterLast(li.get(7).text(), "：");
        residence.setGreeningRate(greeningRate);// 绿化率
        // 经纬度
        String longitude = root.select("#zoneMap").attr("longitude");
        String latitude = root.select("#zoneMap").attr("latitude");
        residence.setLongitude(longitude);
        residence.setLatitude(latitude);

    }

    /**
     * 获取总页数
     *
     * @param root
     * @return
     */
    public Integer getTotalPage(Document root) {
        String pageTotal_str = null;
        try {
            Elements elements = root.select(".con-box");
            for (Element e : elements) {
                pageTotal_str = CrawlerUtils.getNumber(e.child(0).child(0).text())[1];
            }
        } catch (Exception e) {
            LOGGER.error("获取小区个数失败", e);
        }
        return Integer.valueOf(pageTotal_str);

    }

    public static void main(String[] args) throws Exception {
        new LJResidenceCrawler().ljResidCrawlerMethod();
    }
}
