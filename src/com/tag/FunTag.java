package com.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
/**
 * 判断用户是否有这个功能
 * @author Administrator
 *
 */
public class FunTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String funid;
	private List funlist;
	private boolean bl=false;

	public FunTag() {
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
		if(funlist!=null && funlist.size()>0){
			String[] strArr=funid.split(",");
			for(int i=0;i<funlist.size();i++){
				for(int j=0;j<strArr.length;j++){
					if(funlist.get(i).equals(strArr[j].trim())){
						bl = true;
						return BodyTag.EVAL_BODY_BUFFERED;//EVAL_BODY_TAG;
//						EVAL_BODY_INCLUDE       ,   TagSupport类   
//						  EVAL_BODY_AGAIN           ,   IteraterTag类   
//						  EVAL_BODY_BUFFERD       ,   BodyTag类 
					}
				}
			}
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

	public String getFunid() {
		return funid;
	}

	public void setFunid(String funid) {
		this.funid = funid;
	}

	public List getFunlist() {
		return (funlist==null?(new ArrayList()):funlist);
	}

	public void setFunlist(List funlist) {
		this.funlist = funlist;
	}
	
	

}
