function submits(){
	if($("#eidtForm").validationEngine('validate')){
		$("#tj,#cz").hide();return true;
	}else{
		$("#tj,#cz").show();return false;
	}
}
	function fromcitynameshowMenu() {
		var cityObj = $("#fromcityname");
		var cityOffset = $("#fromcityname").offset();
		$("#fromcitynamemenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", fromcitynameonBodyDown);
	}
	function fromcitynamehideMenu() {
			$("#fromcitynamemenuContent").fadeOut("fast");
			$("body").unbind("mousedown", fromcitynameonBodyDown);
		}
	function fromcitynameonBodyDown(event) {
		if (!(event.target.id == "fromcitynamemenuBtn" || event.target.id == "fromcitynamemenuContent" || $(event.target).parents("#fromcitynamemenuContent").length>0)) {
			fromcitynamehideMenu();
		}
	}
function fromcitynameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treefromcityname"),
	nodes = zTree.getSelectedNodes(),
	v = "",k="";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		k += nodes[i].id + ",";}
	if (v.length > 0 ) {v = v.substring(0, v.length-1);
		k = k.substring(0, k.length-1);}
	$("#fromcityname").attr("value", v);
	$("#fromcityjm").attr("value", k);
	fromcitynamehideMenu();
}
	function tocitynameshowMenu() {
		var cityObj = $("#tocityname");
		var cityOffset = $("#tocityname").offset();
		$("#tocitynamemenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", tocitynameonBodyDown);
	}
	function tocitynamehideMenu() {
			$("#tocitynamemenuContent").fadeOut("fast");
			$("body").unbind("mousedown", tocitynameonBodyDown);
		}
	function tocitynameonBodyDown(event) {
		if (!(event.target.id == "tocitynamemenuBtn" || event.target.id == "tocitynamemenuContent" || $(event.target).parents("#tocitynamemenuContent").length>0)) {
			tocitynamehideMenu();
		}
	}
function tocitynameonClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treetocityname"),
	nodes = zTree.getSelectedNodes(),
	v = "",k="";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		k += nodes[i].id + ",";}
	if (v.length > 0 ) {v = v.substring(0, v.length-1);
		k = k.substring(0, k.length-1);}
	$("#tocityname").attr("value", v);
	$("#tocityjm").attr("value", k);
	tocitynamehideMenu();
}

	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check) alert("只能选择节点...");
		return check;
	}
	var settingfromcityname = {view: {dblClickExpand: false},data: {simpleData: {enable: true}},callback: {beforeClick: beforeClick,onClick: fromcitynameonClick}};
	var settingtocityname = {view: {dblClickExpand: false},data: {simpleData: {enable: true}},callback: {beforeClick: beforeClick,onClick: tocitynameonClick}};
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
	$.fn.zTree.init($("#treefromcityname"), settingfromcityname, fromcitynamezNodes);
	$.fn.zTree.init($("#treetocityname"), settingtocityname, tocitynamezNodes);
});
