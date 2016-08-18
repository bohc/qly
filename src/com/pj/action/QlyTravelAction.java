/**
 *柏红春
 *Tue Nov 11 13:41:50 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.base.SessionBean;
import com.pj.bean.QlyTravel;
import com.pj.bean.extend.ExtQlyTravel;
import com.pj.dao.QlyTravelDao;
import com.pj.dao.QlyTraveltypeDao;
import com.util.QueryLikeUtil;

@SuppressWarnings("serial")
public class QlyTravelAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyTravel qlytravel;
	private ExtQlyTravel extqlytravel;
	private String[] ck;
	private List<String> vtypenamelist;
	private List<String> innfeaturelist;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlytravel = extqlytravel == null ? new ExtQlyTravel() : extqlytravel;
		ExtQlyTravel extqlytraveltemp = new ExtQlyTravel();
		try {
			BeanUtils.copyProperties(extqlytraveltemp, extqlytravel);
			// 将行程分类名称查询模糊化
			try {
				if (extqlytravel.getVtypename() != null) {
					extqlytraveltemp.setVtypename(QueryLikeUtil.getLikeStr(extqlytraveltemp.getVtypename()));
				}
			} catch (Exception e) {
			}
			// 将行程标题查询模糊化
			try {
				if (extqlytravel.getTraveltitle() != null) {
					extqlytraveltemp.setTraveltitle(QueryLikeUtil.getLikeStr(extqlytraveltemp.getTraveltitle()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlytraveltemp.setIsPage("1");
		extqlytraveltemp.setSort("DESC");
		extqlytraveltemp.setSortCol("ltid");

		extqlytraveltemp.setPageSize(15);
		
		getRecordCurpage("qlytravel");

		sql = "qlytravel.bhc_getAllList";
		sqlcount = "qlytravel.bhc_getAllCount";
		pc = QlyTravelDao.getInstence().getInfoList(currentpage, extqlytraveltemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlytravellist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlytravel != null) {
			sql = "qlytravel.bhc_selectByPrimaryKey";
			qlytravel = (QlyTravel) QlyTravelDao.getInstence().getInfo(sql, qlytravel);
		}
		
		recordCurpage("qlytravel", currentpage);
		
		sql = "qlytraveltype.bhc_getAllList";
		vtypenamelist = QlyTravelDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/qlytraveledit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlytravel != null) {
			sql = "qlytravel.bhc_selectByPrimaryKey";
			qlytravel = (QlyTravel) QlyTravelDao.getInstence().getInfo(sql, qlytravel);
			qlytravel.setLtid(null);
		}
		
		recordCurpage("qlytravel", currentpage);
		
		sql = "qlytraveltype.bhc_getAllList";
		vtypenamelist = QlyTravelDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/qlytraveledit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		SessionBean sb = (SessionBean) session.get("sb");
		if (qlytravel.getLtid() == null || qlytravel.getLtid().equals("")) {
			qlytravel.setEntime(new Date());
			qlytravel.setEnman(sb.getQlyuserinfo().getRealname());
			sql = "qlytravel.bhc_insert";
			QlyTravelDao.getInstence().insert(sql, qlytravel);
			msg = "增加成功";
		} else {
			sql = "qlytravel.bhc_update";
			QlyTravelDao.getInstence().update(sql, qlytravel);
			msg = "更新成功";
		}
		printMsg(msg, "window.location.href='qlytravel!list.do'");
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "qlytravel.bhc_delete";
		QlyTravelDao.getInstence().delete(sql, qlytravel);
		
		recordCurpage("qlytravel", currentpage);
		
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlytravel!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlytravel = extqlytravel == null ? new ExtQlyTravel() : extqlytravel;
		if (ck != null && ck.length > 0) {
			sql = "qlytravel.bhc_delete_batch";
			for (String sid : ck) {
				extqlytravel.getPlist().add(sid);
			}
			QlyTravelDao.getInstence().delete(sql, extqlytravel);
		}
		
		recordCurpage("qlytravel", currentpage);
		
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlytravel!list.do", m);
		return this.NONE;
	}

	/**
	 * 供选择使用
	 */
	public String selectlist() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlytravel = extqlytravel == null ? new ExtQlyTravel() : extqlytravel;
		ExtQlyTravel extqlytraveltemp = new ExtQlyTravel();
		try {
			BeanUtils.copyProperties(extqlytraveltemp, extqlytravel);
			// 将行程分类名称查询模糊化
			try {
				if (extqlytravel.getVtypename() != null) {
					extqlytraveltemp.setVtypename(QueryLikeUtil.getLikeStr(extqlytraveltemp.getVtypename()));
				}
			} catch (Exception e) {
			}
			// 将行程标题查询模糊化
			try {
				if (extqlytravel.getTraveltitle() != null) {
					extqlytraveltemp.setTraveltitle(QueryLikeUtil.getLikeStr(extqlytraveltemp.getTraveltitle()));
				}
			} catch (Exception e) {
			}
			// 将自费查询模糊化
			try {
				if (extqlytravel.getSelfexpense() != null) {
					extqlytraveltemp.setSelfexpense(QueryLikeUtil.getLikeStr(extqlytraveltemp.getSelfexpense()));
				}
			} catch (Exception e) {
			}
			// 将购物查询模糊化
			try {
				if (extqlytravel.getShop() != null) {
					extqlytraveltemp.setShop(QueryLikeUtil.getLikeStr(extqlytraveltemp.getShop()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlytraveltemp.setIsPage("1");
		extqlytraveltemp.setSort("DESC");
		extqlytraveltemp.setSortCol("ltid");

		extqlytraveltemp.setPageSize(15);

		sql = "qlytravel.bhc_getAllListSelect";
		sqlcount = "qlytravel.bhc_getAllCountSelect";
		pc = QlyTravelDao.getInstence().getInfoList(currentpage, extqlytraveltemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlytravellistselect.jsp";

		sql = "qlytraveltype.bhc_getAllListSelect";
		vtypenamelist = QlyTraveltypeDao.getInstence().getList(sql);
		
		return SUCCESS;
	}
	
	public QlyTravel getQlytravel() {
		return this.qlytravel;
	}

	public void setQlytravel(QlyTravel qlytravel) {
		this.qlytravel = qlytravel;
	}

	public ExtQlyTravel getExtqlytravel() {
		return this.extqlytravel;
	}

	public void setExtqlytravel(ExtQlyTravel extqlytravel) {
		this.extqlytravel = extqlytravel;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

	public List<String> getVtypenamelist() {
		return this.vtypenamelist;
	}

	public void setVtypenamelist(List<String> vtypenamelist) {
		this.vtypenamelist = vtypenamelist;
	}

	public List<String> getInnfeaturelist() {
		return this.innfeaturelist;
	}

	public void setInnfeaturelist(List<String> innfeaturelist) {
		this.innfeaturelist = innfeaturelist;
	}
}
