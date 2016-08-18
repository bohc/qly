/**
 *柏红春
 *2014-11-04 21:55:30
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.ArrayList;
import java.util.List;

import com.pj.bean.QlyTarea;

@SuppressWarnings("serial")
public class ExtQlyTarea extends QlyTarea {
	private List<String> plist = new ArrayList<String>();

	public List<String> getPlist() {
		return plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}

}
