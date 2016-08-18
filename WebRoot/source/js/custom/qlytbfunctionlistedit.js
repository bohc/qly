function submits() {
	if ($("#eidtForm").validationEngine('validate')) {
		$("#tj,#cz").hide();
		return true;
	} else {
		$("#tj,#cz").show();
		return false;
	}
};
function vsnameshowMenu() {
	var cityObj = $("#vsname");
	var cityOffset = $("#vsname").offset();
	$("#vsnamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", vsnameonBodyDown);
}
function vsnamehideMenu() {
	$("#vsnamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", vsnameonBodyDown);
}
function vsnameonBodyDown(event) {
	if (!(event.target.id == "vsnamemenuBtn"
			|| event.target.id == "vsnamemenuContent" || $(event.target)
			.parents("#vsnamemenuContent").length > 0)) {
		vsnamehideMenu();
	}
}
function vsnameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treevsname"), nodes = zTree.getSelectedNodes(), v = "",icode="";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
		icode += nodes[i].id + ",";
	}
	if (v.length > 0){
		v = v.substring(0, v.length - 1);
		icode = icode.substring(0, icode.length - 1);
	}
	var cityObj = $("#vsname");
	var iObj = $("#iscode");
	cityObj.attr("value", v);
	iObj.attr("value", icode);
	vsnamehideMenu();
}

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check){
		//alert("只能选择节点...");
		check=true;
	}
	return check;
}
var settingvsname = {
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
		onClick : vsnameonClick
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
	$.fn.zTree.init($("#treevsname"), settingvsname, vsnamezNodes);
});
