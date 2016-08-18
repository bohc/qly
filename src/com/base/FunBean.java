package com.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 装载功能的临时类
 * 
 * @author Administrator
 * 
 */
public class FunBean {
	private String modleid;
	private String modelname;
	private String parid;
	private String url;
	private List listFun = new ArrayList();
	private String leafFlag = "0";//默认是叶结点

	public String getModleid() {
		return modleid;
	}

	public void setModleid(String modleid) {
		this.modleid = modleid;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getParid() {
		return parid;
	}

	public void setParid(String parid) {
		this.parid = parid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List getListFun() {
		return listFun;
	}

	public void setListFun(List listFun) {
		this.listFun = listFun;
	}

	public String getLeafFlag() {
		return leafFlag;
	}

	public void setLeafFlag(String leafFlag) {
		this.leafFlag = leafFlag;
	}

}
