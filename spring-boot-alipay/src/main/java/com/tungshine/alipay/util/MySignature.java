package com.tungshine.alipay.util;

import java.util.*;

/**
 * @ Author: TungShine
 * @ Description:
 * @ Date: Create in 15:29 2018/7/11
 * @ Modified By:
 */
public class MySignature {


    public static void main(String args[]) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("f", "f");
        map.put("1", "10");
        map.put("c", "c");
        map.put("4", "4");
        map.put("e", "e");
        System.out.println(getSignatureContent(map));
    }

    public static String getSignatureContent(HashMap<String, String> params) {
        TreeMap<String, String> sortedMap = new TreeMap<String, String>();
        sortedMap.putAll(params);
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedMap.keySet());
        int index = 0;
        for (String key : keys) {
            String value = sortedMap.get(key);
            content.append((index == 0 ? "" : "&") + key + "=" + value);
            index++;
            System.out.println(index);
        }
        return content.toString();
    }
}
