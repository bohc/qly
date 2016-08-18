/**
 *柏红春
 *Thu Nov 13 16:16:22 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyLinetravel extends ModelBase implements Serializable {
	/**
	 * 自增序列号
	 */
	private Integer id;
	/**
	 * 线路自增序列号
	 */
	private Integer ltid;
	/**
	 * 线路编号
	 */
	private String lineid;
	/**
	 * 线路名称
	 */
	private String linename;
	/**
	 * 第几天
	 */
	private Integer daynum;
	/**
	 * 行程编码
	 */
	private String travelcode;
	/**
	 * 行程标题
	 */
	private String traveltitle;
	/**
	 * 游玩景区编码，用逗号隔开
	 */
	private String viewid;
	/**
	 * 景区标题
	 */
	private String viewcontent;
	/**
	 * 住宿地方
	 */
	private String stay;
	/**
	 * 酒店标准
	 */
	private String hotel;
	/**
	 * 住宿特色
	 */
	private String innfeature;
	/**
	 * 说明
	 */
	private String mark;
	/**
	 * 交通工具
	 */
	private String vehicle;
	/**
	 * 交通工具价格数据
	 */
	private String vehiclesprice;
	/**
	 * 交通工具显示名称
	 */
	private String vehiclespricetitle;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setLtid(Integer ltid) {
		this.ltid = ltid;
	}

	public Integer getLtid() {
		return this.ltid;
	}

	public void setLineid(String lineid) {
		this.lineid = lineid;
	}

	public String getLineid() {
		return this.lineid;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

	public String getLinename() {
		return this.linename;
	}

	public void setDaynum(Integer daynum) {
		this.daynum = daynum;
	}

	public Integer getDaynum() {
		return this.daynum;
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

	public void setViewid(String viewid) {
		this.viewid = viewid;
	}

	public String getViewid() {
		return this.viewid;
	}

	public void setViewcontent(String viewcontent) {
		this.viewcontent = viewcontent;
	}

	public String getViewcontent() {
		return this.viewcontent;
	}

	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getInnfeature() {
		return innfeature;
	}

	public void setInnfeature(String innfeature) {
		this.innfeature = innfeature;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return this.mark;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getVehicle() {
		return this.vehicle;
	}

	public String getVehiclesprice() {
		return vehiclesprice;
	}

	public void setVehiclesprice(String vehiclesprice) {
		this.vehiclesprice = vehiclesprice;
	}

	public String getVehiclespricetitle() {
		return vehiclespricetitle;
	}

	public void setVehiclespricetitle(String vehiclespricetitle) {
		this.vehiclespricetitle = vehiclespricetitle;
	}

	@Override
	public String toString() {
		return "QlyLinetravel [id=" + id + ", ltid=" + ltid + ", lineid=" + lineid + ", linename=" + linename + ", daynum=" + daynum + ", travelcode=" + travelcode + ", traveltitle=" + traveltitle + ", viewid=" + viewid
				+ ", viewcontent=" + viewcontent + ", stay=" + stay + ", hotel=" + hotel + ", innfeature=" + innfeature + ", mark=" + mark + ", vehicle=" + vehicle + ", vehiclesprice=" + vehiclesprice
				+ ", vehiclespricetitle=" + vehiclespricetitle + "]";
	}

}
