<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>系统功能列表</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlytbfunctionlistedit.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:100%;} .form-label{width:100px;}
	  		.form table{width:100%;} .form-header{width:100%;}
	  	</style>
	    
		<script type="text/javascript">
			var vsnamezNodes =[
			<s:iterator value="vsnamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.icode}", pId:"${did.iscode}", name:"${did.vname}", open:true}</s:if>
				<s:else>,{ id:"${did.icode}", pId:"${did.iscode}", name:"${did.vname}", open:true}</s:else>
			</s:iterator>];
		</script>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">系统功能列表</label></div>
		<s:form name="eidtForm" action="qlytbfunctionlist!save.do" method="post" theme="simple" >
				
			<table>
				<s:hidden name="qlytbfunctionlist.nid" id="nid"/>
				<s:hidden name="qlytbfunctionlist.iscode" id="iscode"/>
				<tr>
					<td class="form-label"><label>功能编号</label></td>
					<td class="form-content"><s:textfield name="qlytbfunctionlist.icode" id="icode" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>上级编号名称</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlytbfunctionlist.vsname" id="vsname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="vsnamemenuBtn" href="#" onclick="vsnameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="vsnamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treevsname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>级次</label></td>
					<td class="form-content"><s:textfield name="qlytbfunctionlist.ilevel" id="ilevel" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>下级子数</label></td>
					<td class="form-content"><s:textfield name="qlytbfunctionlist.isubnum" id="isubnum" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>功能名称</label></td>
					<td class="form-content"><s:textfield name="qlytbfunctionlist.vname" id="vname" cssClass="txt" maxLength="64"></s:textfield></td>
					<td class="form-label"><label>权限类型</label></td>
					<td class="form-content"><s:radio id="ifunctype" cssClass="" name="qlytbfunctionlist.ifunctype" list="#{'100':'系统功能','0':'其它'}" listKey="key" listValue="value" theme="simple"></s:radio></td>
				</tr>
				<tr>
					<td class="form-label"><label>路径</label></td>
					<td class="form-content"><s:textfield name="qlytbfunctionlist.vurl" id="vurl" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label></label></td>
					<td class="form-content"></td>
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
