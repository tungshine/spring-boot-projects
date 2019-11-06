package com.tungshine.alipay.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 针对返回json数据格式做处理
 *
 * @author huangxiaolong@2017-08-31
 */
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    final static String RESULT = "result";
    final static String MSG = "msg";
    final public static int STATUS_SUCCESS = 0;// 状态码：0代表成功，其它代表失败

    /**
     * 读取body内容
     *
     * @param request
     * @return byte[]
     * @author TangXu
     * @date 2019/11/6 11:04
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
     * @Description 解析Request，读取流，获取Json对象
     * @Date 2018/9/7 17:42
     * @Param [request]
     */
    public static JSONObject getJSONObject(HttpServletRequest request) {
        String data = null;
        try {
            byte[] body = IOUtils.toByteArray(request.getInputStream());
            data = new String(body, "UTF-8");
        } catch (IOException e) {
            logger.error("读取body失败,e:", e);
        }
        return JSONObject.parseObject(data);
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
     * 解密http请求内容,对字节做解密处理
     *
     * @param content
     * @return
     */
    public static JSONObject decrypt(byte[] content) {
        int index = -1;
        for (int i = 0; i < content.length; i++) {
            if (content[i] == (byte) 0) {
                index = i;
                break;
            }
        }
        String shead = new String(ByteUtils.copy(content, 0, index));
        byte[] tail = ByteUtils.copy(content, index + 1, content.length - index - 1);

        try {
            JSONObject json = JSONObject.parseObject(shead);
            if ("0".equals(json.getString("version"))) {// 不加密
                return JSONObject.parseObject(new String(tail, "UTF-8"));
            } else {// 加密
                byte[] key = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 0, 0, 0, 0, 0, 0};
                byte[] bjsonmsg = XXTEA.decrypt(tail, key);
                String str = new String(bjsonmsg, "UTF-8");
                return JSONObject.parseObject(str);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("e:", e);
        }
        return null;
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
//	public static byte[] errorBytes(HttpServletResponse response, long errorCode, String msg) {
//		String str = error(errorCode, msg);
//		return wrapMSG(response, str);
//	}

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
