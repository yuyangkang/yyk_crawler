package com.yyk.crawler.domain.reduction.impl;

import com.yyk.crawler.domain.analysis.model.AnaLysisResult;
import com.yyk.crawler.domain.reduction.AbstractReduction;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: yuyangkang
 * @Title: PrintReduction
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/7 2:07
 */
@Slf4j
public class PrintReduction implements AbstractReduction {


    @Override
    public void redution(AnaLysisResult redult) {
        log.info("**************\nredutionResult = {}\n thread = {}\n*************",redult,Thread.currentThread().getName());
    }
}
