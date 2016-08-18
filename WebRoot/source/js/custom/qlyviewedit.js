function submits() {
	if ($("#eidtForm").validationEngine('validate')) {
		$("#tj,#cz").hide();
		return true;
	} else {
		$("#tj,#cz").show();
		return false;
	}
};

var swfuviewpic, swfuvideourl;
var setsviewpic = {
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
	button_placeholder_id : "sbphviewpic",
	button_width : 61,
	button_height : 22,
	/** Flash Settings */
	flash_url : "source/js/swfupload/swfupload.swf",
	swfupload_element_id : "flashUI1",
	degraded_element_id : "degradedUI1",
	custom_settings : {
		progressTarget : "fupviewpic",
		cancelButtonId : "bcviewpic",
		htarget : "viewpic",
		showtarget : "showviewpic"
	},
	/** Debug Settings */
	debug : false,
	use_query_string : true
};
var setsvideourl = {
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
	button_placeholder_id : "sbphvideourl",
	button_width : 61,
	button_height : 22,
	/** Flash Settings */
	flash_url : "source/js/swfupload/swfupload.swf",
	swfupload_element_id : "flashUI2",
	degraded_element_id : "degradedUI2",
	custom_settings : {
		progressTarget : "fupvideourl",
		cancelButtonId : "bcvideourl",
		htarget : "videourl",
		showtarget : "showvideourl"
	},
	/** Debug Settings */
	debug : false,
	use_query_string : true
};
function typenameshowMenu() {
	var cityObj = $("#typename");
	var cityOffset = $("#typename").offset();
	$("#typenamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", typenameonBodyDown);
}
function typenamehideMenu() {
	$("#typenamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", typenameonBodyDown);
}
function typenameonBodyDown(event) {
	if (!(event.target.id == "typenamemenuBtn"
			|| event.target.id == "typenamemenuContent" || $(event.target)
			.parents("#typenamemenuContent").length > 0)) {
		typenamehideMenu();
	}
}
function typenameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treetypename"), nodes = zTree
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
	$("#typename").attr("value", v);
	$("#typeid").attr("value", k);
	typenamehideMenu();
}
function citynameshowMenu() {
	var cityObj = $("#cityname");
	var cityOffset = $("#cityname").offset();
	$("#citynamemenuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", citynameonBodyDown);
}
function citynamehideMenu() {
	$("#citynamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", citynameonBodyDown);
}
function citynameonBodyDown(event) {
	if (!(event.target.id == "citynamemenuBtn"
			|| event.target.id == "citynamemenuContent" || $(event.target)
			.parents("#citynamemenuContent").length > 0)) {
		citynamehideMenu();
	}
}
function citynameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treecityname"), nodes = zTree
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
	$("#cityname").attr("value", v);
	$("#cityid").attr("value", k);
	citynamehideMenu();
}

function beforeClick(treeId, treeNode) {
//	var check = (treeNode && !treeNode.isParent);
//	if (!check)
//		alert("只能选择节点...");
	return true;
}
var settingtypename = {
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
		onClick : typenameonClick
	}
};
var settingcityname = {
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
		onClick : citynameonClick
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
	swfuviewpic = new SWFUpload(setsviewpic);
	swfuvideourl = new SWFUpload(setsvideourl);
	$.fn.zTree.init($("#treetypename"), settingtypename, typenamezNodes);
	$.fn.zTree.init($("#treecityname"), settingcityname, citynamezNodes);
});

function delimg(dlid,vpid,picurl){
	$.post("qlyviewpic!delimg.do",
			{ 'qlyviewpic.vpid':vpid,'qlyviewpic.picurl':picurl},
			   	function(data){
					$('#'+dlid).remove();
				}, 
			  	"xml");
} 
