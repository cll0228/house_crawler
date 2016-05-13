package com.lezhi.crawler.util;

/**
 * Created with IntelliJ IDEA.
 * User: Paul Jiang
 * Date: 15-8-12
 * Time: ����1:40
 * To change this template use File | Settings | File Templates.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressUtil {

    /**
     * ��ȡ��ַ��¥�źͷ����
     * @param address
     * @return
     */
    public static String[] analyzeAddressRoom(String address) {
        Pattern p1 = Pattern.compile("(\\w+)(\\u53f7|\\u680b|\\u53f7\\u697c|\\u5e62)(\\w+)(\\u5ba4)?");
        Matcher m = p1.matcher(address);
        if (m.find()) {
            return new String[] { m.group(1), m.group(3)};
        }
        return null;
    }

    /**
     * ��ȡ��ַ�ģ�·��+�ţ�����С����
     * @param address
     * @return
     */
    public static String analyzeAddress(String address) {
        Pattern p1 = Pattern.compile("([^\u5e02|\u53bf]+[\u5e02|\u53bf])?([^\u533a|\u53bf]+[\u533a|\u53bf])?([^\u9547]+\u9547)?(\\W+(\u8def|\u8857|\\W\u9053)\\d+(\u5f04|\u53f7))");
        Pattern p2 = Pattern.compile("([^\u5e02|\u53bf]+[\u5e02|\u53bf])?([^\u533a|\u53bf]+[\u533a|\u53bf])?([^\u9547]+\u9547)?(\\W+(\u8def|\u8857|\\W\u9053))?([^\\d]+)(\\d+(\u53f7|\u680b|\u5e62))?");
        Matcher m = p1.matcher(address);
        if (m.find()) {
            address = m.group(4);
        } else {
            m = p2.matcher(address);
            if (m.find())
                address = m.group(6);
        }
        return address;
    }

    public static void main(String[] args) {
        String address = "�´�·378�Ŵ������";
        System.out.println(analyzeAddress(address));
        String[] r = analyzeAddressRoom(address);
        if (null != r)
        System.out.println(r[0] + ", " + r[1]);
    }
}
