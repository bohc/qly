/**
 *柏红春
 *Thu Nov 06 13:03:52 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyViewtype extends ModelBase implements Serializable {
	/**
	 *主键
	 */
	private Integer id;
	/**
	 *分类名称
	 */
	private String vtypename;
	/**
	 *分类编号
	 */
	private String vtypecode;
	/**
	 *上级编号
	 */
	private String vparcode;
	/**
	 *上级名称
	 */
	private String vparname;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setVtypename(String vtypename) {
		this.vtypename = vtypename;
	}

	public String getVtypename() {
		return this.vtypename;
	}

	public void setVtypecode(String vtypecode) {
		this.vtypecode = vtypecode;
	}

	public String getVtypecode() {
		return this.vtypecode;
	}

	public void setVparcode(String vparcode) {
		this.vparcode = vparcode;
	}

	public String getVparcode() {
		return this.vparcode;
	}

	public void setVparname(String vparname) {
		this.vparname = vparname;
	}

	public String getVparname() {
		return this.vparname;
	}

	@Override
	public String toString() {
		return ";id:" + id + ";vtypename:" + vtypename + ";vtypecode:"
				+ vtypecode + ";vparcode:" + vparcode + ";vparname:" + vparname;
	}
}
