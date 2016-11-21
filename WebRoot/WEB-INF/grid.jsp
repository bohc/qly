<%@ page import="com.util.Config"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>欢迎您使用云南省食品药品监督管理局证照管理系统</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="source/css/global.css" type="text/css" />
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<link rel="stylesheet" href="source/js/jquery1.8.2/themes/base/jquery.ui.all.css" type="text/css"></link>
	<link rel="stylesheet" href="source/js/jquery1.8.2/themes/base/jquery.ui.tabs.css" type="text/css"></link>
	<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.tabs.js"></script>
		<link id="skin" rel="stylesheet" href="source/js/jbox2.3/Default/jbox.css" ></link>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-2.3.min.js"></script>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-zh-CN.js"></script> 
	<script type="text/javascript" src="source/js/custom/welcome.js"></script>
	<style type="text/css">
		.items { float:left; margin:10px 0 0 0; display:block; border:1px solid #D7DDE8;}
		.header { height:35px; line-height:35px; padding-left:20px; text-align: left; color:#FFF; font-weight:bold; font-size:16px; }
		.itemView { text-align:left; padding:5px; }
		.grid-table th, tr, td { height:26px; border: 1px #999999 solid; padding: 2px; }
		.ui-widget-header { border:0px; border-bottom: 1px solid #aaaaaa; background: #F4F8Fb; color: #222222; }
		.ui-widget-content { border: 1px solid #aaaaaa; background: #F4F8Fb; color: #222222; }
		.ui-state-active a span { color:#468dcb; }
		.bold { font-weight:bold; }
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			$(window).resize(function () { resizePage(); });
			resizePage();
			initGrid();
			$("#tabs").tabs();
			showTime();
		});
		
		function showTime(){
		 	var date = new Date();
			var weekday=new Array(7)
			weekday[0]="星期日"
			weekday[1]="星期一"
			weekday[2]="星期二"
			weekday[3]="星期三"
			weekday[4]="星期四"
			weekday[5]="星期五"
			weekday[6]="星期六" 
			var y = date.getFullYear() + "年";
			var m = (date.getMonth() + 1) + "月";
			var d = date.getDate() + "日";
			var w = weekday[date.getDay()];
			var h = date.getHours() + "时";
			var m1 = date.getMinutes() +"分";
			$("#showTime").text(y + m + d + " " + h + m1 + " " + w);
			setTimeout(showTime , 60000);
		}
		//页面加载时，对Table表格移动鼠标行变色操作，通用方法
		function initGrid() {
	    	$('table.grid-table').each(function () {
				var _this = $(this);
				//设置偶数行和奇数行颜色
				_this.find("tr:odd").css("background-color", "#DEE9F7");
				_this.find("tr:even").css("background-color", "#FFFFFF");
				//鼠标移动隔行变色hover用法关键
				_this.find("tr:not(:first)").hover(function () {
					$(this).attr("bColor", $(this).css("background-color")).css("background-color", "#FFF4CE").css("cursor", "pointer");
					$(this).children("td:first").addClass("grid-arrow");
				}, function () {
					$(this).css("background-color", $(this).attr("bColor"));
					$(this).children("td:first").removeClass("grid-arrow");
				});
			});
		}
		
		function resizePage(){
			var brow=$.browser;
			var dealWidth = 20;
			var dealHeight = 73;
			if(brow.msie && brow.version > 8){
				dealHeight = 75;
			}
			var pageWidth = $(window).width() - dealWidth;
			var pageHeight = $(window).height() - dealHeight;
			$(".items").css("width",(pageWidth)/2-20);
			$(".items").css("height",(pageHeight)/2);
		}
	</script>
</head>
 <body>
 	<div class="wraper">
  	 <div style="padding:5px 5px 0px 5px; background-color:#F4F8Fb;">
		 <div class="header" style="height:40px; line-height:40px; background-color:#468CCA; text-align:right; padding-right:40px;">
		 	欢迎您 尊敬的系统管理员 当前日期：<label id="showTime" style="color:#FFF; line-height:40px; font-weight:bold; font-size:16px;"></label>
		 </div>
		 <div id="container" class="mainview" style="margin:0 auto; text-align:center; border:0px;">
		 	<div class="items">
		 		<div class="header">
		 			数据设置
		 		</div>
		 		<div class="itemView">
		 			<table class="grid-table">
						<tr>
							<th width="6px"></th>
							<th width="130px">说明</th>
							<th align="left">业务操作</th>
							<th width="40px">操作</th>
						</tr>
						<tr>
							<td width="6px"></td>
							<td align="center">XML生成天数</td>
							<td><input name="xmlday" id="xmlday"  value="${msg}"/></td>
							<td align="center"><a href="javascript:editXml();">修改</a></td>
						</tr>
					</table>
		 		</div>
		 	</div>
		 	<div class="items" style="float:right;">
		 		<div class="header">
		 			业务介绍
		 		</div>
		 		<div class="itemView">
		 			<div id="tabs" style="border:0px;">
				  		<ul>
					        <li><a href="#tabs1"><span class="bold">业务１</span></a></li>
				    	</ul>
						<div id="tabs1">
							<div>文字描述</div>
						</div>
					</div>
		 		</div>
		 	</div> 
		 	<div class="items">
		 		<div class="header">
		 			常用资料查看&打印
		 		</div>
		 		<div class="itemView">
					<table class="grid-table">
						<tr>
							<th width="6px"></th>
							<th width="30px">序号</th>
							<th>文件名称</th>
							<th width="80px">操作</th>
						</tr>
						<tr>
							<td width="6px"></td>
							<td align="center">1</td>
							<td>某某某文件</td>
							<td align="center">查看   打印</td>
						</tr>
					</table>
		 		</div>
		 	</div>
		 	<div class="items" style="float:right;">
		 		<div class="header">
		 			系统使用帮助
		 		</div>
		 		<div class="itemView">
		 			<table class="grid-table">
						<tr>
							<th width="6px"></th>
							<th width="30px">序号</th>
							<th>名称</th>
							<th width="80px">操作</th>
						</tr>
						<tr>
							<td width="6px"></td>
							<td align="center">1</td>
							<td>更新自然人名称过滤字段，提示信息出现之前，请勿进行其他操作</td>
							<td align="center"><a href="#">点击更新</a></td>
						</tr>
					</table>
		 		</div>
		 	</div>
		 	<div style="clear:both; width:0px; display:none;"></div>
		 </div>
	 </div>
	</div>
 </body>
</html>