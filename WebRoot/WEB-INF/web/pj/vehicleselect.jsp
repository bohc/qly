<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	  	<title>自定义交通工具选择</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="source/js/custom/vehicleselect.js"></script>
</head>
 <body>
<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
								出发城市简码/拼音/文字:<s:textfield name="extqlycustomviehcleprice.fromcityjm"/>
								目的城市简码/拼音/文字:<s:textfield name="extqlycustomviehcleprice.tocityjm"/>
								<s:submit value="查询" cssClass="btn"></s:submit>
					</div>
					
					<table id="grid-table" class="grid-table">
						<tr>
							<th bgcolor="#CDE1F9" width="6px"><input id="toggleAll" type="checkbox" /></th>
							<th bgcolor="#CDE1F9" width="40px">交通工具类型</th>
							<th bgcolor="#CDE1F9">目的地简码</th>
							<th bgcolor="#CDE1F9">出发城市名</th>
							<th bgcolor="#CDE1F9">目的城市名</th>
							<th bgcolor="#CDE1F9" width="52px">成人价</th>
							<th bgcolor="#CDE1F9" width="52px">儿童价</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr>
							<td width="6px"><input name="ck" type="checkbox" value="${did.id}"/></td>
							<td>${did.traficaltype}</td>
							<td>${did.seattype}</td>
							<td>${did.fromcityname}</td>
							<td>${did.tocityname}</td>
							<td>${did.price}</td>
							<td>${did.childrenprice}</td>
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
