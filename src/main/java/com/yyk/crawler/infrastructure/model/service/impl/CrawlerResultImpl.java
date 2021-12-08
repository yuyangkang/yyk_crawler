package com.yyk.crawler.infrastructure.model.service.impl;

import com.yyk.crawler.domain.analysis.model.pojo.WallhavenAnalysisResult;
import com.yyk.crawler.domain.reduction.repository.WallHavenDataRepository;
import com.yyk.crawler.infrastructure.model.dao.CrawlerResultDao;
import com.yyk.crawler.infrastructure.model.entity.CrawlerResult;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CrawlerResultImpl
 * @Description TODO
 * @Date 2021/12/8 13:26
 * @Created by yuyangkang
 */
@Service("CrawlerResult")
public class CrawlerResultImpl implements WallHavenDataRepository {

    @Autowired
    CrawlerResultDao crawlerResultDao;

    @Override
    public void save(WallhavenAnalysisResult result) {
        String type = ClassUtils.getShortClassName(result,"none");
        List<CrawlerResult> data = new ArrayList<>();
        for(String du : result.getDowloadUrl()){
            CrawlerResult t = new CrawlerResult();
            t.setJsonData(du);
            t.setType(type);
            data.add(t);
        }
        crawlerResultDao.saveAll(data);
    }
}
