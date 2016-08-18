/**
 *柏红春
 *Mon Nov 03 09:46:38 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import com.pj.dao.QlyUserinfoDao;
import com.pj.bean.QlyUserinfo;
import com.pj.bean.extend.ExtQlyUserinfo;

import com.util.QueryLikeUtil;

import org.apache.commons.beanutils.BeanUtils;
import com.base.SessionBean;

@SuppressWarnings("serial")
public class QlyUserinfoAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyUserinfo qlyuserinfo;
	private ExtQlyUserinfo extqlyuserinfo;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyuserinfo = extqlyuserinfo == null ? new ExtQlyUserinfo()
				: extqlyuserinfo;
		ExtQlyUserinfo extqlyuserinfotemp = new ExtQlyUserinfo();
		try {
			BeanUtils.copyProperties(extqlyuserinfotemp, extqlyuserinfo);
			//将登录名查询模糊化
			try {
				if (extqlyuserinfo.getUsername() != null) {
					extqlyuserinfotemp.setUsername(QueryLikeUtil
							.getLikeStr(extqlyuserinfotemp.getUsername()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		//这是分页标识
		extqlyuserinfotemp.setIsPage("1");
		extqlyuserinfotemp.setSort("DESC");
		extqlyuserinfotemp.setSortCol("userid");

		extqlyuserinfotemp.setPageSize(15);

		sql = "qlyuserinfo.bhc_getAllList";
		sqlcount = "qlyuserinfo.bhc_getAllCount";
		pc = QlyUserinfoDao.getInstence().getInfoList(currentpage,
				extqlyuserinfotemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlyuserinfolist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlyuserinfo != null) {
			sql = "qlyuserinfo.bhc_selectByPrimaryKey";
			qlyuserinfo = (QlyUserinfo) QlyUserinfoDao.getInstence().getInfo(
					sql, qlyuserinfo);
		}
		pagename = "WEB-INF/web/pj/qlyuserinfoedit.jsp";
		return SUCCESS;
	}

	/**
	 *保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlyuserinfo.getUserid() == null
				|| qlyuserinfo.getUserid().equals("")) {
			sql = "qlyuserinfo.bhc_insert";
			QlyUserinfoDao.getInstence().insert(sql, qlyuserinfo);
			msg = "增加成功";
		} else {
			sql = "qlyuserinfo.bhc_update";
			QlyUserinfoDao.getInstence().update(sql, qlyuserinfo);
			msg = "更新成功";
		}
		printMsg(msg, "window.location.href='qlyuserinfo!list.do'");
		return this.NONE;
	}

	/**
	 *删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "qlyuserinfo.bhc_delete";
		QlyUserinfoDao.getInstence().delete(sql, qlyuserinfo);
		printMsg("删除成功", "window.location.href='qlyuserinfo!list.do'");
		return this.NONE;
	}

	public QlyUserinfo getQlyuserinfo() {
		return this.qlyuserinfo;
	}

	public void setQlyuserinfo(QlyUserinfo qlyuserinfo) {
		this.qlyuserinfo = qlyuserinfo;
	}

	public ExtQlyUserinfo getExtqlyuserinfo() {
		return this.extqlyuserinfo;
	}

	public void setExtqlyuserinfo(ExtQlyUserinfo extqlyuserinfo) {
		this.extqlyuserinfo = extqlyuserinfo;
	}
}
