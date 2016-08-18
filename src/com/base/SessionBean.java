package com.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pj.bean.QlyUserinfo;

public class SessionBean {
	private QlyUserinfo qlyuserinfo;
	private List<String> listFun = new ArrayList<String>();// 菜单功能
	private Set<Integer> roleid = new HashSet<Integer>();

	public QlyUserinfo getQlyuserinfo() {
		return qlyuserinfo;
	}

	public void setQlyuserinfo(QlyUserinfo qlyuserinfo) {
		this.qlyuserinfo = qlyuserinfo;
	}

	public List<String> getListFun() {
		return listFun;
	}

	public void setListFun(List<String> listFun) {
		this.listFun = listFun;
	}

	public Set<Integer> getRoleid() {
		return roleid;
	}

	public void setRoleid(Set<Integer> roleid) {
		this.roleid = roleid;
	}

}
