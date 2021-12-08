package com.yyk.crawler.domain.analysis;

import com.yyk.crawler.domain.spider.SpiderV1;
import com.yyk.crawler.domain.analysis.model.AnaLysisResult;
import com.yyk.crawler.domain.analysis.model.pojo.PageData;

import java.util.function.Supplier;

/**
 * @author: yuyangkang
 * @Title: AbstractAnalysis
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/6 23:16
 */
public abstract class AbstractAnalysis<T extends AnaLysisResult> implements Supplier<T> {

    private String hostName;

    /**
     * 页面数据
     */
    protected PageData pageData;

    /**
     * 执行对象，传入进行下一次修改
     */
    protected SpiderV1 spider;


    public AbstractAnalysis(String hostName) {
        this.hostName = hostName;
    }

    /**
     * 设置下一个爬虫
     */
    public abstract Runnable optNexSpider(T result);

    /**
     * 真正的get方法
     * @return
     */
    public T getResult(){
        T result = get();
        result.setNextSpider(optNexSpider(result));
        return result;
    }

    /**
     * 加载要解析的数据
     * @param pageData
     * @return
     */
    public AbstractAnalysis loadHtmlData(PageData pageData , SpiderV1 spider){
        this.pageData = pageData;
        this.spider = spider;
        return this;
    }
}
