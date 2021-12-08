package com.yyk.crawler.application.service.impl;

import com.yyk.crawler.application.service.SpiderRunService;
import com.yyk.crawler.domain.analysis.model.impl.WallhavenAnalysis;
import com.yyk.crawler.domain.reduction.impl.SaveWallToDataBase;
import com.yyk.crawler.domain.reduction.repository.WallHavenDataRepository;
import com.yyk.crawler.domain.spider.SpiderV1;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: yuyangkang
 * @Title: WallHavenSpider
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/7 0:43
 */
@Service("WallHavenSpider")
public class WallHavenSpider implements SpiderRunService {

    private static SpiderV1 spiderV1 = null;

    @Resource(name = "CrawlerResult")
    WallHavenDataRepository repository;

    @Override
    public void doSpider() {
        SpiderV1.build("https://wallhaven.cc/random?seed=lVEf30&page=2",new WallhavenAnalysis(),3)
                .addReduction(new SaveWallToDataBase(repository))
                .setInterval(10000L)
                .start();
    }
}
