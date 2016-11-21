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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Config {

	/**
	 * 配置文件实例
	 */
	private static Properties propertie = null;

	private static String filePath = "jdbc.properties";

	/**
	 * 初始化函数
	 */
	private Config() {
	}

	/**
	 * 静态加载配置文件
	 */
	static {
		try {
			propertie = new Properties();
			// InputStream in =
			// com.ijipin.oa.util.Config.class.getResourceAsStream("/DataBaseConfig.properties");
			propertie.load(Config.class.getResourceAsStream("/"+filePath));
		} catch (Exception ex) {
			System.out.println("加载配置文件出错。");
		}
	}

	/**
	 * 通过键值得到值
	 * 
	 * @param key
	 *            键
	 * @param strDeault
	 *            默认值
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

	/**
	 * 修改或添加键值对 如果key存在，修改, 反之，添加。
	 * 
	 * @param filePath
	 *            文件路径，即文件所在包的路径，例如：java/util/config.properties
	 * @param key
	 *            键
	 * @param value
	 *            键对应的值
	 */
	public static void writeData(String key, String value) {
		// 获取绝对路径
		String fp = Config.class.getResource("/" + filePath).toString();
		// 截掉路径的”file:/“前缀
		fp = fp.substring(6);
		try {
			File file = new File(fp);
			if (!file.exists())
				file.createNewFile();
			OutputStream fos = new FileOutputStream(fp);
			propertie.setProperty(key, value);
			// 保存，并加入注释
			propertie.store(fos, "Update '" + key + "' value");
			fos.close();
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating " + value + " value error");
		}
	}
}
