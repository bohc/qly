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

function selectLine(surl, lid) {
	$("#copyCustomPriceform .tolineid").val(lid);
	$.jBox("iframe:" + surl, {
		title : "请选择要复制到的线路",
		width : 1000,
		height : 500,
		top : '10px',
		buttons : {
			'关闭' : 0,
			'复制' : 1
		},
		buttonsFocus : 0,
		submit : function(v, h, f) {
			if (v == 0) {
				return true;
			} else if (v == 1) {
				var we = h.children(0).attr("name");
				var tid = "";
				$(window.frames[we].document).find("input[name='ck']").filter(
						function() {
							return $(this).attr("checked");
						}).each(function(i) {
					tid += $(this).attr("tid") + ",";
				});
				if (tid.length > 0) {
					tid = tid.substring(0, tid.length - 1);
					$("#copyCustomPriceform .lineid").val(tid);
					setDifferencePrice();
					return true;
				} else {
					$.jBox.tip("你没有选择数据", 'error');
					return false;
				}
			}
		}
	});
}

function setLinePriceDiff(surl) {
	$.jBox("iframe:" + surl, {
		title : "设置线路不同时段的价格",
		width : 1000,
		height : 500,
		top : '10px',
		buttons : {
			'返回' : 0
		},
		buttonsFocus : 0,
		submit : function(v, h, f) {
			if (v == 0) {
				return true;
			}
			return true;
		}
	});
}

function setDifferencePrice() {
	var html = "<div style='padding:10px;'>价差：<input type='text' id='pdif' name='pdif' class='validate[custom[number]]'/></div>";
	$.jBox("html:" + html, {
		title : "请输入与来源对象的价差",
		width : 1000,
		height : 500,
		top : '10px',
		buttons : {
			'关闭' : 0,
			'复制' : 1
		},
		buttonsFocus : 0,
		submit : function(v, h, f) {
			var sub = true;
			if (v == 0) {
				return true;
			}
			if (v == 1) {
				if (f.pdif != '') {
					if (!$("#pdif").validationEngine('validate')) {
						$("#copyCustomPriceform .pricedif").val(f.pdif);
						$("#copyCustomPriceform").attr("action",
								"qlycitytocitypricecustom!copyprice.do");
						$("#copyCustomPriceform").submit();
						return true;
					} else {
						return false;
					}
				} else {
					$("#copyCustomPriceform").attr("action",
							"qlycitytocitypricecustom!copyprice.do");
					$("#copyCustomPriceform").submit();
					return true;
				}
			}
		}
	});
}

function totravel(ltid, lineid, linename) {
	$("#totravelform .ltid").val(ltid);
	$("#totravelform .lineid").val(lineid);
	$("#totravelform .linename").val(linename);
	$("#totravelform").attr("action", "qlylinetravel!list.do");
	$("#totravelform").submit();
}
function toCustomPrice(lineid, linename, placeid, backplaceid,placename,backplacename) {
	$("#sendform .lineid").val(lineid);
	$("#sendform .linename").val(linename);
	$("#sendform .placeid").val(placeid);
	$("#sendform .placename").val(placename);
	$("#sendform .backplaceid").val(backplaceid);
	$("#sendform .backplacename").val(backplacename);
	$("#sendform").attr("action", "qlycitytocitypricecustom!list.do");
	$("#sendform").submit();
}

function fromcityjmshowMenu() {
	var cityObj = $("#fromcityjm");
	var cityOffset = $("#fromcityjm").offset();
	$("#fromcityjmmenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", fromcityjmonBodyDown);
}
function fromcityjmhideMenu() {
	$("#fromcityjmmenuContent").fadeOut("fast");
	$("body").unbind("mousedown", fromcityjmonBodyDown);
}
function fromcityjmonBodyDown(event) {
	if (!(event.target.id == "fromcityjmmenuBtn"
			|| event.target.id == "fromcityjmmenuContent" || $(event.target)
			.parents("#fromcityjmmenuContent").length > 0)) {
		fromcityjmhideMenu();
	}
}
function fromcityjmOnCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treefromcityjm");
	var v = "", k = "";
	var checkNodes = zTree.getCheckedNodes(true);
	checkNodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for (var i = 0, l = checkNodes.length; i < l; i++) {
		v += checkNodes[i].name + "--";
		k += checkNodes[i].id + "--";
	}
	if (v.length > 0) {
		v = v.substring(0, v.length - 1);
		k = k.substring(0, k.length - 1);
	}
	$("#fromcityjm").attr("value", v);
}
var settingfromcityjm = {
	check : {
		enable : true
	},
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onCheck : fromcityjmOnCheck
	}
};
function passcityshowMenu() {
	var cityObj = $("#passcity");
	var cityOffset = $("#passcity").offset();
	$("#passcitymenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", passcityonBodyDown);
}
function passcityhideMenu() {
	$("#passcitymenuContent").fadeOut("fast");
	$("body").unbind("mousedown", passcityonBodyDown);
}
function passcityonBodyDown(event) {
	if (!(event.target.id == "passcitymenuBtn"
			|| event.target.id == "passcitymenuContent" || $(event.target)
			.parents("#passcitymenuContent").length > 0)) {
		passcityhideMenu();
	}
}
function passcityonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treepasscity"), nodes = zTree
			.getSelectedNodes(), v = "", k = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for (var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name;
		k += nodes[i].id;
	}
	$("#passcity").attr("value", v);
	passcityhideMenu();
}
var settingpasscity = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : passcityonClick
	}
};

function placenameshowMenu() {
	var cityObj = $("#placename");
	var cityOffset = $("#placename").offset();
	$("#placenamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", placenameonBodyDown);
}
function placenamehideMenu() {
	$("#placenamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", placenameonBodyDown);
}
function placenameonBodyDown(event) {
	if (!(event.target.id == "placenamemenuBtn"
			|| event.target.id == "placenamemenuContent" || $(event.target)
			.parents("#placenamemenuContent").length > 0)) {
		placenamehideMenu();
	}
}
function placenameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeplacename"), nodes = zTree
			.getSelectedNodes(), v = "", k = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for (var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name;
		k += nodes[i].id;
	}
	$("#placename").attr("value", v);
	placenamehideMenu();
}
var settingplacename = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : passcityonClick
	}
};

$(document).ready(function() {
	initGrid();

	$('#a_del_batch').bind('click', function() {
		$("input[name='ck']").filter(function(i) {
			return $(this).attr('checked');
		}).each(function() {
			c = 1;
		});
		if (c > 0) {
			if (confirm('您确定要删除吗?')) {
				$('#listForm').attr('action', 'qlyline!batchdel.do').submit();
			}
		} else {
			alert('没有选中');
		}
	});

	$("#btn_price_edit").bind({click : submits});
	$.fn.zTree.init($("#treefromcityjm"), settingfromcityjm, fromcityjmzNodes);
	$.fn.zTree.init($("#treepasscity"), settingpasscity, passcityzNodes);
	$.fn.zTree.init($("#treeplacename"), settingplacename, placenamezNodes);
});

function genarateLine() {
	console.log($(this).attr("qid"));
	$.jBox.confirm("是否确定生成线路？", "提示", function(v, h, f){
		if(v=="ok"){
			$.jBox.tip("正在生成线路，请稍等...", 'loading');
			$.post("qlyline!genarateLine.do", {"qlyline.id" : $(this).attr("qid")
			}, function(data) {console.log(data);
				if(data.results==1){
					$.jBox.tip("线路生成完毕...", 'success');
				}else{
					$.jBox.tip("由于未知的原因，线路生成失败...", 'success');
				}
			}, "json");
		}
	});
}

function submits() {
	if ($("#editprice").validationEngine('validate')) {
		var zTree = $.fn.zTree.getZTreeObj("treefromcityjm");
		var k = "";
		var checkNodes = zTree.getCheckedNodes(true);
		checkNodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for (var i = 0, l = checkNodes.length; i < l; i++) {
			if ((checkNodes[i] && !checkNodes[i].isParent)) {
				k += checkNodes[i].id + ",";
			}
		}
		if (k.length > 0) {
			k = k.substring(0, k.length - 1);
		}
		if (k == "") {
			if (confirm("你没有选择地区，此操作将更新所有的价格数据，是否执行？")) {
				$("#btn_price_edit").hide();
				$.post("qlycitytocitypricecustom!editpricebycitys.do", {
					"extqlycitytocitypricecustom.tolineid" : k,
					"extqlycitytocitypricecustom.pricedif" : $("#pvalue").val()
				}, function(data) {
					jBox.tip($(data).find("msg").text(), 'info');
					$("#btn_price_edit").show();
				}, "xml");
			}
		} else {
			$("#btn_price_edit").hide();
			$.post("qlycitytocitypricecustom!editpricebycitys.do", {
				"extqlycitytocitypricecustom.tolineid" : k,
				"extqlycitytocitypricecustom.pricedif" : $("#pvalue").val()
			}, function(data) {
				jBox.tip($(data).find("msg").text(), 'info');
				$("#btn_price_edit").show();
			}, "xml");
		}
	} else {
		// alert(false);
		// return false;
	}
}
