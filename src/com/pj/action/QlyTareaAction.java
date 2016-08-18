/**
 *柏红春
 *Tue Nov 04 22:20:49 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.base.SessionBean;
import com.opensymphony.xwork2.Action;
import com.pj.bean.QlyTarea;
import com.pj.bean.extend.ExtQlyTarea;
import com.pj.dao.QlyTareaDao;
import com.pj.dao.QlyViewDao;
import com.util.ChineseToPY;
import com.util.QueryLikeUtil;

@SuppressWarnings("serial")
public class QlyTareaAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyTarea qlytarea;
	private ExtQlyTarea extqlytarea;
	private String[] ck;
	private List<String> pnamelist;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlytarea = extqlytarea == null ? new ExtQlyTarea() : extqlytarea;
		ExtQlyTarea extqlytareatemp = new ExtQlyTarea();
		try {
			BeanUtils.copyProperties(extqlytareatemp, extqlytarea);
			// 将拼音简码查询模糊化
			try {
				if (extqlytarea.getAreapy() != null) {
					extqlytareatemp.setAreapy(QueryLikeUtil.getLikeStr(extqlytareatemp.getAreapy()));
				}
			} catch (Exception e) {
			}
			// 将上级地区编号查询模糊化
			try {
				if (extqlytarea.getPid() != null) {
					extqlytareatemp.setPid(QueryLikeUtil.getLikeStr(extqlytareatemp.getPid()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlytareatemp.setIsPage("1");
		extqlytareatemp.setSort("DESC");
		extqlytareatemp.setSortCol("aid");

		extqlytareatemp.setPageSize(15);

		getRecordCurpage("qlytarea");

		sql = "qlytarea.bhc_getAllList";
		sqlcount = "qlytarea.bhc_getAllCount";
		pc = QlyTareaDao.getInstence().getInfoList(currentpage, extqlytareatemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlytarealist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlytarea != null) {
			sql = "qlytarea.bhc_selectByPrimaryKey";
			qlytarea = (QlyTarea) QlyTareaDao.getInstence().getInfo(sql, qlytarea);
		}
		sql = "qlytarea.bhc_getAllList";
		pnamelist = QlyTareaDao.getInstence().getList(sql);
		recordCurpage("qlytarea", currentpage);
		pagename = "WEB-INF/web/pj/qlytareaedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlytarea.getArea() != null && qlytarea.getArea().length() > 0) {
			qlytarea.setAreapy(ChineseToPY.getPinYinHeadChar(qlytarea.getArea()));
		}
		if (qlytarea.getAid() == null || qlytarea.getAid().equals("")) {
			sql = "qlytarea.bhc_insert";
			QlyTareaDao.getInstence().insert(sql, qlytarea);
			msg = "增加成功";
		} else {
			sql = "qlytarea.bhc_update";
			QlyTareaDao.getInstence().update(sql, qlytarea);
			msg = "更新成功";
		}
		Map m = new HashMap();
		printMsg(msg, "qlytarea!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "qlytarea.bhc_delete";
		QlyTareaDao.getInstence().delete(sql, qlytarea);
		recordCurpage("qlytarea", currentpage);
		printMsg("删除成功", "window.location.href='qlytarea!list.do'");
		return this.NONE;
	}

	/**
	 * 批量删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlytarea = extqlytarea == null ? new ExtQlyTarea() : extqlytarea;
		if (ck != null && ck.length > 0) {
			sql = "qlytarea.bhc_delete_batch";
			for (String sid : ck) {
				extqlytarea.getPlist().add(sid);
			}
			QlyViewDao.getInstence().delete(sql, extqlytarea);
		}
		recordCurpage("qlytarea", currentpage);
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlytarea!list.do", m);
		return this.NONE;
	}

	public String selectlist() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlytarea = extqlytarea == null ? new ExtQlyTarea() : extqlytarea;
		ExtQlyTarea extqlytareatemp = new ExtQlyTarea();
		try {
			BeanUtils.copyProperties(extqlytareatemp, extqlytarea);
			// 将拼音简码查询模糊化
			try {
				if (extqlytarea.getAreapy() != null) {
					extqlytareatemp.setAreapy(QueryLikeUtil.getLikeStr(extqlytareatemp.getAreapy()));
				}
			} catch (Exception e) {
			}
			// 将上级地区编号查询模糊化
			try {
				if (extqlytarea.getPid() != null) {
					extqlytareatemp.setPid(QueryLikeUtil.getLikeStr(extqlytareatemp.getPid()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlytareatemp.setIsPage("1");
		extqlytareatemp.setSort("DESC");
		extqlytareatemp.setSortCol("aid");

		extqlytareatemp.setPageSize(15);

		sql = "qlytarea.bhc_getAllListSelect";
		sqlcount = "qlytarea.bhc_getAllCountSelect";
		pc = QlyTareaDao.getInstence().getInfoList(currentpage, extqlytareatemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlytarealistselect.jsp";
		return SUCCESS;
	}

	public String ajaxlist() {
		extqlytarea = extqlytarea == null ? new ExtQlyTarea() : extqlytarea;
		ExtQlyTarea extqlytareatemp = new ExtQlyTarea();
		try {
			BeanUtils.copyProperties(extqlytareatemp, extqlytarea);
			// 将拼音简码查询模糊化
			try {
				if (extqlytarea.getAreapy() != null) {
					extqlytareatemp.setAreapy(QueryLikeUtil.getLikeStr(extqlytareatemp.getAreapy()));
				}
			} catch (Exception e) {
			}
			// 将上级地区编号查询模糊化
			try {
				if (extqlytarea.getPid() != null) {
					extqlytareatemp.setPid(QueryLikeUtil.getLikeStr(extqlytareatemp.getPid()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlytareatemp.setIsPage(null);
		extqlytareatemp.setSort("DESC");
		extqlytareatemp.setSortCol("aid");

		sql = "qlytarea.bhc_getAllListSelect";
		ilist = QlyTareaDao.getInstence().getList(sql, extqlytarea);
		printXML(ilist);
		return Action.NONE;
	}

	public QlyTarea getQlytarea() {
		return this.qlytarea;
	}

	public void setQlytarea(QlyTarea qlytarea) {
		this.qlytarea = qlytarea;
	}

	public ExtQlyTarea getExtqlytarea() {
		return this.extqlytarea;
	}

	public void setExtqlytarea(ExtQlyTarea extqlytarea) {
		this.extqlytarea = extqlytarea;
	}

	public List<String> getPnamelist() {
		return this.pnamelist;
	}

	public void setPnamelist(List<String> pnamelist) {
		this.pnamelist = pnamelist;
	}

	public String[] getCk() {
		return ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

}
