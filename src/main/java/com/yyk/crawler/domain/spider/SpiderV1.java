package com.yyk.crawler.domain.spider;

import com.yyk.crawler.domain.analysis.model.AnaLysisResult;
import com.yyk.crawler.domain.reduction.AbstractReduction;
import com.yyk.crawler.domain.reduction.impl.PrintReduction;
import com.yyk.crawler.domain.request.AbstractRequest;
import com.yyk.crawler.domain.request.model.impl.BaseRequest;
import com.yyk.crawler.domain.analysis.AbstractAnalysis;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yuyangkang
 * @Title: SpiderV1
 * @ProjectName: demo
 * @Description:
 * @date: 2021/12/4 21:32
 */
@Slf4j
public class SpiderV1<R extends AnaLysisResult> implements Runnable {

    private SpiderV1() {
    }

    /**
     * 私有的线程池
     */
    private ExecutorService threadPoll;

    /**
     * 爬虫的数量
     */
    private Integer syncCount = 1 ;

    /**
     * 最大执行次数
     */
    private Integer maxRun = Integer.MAX_VALUE;
    public SpiderV1 setMaxRun(Integer maxRun){
        this.maxRun =maxRun;
        return this;
    }

    /**
     * 间隔时间 默认1000ms
     */
    private Long interval = 5000L;
    public SpiderV1 setInterval(Long interval){
        this.interval =interval;
        return this;
    }

    /**
     * 执行次数
     */
    private AtomicLong runNum = new AtomicLong(0L);


    /**
     * 请求网站信息
     */
    private AbstractRequest request;
    public SpiderV1 setRequest(AbstractRequest request){
        this.request =request;
        return this;
    }

    /**
     * 解析请求
     */
    private AbstractAnalysis<R> analysis;
    public SpiderV1 setAnalysis(AbstractAnalysis analysis){
        this.analysis =analysis;
        return this;
    }

    /**
     * 结果处理
     */
    private List<AbstractReduction> reductionList= new ArrayList<>();
    public SpiderV1 addReduction(AbstractReduction reduction){
        this.reductionList.add(reduction);
        return this;
    }


    /**
     * 爬虫模式
     */
    private Mode mode = Mode.CYCLE;

    @Override
    public void run() {
        R result = (R) analysis.loadHtmlData(request.get(),this).getResult();
        for (AbstractReduction reduction : reductionList){
            reduction.redution(result);
        }
        runNum.getAndIncrement();
        if((runNum.get()*syncCount >= maxRun)){
            return;
        }
        if(mode.compareTo(Mode.CYCLE) == 0){
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(result.getNextSpider() != null){
                result.getNextSpider().run();
            }
        }
    }

    /**
     * 启动方法！
     */
    public void start(){
        if(this.reductionList.size() == 0){
            this.reductionList.add(new PrintReduction());
        }
        for(int i = 0 ; i <syncCount ; i++){
            this.threadPoll.execute(this);
        }
    }

    public enum Mode{
        CYCLE,
        TIME,
        NUM;
    }


    public static SpiderV1 build(String url,AbstractAnalysis analysis ,Integer syncCount){
        SpiderV1 spiderV1 = new SpiderV1();
        spiderV1.request = new BaseRequest(url);
        spiderV1.analysis=analysis;
        spiderV1.syncCount =syncCount;
        spiderV1.threadPoll =  Executors.newFixedThreadPool(syncCount);
        return spiderV1;
    }

    public static SpiderV1 build(String url,AbstractAnalysis analysis){
        SpiderV1 spiderV1 = new SpiderV1();
        spiderV1.request = new BaseRequest(url);
        spiderV1.analysis=analysis;
        return spiderV1;
    }
}
