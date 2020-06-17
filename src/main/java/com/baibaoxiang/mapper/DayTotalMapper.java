package com.baibaoxiang.mapper;

import com.baibaoxiang.po.DayTotal;
import com.baibaoxiang.po.DayTotalExample;
import com.baibaoxiang.po.DayTotalKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DayTotalMapper {
    int countByExample(DayTotalExample example);

    int deleteByExample(DayTotalExample example);

    int deleteByPrimaryKey(DayTotalKey key);

    int insert(DayTotal record);

    int insertSelective(DayTotal record);

    List<DayTotal> selectByExample(DayTotalExample example);

    DayTotal selectByPrimaryKey(DayTotalKey key);

    int updateByExampleSelective(@Param("record") DayTotal record, @Param("example") DayTotalExample example);

    int updateByExample(@Param("record") DayTotal record, @Param("example") DayTotalExample example);

    int updateByPrimaryKeySelective(DayTotal record);

    int updateByPrimaryKey(DayTotal record);
}