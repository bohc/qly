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
		<script type="text/javascript" src="source/js/custom/qlylinetravellist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<s:hidden name="extqlylinetravel.ltid" id="ltid"></s:hidden>
				<s:hidden name="extqlylinetravel.lineid" id="lineid"></s:hidden>
				<s:hidden name="extqlylinetravel.linename" id="linename"></s:hidden>
				<div class="grid">
					<div class="grid-search">
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a href="javascript:addinfo();" target="_self"><img title="添加" src="source/img/icon32_32/1096738.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_del_batch" href="#" target="_self"><img title="删除" src="source/img/icon32_32/1096778.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
					</div>
					
					<table class="grid-table">
					<colgroup>
					<col>
					<col>
					<col>
					<col>
					<col style="width: 200px; white-space:nowrap; table-layout:fixed; overflow: hidden; word-break:keep-all;">
					</colgroup>
					<thead>
						<tr>
							<th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" ></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">行程标题</th>
							<th bgcolor="#CDE1F9">第几天</th>
							<th bgcolor="#CDE1F9">线路名称</th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" value="${did.id}" tid="${did.id}"></td>
							<td width="40px;">
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlylinetravel!edit.do?qlylinetravel.id=${did.id}" class="text"><img title="编辑" src="source/img/frame/pencil.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2303">
									<a href="qlylinetravel!del.do?qlylinetravel.id=${did.id}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.traveltitle}</td>
							<td>${did.daynum}</td>
							<td title="${did.linename }"><ft:subStringFix str="${did.linename}" start="0" end="15"/></td>
						</tr>
						</s:iterator>
						</tbody>
					</table>

					 <div class="grid-page"><%@ include file="/inc/adminpage.jsp" %></div>
				</div>
			</s:form>
		 </div>
	</div>
	<s:form id="addform" action="qlylinetravel!edit.do" method="post">
		<s:hidden name="extqlylinetravel.ltid" id="ltid"></s:hidden>
		<s:hidden name="extqlylinetravel.lineid" id="lineid"></s:hidden>
		<s:hidden name="extqlylinetravel.linename" id="linename"></s:hidden>
	</s:form>
 </body>
</html>
