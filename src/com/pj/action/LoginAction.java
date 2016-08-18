package com.pj.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.base.SessionBean;
import com.pj.bean.QlyUserinfo;
import com.pj.dao.QlyUserinfoDao;

@SuppressWarnings("serial")
public class LoginAction extends ActionBase {
	private String vusername;
	private String vpassword;
	private String rerand;

	public String loginv() {
		// uname="admin";
		// pwd="666888";
		// rerand="123456";
		this.clearActionErrors();
		if (StringUtils.defaultString(rerand).equals("")) {
			addActionError("验证码不能为空！！");
			pagename = "WEB-INF/login.jsp";
			return SUCCESS;
		}
		if (!rerand.equals(this.session.get("rand"))) {
			addActionError("输入的验证码不正确！！");
			pagename = "WEB-INF/login.jsp";
			return SUCCESS;
		}
		if (StringUtils.defaultString(vusername).equals("")) {
			addActionError("用户名不能为空！！");
			pagename = "WEB-INF/login.jsp";
			return SUCCESS;
		}
		if (StringUtils.defaultString(vpassword).equals("")) {
			addActionError("密码不能为空！！");
			pagename = "WEB-INF/login.jsp";
			return SUCCESS;
		}

		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("username", vusername);
		pmap.put("pwd", vpassword);
		sql = "qlyuserinfo.bhc_login";
		QlyUserinfo tui = (QlyUserinfo) QlyUserinfoDao.getInstence().getInfo(sql, pmap);
		if (tui == null) {
			addActionError("此用户不存在！！");
			pagename = "WEB-INF/login.jsp";
			return SUCCESS;
		}

		SessionBean sb = new SessionBean();
		sb.setQlyuserinfo(tui);

		List<String> listfun = new ArrayList<String>();
		listfun.add("2301");
		listfun.add("2302");
		listfun.add("2303");
		listfun.add("2304");
		listfun.add("0101");
		listfun.add("0102");
		listfun.add("0103");
		sb.setListFun(listfun);

		this.session.put("sb", sb);

		pagename = "index!index.do";
		return "rsuccess";
	}

	public String logout() {
		this.session.clear();
		return SUCCESS;
	}

	public String login() {
		pagename = "/fail.jsp";
		return SUCCESS;
	}

	public String getVusername() {
		return vusername;
	}

	public void setVusername(String vusername) {
		this.vusername = vusername;
	}

	public String getVpassword() {
		return vpassword;
	}

	public void setVpassword(String vpassword) {
		this.vpassword = vpassword;
	}

	public String getRerand() {
		return rerand;
	}

	public void setRerand(String rerand) {
		this.rerand = rerand;
	}

}
