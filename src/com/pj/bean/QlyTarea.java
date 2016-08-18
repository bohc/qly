/**
 *柏红春
 *Tue Nov 04 21:55:30 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyTarea extends ModelBase implements Serializable {
	/**
	 * 主键
	 */
	private Integer aid;
	/**
	 * 编号
	 */
	private String acode;
	/**
	 * 数据引用编号
	 */
	private String ircode;
	/**
	 * 地名
	 */
	private String area;
	/**
	 * 拼音简码
	 */
	private String areapy;
	/**
	 * 上级地区编号
	 */
	private String pid;
	/**
	 * 上级地区名称
	 */
	private String pname;
	/**
	 * 简称
	 */
	private String remark;
	/**
	 * 转机城市编码
	 */
	private String airportcode;
	/**
	 * 转机城市名字
	 * 
	 * @param aid
	 */
	private String airportname;

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getAid() {
		return this.aid;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getAcode() {
		return this.acode;
	}

	public void setIrcode(String ircode) {
		this.ircode = ircode;
	}

	public String getIrcode() {
		return this.ircode;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return this.area;
	}

	public void setAreapy(String areapy) {
		this.areapy = areapy;
	}

	public String getAreapy() {
		return this.areapy;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPname() {
		return this.pname;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public String getAirportcode() {
		return airportcode;
	}

	public void setAirportcode(String airportcode) {
		this.airportcode = airportcode;
	}

	public String getAirportname() {
		return airportname;
	}

	public void setAirportname(String airportname) {
		this.airportname = airportname;
	}

	@Override
	public String toString() {
		return "QlyTarea [aid=" + aid + ", acode=" + acode + ", ircode=" + ircode + ", area=" + area + ", areapy=" + areapy + ", pid=" + pid + ", pname=" + pname + ", remark=" + remark + ", airportcode=" + airportcode
				+ ", airportname=" + airportname + "]";
	}

}
