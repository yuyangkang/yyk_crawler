package com.yyk.crawler.domain.analysis.model;

/**
 * @author: yuyangkang
 * @Title: AnaLysisResult
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/7 0:26
 */
public abstract class AnaLysisResult{

    /**
     * 继续执行的爬虫
     */
    protected Runnable nextSpider = null;

    public void setNextSpider(Runnable nextSpider) {
        this.nextSpider = nextSpider;
    }

    public Runnable getNextSpider() {
        return nextSpider;
    }
}
