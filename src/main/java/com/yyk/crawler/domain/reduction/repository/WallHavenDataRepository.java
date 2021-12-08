package com.yyk.crawler.domain.reduction.repository;

import com.yyk.crawler.domain.analysis.model.pojo.WallhavenAnalysisResult;

/**
 * @Classname WallHavenDataSave
 * @Description TODO
 * @Date 2021/12/8 13:04
 * @Created by yuyangkang
 */
public interface WallHavenDataRepository {

    void save(WallhavenAnalysisResult result);

}
