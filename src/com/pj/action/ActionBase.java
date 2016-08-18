package com.pj.action;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.base.SessionBean;
import com.opensymphony.xwork2.ActionContext;
import com.util.CommonUtil;
import com.util.SqlDateConverter;
import com.util.SqlTimestampConverter;
import com.util.XmlPersistence;

@SuppressWarnings("serial")
public class ActionBase extends ParamActionBase implements SessionAware {

	public String[] chbx;
	protected String chitem;
	protected String encity;
	protected InputStream inputStream;

	static {
		ConvertUtils.register((Converter) new SqlDateConverter(null), Date.class);
		ConvertUtils.register((Converter) new SqlTimestampConverter(null), Date.class);
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	// 记录下当前的页面到第几页了
	@SuppressWarnings("unchecked")
	public void recordCurpage(String key, String value) {
		Map<String, String> pageMap = (Map<String, String>) session.get("pageMap");
		pageMap = pageMap == null ? new HashMap<String, String>() : pageMap;
		pageMap.put(key, value);
		session.put("pageMap", pageMap);
	}

	// 取得改变前的页面到第几页了
	@SuppressWarnings("unchecked")
	public void getRecordCurpage(String key) {
		Map<String, String> pageMap = (Map<String, String>) session.get("pageMap");
		if (pageMap != null && pageMap.get(key) != null) {
			currentpage= pageMap.get(key);
			pageMap.put(key, null);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> void printToAsXml(T obj) throws IOException {
		if (obj == null) {
			printXML("");
		} else {

			XmlPersistence<T> xps = new XmlPersistence<T>("", false);
			if (obj instanceof ArrayList || obj instanceof List) {
				for (int i = 0; i < ((ArrayList) obj).size(); i++) {
					xps.add((T) ((ArrayList) obj).get(i));
				}
			} else {
				xps.add(obj);
			}
			msg = xps.getXmlFile();
			// printXML(msg);
			printStringToStream(msg);
		}
	}

	public void printMsg(String msg, String returnUrl) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + msg + "\");" + returnUrl + ";");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	// 弹出提示信息，以POST形式提交，跳转页面
	public void printMsg(String msg, String action, Map<String, String> map) {
		Iterator<String> keys = map.keySet().iterator();
		String para = "";
		while (keys.hasNext()) {
			String key = keys.next().toString();
			para += "<input type='hidden' name='" + key + "' value='" + map.get(key) + "'></input>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer();
		xmlBuf.append("<form id='submit' method='post' action='" + action + "'>");
		xmlBuf.append(para);
		xmlBuf.append("</form>");
		xmlBuf.append("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + msg + "\"); document.getElementById('submit').submit();");
		xmlBuf.append("</script>");

		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void alertMsg(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + msg + "\");");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void printHtml(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer(msg);
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void printMsgRedirect(String returnUrl) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append(returnUrl + ";");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void printCloseWindow() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.close();");// window.parent.window.parent.jBox.close()
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void printXML(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer();
		xmlBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuf.append(msg);
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public void printXML(Object obj) {
		String outstr = "-1";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		XmlPersistence<Object> xp = new XmlPersistence<Object>("", false, true);
		if (obj != null) {
			if (obj instanceof List) {
				for (int i = 0; i < ((List) obj).size(); i++) {
					Object o = ((List) obj).get(i);
					xp.add(o);
				}
			} else {
				xp.add(obj);
			}
			outstr = xp.getXmlFile();
		}
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(outstr);
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void printJson(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer(msg);
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public static String formatxml(String str) throws UnsupportedEncodingException, IOException, DocumentException {
		// System.out.println(" str : " + str);

		SAXReader reader = new SAXReader();
		// System.out.println(reader);
		// 注释：创建一个串的字符输入流
		StringReader in = new StringReader(str);
		org.dom4j.Document doc = (org.dom4j.Document) reader.read(in);
		// System.out.println(doc.getRootElement());
		// 注释：创建输出格式
		OutputFormat formater = OutputFormat.createPrettyPrint();
		// 注释：设置xml的输出编码
		formater.setEncoding("utf-8");
		// 注释：创建输出(目标)
		StringWriter out = new StringWriter();
		// 注释：创建输出流
		XMLWriter writer = new XMLWriter(out, formater);
		// 注释：输出格式化的串到目标中，执行后。格式化后的串保存在out中。
		writer.write(doc);

		System.out.println(out.toString());
		// 注释：返回我们格式化后的结果
		return out.toString();
	}

	public void reDir(String returnUrl) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.location.href='" + returnUrl + "'" + ";");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void reDirReload(String returnUrl) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.location.href='javascript:history.back(-2)';");
		// xmlBuf.append("window.document.reload();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void reReload() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.location.reload();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void reBackHistory(String msg, int nHistory) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + msg + "\");");
		xmlBuf.append("window.location.href='javascript:history.back(" + nHistory + ")';");
		// xmlBuf.append("window.document.reload();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void reBackHistory2(int nHistory) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.location.href='javascript:history.back(" + nHistory + ")';");
		xmlBuf.append("window.location.reload();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回
	 */
	public void reBack(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + msg + "\");");
		xmlBuf.append("window.location.href='javascript:history.back(-1)'");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭窗体
	 */
	public void reClose(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + msg + "\");");
		xmlBuf.append("window.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭子窗体，刷新父窗体
	 */
	public void reCloseReload(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + msg + "\");");
		xmlBuf.append("window.opener.location.reload();");
		xmlBuf.append("window.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭子窗体，刷新父窗体
	 */
	public void CloseJbox() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.parent.reFresh();");
		xmlBuf.append("window.parent.window.jBox.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭子窗体，刷新父窗体
	 */
	public void parentFun(String strFunNane) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.parent." + strFunNane + "();");
		// xmlBuf.append("window.parent.window.jBox.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 当前窗口函数
	 */
	public void winFun(String strFunNane) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append(strFunNane + "();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭子窗体，刷新父窗体
	 */
	public void reCloseJboxReload() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.parent.window.location.reload();");
		xmlBuf.append("window.parent.window.jBox.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭子窗体，刷新父窗体
	 */
	public void reCloseJboxReloadFirst(String strURL) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.parent.window.location.href='" + strURL + "';");
		xmlBuf.append("window.parent.window.jBox.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭子窗体，刷新父窗体
	 */
	public void reCloseJbox() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.parent.window.jBox.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭子窗体，刷新父窗体
	 */
	public void reCloseJbox(String strMsg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + strMsg + "\");");
		xmlBuf.append("window.parent.window.jBox.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭子窗体，刷新父窗体
	 */
	public void reCloseReloadURL(String msg, String strURL) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("alert(\"" + msg + "\");");
		xmlBuf.append("window.opener.location=\"" + strURL + "\";");
		xmlBuf.append("window.close();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void reload() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.location.reload();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void refresh() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer xmlBuf = new StringBuffer("<script language=\"javascript\">");
		xmlBuf.append("window.location.refresh();");
		xmlBuf.append("</script>");
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(xmlBuf.toString());
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向页面写json信息
	 * 
	 * @param msg
	 */
	public void printData(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		// response.setContentType("text/xml;charset=UTF-8");
		// text/plain
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			pw.write(msg);
			pw.flush();
			pw.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public int getRandom() {
		Random random = new Random();
		return Math.abs(random.nextInt() % 100000) + 100000;
	}

	protected int getUserID() {
		@SuppressWarnings("unused")
		SessionBean sb = (SessionBean) session.get("sb");
		return 0;
		/*
		 * Userinfo user = sb.getUserinfo(); return user.getUserid();
		 */
	}

	protected int getUserEntID() {
		@SuppressWarnings("unused")
		SessionBean sb = (SessionBean) session.get("sb");
		return 0;
		/*
		 * Userinfo user = sb.getUserinfo(); return user.getCompid();
		 */
	}

	/**
	 * 输出文件
	 * 
	 * @param response
	 * @param path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void printStringToStream(String msg) {
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		// 读到流中
		InputStream inStream = null;
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"stream\"");
		// 循环取出流中的数据
		byte[] b = new byte[1024];
		int len;
		try {
			inStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));
			CommonUtil.compress(inStream, response.getOutputStream());
			// response.getOutputStream().write(inStream.available());
			// while ((len = inStream.read(b)) > 0){
			// response.getOutputStream().write(b, 0, len);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getOutputStream().write(e.getMessage().getBytes(), 0, e.getMessage().getBytes().length);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (null != inStream) {
					inStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String[] getChbx() {
		return chbx;
	}

	public void setChbx(String[] chbx) {
		this.chbx = chbx;
	}

	public String getChitem() {
		return chitem;
	}

	public void setChitem(String chitem) {
		this.chitem = chitem;
	}

	public String getEncity() {
		return encity;
	}

	public void setEncity(String encity) {
		this.encity = encity;
	}
}
