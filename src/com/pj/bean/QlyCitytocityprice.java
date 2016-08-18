/**
 *柏红春
 *Tue Nov 25 23:29:33 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyCitytocityprice extends ModelBase implements Serializable {
	/**
	 *主键
	 */
	private Integer id;
	/**
	 *交通工具类型
	 */
	private String traficaltype;
	/**
	 *所需费用
	 */
	private Double price;
	/**
	 *出发城市名
	 */
	private String fromcityname;
	/**
	 *到达城市名
	 */
	private String tocityname;
	/**
	 *添加时间
	 */
	private Date entime;
	/**
	 *添加人
	 */
	private String enman;
	/**
	 *出发城市简码
	 */
	private String fromcityjm;
	/**
	 *到达城市简码
	 */
	private String tocityjm;
	/**
	 *出发城市拼音
	 */
	private String fromcitypy;
	/**
	 *到达城市拼音
	 */
	private String tocitypy;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTraficaltype(String traficaltype) {
		this.traficaltype = traficaltype;
	}

	public String getTraficaltype() {
		return this.traficaltype;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setFromcityname(String fromcityname) {
		this.fromcityname = fromcityname;
	}

	public String getFromcityname() {
		return this.fromcityname;
	}

	public void setTocityname(String tocityname) {
		this.tocityname = tocityname;
	}

	public String getTocityname() {
		return this.tocityname;
	}

	public void setEntime(Date entime) {
		this.entime = entime;
	}

	public Date getEntime() {
		return this.entime;
	}

	public void setEnman(String enman) {
		this.enman = enman;
	}

	public String getEnman() {
		return this.enman;
	}

	public void setFromcityjm(String fromcityjm) {
		this.fromcityjm = fromcityjm;
	}

	public String getFromcityjm() {
		return this.fromcityjm;
	}

	public void setTocityjm(String tocityjm) {
		this.tocityjm = tocityjm;
	}

	public String getTocityjm() {
		return this.tocityjm;
	}

	public void setFromcitypy(String fromcitypy) {
		this.fromcitypy = fromcitypy;
	}

	public String getFromcitypy() {
		return this.fromcitypy;
	}

	public void setTocitypy(String tocitypy) {
		this.tocitypy = tocitypy;
	}

	public String getTocitypy() {
		return this.tocitypy;
	}

	@Override
	public String toString() {
		return ";id:" + id + ";traficaltype:" + traficaltype + ";price:"
				+ price + ";fromcityname:" + fromcityname + ";tocityname:"
				+ tocityname + ";entime:" + entime + ";enman:" + enman
				+ ";fromcityjm:" + fromcityjm + ";tocityjm:" + tocityjm
				+ ";fromcitypy:" + fromcitypy + ";tocitypy:" + tocitypy;
	}
}
