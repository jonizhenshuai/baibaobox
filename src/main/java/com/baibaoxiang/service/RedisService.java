package com.baibaoxiang.service;

import com.baibaoxiang.po.DayTotal;

import java.util.List;

/**
 * @author sheng
 * @create 2019-05-27-19:06
 */
public interface RedisService {


    /** 保存阅读量 到redis
     * @param no
     */
    void saveReadNumRedis(String no) throws Exception;

    /** 保存阅读量 到redis
     * @param no
     */
    void saveLikeNumRedis(String no) throws Exception;

    /** 获取阅读数据
     * @return
     * @throws Exception
     */
    List<DayTotal> getReadDataFromRedis() throws Exception;

    /** 获取点赞数的数据
     * @return
     * @throws Exception
     */
    List<DayTotal> getLikeDataFromRedis() throws Exception;
}
