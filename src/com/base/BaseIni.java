package com.base;

import java.util.Map;

import javax.servlet.ServletContext;

/**
 * 初始化工程中用到的一些简单的基础数据
 * 
 * @author Administrator
 * 
 */
public class BaseIni {
	private static String re = "pjtim888";
	private static ServletContext sc = null;
	private static Map sessmap = null;
	private static String basepath = "";// 当前工程的路径
	public static int xmlday = 60;

	public static String getRe() {
		return re;
	}

	public static void setRe(String re) {
		BaseIni.re = re;
	}

	public static Map getSessmap() {
		return sessmap;
	}

	public static void setSessmap(Map sessmap) {
		BaseIni.sessmap = sessmap;
	}

	public static String getBasepath() {
		return basepath;
	}

	public static void setBasepath(String basepath) {
		BaseIni.basepath = basepath;
	}

	public static ServletContext getSc() {
		return sc;
	}

	public static void setSc(ServletContext sc) {
		BaseIni.sc = sc;
	}
}
