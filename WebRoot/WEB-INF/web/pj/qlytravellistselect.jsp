<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>标准</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="source/css/global.css" type="text/css">
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
	<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
	<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
	<script type="text/javascript">
		$(document).ready(function(){
			initGrid();
			$.fn.zTree.init($("#treevtypename"), settingvtypename, vtypenamezNodes);
		});
		
		//页面加载时，对Table表格移动鼠标行变色操作，通用方法
		function initGrid() {
	    	$('table.grid-table').each(function () {
				var _this = $(this);
				//设置偶数行和奇数行颜色
				_this.find("tr:odd").css("background-color", "#DEE9F7");
				_this.find("tr:even").css("background-color", "#FFFFFF");
				//鼠标移动隔行变色hover用法关键
				_this.find("tr:not(:first)").hover(function () {
					$(this).attr("bColor", $(this).css("background-color")).css("background-color", "#FFF4CE").css("cursor", "pointer");
					$(this).children("td:first").addClass("grid-arrow");
				}, function () {
					$(this).css("background-color", $(this).attr("bColor"));
					$(this).children("td:first").removeClass("grid-arrow");
				});
			});

			$("#toggleAll").bind("click", function() { 
				if(this.checked){
					$("input[name='ck']").attr("checked", true);
				}else{
					$("input[name='ck']").attr("checked", false);
				}
			});
		}
		
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
					.getSelectedNodes(), v = "";
			nodes.sort(function compare(a, b) {
				return a.id - b.id;
			});
			for ( var i = 0, l = nodes.length; i < l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0)
				v = v.substring(0, v.length - 1);
			var cityObj = $("#vtypename");
			cityObj.attr("value", v);
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
	</script>
	<script type="text/javascript">
		var vtypenamezNodes =[
		<s:iterator value="vtypenamelist" status="stat" id="did">
			<s:if test="#stat.index==0">{ id:"${did.vtypecode}", pId:"${did.vparcode}", name:"${did.vtypename}", open:true}</s:if>
			<s:else>,{ id:"${did.vtypecode}", pId:"${did.vparcode}", name:"${did.vtypename}", open:true}</s:else>
		</s:iterator>];
	</script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form name="listForm" action="" theme="simple">
				<div class="grid">
					<div class="grid-search">
							<div class="content_wrap">
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
										行程分类名称:<s:textfield name="extqlytravel.vtypename" id="vtypename" cssClass="txt" maxLength="50" readonly="" style="width:140px;" onclick="vtypenameshowMenu(); return false;"></s:textfield>
										行程标题:<s:textfield name="extqlytravel.traveltitle"/>
										自费:<s:textfield name="extqlytravel.selfexpense"/>
										购物:<s:textfield name="extqlytravel.shop"/>
										<s:submit value="查询" cssClass="btn"></s:submit>
										</li>
									</ul>
								</div>
							</div>
							<div id="vtypenamemenuContent" class="menuContent" style="display:none; position: absolute;">
								<ul id="treevtypename" class="ztree" style="margin-top:0; width:200px;"></ul>
							</div>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" ></th>
							<th bgcolor="#CDE1F9">行程分类名称</th>
							<th bgcolor="#CDE1F9">行程编码</th>
							<th bgcolor="#CDE1F9">行程标题</th>
							<th bgcolor="#CDE1F9">酒店</th>
							<th bgcolor="#CDE1F9">自费</th>
							<th bgcolor="#CDE1F9">购物</th>
							<th bgcolor="#CDE1F9">是否启用</th>
							<th bgcolor="#CDE1F9">创建时间</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" tid="${did.ltid}" tname="${did.traveltitle}"
						traveltitle="${did.traveltitle }" travelcode="${did.travelcode }" selfexpense="${did.selfexpense }"
						shop="${did.shop }" vtypename="${did.vtypename }" breakfast="${did.breakfast }" lunch="${did.lunch }"
						supper="${did.supper }"  activity="<ft:replace str="${did.activity}"/>" mark="<ft:replace str="${did.mark}"/>"></td>
							<td>${did.vtypename}</td>
							<td>${did.travelcode}</td>
							<td>${did.traveltitle}</td>
							<td><s:if test="hotel=='一星'">一星</s:if><s:elseif test="hotel=='二星'">二星</s:elseif><s:elseif test="hotel=='三星'">三星</s:elseif><s:elseif test="hotel=='四星'">四星</s:elseif><s:elseif test="hotel=='五星'">五星</s:elseif></td>
							<td><s:if test="selfexpense=='自费'">有</s:if><s:elseif test="selfexpense=='没有'">无</s:elseif></td>
							<td><s:if test="shop=='购物'">有</s:if><s:elseif test="shop=='没有'">无</s:elseif></td>
							<td><s:if test="isuse==0">启用</s:if><s:elseif test="isuse==1">锁定</s:elseif></td>
							<td><s:date name="entime" format="yyyy-MM-dd"/></td>
						</tr>
						</s:iterator>
					</table>
					 <div class="grid-page"><%@ include file="/inc/adminpage.jsp" %></div>
				</div>
			</s:form>
		 </div>
	</div>
 </body>
</html>
