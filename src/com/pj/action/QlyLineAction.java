/**
 *柏红春
 *Tue Nov 11 11:40:51 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.base.BaseIni;
import com.base.SessionBean;
import com.opensymphony.xwork2.ActionSupport;
import com.pj.bean.QlyLine;
import com.pj.bean.QlyLinepic;
import com.pj.bean.QlyLinetravel;
import com.pj.bean.extend.ExtQlyLine;
import com.pj.bean.extend.ExtQlyLinepic;
import com.pj.bean.extend.ExtQlyViewpic;
import com.pj.dao.QlyLineDao;
import com.pj.dao.QlyLinepicDao;
import com.pj.dao.QlyLinetravelDao;
import com.pj.dao.QlyViewpicDao;
import com.util.Config;
import com.util.QueryLikeUtil;

import sun.security.krb5.internal.CredentialsUtil;

@SuppressWarnings("serial")
public class QlyLineAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyLine qlyline;
	private ExtQlyLine extqlyline;
	private String[] ck;
	private List<String> vfiles;
	private List<String> placenamelist;
	private List<String> linesubjectlist;
	private List<String> attributelist;
	private List<String> festivalactlist;
	private List<String> passcitylist;
	private List<String> fromcityjmlist;
	private List<String> viehcletimelist;
	private List<String> backviehcletimelist;
	private List<String> platformtypelist;

	public String list() {
		Long oldv = System.currentTimeMillis();
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyline = extqlyline == null ? new ExtQlyLine() : extqlyline;
		ExtQlyLine extqlylinetemp = new ExtQlyLine();
		try {
			BeanUtils.copyProperties(extqlylinetemp, extqlyline);
			// 将线路名称查询模糊化
			try {
				if (extqlyline.getLinename() != null) {
					extqlylinetemp.setLinename(QueryLikeUtil.getLikeStr(extqlylinetemp.getLinename()));
				}
			} catch (Exception e) {
			}
			// 将线路平台查询模糊化
			try {
				if (extqlyline.getPlatformtype() != null) {
					extqlylinetemp.setPlatformtype(QueryLikeUtil.getLikeStr(extqlylinetemp.getPlatformtype()));
				}
			} catch (Exception e) {
			}
			// 将线路编号查询模糊化
			try {
				if (extqlyline.getLineid() != null) {
					extqlylinetemp.setLineid(QueryLikeUtil.getLikeStr(extqlylinetemp.getLineid()));
				}
			} catch (Exception e) {
			}
			// 将目的地类型查询模糊化
			try {
				if (extqlyline.getDestinationtype() != null) {
					extqlylinetemp.setDestinationtype(QueryLikeUtil.getLikeStr(extqlylinetemp.getDestinationtype()));
				}
			} catch (Exception e) {
			}
			// 将集合地名称查询模糊化
			try {
				if (extqlyline.getPlacename() != null) {
					extqlylinetemp.setPlacename(QueryLikeUtil.getLikeStr(extqlylinetemp.getPlacename()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlylinetemp.setIsPage("1");
		extqlylinetemp.setSort("DESC");
		extqlylinetemp.setSortCol("id");

		extqlylinetemp.setPageSize(15);

		// 还原到上次访问的页数
		getRecordCurpage("qlyline");

		sql = "extqlyline.bhc_getAllList";
		sqlcount = "extqlyline.bhc_getAllCount";
		pc = QlyLineDao.getInstence().getInfoList(currentpage, extqlylinetemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlylinelist.jsp";

		sql = "qlytarea.bhc_getAllList";
		fromcityjmlist = QlyLineDao.getInstence().getList(sql);
		System.out.println("共耗时：" + ((System.currentTimeMillis() - oldv) / 1000));
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String edit() {
		if (qlyline != null && qlyline.getId() != null) {
			sql = "extqlyline.bhc_selectByPrimaryKey";
			qlyline = (QlyLine) QlyLineDao.getInstence().getInfo(sql, qlyline);
			if (qlyline.getLinesubject() != null && qlyline.getLinesubject().length() > 0) {
				String[] temps = qlyline.getLinesubject().replaceAll(" ", "").split(",");
				linesubjectlist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						linesubjectlist.add(strv.trim());
					}
				}
			}
			if (qlyline.getAttribute() != null && qlyline.getAttribute().length() > 0) {
				String[] temps = qlyline.getAttribute().replaceAll(" ", "").split(",");
				attributelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						attributelist.add(strv.trim());
					}
				}
			}
			if (qlyline.getFestivalact() != null && qlyline.getFestivalact().length() > 0) {
				String[] temps = qlyline.getFestivalact().replaceAll(" ", "").split(",");
				festivalactlist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						festivalactlist.add(strv.trim());
					}
				}
			}
			if (qlyline.getViehcletime() != null && qlyline.getViehcletime().length() > 0) {
				String[] temps = qlyline.getViehcletime().replaceAll(" ", "").split(",");
				viehcletimelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						viehcletimelist.add(strv.trim());
					}
				}
			}
			if (qlyline.getBackviehcletime() != null && qlyline.getBackviehcletime().length() > 0) {
				String[] temps = qlyline.getBackviehcletime().replaceAll(" ", "").split(",");
				backviehcletimelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						backviehcletimelist.add(strv.trim());
					}
				}
			}
			if (qlyline.getPlatformtype() != null && qlyline.getPlatformtype().length() > 0) {
				String[] temps = qlyline.getPlatformtype().replaceAll(" ", "").split(",");
				platformtypelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						platformtypelist.add(strv.trim());
					}
				}
			}

			sql = "extqlylinepic.bhc_getListByLineid";
			ExtQlyLinepic eql = new ExtQlyLinepic();
			eql.setLineid(qlyline.getId());
			ilist = QlyViewpicDao.getInstence().getList(sql, eql);
		}
		sql = "qlytarea.bhc_getAllList";
		placenamelist = QlyLineDao.getInstence().getList(sql);
		// sql = "qlytarea.bhc_getAllList";
		// passcitylist = QlyLineDao.getInstence().getList(sql);
		recordCurpage("qlyline", currentpage);
		pagename = "WEB-INF/web/pj/qlylineedit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlyline != null) {
			sql = "extqlyline.copyLineSP";
			Map m = new HashMap();
			m.put("lid", qlyline.getId());
			qlyline = (QlyLine) QlyLineDao.getInstence().getInfo(sql, m);
			if (qlyline.getLinesubject() != null && qlyline.getLinesubject().length() > 0) {
				String[] temps = qlyline.getLinesubject().replaceAll(" ", "").split(",");
				linesubjectlist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						linesubjectlist.add(strv.trim());
					}
				}
			}
			if (qlyline.getAttribute() != null && qlyline.getAttribute().length() > 0) {
				String[] temps = qlyline.getAttribute().replaceAll(" ", "").split(",");
				attributelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						attributelist.add(strv.trim());
					}
				}
			}
			if (qlyline.getFestivalact() != null && qlyline.getFestivalact().length() > 0) {
				String[] temps = qlyline.getFestivalact().replaceAll(" ", "").split(",");
				festivalactlist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						festivalactlist.add(strv.trim());
					}
				}
			}
		}
		sql = "qlytarea.bhc_getAllList";
		placenamelist = QlyLineDao.getInstence().getList(sql);
		// sql = "qlytarea.bhc_getAllList";
		// passcitylist = QlyLineDao.getInstence().getList(sql);
		recordCurpage("qlyline", currentpage);
		pagename = "WEB-INF/web/pj/qlylineedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		SessionBean sb = (SessionBean) session.get("sb");
		// 保证主图只有一张，取的是最后一张图片
		if (qlyline.getJourneypic() != null) {
			if (qlyline.getJourneypic().startsWith(",")) {
				qlyline.setJourneypic(qlyline.getJourneypic().substring(1));
			}
			String[] pics = qlyline.getJourneypic().split(",");
			if (pics.length > 1) {
				// File f = new File(BaseIni.getBasepath() + "/" + pics[0]);
				// if (f.exists()) {
				// f.delete();
				// }
				qlyline.setJourneypic(pics[1]);
			}
		}
		if (qlyline.getId() == null || qlyline.getId().equals("")) {
			qlyline.setCredate(new Date());
			qlyline.setEnman(sb.getQlyuserinfo().getUsername());
			sql = "extqlyline.bhc_insert";
			QlyLineDao.getInstence().insert(sql, qlyline);
			msg = "增加成功";
		} else {
			sql = "extqlyline.bhc_update";
			QlyLineDao.getInstence().update(sql, qlyline);
			QlyLinetravel qlt = new QlyLinetravel();
			qlt.setLtid(qlyline.getId());
			qlt.setLineid(qlyline.getLineid());
			qlt.setLinename(qlyline.getLinename());
			sql = "qlylinetravel.bhc_updateByLtId";
			QlyLinetravelDao.getInstence().update(sql, qlt);
			msg = "更新成功";
		}
		if (vfiles != null && vfiles.size() > 0) {
			sql = "extqlylinepic.bhc_delete_picbylineid";
			QlyLinepic ql = new QlyLinepic();
			ql.setLineid(qlyline.getId());
			QlyLinepicDao.getInstence().delete(sql, ql);
			sql = "extqlylinepic.bhc_insert";
			for (String purl : vfiles) {
				ql.setVpicfix(purl);
				QlyLinepicDao.getInstence().insert(sql, ql);
			}
		}
		Map<String, String> m = new HashMap<String, String>();
		printMsg(msg, "qlyline!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlyline.bhc_delete";
		QlyLineDao.getInstence().delete(sql, qlyline);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlyline!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String delline() {
		sql = "extqlyline.delLineSP";
		Map<String, Object> sm = new HashMap<String, Object>();
		sm.put("lid", qlyline.getId());
		sm.put("errcode", 0);
		QlyLineDao.getInstence().delete(sql, sm);
		Map<String, String> m = new HashMap<String, String>();
		if (sm.get("errcode").toString().equals("0")) {
			printMsg("删除成功。", "qlyline!list.do", m);
		} else {
			printHtml("删除失败。");
		}
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlyline = extqlyline == null ? new ExtQlyLine() : extqlyline;
		if (ck != null && ck.length > 0) {
			sql = "extqlyline.bhc_delete_batch";
			for (String sid : ck) {
				extqlyline.getPlist().add(sid);
			}
			QlyLineDao.getInstence().delete(sql, extqlyline);
		}
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlyline!list.do", m);
		return this.NONE;
	}

	public String listselect() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyline = extqlyline == null ? new ExtQlyLine() : extqlyline;
		ExtQlyLine extqlylinetemp = new ExtQlyLine();
		try {
			BeanUtils.copyProperties(extqlylinetemp, extqlyline);
			// 将线路名称查询模糊化
			try {
				if (extqlyline.getLinename() != null) {
					extqlylinetemp.setLinename(QueryLikeUtil.getLikeStr(extqlylinetemp.getLinename()));
				}
			} catch (Exception e) {
			}
			// 将目的地类型查询模糊化
			try {
				if (extqlyline.getDestinationtype() != null) {
					extqlylinetemp.setDestinationtype(QueryLikeUtil.getLikeStr(extqlylinetemp.getDestinationtype()));
				}
			} catch (Exception e) {
			}
			// 将集合地名称查询模糊化
			try {
				if (extqlyline.getPlacename() != null) {
					extqlylinetemp.setPlacename(QueryLikeUtil.getLikeStr(extqlylinetemp.getPlacename()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlylinetemp.setIsPage("1");
		extqlylinetemp.setSort("DESC");
		extqlylinetemp.setSortCol("id");

		extqlylinetemp.setPageSize(15);

		sql = "extqlyline.bhc_getAllListSelect";
		sqlcount = "extqlyline.bhc_getAllCountSelect";
		pc = QlyLineDao.getInstence().getInfoList(currentpage, extqlylinetemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlylinelistselect.jsp";
		return SUCCESS;
	}

	public String genarateLine() throws UnsupportedEncodingException {
		String sb = "{\"results\":1}";
		inputStream = new ByteArrayInputStream(sb.getBytes("UTF-8"));
		return "ajax-success";
	}

	// 修改properties文件的值
	public String xmlEdit() {
		String sb = "{\"results\":1}";
		BaseIni.xmlday = Integer.parseInt(msg);
		try {
			inputStream = new ByteArrayInputStream(sb.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			sb = "{\"results\":0}";
		}
		return "ajax-success";
	}

	public QlyLine getQlyline() {
		return this.qlyline;
	}

	public void setQlyline(QlyLine qlyline) {
		this.qlyline = qlyline;
	}

	public ExtQlyLine getExtqlyline() {
		return this.extqlyline;
	}

	public void setExtqlyline(ExtQlyLine extqlyline) {
		this.extqlyline = extqlyline;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

	public List<String> getPlacenamelist() {
		return this.placenamelist;
	}

	public void setPlacenamelist(List<String> placenamelist) {
		this.placenamelist = placenamelist;
	}

	public List<String> getLinesubjectlist() {
		return this.linesubjectlist;
	}

	public void setLinesubjectlist(List<String> linesubjectlist) {
		this.linesubjectlist = linesubjectlist;
	}

	public List<String> getAttributelist() {
		return this.attributelist;
	}

	public void setAttributelist(List<String> attributelist) {
		this.attributelist = attributelist;
	}

	public List<String> getFestivalactlist() {
		return this.festivalactlist;
	}

	public void setFestivalactlist(List<String> festivalactlist) {
		this.festivalactlist = festivalactlist;
	}

	public List<String> getPasscitylist() {
		return this.passcitylist;
	}

	public void setPasscitylist(List<String> passcitylist) {
		this.passcitylist = passcitylist;
	}

	public List<String> getVfiles() {
		return vfiles;
	}

	public void setVfiles(List<String> vfiles) {
		this.vfiles = vfiles;
	}

	public List<String> getFromcityjmlist() {
		return fromcityjmlist;
	}

	public void setFromcityjmlist(List<String> fromcityjmlist) {
		this.fromcityjmlist = fromcityjmlist;
	}

	public List<String> getViehcletimelist() {
		return viehcletimelist;
	}

	public void setViehcletimelist(List<String> viehcletimelist) {
		this.viehcletimelist = viehcletimelist;
	}

	public List<String> getBackviehcletimelist() {
		return backviehcletimelist;
	}

	public void setBackviehcletimelist(List<String> backviehcletimelist) {
		this.backviehcletimelist = backviehcletimelist;
	}

	public List<String> getPlatformtypelist() {
		return platformtypelist;
	}

	public void setPlatformtypelist(List<String> platformtypelist) {
		this.platformtypelist = platformtypelist;
	}

}
