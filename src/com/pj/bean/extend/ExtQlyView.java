/**
 *柏红春
 *2014-11-20 22:59:21
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.ArrayList;
import java.util.List;

import com.pj.bean.QlyView;

@SuppressWarnings("serial")
public class ExtQlyView extends QlyView {
	private List<String> plist = new ArrayList<String>();
	private String cond;

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

}
