package com.tungshine.erp.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Set;

/**
 * 针对返回json数据格式做处理
 *
 * @author huangxiaolong@2017-08-31
 */
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    final static String RESULT = "result";
    final static String MSG = "msg";


    /**
     * 读取body内容
     *
     * @param request
     * @return
     */
    public static byte[] readBody(HttpServletRequest request) {
        try {
            return IOUtils.toByteArray(request.getInputStream());
        } catch (IOException e) {
            logger.error("读取body失败,e:", e);
        }
        return new byte[0];
    }

    /**
     * @Author TangXu
     * @Description 解析Request中请求参数转换为JSONObject
     * @Date 2018/9/9 15:16
     * @Param [request]
     */
    public static JSONObject convertMap2JSONObject(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keys = parameterMap.keySet();
        keys.forEach(key -> {
            jsonObject.put(key, parameterMap.get(key)[0]);
        });
        return jsonObject;
    }

    /**
     * map转json
     * @param map
     * @return
     */
    public static JSONObject map2JSONObject(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        Set<String> keys = map.keySet();
        keys.forEach(key -> {
            jsonObject.put(key, map.get(key).toString());
        });
        return jsonObject;
    }

    /**
     * @Author TangXu
     * @Description 解析Request，读取流，获取Json对象
     * @Date 2018/9/7 17:42
     * @Param [request]
     */
    public static JSONObject getJSONObject(HttpServletRequest request) {
        String data = null;
        try {
            byte[] body = IOUtils.toByteArray(request.getInputStream());
            data = new String(body, "UTF-8");
            logger.info("getJSONObject request data is : " + data);
        } catch (IOException e) {
            logger.error("读取body失败,e:", e);
        }
        return JSONObject.parseObject(data);
    }

    /**
     * @author: TangXu
     * @date: 2018/10/26 16:29
     * @description: HttpServletRequest转对象
     * @param: [request, clazz]
     */
    public static Object parseObject(HttpServletRequest request, Class<?> clazz) {
        String data = null;
        try {
            byte[] body = IOUtils.toByteArray(request.getInputStream());
            data = new String(body, "UTF-8");
            logger.info("getJSONObject request data is : " + data);
            return JSONObject.parseObject(data, clazz);
        } catch (IOException e) {
            logger.error("读取body失败,e:", e);
            return null;
        }
    }

    /**
     * 对结果进行处理
     *
     * @param result
     * @return
     */
    private static byte[] wrapMSG(HttpServletResponse response, String result) {
        byte[] bytes = null;
        try {
            bytes = result.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("处理失败,e:", e);
        }
        // response.setContentType("text/html; charset=UTF-8");
        response.setContentLength(bytes.length);
        logger.debug(result);
        return bytes;
    }


    /**
     * 返回错误信息
     *
     * @param errorCode
     * @param msg
     * @return
     */
    public static String error(long errorCode, String msg) {
		 /*JSONObject json = new JSONObject();
		 json.put(RESULT, errorCode);
		 json.put(MSG, msg);
		 return json.toString();*/

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"").append(RESULT).append("\"").append(":").append(errorCode);
        sb.append(",");
        sb.append("\"").append(MSG).append("\"").append(":").append("\"").append(msg).append("\"");
        sb.append("}");
        return sb.toString();
    }


    /**
     * 返回字节类型
     *
     * @param errorCode
     * @param msg
     * @return
     */
    public static byte[] errorBytes(HttpServletRequest request, HttpServletResponse response, long errorCode, String msg) {
        String str = error(errorCode, msg);
        request.setAttribute("RESPONSE_DATA", JSONObject.parseObject(str));
        return wrapMSG(response, str);
    }

    /**
     * 成功后返回字节类型
     *
     * @param errorCode
     * @param msg
     * @return
     */
//	public static byte[] successBytes(HttpServletResponse response, String jsonStr) {
//		return wrapMSG(response, jsonStr);
//	}

    /**
     * 成功后返回字节类型
     *
     * @return
     */
    public static byte[] successBytes(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        request.setAttribute("RESPONSE_DATA", JSONObject.parseObject(jsonStr));
        return wrapMSG(response, jsonStr);
    }

    /**
     * 将请求内容转换成json(不对字节做特殊处理)
     *
     * @param content
     * @return
     */
    public static JSONObject convertHttpContent(String content) throws Exception {
        JSONObject json = null;
        try {
            json = JSONObject.parseObject(content);
        } catch (Exception e) {
            if (content.startsWith("val=")) {
                try {
                    content = content.replace("val=", "");
                    content = URLDecoder.decode(content, "utf-8");
                    json = JSONObject.parseObject(content);
                } catch (Exception e1) {
                    throw e1;
                }
            } else {
                throw e;
            }
        }

        return json;
    }

}
