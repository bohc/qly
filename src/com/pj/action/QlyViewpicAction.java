/**
 *柏红春
 *Wed Nov 19 21:40:32 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.base.BaseIni;
import com.base.SessionBean;
import com.opensymphony.xwork2.Action;
import com.pj.bean.QlyViewpic;
import com.pj.bean.extend.ExtQlyViewpic;
import com.pj.dao.QlyViewpicDao;

@SuppressWarnings("serial")
public class QlyViewpicAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyViewpic qlyviewpic;
	private ExtQlyViewpic extqlyviewpic;
	private String[] ck;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyviewpic = extqlyviewpic == null ? new ExtQlyViewpic() : extqlyviewpic;
		ExtQlyViewpic extqlyviewpictemp = new ExtQlyViewpic();
		try {
			BeanUtils.copyProperties(extqlyviewpictemp, extqlyviewpic);
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlyviewpictemp.setIsPage("1");
		extqlyviewpictemp.setSort("DESC");
		extqlyviewpictemp.setSortCol("vpid");

		extqlyviewpictemp.setPageSize(15);

		sql = "extqlyviewpic.bhc_getAllList";
		sqlcount = "extqlyviewpic.bhc_getAllCount";
		pc = QlyViewpicDao.getInstence().getInfoList(currentpage, extqlyviewpictemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlyviewpiclist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlyviewpic != null && qlyviewpic.getVpid() != null) {
			sql = "extqlyviewpic.bhc_selectByPrimaryKey";
			qlyviewpic = (QlyViewpic) QlyViewpicDao.getInstence().getInfo(sql, qlyviewpic);
		}
		pagename = "WEB-INF/web/pj/qlyviewpicedit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlyviewpic != null && qlyviewpic.getVpid() != null) {
			sql = "extqlyviewpic.bhc_selectByPrimaryKey";
			qlyviewpic = (QlyViewpic) QlyViewpicDao.getInstence().getInfo(sql, qlyviewpic);
			qlyviewpic.setVpid(null);
		}
		pagename = "WEB-INF/web/pj/qlyviewpicedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlyviewpic.getVpid() == null || qlyviewpic.getVpid().equals("")) {
			sql = "extqlyviewpic.bhc_insert";
			QlyViewpicDao.getInstence().insert(sql, qlyviewpic);
			msg = "增加成功";
		} else {
			sql = "extqlyviewpic.bhc_update";
			QlyViewpicDao.getInstence().update(sql, qlyviewpic);
			msg = "更新成功";
		}
		Map<String, String> m = new HashMap<String, String>();
		printMsg(msg, "qlyviewpic!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlyviewpic.bhc_delete";
		QlyViewpicDao.getInstence().delete(sql, qlyviewpic);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlyviewpic!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String delimg() {
		try {
			sql = "extqlyviewpic.bhc_selectByPrimaryKey";
			qlyviewpic = (QlyViewpic) QlyViewpicDao.getInstence().getInfo(sql, qlyviewpic);
			sql = "extqlyviewpic.bhc_delete";
			QlyViewpicDao.getInstence().delete(sql, qlyviewpic);
			String fpath = BaseIni.getBasepath() + "/" + qlyviewpic.getPicurl();
			File f = new File(fpath);
			if (f.exists()) {
				f.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		printHtml("");
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlyviewpic = extqlyviewpic == null ? new ExtQlyViewpic() : extqlyviewpic;
		if (ck != null && ck.length > 0) {
			sql = "extqlyviewpic.bhc_delete_batch";
			for (String sid : ck) {
				extqlyviewpic.getPlist().add(sid);
			}
			QlyViewpicDao.getInstence().delete(sql, extqlyviewpic);
		}
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlyviewpic!list.do", m);
		return this.NONE;
	}

	public String showlist() {
		extqlyviewpic = extqlyviewpic == null ? new ExtQlyViewpic() : extqlyviewpic;
		sql = "extqlyviewpic.bhc_getListByViewid";
		ilist = QlyViewpicDao.getInstence().getList(sql, extqlyviewpic);
		StringBuffer sbs = new StringBuffer();
		sbs.append("<ps>");
		if (ilist != null && ilist.size() > 0) {
			for (int i = 0; i < ilist.size(); i++) {
				QlyViewpic vp = (QlyViewpic) ilist.get(i);
				sbs.append("<p id=\"" + vp.getVpid() + "\" src=\"" + vp.getPicurl() + "\"></p>");
			}
		}
		sbs.append("</ps>");
		printXML(sbs.toString());
		return Action.NONE;
	}

	public QlyViewpic getQlyviewpic() {
		return this.qlyviewpic;
	}

	public void setQlyviewpic(QlyViewpic qlyviewpic) {
		this.qlyviewpic = qlyviewpic;
	}

	public ExtQlyViewpic getExtqlyviewpic() {
		return this.extqlyviewpic;
	}

	public void setExtqlyviewpic(ExtQlyViewpic extqlyviewpic) {
		this.extqlyviewpic = extqlyviewpic;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}
}
