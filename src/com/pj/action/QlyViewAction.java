/**
 *柏红春
 *Mon Nov 10 13:26:41 CST 2014
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.base.SessionBean;
import com.pj.bean.QlyView;
import com.pj.bean.QlyViewpic;
import com.pj.bean.extend.ExtQlyView;
import com.pj.bean.extend.ExtQlyViewpic;
import com.pj.dao.QlyViewDao;
import com.pj.dao.QlyViewpicDao;
import com.util.QueryLikeUtil;

@SuppressWarnings("serial")
public class QlyViewAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyView qlyview;
	private ExtQlyView extqlyview;
	private String[] ck;
	private List<String> typenamelist;
	private List<String> citynamelist;
	private List<String> viewtypelist;
	private List<String> suitablelist;
	private List<String> vfiles;
	private File viewpic;
	private String viewpicContentType;
	private String viewpicFileName;
	private File videourl;
	private String videourlContentType;
	private String videourlFileName;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyview = extqlyview == null ? new ExtQlyView() : extqlyview;
		ExtQlyView extqlyviewtemp = new ExtQlyView();
		try {
			BeanUtils.copyProperties(extqlyviewtemp, extqlyview);
			// 将景点名称查询模糊化
			try {
				if (extqlyview.getViewname() != null) {
					extqlyviewtemp.setViewname(QueryLikeUtil.getLikeStr(extqlyviewtemp.getViewname()));
				}
			} catch (Exception e) {
			}
			// 将分类名称查询模糊化
			try {
				if (extqlyview.getTypename() != null) {
					extqlyviewtemp.setTypename(QueryLikeUtil.getLikeStr(extqlyviewtemp.getTypename()));
				}
			} catch (Exception e) {
			}
			// 将城市名称查询模糊化
			try {
				if (extqlyview.getCityname() != null) {
					extqlyviewtemp.setCityname(QueryLikeUtil.getLikeStr(extqlyviewtemp.getCityname()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlyviewtemp.setIsPage("1");
		extqlyviewtemp.setSort("DESC");
		extqlyviewtemp.setSortCol("id");

		extqlyviewtemp.setPageSize(15);
		
		getRecordCurpage("qlyview");

		sql = "extqlyview.bhc_getAllList";
		sqlcount = "extqlyview.bhc_getAllCount";
		pc = QlyViewDao.getInstence().getInfoList(currentpage, extqlyviewtemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlyviewlist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlyview != null) {
			sql = "extqlyview.bhc_selectByPrimaryKey";
			qlyview = (QlyView) QlyViewDao.getInstence().getInfo(sql, qlyview);
			if (qlyview.getViewtype() != null && qlyview.getViewtype().length() > 0) {
				String[] temps = qlyview.getViewtype().replaceAll(" ", "").split(",");
				viewtypelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						viewtypelist.add(strv.trim());
					}
				}
			}
			if (qlyview.getSuitable() != null && qlyview.getSuitable().length() > 0) {
				String[] temps = qlyview.getSuitable().replaceAll(" ", "").split(",");
				suitablelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						suitablelist.add(strv.trim());
					}
				}
			}
			
			sql="extqlyviewpic.bhc_getListByViewid";
			ExtQlyViewpic qvp=new ExtQlyViewpic();
			qvp.setViewid(qlyview.getId());
			ilist=QlyViewpicDao.getInstence().getList(sql, qvp);
		}
		
		recordCurpage("qlyview", currentpage);
		
		sql = "qlyviewtype.bhc_getAllList";
		typenamelist = QlyViewDao.getInstence().getList(sql);
		sql = "qlytarea.bhc_getAllList";
		citynamelist = QlyViewDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/qlyviewedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		SessionBean sb = (SessionBean) session.get("sb");
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		String fpath = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
		if (viewpicFileName != null && viewpicFileName.length() > 0) {
			try {
				String f_fix = viewpicFileName.substring(viewpicFileName.lastIndexOf("/"));
				String filepath = "upload/" + fpath + qlyview.getViewpic() + "/" + f_fix;
				FileUtils.copyFile(this.viewpic, new File(filepath));
				qlyview.setViewpic(filepath);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		if (videourlFileName != null && videourlFileName.length() > 0) {
			try {
				String f_fix = videourlFileName.substring(videourlFileName.lastIndexOf("/"));
				String filepath = "upload/" + fpath + qlyview.getVideourl() + "/" + f_fix;
				FileUtils.copyFile(this.videourl, new File(filepath));
				qlyview.setVideourl(filepath);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		if (qlyview.getId() == null || qlyview.getId().equals("")) {
			qlyview.setUserid(sb.getQlyuserinfo().getUsername());
			qlyview.setUsername(sb.getQlyuserinfo().getRealname());
			sql = "extqlyview.bhc_insert";
			QlyViewDao.getInstence().insert(sql, qlyview);
			msg = "增加成功";
		} else {
			sql = "extqlyview.bhc_update";
			QlyViewDao.getInstence().update(sql, qlyview);
			msg = "更新成功";
		}
		if (vfiles != null && vfiles.size() > 0) {
			sql = "extqlyviewpic.bhc_delete_picall";
			QlyViewpic qvp = new QlyViewpic();
			qvp.setViewid(qlyview.getId());
			QlyViewpicDao.getInstence().delete(sql, qvp);
			sql = "extqlyviewpic.bhc_insert";
			for (String purl : vfiles) {
				qvp.setPicurl(purl);
				QlyViewpicDao.getInstence().insert(sql, qvp);
			}
		}
		printMsg(msg, "window.location.href='qlyview!list.do'");
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlyview.bhc_delete";
		QlyViewDao.getInstence().delete(sql, qlyview);
		recordCurpage("qlyview", currentpage);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlyview!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlyview = extqlyview == null ? new ExtQlyView() : extqlyview;
		if (ck != null && ck.length > 0) {
			sql = "extqlyview.bhc_delete_batch";
			for (String sid : ck) {
				extqlyview.getPlist().add(sid);
			}
			QlyViewDao.getInstence().delete(sql, extqlyview);
		}
		recordCurpage("qlyview", currentpage);
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlyview!list.do", m);
		return this.NONE;
	}

	public String selectVewList() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlyview = extqlyview == null ? new ExtQlyView() : extqlyview;
		ExtQlyView extqlyviewtemp = new ExtQlyView();
		try {
			BeanUtils.copyProperties(extqlyviewtemp, extqlyview);
			// 将景点名称查询模糊化
			try {
				if (extqlyview.getViewname() != null) {
					extqlyviewtemp.setViewname(QueryLikeUtil.getLikeStr(extqlyviewtemp.getViewname()));
				}
			} catch (Exception e) {
			}
			// 将分类名称查询模糊化
			try {
				if (extqlyview.getTypename() != null) {
					extqlyviewtemp.setTypename(QueryLikeUtil.getLikeStr(extqlyviewtemp.getTypename()));
				}
			} catch (Exception e) {
			}
			// 将省份查询模糊化
			try {
				if (extqlyview.getCityname() != null) {
					extqlyviewtemp.setCityname(QueryLikeUtil.getLikeStr(extqlyviewtemp.getCityname()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlyviewtemp.setIsPage("1");
		extqlyviewtemp.setSort("DESC");
		extqlyviewtemp.setSortCol("viewid");

		extqlyviewtemp.setPageSize(15);

		sql = "extqlyview.bhc_getAllListSelect";
		sqlcount = "extqlyview.bhc_getAllCountSelect";
		pc = QlyViewDao.getInstence().getInfoList(currentpage, extqlyviewtemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		
		sql = "qlyviewtype.bhc_getAllList";
		typenamelist = QlyViewDao.getInstence().getList(sql);
		
		pagename = "/WEB-INF/web/pj/qlyviewlistselect.jsp";
		return SUCCESS;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String delimg() {
		if (extqlyview != null) {
			qlyview = new QlyView();
			qlyview.setId(extqlyview.getId());
		}

		// Map<String, String> m = new HashMap<String, String>();
		// printMsg("删除成功", "qlyview!list.do", m);
		return this.NONE;
	}

	public QlyView getQlyview() {
		return this.qlyview;
	}

	public void setQlyview(QlyView qlyview) {
		this.qlyview = qlyview;
	}

	public ExtQlyView getExtqlyview() {
		return this.extqlyview;
	}

	public void setExtqlyview(ExtQlyView extqlyview) {
		this.extqlyview = extqlyview;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

	public List<String> getTypenamelist() {
		return this.typenamelist;
	}

	public void setTypenamelist(List<String> typenamelist) {
		this.typenamelist = typenamelist;
	}

	public List<String> getCitynamelist() {
		return this.citynamelist;
	}

	public void setCitynamelist(List<String> citynamelist) {
		this.citynamelist = citynamelist;
	}

	public List<String> getViewtypelist() {
		return this.viewtypelist;
	}

	public void setViewtypelist(List<String> viewtypelist) {
		this.viewtypelist = viewtypelist;
	}

	public List<String> getSuitablelist() {
		return this.suitablelist;
	}

	public void setSuitablelist(List<String> suitablelist) {
		this.suitablelist = suitablelist;
	}

	public File getViewpic() {
		return this.viewpic;
	}

	public void setViewpic(File viewpic) {
		this.viewpic = viewpic;
	}

	public String getViewpicContentType() {
		return this.viewpicContentType;
	}

	public void setViewpicContentType(String viewpicContentType) {
		this.viewpicContentType = viewpicContentType;
	}

	public String getViewpicFileName() {
		return this.viewpicFileName;
	}

	public void setViewpicFileName(String viewpicFileName) {
		this.viewpicFileName = viewpicFileName;
	}

	public File getVideourl() {
		return this.videourl;
	}

	public void setVideourl(File videourl) {
		this.videourl = videourl;
	}

	public String getVideourlContentType() {
		return this.videourlContentType;
	}

	public void setVideourlContentType(String videourlContentType) {
		this.videourlContentType = videourlContentType;
	}

	public String getVideourlFileName() {
		return this.videourlFileName;
	}

	public void setVideourlFileName(String videourlFileName) {
		this.videourlFileName = videourlFileName;
	}

	public List<String> getVfiles() {
		return vfiles;
	}

	public void setVfiles(List<String> vfiles) {
		this.vfiles = vfiles;
	}

}
