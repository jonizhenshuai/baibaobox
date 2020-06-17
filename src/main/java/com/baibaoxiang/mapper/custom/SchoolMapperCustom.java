package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.School;

import java.util.List;

/**
 * @author sheng
 * @create 2019-05-08-11:47
 */
public interface SchoolMapperCustom {

    /** 通过id 查询
     * @param no
     * @return
     * @throws Exception
     */
    School selectSchoolById(Integer no) throws Exception;

    /** 获取所有的校区信息
     * @return
     * @throws Exception
     */
    List<School> selectAllSchool () throws Exception;

    /**通过校名删除学校（即是 删除该校名的所有校区）
     * @param name
     */
    void deleteSchoolBySchoolName(String name);

    /** 通过校名 获取该校名的编号
     * @param name
     */
    Integer selectNoBySchoolName(String name);


    /**
     * @return
     */
    List<String> selectDifferentSchoolName(String name);
}
