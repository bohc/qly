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
		<script type="text/javascript" src="source/js/custom/qlyuserinfolist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
						
								登录名:<s:textfield name="extqlyuserinfo.username"/>
								<s:submit value="查询" cssClass="btn"></s:submit>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a href="qlyuserinfo!edit.do" target="_self"><img title="添加" src="source/img/add.png" width="16" height="16"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" ></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">登录名</th>
							<th bgcolor="#CDE1F9">密码</th>
							<th bgcolor="#CDE1F9">用户类型</th>
							<th bgcolor="#CDE1F9">别名</th>
							<th bgcolor="#CDE1F9">所属企业</th>
							<th bgcolor="#CDE1F9">企业名</th>
							<th bgcolor="#CDE1F9">地区编号</th>
							<th bgcolor="#CDE1F9">所在地区</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" ></td>
							<td width="40px;">
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlyuserinfo!edit.do?qlyuserinfo.userid=${did.userid}" class="text"><img title="编辑" src="source/img/frame/pencil.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2303">
									<a href="qlyuserinfo!del.do?qlyuserinfo.userid=${did.userid}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.username}</td>
							<td>${did.pwd}</td>
							<td>${did.type}</td>
							<td>${did.realname}</td>
							<td>${did.compid}</td>
							<td>${did.cname}</td>
							<td>${did.acode}</td>
							<td>${did.cityname}</td>
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
