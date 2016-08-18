/**
 *柏红春
 *Sun Dec 07 00:19:41 CST 2014
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.base.SessionBean;
import com.opensymphony.xwork2.Action;
import com.pj.bean.QlyCitytocitypricecustom;
import com.pj.bean.QlyCustomviehcleprice;
import com.pj.bean.extend.ExtQlyCitytocitypricecustom;
import com.pj.dao.QlyCitytocitypriceDao;
import com.pj.dao.QlyCitytocitypricecustomDao;
import com.pj.dao.QlyCustomviehclepriceDao;
import com.util.CommonUtil;
import com.util.QueryLikeUtil;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class QlyCitytocitypricecustomAction extends ActionBase {
	private QlyCitytocitypricecustom qlycitytocitypricecustom;
	private ExtQlyCitytocitypricecustom extqlycitytocitypricecustom;
	private String[] ck;
	private List<String> fromcitynamelist;
	private String vehiclecome;
	private List<String> vehiclecometime;
	private String vehiclecomeseattype;
	private String vehicleback;
	private String vehiclebackseattype;
	private List<String> vehiclebacktime;
	private String placeid, backplaceid, placename, backplacename;

	public String list() {
		extqlycitytocitypricecustom = extqlycitytocitypricecustom == null ? new ExtQlyCitytocitypricecustom() : extqlycitytocitypricecustom;
		ExtQlyCitytocitypricecustom extqlycitytocitypricecustomtemp = new ExtQlyCitytocitypricecustom();
		try {
			BeanUtils.copyProperties(extqlycitytocitypricecustomtemp, extqlycitytocitypricecustom);
			// 将线路名称查询模糊化
			try {
				extqlycitytocitypricecustomtemp.setLinename(null);// 这里先不用这个字段进行查询
				if (extqlycitytocitypricecustom.getLinename() != null) {
					extqlycitytocitypricecustomtemp.setLinename(QueryLikeUtil.getLikeStr(extqlycitytocitypricecustomtemp.getLinename()));
				}
			} catch (Exception e) {
			}
			// 将出发城市名jm简拼
			try {
				if (extqlycitytocitypricecustom.getFromcityjm() != null) {
					extqlycitytocitypricecustomtemp.setFromcityjm(CommonUtil.formatAreaCode(extqlycitytocitypricecustomtemp.getFromcityjm()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlycitytocitypricecustomtemp.setIsPage("1");
		extqlycitytocitypricecustomtemp.setSort("DESC");
		extqlycitytocitypricecustomtemp.setSortCol("id");

		extqlycitytocitypricecustomtemp.setPageSize(15);

		sql = "extqlycitytocitypricecustom.bhc_getAllList";
		sqlcount = "extqlycitytocitypricecustom.bhc_getAllCount";
		pc = QlyCitytocitypricecustomDao.getInstence().getInfoList(currentpage, extqlycitytocitypricecustomtemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlycitytocitypricecustomlist.jsp";

		sql = "qlytarea.bhc_getAllList";
		fromcitynamelist = QlyCitytocitypriceDao.getInstence().getList(sql);
		return SUCCESS;
	}

	public String edit() {
		if (qlycitytocitypricecustom != null && qlycitytocitypricecustom.getId() != null) {
			sql = "extqlycitytocitypricecustom.bhc_selectByPrimaryKey";
			qlycitytocitypricecustom = (QlyCitytocitypricecustom) QlyCitytocitypricecustomDao.getInstence().getInfo(sql, qlycitytocitypricecustom);
		} else {
			if (extqlycitytocitypricecustom != null) {
				qlycitytocitypricecustom = new QlyCitytocitypricecustom();
				qlycitytocitypricecustom.setLineid(extqlycitytocitypricecustom.getLineid());
				qlycitytocitypricecustom.setLinename(extqlycitytocitypricecustom.getLinename());
			}
		}
		sql = "qlytarea.bhc_getAllList";
		fromcitynamelist = QlyCitytocitypriceDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/qlycitytocitypricecustomedit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlycitytocitypricecustom != null && qlycitytocitypricecustom.getId() != null) {
			sql = "extqlycitytocitypricecustom.bhc_selectByPrimaryKey";
			qlycitytocitypricecustom = (QlyCitytocitypricecustom) QlyCitytocitypricecustomDao.getInstence().getInfo(sql, qlycitytocitypricecustom);
			qlycitytocitypricecustom.setId(null);
		}
		pagename = "WEB-INF/web/pj/qlycitytocitypricecustomedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlycitytocitypricecustom.getId() == null || qlycitytocitypricecustom.getId().equals("")) {
			sql = "extqlycitytocitypricecustom.bhc_insert";
			QlyCitytocitypricecustomDao.getInstence().insert(sql, qlycitytocitypricecustom);
			sql = "extqlyline.statCustomCount";
			QlyCitytocitypricecustomDao.getInstence().update(sql);
			msg = "增加成功";
		} else {
			sql = "extqlycitytocitypricecustom.bhc_update";
			QlyCitytocitypricecustomDao.getInstence().update(sql, qlycitytocitypricecustom);
			msg = "更新成功";
		}
		Map<String, String> m = new HashMap<String, String>();
		printMsg(msg, "qlycitytocitypricecustom!list.do", m);
		return this.NONE;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String saveprocedu() {
		qlycitytocitypricecustom.setTraficaltype(vehiclecome);
		qlycitytocitypricecustom.setVehiclecomeseattype(vehiclecomeseattype);
		if (vehiclecometime != null) {
			StringBuffer sf = new StringBuffer();
			for (String stime : vehiclecometime) {
				sf.append(stime).append(",");
			}
			sf.setLength(sf.length() - 1);
			qlycitytocitypricecustom.setVehiclecometime(sf.toString());
		}
		qlycitytocitypricecustom.setVehiclebacktype(vehicleback);
		qlycitytocitypricecustom.setVehiclebackseattype(vehiclebackseattype);
		if (vehiclebacktime != null) {
			StringBuffer sf = new StringBuffer();
			for (String stime : vehiclebacktime) {
				sf.append(stime).append(",");
			}
			sf.setLength(sf.length() - 1);
			qlycitytocitypricecustom.setVehiclebacktime(sf.toString());
		}

		SessionBean sb = (SessionBean) session.get("sb");
		int f = 0;
		sql = "extqlycitytocitypricecustom.addCityToCityPrice";
		StringBuffer fc = new StringBuffer();
		if (ck != null) {
			for (String fjm : ck) {
				fc.append(fjm).append(",");
			}
		}
		if (fc.length() > 0) {
			if (qlycitytocitypricecustom.getPrice() == null) {
				qlycitytocitypricecustom.setPrice(0D);
			}
			fc.setLength(fc.length() - 1);
			Map<String, Object> pm = new HashMap<String, Object>();
			pm.put("lid", qlycitytocitypricecustom.getLineid());
			pm.put("lname", qlycitytocitypricecustom.getLinename());
			pm.put("cnt", ck.length);
			pm.put("fc", fc.toString());
			pm.put("tra", qlycitytocitypricecustom.getTraficaltype());
			pm.put("vehiclecomeseattype", qlycitytocitypricecustom.getVehiclecomeseattype());
			pm.put("vehiclecometime", qlycitytocitypricecustom.getVehiclecometime());
			pm.put("vehiclebacktype", qlycitytocitypricecustom.getVehiclebacktype());
			pm.put("vehiclebackseattype", qlycitytocitypricecustom.getVehiclebackseattype());
			pm.put("vehiclebacktime", qlycitytocitypricecustom.getVehiclebacktime());
			pm.put("price", qlycitytocitypricecustom.getPrice());
			pm.put("childrenprice", qlycitytocitypricecustom.getChildrenprice());
			pm.put("enman", sb.getQlyuserinfo().getUsername());
			pm.put("result", null);
			f = QlyCitytocitypriceDao.getInstence().update(sql, pm);
		}

		if (f > 0) {
			msg = "增加成功";
		} else {
			msg = "操作失败";
		}
		Map<String, String> m = new HashMap<String, String>();
		m.put("extqlycitytocitypricecustom.lineid", qlycitytocitypricecustom.getLineid() + "");
		m.put("extqlycitytocitypricecustom.linename", qlycitytocitypricecustom.getLinename());
		printMsg(msg, "qlycitytocitypricecustom!list.do", m);
		return this.NONE;
	}

	/**
	 * 保存方法
	 */
	public String copyprice() {
		SessionBean sb = (SessionBean) session.get("sb");
		if (extqlycitytocitypricecustom != null) {
			String tstr = extqlycitytocitypricecustom.getTolineid();
			String[] strs = tstr.split(",");
			if (strs.length > 0) {
				Map<String, Object> pm = new HashMap<String, Object>();
				pm.put("lid", extqlycitytocitypricecustom.getLineid());
				pm.put("tlen", strs.length);
				pm.put("tid", extqlycitytocitypricecustom.getTolineid());
				pm.put("pdif", extqlycitytocitypricecustom.getPricedif());
				pm.put("enman", sb.getQlyuserinfo().getUsername());
				sql = "extqlycitytocitypricecustom.copyCityToCityPrice";
				QlyCitytocitypriceDao.getInstence().update(sql, pm);
			}
		}
		msg = "拷贝完成";
		printMsg(msg, "window.history.go(-1);");
		return Action.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlycitytocitypricecustom.bhc_selectByPrimaryKey";
		QlyCitytocitypricecustom qcc = (QlyCitytocitypricecustom) QlyCitytocitypricecustomDao.getInstence().getInfo(sql, qlycitytocitypricecustom);
		sql = "extqlycitytocitypricecustom.bhc_delete";
		QlyCitytocitypricecustomDao.getInstence().delete(sql, qlycitytocitypricecustom);
		sql = "extqlyline.statCustomCount";
		QlyCitytocitypricecustomDao.getInstence().update(sql);
		Map<String, String> m = new HashMap<String, String>();
		m.put("extqlycitytocitypricecustom.lineid", qcc.getLineid() + "");
		m.put("extqlycitytocitypricecustom.linename", qcc.getLinename());
		printMsg("删除成功", "qlycitytocitypricecustom!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlycitytocitypricecustom = extqlycitytocitypricecustom == null ? new ExtQlyCitytocitypricecustom() : extqlycitytocitypricecustom;
		if (ck != null && ck.length > 0) {
			sql = "extqlycitytocitypricecustom.bhc_delete_batch";
			for (String sid : ck) {
				extqlycitytocitypricecustom.getPlist().add(sid);
			}
			QlyCitytocitypricecustomDao.getInstence().delete(sql, extqlycitytocitypricecustom);
			sql = "extqlyline.statCustomCount";
			QlyCitytocitypricecustomDao.getInstence().update(sql);
		}
		m = new HashMap<String, String>();
		m.put("extqlycitytocitypricecustom.lineid", extqlycitytocitypricecustom.getLineid() + "");
		m.put("extqlycitytocitypricecustom.linename", extqlycitytocitypricecustom.getLinename());
		printMsg("删除成功", "qlycitytocitypricecustom!list.do", m);
		return this.NONE;
	}

	// 修改成人价格
	public String editprice() throws UnsupportedEncodingException {
		StringBuffer sbs = new StringBuffer();
		int results = 0;
		try {
			sql = "extqlycitytocitypricecustom.bhc_selectByPrimaryKey";
			QlyCitytocitypricecustom qobj = (QlyCitytocitypricecustom) QlyCitytocitypricecustomDao.getInstence().getInfo(sql, qlycitytocitypricecustom);
			qobj.setPrice(qlycitytocitypricecustom.getPrice());
			if (qobj != null) {
				sql = "extqlycitytocitypricecustom.bhc_update";
				QlyCitytocitypricecustomDao.getInstence().update(sql, qobj);
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
			sql = "extqlycitytocitypricecustom.bhc_selectByPrimaryKey";
			QlyCitytocitypricecustom qobj = (QlyCitytocitypricecustom) QlyCitytocitypricecustomDao.getInstence().getInfo(sql, qlycitytocitypricecustom);
			qobj.setChildrenprice(qlycitytocitypricecustom.getChildrenprice());
			if (qobj != null) {
				sql = "extqlycitytocitypricecustom.bhc_update";
				QlyCitytocitypricecustomDao.getInstence().update(sql, qobj);
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
	
	/**
	 * 根据出发城市更新价格，ＡＪＡＸ使用
	 * 
	 * @return
	 */
	public String editpricebycitys() {
		if (extqlycitytocitypricecustom != null && extqlycitytocitypricecustom.getPricedif() != 0 && extqlycitytocitypricecustom.getLineid()!=null) {
			sql = "extqlycitytocitypricecustom.update_price";
			int result = QlyCitytocitypriceDao.getInstence().update(sql, extqlycitytocitypricecustom);
			if (result > 0) {
				msg = "<root><msg>success</msg></root>";
			} else {
				msg = "<root><msg>fail</msg></root>";
			}
		} else {
			msg = "<root><msg>fail</msg></root>";
		}
		printXML(msg);
		return Action.NONE;
	}

	public QlyCitytocitypricecustom getQlycitytocitypricecustom() {
		return this.qlycitytocitypricecustom;
	}

	public void setQlycitytocitypricecustom(QlyCitytocitypricecustom qlycitytocitypricecustom) {
		this.qlycitytocitypricecustom = qlycitytocitypricecustom;
	}

	public ExtQlyCitytocitypricecustom getExtqlycitytocitypricecustom() {
		return this.extqlycitytocitypricecustom;
	}

	public void setExtqlycitytocitypricecustom(ExtQlyCitytocitypricecustom extqlycitytocitypricecustom) {
		this.extqlycitytocitypricecustom = extqlycitytocitypricecustom;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

	public List<String> getFromcitynamelist() {
		return fromcitynamelist;
	}

	public void setFromcitynamelist(List<String> fromcitynamelist) {
		this.fromcitynamelist = fromcitynamelist;
	}

	public String getVehiclecome() {
		return vehiclecome;
	}

	public void setVehiclecome(String vehiclecome) {
		this.vehiclecome = vehiclecome;
	}

	public List<String> getVehiclecometime() {
		return vehiclecometime;
	}

	public void setVehiclecometime(List<String> vehiclecometime) {
		this.vehiclecometime = vehiclecometime;
	}

	public String getVehiclecomeseattype() {
		return vehiclecomeseattype;
	}

	public void setVehiclecomeseattype(String vehiclecomeseattype) {
		this.vehiclecomeseattype = vehiclecomeseattype;
	}

	public String getVehicleback() {
		return vehicleback;
	}

	public void setVehicleback(String vehicleback) {
		this.vehicleback = vehicleback;
	}

	public String getVehiclebackseattype() {
		return vehiclebackseattype;
	}

	public void setVehiclebackseattype(String vehiclebackseattype) {
		this.vehiclebackseattype = vehiclebackseattype;
	}

	public List<String> getVehiclebacktime() {
		return vehiclebacktime;
	}

	public void setVehiclebacktime(List<String> vehiclebacktime) {
		this.vehiclebacktime = vehiclebacktime;
	}

	public String getPlaceid() {
		return placeid;
	}

	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}

	public String getBackplaceid() {
		return backplaceid;
	}

	public void setBackplaceid(String backplaceid) {
		this.backplaceid = backplaceid;
	}

	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public String getBackplacename() {
		return backplacename;
	}

	public void setBackplacename(String backplacename) {
		this.backplacename = backplacename;
	}

}
