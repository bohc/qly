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
		<script type="text/javascript" src="source/js/custom/qlyviewlist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
								景点名称:<s:textfield name="extqlyview.viewname"/>
								分类名称:<s:textfield name="extqlyview.typename"/>
								城市名称:<s:textfield name="extqlyview.cityname"/>
								<s:submit value="查询" cssClass="btn"></s:submit>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_add" href="qlyview!edit.do" target="_self"><img title="添加" src="source/img/icon32_32/1096738.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_del_batch" href="#" target="_self"><img title="删除" src="source/img/icon32_32/1096778.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" ></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">景点名称</th>
							<th bgcolor="#CDE1F9">分类名称</th>
							<th bgcolor="#CDE1F9">城市名称</th>
							<th bgcolor="#CDE1F9">景区级别</th>
							<th bgcolor="#CDE1F9">景区类型</th>
							<th bgcolor="#CDE1F9">适宜人群</th>
							<th bgcolor="#CDE1F9">状态</th>
							<th bgcolor="#CDE1F9">是否推荐</th>
							<th bgcolor="#CDE1F9">国内/国外</th>
							<th bgcolor="#CDE1F9">是否热点</th>
							<th bgcolor="#CDE1F9">首页推荐</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" value="${did.id}" tid="${did.id}"></td>
							<td width="40px;">
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlyview!edit.do?qlyview.id=${did.id}&currentpage=${currentpage}" class="text"><img title="编辑" src="source/img/frame/pencil.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2303">
									<a href="qlyview!del.do?qlyview.id=${did.id}&currentpage=${currentpage}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.viewname}</td>
							<td>${did.typename}</td>
							<td>${did.cityname}</td>
							<td><s:if test="level=='一星'">一星</s:if><s:elseif test="level=='二星'">二星</s:elseif><s:elseif test="level=='三星'">三星</s:elseif><s:elseif test="level=='四星'">四星</s:elseif><s:elseif test="level=='五星 '">五星 </s:elseif></td>
							<td>${did.viewtype}</td>
							<td>${did.suitable}</td>
							<td><s:if test="state==0">显示</s:if><s:elseif test="state==1">隐藏</s:elseif></td>
							<td><s:if test="isrecommend==0">一般</s:if><s:elseif test="isrecommend==1">推荐</s:elseif></td>
							<td><s:if test="inorout==0">国内</s:if><s:elseif test="inorout==1">国外</s:elseif></td>
							<td><s:if test="ishot==0">一般</s:if><s:elseif test="ishot==1">热点</s:elseif></td>
							<td><s:if test="istop==0">一般</s:if><s:elseif test="istop==1">首页</s:elseif></td>
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
