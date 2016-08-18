/*
 * -------------------------------------------------------------
 * 说明：生成编号
 * 
 * 
 * 作          者：肖小兵
 * 编写日期：
 * 
 * 修改情况：
 * ------------------------------------------------------------- 
 * 修改内容                                                                    修改日期                      修改人
 * 
 * 
 * ------------------------------------------------------------- 
 */

package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GenID {

	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
	

	/**
	 * 初始化函数
	 */
	private GenID() {
	}

	
    /**
     * 生成字符串主键序列
     * @param strPre
     * @return
     */
	public static String getStringKey(String strPre) {
		String strRe = dateFormat.format(new Date());
		return strPre+strRe+String.valueOf(getRandom());
	}
	
	/**
	 * 重命名文件
	 * 
	 * @param fileExt
	 * @return
	 */
	public static String GetFileName(String fileExt) {

		String strFulName = "";
		// 文件名（不包括扩展名）
		java.util.Date currentTime = new java.util.Date();
		strFulName = dateFormat.format(currentTime);
		strFulName += String.valueOf(getRandom()) + fileExt;
		return strFulName;
	}
	
	public static int getRandom() {
		Random random = new Random();
		return Math.abs(random.nextInt() % 1000) +1000;
	}

	

}
