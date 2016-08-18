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
		<script type="text/javascript" src="source/js/custom/qlytravellist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
								行程分类名称:<s:textfield name="extqlytravel.vtypename"/>
								行程标题:<s:textfield name="extqlytravel.traveltitle"/>
								<s:submit value="查询" cssClass="btn"></s:submit>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_add" href="qlytravel!edit.do" target="_self"><img title="添加" src="source/img/icon32_32/1096738.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_del_batch" href="#" target="_self"><img title="删除" src="source/img/icon32_32/1096778.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" ></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">行程编码</th>
							<th bgcolor="#CDE1F9">行程分类名称</th>
							<th bgcolor="#CDE1F9">行程标题</th>
							<th bgcolor="#CDE1F9">早餐</th>
							<th bgcolor="#CDE1F9">中餐</th>
							<th bgcolor="#CDE1F9">晚餐</th>
							<th bgcolor="#CDE1F9">自费</th>
							<th bgcolor="#CDE1F9">购物</th>
							<th bgcolor="#CDE1F9">是否启用</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" value="${did.ltid}" tid="${did.ltid}"></td>
							<td width="60px;">
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlytravel!copy.do?qlytravel.ltid=${did.ltid}&currentpage=${currentpage}" class="text"><img title="拷贝" src="source/img/icon16_16/1060900.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlytravel!edit.do?qlytravel.ltid=${did.ltid}&currentpage=${currentpage}" class="text"><img title="编辑" src="source/img/frame/pencil.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2303">
									<a href="qlytravel!del.do?qlytravel.ltid=${did.ltid}&currentpage=${currentpage}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.travelcode}</td>
							<td>${did.vtypename}</td>
							<td>${did.traveltitle}</td>
							<td><s:if test="breakfast=='自行安排'">自行安排</s:if><s:elseif test="breakfast=='提供'">提供</s:elseif></td>
							<td><s:if test="lunch=='自行安排'">自行安排</s:if><s:elseif test="lunch=='提供'">提供</s:elseif></td>
							<td><s:if test="supper=='自行安排'">自行安排</s:if><s:elseif test="supper=='提供'">提供</s:elseif></td>
							<td>${did.selfexpense}</td>
							<td>${did.shop}</td>
							<td><s:if test="isuse==0">启用</s:if><s:elseif test="isuse==1">锁定 </s:elseif></td>
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
