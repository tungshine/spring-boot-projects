package com.tungshine.erp.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TangXu
 * @create 2018-11-30 10:24
 * @description:
 */
public class BaseApi {

    public Map<String, Object> returnSuccess(Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "success");
        map.put("result", object);
        return map;
    }

    public Map<String, Object> returnError(String msg, int errorCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", errorCode);
        map.put("msg", msg);
        map.put("result", null);
        return map;
    }
}