$(document).ready(function(){
	$.fn.zTree.init($("#treefromcityjm"), settingfromcity, cityNodes);
	$.fn.zTree.init($("#treetocityjm"), settingtocity, cityNodes);
	$('#genarate_btn').bind({
		click : ajaxLines
	});
});

var settingfromcity = {
		check : {enable : true},view : {dblClickExpand : false},data : {simpleData : {enable : true}},
		callback : {onCheck : fromcityjmOnCheck}
};

function fromcityjmOnCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treefromcityjm"),v = "",checkNodes = zTree.getCheckedNodes(true);
	checkNodes.sort(function compare(a, b) { return a.id - b.id; });
	for (var i = 0, l = checkNodes.length; i < l; i++) { 
		if(!checkNodes[i].isParent){
			v += checkNodes[i].name + "\r\n";
		}
	}
	if (v.length > 0) { v = v.substring(0, v.length - 2); }
	
		$("#fromcity").attr("value", v);
}

var settingtocity = {
		check : {enable : true},view : {dblClickExpand : false},data : {simpleData : {enable : true}},
		callback : {onCheck : tocityjmOnCheck}
};

function tocityjmOnCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treetocityjm"),v = "",checkNodes = zTree.getCheckedNodes(true);
	checkNodes.sort(function compare(a, b) { return a.id - b.id; });
	for (var i = 0, l = checkNodes.length; i < l; i++) {
		if(!checkNodes[i].isParent){
			v += checkNodes[i].name + "\r\n";
		}
	}
	if (v.length > 0) { v = v.substring(0, v.length - 2); }
	if(!treeNode.isParent){
		$("#tocity").attr("value", v);
	}
}

function ajaxLines(){
	var data={
			fromcity : $('#fromcity').val(),
			tocity : $('#tocity').val(),
			linecode : $.trim($('#linecode').val())
	};
	ajax("px!ajaxLines.do",data,function(o){
		var lineurl="";
		$(o.obj).each(function( i, val){
			lineurl+=val+"\r\n";
		});
		$('#linelist').val(lineurl);
	});
}

//与后台调用的统一方法
function ajax(url,data,callback){
	$.ajax({
		url : url,
		type : 'POST',
		data : data,
		timeout : 30000,
		beforeSend : function() {
			$.jBox.tip("正在处理中...", 'loading');
		},
		success : function(data,response,status) {
			$.jBox.tip("处理完成...", 'success');
			var obj = eval('(' + data + ')');
			if(obj.results == '0'){
				$.jBox.error('提示', '操作失败');
			}else if(obj.results == '1' || obj.results=='2')
				callback(obj);
			else{
				$.jBox.error('提示', '未知错误，请联系系统管理员');
			}
		},
		error : function(request,info,obj){
			$.jBox.tip("系统异常..."+info, 'success');
		}
	});
}