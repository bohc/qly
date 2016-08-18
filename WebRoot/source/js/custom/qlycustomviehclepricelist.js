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
$(document).ready(function(){
	initGrid();
	$('#a_del_batch').bind('click', function() {$("input[name='ck']").filter(function(i) {return $(this).attr('checked');}).each(function() {c=1;});if (c > 0) {if( confirm('您确定要删除吗?') ){$('#listForm').attr('action', 'qlycustomviehcleprice!batchdel.do').submit();}} else {alert('没有选中');}});
});

function a_add(){
	$.jBox('iframe:qlycustomviehcleprice!edit.do', {
		title: "添加交通",
	    width: 700,
	    height: 544,
	    showClose:false,
	    buttons: { '关闭': 0,'提交': 1},
	    buttonsFocus: 0,
	    submit: function (v, h, f) { 
	    	switch(v){
		    	case 0:
		    		break;
		    	case 1:
		    		var iframeName = h.children(0).attr("name");
		            var container = window.frames[iframeName].document
		            var fromcityname = $("#fromcityname", container).val();
		            if(fromcityname==""){
		            	alert("请选择出发地。");
		            	break;
		            }
		            var fromcityjm = $("#fromcityjm", container).val();
		            var tocityname = $("#tocityname", container).val();
		            if(tocityname==""){
		            	alert("请选择目的地。");
		            	break;
		            }
		            var tocityjm = $("#tocityjm", container).val();
		            var seattype = $("input[name='seattype']:checked",container).val()
		            var vehicle = $("input[name='vehicle']:checked",container).val()
		            var price = $("#price", container).val();
		            if(vehicle!='飞机'){
		            	if(price==""){
		            		alert("请填写价格。");
		            		break;
		            	}
		            }else{
		            	price="0";
		            }
		            var data={
		            		"qlycustomviehcleprice.traficaltype":vehicle,
		            		"qlycustomviehcleprice.seattype":seattype,
		            		"qlycustomviehcleprice.price":price,
		            		"qlycustomviehcleprice.childrenprice":$('#childrenprice',container).val(),
		            		"qlycustomviehcleprice.fromcityname":fromcityname,
		            		"qlycustomviehcleprice.fromcityjm":fromcityjm,
		            		"qlycustomviehcleprice.tocityname":tocityname,
		            		"qlycustomviehcleprice.tocityjm":tocityjm
		            	};
		            savePrice(data);
		            break;
	    	}
	    	if(v==0){
	    		return true;
	    	}
	    	return false;
	    }
	});
}


function savePrice(tdata){
	$.ajax({
	   type: "POST",
	   url: "qlycustomviehcleprice!saveAjax.do",
	   data: tdata,
	   success: function(msg){
		   console.log( "Data Saved: " + msg );
		   var obj = eval('(' + msg + ')');
		   if(obj.results == 1){
			   var o=obj.obj;
			   showPrice(o);
		   }
	   }
	});
}

function changePrice(cid, price) {
	if($("#"+price).val()!=""){
		$.post("qlycustomviehcleprice!editprice.do", {"qlycustomviehcleprice.id" : cid,"qlycustomviehcleprice.price" : $("#"+price).val()}, 
				function(o) {if(o.results==1){$.jBox.tip('修改完成');}}, "json");
	}
}

function changeChildrenPrice(cid, price) {
	if($("#"+price).val()!=""){
		$.post("qlycustomviehcleprice!editChildrenprice.do", {"qlycustomviehcleprice.id" : cid,"qlycustomviehcleprice.childrenprice" : $("#"+price).val()}, 
				function(o) {if(o.results==1){$.jBox.tip('修改完成');}}, "json");
	}
}

function showPrice(obj){
	var cobj=$("#m-tr").clone(true);
	cobj.attr("id",$("#m-tr").siblings().size()+1);
	cobj.appendTo($("#grid-table"));
	cobj.show();
	$("a",cobj).attr("href",$("a",cobj).attr("href")+obj.id);
	cobj.find($("[name='ck']")).val(obj.id).attr("tid",obj.id);
	$("td:nth-child(3)",cobj).html(obj.traficaltype);
	$("td:nth-child(4)",cobj).find('input').val(obj.price);
	$("td:nth-child(4)",cobj).find('input').attr("id","price"+($("#grid-table").find("tr").length-1));
	$("td:nth-child(4)",cobj).find('a').attr("href","javascript:changePrice('"+obj.id+"','price"+($("#grid-table").find("tr").length-1)+"');");
	$("td:nth-child(5)",cobj).find('input').val(obj.childrenprice);
	$("td:nth-child(5)",cobj).find('input').attr("id","childrenprice"+($("#grid-table").find("tr").length-1));
	$("td:nth-child(5)",cobj).find('a').attr("href","javascript:changeChildrenPrice('"+obj.id+"','childrenprice"+($("#grid-table").find("tr").length-1)+"');");
	$("td:nth-child(6)",cobj).html(obj.seattype);
	$("td:nth-child(7)",cobj).html(obj.fromcityname);
	$("td:nth-child(8)",cobj).html(obj.fromcityjm);
	$("td:nth-child(9)",cobj).html(obj.fromcitypy);
	$("td:nth-child(10)",cobj).html(obj.tocityname);
	$("td:nth-child(11)",cobj).html(obj.entime);
	$("td:nth-child(12)",cobj).html(obj.enman);
}
