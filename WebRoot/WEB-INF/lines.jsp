<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" >
  <head>
    <title>index.html</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
	<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
	<link id="skin" rel="stylesheet" href="source/js/jbox2.3/Default/jbox.css" ></link>
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-2.3.min.js"></script>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-zh-CN.js"></script>
	<script type="text/javascript" src="source/js/custom/lines.js"></script>
	<script type="text/javascript">
		var cityNodes =[
   			<s:iterator value="fromcityjmlist" status="stat" id="did">
   				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:if>
   				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
   			</s:iterator>];
	</script>
	<style type="text/css">
		#div1, #div3{float:left; width: 460px;}
		#div2{ float:left; width: 164px; height: 200px; margin-right: 10px;}
		#div1 div, #div3 div{ float:left; width: 44%; margin: 0px 10px; }
		ul.ztree{width:200px; height:170px; margin: 10px 10px;}
		#div2 ul{overflow:hidden; text-align: center; width: 160px;}
		#ndiv1 ul{ margin:10px 10px 10px 20px; width:1055px;height: 300px}
	</style>
  </head>
  <body>
  <div id="top_div">
  		<div>
  			<div id="div1">
				<div id="fromcityjmmenuTree">
					<ul id="treefromcityjm" class="ztree"></ul>
				</div>
				<div id="fromcityjmmenuContent">
					<ul id="fromcityjm" class="ztree">
						<textarea id="fromcity" name="tocity" style="width:100%;height: 100%"></textarea>
					</ul>
				</div>
  			</div>
  			<div id="div2">
  				<div>
	  				<ul class="ztree">
	  					<input id="linecode" name="linecode" style="type:90%">
	  					<br/>
	  					<br/>
						<input id="genarate_btn" name="genarate_btn" type="button" value="生成"/>
					</ul>
				</div>
  			</div>
  			<div id="div3">
  				<div id="tocityjmmenuTree">
					<ul id="treetocityjm" class="ztree"></ul>
				</div>
				<div id="tocityjmmenuContent">
					<ul id="tocityjm" class="ztree">
						<textarea id="tocity" name="tocity" style="width:100%;height: 100%"></textarea>
					</ul>
				</div>
  			</div>
		</div>
 		<div>
 			<div id="ndiv1">
	 			<ul class="ztree">
	 				<textarea id="linelist" name="linelist" style="width:100%;height: 100%"></textarea>
	 			</ul>
 			</div>
 		</div>
  </div>
  </body>
</html>
