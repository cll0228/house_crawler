package com.lezhi.crawler.service;

import org.jsoup.nodes.Document;

import java.util.Collection;

/**
 * Created by Cuill on 2016/5/12.
 */
public interface Crawler {
    // 抓取方法
    void start() throws Exception;

    // 获取总页数
    Integer getTotalPage(Document root);

    // 保存数据
    void saveDataToDb(Collection<?> T, Integer integer);

    // 写入文件
    void writeStringToFile(String s);
}
