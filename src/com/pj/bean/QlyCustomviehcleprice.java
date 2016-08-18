/**
*柏红春
*Thu Mar 17 14:42:24 CST 2016
*/
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyCustomviehcleprice extends ModelBase implements Serializable {
	/**
	 * 自增主键
	 */
	private Integer id;
	/**
	 * 交通工具类型
	 */
	private String traficaltype;
	/**
	 * 交通工具座位级别
	 */
	private String seattype;
	/**
	 * 所需费用
	 */
	private Double price;
	/**
	 * 儿童价
	 */
	private Double childrenprice;
	/**
	 * 出发城市名
	 */
	private String fromcityname;
	/**
	 * 出发城市简码
	 */
	private String fromcityjm;
	/**
	 * 出发城市拼音
	 */
	private String fromcitypy;
	/**
	 * 目的城市名
	 */
	private String tocityname;
	/**
	 * 目的城市简码
	 */
	private String tocityjm;
	/**
	 * 目的城市拼音
	 */
	private String tocitypy;
	/**
	 * 添加时间
	 */
	private Date entime;
	/**
	 * 添加人
	 */
	private String enman;

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

	public String getSeattype() {
		return seattype;
	}

	public void setSeattype(String seattype) {
		this.seattype = seattype;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return this.price;
	}

	public Double getChildrenprice() {
		return childrenprice;
	}

	public void setChildrenprice(Double childrenprice) {
		this.childrenprice = childrenprice;
	}

	public void setFromcityname(String fromcityname) {
		this.fromcityname = fromcityname;
	}

	public String getFromcityname() {
		return this.fromcityname;
	}

	public void setFromcityjm(String fromcityjm) {
		this.fromcityjm = fromcityjm;
	}

	public String getFromcityjm() {
		return this.fromcityjm;
	}

	public void setFromcitypy(String fromcitypy) {
		this.fromcitypy = fromcitypy;
	}

	public String getFromcitypy() {
		return this.fromcitypy;
	}

	public void setTocityname(String tocityname) {
		this.tocityname = tocityname;
	}

	public String getTocityname() {
		return this.tocityname;
	}

	public void setTocityjm(String tocityjm) {
		this.tocityjm = tocityjm;
	}

	public String getTocityjm() {
		return this.tocityjm;
	}

	public void setTocitypy(String tocitypy) {
		this.tocitypy = tocitypy;
	}

	public String getTocitypy() {
		return this.tocitypy;
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

	@Override
	public String toString() {
		return ";id:" + id + ";traficaltype:" + traficaltype + ";price:" + price + ";fromcityname:" + fromcityname + ";fromcityjm:" + fromcityjm + ";fromcitypy:" + fromcitypy + ";tocityname:" + tocityname + ";tocityjm:"
				+ tocityjm + ";tocitypy:" + tocitypy + ";entime:" + entime + ";enman:" + enman;
	}
}
