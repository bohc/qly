/**
 *柏红春
 *2014-12-07 00:19:41
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.Date;

import java.util.List;

import java.util.ArrayList;

import com.pj.bean.QlyCitytocitypricecustom;

@SuppressWarnings("serial")
public class ExtQlyCitytocitypricecustom extends QlyCitytocitypricecustom {
	private List<String> plist = new ArrayList<String>();
	private String tolineid;// 拷贝的对像ID，有可能是用逗号分开的字符串
	private int pricedif;// 在拷贝对象基础上进行加减的价格，目前只考虑整数；

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}

	public String getTolineid() {
		return tolineid;
	}

	public void setTolineid(String tolineid) {
		this.tolineid = tolineid;
	}

	public int getPricedif() {
		return pricedif;
	}

	public void setPricedif(int pricedif) {
		this.pricedif = pricedif;
	}

}
