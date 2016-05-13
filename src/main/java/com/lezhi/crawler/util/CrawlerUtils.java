package com.lezhi.crawler.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Cuill on 2016/5/13.
 */
public class CrawlerUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerUtils.class);

    /**
     * 数字 返回true 汉字 返回false ，数字返回true
     *
     * @param str
     * @return
     */
    public static boolean isStr(String str) {
        return str.matches("\\d+(.\\d+)?");
    }

    /**
     * 字符串转BigDecimal
     *
     * @param str
     * @param num 保留小数位数
     * @return
     */
    public static BigDecimal stringToBigDecimal(String str, int num) {
        try {
            BigDecimal bigDecimal = new BigDecimal(str);
            return bigDecimal.setScale(num, bigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            LOGGER.error("转化异常,参数=" + str, e);
        }
        return null;
    }

    /**
     * 截取字符串数字
     * 
     * @param str
     * @return
     */
    public static String[] getNumber(String str) {
        return str.split("\\D+");
    }
}
