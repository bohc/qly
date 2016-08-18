<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>自定义交通工具价格</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="source/css/global.css" type="text/css"/>
	<link id="skin" rel="stylesheet" href="source/js/jbox2.3/Default/jbox.css" ></link>
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-2.3.min.js"></script>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-zh-CN.js"></script>  
	<script type="text/javascript" src="source/js/custom/qlycustomviehclepricelist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
								出发城市:<s:textfield name="extqlycustomviehcleprice.fromcityjm"/>
								目的城市:<s:textfield name="extqlycustomviehcleprice.tocityjm"/>
								<s:submit value="查询" cssClass="btn"></s:submit>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_add" href="#" onclick="a_add()"><img title="添加" src="source/img/icon32_32/1096738.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
						<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
							<a id="a_del_batch" href="#" target="_self"><img title="删除" src="source/img/icon32_32/1096778.png" width="32" height="32"  align="absmiddle" border="0"/></a>
						</ft:exitsfun>
					</div>
					<table id="grid-table" class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" /></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">交通工具类型</th>
							<th bgcolor="#CDE1F9">成人价</th>
							<th bgcolor="#CDE1F9">儿童价</th>
							<th bgcolor="#CDE1F9">座位</th>
							<th bgcolor="#CDE1F9">出发城市名</th>
							<th bgcolor="#CDE1F9">出发城市简码</th>
							<th bgcolor="#CDE1F9">出发城市拼音</th>
							<th bgcolor="#CDE1F9">目的城市名</th>
							<th bgcolor="#CDE1F9">添加时间</th>
							<th bgcolor="#CDE1F9">添加人</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" value="${did.id}" tid="${did.id}"/></td>
							<td width="26px;">
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
									<a href="qlycustomviehcleprice!del.do?qlycustomviehcleprice.id=${did.id}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="middle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.traficaltype}</td>
							<td width="110px">
								<input name="price" id="price<s:property value="#stat.index"/>" style="width:80px" value="${did.price }" type="text"/>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
									<a href="javascript:changePrice('${did.id}','price<s:property value="#stat.index"/>');" class="text"><img title="编辑" src="source/img/icon16_16/1061074.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td width="110px">
								<input name="childrenprice" id="childrenprice<s:property value="#stat.index"/>" style="width:80px" value="${did.childrenprice }" type="text"/>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
									<a href="javascript:changeChildrenPrice('${did.id}','childrenprice<s:property value="#stat.index"/>');" class="text"><img title="编辑" src="source/img/icon16_16/1061074.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.seattype}</td>
							<td>${did.fromcityname}</td>
							<td>${did.fromcityjm}</td>
							<td>${did.fromcitypy}</td>
							<td>${did.tocityname}</td>
							<td><s:date name="entime" format="yyyy-MM-dd"/></td>
							<td>${did.enman}</td>
						</tr>
						</s:iterator>
					</table>

					 <div class="grid-page"><%@ include file="/inc/adminpage.jsp" %></div>
				</div>
			</s:form>
		 </div>
	</div>
	<s:form id="sendform"></s:form>
	<table style="display:none">
		<tr id="m-tr" >
			<td width="6px"><input name="ck" type="checkbox"/></td>
			<td width="26px;">
					<a href="qlycustomviehcleprice!del.do?qlycustomviehcleprice.id=" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="middle" border="0"/></a>
			</td>
			<td></td>
			<td>
				<input name="price" id="price" style="width:80px" value="" type="text"/>
				<a href="javascript:changePrice('3','price');" class="text">
				<img title="编辑" src="source/img/icon16_16/1061074.png" width="16" height="16" align="absmiddle" border="0"/></a>
			</td>
			<td>
				<input name="childrenprice" id="childrenprice" style="width:80px" value="" type="text"/>
				<a href="javascript:changechildrenPrice('3','childrenprice');" class="text">
				<img title="编辑" src="source/img/icon16_16/1061074.png" width="16" height="16" align="absmiddle" border="0"/></a>
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
 </body>
</html>
