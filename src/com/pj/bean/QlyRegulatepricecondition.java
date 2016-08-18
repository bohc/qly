/**
 *柏红春
 *Tue Dec 09 00:42:38 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyRegulatepricecondition extends ModelBase implements Serializable {
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 主键ID
	 */
	private Integer lineid;
	/**
	 * 调整价格
	 */
	private Integer price;
	/**
	 * 调整价格分类
	 */
	private String difftype;
	/**
	 * 开始时间
	 */
	private Date startdate;
	/**
	 * 结束时间
	 */
	private Date enddate;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getLineid() {
		return lineid;
	}

	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPrice() {
		return this.price;
	}

	public String getDifftype() {
		return difftype;
	}

	public void setDifftype(String difftype) {
		this.difftype = difftype;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	@Override
	public String toString() {
		return ";id:" + id + ";lineid:" + lineid + ";price:" + price + ";difftype:" + difftype + ";startdate:" + startdate + ";enddate:" + enddate;
	}
}
