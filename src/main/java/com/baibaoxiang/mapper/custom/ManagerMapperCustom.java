package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.Manager;

import java.util.List;

/**
 * @author sheng
 * @create 2019-04-29-18:42
 */
public interface ManagerMapperCustom {

    /** 通过管理员名称查询 管理员信息 (不携带密码,盐)
     * @param username 管理员名称
     * @return Manager:管理员
     * @throws Exception
     */
    Manager findManagerByUsername(String username) throws Exception;


    /** 通过权限名 查询所有的地方管理员
     * @param title
     * @return List<Manager> 返回所有的地方管理员
     * @throws Exception
     */
    List<Manager> findManagersByTitle(String title) throws Exception;

    /** 通过id 查询管理员昵称
     * @param id
     * @return
     * @throws Exception
     */
    Manager findManagerNameById(Integer id) throws Exception;

    Manager findManagerWithPassword_salt(String username) throws Exception;

    void deleteManagerByUsername(String username) throws Exception;

}
