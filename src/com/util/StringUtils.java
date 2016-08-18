package com.util;

import java.util.regex.Pattern;

/**
 * <p>
 * Description:字符处理类�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: www.7csky.cn
 * </p>
 * 
 * @author jamin
 * @version 1.0
 */
public class StringUtils {
	public StringUtils() {
	}

	/**
	 * 截取长度
	 * 
	 * @param str
	 * @param len
	 * @param symbol
	 * @return
	 */
	public static String getLimitLengthString(String str, int len, String symbol) {
		int counterOfDoubleByte;
		byte b[];

		counterOfDoubleByte = 0;
		try {
			b = str.getBytes("GBK");
			if (b.length <= len)
				return str;
			for (int i = 0; i < len; i++) {
				if (b[i] < 0)
					counterOfDoubleByte++;
			}

			if (counterOfDoubleByte % 2 == 0)
				return new String(b, 0, len, "GBK") + symbol;
			else
				return new String(b, 0, len - 1, "GBK") + symbol;
		} catch (Exception ex) {
			return str;
		}
	}

	/**
	 * @param str
	 * @param toCount
	 * @param more
	 * @version
	 * @author jamin
	 * @return String
	 */
	public static String substring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = str.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		if (toCount == reInt || (toCount == reInt - 1))
			reStr += more;
		return reStr;
	}

	/**
	 * 除 html jamin
	 * 
	 * @param inputString
	 * @return string
	 */
	public static String Html2Text(String inputString) {
		if (inputString == null || "".equals(inputString))
			return "";

		String htmlStr = inputString;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

	/**
	 * stringToHTML
	 * 
	 * @param sourceStr
	 *            String
	 * @return String
	 */
	public static String stringToHTML(String sourceStr) {
		if (sourceStr == null) {
			return "";
		}

		String returnStr = sourceStr;
		returnStr = returnStr.replaceAll("'", "&#39");
		returnStr = returnStr.replaceAll("<", "&lt;");
		returnStr = returnStr.replaceAll(">", "&gt;");
		returnStr = returnStr.replaceAll(" ", "&nbsp;");
		returnStr = returnStr.replaceAll("\r\n", "<br>");
		returnStr = returnStr.replaceAll("\n", "<br>");

		return returnStr;
	}

	/**
	 * HTMLToString
	 * 
	 * @param sourceStr
	 *            String
	 * @return String
	 */
	public static String HTMLToString(String sourceStr) {
		String returnStr = sourceStr;
		if (sourceStr == null) {
			return "";
		}
		returnStr = returnStr.replaceAll("&#39", "'");
		returnStr = returnStr.replaceAll("<br>", "\n");
		returnStr = returnStr.replaceAll("&nbsp;", " ");
		returnStr = returnStr.replaceAll("&lt;", "<");
		returnStr = returnStr.replaceAll("&gt;", ">");
		return returnStr;
	}

	/**
	 * 过滤掉sql字符串
	 * @param sourceStr
	 * @return
	 */
	public static String sqlFilter(String sourceStr) {
		String returnStr = sourceStr;
		if (sourceStr == null) {
			return "";
		}
		returnStr = returnStr.replaceAll("like", "");
		returnStr = returnStr.replaceAll("%", "");
		returnStr = returnStr.replaceAll("or;", "");
		returnStr = returnStr.replaceAll("and", "");
		return returnStr;
	}

	/**
	 * 是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || "".equalsIgnoreCase(str))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}
