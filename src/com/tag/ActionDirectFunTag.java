package com.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
/**
 * 判断用户是否有这个功能
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class ActionDirectFunTag extends BodyTagSupport {

	private String rflag;
	private String mf;
	private String params;

	public ActionDirectFunTag() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		try{
			if(rflag != null){
				String directurl="\r\n\tvar vm=parent.document.getElementById(\"menuarea\");"+
				   "\r\n\tvm.style.display=\"none\";"+
				   "\r\n\tparent.document.getElementById(\"MainIframe\").src=";
				if(rflag.equals("pjmap888")){
					pageContext.getOut().write("top.location.href='tblnodexml!tblnodeDistributing.do"+(params==null?"":"?"+params)+"';");
				}else if(rflag.equals("pjhsc888")){
					pageContext.getOut().write(directurl+"\"plog!list.do"+(params==null?"":"?"+params)+"\";");
				}else if(rflag.equals("pjhtj888")){
					pageContext.getOut().write(directurl+"\"pstat!list.do"+(params==null?"":"?"+params)+"\";");
				}else if(rflag.equals("pjtim888")){
					pageContext.getOut().write(directurl+"\"tblsubevent!newInfoList.do\";");
				}else if(rflag.equals("pjstat888")){
					pageContext.getOut().write(directurl+"\"tblsubevent!list.do\";");
				}else if(rflag.equals("pjsdu888")){
					pageContext.getOut().write(directurl+"\"chac!list.do\";");
				}else if(rflag.equals("pjsds888")){
					pageContext.getOut().write(directurl+"\"orderstat!list.do\";");
				}
			}
			if(mf != null){
				String directurl="\r\n\tvar spliter=parent.document.getElementById(\"spliter\");"+
				  				 "\r\n\tspliter.click();";
				if(mf.equals("1")){
					pageContext.getOut().write(directurl);
				}
			}
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

	public String getRflag() {
		return rflag;
	}

	public void setRflag(String rflag) {
		this.rflag = rflag;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
