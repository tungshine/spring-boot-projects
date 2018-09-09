package com.tungshine.alipay.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author TangXu
 * @Description
 * @Date 2018/9/9 14:50
 * @Param 
 */
public class ByteUtils {
	
	public static byte[] copy(byte[] src, int off, int len) {
		byte[] receive = new byte[len];
		System.arraycopy(src, off, receive, 0, len);
		return receive;
	}
	
	public static byte[] merge(byte before, byte[] after) {
		byte[] buffer = new byte[after.length + 1];
		buffer[0] = before;
		System.arraycopy(after, 0, buffer, 1, after.length);
		return buffer;
	}
	
	public static byte[] merge(byte[] before, byte[] after) {
		byte[] buffer = new byte[before.length + after.length];
		System.arraycopy(before, 0, buffer, 0, before.length);
		System.arraycopy(after, 0, buffer, before.length, after.length);
 		return buffer;
	}
	
	/**
	 * 打印字节数组
	 * @param data
	 */
	public static void print(byte[] data){
		if(data==null || data.length==0){
			System.out.println("null");
			return;
		}
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		for(int i=0;i<data.length;i++){
			sb.append(data[i]);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		System.out.println(sb.toString());
	}

	/**
	 * @Author lih
	 * @Description 解析byte数组
	 * @Date 20:51 2018/8/31
	 * @Param [data, retParame]
	 * @return java.util.Map
	 **/
	public static JSONObject parseByteArr(byte[] data ){
		if (null == data || data.length <1){
			return null;
		}
		String dataStr = new String(data);
		JSONObject jsonObject = JSONObject.parseObject(dataStr);
		return jsonObject;
	}
	
}
