package com.whz.Service.Impl;

import com.whz.Service.IUserService;
import com.whz.annotation.Service;
import com.whz.annotation.Transactional;
import com.whz.domain.User;

import java.util.List;
import java.util.Map;

import com.whz.helper.DatabaseHelper;

/**
 * @auther whz
 * @create 2022-02-08 15:44
 */
@Service
public class UserServiceImpl implements IUserService {

    /**
     * 获取所有用户
     */
    public List<User> getAllUser() {
        String sql = "SELECT * FROM user";
        return DatabaseHelper.queryEntityList(User.class, sql);
    }

    /**
     * 根据id获取用户信息
     */
    public User GetUserInfoById(Integer id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return DatabaseHelper.queryEntity(User.class, sql, id);
    }

    /**
     * 修改用户信息
     */
    @Transactional
    public boolean updateUser(int id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(User.class, id, fieldMap);
    }
}
