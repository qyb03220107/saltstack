package com.lgy.util;

public class StringUtils {
	/**
	 * 
	 * <p>Title: removeStart</p>  
	 * <p>Description: 删除指定的开头字符串</p>  
	 * @param start
	 * @return
	 * @throws Exception 
	 */
	public static String removeStart(String str, String prefix) throws Exception  {
		if (null == str)
			return null;
		if ("".equals(str.trim()))
			return "";
		if (null == prefix || "".equals(prefix))
			return str;
		if(str.startsWith(prefix)) {
			return str.substring(prefix.length(), str.length());
		}
	    throw new Exception(str + " 没有按指定字符串" + prefix + "开头");
	}
	
	
	/**
	 * 删除str指定的后缀
	 * 
	 * @param str
	 * @param suffix
	 * @return
	 */
	public static String removeSuffix(String str, String suffix)
			throws Exception {
		if (null == str)
			return null;
		if ("".equals(str.trim()))
			return "";
		if (null == suffix || "".equals(suffix))
			return str;
		if (str.endsWith(suffix)) {
			return str.substring(0, str.length() - suffix.length());
		}
		throw new Exception(str + " 没有按指定字符串" + suffix + "结尾");
	}
}
