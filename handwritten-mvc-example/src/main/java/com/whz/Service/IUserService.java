package com.whz.Service;

import com.whz.domain.User;
import java.util.List;
import java.util.Map;

/**
 * @auther whz
 * @create 2022-02-08 15:25
 */
public interface IUserService {

    List<User> getAllUser();

    User GetUserInfoById(Integer id);

    boolean updateUser(int id, Map<String, Object> fieldMap);
}
