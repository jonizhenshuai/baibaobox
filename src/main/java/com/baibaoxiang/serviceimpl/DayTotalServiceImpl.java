package com.baibaoxiang.serviceimpl;

import com.baibaoxiang.mapper.DayTotalMapper;
import com.baibaoxiang.mapper.custom.DayTotalMapperCustom;
import com.baibaoxiang.po.DayTotal;
import com.baibaoxiang.po.DayTotalKey;
import com.baibaoxiang.po.ReadLikeNumber;
import com.baibaoxiang.service.DayTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * @author sheng
 * @create 2019-05-03-15:44
 */
@Service
public class DayTotalServiceImpl implements DayTotalService {

    @Autowired
    DayTotalMapper dayTotalMapper;
    @Autowired
    DayTotalMapperCustom dayTotalMapperCustom;

    @Override
    public DayTotal selectByPrimaryKey(DayTotalKey key) throws Exception {
        return dayTotalMapper.selectByPrimaryKey(key);
    }

    @Override
    public int deleteByPrimaryKey(DayTotalKey key) throws Exception {
        return dayTotalMapper.deleteByPrimaryKey(key);
    }

    @Override
    public void deleteDayTotalByTime(Date time) throws Exception{
            dayTotalMapperCustom.deleteDayTotalByTime(time);
    }

    @Override
    public int insert(DayTotal record) throws Exception {
        return dayTotalMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(DayTotal record) throws Exception {
        return dayTotalMapper.updateByPrimaryKey(record);
    }


    @Override
    public void updateReadNum( Date time, Integer num,String no) throws Exception {
        dayTotalMapperCustom.updateReadNum(time,num,no);
    }

    @Override
    public void updateLikeNum(Date time, Integer num, String no) throws Exception {
        dayTotalMapperCustom.updateLikeNum(time,num,no);
    }

    @Override
    public ReadLikeNumber dayTotal(Date time) throws Exception {
        return dayTotalMapperCustom.dayTotal(time);
    }

    @Override
    public ReadLikeNumber dayTotalArea(Date time, String area) throws Exception {
        return dayTotalMapperCustom.dayTotalArea(time,area);
    }

    @Override
    public ReadLikeNumber dayTotalType(Date time, String type) throws Exception {
        return dayTotalMapperCustom.dayTotalType(time,type);
    }

    @Override
    public ReadLikeNumber dayTotalTypeArea(Date time, String area, String type) throws Exception {
        return dayTotalMapperCustom.dayTotalTypeArea(time,area,type);
    }

    @Override
    public ReadLikeNumber dayTotalNo(Date time, String no) throws Exception {
        return dayTotalMapperCustom.dayTotalNo(time,no);
    }

}
