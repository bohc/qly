/*
 * -------------------------------------------------------------
 * 说明：访问配置文件类
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

import java.util.Properties;

public class Config {

	/**
	 * 配置文件实例
	 */
	private static Properties propertie = null;
	
	/**
	 * 初始化函数
	 */
	private Config()
	{
	}

	/**
	 * 静态加载配置文件
	 */
	static {
		try	{
			propertie = new Properties ();
			//InputStream in = com.ijipin.oa.util.Config.class.getResourceAsStream("/DataBaseConfig.properties");
			propertie.load(com.util.Config.class.getResourceAsStream("jdbc.properties"));
		}catch (Exception ex) {
			System.out.println ("加载配置文件出错。");
		}
	}

	/**
	 * 通过键值得到值
	 * @param key  键
	 * @param strDeault 默认值
	 * @return 如找到键值就返回值如没有就返回默认值
	 */
	public static String getValueByKey(String key, String strDeault) {
		if (propertie.containsKey(key)) {
			// 得到某一属性的值
			String value = propertie.getProperty(key);
			return value;
		} else
			return strDeault;
	}
}
