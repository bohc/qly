<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>景点</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<script type="text/javascript" src="source/js/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="source/js/swfupload/fileprogress.js"></script>
		<script type="text/javascript" src="source/js/swfupload/swfupload.queue.js"></script>
		<script type="text/javascript" src="source/js/swfupload/handlers.js"></script>
		<link rel="stylesheet" href="source/js/swfupload/spicshow.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlyviewedit.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:100%;} .form-label{width:100px;}
	  		.form table{width:100%;} .form-header{width:100%;}
	  		.zc ul{width: 20%;float: left;list-style-type:none}
	  		.zc ul li{float: left;list-style-type:none}
	  		.zc2 ul{width: 33%;float: left;list-style-type:none}
	  		.zc2 ul li{float: left;list-style-type:none}
	  		.zc3 ul{width: 50%;float: left;list-style-type:none}
	  		.zc3 ul li{float: left;list-style-type:none}
	  	</style>
	    
		<script type="text/javascript">
			var typenamezNodes =[
			<s:iterator value="typenamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.vtypecode}", pId:"${did.vparcode}", name:"${did.vtypename}", open:true}</s:if>
				<s:else>,{ id:"${did.vtypecode}", pId:"${did.vparcode}", name:"${did.vtypename}", open:false}</s:else>
			</s:iterator>];
			var citynamezNodes =[
			<s:iterator value="citynamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:if>
				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
			</s:iterator>];
		</script>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">景点</label></div>
		<s:form name="eidtForm" action="qlyview!save.do" method="post" theme="simple" enctype ="multipart/form-data">
				
			<table class="grid-table">
				<s:hidden name="qlyview.id" id="id"/>
				<s:hidden name="qlyview.typeid" id="typeid"/>
				<s:hidden name="qlyview.cityid" id="cityid"/>
				<s:hidden name="qlyview.viewpic" id="viewpic"/>
				<s:hidden name="qlyview.cdate" id="cdate"/>
				<s:hidden name="qlyview.userid" id="userid"/>
				<s:hidden name="qlyview.username" id="username"/>
				<s:hidden name="qlyview.videourl" id="videourl"/>
				<s:hidden name="qlyview.clickcount" id="clickcount"/>
				<tr>
					<td class="form-label"><label>景点名称</label></td>
					<td colspan="3" class="zc3">
						<ul>
							<li><s:textarea name="qlyview.viewname" id="viewname" onpropertychange="this.style.height=this.scrollHeight+'px';"  oninput="this.style.height=this.scrollHeight+'px';" style="overflow:hidden;width:350px"  rows="1"></s:textarea></li>
						</ul>
						<ul>
							<li><label>&nbsp;&nbsp;&nbsp;&nbsp;景区级别：</label></li>
							<li><s:radio id="level" cssClass="" name="qlyview.level" list="#{'一星':'一星','二星':'二星','三星':'三星','四星':'四星','五星 ':'五星 '}" listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>分类名称</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlyview.typename" id="typename" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="typenamemenuBtn" href="#" onclick="typenameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="typenamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treetypename" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
					<td class="form-label"><label>城市名称</label></td>
					<td class="form-content">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlyview.cityname" id="cityname" cssClass="txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="citynamemenuBtn" href="#" onclick="citynameshowMenu(); return false;">选择</a></li>
								</ul>
							</div>
						</div>
						<div id="citynamemenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treecityname" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>景区类型</label></td>
					<td class="form-content"><s:checkboxlist id="viewtype" cssClass="txt" name="qlyview.viewtype" list="#{'景区类':'景区类','演出类':'演出类','娱乐类':'娱乐类'}" listKey="key" listValue="value" value="%{viewtypelist}" label="" theme="simple4" /></td>
					<td class="form-label"><label>适宜人群</label></td>
					<td class="form-content"><s:checkboxlist id="suitable" cssClass="txt" name="qlyview.suitable" list="#{'学生':'学生','上班族':'上班族','老人 ':'老人 '}" listKey="key" listValue="value" value="%{suitablelist}" label="" theme="simple4" /></td>
				</tr>
				<tr>
					<td class="form-label"><label></label></td>
					<td colspan="3" class="zc2">
						<ul>
							<li><label>景点编号：</label></li>
							<li><s:textfield name="qlyview.viewid" id="viewid" maxLength="30"></s:textfield></li>
						</ul>
						<ul>
							<li><label>联系方式：</label></li>
							<li><s:textfield name="qlyview.linkway" id="linkway"></s:textfield></li>
						</ul>
						<ul>
							<li><label>排序：</label></li>
							<li><s:textfield name="qlyview.ordernum" id="ordernum"></s:textfield></li>
						</ul>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label></label></td>
					<td colspan="3" class="zc">
						<ul>
							<li><label>状态：</label></li>
							<li><s:radio id="state" cssClass="" name="qlyview.state" list="#{'0':'显示','1':'隐藏'}" listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
						<ul>
							<li><label>是否推荐：</label></li>
							<li><s:radio id="isrecommend" cssClass="" name="qlyview.isrecommend" list="#{'0':'一般','1':'推荐'}" listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
						<ul>
							<li><label>国内/国外：</label></li>
							<li><s:radio id="inorout" cssClass="" name="qlyview.inorout" list="#{'0':'国内','1':'国外'}" listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
						<ul>
							<li><label>是否热点：</label></li>
							<li><s:radio id="ishot" cssClass="" name="qlyview.ishot" list="#{'0':'一般','1':'热点'}" listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
						<ul>
							<li><label>首页推荐：</label></li>
							<li><s:radio id="istop" cssClass="" name="qlyview.istop" list="#{'0':'一般','1':'首页'}" listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>副标题</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyview.subviewname" id="subviewname" onpropertychange="this.style.height=this.scrollHeight+'px';"  oninput="this.style.height=this.scrollHeight+'px';" style="overflow:hidden;width:100%"  rows="1"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>景区地址</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyview.adress" id="adress" onpropertychange="this.style.height=this.scrollHeight+'px';"  oninput="this.style.height=this.scrollHeight+'px';" style="overflow:hidden;width:100%"  rows="1"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>简要描述</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyview.vdescribe" id="vdescribe" onpropertychange="this.style.height=this.scrollHeight+'px';"  oninput="this.style.height=this.scrollHeight+'px';" style="overflow:hidden;width:100%"  rows="6"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>景区介绍</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyview.introduce" id="introduce" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>交通指南</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyview.traffic" id="traffic" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>购票须知</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyview.ticketnotice" id="ticketnotice" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>特色购物</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyview.speshop" id="speshop" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>特色美食</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyview.spefood" id="spefood" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>景区图片</label></td>
					<td colspan="3">
					<div style="float:left;">
						<div class="fieldset flash" id="fupviewpic">
							<span class="legend">上传文件</span>
						</div>
						<div style="padding-left: 5px;">
							<span id="sbphviewpic"></span>
							<input id="bcviewpic" type="button" value="取消上传"  onclick="cancelQueue(swfuviewpic);" style="display:none" />
						</div>
					</div>
					<div id="oldviewpic" class="showview">
						<s:iterator value="ilist" status="stat" id="did">
							<dl  id="olddl<s:property value="#stat.index"/>">
							   <dt><a class="item" href="javascript:void(0);"><img alt="图片" src="${did.picurl }" style="width: 120px; height: 82px;"/></a></dt>
							   <dd class="desc"><input type="hidden" name="vfiles" data-type="desc" value="${did.picurl }" /></dd>
							   <dd class="delete">
								<a title="删除" href="javascript:delimg('olddl<s:property value="#stat.index"/>','${did.vpid }','${did.picurl }');"><img title="删除" alt="删除"  src="https://source.qunarzz.com/site/images/travel/tts/delete.png" /></a>
							   </dd>
							</dl>
						</s:iterator>
					</div>
					<div id="showviewpic" class="showview"></div></td>
				</tr>
				<tr>
					<td class="form-label"><label>景点视频</label></td>
					<td colspan="3">
					<div style="float:left;">
						<div class="fieldset flash" id="fupvideourl">
							<span class="legend">上传文件</span>
						</div>
						<div style="padding-left: 5px;">
							<span id="sbphvideourl"></span>
							<input id="bcvideourl" type="button" value="取消上传"  onclick="cancelQueue(swfuvideourl);" style="display:none" />
						</div>
					</div>
					<div id="oldvideoull" class="showview"></div>
					<div id="showvideourl" class="showview"></div></td>
				</tr>
			</table>
			<ckeditor:replace replace="introduce" basePath="ckeditor/" />
			<ckeditor:replace replace="traffic" basePath="ckeditor/" />
			<ckeditor:replace replace="ticketnotice" basePath="ckeditor/" />
			<ckeditor:replace replace="speshop" basePath="ckeditor/" />
			<ckeditor:replace replace="spefood" basePath="ckeditor/" />
		    <p class="form-opt">
	       		<input type="submit" id="tj" class="btn" value="保 存" />&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="reset" id="cz" class="btn" value="重 置" />
	       </p>
		</s:form>
	</div>
	</div>
	
  </body>
</html>
