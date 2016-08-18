<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>null</title>
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
	<link id="skin" rel="stylesheet" href="source/js/jbox2.3/Default/jbox.css" ></link>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-2.3.min.js"></script>
	<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-zh-CN.js"></script>
	<style type="text/css">
		.zTreeDemoBackground ul{list-style-type:none}
	  	.zTreeDemoBackground ul li{list-style-type:none}
	</style>
	<script type="text/javascript">
		var v;
		var fromcitynamezNodes =[
		<s:iterator value="fromcitynamelist" status="stat" id="did">
			<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:if>
			<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
		</s:iterator>];
		var fromcityjmzNodes=fromcitynamezNodes;
	</script>
	<script type="text/javascript" src="source/js/custom/qlycitytocitypricecustomlist.js"></script>
</head>
 <body>
 	<div class="wraper">
  		<div style="padding:5px; background-color:#F4F8Fb;">
			<s:form id="listForm" name="listForm" action="" theme="simple">
				<s:hidden name="extqlycitytocitypricecustom.lineid"></s:hidden>
				<s:hidden name="extqlycitytocitypricecustom.linename"></s:hidden>
				<div class="grid" style="width:100%">
					<div class="grid-search">
						<div style="float:left">
				  				<div style="float:left;">
				  					<div class="content_wrap" >
											<div class="zTreeDemoBackground left">
												<ul class="list">
													<li class="title">调价城市：<s:textfield name="extqlyline.fromcityjm" id="editfromcityjm" onclick="fromcityjmshowMenu(); return false;"  readonly="" style="width:300px;"></s:textfield></li>
												</ul>
											</div>
										</div>
										<div id="fromcityjmmenuContent" class="menuContent" style="display:none; position: absolute;">
											<ul id="treefromcityjm" class="ztree" style="margin-top:0; width:200px;"></ul>
										</div>
				  				</div>
				  				<div style="float:left">
				  						<label>价格</label>
				  						<input name="pvalue" id="pvalue" class="validate[required,custom[number]]" style="width:40px"/>
				  						<input type="button" id="btn_price_edit" value="调整"/>
				  				</div>
						</div>
						<div class="content_wrap" style="float:right">
								<div class="zTreeDemoBackground left">
									<ul class="list" style="width:100%">
										<li class="title">
											出发城市名:<s:textfield name="extqlycitytocitypricecustom.fromcityname" id="extfromcityname" onclick="fromcitynameshowMenu(); return false;" cssClass="txt" readonly="readonly" style="width:140px;"></s:textfield>
											<s:hidden name="extqlycitytocitypricecustom.fromcityjm" id="extfromcityjm" />
											<s:submit value="查询" cssClass="btn"></s:submit>
											<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
												<a id="a_add" href="javascript:toCustomPrice();" target="_self"><img title="添加" src="source/img/icon32_32/1096738.png" width="32" height="32"  align="absmiddle" border="0"/></a>
											</ft:exitsfun>
											<ft:exitsfun funlist="${session.sb.listFun}" funid="2301">
												<a id="a_del_batch" href="#" target="_self"><img title="删除" src="source/img/icon32_32/1096778.png" width="32" height="32"  align="absmiddle" border="0"/></a>
											</ft:exitsfun>
											<a id="a_back" href="qlyline!list.do" target="_self"><img title="返回" src="source/img/icon32_32/1134581.png" width="32" height="32"  align="absmiddle" border="0"/></a>
										</li>
									</ul>
								</div>
						</div>
						<div id="fromcitynamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="fromcityname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</div>
					
					<table class="grid-table">
						<tr><th width="6px" bgcolor="#CDE1F9"><input id="toggleAll" type="checkbox" ></th>
							<th bgcolor="#CDE1F9">操作</th>
							<th bgcolor="#CDE1F9">线路名称</th>
							<th bgcolor="#CDE1F9">出发城市名</th>
							<th bgcolor="#CDE1F9">成人浮动费用</th>
							<th bgcolor="#CDE1F9">儿童浮动费用</th>
							<th bgcolor="#CDE1F9">交通工具类型</th>
						</tr>
						<s:iterator value="ilist" status="stat" id="did">
						<tr><td width="6px"><input name="ck" type="checkbox" value="${did.id}" tid="${did.id}"/></td>
							<td width="40px;">
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2303">
									<a href="qlycitytocitypricecustom!del.do?qlycitytocitypricecustom.id=${did.id}&qlycitytocitypricecustom.lineid=${did.lineid}" class="text" onclick="return confirm('是否确定删除删除后将不可恢复')"><img title="删除" src="source/img/frame/cancel.png" width="16" height="16"  align="middle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.linename}</td>
							<td>${did.fromcityname}</td>
							<td style="width:110px;">
								<input name="price" id="price<s:property value="#stat.index"/>" style="width:80px" value="${did.price }" type="text"/>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="javascript:changePrice('${did.id}','price<s:property value="#stat.index"/>');" class="text"><img title="编辑" src="source/img/icon16_16/1061074.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td style="width:110px;">
								<input name="childrenprice" id="childrenprice<s:property value="#stat.index"/>" style="width:80px" value="${did.childrenprice }" type="text"/>
								<ft:exitsfun funlist="${session.sb.listFun}" funid="2302">
									<a href="javascript:changeChildrenPrice('${did.id}','childrenprice<s:property value="#stat.index"/>');" class="text"><img title="编辑" src="source/img/icon16_16/1061074.png" width="16" height="16"  align="absmiddle" border="0"/></a>
								</ft:exitsfun>
							</td>
							<td>${did.traficaltype}</td>
						</tr>
						</s:iterator>
					</table>

					 <div class="grid-page"><%@ include file="/inc/adminpage.jsp" %></div>
				</div>
			</s:form>
		 </div>
	</div>
	<s:form id="sendform">
		<s:hidden name="extqlycitytocitypricecustom.lineid" id="lineid"></s:hidden>
		<s:hidden name="extqlycitytocitypricecustom.linename" id="linename"></s:hidden>
		<s:hidden name="placeid" id="placeid"></s:hidden>
		<s:hidden name="backplaceid" id="backplaceid"></s:hidden>
		<s:hidden name="placename" id="placename"></s:hidden>
		<s:hidden name="backplacename" id="backplacename"></s:hidden>
	</s:form>
 </body>
</html>
