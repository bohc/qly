/**
 *柏红春
 *2014-11-12 12:05:05
 *这个类目前只是在列表的查询中作为参数使用
 */
package com.pj.bean.extend;

import java.util.ArrayList;
import java.util.List;

import com.pj.bean.QlyLine;

@SuppressWarnings("serial")
public class ExtQlyLine extends QlyLine {
	private List<String> plist = new ArrayList<String>();
	private int eid;
	private String fromcityjm;

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getFromcityjm() {
		return fromcityjm;
	}

	public void setFromcityjm(String fromcityjm) {
		this.fromcityjm = fromcityjm;
	}

}
