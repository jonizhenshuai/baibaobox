package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.AreaMapper;
import com.baibaoxiang.mapper.SchoolMapper;
import com.baibaoxiang.mapper.custom.SchoolMapperCustom;
import com.baibaoxiang.po.Area;
import com.baibaoxiang.po.AreaExample;
import com.baibaoxiang.po.School;
import com.baibaoxiang.po.SchoolExample;
import com.baibaoxiang.service.SchoolService;
import com.baibaoxiang.tool.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author sheng
 * @create 2019-05-03-15:44
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    JedisClient jedisClient;
    @Autowired
    SchoolMapper schoolMapper;
    @Autowired
    SchoolMapperCustom schoolMapperCustom;
    @Autowired
    AreaMapper areaMapper;
    private final String schoolInfoKey = "School_INFO:";
    @Override
    public int insertSchool(School record) throws Exception {
        String key = schoolInfoKey + record.getNo();
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        return schoolMapper.insert(record);
    }

    @Override
    public int deleteSchool(Integer no) throws Exception {
        deleteKey(no);
        return schoolMapper.deleteByPrimaryKey(no);
    }

    /**
     * 删除通用缓存方法
     * @param schoolNo
     */
    private void deleteKey(Integer schoolNo) {
        String key = schoolInfoKey + schoolNo;
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
    }

    @Override
    public void deleteSchoolBatch(Integer[] no) throws Exception {
        for (int i = 0; i < no.length; i++) {
            deleteKey(no[i]);
            schoolMapper.deleteByPrimaryKey(no[i]);
        }
    }

    @Override
    public void deleteSchoolBySchoolName(String name) throws Exception {
        schoolMapperCustom.deleteSchoolBySchoolName(name);
    }

    @Override
    public School selectSchoolByNo(Integer no) throws Exception {
        return schoolMapper.selectByPrimaryKey(no);
    }

    @Override
    public List<School> selectAllSchool() throws Exception {
        return schoolMapperCustom.selectAllSchool();
    }

    /**
     * 添加缓存机制
     * @return
     * @throws Exception
     */
    @Override
    public List<School> selectDifferentSchoolName() throws Exception {
        if (jedisClient.exists(schoolInfoKey)) {
            return JsonUtils.jsonToList(jedisClient.get(schoolInfoKey),School.class);
        } else {
            SchoolExample schoolExample = new SchoolExample();
            List<School> schoolName = schoolMapper.selectByExample(schoolExample);
            if (schoolName!=null){
                jedisClient.set(schoolInfoKey,JsonUtils.objectToJson(schoolName));
            }
            return schoolName;
        }
    }

    /**
     * 查询学校的方法
     * 添加校区缓存
     * @param schoolNo
     * @return
     * @throws Exception
     */
    @Override
    public List<Area> selectSchoolArea(Integer schoolNo) throws Exception {
        // 添加缓存的原则是，不能够影响现在有的业务逻辑
        // 查询缓存
        /*删除key 存在于添加和删除area的时候，删除*/
        if (schoolNo==0){
            //如果为空值，则抛出异常
            throw new RuntimeException();
        }
        String key = schoolInfoKey + schoolNo;
        if(jedisClient.exists(key)){
            return JsonUtils.jsonToList(jedisClient.get(key),Area.class);
        }
        AreaExample areaExample = new AreaExample();
        AreaExample.Criteria criteria = areaExample.createCriteria();
        criteria.andSchoolnoEqualTo(schoolNo);
        List<Area> areas = areaMapper.selectByExample(areaExample);
        if (areas!=null){
            jedisClient.set(key,JsonUtils.objectToJson(areas));
        }
        return areas;
    }

    @Override
    public Integer selectNoBySchoolName(String name) throws Exception {
        return schoolMapperCustom.selectNoBySchoolName(name);
    }
}
