package com.baibaoxiang.serviceimpl;

import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.AreaMapper;
import com.baibaoxiang.mapper.ArticleMapper;
import com.baibaoxiang.mapper.ArticleTypeMapper;
import com.baibaoxiang.mapper.SchoolMapper;
import com.baibaoxiang.mapper.custom.ArticleTypeMapperCustom;
import com.baibaoxiang.mapper.custom.SchoolMapperCustom;
import com.baibaoxiang.po.*;
import com.baibaoxiang.service.ArticleTypeService;
import com.baibaoxiang.service.SchoolService;
import com.baibaoxiang.tool.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sheng
 * @create 2019-05-03-11:25
 */
@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Autowired
    ArticleTypeMapper articleTypeMapper;
    @Autowired
    ArticleTypeMapperCustom articleTypeMapperCustom;
    @Autowired
    JedisClient jedisClient;
    @Autowired
    ArticleMapper articleMapper;

    private final String key = "Type_INFO:";
    @Override
    public ArticleType selectByPrimaryKey(Integer id) throws Exception {
        return articleTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(ArticleType record) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        return articleTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        return articleTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ArticleType record) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        return articleTypeMapper.insertSelective(record);
    }

    /**
     * 添加缓存机制
     * @return·
     * @throws Exception
     */
    @Override
    public List<ArticleType> selectArticleTypes() throws Exception {
        if (jedisClient.exists(key)){
            String jsonString = jedisClient.get(key);
            return JsonUtils.jsonToList(jsonString,ArticleType.class);
        }else{
            ArticleTypeExample articleTypeExample = new ArticleTypeExample();
            articleTypeExample.setOrderByClause("sequence_num  asc");
            List<ArticleType> articleTypes = articleTypeMapper.selectByExample(articleTypeExample);
            jedisClient.set(key,JsonUtils.objectToJson(articleTypes));
            return articleTypes;
        }
    }

    @Override
    public List<ArticleType> selectArticleTypesSelective() throws Exception {
        return articleTypeMapperCustom.selectArticleTypesSelective();
    }

    @Override
    public ArticleType selectArticleTypeByType(String type) throws Exception {
        return articleTypeMapperCustom.selectArticleTypeByType(type);
    }

    @Override
    public void deleteByType(String type) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
            articleTypeMapperCustom.deleteByType(type);
    }

    @Override
    public void updateSequenceNumByAddOne(Integer oldSequenceNum, Integer newSequenceNum) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        articleTypeMapperCustom.updateSequenceNumByAddOne(oldSequenceNum,newSequenceNum);
    }

    @Override
    public void updateSequenceNumBySubOne(Integer oldSequenceNum, Integer newSequenceNum) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        articleTypeMapperCustom.updateSequenceNumBySubOne(oldSequenceNum,newSequenceNum);
    }

    @Override
    public void updateSequenceNumById(Integer newSequenceNum, Integer id) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        articleTypeMapperCustom.updateSequenceNumById(newSequenceNum, id);
    }

    @Override
    public Integer findMaxSequence() {
        return articleTypeMapperCustom.findMaxSequence();
    }

}
