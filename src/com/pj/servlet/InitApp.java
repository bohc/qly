package com.pj.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.BaseIni;

@SuppressWarnings("serial")
public class InitApp extends HttpServlet {

	public InitApp() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		// 得到当前系统的路径
		BaseIni.setBasepath(application.getRealPath("/"));
		BaseIni.setSc(application);
		//加载要发送的预警
		//new Thread(new LoadNoticeToSys()).start();
		//加载要发的通知
		//new Thread(new LoadNoticeFromTable()).start();
		//启动UDP服务
		//new Thread(new UdpServerStart()).start();
		//new Thread(new UdpClientStart()).start();
	}

}
