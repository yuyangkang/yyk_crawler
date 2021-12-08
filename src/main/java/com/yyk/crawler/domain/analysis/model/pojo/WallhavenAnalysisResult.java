package com.yyk.crawler.domain.analysis.model.pojo;

import com.yyk.crawler.domain.analysis.model.AnaLysisResult;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: yuyangkang
 * @Title: WallhavenAnalysisResult
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/6 23:25
 */
@Data
public class WallhavenAnalysisResult extends AnaLysisResult {

    private Map<String,String> idAndType;

    private List<String> ids;

    private String nextUrl;

    private List<String> dowloadUrl;

}
