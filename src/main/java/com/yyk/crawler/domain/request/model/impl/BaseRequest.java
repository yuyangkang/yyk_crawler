package com.yyk.crawler.domain.request.model.impl;

import com.yyk.crawler.domain.analysis.model.pojo.PageData;
import com.yyk.crawler.domain.request.AbstractRequest;
import com.yyk.crawler.infrastructure.utils.HttpClienUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuyangkang
 * @Title: BaseRequest
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/5 23:08
 */
@Slf4j
public class BaseRequest extends AbstractRequest {

    private List<NameValuePair> requestParmas = new ArrayList<>();

    public BaseRequest(String url) {
        super(url);
    }

    @Override
    public PageData get() {
        try {
            PageData result = new PageData();
            result.setRequestUrl(this.url);
            result.setHtmlString(HttpClienUtils.getRequest(this.url,this.requestParmas));
            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new PageData();
        }

    }
}
