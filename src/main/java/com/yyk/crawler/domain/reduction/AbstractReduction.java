package com.yyk.crawler.domain.reduction;

import com.yyk.crawler.domain.analysis.model.AnaLysisResult;

/**
 * @author: yuyangkang
 * @Title: AbstractReduction
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/7 2:05
 */
public interface AbstractReduction<T extends AnaLysisResult> {

    void redution(T result);

}
