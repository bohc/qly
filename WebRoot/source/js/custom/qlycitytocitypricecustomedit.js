function submits(){
	if($("#eidtForm").validationEngine('validate')){
		reSelectedNodes();
		$("#tj,#cz").hide();return true;
	}else{
		$("#tj,#cz").show();return false;
	}
}

function fromcitynameshowMenu() {
	var cityObj = $("#fromcityname");
	var cityOffset = $("#fromcityname").offset();
	$("#fromcitynamemenuContent").css({left : cityOffset.left + "px",top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", fromcitynameonBodyDown);
}
function fromcitynamehideMenu() {
	$("#fromcitynamemenuContent").fadeOut("fast");
	$("body").unbind("mousedown", fromcitynameonBodyDown);
}
function fromcitynameonBodyDown(event) {
	if (!(event.target.id == "fromcitynamemenuBtn"
			|| event.target.id == "fromcitynamemenuContent" || $(event.target)
			.parents("#fromcitynamemenuContent").length > 0)) {
		fromcitynamehideMenu();
	}
}

function reSelectedNodes() {
	var zTree = $.fn.zTree.getZTreeObj("treefromcityname");
	var nodes = zTree.getCheckedNodes(true);
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		if (nodes[i] && !nodes[i].isParent){
			$("#fromcitynamemenuContent").append("<input type=\"hidden\" name=\"ck\" id=\"" + nodes[i].id+ "\" value=\"" + nodes[i].id + "\" />");
		}
	}
}

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择节点...");
	return check;
}

function zTreeOnNodeCreated(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treefromcityname");
	if (treeNode.id == $("#fromcityjm").val()) {
		zTree.checkNode(treeNode, true, true, callbackFlag);
	}
};

var settingfromcityname = {
	check : { enable : true },
	view : { dblClickExpand : false },
	data : { simpleData : { enable : true } },
	callback : { beforeClick : beforeClick, onNodeCreated : zTreeOnNodeCreated, onCheck: zTreeOnCheck }
};

function zTreeOnCheck(event, treeId, treeNode) {
	ajaxVehicleShow();
};

$(document).ready(function(){
	$("#tj").bind({click:function(){
		return submits();
	}});
	$("#tj,#cz").bind({
		mouseover:function(){
			$(this).css({"background-color":"red","cursor":"pointer"});
		},  mouseout:function(){
			$(this).css({"background-color":"#FFFFFF","cursor":""});
		}
	});
	$.fn.zTree.init($("#treefromcityname"), settingfromcityname,fromcitynamezNodes);
	initEvent();
});

function initEvent(){
	$("input[name='vehiclecome']").bind('click',function(){changeSeattype('vehiclecome');});
	$("input[name='vehicleback']").bind('click',function(){changeSeattype('vehicleback');});
}

function changeSeattype(aobj){
	var vehicle = $("input[name='"+aobj+"']:checked").val();
	disableEle(aobj,vehicle);
	var stype=$('#'+aobj+"seatshow");
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
		ele+='<input type="radio" name="'+aobj+'seattype" id="'+aobj+'seattype'+objs[i]+'" '+cflag+' value="'+objs[i]+'"><label for="'+aobj+'seattype'+objs[i]+'">'+objs[i]+'</label>';
	}
	$(stype).html(ele);
	
	ajaxVehicleShow();
}

function disableEle(aobj,val){
	if(val != '飞机'){
		$("input[name='"+aobj+"time']").removeAttr("checked");
		$("#"+aobj+"span").hide();
	}else{
		$("input[name='"+aobj+"time']").attr("checked","checked");
		$("#"+aobj+"span").show();
	}
}

//用ajax从数据库里把能对应的交通取出来
function ajaxVehicleShow(){
	var cs=new Array(),ds=new Array(),ind=0;;
	var ns="";
	var zTree = $.fn.zTree.getZTreeObj("treefromcityname");
	var nodes = zTree.getCheckedNodes(true);
	nodes.sort(function compare(a, b) { return a.id - b.id; });
	for ( var i = 0, l = nodes.length; i < l; i++) {
		if (nodes[i] && !nodes[i].isParent){ ns+=nodes[i].id+","; cs[ind]=nodes[i]; ds[ind]=nodes[i]; ind++;}
	}
	$("#from_city_count").html("已选择 "+ind+" 个出发地");
	if(ns.length>0){ 
		ns = ns.substring(0, ns.length - 1); 
		var vt = $("input[name='vehiclecome']:checked").val();
		var cst = $("input[name='vehiclecomeseattype']:checked").val();
		var bst = $("input[name='vehiclebackseattype']:checked").val();
		var placeid=$("#placeid").val();
		var backplaceid=$("#backplaceid").val();
		var placename=$("#placename").val();
		var backplacename=$("#backplacename").val();
		var pd={"extqlycustomviehcleprice.fromcityjm":ns
				,"extqlycustomviehcleprice.tocityjm":placeid
				,"extqlycustomviehcleprice.seattype":cst
				,"extqlycustomviehcleprice.traficaltype":vt
				};
		$.ajax({
		   type: "POST",
		   url: "qlycustomviehcleprice!showAjaxCome.do",
		   data: pd,
		   dataType: "JSON",
		   success: function(msg){
			   $(".d-class",$("table.vehicle-come-show")).remove();
			   if(msg.results==1){
				   var os=msg.obj;
				   if(os.length>0){
					   $.each(os,function(i,n){
						   showVehicle(n,"come");
						   $(cs).filter(function(index){
							   if(this.id==n.fromcityjm){
								   cs[index]=null;
								   return false;
							   }
							   return true;
						   });
					   });
				   }
			   }
			   showNodefineVehicle(cs, placename,"come");
		   }
		});
		var pdb={"extqlycustomviehcleprice.fromcityjm":backplaceid
				,"extqlycustomviehcleprice.tocityjm":ns
				,"extqlycustomviehcleprice.seattype":bst
				,"extqlycustomviehcleprice.traficaltype":vt
				};
		$.ajax({
			type: "POST",
			url: "qlycustomviehcleprice!showAjaxBack.do",
			data: pdb,
			dataType: "JSON",
			success: function(msg){
				$(".d-class",$("table.vehicle-back-show")).remove();
				if(msg.results==1){
					   var os=msg.obj;
					   if(os.length>0){
						   $.each(os,function(i,n){
							   showVehicle(n,"back");
							   $(ds).filter(function(index){
								   if(this.id==n.tocityjm){
									   ds[index]=null;
									   return false;
								   }
								   return true;
							   });
						   });
					   }
				   }
				showNodefineVehicle(ds, backplacename, "back");
			}
		});
	}
}

function showNodefineVehicle(cs, placename, aobj){
	 if(cs.length>0){
     	$.each(cs,function(i, n){
     		if(n!=null){
     			var oh=$("#tr-show",$("table.vehicle-come-show")).clone(true);
     			oh.addClass("d-class");
     			$(oh).find("td").css("color","gray");
     			if(aobj=="come"){
     				$(">td:nth-child(1)",oh).html(n.name);
     				$(">td:nth-child(2)",oh).html(placename);
     			}else{
     				$(">td:nth-child(2)",oh).html(n.name);
     				$(">td:nth-child(1)",oh).html(placename);
     			}
     			$(">td:nth-child(7)",oh).html("没有");
     			$(oh).show();
     			$("table.vehicle-"+aobj+"-show").append(oh);
     		}
     	})
     }
}

function showVehicle(o, aobj){
			var oh=$("#tr-show",$("table.vehicle-come-show")).clone(true);
			oh.addClass("d-class");
			$(oh).find("td").css("color","green");
			$(">td:nth-child(1)",oh).html(o.fromcityname);
			$(">td:nth-child(2)",oh).html(o.tocityname);
			$(">td:nth-child(3)",oh).html(o.traficaltype);
			$(">td:nth-child(4)",oh).html(o.price);
			$(">td:nth-child(5)",oh).html(o.childrenprice);
			$(">td:nth-child(6)",oh).html(o.seattype);
			$(oh).show();
			$("table.vehicle-"+aobj+"-show").append(oh);
}

