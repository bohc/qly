<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>标准</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="source/css/global.css" type="text/css">
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			initGrid();
		});
		
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
	</script>
</head>
 <body>
 	<div class="wraper">
  	 <div style="padding:5px; background-color:#F4F8Fb;">
				<div class="grid">
					<div class="grid-search">
						<input type="button" class="btn" value="查 找" />
						<input type="button" class="btn" value="三个字" />
						<input type="button" class="btn" value="四个字的" />
					</div>
					<table class="grid-table">
						<tr>
							<th width="6px"></th>
							<th>操作</th>
							<th>我是标题1</th>
							<th>我是标题2</th>
							<th>我是标题3</th>
							<th>我是标题4</th>
							<th>我是标题5</th>
							<th>我是标题6</th>
							<th>我是标题7</th>
							<th>我是标题8</th>
							<th>我是标题9</th>
							<th>我是标题10</th>
							<th>我是标题11</th>
						</tr>
						<tr>
							<td width="6px"></td>
							<td>修改 删除 审核</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
						</tr>
						<tr>
							<td width="6px"></td>
							<td>修改 删除 审核</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
							<td>我是内容</td>
						</tr>
					 </table>
				</div>
		 </div>
	</div>
 </body>
</html>