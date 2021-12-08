package com.yyk.crawler.domain.analysis.model.impl;

import com.yyk.crawler.domain.analysis.model.pojo.WallhavenAnalysisResult;
import com.yyk.crawler.domain.analysis.AbstractAnalysis;
import com.yyk.crawler.domain.request.model.impl.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yuyangkang
 * @Title: WallhavenAnalysis
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/6 23:13
 */
@Slf4j
public class WallhavenAnalysis extends AbstractAnalysis<WallhavenAnalysisResult> {

    private static final String HOST= "w.wallhaven.cc";


    private static AtomicLong PAGE = new AtomicLong(1L);

    public WallhavenAnalysis() {
        super(HOST);
    }

    @Override
    public WallhavenAnalysisResult get() {
        Map<String,String> idAndType = new HashMap<>();
        List<String> ids = new ArrayList<>();
        for (Element e: Jsoup.parse(this.pageData.getHtmlString()).select(".thumb-listing-page").select("figure")) {
            String picId = e.select("figure").attr("data-wallpaper-id");
            String picType = e.select("span").eachText().contains("PNG") ? "png" : "jpg";
            idAndType.put(picId,picType);
            ids.add(picId);
        }
        WallhavenAnalysisResult result = new WallhavenAnalysisResult();
        result.setIdAndType(idAndType);
        result.setIds(ids);
        result.setNextUrl(this.pageData.getRequestUrl());
        return result;
    }


    @Override
    public Runnable optNexSpider(WallhavenAnalysisResult result) {
        Long page;
        synchronized (PAGE){
            page = PAGE.getAndIncrement();
        }
        String nextUrl = result.getNextUrl().replaceAll("page=[\\d]*" ,"page="+page);
        log.info("**************\nthreadName:{},\npage={},\nnextUrl = {}\n************",Thread.currentThread().getName(),page,nextUrl);
        return this.spider.setRequest(new BaseRequest(nextUrl));
    }
}
