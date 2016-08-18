<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>各城市到达的价格</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlycitytocitypriceedit.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:100%;} .form-label{width:100px;}
	  		.form table{width:100%;} .form-header{width:100%;}
	  	</style>
	    
		<script type="text/javascript">
			var v;
			var fromcitynamezNodes =[
			<s:iterator value="fromcitynamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:if>
				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
			</s:iterator>];
			var tocitynamezNodes =[
			<s:iterator value="tocitynamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:if>
				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
			</s:iterator>];
		</script>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">各城市到达的价格</label></div>
		<s:form name="eidtForm" action="qlycitytocityprice!saveprocedu.do" method="post" theme="simple" >
				
			<table>
				<s:hidden name="qlycitytocityprice.id" id="id"/>
				<s:hidden name="qlycitytocityprice.entime" id="entime"/>
				<s:hidden name="qlycitytocityprice.enman" id="enman"/>
				<s:hidden name="qlycitytocityprice.fromcityjm" id="fromcityjm"/>
				<s:hidden name="qlycitytocityprice.tocityjm" id="tocityjm"/>
				<s:hidden name="qlycitytocityprice.fromcitypy" id="fromcitypy"/>
				<s:hidden name="qlycitytocityprice.tocitypy" id="tocitypy"/>
				<tr>
					<td class="form-label"><label>交通工具类型</label></td>
					<td class="form-content"><s:radio id="traficaltype" cssClass="validate[required]" name="qlycitytocityprice.traficaltype" list="#{'飞机':'飞机','汽车':'汽车','火车':'火车','轮船':'轮船'}" listKey="key" listValue="value" theme="simple"></s:radio></td>
					<td class="form-label"><label>所需费用</label></td>
					<td class="form-content"><s:textfield name="qlycitytocityprice.price" id="price" cssClass="validate[required,custom[number]]"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>出发城市名</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlycitytocityprice.fromcityname" id="fromcityname" cssClass="txt" maxLength="50" readonly="" style="width:140px;display:none"></s:textfield></li>
								</ul>
							</div>
						</div>
						<div id="fromcitynamemenuContent" class="menuContent">
							<ul id="treefromcityname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
					<td class="form-label"><label>到达城市名</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlycitytocityprice.tocityname" id="tocityname" cssClass="validate[required]" maxLength="50" readonly="" style="width:140px;"></s:textfield></li>
								</ul>
							</div>
						</div>
						<div id="tocitynamemenuContent" class="menuContent" >
							<ul id="treetocityname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
				</tr>
			</table>
		    <p class="form-opt">
	       		<input type="submit" id="tj" class="btn" value="保 存" />&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="reset" id="cz" class="btn" value="重 置" />
	       		<s:hidden id="rs" />
	       </p>
		</s:form>
	</div>
	</div>
	
  </body>
</html>
