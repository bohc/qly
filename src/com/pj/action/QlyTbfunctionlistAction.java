/**
 *柏红春
 *Mon Nov 03 11:25:23 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.base.SessionBean;
import com.pj.bean.QlyTbfunctionlist;
import com.pj.bean.extend.ExtQlyTbfunctionlist;
import com.pj.dao.QlyTbfunctionlistDao;
import com.util.QueryLikeUtil;

@SuppressWarnings("serial")
public class QlyTbfunctionlistAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyTbfunctionlist qlytbfunctionlist;
	private ExtQlyTbfunctionlist extqlytbfunctionlist;
	private List<String> vsnamelist;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlytbfunctionlist = extqlytbfunctionlist == null ? new ExtQlyTbfunctionlist()
				: extqlytbfunctionlist;
		ExtQlyTbfunctionlist extqlytbfunctionlisttemp = new ExtQlyTbfunctionlist();
		try {
			BeanUtils.copyProperties(extqlytbfunctionlisttemp,
					extqlytbfunctionlist);
			//将上级编号名称查询模糊化
			try {
				if (extqlytbfunctionlist.getVsname() != null) {
					extqlytbfunctionlisttemp.setVsname(QueryLikeUtil
							.getLikeStr(extqlytbfunctionlisttemp.getVsname()));
				}
			} catch (Exception e) {
			}
			//将功能名称查询模糊化
			try {
				if (extqlytbfunctionlist.getVname() != null) {
					extqlytbfunctionlisttemp.setVname(QueryLikeUtil
							.getLikeStr(extqlytbfunctionlisttemp.getVname()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		//这是分页标识
		extqlytbfunctionlisttemp.setIsPage("1");
		extqlytbfunctionlisttemp.setSort("DESC");
		extqlytbfunctionlisttemp.setSortCol("nid");

		extqlytbfunctionlisttemp.setPageSize(15);

		sql = "qlytbfunctionlist.bhc_getAllList";
		sqlcount = "qlytbfunctionlist.bhc_getAllCount";
		pc = QlyTbfunctionlistDao.getInstence().getInfoList(currentpage,
				extqlytbfunctionlisttemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlytbfunctionlistlist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlytbfunctionlist != null) {
			sql = "qlytbfunctionlist.bhc_selectByPrimaryKey";
			qlytbfunctionlist = (QlyTbfunctionlist) QlyTbfunctionlistDao
					.getInstence().getInfo(sql, qlytbfunctionlist);
		}
		sql = "qlytbfunctionlist.bhc_getAllListBySelect";
		vsnamelist = QlyTbfunctionlistDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/qlytbfunctionlistedit.jsp";
		return SUCCESS;
	}

	/**
	 *保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlytbfunctionlist.getNid() == null
				|| qlytbfunctionlist.getNid().equals("")) {
			sql = "qlytbfunctionlist.bhc_insert";
			QlyTbfunctionlistDao.getInstence().insert(sql, qlytbfunctionlist);
			msg = "增加成功";
		} else {
			sql = "qlytbfunctionlist.bhc_update";
			QlyTbfunctionlistDao.getInstence().update(sql, qlytbfunctionlist);
			msg = "更新成功";
		}
		printMsg(msg, "window.location.href='qlytbfunctionlist!list.do'");
		return this.NONE;
	}

	/**
	 *删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "qlytbfunctionlist.bhc_delete";
		QlyTbfunctionlistDao.getInstence().delete(sql, qlytbfunctionlist);
		printMsg("删除成功", "window.location.href='qlytbfunctionlist!list.do'");
		return this.NONE;
	}

	public QlyTbfunctionlist getQlytbfunctionlist() {
		return this.qlytbfunctionlist;
	}

	public void setQlytbfunctionlist(QlyTbfunctionlist qlytbfunctionlist) {
		this.qlytbfunctionlist = qlytbfunctionlist;
	}

	public ExtQlyTbfunctionlist getExtqlytbfunctionlist() {
		return this.extqlytbfunctionlist;
	}

	public void setExtqlytbfunctionlist(
			ExtQlyTbfunctionlist extqlytbfunctionlist) {
		this.extqlytbfunctionlist = extqlytbfunctionlist;
	}

	public List<String> getVsnamelist() {
		return this.vsnamelist;
	}

	public void setVsnamelist(List<String> vsnamelist) {
		this.vsnamelist = vsnamelist;
	}
}
