/**
 *柏红春
 *Thu Nov 06 13:08:17 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import com.pj.dao.QlyViewtypeDao;
import com.pj.bean.QlyViewtype;
import com.pj.bean.extend.ExtQlyViewtype;

import com.util.QueryLikeUtil;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.base.SessionBean;

@SuppressWarnings("serial")
public class QlyViewtypeAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyViewtype qlyviewtype;
	private ExtQlyViewtype extqlyviewtype;
	private String[] ck;
	private List<String> vparnamelist;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyviewtype = extqlyviewtype == null ? new ExtQlyViewtype() : extqlyviewtype;
		ExtQlyViewtype extqlyviewtypetemp = new ExtQlyViewtype();
		try {
			BeanUtils.copyProperties(extqlyviewtypetemp, extqlyviewtype);
			// 将分类名称查询模糊化
			try {
				if (extqlyviewtype.getVtypename() != null) {
					extqlyviewtypetemp.setVtypename(QueryLikeUtil.getLikeStr(extqlyviewtypetemp.getVtypename()));
				}
			} catch (Exception e) {
			}
			// 将上级名称查询模糊化
			try {
				if (extqlyviewtype.getVparname() != null) {
					extqlyviewtypetemp.setVparname(QueryLikeUtil.getLikeStr(extqlyviewtypetemp.getVparname()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlyviewtypetemp.setIsPage("1");
		extqlyviewtypetemp.setSort("DESC");
		extqlyviewtypetemp.setSortCol("id");

		extqlyviewtypetemp.setPageSize(15);

		sql = "qlyviewtype.bhc_getAllList";
		sqlcount = "qlyviewtype.bhc_getAllCount";
		pc = QlyViewtypeDao.getInstence().getInfoList(currentpage, extqlyviewtypetemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlyviewtypelist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlyviewtype != null) {
			sql = "qlyviewtype.bhc_selectByPrimaryKey";
			qlyviewtype = (QlyViewtype) QlyViewtypeDao.getInstence().getInfo(sql, qlyviewtype);
		}
		sql = "qlyviewtype.bhc_getAllList";
		vparnamelist = QlyViewtypeDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/qlyviewtypeedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlyviewtype.getId() == null || qlyviewtype.getId().equals("")) {
			sql = "qlyviewtype.bhc_insert";
			QlyViewtypeDao.getInstence().insert(sql, qlyviewtype);
			msg = "增加成功";
		} else {
			sql = "qlyviewtype.bhc_update";
			QlyViewtypeDao.getInstence().update(sql, qlyviewtype);
			msg = "更新成功";
		}
		printMsg(msg, "window.location.href='qlyviewtype!list.do'");
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "qlyviewtype.bhc_delete";
		QlyViewtypeDao.getInstence().delete(sql, qlyviewtype);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlyviewtype!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlyviewtype = extqlyviewtype == null ? new ExtQlyViewtype() : extqlyviewtype;
		if (ck != null && ck.length > 0) {
			sql = "qlyviewtype.bhc_delete_batch";
			for (String sid : ck) {
				extqlyviewtype.getPlist().add(sid);
			}
			QlyViewtypeDao.getInstence().delete(sql, extqlyviewtype);
		}
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlyviewtype!list.do", m);
		return this.NONE;
	}

	public QlyViewtype getQlyviewtype() {
		return this.qlyviewtype;
	}

	public void setQlyviewtype(QlyViewtype qlyviewtype) {
		this.qlyviewtype = qlyviewtype;
	}

	public ExtQlyViewtype getExtqlyviewtype() {
		return this.extqlyviewtype;
	}

	public void setExtqlyviewtype(ExtQlyViewtype extqlyviewtype) {
		this.extqlyviewtype = extqlyviewtype;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

	public List<String> getVparnamelist() {
		return this.vparnamelist;
	}

	public void setVparnamelist(List<String> vparnamelist) {
		this.vparnamelist = vparnamelist;
	}
}
