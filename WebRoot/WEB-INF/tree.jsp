<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>演示按钮</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="source/css/global.css" type="text/css">
	<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$.fn.zTree.init($("#menu ul.ztree"), setting, zNodes);
		});
	  
	  //zTree设置
		var setting = {
			check: {
				enable: false
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		//zTree数据
		var zNodes =[
			<s:iterator value="ilist" status="stat" id="did">
			<s:if test="#stat.index==0">
				{ id:"${did.icode}", pId:"${did.iscode}", name:"${did.vname}", url:"${did.vurl}",target:"MainIframe", open:true}
			</s:if>
			<s:else>
				,{ id:"${did.icode}", pId:"${did.iscode}", name:"${did.vname}", url:"${did.vurl}",target:"MainIframe", open:true}
			</s:else>
			</s:iterator>
		];
	</script>
	<style type="text/css">
		body{
			background-color:#E8EDF3;
		}
	</style>
</head>
 <body>
 	<div class="wraper">
		<div id="menu" class="menu">
			<ul class="ztree"></ul>
		</div>
		<div style="clear:both;"></div>
	</div>
 </body>
</html>