var swfujourneypic;
var setsjourneypic = {
	upload_url : "fupload!uploadFile.do",
	post_params : {
		"module" : "view",
		"path" : "upload"
	},
	/** File Upload Settings */
	file_post_name : "file",
	file_size_limit : "102400",
	file_types : "*.*",
	file_types_description : "All Files",
	file_upload_limit : "10",
	file_queue_limit : "0",
	/** Event Handler Settings (all my handlers are in the Handler.js file) */
	// file_dialog_start_handler:fileDialogStart,
	file_queued_handler : fileQueued,
	file_queue_error_handler : fileQueueError,
	file_dialog_complete_handler : fileDialogComplete,
	upload_start_handler : uploadStart,
	upload_progress_handler : uploadProgress,
	upload_error_handler : uploadError,
	upload_success_handler : uploadSuccess,
	upload_complete_handler : uploadComplete,
	/** Button Settings */
	button_image_url : "source/js/swfupload/upload_61x22.png",
	button_placeholder_id : "sbphjourneypic",
	button_width : 61,
	button_height : 22,
	/** Flash Settings */
	flash_url : "source/js/swfupload/swfupload.swf",
	swfupload_element_id : "flashUI1",
	degraded_element_id : "degradedUI1",
	custom_settings : {
		progressTarget : "fupjourneypic",
		cancelButtonId : "bcjourneypic",
		htarget : "journeypic",
		showtarget : "showjourneypic"
	},
	/** Debug Settings */
	debug : false,
	use_query_string : true
};
function submits() {
	if ($("#eidtForm").validationEngine('validate')) {
		$("#tj,#cz").hide();
		return true;
	} else {
		$("#tj,#cz").show();
		return false;
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
	var zTree = $.fn.zTree.getZTreeObj("treeplacename"), nodes = zTree.getSelectedNodes(), v = "", k = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name;
		k += nodes[i].id;
	}
	$("#placename").attr("value", v);
	$("#placeid").attr("value", k);
	placenamehideMenu();
}

function backplacenameshowMenu() {
	var cityObj = $("#backplacename");
	var cityOffset = $("#backplacename").offset();
	$("#backplacenamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", backplacenameonBodyDown);
}
function backplacenamehideMenu() {
	$("#backplacenamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", backplacenameonBodyDown);
}
function backplacenameonBodyDown(event) {
	if (!(event.target.id == "backplacenamemenuBtn"
			|| event.target.id == "backplacenamemenuContent" || $(event.target)
			.parents("#backplacenamemenuContent").length > 0)) {
		backplacenamehideMenu();
	}
}
function backplacenameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treebackplacename"), nodes = zTree.getSelectedNodes(), v = "", k = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name;
		k += nodes[i].id;
	}
	$("#backplacename").attr("value", v);
	$("#backplaceid").attr("value", k);
	backplacenamehideMenu();
}

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
	passcityhideMenu();
}

function passcityOnCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treepasscity");
	var v = "", k = "";
	var checkNodes = zTree.getCheckedNodes(true);
	checkNodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = checkNodes.length; i < l; i++) {
		v += checkNodes[i].name + "--";
		k += checkNodes[i].id + "--";
	}
	if (v.length > 0) {
		v = v.substring(0, v.length - 1);
		k = k.substring(0, k.length - 1);
	}
	$("#passcity").attr("value", v);
}

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择节点...");
	return check;
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
		beforeClick : beforeClick,
		onClick : placenameonClick
	}
};
var settingbackplacename = {
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
			onClick : backplacenameonClick
		}
};
var settingpasscity = {
	check: {
			enable: true
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
		beforeClick : beforeClick,
		onClick : passcityonClick,
		onCheck: passcityOnCheck
	}
};
$(document).ready(function() {
	$("#tj").bind({
		click : function() {
			return submits();
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
	swfujourneypic = new SWFUpload(setsjourneypic);
	$.fn.zTree.init($("#treeplacename"), settingplacename, placenamezNodes);
	$.fn.zTree.init($("#treebackplacename"), settingbackplacename, placenamezNodes);
	$.fn.zTree.init($("#treepasscity"), settingpasscity, passcityzNodes);
});

function delimg(dlid,vpid,picurl){
	$.post("qlylinepic!delimg.do",
			{ 'qlylinepic.id':vpid,'qlylinepic.picurl':picurl},
			   	function(data){
					$('#'+dlid).remove();
				}, 
			  	"xml");
}
