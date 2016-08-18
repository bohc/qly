/**
 *柏红春
 *Mon Nov 10 14:44:21 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import com.pj.dao.QlyTraveltypeDao;
import com.pj.bean.QlyTraveltype;
import com.pj.bean.extend.ExtQlyTraveltype;

import com.util.QueryLikeUtil;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.base.SessionBean;

@SuppressWarnings("serial")
public class QlyTraveltypeAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyTraveltype qlytraveltype;
	private ExtQlyTraveltype extqlytraveltype;
	private String[] ck;
	private List<String> vparnamelist;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlytraveltype = extqlytraveltype == null ? new ExtQlyTraveltype()
				: extqlytraveltype;
		ExtQlyTraveltype extqlytraveltypetemp = new ExtQlyTraveltype();
		try {
			BeanUtils.copyProperties(extqlytraveltypetemp, extqlytraveltype);
			//将分类名称查询模糊化
			try {
				if (extqlytraveltype.getVtypename() != null) {
					extqlytraveltypetemp.setVtypename(QueryLikeUtil
							.getLikeStr(extqlytraveltypetemp.getVtypename()));
				}
			} catch (Exception e) {
			}
			//将上级名称查询模糊化
			try {
				if (extqlytraveltype.getVparname() != null) {
					extqlytraveltypetemp.setVparname(QueryLikeUtil
							.getLikeStr(extqlytraveltypetemp.getVparname()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		//这是分页标识
		extqlytraveltypetemp.setIsPage("1");
		extqlytraveltypetemp.setSort("DESC");
		extqlytraveltypetemp.setSortCol("id");

		extqlytraveltypetemp.setPageSize(15);
		
		getRecordCurpage("qlytraveltype");

		sql = "qlytraveltype.bhc_getAllList";
		sqlcount = "qlytraveltype.bhc_getAllCount";
		pc = QlyTraveltypeDao.getInstence().getInfoList(currentpage,
				extqlytraveltypetemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlytraveltypelist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlytraveltype != null) {
			sql = "qlytraveltype.bhc_selectByPrimaryKey";
			qlytraveltype = (QlyTraveltype) QlyTraveltypeDao.getInstence()
					.getInfo(sql, qlytraveltype);
		}
		sql = "qlytraveltype.bhc_getAllList";
		vparnamelist = QlyTraveltypeDao.getInstence().getList(sql);
		
		recordCurpage("qlytraveltype", currentpage);
		
		pagename = "WEB-INF/web/pj/qlytraveltypeedit.jsp";
		return SUCCESS;
	}

	/**
	 *保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlytraveltype.getId() == null || qlytraveltype.getId().equals("")) {
			sql = "qlytraveltype.bhc_insert";
			QlyTraveltypeDao.getInstence().insert(sql, qlytraveltype);
			msg = "增加成功";
		} else {
			sql = "qlytraveltype.bhc_update";
			QlyTraveltypeDao.getInstence().update(sql, qlytraveltype);
			msg = "更新成功";
		}
		printMsg(msg, "window.location.href='qlytraveltype!list.do'");
		return this.NONE;
	}

	/**
	 *删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "qlytraveltype.bhc_delete";
		QlyTraveltypeDao.getInstence().delete(sql, qlytraveltype);
		
		recordCurpage("qlytraveltype", currentpage);
		
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlytraveltype!list.do", m);
		return this.NONE;
	}

	/**
	 *删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlytraveltype = extqlytraveltype == null ? new ExtQlyTraveltype()
				: extqlytraveltype;
		if (ck != null && ck.length > 0) {
			sql = "qlytraveltype.bhc_delete_batch";
			for (String sid : ck) {
				extqlytraveltype.getPlist().add(sid);
			}
			QlyTraveltypeDao.getInstence().delete(sql, extqlytraveltype);
		}
		recordCurpage("qlytraveltype", currentpage);
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlytraveltype!list.do", m);
		return this.NONE;
	}

	public QlyTraveltype getQlytraveltype() {
		return this.qlytraveltype;
	}

	public void setQlytraveltype(QlyTraveltype qlytraveltype) {
		this.qlytraveltype = qlytraveltype;
	}

	public ExtQlyTraveltype getExtqlytraveltype() {
		return this.extqlytraveltype;
	}

	public void setExtqlytraveltype(ExtQlyTraveltype extqlytraveltype) {
		this.extqlytraveltype = extqlytraveltype;
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
