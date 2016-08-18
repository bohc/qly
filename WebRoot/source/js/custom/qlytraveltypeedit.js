function submits() {
	if ($("#eidtForm").validationEngine('validate')) {
		$("#tj,#cz").hide();
		return true;
	} else {
		$("#tj,#cz").show();
		return false;
	}
};
function vparnameshowMenu() {
	var cityObj = $("#vparname");
	var cityOffset = $("#vparname").offset();
	$("#vparnamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", vparnameonBodyDown);
}
function vparnamehideMenu() {
	$("#vparnamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", vparnameonBodyDown);
}
function vparnameonBodyDown(event) {
	if (!(event.target.id == "vparnamemenuBtn"
			|| event.target.id == "vparnamemenuContent" || $(event.target)
			.parents("#vparnamemenuContent").length > 0)) {
		vparnamehideMenu();
	}
}
function vparnameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treevparname"), nodes = zTree
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
	$("#vparname").attr("value", v);
	$("#vparcode").attr("value", k);
	vparnamehideMenu();
}

function beforeClick(treeId, treeNode) {
	/**
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择节点...");
		*/
	
	return true;
}
var settingvparname = {
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
		onClick : vparnameonClick
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
	$.fn.zTree.init($("#treevparname"), settingvparname, vparnamezNodes);
});
