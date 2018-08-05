package com.tungshine.sfyp.util;

public class StringUtils {

	public static String getTrimString(String str) {
		return str == null ? "" : str.trim();
	}

	public static boolean isEmpty(String str) {
		if (str != null && !str.equals(""))
			return false;
		else
			return true;
	}

	public static boolean isNotEmpty(String str) {
		if (str != null && !str.equals(""))
			return true;
		else
			return false;
	}

	public static String[] parseString(String str, String regex) {
		return str.split(regex);
	}

	public static String parseStringForString(String str, String regex, int index) {
		String[] strArr = str.split(regex);
		String temp = "";
		if (strArr.length > index)
			temp = strArr[index];
		return temp;
	}

	public static String parseArrayForString(String str, String regex) {
		String[] strArr = str.split(regex);
		String temp = "";
		for (int i = 0; i < strArr.length; i++)
			temp += strArr[i];
		return temp;
	}
}
