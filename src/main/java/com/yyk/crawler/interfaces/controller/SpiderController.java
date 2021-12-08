package com.yyk.crawler.interfaces.controller;

import com.yyk.crawler.application.service.impl.WallHavenSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuyangkang
 * @Title: SpiderController
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/7 1:23
 */
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    WallHavenSpider wallHavenSpider;

    @GetMapping("/wallHaven")
    public String wallHavenSpider(){
        wallHavenSpider.doSpider();
        return "ok";
    }

}
