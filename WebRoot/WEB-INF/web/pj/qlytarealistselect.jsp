<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>标准</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="source/css/global.css" type="text/css">
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			initGrid();
		});
	</script>
		<script type="text/javascript" src="source/js/custom/qlytarealist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
								拼音简码:<s:textfield name="extqlytarea.areapy"/>
								上级地区编号:<s:textfield name="extqlytarea.pid"/>
								<s:submit value="查询" cssClass="btn"></s:submit>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="hidden" ></th>
							<th bgcolor="#CDE1F9">编号</th>
							<th bgcolor="#CDE1F9">数据引用编号</th>
							<th bgcolor="#CDE1F9">地名</th>
							<th bgcolor="#CDE1F9">上级地区名称</th>
							<th bgcolor="#CDE1F9">拼音简码</th>
							<th bgcolor="#CDE1F9">上级地区编号</th>
							<th bgcolor="#CDE1F9">简称</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="radio" tid="${did.acode }" tname="${did.area }" tpy="${did.areapy }" ircode="${did.ircode }"></td>
							<td>${did.acode}</td>
							<td>${did.ircode}</td>
							<td>${did.area}</td>
							<td>${did.pname}</td>
							<td>${did.areapy}</td>
							<td>${did.pid}</td>
							<td>${did.remark}</td>
						</tr>
						</s:iterator>
					</table>

					 <div class="grid-page"><%@ include file="/inc/adminpage.jsp" %></div>
				</div>
			</s:form>
		 </div>
	</div>
	<s:form id="sendform"></s:form>
 </body>
</html>
