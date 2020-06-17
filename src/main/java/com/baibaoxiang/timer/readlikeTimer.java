package com.baibaoxiang.timer;

import com.baibaoxiang.po.DayTotal;
import com.baibaoxiang.po.DayTotalKey;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.DayTotalService;
import com.baibaoxiang.service.RedisService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author sheng
 * @create 2019-05-27-21:47
 */
@Component
public class readlikeTimer {

    @Autowired
    DayTotalService dayTotalService;
    @Autowired
    RedisService redisService;
    @Autowired
    ArticleService articleService;

    /** 删除三十天前的 DayTotal记录
     *  0秒 0分 0时 *天 *月 ？周
     *  每天的 凌晨3点整点 删除
     * @throws Exception
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteDayTotalBatch() throws Exception{
        Date now = new Date();
        Date startDate = DateUtils.addDays(now,-30);
        java.sql.Date time = new java.sql.Date(startDate.getTime());
        dayTotalService.deleteDayTotalByTime(time);
    }

    /** 定时 插入/更新 阅读量和点赞量
     *  每两小时更新一次  （开发阶段 每分钟更新一次）
     * @throws Exception
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void updateReadLikeNum() throws Exception{

        List<DayTotal> dayTotalsRead = redisService.getReadDataFromRedis();
        List<DayTotal> dayTotalsLike = redisService.getLikeDataFromRedis();
        DayTotalKey dayTotalKey = new DayTotalKey();
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        //对阅读量的操作
        for(DayTotal dayTotal : dayTotalsRead){
            dayTotalKey.setNo(dayTotal.getNo());
            dayTotalKey.setTime(date1);
            //查询dayTotal表  是否已经插入了该字段  不存在则插入  存在则更新
            if(dayTotalService.selectByPrimaryKey(dayTotalKey) == null){
                dayTotalService.insert(dayTotal);
            }else{
                dayTotalService.updateReadNum(date1,dayTotal.getDayReadNum(),dayTotal.getNo());
            }
            //更新 article表的 read_num
            articleService.updateReadNum(dayTotal.getNo(),dayTotal.getDayReadNum());
        }

        //对点赞量的操作
        for(DayTotal dayTotal : dayTotalsLike){
            dayTotalKey.setNo(dayTotal.getNo());
            dayTotalKey.setTime(date1);
            //查询dayTotal表 是否已经插入了该该字段  不存在则插入  存在则更新
            if(dayTotalService.selectByPrimaryKey(dayTotalKey) == null){
                dayTotalService.insert(dayTotal);
            }else{
                dayTotalService.updateLikeNum(date1,dayTotal.getDayLikeNum(),dayTotal.getNo());
            }
            //更新 article 表中的 like_num
            articleService.updateLikeNum(dayTotal.getNo(),dayTotal.getDayLikeNum());
        }
    }
}
