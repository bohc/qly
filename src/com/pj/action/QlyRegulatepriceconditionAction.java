/**
 *柏红春
 *Tue Dec 09 09:15:46 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import com.pj.dao.QlyRegulatepriceconditionDao;
import com.pj.bean.QlyRegulatepricecondition;
import com.pj.bean.extend.ExtQlyRegulatepricecondition;

import com.util.QueryLikeUtil;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.base.SessionBean;

@SuppressWarnings("serial")
public class QlyRegulatepriceconditionAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyRegulatepricecondition qlyregulatepricecondition;
	private ExtQlyRegulatepricecondition extqlyregulatepricecondition;
	private String[] ck;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyregulatepricecondition = extqlyregulatepricecondition == null ? new ExtQlyRegulatepricecondition() : extqlyregulatepricecondition;
		ExtQlyRegulatepricecondition extqlyregulatepriceconditiontemp = new ExtQlyRegulatepricecondition();
		try {
			BeanUtils.copyProperties(extqlyregulatepriceconditiontemp, extqlyregulatepricecondition);
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlyregulatepriceconditiontemp.setIsPage("1");
		extqlyregulatepriceconditiontemp.setSort("DESC");
		extqlyregulatepriceconditiontemp.setSortCol("id");

		extqlyregulatepriceconditiontemp.setPageSize(15);

		sql = "extqlyregulatepricecondition.bhc_getAllList";
		sqlcount = "extqlyregulatepricecondition.bhc_getAllCount";
		pc = QlyRegulatepriceconditionDao.getInstence().getInfoList(currentpage, extqlyregulatepriceconditiontemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlyregulatepriceconditionlist.jsp";
		return SUCCESS;
	}

	public String listselect() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyregulatepricecondition = extqlyregulatepricecondition == null ? new ExtQlyRegulatepricecondition() : extqlyregulatepricecondition;
		ExtQlyRegulatepricecondition extqlyregulatepriceconditiontemp = new ExtQlyRegulatepricecondition();
		try {
			BeanUtils.copyProperties(extqlyregulatepriceconditiontemp, extqlyregulatepricecondition);
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlyregulatepriceconditiontemp.setIsPage("1");
		extqlyregulatepriceconditiontemp.setSort("DESC");
		extqlyregulatepriceconditiontemp.setSortCol("id");

		sql = "extqlyregulatepricecondition.bhc_getAllListSelect";
		sqlcount = "extqlyregulatepricecondition.bhc_getAllCountSelect";
		pc = QlyRegulatepriceconditionDao.getInstence().getInfoList(currentpage, extqlyregulatepriceconditiontemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlyregulatepriceconditionlist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlyregulatepricecondition != null && qlyregulatepricecondition.getId() != null) {
			sql = "extqlyregulatepricecondition.bhc_selectByPrimaryKey";
			qlyregulatepricecondition = (QlyRegulatepricecondition) QlyRegulatepriceconditionDao.getInstence().getInfo(sql, qlyregulatepricecondition);
		}
		pagename = "WEB-INF/web/pj/qlyregulatepriceconditionedit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlyregulatepricecondition != null && qlyregulatepricecondition.getId() != null) {
			sql = "extqlyregulatepricecondition.bhc_selectByPrimaryKey";
			qlyregulatepricecondition = (QlyRegulatepricecondition) QlyRegulatepriceconditionDao.getInstence().getInfo(sql, qlyregulatepricecondition);
			qlyregulatepricecondition.setId(null);
		}
		pagename = "WEB-INF/web/pj/qlyregulatepriceconditionedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlyregulatepricecondition.getId() == null || qlyregulatepricecondition.getId().equals("")) {
			sql = "extqlyregulatepricecondition.bhc_insert";
			QlyRegulatepriceconditionDao.getInstence().insert(sql, qlyregulatepricecondition);
			msg = "增加成功";
		} else {
			sql = "extqlyregulatepricecondition.bhc_update";
			QlyRegulatepriceconditionDao.getInstence().update(sql, qlyregulatepricecondition);
			msg = "更新成功";
		}
		Map<String, String> m = new HashMap<String, String>();
		m.put("extqlyregulatepricecondition.lineid", qlyregulatepricecondition.getLineid()+"");
		printMsg(msg, "qlyregulatepricecondition!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlyregulatepricecondition.bhc_delete";
		QlyRegulatepriceconditionDao.getInstence().delete(sql, qlyregulatepricecondition);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlyregulatepricecondition!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlyregulatepricecondition = extqlyregulatepricecondition == null ? new ExtQlyRegulatepricecondition() : extqlyregulatepricecondition;
		if (ck != null && ck.length > 0) {
			sql = "extqlyregulatepricecondition.bhc_delete_batch";
			for (String sid : ck) {
				extqlyregulatepricecondition.getPlist().add(sid);
			}
			QlyRegulatepriceconditionDao.getInstence().delete(sql, extqlyregulatepricecondition);
		}
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlyregulatepricecondition!list.do", m);
		return this.NONE;
	}

	public QlyRegulatepricecondition getQlyregulatepricecondition() {
		return this.qlyregulatepricecondition;
	}

	public void setQlyregulatepricecondition(QlyRegulatepricecondition qlyregulatepricecondition) {
		this.qlyregulatepricecondition = qlyregulatepricecondition;
	}

	public ExtQlyRegulatepricecondition getExtqlyregulatepricecondition() {
		return this.extqlyregulatepricecondition;
	}

	public void setExtqlyregulatepricecondition(ExtQlyRegulatepricecondition extqlyregulatepricecondition) {
		this.extqlyregulatepricecondition = extqlyregulatepricecondition;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}
}
