<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>景点分类</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlyviewtypeedit.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:100%;} .form-label{width:100px;}
	  		.form table{width:100%;} .form-header{width:100%;}
	  	</style>
	    
		<script type="text/javascript">
			var vparnamezNodes =[
			<s:iterator value="vparnamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.vtypecode}", pId:"${did.vparcode}", name:"${did.vtypename}", open:true}</s:if>
				<s:else>,{ id:"${did.vtypecode}", pId:"${did.vparcode}", name:"${did.vtypename}", open:false}</s:else>
			</s:iterator>];
		</script>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">景点分类</label></div>
		<s:form name="eidtForm" action="qlyviewtype!save.do" method="post" theme="simple" >
				
			<table class="grid-table">
				<s:hidden name="qlyviewtype.id" id="id"/>
				<tr>
					<td class="form-label"><label>上级名称</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlyviewtype.vparname" id="vparname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="vparnamemenuBtn" href="#" onclick="vparnameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="vparnamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treevparname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
					<td class="form-label"><label>上级编号</label></td>
					<td class="form-content"><s:textfield name="qlyviewtype.vparcode" id="vparcode" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>分类名称</label></td>
					<td class="form-content"><s:textfield name="qlyviewtype.vtypename" id="vtypename" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>分类编号</label></td>
					<td class="form-content"><s:textfield name="qlyviewtype.vtypecode" id="vtypecode" cssClass="txt"></s:textfield></td>
				</tr>
			</table>
		    <p class="form-opt">
	       		<input type="submit" id="tj" class="btn" value="保 存" />&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="reset" id="cz" class="btn" value="重 置" />
	       </p>
		</s:form>
	</div>
	</div>
	
  </body>
</html>
