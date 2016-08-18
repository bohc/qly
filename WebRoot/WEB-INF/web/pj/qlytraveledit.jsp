<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>线路行程安排</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlytraveledit.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:100%;} .form-label{width:100px;text-align: right;}
	  		.form table{width:100%;} .form-header{width:100%;}
	  		.zc ul{width: 30%;float: left;list-style-type:none}
	  		.zc ul li{float: left;list-style-type:none}
	  	</style>
	    
		<script type="text/javascript">
			var vtypenamezNodes =[
			<s:iterator value="vtypenamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.vtypecode}", pId:"${did.vparcode}", name:"${did.vtypename}", open:true}</s:if>
				<s:else>,{ id:"${did.vtypecode}", pId:"${did.vparcode}", name:"${did.vtypename}", open:false}</s:else>
			</s:iterator>];
		</script>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">线路行程安排</label></div>
		<s:form name="eidtForm" action="qlytravel!save.do" method="post" theme="simple" >
			<s:hidden name="qlytravel.ltid" id="ltid"/>
			<s:hidden name="qlytravel.vtypecode" id="vtypecode"/>
			<s:hidden name="qlytravel.enman" id="enman"/>
			<s:hidden name="qlytravel.entime" id="entime"/>
			<table class="grid-table">
				<col width="60px"/>
				<col></col>
				<col width="60px"/>
				<col></col>
				<tr>
					<td class="form-label"><label>行程标题</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlytravel.traveltitle" id="traveltitle" onpropertychange="this.style.height=this.scrollHeight+'px';"  oninput="this.style.height=this.scrollHeight+'px';" style="overflow:hidden;width:100%"  rows="1"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>行程分类名称</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlytravel.vtypename" id="vtypename" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="vtypenamemenuBtn" href="#" onclick="vtypenameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="vtypenamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treevtypename" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
					<td class="form-label"><label>行程编码</label></td>
					<td class="form-content"><s:textfield name="qlytravel.travelcode" id="travelcode" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>三餐</label></td>
					<td colspan="3" class="zc">
						<ul>
							<li><label>早餐：</label></li>
							<li><s:radio id="breakfast" cssClass="" name="qlytravel.breakfast" list='#{"自行安排":"自行安排","提供":"提供"}' listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
						<ul>
							<li><label>中餐：</label></li>
							<li><s:radio id="lunch" cssClass="" name="qlytravel.lunch" list='#{"自行安排":"自行安排","提供":"提供"}' listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
						<ul>
							<li><label>晚餐：</label></li>
							<li><s:radio id="supper" cssClass="" name="qlytravel.supper" list='#{"自行安排":"自行安排","提供":"提供"}' listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>自费：</label></td>
					<td class="form-content" colspan="3"><s:textfield id="selfexpense" cssClass="txt" name="qlytravel.selfexpense" cssStyle="width:60%"/><span>注：用逗号隔开</span></td>
				</tr>
				<tr>
					<td class="form-label"><label>购物：</label></td>
					<td class="form-content" colspan="3"><s:textfield id="shop" cssClass="txt" name="qlytravel.shop" cssStyle="width:60%"/><span>注：用逗号隔开</span></td>
				</tr>
				<tr>
					<td class="form-label"><label>是否启用：</label></td>
					<td colspan="3" class="zc">
						<s:radio id="isuse" cssClass="" name="qlytravel.isuse" list="#{'0':'启用','1':'锁定 '}" listKey="key" listValue="value" theme="simple"></s:radio></li>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>活动安排</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlytravel.activity" id="activity" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>说明提示</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlytravel.mark" id="mark" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
			</table>
					<ckeditor:replace replace="activity" basePath="ckeditor/" />
					<ckeditor:replace replace="mark" basePath="ckeditor/" />
		    <p class="form-opt">
	       		<input type="submit" id="tj" class="btn" value="保 存" />&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="reset" id="cz" class="btn" value="重 置" />
	       </p>
		</s:form>
	</div>
	</div>
	
  </body>
</html>
