/**
 *柏红春
 *2014-12-09 00:42:38
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.ArrayList;
import java.util.List;

import com.pj.bean.QlyRegulatepricecondition;

@SuppressWarnings("serial")
public class ExtQlyRegulatepricecondition extends QlyRegulatepricecondition {
	private List<String> plist = new ArrayList<String>();
	private String linename;

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}

	public String getLinename() {
		return linename;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

}
