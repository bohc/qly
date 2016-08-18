<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>调整价格条件</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="source/css/global.css" type="text/css"/>
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="source/js/custom/qlyregulatepriceconditionlist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
						
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_add" href="qlyregulatepricecondition!edit.do?qlyregulatepricecondition.lineid=${extqlyregulatepricecondition.lineid}" target="_self"><img title="添加" src="source/img/icon32_32/1096738.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_del_batch" href="#" target="_self"><img title="删除" src="source/img/icon32_32/1096778.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" ></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">调整类别</th>
							<th bgcolor="#CDE1F9">调整价格</th>
							<th bgcolor="#CDE1F9">开始时间</th>
							<th bgcolor="#CDE1F9">结束时间</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" value="${did.id}" tid="${did.id}"/></td>
							<td width="60px;">
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlyregulatepricecondition!copy.do?qlyregulatepricecondition.id=${did.id}" class="text"><img title="拷贝" src="source/img/icon16_16/1060900.png" width="16" height="16"  align="middle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlyregulatepricecondition!edit.do?qlyregulatepricecondition.id=${did.id}" class="text"><img title="编辑" src="source/img/frame/pencil.png" width="16" height="16"  align="middle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2303">
									<a href="qlyregulatepricecondition!del.do?qlyregulatepricecondition.id=${did.id}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="middle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.difftype}</td>
							<td>${did.price}</td>
							<td><s:date name="startdate" format="yyyy-MM-dd"/></td>
							<td><s:date name="enddate" format="yyyy-MM-dd"/></td>
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
