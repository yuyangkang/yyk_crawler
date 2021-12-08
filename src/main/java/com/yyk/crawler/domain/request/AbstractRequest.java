package com.yyk.crawler.domain.request;

import com.yyk.crawler.domain.analysis.model.pojo.PageData;

import java.util.function.Supplier;

/**
 * @author: yuyangkang
 * @Title: AbstractRequest
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/4 21:05
 */
public abstract class AbstractRequest implements Supplier<PageData> {

     /**
      * 请求地址
      */
     protected String url;

     public AbstractRequest(String url) {
          this.url = url;
     }
}
