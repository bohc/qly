/**
 *柏红春
 *Thu Nov 13 16:23:52 CST 2014
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.pj.bean.QlyLinetravel;
import com.pj.bean.extend.ExtQlyCustomviehcleprice;
import com.pj.bean.extend.ExtQlyLinetravel;
import com.pj.bean.extend.ExtQlyTravel;
import com.pj.dao.QlyCitytocitypriceDao;
import com.pj.dao.QlyCustomviehclepriceDao;
import com.pj.dao.QlyLinetravelDao;

@SuppressWarnings("serial")
public class QlyLinetravelAction extends ActionBase {
	private QlyLinetravel qlylinetravel;
	private ExtQlyLinetravel extqlylinetravel;
	private String[] ck;
	private List<String> vehiclelist;
	private List<String> innfeaturelist;
	private Map<String, String> mviews = new HashMap<String, String>();
	private List<ExtQlyCustomviehcleprice> mvehicles = new ArrayList<ExtQlyCustomviehcleprice>();
	private List<String> citynamelist;
	private List<String> vehiclesprice;
	private List<String> vehiclespricetitle;

	public String list() {
		extqlylinetravel = extqlylinetravel == null ? new ExtQlyLinetravel() : extqlylinetravel;
		ExtQlyLinetravel extqlylinetraveltemp = new ExtQlyLinetravel();
		try {
			BeanUtils.copyProperties(extqlylinetraveltemp, extqlylinetravel);
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlylinetraveltemp.setIsPage("1");
		extqlylinetraveltemp.setSort("ASC");
		extqlylinetraveltemp.setSortCol("daynum");

		extqlylinetraveltemp.setPageSize(15);

		sql = "qlylinetravel.bhc_getAllList";
		sqlcount = "qlylinetravel.bhc_getAllCount";
		pc = QlyLinetravelDao.getInstence().getInfoList(currentpage, extqlylinetraveltemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlylinetravellist.jsp";
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String edit() {
		if (qlylinetravel != null && qlylinetravel.getId() != null) {
			sql = "qlylinetravel.bhc_selectByPrimaryKey";
			qlylinetravel = (QlyLinetravel) QlyLinetravelDao.getInstence().getInfo(sql, qlylinetravel);
			if (qlylinetravel.getVehicle() != null && qlylinetravel.getVehicle().length() > 0) {
				String[] temps = qlylinetravel.getVehicle().replaceAll(" ", "").split(",");
				vehiclelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						vehiclelist.add(strv.trim());
					}
				}
			}
			if (qlylinetravel.getInnfeature() != null && qlylinetravel.getInnfeature().length() > 0) {
				String[] temps = qlylinetravel.getInnfeature().replaceAll(" ", "").split(",");
				innfeaturelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						innfeaturelist.add(strv.trim());
					}
				}
			}

			ExtQlyTravel eqlt = new ExtQlyTravel();
			if (qlylinetravel.getTravelcode() != null) {
				String tcodes[] = qlylinetravel.getTravelcode().split(",");
				for (String tcode : tcodes) {
					if (tcode.length() > 0) {
						eqlt.getPlist().add(tcode);
					}
				}
				sql = "qlytravel.bhc_getTravelByLtid";
				ilist = QlyLinetravelDao.getInstence().getList(sql, eqlt);
			}

			if (qlylinetravel.getViewid() != null) {
				String[] vids = qlylinetravel.getViewid().split(",");
				String[] vnames = qlylinetravel.getViewcontent().split(",");
				for (int i = 0; i < vids.length; i++) {
					mviews.put(vids[i], vnames[i]);
				}
			}
			if (qlylinetravel.getVehiclesprice() != null) {
				sql = "extqlycustomviehcleprice.bhc_selectInId";
				mvehicles = QlyCustomviehclepriceDao.getInstence().getList(sql, qlylinetravel.getVehiclesprice());
				if (mvehicles != null) {
					Map<Integer, String> m = new HashMap<Integer, String>();
					String vtitle = qlylinetravel.getVehiclespricetitle();
					if (vtitle != null) {
						String[] vtitles = vtitle.split("#");
						if (vtitles.length > 0) {
							for (String s : vtitles) {
								String[] k = s.split(":");
								m.put(Integer.parseInt(k[0]), k[1].trim());
							}
						}
					}
					for (ExtQlyCustomviehcleprice c : mvehicles) {
						if (c.getTraficaltype().trim().equals("飞机")) {
							if (m.containsKey(c.getId())) {
								String[] s = m.get(c.getId()).split(",");
								for (String p : s) {
									c.getPlist().add(p.trim());
								}
							}
						}
					}
				}
			}
		} else {
			qlylinetravel = new QlyLinetravel();
			qlylinetravel.setLtid(extqlylinetravel.getLtid());
			qlylinetravel.setLineid(extqlylinetravel.getLineid());
			qlylinetravel.setLinename(extqlylinetravel.getLinename());
		}
		pagename = "WEB-INF/web/pj/qlylinetraveledit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlylinetravel != null && qlylinetravel.getId() != null) {
			sql = "qlylinetravel.bhc_selectByPrimaryKey";
			qlylinetravel = (QlyLinetravel) QlyLinetravelDao.getInstence().getInfo(sql, qlylinetravel);
			if (qlylinetravel.getVehicle() != null && qlylinetravel.getVehicle().length() > 0) {
				String[] temps = qlylinetravel.getVehicle().replaceAll(" ", "").split(",");
				vehiclelist = new ArrayList<String>();
				if (temps.length > 0) {
					for (String strv : temps) {
						vehiclelist.add(strv.trim());
					}
				}
			}
			qlylinetravel.setId(null);
		}
		pagename = "WEB-INF/web/pj/qlylinetraveledit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (vehiclesprice != null && vehiclesprice.size() > 0) {
			StringBuffer sbs = new StringBuffer();
			for (String val : vehiclesprice) {
				sbs.append(val).append(",");
			}
			sbs.setLength(sbs.length() - 1);
			qlylinetravel.setVehiclesprice(sbs.toString());
		}
		if (vehiclespricetitle != null && vehiclespricetitle.size() > 0) {
			StringBuffer sbs = new StringBuffer();
			for (String val : vehiclespricetitle) {
				sbs.append(val).append("#");
			}
			sbs.setLength(sbs.length() - 1);
			qlylinetravel.setVehiclespricetitle(sbs.toString());
		}

		if (qlylinetravel.getId() == null || qlylinetravel.getId().equals("")) {
			sql = "qlylinetravel.bhc_insert";
			QlyLinetravelDao.getInstence().insert(sql, qlylinetravel);
			msg = "增加成功";
		} else {
			sql = "qlylinetravel.bhc_update";
			QlyLinetravelDao.getInstence().update(sql, qlylinetravel);
			msg = "更新成功";
		}
		Map<String, String> m = new HashMap<String, String>();
		m.put("extqlylinetravel.ltid", qlylinetravel.getLtid() + "");
		m.put("extqlylinetravel.lineid", qlylinetravel.getLineid());
		m.put("extqlylinetravel.linename", qlylinetravel.getLinename());
		printMsg(msg, "qlylinetravel!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "qlylinetravel.bhc_delete";
		QlyLinetravelDao.getInstence().delete(sql, qlylinetravel);
		Map<String, String> m = new HashMap<String, String>();
		m.put("extqlylinetravel.ltid", qlylinetravel.getLtid() + "");
		m.put("extqlylinetravel.lineid", qlylinetravel.getLineid());
		m.put("extqlylinetravel.linename", qlylinetravel.getLinename());
		printMsg("删除成功", "qlylinetravel!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlylinetravel = extqlylinetravel == null ? new ExtQlyLinetravel() : extqlylinetravel;
		if (ck != null && ck.length > 0) {
			sql = "qlylinetravel.bhc_delete_batch";
			for (String sid : ck) {
				extqlylinetravel.getPlist().add(sid);
			}
			QlyLinetravelDao.getInstence().delete(sql, extqlylinetravel);
		}
		m = new HashMap<String, String>();
		m.put("extqlylinetravel.ltid", extqlylinetravel.getLtid() + "");
		m.put("extqlylinetravel.lineid", extqlylinetravel.getLineid());
		m.put("extqlylinetravel.linename", extqlylinetravel.getLinename());
		printMsg("删除成功", "qlylinetravel!list.do", m);
		return this.NONE;
	}

	public QlyLinetravel getQlylinetravel() {
		return this.qlylinetravel;
	}

	public void setQlylinetravel(QlyLinetravel qlylinetravel) {
		this.qlylinetravel = qlylinetravel;
	}

	public ExtQlyLinetravel getExtqlylinetravel() {
		return this.extqlylinetravel;
	}

	public void setExtqlylinetravel(ExtQlyLinetravel extqlylinetravel) {
		this.extqlylinetravel = extqlylinetravel;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

	public List<String> getVehiclelist() {
		return this.vehiclelist;
	}

	public void setVehiclelist(List<String> vehiclelist) {
		this.vehiclelist = vehiclelist;
	}

	public List<String> getInnfeaturelist() {
		return innfeaturelist;
	}

	public void setInnfeaturelist(List<String> innfeaturelist) {
		this.innfeaturelist = innfeaturelist;
	}

	public Map<String, String> getMviews() {
		return mviews;
	}

	public void setMviews(Map<String, String> mviews) {
		this.mviews = mviews;
	}

	public List<String> getCitynamelist() {
		return citynamelist;
	}

	public void setCitynamelist(List<String> citynamelist) {
		this.citynamelist = citynamelist;
	}

	public List<String> getVehiclesprice() {
		return vehiclesprice;
	}

	public void setVehiclesprice(List<String> vehiclesprice) {
		this.vehiclesprice = vehiclesprice;
	}

	public List<String> getVehiclespricetitle() {
		return vehiclespricetitle;
	}

	public void setVehiclespricetitle(List<String> vehiclespricetitle) {
		this.vehiclespricetitle = vehiclespricetitle;
	}

	public List<ExtQlyCustomviehcleprice> getMvehicles() {
		return mvehicles;
	}

	public void setMvehicles(List<ExtQlyCustomviehcleprice> mvehicles) {
		this.mvehicles = mvehicles;
	}

}
