/**
*柏红春
*2016-03-17 14:42:24
*这个类目前只是在列表的查询中作为参数使用
*/
package com.pj.bean.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pj.bean.QlyCustomviehcleprice;
import com.pj.bean.QlyFlyticket;

@SuppressWarnings("serial")
public class ExtQlyCustomviehcleprice extends QlyCustomviehcleprice {
	private List<String> plist = new ArrayList<String>();
	private int dayno;
	private String vtime;// 航班时间
	private Map<String, List<QlyFlyticket>> s, d;

	public List<String> getPlist() {
		return this.plist;
	}

	public void setPlist(List<String> plist) {
		this.plist = plist;
	}

	public int getDayno() {
		return dayno;
	}

	public void setDayno(int dayno) {
		this.dayno = dayno;
	}

	public String getVtime() {
		return vtime;
	}

	public void setVtime(String vtime) {
		this.vtime = vtime;
	}

	public Map<String, List<QlyFlyticket>> getS() {
		return s;
	}

	public void setS(Map<String, List<QlyFlyticket>> s) {
		this.s = s;
	}

	public Map<String, List<QlyFlyticket>> getD() {
		return d;
	}

	public void setD(Map<String, List<QlyFlyticket>> d) {
		this.d = d;
	}

}
