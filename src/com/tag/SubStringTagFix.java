package com.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
/**
 * 根据传入的大小，取相应长度的字符长度
 * @author Administrator
 *
 */
public class SubStringTagFix extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String str;
	private int start=0;
	private int end=0;

	public SubStringTagFix() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		try{
			if(str != null){
				if(str.length()>end){
					str=str.substring(start,end);
					str+="...";
				}
			}
			pageContext.getOut().write(str);
			
		}catch(Exception e){
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
		this.bodyContent=b;
		super.setBodyContent(b);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
