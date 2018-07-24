package com.tungshine.alipay.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import org.thymeleaf.util.StringUtils;

/**
 * @ Author: TungShine
 * @ Description:
 * @ Date: Create in 14:46 2018/7/10
 * @ Modified By:
 */
public class AlipayUtil {
    public static String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    public static String appId = "2016091800543227";
    public static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDDhKqTJSPVo7y+J6s5z4f5uS0fBmaM3Crlgunz7vDxGzSBfJLG6l/DVWRslhWEGAHzJ6dJTx8Di4cx3IKh9BeyDB8PsR2+wR6MEiXUrw0ceA+3eklHQLiyweLxp2xk0L8qpoaFTFsfw6F1WIFKtEKmiSnBj1NmpOmGmnzw2Ivfww7NtR3+V4hoQ366W9OkJZXq1Y3ZT0cNTkB/asvi1rOp/HErdZFm+W8FkVszddFAs94vrZEanJON/OZVQ0WGhFf8BEtuAno145EOjphJMcMINJGbc7hXV5gPXIa2N0lBdEfVW35py781uqczxZY0Brv8bgnYkoq2DRq7uXQN+WcvAgMBAAECggEAYdE51aw7JWNpxmjqTV361YFDhcYaAFcWTwUULypX+Qh3WGEOvd38UymhuIHN+exY7WHlW9Pt3EQuO0/rO0N3p2hREfS2wPy1X7aknqeFk7ytgEn552AGB8jg1a3BmR6mxZvSqVXuMRIa8S56YPmWbuMotOIUA8J14xXfdE+iiyuXRE8zSqsxQnJO+rQeh3N6FHKDiJM4c4M9vB05Jt9qPGz26W7/m/qx2MbN25IgParxnLDxFgPCqARcAG3/8xtLUCsg/Jd3lXxXvrU4XkakoNwDyw+HMk3yEnmk4VKrYvxT5kvBFUifLkDiK/AZYUStBO7EeTWcijzTYD9Jk1GWIQKBgQDsclvj80wZhKDNGGXj3iG0MJcrx8hpfkNLIwdsYq6/olhO+xEaV5DXiswseY/c1XImzR/J3X4H9vNjVMipAJLGrFh882z9B/oJ49E2TMpR3VpLtbLAh+DBS9gh7R+MGBgs4Cx2V2asAt9u9RjNDWCs9D02Lsm02cvwo8YQfS9kMwKBgQDTr9cXLMEyocXZSb2GH2Hn+WtTFbC09+sv1Iqu05HJRqPjjWIdV3i5TorKlMvI4ni4OybTxT+VFf";
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw4SqkyUj1aO8vierOc+H+bktHwZmjNwq5YLp8+7w8Rs0gXySxupfw1VkbJYVhBgB8yenSU8fA4uHMdyCofQXsgwfD7EdvsEejBIl1K8NHHgPt3pJR0C4ssHi8adsZNC/KqaGhUxbH8OhdViBSrRCpokpwY9TZqTphpp88NiL38MOzbUd/leIaEN+ulvTpCWV6tWN2U9HDU5Af2rL4tazqfxxK3WRZvlvBZFbM3XRQLPeL62RGpyTjfzmVUNFhoRX/ARLbgJ6NeORDo6YSTHDCDSRm3O4V1eYD1yGtjdJQXRH1Vt+acu/NbqnM8WWNAa7/G4J2JKKtg0au7l0DflnLwIDAQAB";
    public static String format = "json";
    public static String charset = "UTF-8";
    public static String signType = "RSA2";

    public static void main(String args[]) {
        pay();
//        auth();
    }


    public static void pay() {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, publicKey, signType);
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        String out_trade_no = "123";
        String scene = "bar_code";
        String auth_code = "28763443825664394";
        String product_code = "12345678";
        String subject = "测试订单";
        String buyer_id = "";
        String seller_id = "";
        Double total_amount = 0.01;
        request.setBizContent(new AlipayUtil().getBizContent(out_trade_no, scene, auth_code, product_code, subject, buyer_id, seller_id, total_amount));
        try {
            AlipayTradePayResponse response = alipayClient.execute(request);
            System.out.println(response);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    public String getBizContent(String out_trade_no, String scene, String auth_code, String product_code, String subject, String buyer_id, String seller_id, Double total_amount) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("out_trade_no:").append(out_trade_no).append(",");//商户订单号
        sb.append("scene:").append(scene).append(",");// bar_code:条码支付, wave_code：声波支付
        sb.append("auth_code:").append(auth_code).append(",");// 支付授权码
        sb.append("product_code:").append(product_code).append(",");// 销售产品码
        sb.append("subject:").append(subject).append(",");// 订单标题
        if (!StringUtils.isEmpty(buyer_id)) sb.append("buyer_id:").append(buyer_id).append(",");// 买家支付宝id，如果为空，会从传入了码值信息中获取买家ID
        if (!StringUtils.isEmpty(seller_id)) sb.append("seller_id:").append(seller_id).append(",");// 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
        sb.append("total_amount:").append(total_amount).append(",");// 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        return sb.toString();
    }

    public static String auth() {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, publicKey, signType);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.user.userinfo.share
        AlipayUserUserinfoShareRequest request = new AlipayUserUserinfoShareRequest();
        //授权类接口执行API调用时需要带上accessToken
        try {
            AlipayUserUserinfoShareResponse response = alipayClient.execute(request, "accessToken");
            return response.toString();
        } catch (AlipayApiException e) {
            return e.getMessage();
        }
    }
}
