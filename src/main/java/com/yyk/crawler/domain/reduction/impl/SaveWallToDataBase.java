package com.yyk.crawler.domain.reduction.impl;

import com.yyk.crawler.domain.analysis.model.pojo.WallhavenAnalysisResult;
import com.yyk.crawler.domain.reduction.AbstractReduction;
import com.yyk.crawler.domain.reduction.repository.WallHavenDataRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuyangkang
 * @Title: SaveWallToDataBase
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/8 1:23
 */
@Slf4j
public class SaveWallToDataBase implements AbstractReduction<WallhavenAnalysisResult> {

    private WallHavenDataRepository repository;

    public SaveWallToDataBase(WallHavenDataRepository repository) {
        this.repository = repository;
    }

    private final String dowloadUrlBuilder = "https://w.wallhaven.cc/full/@1/wallhaven-@2.@3";

    @Override
    public void redution(WallhavenAnalysisResult result) {
        List<String> dowloadUrls = new ArrayList<>();
        for(String id : result.getIds()){
            String dl = dowloadUrlBuilder.replace("@1",id.substring(0,2)).replace("@2",id).replace("@3",result.getIdAndType().get(id));
            dowloadUrls.add(dl);
        }
        result.setDowloadUrl(dowloadUrls);
        repository.save(result);
    }
}
