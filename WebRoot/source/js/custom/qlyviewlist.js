//页面加载时，对Table表格移动鼠标行变色操作，通用方法
var c;

function initGrid() {
	$('table.grid-table').each(
			function() {
				var _this = $(this);
				// 设置偶数行和奇数行颜色
				_this.find("tr:odd").css("background-color", "#DEE9F7");
				_this.find("tr:even").css("background-color", "#FFFFFF");
				// 鼠标移动隔行变色hover用法关键
				_this.find("tr:not(:first)")
						.hover(
								function() {
									$(this).attr("bColor",
											$(this).css("background-color"))
											.css("background-color", "#FFF4CE")
											.css("cursor", "pointer");
									$(this).children("td:first").addClass(
											"grid-arrow");
								},
								function() {
									$(this).css("background-color",
											$(this).attr("bColor"));
									$(this).children("td:first").removeClass(
											"grid-arrow");
								});
			});

	$("#toggleAll").bind("click", function() {
		if (this.checked) {
			$("input[name='ck']").attr("checked", true);
		} else {
			$("input[name='ck']").attr("checked", false);
		}
	});
	
}
$(document).ready(function(){
	initGrid();

	$('#a_del_batch').bind('click', function() {$("input[name='ck']").filter(function(i) {return $(this).attr('checked');}).each(function() {c=1;});if (c > 0) {if( confirm('您确定要删除吗?') ){$('#listForm').attr('action', 'qlyview!batchdel.do').submit();}} else {alert('没有选中');}});
});
