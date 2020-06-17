package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.Article;
import org.apache.ibatis.annotations.Param;
import sun.rmi.server.InactiveGroupException;

import java.sql.Date;
import java.util.List;

/**
 * @author sheng
 * @create 2019-04-27-11:11
 */
public interface ArticleMapperCustom {


    /**
     * @param typeNo
     * @param areaNo
     * @return
     */
    List<Article> selectByTypeArea(@Param("typeNo") Integer typeNo, @Param("areaNo") Integer areaNo);


    List<Article> selectAllArticles();

    /** 查询某类型的所有推文
     * @param typeNo
     * @return
     */
    List<Article> selectByType(@Param("typeNo") Integer typeNo);

    /** 通过 创建的时间段 找出批量文章的编号。
     * @param startTime
     * @param endTime
     * @return List<String>
     */
    List<String> selectNoByCreateTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /** 更新 阅读量
     * @param no
     * @param readNum
     */
    void updateReadNum(@Param("no") String no, @Param("readNum") Integer readNum);

    /** 更新 点赞量
     * @param no
     * @param likeNum
     */
    void updateLikeNum(@Param("no") String no, @Param("likeNum") Integer likeNum);

    /** 同时更新 阅读量和点赞量
     * @param no
     * @param readNum
     * @param likeNum
     */
    void updateReadLikeNum(@Param("no") String no, @Param("readNum") Integer readNum, @Param("likeNum") Integer likeNum);


    /** 设置置顶
     * @param no
     * @param top
     */
    void setTopArticle(@Param("no") String no,@Param("top") Integer top);

    void insertArticle();

}
