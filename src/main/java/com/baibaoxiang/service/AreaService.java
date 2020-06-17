package com.baibaoxiang.service;

import com.baibaoxiang.po.Area;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sheng
 * @create 2019-07-03-16:05
 */
public interface AreaService {

    /** 通过id 获取校区信息
     * @param id
     * @return
     * @throws Exception
     */
    Area findAreaById(Integer id) throws Exception;

    /** 获取所有的校区信息
     * @return
     * @throws Exception
     */
    List<Area> findAllAreas() throws Exception;

    /** 获取某学校的所有校区
     * @param name
     * @return
     * @throws Exception
     */
    List<Area> findAreaBySchoolName(String name) throws Exception;

    /** 更新
     * @param area
     * @throws Exception
     */
    void updateArea(Area area) throws Exception;

    /**
     * @param id
     * @throws Exception
     */
    void deleteAreaById(Integer id) throws Exception;

    /** 批量删除area
     * @param ids
     * @throws Exception
     */
    void deleteAreaBatch(Integer ids[]) throws Exception;

    /** 添加
     * @param no,schoolNo,name
     * @throws Exception
     */
    void insertArea(Integer no,Integer schoolNo, String name) throws Exception;

    /**
     *
     * 获取地区表最大一个主键
     */
    Integer findMaxAreaNo();
}
