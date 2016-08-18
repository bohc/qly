package com.Interceptor;

import java.util.Map;

import com.base.SessionBean;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends AbstractInterceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
	}

	// 拦截Action处理的拦截方法
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		// 取出名为user的session属性
		// HttpServletRequest request = ServletActionContext.getRequest();
		// HttpSession session = request.getSession();

		if (session != null && session.get("sb") != null) {
			// Object action = invocation.getAction();
			SessionBean sb = (SessionBean) session.get("sb");
			// 如果没有登陆，或者登陆所有的用户名不是aumy，都返回重新登陆
			if (sb != null && sb.getQlyuserinfo() != null) {
				return invocation.invoke();
			}
		}
		// 没有登陆，将服务器提示设置成一个HttpServletRequest属性
		ctx.put("tip", "您还没有<SPAN class=hilite3>登录</SPAN>，请登陆系统");
		return Action.LOGIN;
	}
}
