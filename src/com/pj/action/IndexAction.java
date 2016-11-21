package com.pj.action;

import java.util.HashMap;
import java.util.Map;

import com.base.BaseIni;
import com.pj.dao.QlyTbfunctionlistDao;

@SuppressWarnings("serial")
public class IndexAction extends ActionBase {
	private String ftype = "100";

	public String index() {
		pagename = "/WEB-INF/index.html";
		return SUCCESS;
	}

	public String rightFile() {
		msg = String.valueOf(BaseIni.xmlday);
		pagename = "WEB-INF/grid.jsp";
		return SUCCESS;
	}

	public String leftFile() {
		sql = "qlytbfunctionlist.bhc_getAllListByEmp";
		Map pmap = new HashMap();
		pmap.put("ifunctype", ftype);
		ilist = QlyTbfunctionlistDao.getInstence().getList(sql, pmap);
		pagename = "WEB-INF/tree.jsp";
		return SUCCESS;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

}
