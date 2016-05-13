package com.lezhi.crawler.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lezhi.crawler.dto.FangjiaResidenceInfo;
import com.lezhi.crawler.mapper.FangjiaResidenceInfoMapper;
import com.lezhi.crawler.util.CrawlerUtils;

/**
 * Created by Cuill on 2016/5/12.
 */
@Service
public class FangJiaCrawler implements Crawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FangJiaCrawler.class);

    @Autowired
    private HttpService httpService;

    @Autowired
    private FangjiaResidenceInfoMapper fangjiaResidenceInfoMapper;

    private String FANGJIA_BAER_URL = "http://www.fangjiadp.com/shanghai/esf/advanced/search?pg={page}";

    private String RESID_URL = "/shanghai/esf/advanced/search";

    private String BASE_URL = "http://www.fangjiadp.com";

    /**
     * 抓取房价网小区数据
     */
    public void start() throws Exception {
        Document document = parseHtml(BASE_URL + RESID_URL);
        Integer totalPage = getTotalPage(document);
        for (int i = 1; i <= 10; i++) {
            List<FangjiaResidenceInfo> fangjiaResidenceInfos = new ArrayList<FangjiaResidenceInfo>();
            // 每次请求url
            String url = StringUtils.replace(FANGJIA_BAER_URL, "{page}", i + "");
            LOGGER.debug("正在解析第" + i + "页！url=" + url);
            Document root = parseHtml(url);
            Elements elements = root.select("#esfunitlist li");
            // 首页列表解析的信息
            for (Element li : elements) {
                try {
                    FangjiaResidenceInfo f = new FangjiaResidenceInfo();
                    String href = li.select("a").attr("href");
                    f.setResidenceUrl(BASE_URL + href);// 小区url
                    f.setResidenceId(Long.valueOf(CrawlerUtils.getNumber(href)[1]));
                    String residenceName = li.select(".ct_name b").text();
                    f.setResidenceName(residenceName);
                    String add_text = li.select(".ct_name em").text();
                    String address = StringUtils.substringAfter(add_text, "]");
                    f.setAddress(address);// 地址
                    String price = li.select(".ct_price").select(".mb10 b").text();
                    f.setPriceRange(price);
                    // 解析详情页部分数据
                    doparseOtherInfo(f);
                    fangjiaResidenceInfos.add(f);
                } catch (Exception e) {
                    LOGGER.error("解析当前数据失败,li=" + li, e);
                }
            }
            if (null != fangjiaResidenceInfos && fangjiaResidenceInfos.size() == 16) {
                saveDataToDb(fangjiaResidenceInfos, i);
            }
        }
    }

    private void doparseOtherInfo(FangjiaResidenceInfo f) throws Exception {
        String url = f.getResidenceUrl() + "_des";
        LOGGER.debug("正在解析小区基本参数！url=" + url);
        Document document = parseHtml(url);
        String title = document.select(".esgrid_left").select(".clearfix a").text();
        if (!"-".equals(title)) {
            String dis_bl = StringUtils.replace(StringUtils.substringBefore(title, "]"), "[", "");
            String[] split = dis_bl.split("-");
            f.setDistrict(split[0]);// 区域
            f.setBlock(split[1]);// 区域
        }
        Elements lis = document.select(".descr_cont_info ul li");
        // 建筑面积
        String area = lis.get(2).select("cite").text();
        if (!"-㎡".equals(area)) {
            f.setPropertyArea(StringUtils.replace(area, ",", "."));
        } else
            f.setPropertyArea(null);
        // 开发商
        String developers = lis.get(3).select("cite").text();
        if (!"-".equals(developers)) {
            f.setDevelopers(developers);
        } else
            f.setDevelopers(null);
        String mainHouse = lis.get(4).select("cite").text();
        if (!"-".equals(mainHouse)) {
            f.setMainHouseType(mainHouse);
        }
        String house_num = lis.get(5).select("cite").text();
        if (!"-".equals(house_num)) {
            String[] number = CrawlerUtils.getNumber(house_num);
            f.setBuildingNum(Integer.valueOf(number[1]));// 楼栋总数
            f.setTotalHouse(Integer.valueOf(number[2]));// 房间套
        }
        String propertyCompany = lis.get(7).select("cite").text();
        if (!"-".equals(propertyCompany)) {
            f.setPropertyCompany(propertyCompany);// 物业公司
        } else
            f.setPropertyCompany(null);
        String greeningRate_str = lis.get(8).select("cite").text();
        if (!"-".equals(greeningRate_str)) {
            BigDecimal greeningRate = CrawlerUtils
                    .stringToBigDecimal(StringUtils.substringBefore(greeningRate_str, "%"), 2);
            f.setGreeningRate(greeningRate);
        } else
            f.setGreeningRate(null);
        String year = lis.get(9).select("cite").text();
        if (!"-".equals(year)) {
            f.setResidenceAge(Integer.valueOf(CrawlerUtils.getNumber(year)[0]));
        }
        String volumeRate_str = lis.get(10).select("cite").text();
        if (!"-".equals(volumeRate_str)) {
            BigDecimal volumeRate = CrawlerUtils.stringToBigDecimal(volumeRate_str, 1);
            f.setVolumeRate(volumeRate);
        }
    }

    private Document parseHtml(String url) throws Exception {
        return Jsoup.parse(httpService.doGet(url));
    }

    /**
     * 获取总页数
     *
     * @param document
     * @return
     */
    public Integer getTotalPage(Document document) {
        Elements elements = document.select(".pagination a");
        int max = 0;
        try {
            for (int i = 1; i < elements.size(); i++) {
                if (!CrawlerUtils.isStr(elements.get(i).text())) {
                    continue;
                }
                if (Integer.valueOf(elements.get(i).text()) > Integer.valueOf(elements.get(i - 1).text())) {
                    max = Integer.valueOf(elements.get(i).text());
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取总页数失败", e);
        }
        return max;
    }

    public void saveDataToDb(Collection<?> T, Integer i) {
        try {
            fangjiaResidenceInfoMapper.save(T);
        } catch (Exception e) {
            LOGGER.error("第" + i + "页数据保存Db失败！！", e);
        }
        LOGGER.debug("第" + i + "页数据保存Db成功！！");
    }

    public void writeStringToFile(String s) {

    }

}
