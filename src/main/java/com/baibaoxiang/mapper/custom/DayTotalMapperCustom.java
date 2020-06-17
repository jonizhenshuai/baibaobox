package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.ReadLikeNumber;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;


/**
 * @author sheng
 * @create 2019-05-09-20:20
 */
public interface DayTotalMapperCustom {

    /** 根据日期批量删除 DayTotal
     * @param time
     * @throws Exception
     */
    void deleteDayTotalByTime(Date time);
    /**
     * 更新某天 某推文的阅读量
     *
     * @param no
     * @param time
     * @param num
     * @throws Exception
     */
    void updateReadNum(@Param("time") Date time, @Param("num") Integer num, @Param("no") String no) throws Exception;

    /**
     * 更新某天 某推文的点赞数
     *
     * @param no
     * @param time
     * @param num
     * @throws Exception
     */
    void updateLikeNum(@Param("time") Date time, @Param("num") Integer num, @Param("no") String no) throws Exception;

    /**
     * 网站一天的总点赞数和阅读量 （超级管理员使用）
     *
     * @param time
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotal(Date time) throws Exception;

    /**
     * 某校区的 某天的总浏览量和点赞数（超级管理员和该地区管理员使用）
     *
     * @param time
     * @param area
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotalArea(@Param("time") Date time, @Param("area") String area) throws Exception;

    /**
     * 某类型的所有推文 某天的总浏览量和点赞数（超级管理员使用）
     *
     * @param type
     * @param time
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotalType(@Param("time") Date time, @Param("type") String type) throws Exception;

    /**
     * 某校区的 某类型的所有推文 某天的总浏览量和点赞数（超级管理员和该地区管理员使用）
     *
     * @param type
     * @param area
     * @param time
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotalTypeArea(@Param("time") Date time, @Param("area") String area, @Param("type") String type) throws Exception;

    /**
     * 某篇推文 某天的点赞数
     *
     * @param time
     * @param no
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotalNo(@Param("time") Date time, @Param("no") String no) throws Exception;


}
