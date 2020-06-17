package com.baibaoxiang.serviceimpl;

import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.AreaMapper;
import com.baibaoxiang.mapper.custom.AreaMapperCustom;
import com.baibaoxiang.po.Area;
import com.baibaoxiang.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sheng
 * @create 2019-07-03-17:09
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    JedisClient jedisClient;
    @Autowired
    AreaMapper areaMapper;
    @Autowired
    AreaMapperCustom areaMapperCustom;

    @Override
    public Area findAreaById(Integer id) throws Exception {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Area> findAllAreas() throws Exception {
        return areaMapperCustom.findAllAreas();
    }

    @Override
    public List<Area> findAreaBySchoolName(String name) throws Exception {
        return areaMapperCustom.findAreaBySchoolName(name);
    }

    @Override
    public void updateArea(Area area) throws Exception {
        areaMapper.updateByPrimaryKeySelective(area);
    }

    @Override
    public void deleteAreaById(Integer id) throws Exception {
        areaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAreaBatch(Integer[] ids) throws Exception {
        for(Integer id : ids){
            areaMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void insertArea(Integer no,Integer schoolNo, String name) throws Exception {
        areaMapperCustom.insertArea(no,schoolNo,name);
    }

    @Override
    public Integer findMaxAreaNo() {
        return areaMapperCustom.findMaxAreaNo();
    }


}
