package com.yyk.crawler.interfaces.controller;

import com.yyk.crawler.domain.request.model.impl.BaseRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2021/12/3 17:08
 * @Created by yuyangkang
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/t1")
    public void t1(){

        new BaseRequest("https://wallhaven.cc/toplist?page=2").get();

    }

}
