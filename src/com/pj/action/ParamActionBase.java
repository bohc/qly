package com.pj.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.util.page.PageControl;

@SuppressWarnings({"serial","unchecked"})
public class ParamActionBase extends ActionSupport implements SessionAware{
	// 分页用,必须有这两个
	protected PageControl pc;
	protected String currentpage="0";

	protected String pagename;
	protected String msg="";
	
	protected String sql;
	protected String sqlcount;
	
	public static final String REDIRECT = "redi";
	
	protected List ilist;

	protected Map session;

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public PageControl getPc() {
		return pc;
	}

	public void setPc(PageControl pc) {
		this.pc = pc;
	}

	public String getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSqlcount() {
		return sqlcount;
	}

	public void setSqlcount(String sqlcount) {
		this.sqlcount = sqlcount;
	}

	public List getIlist() {
		return ilist;
	}

	public void setIlist(List ilist) {
		this.ilist = ilist;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
