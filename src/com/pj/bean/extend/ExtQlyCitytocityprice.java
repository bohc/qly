/**
 *柏红春
 *2014-11-25 23:29:33
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.Date;

import java.util.List;

import java.util.ArrayList;

import com.pj.bean.QlyCitytocityprice;

@SuppressWarnings("serial")
public class ExtQlyCitytocityprice extends QlyCitytocityprice {
	private List<String> plist = new ArrayList<String>();
	private Integer lineid;

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}

	public Integer getLineid() {
		return lineid;
	}

	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}

}
