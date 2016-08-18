/**
 *柏红春
 *Fri Nov 28 00:28:42 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyLinepic extends ModelBase implements Serializable {
	/**
	 *主键ＩＤ
	 */
	private Integer id;
	/**
	 *线路编号
	 */
	private Integer lineid;
	/**
	 *图片路径
	 */
	private String vpicfix;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}

	public Integer getLineid() {
		return this.lineid;
	}

	public void setVpicfix(String vpicfix) {
		this.vpicfix = vpicfix;
	}

	public String getVpicfix() {
		return this.vpicfix;
	}

	@Override
	public String toString() {
		return ";id:" + id + ";lineid:" + lineid + ";vpicfix:" + vpicfix;
	}
}
