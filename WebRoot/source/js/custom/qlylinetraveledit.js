function submits() {
	if ($("#eidtForm").validationEngine('validate')) {
		try{
			$("#tj,#cz").hide();
			setViews();
			setVehicles();
		}catch(err){
			console.log(err);
			$("#tj,#cz").show();
			return false;
		}
		return true;
	} else {
		$("#tj,#cz").show();
		return false;
	}
};

function setViews(){
	var vname = "";
	var vid = "";
	$("input[id^='txt_view_']").each(function(){
		vname += $(this).attr("title") + ",";
		var vd=$(this).attr("id");
		vid += vd.substring('txt_view_'.length, vd.length) + ",";
	});
	if(vname.length>0){
		vname = vname.substring(0, vname.length - 1);
		vid = vid.substring(0, vid.length - 1);
	}
	$("#viewcontent").val(vname);
	$("#viewid").val(vid);
}

function setVehicles(){
	$(".v-class").each(function(){
		var vid=$(">td:nth-child(1)",this).html();
		if(vid!=""){
			$('#eidtForm').append('<input type="hidden" name="vehiclesprice" value="'+vid+'"/>');
		}
		var vtime = "";
		$("input[name='vtime']:checked",$(this)).each(function(){
			vtime+=$(this).val()+",";
		})
		if(vtime.length>0){
			vtime = vtime.substring(0, vtime.length - 1);
			$('#eidtForm').append('<input type="hidden" name="vehiclespricetitle" value="'+(vid+":"+vtime)+'"/>');
		}
	});
}


$(document).ready(function() {
	$("#tj").bind({ click : function() { return submits(); } });
	$("#tj,#cz").bind({
		mouseover : function() { $(this).css({"background-color" : "red","cursor" : "pointer"}); },
		mouseout : function() { $(this).css({"background-color" : "#FFFFFF","cursor" : ""}); }
	});
});

function selectTravel(surl) {
	$.jBox("iframe:" + surl, {
		title : "行程选择",
		width : 1000,
		height : 500,
		top : '10px',
		buttons : {
			'关闭' : 0,
			'选择' : 1
		},
		buttonsFocus : 0,
		submit : function(v, h, f) {
			if (v == 0) {
				return true; // close the window
			}
			if (v == 1) {
				var we = h.children(0).attr("name");
				var container = window.frames[we].document;
				var tname = "",tid = "";
				$("#stable").nextAll().remove();
				$(container).find("input[name='ck']").filter(function() {
					return $(this).attr("checked");
				}).each(function(i) {
					tname += $(this).attr("tname") + ",";
					tid += $(this).attr("tid") + ",";
					showTravel(this);
				});
				if(tname.length>0){
					tname = tname.substring(0, tname.length - 1);
					tid = tid.substring(0, tid.length - 1);
				}
				$("#traveltitle").val(tname);
				$("#travelcode").val(tid);
			}
			return true;
		}
	});
}
function selectView(surl) {
	$.jBox("iframe:" + surl, {
		title : "景点选择",
		width : 1000,
		height : 500,
		top : '10px',
		buttons : {
			'关闭' : 0,
			'选择' : 1
		},
		buttonsFocus : 0,
		submit : function(v, h, f) {
			if (v == 0) {
				return true; // close the window
			}
			if (v == 1) {
				var we = h.children(0).attr("name");
				var container = window.frames[we].document;
				$(container).find("input[name='ck']").filter(function() {
					return $(this).attr("checked");
				}).each(function(i) {
					$('#btn_view_sel').after('<div><span>'+$(this).attr("vname")+'</span><input type="button" id="txt_view_'+$(this).attr("vid")+'" title="'+$(this).attr("vname")+'" value="删除" onclick="javascript:dele(this);"></div>');
				});
			}
			return true;
		}
	});
}

function dele(obj){
	$(obj).parent().parent().remove();
}

function showTravel(obj){
	var cobj=$("#stable").clone(true);
	cobj.attr("id",$("#stable").siblings().size()+1);
	cobj.appendTo($("#std"));
	cobj.css("display","block");
	cobj.find($("[class='tratitle']")).html($(obj).attr("traveltitle")+"&nbsp;("+$(obj).attr("travelcode")+")");
	cobj.find($("[class='fee']")).html($(obj).attr("selfexpense")+"&nbsp;");
	cobj.find($("[class='shoping']")).html($(obj).attr("shop")+"]");
	cobj.find($("[class$='vtypename']")).html($(obj).attr("vtypename"));
	cobj.find($("[class='breakfast']")).html($(obj).attr("breakfast"));
	cobj.find($("[class$='lunch']")).html($(obj).attr("lunch"));
	cobj.find($("[class$='supper']")).html($(obj).attr("supper"));
	cobj.find($("[class$='hotel']")).html($(obj).attr("hotel")+"("+$(obj).attr("innfeature")+")");
	cobj.find($("[class$='stay']")).html($(obj).attr("stay"));
	cobj.find($("[class$='activity']")).html($(obj).attr("activity"));
	cobj.find($("[class$='mark']")).html($(obj).attr("mark"));
}

function showAddViehcle(){
	$.jBox('iframe:qlycustomviehcleprice!listSelect.do', {
		title: "添加交通",
	    width: 750,
	    height: 540,
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
		            var tobj=$("input[name='ck']:checked",container);
		            if(tobj.length>0){
		            	$.each(tobj,function(i, n){
		            		var obj=$(n).parent().parent();
		            		var vehicle=$("td:nth-child(2)",obj).html();
		            		var seattype=$("td:nth-child(3)",obj).html();
		            		var fromcityname=$("td:nth-child(4)",obj).html();
		            		var tocityname=$("td:nth-child(5)",obj).html();
		            		var price=$("td:nth-child(6)",obj).html();
		            		var childrenprice=$("td:nth-child(7)",obj).html();
		            		var oh=$("#tr-show",$("table.vehicle-show")).clone(true);
		            		oh.addClass("v-class");
		            		$(">td:nth-child(1)",oh).html($(n).val());
		            		$(">td:nth-child(2)",oh).html(fromcityname);
		            		$(">td:nth-child(3)",oh).html(tocityname);
		            		$(">td:nth-child(4)",oh).html(vehicle);
		            		$(">td:nth-child(5)",oh).html(seattype);
		            		$(">td:nth-child(6)",oh).html(price+"元");
		            		$(">td:nth-child(7)",oh).html(childrenprice+"元");
		            		if(vehicle != "飞机"){
		            			$(">td:nth-child(8)",oh).html("");
		            		}
		            		$(oh).show();
		            		$("table.vehicle-show").append(oh);
		            	})
		            }
		    		break;
	    	}
	    	if(v==0){
	    		return true;
	    	}
	    	return false;
	    }
	});
}

