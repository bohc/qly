/**
 *柏红春
 *Fri Nov 28 00:28:45 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import com.pj.dao.QlyLinepicDao;
import com.pj.dao.QlyViewpicDao;
import com.pj.bean.QlyLinepic;
import com.pj.bean.QlyViewpic;
import com.pj.bean.extend.ExtQlyLinepic;

import com.util.QueryLikeUtil;

import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.base.BaseIni;
import com.base.SessionBean;

@SuppressWarnings("serial")
public class QlyLinepicAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyLinepic qlylinepic;
	private ExtQlyLinepic extqlylinepic;
	private String[] ck;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlylinepic = extqlylinepic == null ? new ExtQlyLinepic()
				: extqlylinepic;
		ExtQlyLinepic extqlylinepictemp = new ExtQlyLinepic();
		try {
			BeanUtils.copyProperties(extqlylinepictemp, extqlylinepic);
		} catch (Exception e) {
		}

		//这是分页标识
		extqlylinepictemp.setIsPage("1");
		extqlylinepictemp.setSort("DESC");
		extqlylinepictemp.setSortCol("id");

		extqlylinepictemp.setPageSize(15);

		sql = "extqlylinepic.bhc_getAllList";
		sqlcount = "extqlylinepic.bhc_getAllCount";
		pc = QlyLinepicDao.getInstence().getInfoList(currentpage,
				extqlylinepictemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlylinepiclist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlylinepic != null && qlylinepic.getId() != null) {
			sql = "extqlylinepic.bhc_selectByPrimaryKey";
			qlylinepic = (QlyLinepic) QlyLinepicDao.getInstence().getInfo(sql,
					qlylinepic);
		}
		pagename = "WEB-INF/web/pj/qlylinepicedit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlylinepic != null && qlylinepic.getId() != null) {
			sql = "extqlylinepic.bhc_selectByPrimaryKey";
			qlylinepic = (QlyLinepic) QlyLinepicDao.getInstence().getInfo(sql,
					qlylinepic);
			qlylinepic.setId(null);
		}
		pagename = "WEB-INF/web/pj/qlylinepicedit.jsp";
		return SUCCESS;
	}

	/**
	 *保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlylinepic.getId() == null || qlylinepic.getId().equals("")) {
			sql = "extqlylinepic.bhc_insert";
			QlyLinepicDao.getInstence().insert(sql, qlylinepic);
			msg = "增加成功";
		} else {
			sql = "extqlylinepic.bhc_update";
			QlyLinepicDao.getInstence().update(sql, qlylinepic);
			msg = "更新成功";
		}
		Map<String, String> m = new HashMap<String, String>();
		printMsg(msg, "qlylinepic!list.do", m);
		return this.NONE;
	}

	/**
	 *删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlylinepic.bhc_delete";
		QlyLinepicDao.getInstence().delete(sql, qlylinepic);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlylinepic!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String delimg() {
		try {
			sql = "extqlylinepic.bhc_selectByPrimaryKey";
			qlylinepic = (QlyLinepic) QlyViewpicDao.getInstence().getInfo(sql, qlylinepic);
			sql = "extqlylinepic.bhc_delete";
			QlyViewpicDao.getInstence().delete(sql, qlylinepic);
			String fpath = BaseIni.getBasepath() + "/" + qlylinepic.getVpicfix();
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
	 *删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlylinepic = extqlylinepic == null ? new ExtQlyLinepic()
				: extqlylinepic;
		if (ck != null && ck.length > 0) {
			sql = "extqlylinepic.bhc_delete_batch";
			for (String sid : ck) {
				extqlylinepic.getPlist().add(sid);
			}
			QlyLinepicDao.getInstence().delete(sql, extqlylinepic);
		}
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlylinepic!list.do", m);
		return this.NONE;
	}

	public QlyLinepic getQlylinepic() {
		return this.qlylinepic;
	}

	public void setQlylinepic(QlyLinepic qlylinepic) {
		this.qlylinepic = qlylinepic;
	}

	public ExtQlyLinepic getExtqlylinepic() {
		return this.extqlylinepic;
	}

	public void setExtqlylinepic(ExtQlyLinepic extqlylinepic) {
		this.extqlylinepic = extqlylinepic;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}
}
