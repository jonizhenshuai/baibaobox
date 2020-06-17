package com.baibaoxiang.serviceimpl;

import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.po.DayTotal;
import com.baibaoxiang.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author sheng
 * @create 2019-05-27-19:13
 */
@Service
public class  RedisServiceImpl implements RedisService {

    @Autowired
    JedisClient jedisClient;

    /** 保存阅读量到 redis
     * @param no
     */
    @Override
    public void saveReadNumRedis(String no) {
        if(jedisClient.hexists("readnum",no)){
            //value 值 自增1
            jedisClient.hincrBy("readnum", no,1);
        }else{
            jedisClient.hset("readnum", no,"1");
        }
    }

    /** 保存点赞量到 redis
     * @param no
     */
    @Override
    public void saveLikeNumRedis(String no) {
        if(jedisClient.hexists("likenum", no)){
            //value 值 自增1
            jedisClient.hincrBy("likenum", no,1);
        }else{
            jedisClient.hset("likenum", no,"1");
        }
    }

    /** 获取 redis中的 阅读量数据
     * @return
     * @throws Exception
     */
    @Override
    public List<DayTotal> getReadDataFromRedis() throws Exception{
        // 通过hkeys 获取所有的key值
        Set<String> read_hkeys = jedisClient.hkeys("readnum");
        List<DayTotal> dayTotals = new ArrayList<DayTotal>();
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        // 对所有的key值 进行遍历
        // 并将 key 和 value 信息(转换/封装)成 dayTotal对象
        for(String read_hkey : read_hkeys){
            Integer readnum = new Integer(0);
            List<String> readnums = jedisClient.hmget("readnum", read_hkey);
            for(String num : readnums){
                readnum = (Integer)Integer.valueOf(num);
            }
            DayTotal dayTotal = new DayTotal();
            dayTotal.setNo(read_hkey);
            dayTotal.setTime(date1);
            dayTotal.setDayReadNum(readnum);
            dayTotal.setDayLikeNum(0);
            dayTotals.add(dayTotal);
        }

        return dayTotals;
    }

    /** 获取 redis中的点赞量
     * @return
     * @throws Exception
     */
    @Override
    public List<DayTotal> getLikeDataFromRedis() throws Exception {
        Set<String> like_hkeys = jedisClient.hkeys("likenum");
        List<DayTotal> dayTotals = new ArrayList<DayTotal>();
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        for(String like_hkey : like_hkeys){
            Integer likenum = new Integer(0);
            List<String> likenums = jedisClient.hmget("likenum", like_hkey);
            for(String num : likenums){
                likenum = (Integer)Integer.valueOf(num);
            }
            DayTotal dayTotal = new DayTotal();
            dayTotal.setNo(like_hkey);
            dayTotal.setTime(date1);
            dayTotal.setDayLikeNum(likenum);
            dayTotal.setDayReadNum(0);
            dayTotals.add(dayTotal);
        }
        return dayTotals;
    }

}
