/**
 *柏红春
 *2014-11-11 13:41:50
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.ArrayList;
import java.util.List;

import com.pj.bean.QlyTravel;

@SuppressWarnings("serial")
public class ExtQlyTravel extends QlyTravel {
	private List<String> plist = new ArrayList<String>();

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}
}
