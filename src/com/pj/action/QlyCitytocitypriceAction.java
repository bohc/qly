/**
 *柏红春
 *Tue Nov 25 23:29:33 CST 2014
 *这个类数据的操作类,父类中已经定义好了这些变量
 *protected PageControl pc 分页用的
 *protected String currentpage 当前页
 *protected String pagename 转向页面
 *protected String msg 提示消息
 *protected String sql 列表sql
 *protected String sqlcount 数量sql
 */
package com.pj.action;

import com.opensymphony.xwork2.Action;
import com.pj.dao.QlyCitytocitypriceDao;
import com.pj.bean.QlyCitytocityprice;
import com.pj.bean.extend.ExtQlyCitytocityprice;

import com.util.QueryLikeUtil;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.base.SessionBean;

@SuppressWarnings("serial")
public class QlyCitytocitypriceAction extends ActionBase {
	private String encode = "UTF-8";
	private QlyCitytocityprice qlycitytocityprice;
	private ExtQlyCitytocityprice extqlycitytocityprice;
	private String[] ck;
	private List<String> fromcitynamelist;
	private List<String> tocitynamelist;

	public String list() {
		SessionBean sb = (SessionBean) session.get("sb");
		extqlycitytocityprice = extqlycitytocityprice == null ? new ExtQlyCitytocityprice() : extqlycitytocityprice;
		ExtQlyCitytocityprice extqlycitytocitypricetemp = new ExtQlyCitytocityprice();
		try {
			BeanUtils.copyProperties(extqlycitytocitypricetemp, extqlycitytocityprice);
			// 将出发城市名查询模糊化
			try {
				if (extqlycitytocityprice.getFromcityname() != null) {
					extqlycitytocitypricetemp.setFromcityname(QueryLikeUtil.getLikeStr(extqlycitytocitypricetemp.getFromcityname()));
				}
			} catch (Exception e) {
			}
			// 将到达城市名查询模糊化
			try {
				if (extqlycitytocityprice.getTocityname() != null) {
					extqlycitytocitypricetemp.setTocityname(QueryLikeUtil.getLikeStr(extqlycitytocitypricetemp.getTocityname()));
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		// 这是分页标识
		extqlycitytocitypricetemp.setIsPage("1");
		extqlycitytocitypricetemp.setSort("DESC");
		extqlycitytocitypricetemp.setSortCol("id");

		extqlycitytocitypricetemp.setPageSize(15);

		sql = "extqlycitytocityprice.bhc_getAllList";
		sqlcount = "extqlycitytocityprice.bhc_getAllCount";
		pc = QlyCitytocitypriceDao.getInstence().getInfoList(currentpage, extqlycitytocitypricetemp, sql, sqlcount);
		ilist = pc.getDataList();
		currentpage = "" + pc.getCurPage();
		pagename = "/WEB-INF/web/pj/qlycitytocitypricelist.jsp";
		return SUCCESS;
	}

	public String edit() {
		if (qlycitytocityprice != null && qlycitytocityprice.getId() != null) {
			sql = "extqlycitytocityprice.bhc_selectByPrimaryKey";
			qlycitytocityprice = (QlyCitytocityprice) QlyCitytocitypriceDao.getInstence().getInfo(sql, qlycitytocityprice);
		}
		sql = "qlytarea.bhc_getAllList";
		fromcitynamelist = QlyCitytocitypriceDao.getInstence().getList(sql);
		sql = "qlytarea.bhc_getAllList";
		tocitynamelist = QlyCitytocitypriceDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/qlycitytocitypriceedit.jsp";
		return SUCCESS;
	}

	public String copy() {
		if (qlycitytocityprice != null && qlycitytocityprice.getId() != null) {
			sql = "extqlycitytocityprice.bhc_selectByPrimaryKey";
			qlycitytocityprice = (QlyCitytocityprice) QlyCitytocitypriceDao.getInstence().getInfo(sql, qlycitytocityprice);
			qlycitytocityprice.setId(null);
		}
		sql = "qlytarea.bhc_getAllList";
		fromcitynamelist = QlyCitytocitypriceDao.getInstence().getList(sql);
		sql = "qlytarea.bhc_getAllList";
		tocitynamelist = QlyCitytocitypriceDao.getInstence().getList(sql);
		pagename = "WEB-INF/web/pj/qlycitytocitypriceedit.jsp";
		return SUCCESS;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String save() {
		if (qlycitytocityprice.getId() == null || qlycitytocityprice.getId().equals("")) {
			sql = "extqlycitytocityprice.bhc_insert";
			QlyCitytocitypriceDao.getInstence().insert(sql, qlycitytocityprice);
			msg = "增加成功";
		} else {
			sql = "extqlycitytocityprice.bhc_update";
			QlyCitytocitypriceDao.getInstence().update(sql, qlycitytocityprice);
			msg = "更新成功";
		}
		Map<String, String> m = new HashMap<String, String>();
		printMsg(msg, "qlycitytocityprice!list.do", m);
		return this.NONE;
	}

	/**
	 * 保存方法
	 */
	@SuppressWarnings("static-access")
	public String saveprocedu() {
		SessionBean sb = (SessionBean) session.get("sb");
		int f = 0;
		sql = "extqlycitytocityprice.addCityToCityPrice";
		StringBuffer fc = new StringBuffer();
		for (String fjm : ck) {
			fc.append(fjm).append(",");
		}
		if (fc.length() > 0) {
			fc.setLength(fc.length() - 1);
			Map<String, Object> pm = new HashMap<String, Object>();
			pm.put("cnt", ck.length);
			pm.put("fc", fc.toString());
			pm.put("tc", qlycitytocityprice.getTocityjm());
			pm.put("tra", qlycitytocityprice.getTraficaltype());
			pm.put("price", qlycitytocityprice.getPrice());
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
		printMsg(msg, "qlycitytocityprice!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String del() {
		sql = "extqlycitytocityprice.bhc_delete";
		QlyCitytocitypriceDao.getInstence().delete(sql, qlycitytocityprice);
		Map<String, String> m = new HashMap<String, String>();
		printMsg("删除成功", "qlycitytocityprice!list.do", m);
		return this.NONE;
	}

	/**
	 * 删除方法
	 */
	@SuppressWarnings("static-access")
	public String batchdel() {
		Map<String, String> m = null;
		extqlycitytocityprice = extqlycitytocityprice == null ? new ExtQlyCitytocityprice() : extqlycitytocityprice;
		if (ck != null && ck.length > 0) {
			sql = "extqlycitytocityprice.bhc_delete_batch";
			for (String sid : ck) {
				extqlycitytocityprice.getPlist().add(sid);
			}
			QlyCitytocitypriceDao.getInstence().delete(sql, extqlycitytocityprice);
		}
		m = new HashMap<String, String>();
		printMsg("删除成功", "qlycitytocityprice!list.do", m);
		return this.NONE;
	}

	/**
	 * 更新价格，ＡＪＡＸ使用
	 * 
	 * @return
	 */
	public String editprice() {
		if (qlycitytocityprice != null && qlycitytocityprice.getId() != null) {
			sql = "extqlycitytocityprice.bhc_update";
			int result = QlyCitytocitypriceDao.getInstence().update(sql, qlycitytocityprice);
			if(result==1){
				msg="success";
			}else{
				msg="fail";
			}
		}else{
			msg="fail";
		}
		printHtml(msg);
		return Action.NONE;
	}

	public QlyCitytocityprice getQlycitytocityprice() {
		return this.qlycitytocityprice;
	}

	public void setQlycitytocityprice(QlyCitytocityprice qlycitytocityprice) {
		this.qlycitytocityprice = qlycitytocityprice;
	}

	public ExtQlyCitytocityprice getExtqlycitytocityprice() {
		return this.extqlycitytocityprice;
	}

	public void setExtqlycitytocityprice(ExtQlyCitytocityprice extqlycitytocityprice) {
		this.extqlycitytocityprice = extqlycitytocityprice;
	}

	public String[] getCk() {
		return this.ck;
	}

	public void setCk(String[] ck) {
		this.ck = ck;
	}

	public List<String> getFromcitynamelist() {
		return this.fromcitynamelist;
	}

	public void setFromcitynamelist(List<String> fromcitynamelist) {
		this.fromcitynamelist = fromcitynamelist;
	}

	public List<String> getTocitynamelist() {
		return this.tocitynamelist;
	}

	public void setTocitynamelist(List<String> tocitynamelist) {
		this.tocitynamelist = tocitynamelist;
	}
}
