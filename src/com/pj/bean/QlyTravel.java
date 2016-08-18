/**
 *柏红春
 *Tue Nov 11 13:41:50 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyTravel extends ModelBase implements Serializable {
	/**
	 * 自增序列号
	 */
	private Integer ltid;
	/**
	 * 分类编码
	 */
	private String vtypecode;
	/**
	 * 行程分类名称
	 */
	private String vtypename;
	/**
	 * 行程编码
	 */
	private String travelcode;
	/**
	 * 行程标题
	 */
	private String traveltitle;
	/**
	 * 早餐
	 */
	private String breakfast;
	/**
	 * 中餐
	 */
	private String lunch;
	/**
	 * 晚餐
	 */
	private String supper;
	/**
	 * 活动安排
	 */
	private String activity;
	/**
	 * 自费
	 */
	private String selfexpense;
	/**
	 * 购物
	 */
	private String shop;
	/**
	 * 说明提示
	 */
	private String mark;
	/**
	 * 是否启用
	 */
	private Integer isuse;
	/**
	 * 创建人
	 */
	private String enman;
	/**
	 * 创建时间
	 */
	private Date entime;

	public void setLtid(Integer ltid) {
		this.ltid = ltid;
	}

	public Integer getLtid() {
		return this.ltid;
	}

	public void setVtypecode(String vtypecode) {
		this.vtypecode = vtypecode;
	}

	public String getVtypecode() {
		return this.vtypecode;
	}

	public void setVtypename(String vtypename) {
		this.vtypename = vtypename;
	}

	public String getVtypename() {
		return this.vtypename;
	}

	public void setTravelcode(String travelcode) {
		this.travelcode = travelcode;
	}

	public String getTravelcode() {
		return this.travelcode;
	}

	public void setTraveltitle(String traveltitle) {
		this.traveltitle = traveltitle;
	}

	public String getTraveltitle() {
		return this.traveltitle;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	public String getBreakfast() {
		return this.breakfast;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	public String getLunch() {
		return this.lunch;
	}

	public void setSupper(String supper) {
		this.supper = supper;
	}

	public String getSupper() {
		return this.supper;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getActivity() {
		return this.activity;
	}

	public void setSelfexpense(String selfexpense) {
		this.selfexpense = selfexpense;
	}

	public String getSelfexpense() {
		return this.selfexpense;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getShop() {
		return this.shop;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return this.mark;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public Integer getIsuse() {
		return this.isuse;
	}

	public void setEnman(String enman) {
		this.enman = enman;
	}

	public String getEnman() {
		return this.enman;
	}

	public void setEntime(Date entime) {
		this.entime = entime;
	}

	public Date getEntime() {
		return this.entime;
	}

	@Override
	public String toString() {
		return "QlyTravel [ltid=" + ltid + ", vtypecode=" + vtypecode + ", vtypename=" + vtypename + ", travelcode=" + travelcode + ", traveltitle=" + traveltitle + ", breakfast=" + breakfast + ", lunch=" + lunch
				+ ", supper=" + supper + ", activity=" + activity + ", selfexpense=" + selfexpense + ", shop=" + shop + ", mark=" + mark + ", isuse=" + isuse + ", enman=" + enman + ", entime=" + entime + "]";
	}

}
