package com.baibaoxiang.service;

import com.baibaoxiang.po.Manager;

import java.util.List;

/**
 * @author sheng
 * @create 2019-04-28-16:47
 */
public interface ManagerService {

    /** 通过管理员名称 查询管理员（不携带密码，盐）
     * @param username
     * @return
     * @throws Exception
     */
    Manager findManagerByUsername (String username) throws Exception;

    /**通过管理员名称 查询管理员(携带密码,盐)
     * @param username
     * @return
     * @throws Exception
     */
    Manager findManagerWithPassword_salt (String username) throws Exception;

    /** 通过权限名 查询所有的地方管理员
     * @param title
     * @return List<Manager> 返回所有的地方管理员
     * @throws Exception
     */
    List<Manager> findManagersByTitle(String title) throws Exception;

    /** 添加管理员
     * @param record
     * @return
     * @throws Exception
     */
    int insert(Manager record) throws Exception;

    /** 修改管理员信息
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKeySelective(Manager record) throws Exception;

    /** 删除管理员
     * @param username
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKey(Integer username) throws Exception;

    /** 批量删除管理员
     * @param usernames
     * @throws Exception
     */
    void deleteManagerBatch(String usernames) throws Exception;

    Manager findManagerNameById(Integer id) throws Exception;

}
