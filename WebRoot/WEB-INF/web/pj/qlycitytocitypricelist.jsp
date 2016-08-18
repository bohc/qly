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
		<script type="text/javascript" src="source/js/custom/qlycitytocitypricelist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
								出发城市名:<s:textfield name="extqlycitytocityprice.fromcityname"/>
								到达城市名:<s:textfield name="extqlycitytocityprice.tocityname"/>
								<s:submit value="查询" cssClass="btn"></s:submit>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_add" href="qlycitytocityprice!edit.do" target="_self"><img title="添加" src="source/img/icon32_32/1096738.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_del_batch" href="#" target="_self"><img title="删除" src="source/img/icon32_32/1096778.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" ></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">交通工具类型</th>
							<th bgcolor="#CDE1F9">所需费用</th>
							<th bgcolor="#CDE1F9">出发城市名</th>
							<th bgcolor="#CDE1F9">到达城市名</th>
							<th bgcolor="#CDE1F9">添加时间</th>
							<th bgcolor="#CDE1F9">出发城市简码</th>
							<th bgcolor="#CDE1F9">到达城市简码</th>
							<th bgcolor="#CDE1F9">出发城市拼音</th>
							<th bgcolor="#CDE1F9">到达城市拼音</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" value="${did.id}" tid="${did.id}"></td>
							<td width="20px;">
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2303">
									<a href="qlycitytocityprice!del.do?qlycitytocityprice.id=${did.id}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td><s:if test="traficaltype=='飞机'">飞机</s:if><s:elseif test="traficaltype=='汽车'">汽车</s:elseif><s:elseif test="traficaltype=='巴士'">巴士</s:elseif><s:elseif test="traficaltype=='轮船'">轮船</s:elseif></td>
							<td style="width:100px;">
								<input name="price" id="price<s:property value="#stat.index"/>" style="width:80px" value="${did.price }" type="text"/>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="javascript:changePrice('${did.id}','price<s:property value="#stat.index"/>');" class="text"><img title="编辑" src="source/img/icon16_16/1061074.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.fromcityname}</td>
							<td>${did.tocityname}</td>
							<td><s:date name="entime" format="yyyy-MM-dd"/></td>
							<td>${did.fromcityjm}</td>
							<td>${did.tocityjm}</td>
							<td>${did.fromcitypy}</td>
							<td>${did.tocitypy}</td>
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
