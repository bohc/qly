/**
 *柏红春
 *Thu Nov 20 22:53:54 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyViewpic extends ModelBase implements Serializable {
	private Integer vpid;
	/**
	 *景点编号
	 */
	private Integer viewid;
	/**
	 *用户编号
	 */
	private String userid;
	/**
	 *图片排列顺序
	 */
	private Integer ordernum;
	/**
	 *图片路径
	 */
	private String picurl;
	/**
	 *图片标题
	 */
	private String title;
	/**
	 *创建日期
	 */
	private Date credate;
	/**
	 *说明
	 */
	private String mark;

	public void setVpid(Integer vpid) {
		this.vpid = vpid;
	}

	public Integer getVpid() {
		return this.vpid;
	}

	public void setViewid(Integer viewid) {
		this.viewid = viewid;
	}

	public Integer getViewid() {
		return this.viewid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getOrdernum() {
		return this.ordernum;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getPicurl() {
		return this.picurl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setCredate(Date credate) {
		this.credate = credate;
	}

	public Date getCredate() {
		return this.credate;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return this.mark;
	}

	@Override
	public String toString() {
		return ";vpid:" + vpid + ";viewid:" + viewid + ";userid:" + userid
				+ ";ordernum:" + ordernum + ";picurl:" + picurl + ";title:"
				+ title + ";credate:" + credate + ";mark:" + mark;
	}
}
