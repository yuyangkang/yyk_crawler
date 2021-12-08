package com.yyk.crawler.infrastructure.model.dao;

import com.yyk.crawler.infrastructure.model.entity.CrawlerResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Classname CrawlerResultDao
 * @Description TODO
 * @Date 2021/12/8 13:25
 * @Created by yuyangkang
 */
@Repository
public interface CrawlerResultDao extends JpaRepository<CrawlerResult ,Long> {
}
