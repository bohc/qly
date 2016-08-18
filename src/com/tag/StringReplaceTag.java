package com.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 */
@SuppressWarnings("serial")
public class StringReplaceTag extends BodyTagSupport {

	private String str;
	private String source = "";
	private String dest = "";

	public StringReplaceTag() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			if (str != null) {
				str = str.replace("<", "&lt;");
				str = str.replace(">", "&gt;");
			}
			pageContext.getOut().write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public void doInitBody() throws JspException {
		super.doInitBody();
	}

	@Override
	public void setBodyContent(BodyContent b) {
		this.bodyContent = b;
		super.setBodyContent(b);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

}
