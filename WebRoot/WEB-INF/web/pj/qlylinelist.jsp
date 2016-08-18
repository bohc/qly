<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>线路列表</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="source/css/global.css" type="text/css"/>
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
  	<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
	<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
	<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
	<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
	<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
	<script type="text/javascript" src="source/js/custom/qlylinelist.js"></script>
	<link id="skin" rel="stylesheet" href="source/js/jbox2.3/Default/jbox.css" ></link>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-2.3.min.js"></script>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-zh-CN.js"></script>  
	<script type="text/javascript">
		var fromcityjmzNodes =[
			<s:iterator value="fromcityjmlist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:if>
				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
			</s:iterator>];
		var passcityzNodes=fromcityjmzNodes;
		var placenamezNodes=fromcityjmzNodes;
	</script>
	<style type="text/css">
		.grid-search ul{list-style-type:none}
	  	.grid-search ul li{list-style-type:none}
	</style>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;"></div>
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<div class="grid" style="background-color:#F4F8Fb;">
					<div class="grid-search" style="margin-bottom: 6px;">
							<div style="float:left">
				  				<div style="float:left;">
				  					<div class="content_wrap" >
											<div class="zTreeDemoBackground left">
												<ul class="list">
													<li class="title">调价城市：<s:textfield name="extqlyline.fromcityjm" id="fromcityjm" onclick="fromcityjmshowMenu(); return false;"  readonly="" style="width:100px;"></s:textfield></li>
												</ul>
											</div>
										</div>
										<div id="fromcityjmmenuContent" class="menuContent" style="display:none; position: absolute;">
											<ul id="treefromcityjm" class="ztree" style="margin-top:0; width:200px;"></ul>
										</div>
				  				</div>
				  				<div style="float:left">
				  					<s:form name="editprice" id="editprice">
				  						<label>价格</label>
				  						<input name="pvalue" id="pvalue" class="validate[required,custom[number]]" style="width:40px"/>
				  						<input type="button" id="btn_price_edit" value="调整"/>
				  					</s:form>
				  				</div>
						</div>
						<div style="float:right">
							<div style="float:left;">
			  					<div class="content_wrap" >
										<div class="zTreeDemoBackground left">
											<ul class="list">
												<li class="title">途经地：<s:textfield name="extqlyline.passcity" id="passcity" onclick="passcityshowMenu(); return false;"  readonly="" style="width:80px;"></s:textfield></li>
											</ul>
										</div>
									</div>
									<div id="passcitymenuContent" class="menuContent" style="display:none; position: absolute;">
										<ul id="treepasscity" class="ztree" style="margin-top:0; width:200px;"></ul>
									</div>
			  				</div>
							<div style="float:left;">
									类型：<s:select id="destinationtype" cssClass="" name="extqlyline.destinationtype" list="#{'':'全部','国内游':'国内游','周边游':'周边游','境外游':'境外游','港奥游':'港奥游','云南游':'云南游'}" listKey="key" listValue="value" theme="simple"></s:select>
									编号：<s:textfield id="lineid" name="extqlyline.lineid" cssStyle="width:80px;"></s:textfield>
									平台：<s:select id="platformtype" cssClass="txt" name="extqlyline.platformtype" list="#{'':'全部','1':'本网站','2':'去哪儿老店','3':'去哪儿新店','4':'途牛平台','5':'携程平台','6':'天猫平台'}" listKey="key" listValue="value"  label="" theme="simple" />
			  				</div>
							<div style="float:left;">
			  					<div class="content_wrap" >
										<div class="zTreeDemoBackground left">
											<ul class="list">
												<li class="title">集合地：<s:textfield name="extqlyline.placename" id="placename" onclick="placenameshowMenu(); return false;"  readonly="" style="width:80px;"></s:textfield></li>
											</ul>
										</div>
									</div>
									<div id="placenamemenuContent" class="menuContent" style="display:none; position: absolute;">
										<ul id="treeplacename" class="ztree" style="margin-top:0; width:200px;"></ul>
									</div>
			  				</div>
							<s:submit value="查询" cssClass="btn"></s:submit>
							<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
								<a id="a_add" href="qlyline!edit.do" target="_self"><img title="添加" src="source/img/icon32_32/1096738.png" width="32" height="32"  align="absmiddle" border="0"/></a>
							</ft:exitsfun>
							<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
								<a id="a_del_batch" href="#" target="_self"><img title="删除" src="source/img/icon32_32/1096778.png" width="32" height="32"  align="absmiddle" border="0"/></a>
							</ft:exitsfun>
						</div>
					</div>
					<table class="grid-table">
						<col style="width:6px;"/>
						<col style="width:150px;"/>
						<col style="width:90px;"/>
						<col style="width:65px;"/>
						<col />
						<col style="width:60px;"/>
						<col style="width:60px;"/>
						<col style="width:60px;"/>
						<col />
						<col style="width:50px;"/>
						<col style="width:85px;"/>
						<col style="width:50px;"/>
						<col style="width:40px;"/>
						<thead>
						<tr>
							<th bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" /></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">路线编号</th>
							<th bgcolor="#CDE1F9">路线价格</th>
							<th bgcolor="#CDE1F9">线路名称</th>
							<th bgcolor="#CDE1F9">参团性质</th>
							<th bgcolor="#CDE1F9">目的地类型</th>
							<th bgcolor="#CDE1F9">集合地名称</th>
							<th bgcolor="#CDE1F9">经路性质</th>
							<th bgcolor="#CDE1F9">状态</th>
							<th bgcolor="#CDE1F9">添加时间</th>
							<th bgcolor="#CDE1F9">自助设计</th>
							<th bgcolor="#CDE1F9">自定城市</th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="ilist" status="stat" id="did">
						<tr>
							<td><input name="ck" type="checkbox" value="${did.id}" tid="${did.id}" fstat="${did.eid }"/></td>
							<td>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="javascript:setLinePriceDiff('qlyregulatepricecondition!list.do?extqlyregulatepricecondition.lineid=${did.id}');" class="text"><img title="按时间段调整价格价格" src="source/img/icon16_16/1060995.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="javascript:selectLine('qlyline!listselect.do?extqlyline.eid=${did.id }','${did.id}');" class="text"><img title="复制城市价格" src="source/img/icon16_16/1061043.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="javascript:totravel('${did.id}','${did.lineid}','${did.linename}');" class="text"><img title="行程" src="source/img/icon16_16/552601.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="javascript:toCustomPrice('${did.id}','${did.linename}','${did.placeid }','${did.backplaceid }','${did.placename }','${did.backplacename }');" class="text"><img title="出发城市" src="source/img/icon16_16/1061035.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlyline!copy.do?qlyline.id=${did.id}&currentpage=${currentpage}" class="text"><img title="拷贝" src="source/img/icon16_16/1060900.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="qlyline!edit.do?qlyline.id=${did.id}&currentpage=${currentpage}" class="text"><img title="编辑" src="source/img/frame/pencil.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2303">
									<a href="qlyline!delline.do?qlyline.id=${did.id}&currentpage=${currentpage}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.lineid}</td>
							<td>${did.lineprice}</td>
							<td>${did.linename}</td>
							<td><s:if test="joinattribute=='参团游'">参团游</s:if><s:elseif test="joinattribute=='自由行'">自由行</s:elseif><s:elseif test="joinattribute=='团队游'">团队游</s:elseif><s:elseif test="joinattribute=='自驾游'">自驾游</s:elseif></td>
							<td><s:if test="destinationtype=='国内游'">国内游</s:if><s:elseif test="destinationtype=='周边游'">周边游</s:elseif><s:elseif test="destinationtype=='境外游'">境外游</s:elseif><s:elseif test="destinationtype=='港奥游'">港奥游</s:elseif><s:elseif test="destinationtype=='云南游'">云南游</s:elseif></td>
							<td>${did.placename}</td>
							<td>${did.attribute}</td>
							<td><s:if test="state==0">未审核</s:if><s:elseif test="state==1">已审核</s:elseif></td>
							<td><s:date name="credate" format="yyyy-MM-dd"/></td>
							<td><s:if test="isown==0">不支持</s:if><s:elseif test="isown==1">支持</s:elseif></td>
							<td>${did.customcount}</td>
						</tr>
						</s:iterator>
						</tbody>
					</table>

					 <div class="grid-page"><%@ include file="/inc/adminpage.jsp" %></div>
				</div>
			</s:form>
		 </div>
	</div>
	<s:form id="sendform">
		<s:hidden name="extqlycitytocitypricecustom.lineid" cssClass="lineid"></s:hidden>
		<s:hidden name="extqlycitytocitypricecustom.linename" cssClass="linename"></s:hidden>
		<s:hidden name="backplaceid" cssClass="backplaceid"></s:hidden>
		<s:hidden name="placeid" cssClass="placeid"></s:hidden>
		<s:hidden name="backplacename" cssClass="backplacename"></s:hidden>
		<s:hidden name="placename" cssClass="placename"></s:hidden>
	</s:form>
	<s:form id="totravelform">
		<s:hidden name="extqlylinetravel.ltid" cssClass="ltid"></s:hidden>
		<s:hidden name="extqlylinetravel.lineid" cssClass="lineid"></s:hidden>
		<s:hidden name="extqlylinetravel.linename" cssClass="linename"></s:hidden>
	</s:form>
	<s:form id="copyCustomPriceform">
		<s:hidden name="extqlycitytocitypricecustom.lineid" cssClass="lineid"></s:hidden>
		<s:hidden name="extqlycitytocitypricecustom.tolineid" cssClass="tolineid"></s:hidden>
		<s:hidden name="extqlycitytocitypricecustom.pricedif" cssClass="pricedif"></s:hidden>
	</s:form>
 </body>
</html>
