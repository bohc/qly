/**
 *柏红春
 *Mon Nov 03 09:46:38 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyUserinfo extends ModelBase implements Serializable {
	/**
	 *ID
	 */
	private Integer userid;
	/**
	 *登录名
	 */
	private String username;
	/**
	 *密码
	 */
	private String pwd;
	/**
	 *用户类型
	 */
	private String type;
	/**
	 *别名
	 */
	private String realname;
	/**
	 *所属企业
	 */
	private Integer compid;
	/**
	 *企业名
	 */
	private String cname;
	/**
	 *地区编号
	 */
	private String acode;
	/**
	 *所在地区
	 */
	private String cityname;

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setCompid(Integer compid) {
		this.compid = compid;
	}

	public Integer getCompid() {
		return this.compid;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCname() {
		return this.cname;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getAcode() {
		return this.acode;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCityname() {
		return this.cityname;
	}

	@Override
	public String toString() {
		return ";userid:" + userid + ";username:" + username + ";pwd:" + pwd
				+ ";type:" + type + ";realname:" + realname + ";compid:"
				+ compid + ";cname:" + cname + ";acode:" + acode + ";cityname:"
				+ cityname;
	}
}
