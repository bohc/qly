<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	  	<title>线路行程安排</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/custom/vehicleedit.js"></script>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<style type="text/css">
			ul.list li{list-style-type: none}
			.grid-table tr td {
			    text-align: left;
			}
		</style>
		<script type="text/javascript">
			var v;
			var citynamezNodes =[
			<s:iterator value="citynamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:if>
				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
			</s:iterator>];
		</script>
</head>
 <body>
<div id="add_viehcle">
	<table class="grid-table">
		<s:hidden name="fromcityjm" id="fromcityjm"/>
	<s:hidden name="tocityjm" id="tocityjm"/>
	<tr>
		<td class="form-label"><label>交通工具类型</label></td>
		<td class="form-content err"><s:radio id="vehicle" name="vehicle" list="#{'飞机':'飞机','汽车':'汽车','火车':'火车','轮船':'轮船'}" listKey="key" listValue="value" value="'汽车'"  theme="simple"></s:radio></td>
		<td class="form-label"><label>座位级别</label></td>
		<td class="form-content" id="stype"><s:radio id="seattype" name="seattype" list="#{'软座':'软座'}" listKey="key" listValue="value" value="'软座'"  theme="simple"></s:radio></td>
	</tr>
	<tr>
		<td class="form-label"><label>成人价</label></td>
		<td class="form-content err"><s:textfield name="price" id="price" cssClass="txt" value="0"></s:textfield></td>
		<td class="form-label"><label>儿童价</label></td>
		<td class="form-content"><s:textfield name="childrenprice" id="childrenprice" cssClass="txt" value="0"></s:textfield></td>
	</tr>
	<tr>
		<td class="form-label"><label>出发地</label></td>
		<td class="form-content">
			<div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<ul class="list">
						<li class="title"><s:textfield name="fromcityname" id="fromcityname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield></li>
					</ul>
				</div>
			</div>
			<div id="fromcitynamemenuContent" class="menuContent">
				<ul id="treefromcityname" class="ztree" style="margin-top:0; width:200px;"></ul>
			</div>
		</td>
		<td class="form-label"><label>目的地</label></td>
		<td class="form-content">
			<div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<ul class="list">
						<li class="title"><s:textfield name="tocityname" id="tocityname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield></li>
					</ul>
				</div>
			</div>
			<div id="tocitynamemenuContent" class="menuContent">
				<ul id="treetocityname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
				</tr>
			</table>
	</div>
</body>
</html>
