package com.tungshine.alipay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author: TungShine
 * @ Description:
 * @ Date: Create in 13:17 2018/7/10
 * @ Modified By:
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("pay")
    public String pay() {
        return "pay";
    }
}
