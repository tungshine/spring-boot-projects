package com.tungshine.alipay.controller;

import com.tungshine.alipay.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @ Author: TungShine
 * @ Description:
 * @ Date: Create in 14:05 2018/7/10
 * @ Modified By:
 */
@RestController
public class PayController {

    @RequestMapping(value = "/testReq")
    public void test(HttpServletRequest request, @RequestBody User user) {
        System.out.println("按对象获取" + user.getName() + "--" + user.getAge());
        Map<String, String[]> map = request.getParameterMap();
        Set<String> keys = map.keySet();

        System.out.println("按参数名获取" + request.getParameter("name"));
        keys.forEach(key -> {
            System.out.println("'" + key + "' : '" + map.get(key)[0] + " '");
        });
//        return user;
    }
}
