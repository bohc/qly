package com.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
/**
 * 判断用户是否有这个功能
 * @author Administrator
 *
 */
public class EqualStringTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String de;
	private String source;
	private boolean bl=false;

	public EqualStringTag() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		try{
			if(bl){
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		if(source!=null && de!=null){
			//String[] strArr=de.split(",");
			//for(int i=0;i<strArr.length;i++){
				if(source.trim().equals(de.trim())){
					bl = true;
					return BodyTag.EVAL_BODY_BUFFERED;
				}
			//}
			
			bl = false;
		}else{
			return SKIP_BODY;
		}
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

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isBl() {
		return bl;
	}

	public void setBl(boolean bl) {
		this.bl = bl;
	}
}
