<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>自定义交通工具价格</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlycustomviehclepriceedit.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:1000px;}
	  		.form table{width:1000px;} 
	  		.form-header{width:1000px;}
	  		.form-label{width:120px;}
	  		.form-content{width:360px;}
	  		.txt,.pwd,.textarea{width:340px;} 
	  		.select{width:344px;}
	  		.textarea{height:60px;}
	  		.colspan3{width:840px;}
	  	</style>
	    
		<script type="text/javascript">
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
		<div class="form-header"><label class="header-content">自定义交通工具价格</label></div>
		<s:form name="eidtForm" id="eidtForm" action="qlycustomviehcleprice!save.do" method="post" theme="simple" >
				
			<table>
				<s:hidden name="qlycustomviehcleprice.id" id="id"/>
				<s:hidden name="qlycustomviehcleprice.fromcityjm" id="fromcityjm"/>
				<s:hidden name="qlycustomviehcleprice.fromcitypy" id="fromcitypy"/>
				<s:hidden name="qlycustomviehcleprice.tocityjm" id="tocityjm"/>
				<s:hidden name="qlycustomviehcleprice.tocitypy" id="tocitypy"/>
				<s:hidden name="qlycustomviehcleprice.entime" id="entime"/>
				<s:hidden name="qlycustomviehcleprice.enman" id="enman"/>
				<tr>
					<td class="form-label"><label>交通工具类型</label></td>
					<td class="form-content"><s:select id="traficaltype" cssClass="select" name="qlycustomviehcleprice.traficaltype" list="#{'飞机':'飞机','火车':'火车','汽车':'汽车'}"></s:select></td>
					<td class="form-label"><label>所需费用</label></td>
					<td class="form-content"><s:textfield name="qlycustomviehcleprice.price" id="price" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>出发城市名</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlycustomviehcleprice.fromcityname" id="fromcityname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="fromcitynamemenuBtn" href="#" onclick="fromcitynameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="fromcitynamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treefromcityname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
					<td class="form-label"><label>目的城市名</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlycustomviehcleprice.tocityname" id="tocityname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="tocitynamemenuBtn" href="#" onclick="tocitynameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="tocitynamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treetocityname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
				</tr>
			</table>
		    <p class="form-opt">
	       		<input type="submit" id="tj" class="btn" value="保 存" />&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="reset" id="cz" class="btn" value="重 置" />
	       </p>
		</s:form>
	</div>
	</div>
	
  </body>
</html>
