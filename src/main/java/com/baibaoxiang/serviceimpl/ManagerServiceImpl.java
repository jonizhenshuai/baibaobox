package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.ManagerMapper;
import com.baibaoxiang.mapper.custom.ManagerMapperCustom;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ManagerService;
import com.baibaoxiang.tool.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** ManagerService接口 的实现类
 * @author sheng
 * @create 2019-04-29-10:04
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    ManagerMapperCustom managerMapperCustom;
    @Autowired
    JedisClient jedisClient;

     private String ManagerInfoKey = "Manager_INFO";
    @Override
    public Manager findManagerByUsername(String username) throws Exception {
        return managerMapperCustom.findManagerByUsername(username);
    }

    @Override
    public Manager findManagerWithPassword_salt(String username) throws Exception {
        return managerMapperCustom.findManagerWithPassword_salt(username);
    }

    @Override
    public List<Manager> findManagersByTitle(String title) throws Exception {
        return managerMapperCustom.findManagersByTitle(title);
    }

    @Override
    public int insert(Manager record) throws Exception {
        return managerMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Manager record) throws Exception {
        return managerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer username) throws Exception {
        return managerMapper.deleteByPrimaryKey(username);
    }

    @Override
    public void deleteManagerBatch(String usernames) throws Exception {
        String username [] = usernames.split(",");
        for(int i = 0; i< username.length; i++){
            managerMapperCustom.deleteManagerByUsername(username[i]);
        }
    }

    @Override
    public Manager findManagerNameById(Integer id) throws Exception {
        return managerMapperCustom.findManagerNameById(id);
    }
}
