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
				_this.find("tr:not(:first)").hover(
						function() {
							$(this).attr("bColor",$(this).css("background-color")).css("background-color", "#FFF4CE").css("cursor", "pointer");
							$(this).children("td:first").addClass("grid-arrow");
						},
						function() {
							$(this).css("background-color",$(this).attr("bColor"));
							$(this).children("td:first").removeClass("grid-arrow");
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

function fromcitynameshowMenu() {
	var cityObj = $("#extfromcityname");
	var cityOffset = $("#extfromcityname").offset();
	$("#fromcitynamemenuContent").css({left : cityOffset.left + "px",top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", fromcitynameonBodyDown);
}
function fromcitynamehideMenu() {
	$("#fromcitynamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", fromcitynameonBodyDown);
}
function fromcitynameonBodyDown(event) {
	if (!(event.target.id == "fromcitynamemenuBtn"
			|| event.target.id == "fromcitynamemenuContent" || $(event.target)
			.parents("#fromcitynamemenuContent").length > 0)) {
		fromcitynamehideMenu();
	}
}

function fromcitynameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("fromcityname"), nodes = zTree
			.getSelectedNodes(), v = "", k = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
		k += nodes[i].id + ",";
	}
	if (v.length > 0) {
		v = v.substring(0, v.length - 1);
		k = k.substring(0, k.length - 1);
	}
	$("#extfromcityname").attr("value", v);
	$("#extfromcityjm").attr("value", k);
	fromcitynamehideMenu();
}

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择节点...");
	return check;
}

var settingfromcityname = {
	view : {dblClickExpand : false},
	data : {simpleData : {enable : true}},
	callback : {onClick : fromcitynameonClick}
};

function fromcityjmshowMenu() {
	var cityObj = $("#editfromcityjm");
	var cityOffset = $("#editfromcityjm").offset();
	$("#fromcityjmmenuContent").css({
		left : cityOffset.left + "px", top : cityOffset.top + cityObj.outerHeight() + "px"
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
	for ( var i = 0, l = checkNodes.length; i < l; i++) {
		v += checkNodes[i].name + "-";
		k += checkNodes[i].id + "-";
	}
	if (v.length > 0) {
		v = v.substring(0, v.length - 1);
		k = k.substring(0, k.length - 1);
	}
	$("#editfromcityjm").attr("value", v);
}
var settingfromcityjm = {
		check: {enable: true},
		view : {dblClickExpand : false},
		data : {simpleData : {enable : true}},
		callback : {onCheck: fromcityjmOnCheck}
};

$(document).ready(
		function() {
			initGrid();

			$('#a_del_batch').bind('click',function() {$("input[name='ck']").filter(function(i) {return $(this).attr('checked');}).each(function() {c = 1;});
				if (c > 0) {if (confirm('您确定要删除吗?')) {$('#listForm').attr('action','qlycitytocitypricecustom!batchdel.do').submit();}} else {alert('没有选中');}
			});
			
			$("#btn_price_edit").bind({click : function() {return submits();}});
			
			$.fn.zTree.init($("#fromcityname"), settingfromcityname, fromcitynamezNodes);
			$.fn.zTree.init($("#treefromcityjm"), settingfromcityjm, fromcityjmzNodes);
	});

function changePrice(cid, price) {
	if($("#"+price).val()!=""){
		$.post("qlycitytocitypricecustom!editprice.do", {"qlycitytocitypricecustom.id" : cid,"qlycitytocitypricecustom.price" : $("#"+price).val()}, 
				function(o) {if(o.results==1){$.jBox.tip('成人价修改完成');}}, "json");
	}
}

function changeChildrenPrice(cid, price) {
	if($("#"+price).val()!=""){
		$.post("qlycitytocitypricecustom!editChildrenprice.do", {"qlycitytocitypricecustom.id" : cid,"qlycitytocitypricecustom.childrenprice" : $("#"+price).val()}, 
				function(o) {if(o.results==1){$.jBox.tip('儿童价修改完成');}}, "json");
	}
}

function submits() {
	if (!$("#pvalue").validationEngine('validate')) {
		var zTree = $.fn.zTree.getZTreeObj("treefromcityjm");
		var k = "";
		var checkNodes = zTree.getCheckedNodes(true);
		checkNodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for ( var i = 0, l = checkNodes.length; i < l; i++) {
			if((checkNodes[i] && !checkNodes[i].isParent)){
				k += checkNodes[i].id + ",";
			}
		}
		if (k.length > 0) {
			k = k.substring(0, k.length - 1);
		}
		var lid=$("#lineid").val();
		if(lid==""){jBox.tip("线路编号不能为空");return}
		if(k==""){
			if(confirm("你没有选择地区，此操作将更新当前线路的所有的价格数据，是否执行？")){
				$("#btn_price_edit").hide();
				$.post("qlycitytocitypricecustom!editpricebycitys.do",
						{ "extqlycitytocitypricecustom.tolineid":k,"extqlycitytocitypricecustom.pricedif":$("#pvalue").val(),"extqlycitytocitypricecustom.lineid":lid },
						   	function(data){jBox.tip($(data).find("msg").text(), 'info');$("#btn_price_edit").show();window.location.reload();}, "xml");
			}
		}else{
			$("#btn_price_edit").hide();
			$.post("qlycitytocitypricecustom!editpricebycitys.do",
					{ "extqlycitytocitypricecustom.tolineid":k,"extqlycitytocitypricecustom.pricedif":$("#pvalue").val(),"extqlycitytocitypricecustom.lineid":lid },
					function(data){jBox.tip($(data).find("msg").text(), 'info');$("#btn_price_edit").show();window.location.reload();}, "xml");
		}
	}
}

function toCustomPrice(){
	$("#sendform").attr("action","qlycitytocitypricecustom!edit.do");
	$("#sendform").submit();
}

