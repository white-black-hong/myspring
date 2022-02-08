package com.whz.Controller;


import com.whz.Service.IUserService;
import com.whz.annotation.Autowired;
import com.whz.annotation.Controller;
import com.whz.annotation.RequestMapping;
import com.whz.annotation.RequestMethod;
import com.whz.bean.Param;
import com.whz.bean.View;
import com.whz.bean.Data;
import com.whz.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther whz
 * @create 2022-02-08 15:23
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户列表
     *
     * @return
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public View getUserList() {
        List<User> userList = userService.getAllUser();
        return new View("index.jsp").addModel("userList", userList);
    }

    /**
     * 用户详情
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Data getUserInfo(Param param) {
        String id = (String) param.getParamMap().get("id");
        User user = userService.GetUserInfoById(Integer.parseInt(id));

        return new Data(user);
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.GET)
    public Data editUser(Param param) {
        String id = (String) param.getParamMap().get("id");
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("age", 911);
        userService.updateUser(Integer.parseInt(id), fieldMap);

        return new Data("Success.");
    }
}
