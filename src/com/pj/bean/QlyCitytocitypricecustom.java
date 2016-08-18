/**
 *柏红春
 *Sun Dec 07 00:19:41 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyCitytocitypricecustom extends ModelBase implements Serializable {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 线路名称
	 */
	private String linename;
	/**
	 * 线路编号
	 */
	private Integer lineid;
	/**
	 * 出发城市名
	 */
	private String fromcityname;
	/**
	 * 来程交通工具类型
	 */
	private String traficaltype;
	/**
	 * 来程交通工具座位级别
	 */
	private String vehiclecomeseattype;
	/**
	 * 来程交通工具出发时间
	 */
	private String vehiclecometime;
	/**
	 * 所需费用
	 */
	private Double price;
	/**
	 * 儿童浮动价
	 */
	private int childrenprice;
	/**
	 * 回程交通工具类型
	 */
	private String vehiclebacktype;
	/**
	 * 回程交通工具座位级别
	 */
	private String vehiclebackseattype;
	/**
	 * 回程交通工具出发时间
	 */
	private String vehiclebacktime;
	/**
	 * 添加时间
	 */
	private Date entime;
	/**
	 * 添加人
	 */
	private String enman;
	/**
	 * 出发城市简码
	 */
	private String fromcityjm;
	/**
	 * 出发城市拼音
	 */
	private String fromcitypy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLinename() {
		return linename;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

	public Integer getLineid() {
		return lineid;
	}

	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}

	public String getFromcityname() {
		return fromcityname;
	}

	public void setFromcityname(String fromcityname) {
		this.fromcityname = fromcityname;
	}

	public String getTraficaltype() {
		return traficaltype;
	}

	public void setTraficaltype(String traficaltype) {
		this.traficaltype = traficaltype;
	}

	public String getVehiclecomeseattype() {
		return vehiclecomeseattype;
	}

	public void setVehiclecomeseattype(String vehiclecomeseattype) {
		this.vehiclecomeseattype = vehiclecomeseattype;
	}

	public String getVehiclecometime() {
		return vehiclecometime;
	}

	public void setVehiclecometime(String vehiclecometime) {
		this.vehiclecometime = vehiclecometime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getChildrenprice() {
		return childrenprice;
	}

	public void setChildrenprice(int childrenprice) {
		this.childrenprice = childrenprice;
	}

	public String getVehiclebacktype() {
		return vehiclebacktype;
	}

	public void setVehiclebacktype(String vehiclebacktype) {
		this.vehiclebacktype = vehiclebacktype;
	}

	public String getVehiclebackseattype() {
		return vehiclebackseattype;
	}

	public void setVehiclebackseattype(String vehiclebackseattype) {
		this.vehiclebackseattype = vehiclebackseattype;
	}

	public String getVehiclebacktime() {
		return vehiclebacktime;
	}

	public void setVehiclebacktime(String vehiclebacktime) {
		this.vehiclebacktime = vehiclebacktime;
	}

	public Date getEntime() {
		return entime;
	}

	public void setEntime(Date entime) {
		this.entime = entime;
	}

	public String getEnman() {
		return enman;
	}

	public void setEnman(String enman) {
		this.enman = enman;
	}

	public String getFromcityjm() {
		return fromcityjm;
	}

	public void setFromcityjm(String fromcityjm) {
		this.fromcityjm = fromcityjm;
	}

	public String getFromcitypy() {
		return fromcitypy;
	}

	public void setFromcitypy(String fromcitypy) {
		this.fromcitypy = fromcitypy;
	}

	@Override
	public String toString() {
		return "QlyCitytocitypricecustom [id=" + id + ", linename=" + linename + ", lineid=" + lineid + ", fromcityname=" + fromcityname + ", traficaltype=" + traficaltype + ", vehiclecomeseattype=" + vehiclecomeseattype
				+ ", vehiclecometime=" + vehiclecometime + ", price=" + price + ", vehiclebacktype=" + vehiclebacktype + ", vehiclebackseattype=" + vehiclebackseattype + ", vehiclebacktime=" + vehiclebacktime
				+ ", entime=" + entime + ", enman=" + enman + ", fromcityjm=" + fromcityjm + ", fromcitypy=" + fromcitypy + "]";
	}

}
