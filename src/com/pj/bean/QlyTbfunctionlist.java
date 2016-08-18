/**
 *柏红春
 *Mon Nov 03 10:37:15 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyTbfunctionlist extends ModelBase implements Serializable {
	/**
	 *表自增主键
	 */
	private Integer nid;
	/**
	 *功能编号
	 */
	private Integer icode;
	/**
	 *上级编号
	 */
	private Integer iscode;
	/**
	 *上级编号名称
	 */
	private String vsname;
	/**
	 *级次
	 */
	private Integer ilevel;
	/**
	 *下级子数
	 */
	private Integer isubnum;
	/**
	 *功能名称
	 */
	private String vname;
	/**
	 *权限类型
	 */
	private Long ifunctype;
	/**
	 *路径
	 */
	private String vurl;

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public Integer getNid() {
		return this.nid;
	}

	public void setIcode(Integer icode) {
		this.icode = icode;
	}

	public Integer getIcode() {
		return this.icode;
	}

	public void setIscode(Integer iscode) {
		this.iscode = iscode;
	}

	public Integer getIscode() {
		return this.iscode;
	}

	public void setVsname(String vsname) {
		this.vsname = vsname;
	}

	public String getVsname() {
		return this.vsname;
	}

	public void setIlevel(Integer ilevel) {
		this.ilevel = ilevel;
	}

	public Integer getIlevel() {
		return this.ilevel;
	}

	public void setIsubnum(Integer isubnum) {
		this.isubnum = isubnum;
	}

	public Integer getIsubnum() {
		return this.isubnum;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVname() {
		return this.vname;
	}

	public void setIfunctype(Long ifunctype) {
		this.ifunctype = ifunctype;
	}

	public Long getIfunctype() {
		return this.ifunctype;
	}

	public void setVurl(String vurl) {
		this.vurl = vurl;
	}

	public String getVurl() {
		return this.vurl;
	}

	@Override
	public String toString() {
		return ";nid:" + nid + ";icode:" + icode + ";iscode:" + iscode
				+ ";vsname:" + vsname + ";ilevel:" + ilevel + ";isubnum:"
				+ isubnum + ";vname:" + vname + ";ifunctype:" + ifunctype
				+ ";vurl:" + vurl;
	}
}
