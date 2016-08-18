/**
 *柏红春
 *Sun Aug 09 17:08:17 CST 2015
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import com.pj.dao.QlyFlyticketDao;
import com.pj.bean.QlyFlyticket;
import com.pj.bean.extend.ExtQlyFlyticket;

import com.util.QueryLikeUtil;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.base.SessionBean;

@SuppressWarnings("serial")
public class QlyFlyticketAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyFlyticket qlyflyticket;
	private ExtQlyFlyticket extqlyflyticket;
	private String[] ck;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyflyticket = extqlyflyticket == null ? new ExtQlyFlyticket()
				: extqlyflyticket;
		ExtQlyFlyticket extqlyflytickettemp = new ExtQlyFlyticket();
		try {
			BeanUtils.copyProperties(extqlyflytickettemp, extqlyflyticket);
		} catch (Exception e) {
		}

		//这是分页标识
		extqlyflytickettemp.setIsPage("1");
		extqlyflytickettemp.setSort("DESC");
		extqlyflytickettemp.setSortCol("id");

		extqlyflytickettemp.setPageSize(15);

		sql = "extqlyflyticket.bhc_getAllList";
		sqlcount = "extqlyflyticket.bhc_getAllCount";
		pc = QlyFlyticketDao.getInstence().getInfoList(currentpage,
				extqlyflytickettemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlyflyticketlist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlyflyticket != null && qlyflyticket.getId() != null) {
			sql = "extqlyflyticket.bhc_selectByPrimaryKey";
			qlyflyticket = (QlyFlyticket) QlyFlyticketDao.getInstence()
					.getInfo(sql, qlyflyticket);
		}
		pagename = "WEB-INF/web/pj/qlyflyticketedit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlyflyticket != null && qlyflyticket.getId() != null) {
			sql = "extqlyflyticket.bhc_selectByPrimaryKey";
			qlyflyticket = (QlyFlyticket) QlyFlyticketDao.getInstence()
					.getInfo(sql, qlyflyticket);
			qlyflyticket.setId(null);
		}
		pagename = "WEB-INF/web/pj/qlyflyticketedit.jsp";
		return SUCCESS;
	}

	/**
	 *保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlyflyticket.getId() == null || qlyflyticket.getId().equals("")) {
			sql = "extqlyflyticket.bhc_insert";
			QlyFlyticketDao.getInstence().insert(sql, qlyflyticket);
			msg = "增加成功";
		} else {
			sql = "extqlyflyticket.bhc_update";
			QlyFlyticketDao.getInstence().update(sql, qlyflyticket);
			msg = "更新成功";
		}
		Map<String, String> m = new HashMap<String, String>();
		printMsg(msg, "qlyflyticket!list.do", m);
		return this.NONE;
	}

	/**
	 *删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlyflyticket.bhc_delete";
		QlyFlyticketDao.getInstence().delete(sql, qlyflyticket);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlyflyticket!list.do", m);
		return this.NONE;
	}

	/**
	 *删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlyflyticket = extqlyflyticket == null ? new ExtQlyFlyticket()
				: extqlyflyticket;
		if (ck != null && ck.length > 0) {
			sql = "extqlyflyticket.bhc_delete_batch";
			for (String sid : ck) {
				extqlyflyticket.getPlist().add(sid);
			}
			QlyFlyticketDao.getInstence().delete(sql, extqlyflyticket);
		}
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlyflyticket!list.do", m);
		return this.NONE;
	}

	public QlyFlyticket getQlyflyticket() {
		return this.qlyflyticket;
	}

	public void setQlyflyticket(QlyFlyticket qlyflyticket) {
		this.qlyflyticket = qlyflyticket;
	}

	public ExtQlyFlyticket getExtqlyflyticket() {
		return this.extqlyflyticket;
	}

	public void setExtqlyflyticket(ExtQlyFlyticket extqlyflyticket) {
		this.extqlyflyticket = extqlyflyticket;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}
}
