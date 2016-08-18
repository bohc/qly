$(document).ready(function() {
	$("#tj").bind({ click : function() { return submits(); } });
	$("#tj,#cz").bind({
		mouseover : function() { $(this).css({"background-color" : "red","cursor" : "pointer"}); },
		mouseout : function() { $(this).css({"background-color" : "#FFFFFF","cursor" : ""}); }
	});
	$.fn.zTree.init($("#treefromcityname"), settingfromcityname,citynamezNodes);
	$.fn.zTree.init($("#treetocityname"), settingtocityname,citynamezNodes);
	$('input[name="vehicle"]').bind('click',changeSeattype);
});

function changeSeattype(){
	var vehicle = $("input[name='vehicle']:checked").val();
	var stype=$('#stype');
	var objs;
	if(vehicle=="飞机"){
		objs=["经济舱"];
	}else if(vehicle=="汽车"){
		objs=["软座"];
	}else if(vehicle=="火车"){
		objs=["硬卧","软卧","硬座","高级软卧","一等座","二等座"];
	}else if(vehicle=="轮船"){
		objs=["经济舱"];
	}
	var ele="";
	for(var i=0;i<objs.length;i++){
		var cflag="";
		if(i==0){
			cflag='checked="checked"';
		}
		ele+='<input type="radio" name="seattype" id="seattype'+objs[i]+'" '+cflag+' value="'+objs[i]+'"><label for="seattype'+objs[i]+'">'+objs[i]+'</label>';
	}
	$(stype).html(ele);
}

function fromcityOnClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treefromcityname"), nodes = zTree.getSelectedNodes(), v = "", k = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name;
		k += nodes[i].id;
	}
	$("#fromcityname").val(v);
	$("#fromcityjm").val(k);
	console.log(v+"=="+k);
}
var settingfromcityname = {
		view : {dblClickExpand : false},
		data : {simpleData : {enable : true}},
		callback : {onClick : fromcityOnClick}
};

function tocityOnClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treetocityname"), nodes = zTree.getSelectedNodes(), v = "", k = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name;
		k += nodes[i].id;
	}
	$("#tocityname").val(v);
	$("#tocityjm").val(k);
	console.log(v+"=="+k);
}
var settingtocityname = {
		view : {dblClickExpand : false},
		data : {simpleData : {enable : true}},
		callback : {onClick : tocityOnClick}
};
