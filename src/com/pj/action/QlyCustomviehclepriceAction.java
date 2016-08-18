/**
*柏红春
*Thu Mar 17 14:42:24 CST 2016
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
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.base.SessionBean;
import com.pj.bean.QlyCustomviehcleprice;
import com.pj.bean.extend.ExtQlyCustomviehcleprice;
import com.pj.dao.QlyCitytocitypriceDao;
import com.pj.dao.QlyCustomviehclepriceDao;
import com.util.ChineseToPY;
import com.util.DateJsonValueProcessor;
import com.util.QueryLikeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@SuppressWarnings("serial")
public class QlyCustomviehclepriceAction extends ActionBase {
	private QlyCustomviehcleprice qlycustomviehcleprice;
	private ExtQlyCustomviehcleprice extqlycustomviehcleprice;
	private String[] ck;
	private List<String> citynamelist;

	public String list() {
		extqlycustomviehcleprice = extqlycustomviehcleprice == null ? new ExtQlyCustomviehcleprice() : extqlycustomviehcleprice;
		ExtQlyCustomviehcleprice extqlycustomviehclepricetemp = new ExtQlyCustomviehcleprice();
		try {
			BeanUtils.copyProperties(extqlycustomviehclepricetemp, extqlycustomviehcleprice);
			// 将出发城市简码查询模糊化
			try {
				if (extqlycustomviehcleprice.getFromcityjm() != null) {
					extqlycustomviehclepricetemp.setFromcityjm(QueryLikeUtil.getLikeStr(extqlycustomviehclepricetemp.getFromcityjm()));
				}
			} catch (Exception e) {
			}
			// 将目的城市简码查询模糊化
			try {
				if (extqlycustomviehcleprice.getTocityjm() != null) {
					extqlycustomviehclepricetemp.setTocityjm(QueryLikeUtil.getLikeStr(extqlycustomviehclepricetemp.getTocityjm()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlycustomviehclepricetemp.setIsPage("1");
		extqlycustomviehclepricetemp.setSort("DESC");
		extqlycustomviehclepricetemp.setSortCol("id");

		extqlycustomviehclepricetemp.setPageSize(15);

		sql = "extqlycustomviehcleprice.bhc_getAllList";
		sqlcount = "extqlycustomviehcleprice.bhc_getAllCount";
		pc = QlyCustomviehclepriceDao.getInstence().getInfoList(currentpage, extqlycustomviehclepricetemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlycustomviehclepricelist.jsp";
		return SUCCESS;
	}

	public String listSelect() {
		extqlycustomviehcleprice = extqlycustomviehcleprice == null ? new ExtQlyCustomviehcleprice() : extqlycustomviehcleprice;
		ExtQlyCustomviehcleprice extqlycustomviehclepricetemp = new ExtQlyCustomviehcleprice();
		try {
			BeanUtils.copyProperties(extqlycustomviehclepricetemp, extqlycustomviehcleprice);
			// 将出发城市简码查询模糊化
			try {
				if (extqlycustomviehcleprice.getFromcityjm() != null) {
					extqlycustomviehclepricetemp.setFromcityjm(QueryLikeUtil.getLikeStr(extqlycustomviehclepricetemp.getFromcityjm()));
				}
			} catch (Exception e) {
			}
			// 将目的城市简码查询模糊化
			try {
				if (extqlycustomviehcleprice.getTocityjm() != null) {
					extqlycustomviehclepricetemp.setTocityjm(QueryLikeUtil.getLikeStr(extqlycustomviehclepricetemp.getTocityjm()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlycustomviehclepricetemp.setIsPage("1");
		extqlycustomviehclepricetemp.setSort("DESC");
		extqlycustomviehclepricetemp.setSortCol("id");

		extqlycustomviehclepricetemp.setPageSize(15);

		sql = "extqlycustomviehcleprice.bhc_getAllListSelect";
		sqlcount = "extqlycustomviehcleprice.bhc_getAllCountSelect";
		pc = QlyCustomviehclepriceDao.getInstence().getInfoList(currentpage, extqlycustomviehclepricetemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/vehicleselect.jsp";
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String edit() {
		if (qlycustomviehcleprice != null && qlycustomviehcleprice.getId() != null) {
			sql = "extqlycustomviehcleprice.bhc_selectByPrimaryKey";
			qlycustomviehcleprice = (QlyCustomviehcleprice) QlyCustomviehclepriceDao.getInstence().getInfo(sql, qlycustomviehcleprice);
		}
		sql = "qlytarea.bhc_getAllList";
		citynamelist = QlyCitytocitypriceDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/vehicleedit.jsp";
		return SUCCESS;
	}

	// 修改价格
	public String editprice() throws UnsupportedEncodingException {
		StringBuffer sbs = new StringBuffer();
		int results = 0;
		try {
			sql = "extqlycustomviehcleprice.bhc_selectByPrimaryKey";
			QlyCustomviehcleprice qobj = (QlyCustomviehcleprice) QlyCustomviehclepriceDao.getInstence().getInfo(sql, qlycustomviehcleprice);
			qobj.setPrice(qlycustomviehcleprice.getPrice());
			if (qobj != null) {
				sql = "extqlycustomviehcleprice.bhc_update";
				QlyCustomviehclepriceDao.getInstence().update(sql, qobj);
			}
			results = 1;
		} catch (Exception e) {
		}
		JSONObject jo = new JSONObject();
		jo.put("results", results);
		sbs.append(jo.toString());
		inputStream = new ByteArrayInputStream(sbs.toString().getBytes("UTF-8"));
		return "ajax-success";
	}

	// 修改儿童价格
	public String editChildrenprice() throws UnsupportedEncodingException {
		StringBuffer sbs = new StringBuffer();
		int results = 0;
		try {
			sql = "extqlycustomviehcleprice.bhc_selectByPrimaryKey";
			QlyCustomviehcleprice qobj = (QlyCustomviehcleprice) QlyCustomviehclepriceDao.getInstence().getInfo(sql, qlycustomviehcleprice);
			qobj.setChildrenprice(qlycustomviehcleprice.getChildrenprice());
			if (qobj != null) {
				sql = "extqlycustomviehcleprice.bhc_update";
				QlyCustomviehclepriceDao.getInstence().update(sql, qobj);
			}
			results = 1;
		} catch (Exception e) {
		}
		JSONObject jo = new JSONObject();
		jo.put("results", results);
		sbs.append(jo.toString());
		inputStream = new ByteArrayInputStream(sbs.toString().getBytes("UTF-8"));
		return "ajax-success";
	}

	@SuppressWarnings("unchecked")
	public String copy() {
		if (qlycustomviehcleprice != null && qlycustomviehcleprice.getId() != null) {
			sql = "extqlycustomviehcleprice.bhc_selectByPrimaryKey";
			qlycustomviehcleprice = (QlyCustomviehcleprice) QlyCustomviehclepriceDao.getInstence().getInfo(sql, qlycustomviehcleprice);
			qlycustomviehcleprice.setId(null);
		}
		sql = "qlytarea.bhc_getAllList";
		citynamelist = QlyCustomviehclepriceDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/vehicleedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		SessionBean sb = (SessionBean) session.get("sb");
		int existflag = Integer.parseInt(QlyCustomviehclepriceDao.getInstence().getInfo("extqlycustomviehcleprice.bhc_exist", qlycustomviehcleprice).toString());
		if (existflag == 0) {
			if (qlycustomviehcleprice.getFromcityname() != null) {
				qlycustomviehcleprice.setFromcitypy(ChineseToPY.getPinYinHeadChar(qlycustomviehcleprice.getFromcityname()));
			}
			if (qlycustomviehcleprice.getTocityname() != null) {
				qlycustomviehcleprice.setTocitypy(ChineseToPY.getPinYinHeadChar(qlycustomviehcleprice.getTocityname()));
			}
			qlycustomviehcleprice.setEntime(new Date());
			qlycustomviehcleprice.setEnman(sb.getQlyuserinfo().getUsername());
			if (qlycustomviehcleprice.getId() == null || qlycustomviehcleprice.getId().equals("")) {
				sql = "extqlycustomviehcleprice.bhc_insert";
				QlyCustomviehclepriceDao.getInstence().insert(sql, qlycustomviehcleprice);
				msg = "增加成功";
			} else {
				sql = "extqlycustomviehcleprice.bhc_update";
				QlyCustomviehclepriceDao.getInstence().update(sql, qlycustomviehcleprice);
				msg = "更新成功";
			}
		} else {
			msg = "已经存在。";
		}
		Map<String, String> m = new HashMap<String, String>();
		printMsg(msg, "qlycustomviehcleprice!list.do", m);
		return this.NONE;
	}

	/**
	 * 保存方法
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String saveAjax() throws UnsupportedEncodingException {
		StringBuffer sbs = new StringBuffer();
		SessionBean sb = (SessionBean) session.get("sb");
		int results = 0;
		int existflag = Integer.parseInt(QlyCustomviehclepriceDao.getInstence().getInfo("extqlycustomviehcleprice.bhc_exist", qlycustomviehcleprice).toString());
		if (existflag == 0) {
			if (qlycustomviehcleprice.getFromcityname() != null) {
				qlycustomviehcleprice.setFromcitypy(ChineseToPY.getPinYinHeadChar(qlycustomviehcleprice.getFromcityname()));
			}
			if (qlycustomviehcleprice.getTocityname() != null) {
				qlycustomviehcleprice.setTocitypy(ChineseToPY.getPinYinHeadChar(qlycustomviehcleprice.getTocityname()));
			}
			qlycustomviehcleprice.setEntime(new Date());
			qlycustomviehcleprice.setEnman(sb.getQlyuserinfo().getUsername());
			if (qlycustomviehcleprice.getId() == null || qlycustomviehcleprice.getId().equals("")) {
				sql = "extqlycustomviehcleprice.bhc_insert";
				QlyCustomviehclepriceDao.getInstence().insert(sql, qlycustomviehcleprice);
				msg = "增加成功";
			} else {
				sql = "extqlycustomviehcleprice.bhc_update";
				QlyCustomviehclepriceDao.getInstence().update(sql, qlycustomviehcleprice);
				msg = "更新成功";
			}
			results = 1;
		} else {
			msg = "已经存在。";
		}

		JSONObject jo = new JSONObject();
		JsonConfig jf = new JsonConfig();
		jf.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jf.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jo.put("results", results);
		jo.put("obj", JSONObject.fromObject(qlycustomviehcleprice, jf));
		sbs.append(jo.toString());
		inputStream = new ByteArrayInputStream(sbs.toString().getBytes("UTF-8"));
		return "ajax-success";
	}

	/**
	 * Ajax 调用显示
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String showAjaxCome() throws UnsupportedEncodingException {
		StringBuffer sbs = new StringBuffer();
		int results = 0;
		try {
			sql = "extqlycustomviehcleprice.bhc_selectInFromcityjm";
			ilist = QlyCustomviehclepriceDao.getInstence().getList(sql, extqlycustomviehcleprice);
			results=1;
		} catch (Exception e) {
		}
		JSONObject jo = new JSONObject();
		JsonConfig jf = new JsonConfig();
		jf.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jf.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jo.put("results", results);
		jo.put("obj", JSONArray.fromObject(ilist, jf));
		sbs.append(jo.toString());
		inputStream = new ByteArrayInputStream(sbs.toString().getBytes("UTF-8"));
		return "ajax-success";
	}
	
	/**
	 * Ajax 回程调用显示
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String showAjaxBack() throws UnsupportedEncodingException {
		StringBuffer sbs = new StringBuffer();
		int results = 0;
		try {
			sql = "extqlycustomviehcleprice.bhc_selectIntocityjm";
			ilist = QlyCustomviehclepriceDao.getInstence().getList(sql, extqlycustomviehcleprice);
			results=1;
		} catch (Exception e) {
		}
		JSONObject jo = new JSONObject();
		JsonConfig jf = new JsonConfig();
		jf.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jf.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jo.put("results", results);
		jo.put("obj", JSONArray.fromObject(ilist, jf));
		sbs.append(jo.toString());
		inputStream = new ByteArrayInputStream(sbs.toString().getBytes("UTF-8"));
		return "ajax-success";
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlycustomviehcleprice.bhc_delete";
		QlyCustomviehclepriceDao.getInstence().delete(sql, qlycustomviehcleprice);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlycustomviehcleprice!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlycustomviehcleprice = extqlycustomviehcleprice == null ? new ExtQlyCustomviehcleprice() : extqlycustomviehcleprice;
		if (ck != null && ck.length > 0) {
			sql = "extqlycustomviehcleprice.bhc_delete_batch";
			for (String sid : ck) {
				extqlycustomviehcleprice.getPlist().add(sid);
			}
			QlyCustomviehclepriceDao.getInstence().delete(sql, extqlycustomviehcleprice);
		}
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlycustomviehcleprice!list.do", m);
		return this.NONE;
	}

	public QlyCustomviehcleprice getQlycustomviehcleprice() {
		return this.qlycustomviehcleprice;
	}

	public void setQlycustomviehcleprice(QlyCustomviehcleprice qlycustomviehcleprice) {
		this.qlycustomviehcleprice = qlycustomviehcleprice;
	}

	public ExtQlyCustomviehcleprice getExtqlycustomviehcleprice() {
		return this.extqlycustomviehcleprice;
	}

	public void setExtqlycustomviehcleprice(ExtQlyCustomviehcleprice extqlycustomviehcleprice) {
		this.extqlycustomviehcleprice = extqlycustomviehcleprice;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

	public List<String> getCitynamelist() {
		return citynamelist;
	}

	public void setCitynamelist(List<String> citynamelist) {
		this.citynamelist = citynamelist;
	}

}
