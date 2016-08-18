<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>路线选择</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="source/css/global.css" type="text/css">
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="source/js/custom/qlylinelist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
						线路名称:<s:textfield name="extqlyline.linename"/>
						目的地类型:<s:textfield name="extqlyline.destinationtype"/>
						集合地名称:<s:textfield name="extqlyline.placename"/>
						<s:submit value="查询" cssClass="btn"></s:submit>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="hidden" ></th>
							<th bgcolor="#CDE1F9">路线价格</th>
							<th bgcolor="#CDE1F9">线路名称</th>
							<th bgcolor="#CDE1F9">参团性质</th>
							<th bgcolor="#CDE1F9">目的地类型</th>
							<th bgcolor="#CDE1F9">集合地名称</th>
							<th bgcolor="#CDE1F9">经路性质</th>
							<th bgcolor="#CDE1F9">状态</th>
							<th bgcolor="#CDE1F9">添加时间</th>
							<th bgcolor="#CDE1F9">自助设计</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="radio" value="${did.id}" tid="${did.id}"></td>
							<td>${did.lineprice}</td>
							<td>${did.linename}</td>
							<td><s:if test="joinattribute=='参团游'">参团游</s:if><s:elseif test="joinattribute=='自由行'">自由行</s:elseif><s:elseif test="joinattribute=='团队游'">团队游</s:elseif><s:elseif test="joinattribute=='自驾游'">自驾游</s:elseif></td>
							<td><s:if test="destinationtype=='国内游'">国内游</s:if><s:elseif test="destinationtype=='周边游'">周边游</s:elseif><s:elseif test="destinationtype=='境外游'">境外游</s:elseif><s:elseif test="destinationtype=='港奥游'">港奥游</s:elseif><s:elseif test="destinationtype=='云南游'">云南游</s:elseif></td>
							<td>${did.placename}</td>
							<td>${did.attribute}</td>
							<td><s:if test="state==0">未审核</s:if><s:elseif test="state==1">已审核</s:elseif></td>
							<td><s:date name="credate" format="yyyy-MM-dd"/></td>
							<td><s:if test="isown==0">不支持</s:if><s:elseif test="isown==1">支持</s:elseif></td>
						</tr>
						</s:iterator>
					</table>
					 <div class="grid-page"><%@ include file="/inc/adminpage.jsp" %></div>
				</div>
			</s:form>
		 </div>
	</div>
 </body>
</html>
