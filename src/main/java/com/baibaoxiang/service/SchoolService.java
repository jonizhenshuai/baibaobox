package com.baibaoxiang.service;
import com.baibaoxiang.po.Area;
import com.baibaoxiang.po.School;
import java.util.List;

/**
 * @author sheng
 * @create 2019-05-03-15:45
 */
public interface SchoolService {


    /** 添加学校
     * @param record 学校
     * @return
     * @throws Exception
     */
    int insertSchool(School record) throws Exception;

    /** 删除学校
     * @param no
     * @return
     * @throws Exception
     */
    int deleteSchool(Integer no) throws Exception;

    /** 批量删除学校
     * @param no
     * @return
     * @throws Exception
     */
    void deleteSchoolBatch(Integer no[]) throws Exception;

    /** 通过校名删除学校（即是 删除该校名的所有校区）
     * @param name
     * @throws Exception
     */
   void deleteSchoolBySchoolName(String name) throws Exception;

    /** 查询学校信息 通过校区的编号
     * @param no
     * @return
     * @throws Exception
     */
    School selectSchoolByNo(Integer no) throws Exception;

    /** 获取所有学校的信息 （超级管理员干的）
     * @return
     * @throws Exception
     */
    List<School> selectAllSchool() throws Exception;

    /**
     * 查询所有不重复的学校名称
     * @return
     * @throws Exception
     */
    List<School> selectDifferentSchoolName() throws Exception;

    /**
     * 查询校区
     * @return
     * @throws Exception
     */
    List<Area> selectSchoolArea(Integer schoolNo) throws Exception;

    /** 通过校名 获取该校名的编号
     * @param name
     * @return
     * @throws Exception
     */
    Integer selectNoBySchoolName(String name) throws Exception;
}
