function submits() {
	if ($("#eidtForm").validationEngine('validate')) {
		$("#tj,#cz").hide();
		return true;
	} else {
		$("#tj,#cz").show();
		return false;
	}
};
function pnameshowMenu() {
	var cityObj = $("#pname");
	var cityOffset = $("#pname").offset();
	$("#pnamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", pnameonBodyDown);
}
function pnamehideMenu() {
	$("#pnamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", pnameonBodyDown);
}
function pnameonBodyDown(event) {
	if (!(event.target.id == "pnamemenuBtn"
			|| event.target.id == "pnamemenuContent" || $(event.target)
			.parents("#pnamemenuContent").length > 0)) {
		pnamehideMenu();
	}
}
function pnameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treepname"), nodes = zTree
			.getSelectedNodes(), v = "", n = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
		n += nodes[i].id + ",";
	}
	if (v.length > 0) {
		v = v.substring(0, v.length - 1);
		n = n.substring(0, n.length - 1);
	}
	$("#pid").attr("value", n);
	$("#pname").attr("value", v);
	pnamehideMenu();
}

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check) {
		// alert("只能选择节点...");
		check = true;
	}
	return check;
}
var settingpname = {
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
		onClick : pnameonClick
	}
};

function airportnameshowMenu() {
	var cityObj = $("#airportname");
	var cityOffset = $("#airportname").offset();
	$("#airportnamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", airportnameonBodyDown);
}
function airportnamehideMenu() {
	$("#airportnamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", airportnameonBodyDown);
}
function airportnameonBodyDown(event) {
	if (!(event.target.id == "airportnamemenuBtn"
			|| event.target.id == "airportnamemenuContent" || $(event.target)
			.parents("#airportnamemenuContent").length > 0)) {
		airportnamehideMenu();
	}
}
function airportnameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeairportname"), nodes = zTree
			.getSelectedNodes(), v = "", n = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
		n += nodes[i].id + ",";
	}
	if (v.length > 0) {
		v = v.substring(0, v.length - 1);
		n = n.substring(0, n.length - 1);
	}
	$("#airportname").attr("value", v);
	$("#airportcode").attr("value", n);
	airportnamehideMenu();
}

function airportbeforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check) {
		// alert("只能选择节点...");
		check = true;
	}
	return check;
}
var settingairportname = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : airportbeforeClick,
		onClick : airportnameonClick
	}
};

$(document).ready(function() {
	$("#tj").bind({
		click : function() {
			submits();
		}
	});
	$("#tj,#cz").bind({
		mouseover : function() {
			$(this).css({
				"background-color" : "red",
				"cursor" : "pointer"
			});
		},
		mouseout : function() {
			$(this).css({
				"background-color" : "#FFFFFF",
				"cursor" : ""
			});
		}
	});
	$.fn.zTree.init($("#treepname"), settingpname, pnamezNodes);
	$.fn.zTree.init($("#treeairportname"), settingairportname, pnamezNodes);
});
