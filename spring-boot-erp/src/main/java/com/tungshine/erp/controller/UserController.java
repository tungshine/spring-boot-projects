package com.tungshine.erp.controller;

import com.tungshine.erp.model.User;
import com.tungshine.erp.service.UserService;
import com.tungshine.erp.util.RedisUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 17:22 2018/5/3
 * @Modified By:
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "获取用户信息", notes = "根据id获取用户详情")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "Long")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @ApiOperation(value = "创建用户", notes = "创建用户信息")
    @ApiImplicitParam(name = "name", value = "用户账号", required = true, dataType = "String", paramType = "String")
    @ResponseBody
    @RequestMapping(value = "user/add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@RequestParam(value = "name") String name) {
        User user = new User();
        user.setName(name);
        userService.addUser(user);
        return "success";
    }

    @RequestMapping(value = "user/list/{pageNo}", method = RequestMethod.GET)
    public String userList(@PathVariable Integer pageNo, Model model) {
        redisUtil.set("name", "shine", 5L);
        int pageSize = 4;
        List<User> users = userService.findUserByPage(pageNo, pageSize);
        model.addAttribute("users", users);
        return "index";
    }

}
