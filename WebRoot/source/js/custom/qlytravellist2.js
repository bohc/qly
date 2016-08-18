// 页面加载时，对Table表格移动鼠标行变色操作，通用方法
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

$(document).ready(
		function() {
			initGrid();
			$('#a_del_batch').bind(
					'click',
					function() {
						$("input[name='ck']").filter(function(i) {
							return $(this).attr('checked');
						}).each(function() {
							c = 1;
						});
						if (c > 0) {
							if (confirm('您确定要删除吗?')) {
								$('#listForm').attr('action',
										'qlytravel!batchdel.do').submit();
							}
						} else {
							alert('没有选中');
						}
					});
			$.fn.zTree.init($("#treevtypename"), settingvtypename,vtypenamezNodes);
		});

function vtypenameshowMenu() {
	var cityObj = $("#vtypename");
	var cityOffset = $("#vtypename").offset();
	$("#vtypenamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", vtypenameonBodyDown);
}
function vtypenamehideMenu() {
	$("#vtypenamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", vtypenameonBodyDown);
}
function vtypenameonBodyDown(event) {
	if (!(event.target.id == "vtypenamemenuBtn"
			|| event.target.id == "vtypenamemenuContent" || $(event.target)
			.parents("#vtypenamemenuContent").length > 0)) {
		vtypenamehideMenu();
	}
}
function vtypenameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treevtypename"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	var cityObj = $("#vtypename");
	cityObj.attr("value", v);
	vtypenamehideMenu();
}

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择节点...");
	return check;
}
var settingvtypename = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : vtypenameonClick
	}
};
