package com.yyk.crawler.infrastructure.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Classname CrawlerResult
 * @Description 爬虫储存的基本类
 * @Date 2021/12/8 13:17
 * @Created by yuyangkang
 */
@Data
@Entity
@Table(name = "crawler_result")
public class CrawlerResult {

    @Id
    @GeneratedValue(generator = "idGeneratorCrawlerResult")
    @GenericGenerator(name ="idGeneratorCrawlerResult" ,strategy = "com.yyk.crawler.infrastructure.utils.SnowFlakeIdGenerator")
    @Column(name = "ID", unique = true, nullable=false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime = LocalDateTime.now();

    private String jsonData;

    private String type;

}
