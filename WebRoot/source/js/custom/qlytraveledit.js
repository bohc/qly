function submits() {
	if ($("#eidtForm").validationEngine('validate')) {
		$("#tj,#cz").hide();
		return true;
	} else {
		$("#tj,#cz").show();
		return false;
	}
};
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
	$("#vtypename").attr("value", v);
	$("#vtypecode").attr("value", k);
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
	$.fn.zTree.init($("#treevtypename"), settingvtypename, vtypenamezNodes);
});
