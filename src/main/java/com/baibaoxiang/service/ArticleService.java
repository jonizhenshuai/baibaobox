package com.baibaoxiang.service;


import com.baibaoxiang.po.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author sheng
 * @create 2019-04-23-00:18
 */
public interface ArticleService {


    /**
     * 通过主键(no)查询文章
     * @param no
     * @return Article
     * @throws Exception
     */
    Article selectByPrimaryKey(String no) throws Exception;

    /** 通过 创建的时间段 找出批量文章的编号
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<String> selectNoByCreateTime(Date startTime, Date endTime) throws Exception;
    /**
     * 按文章的类型(type），地区(area)查询文章
     * 顺序依据:置顶号（号数低的在前面），文章发表时间（最近发表的在前面）
     * @param typeNo,areaNo
     * @return 返回一个Article类型的链表
     * @throws Exception
     */
    List<Article> selectByTypeArea(Integer typeNo, Integer areaNo) throws Exception;

    /**
     * 重载selectByTypeArea方法
     * @param areaNo
     * @param typeNo
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    List<Article> selectByTypeArea(Integer areaNo, Integer typeNo,Integer page,Integer rows) throws Exception;

    /** 查询所有的推文 置顶/发布时间 排序
     * @return
     * @throws Exception
     */
    List<Article> selectAllArticles() throws Exception;

    /** 查询某类型的所有推文  置顶/发布时间 排序
     * @return
     * @throws Exception
     */
    List<Article> selectByType(Integer typeNo) throws Exception;
    /**
     * 添加文章
     * @param record
     * @return int
     * @throws Exception
     */
    int insertSelective(Article record) throws Exception;


    /**
     * 删除文章
     * @param no
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKey(String no) throws Exception;

    /** 批量删除文章
     * @param no
     * @throws Exception
     */
    void deleteArticleBatch(String no[]) throws Exception;


    /**
     * 修改文章
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(Article record) throws Exception;

    /** 更新 阅读量
     * @param no
     * @param readNum
     * @throws Exception
     */
    void updateReadNum(String no, Integer readNum) throws Exception;

    /** 更新 点赞量
     * @param no
     * @param likeNum
     * @throws Exception
     */
    void updateLikeNum(String no, Integer likeNum) throws Exception;

    /** 同时更新 阅读量和点赞量
     * @param no
     * @param readNum
     * @param likeNum
     * @throws Exception
     */
    void updateReadLikeNum(String no, Integer readNum, Integer likeNum) throws Exception;

    /**
     * 查询所有顶置文章
     * @param areaNo
     * @return
     * @throws Exception
     */
    List<Article> selectTopArticle(Integer areaNo,Integer page, Integer rows ) throws Exception;

    /** 设置 文章置顶
     * @param no
     * @param top
     * @throws Exception
     */
    void setTopArticle(String no, Integer top) throws Exception;

}


