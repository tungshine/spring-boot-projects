package com.tungshine.erp.controller;

import com.alibaba.fastjson.JSONObject;
import com.tungshine.erp.model.User;
import com.tungshine.erp.service.UserService;
import com.tungshine.erp.util.HttpUtils;
import com.tungshine.erp.util.RedisUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 17:22 2018/5/3
 * @Modified By:
 */
@RestController
@Profile({"dev"})
public class UserController extends BaseApi {

    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(httpMethod = "POST", value = "获取用户信息", response = Map.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Invalid input", response = ApiResponse.class)})
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public Map<String, Object> getUser(@RequestBody Map<String, Object> params) {
        JSONObject json = HttpUtils.map2JSONObject(params);
        if (null == json) {
            return returnError("参数错误", 60000);
        }
        long id = json.getLongValue("id");
        if (0 == id) {
            return returnError("参数错误", 60000);
        }
        return returnSuccess(userService.getUser(id));
    }

    @ApiOperation(value = "创建用户", notes = "创建用户信息")
    @ApiImplicitParam(name = "name", value = "用户账号", required = true, dataType = "String", paramType = "String")
    @RequestMapping(value = "user/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@RequestParam(value = "name") String name) {
        User user = new User();
        user.setName(name);
        userService.addUser(user);
        return "success";
    }

    @ApiOperation(value = "获取列表", notes = "获取列表")
    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    public Map<String, Object> userList(@RequestBody Map<String, Object> params) {
        redisUtil.set("name", "shine", 5L);
        int pageSize = 4;
        int pageNo = Integer.valueOf(params.get("pageNo").toString());
        List<User> users = userService.findUserByPage(pageNo, pageSize);
        return returnSuccess(users);
    }
}
