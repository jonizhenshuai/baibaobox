package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.ArticleType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** ArticleTypeMapper的扩展接口
 * @author sheng
 * @create 2019-05-03-11:15
 */
public interface ArticleTypeMapperCustom {

    /** 查询所有的 文章类型
     * @return
     */
    List<ArticleType> selectArticleTypes();

    /** 查询除了"推荐"外 的所有其他文章类型
     * @return
     */
    List<ArticleType> selectArticleTypesSelective();

    /**
     * 通过文章类型名称删除
     *
     */
    void deleteByType(String type);

    /** 通过文章类型 查询
     * @param type
     * @return
     */
    ArticleType selectArticleTypeByType(String type);

    /** 将某id 的文章类型 的顺序号更改
     * @param id
     * @param newSequenceNum
     */
    void updateSequenceNumById (@Param("newSequenceNum") Integer newSequenceNum, @Param("id") Integer id);

    /** 文章类型的顺序号 减一。
     * @param oldSequenceNum
     * @param newSequenceNum
     */
    void updateSequenceNumBySubOne(@Param("oldSequenceNum") Integer oldSequenceNum, @Param("newSequenceNum") Integer newSequenceNum);

    /** 文章类型的顺序号 加一。
     * @param oldSequenceNum
     * @param newSequenceNum
     */
    void updateSequenceNumByAddOne(@Param("oldSequenceNum") Integer oldSequenceNum, @Param("newSequenceNum") Integer newSequenceNum);

    /**
     * 查找最后面的类型主键的顺序
     * @return
     */
    Integer findMaxSequence();
}
