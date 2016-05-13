package com.lezhi.crawler.controller;

import com.lezhi.crawler.service.FangJiaCrawler;
import com.lezhi.crawler.service.LJResidenceCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lezhi.crawler.service.LJHouseCrawler;

/**
 * Created by Cuill on 2016/5/9.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private LJHouseCrawler ljHouseCrawlerService;

    @Autowired
    private LJResidenceCrawler ljResidenceCrawler;

    @Autowired
    private FangJiaCrawler fangJiaCrawler;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public void getResidenceByResidName() throws Exception {
        ljHouseCrawlerService.start();
    }

    @ResponseBody
    @RequestMapping(value = "/residenceCrawler", method = RequestMethod.GET)
    public void residenceCrawler() throws Exception {
        ljResidenceCrawler.ljResidCrawlerMethod();
    }

    @ResponseBody
    @RequestMapping(value = "/fangjia", method = RequestMethod.GET)
    public void fangjiaresidenceCrawler() throws Exception {
        fangJiaCrawler.start();
    }

}
