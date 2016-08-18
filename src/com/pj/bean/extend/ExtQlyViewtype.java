/**
 *柏红春
 *2014-11-06 13:03:52
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.Date;

import java.util.List;

import java.util.ArrayList;

import com.pj.bean.QlyViewtype;

@SuppressWarnings("serial")
public class ExtQlyViewtype extends QlyViewtype {
	private List<String> plist = new ArrayList<String>();

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}
}
