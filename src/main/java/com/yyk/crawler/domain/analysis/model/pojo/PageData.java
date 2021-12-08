package com.yyk.crawler.domain.analysis.model.pojo;

import lombok.Data;

/**
 * @author: yuyangkang
 * @Title: PageData
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/7 23:41
 */
@Data
public class PageData {

    private String requestUrl;

    private String htmlString;

    public PageData(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public PageData() {
    }
}
