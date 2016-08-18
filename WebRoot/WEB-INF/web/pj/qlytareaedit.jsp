<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>地区表</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlytareaedit.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:100%;} .form-label{width:100px;}
	  		.form table{width:100%;} .form-header{width:100%;}
	  		li{list-style-type: none;}
	  	</style>
	    
		<script type="text/javascript">
			var pnamezNodes =[
			<s:iterator value="pnamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}",open:true}</s:if>
				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:else>
			</s:iterator>];
		</script>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">地区表</label></div>
		<s:form name="eidtForm" action="qlytarea!save.do" method="post" theme="simple" >
				
			<table>
				<s:hidden name="qlytarea.aid" id="aid"/>
				<s:hidden name="qlytarea.areapy" id="areapy"/>
				<tr>
					<td class="form-label"><label>编号</label></td>
					<td class="form-content"><s:textfield name="qlytarea.acode" id="acode" cssClass="txt" maxLength="50"></s:textfield></td>
					<td class="form-label"><label>数据引用编号</label></td>
					<td class="form-content"><s:textfield name="qlytarea.ircode" id="ircode" cssClass="txt" maxLength="20"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>地名</label></td>
					<td class="form-content"><s:textfield name="qlytarea.area" id="area" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>上级地区名称</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlytarea.pname" id="pname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="pnamemenuBtn" href="#" onclick="pnameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="pnamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treepname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>上级地区编号</label></td>
					<td class="form-content"><s:textfield name="qlytarea.pid" id="pid" cssClass="txt" maxLength="50"></s:textfield></td>
					<td class="form-label"><label>简称</label></td>
					<td class="form-content"><s:textfield name="qlytarea.remark" id="remark" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>机场地编码</label></td>
					<td class="form-content"><s:textfield name="qlytarea.airportcode" id="airportcode" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>机场地名称</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlytarea.airportname" id="airportname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="aireportnamemenuBtn" href="#" onclick="airportnameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="airportnamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treeairportname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
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
