/**
 *柏红春
 *2014-11-10 14:44:15
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.Date;

import java.util.List;

import java.util.ArrayList;

import com.pj.bean.QlyTraveltype;

@SuppressWarnings("serial")
public class ExtQlyTraveltype extends QlyTraveltype {
	private List<String> plist = new ArrayList<String>();

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}
}
