package com.yyk.crawler.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Classname JsonUtil
 * Description json处理的工具类
 * Date 2021/7/21 11:04
 * Created by yuyangkang
 */
public class YuJsonUtil {

    /**
     * 转换jsonString
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        if(null == obj){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(obj) ;
        }catch (JsonProcessingException e){
            throw new RuntimeException("json解析失败！");
        }
    }

    /**
     * 换对象
     * @param jss
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>  T toObject(String jss , Class<T> clazz) {
        if(StringUtils.isBlank(jss)){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(jss , clazz) ;
        }catch (JsonProcessingException e){
            throw new RuntimeException("json解析失败！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json解析失败！");
        }
    }

    public static <T>  T objToObject(Object object , Class<T> clazz){
        return toObject( toJsonString(object), clazz);
    }
}
