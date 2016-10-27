<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>线路</title>
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
		<script type="text/javascript" src="source/js/custom/qlylineedit.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
			.form { width: 100%; }
			.form-label { width: 100px; }
			.form table { width: 100%; }
			.form-header { width: 100%; }
			ul li { list-style-type: none }
			.zc ul { width: 25%; float: left; }
			.zc ul li { float: left; line-height: 28px; }
			.txt{height:26px;}
			.e_city{display: inline-block;border: 0px solid red; width: 31%; text-align: left;}
			.e_city .content_wrap{display:inline-block;}
			
		</style>

		<script type="text/javascript">
			var placenamezNodes =[
			<s:iterator value="placenamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:if>
				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
			</s:iterator>];
			var passcityzNodes =placenamezNodes;
		</script>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">线路</label></div>
		<s:form name="eidtForm" id="eidtForm" action="qlyline!save.do" method="post" theme="simple" >
			<s:hidden name="qlyline.id" id="id"/>
			<s:hidden name="qlyline.placeid" id="placeid"/>
			<s:hidden name="qlyline.backplaceid" id="backplaceid"/>
			<s:hidden name="qlyline.journeypic" id="journeypic"/>
			<s:hidden name="qlyline.credate" id="credate"/>
			<s:hidden name="qlyline.enman" id="enman"/>
			<table class="box_t grid-table">
				<tr>
					<td class="form-label"><label>线路名称</label></td>
					<td colspan="3">
							<table>
								<tr>
									<td style="width:75%"><s:textarea name="qlyline.linename" id="linename" cssClass="validate[required] txt" cssStyle="width:100%" rows="1" ></s:textarea></td>
									<td class="form-label"><label>线路编号</label></td>
									<td><s:textfield name="qlyline.lineid" id="lineid" cssClass="txt" maxLength="30"></s:textfield></td>
								</tr>
							</table>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>线路副标题</label></td>
					<td colspan="3" style="text-align: left">
							<s:textarea name="qlyline.titletuniu" id="titletuniu" cssStyle="width:90%" cssClass="txt" maxLength="250" rows="3"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>平台类型</label></td>
					<td colspan="3">
							<s:checkboxlist id="platformtype" cssClass="txt" name="qlyline.platformtype" list="#{'1':'本网站','2':'去哪儿老店','3':'去哪儿新店','4':'途牛平台','5':'携程平台','6':'天猫平台'}" listKey="key" listValue="value" value="%{platformtypelist}" label="" theme="simple10" />
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>路线价格</label></td>
					<td colspan="3">
						<table>
							<tr>
								<td class="form-content"><s:textfield name="qlyline.lineprice" id="lineprice" cssClass="txt"></s:textfield></td>
								<td class="form-label"><label>儿童价格</label></td>
								<td class="form-content"><s:textfield name="qlyline.childprice" id="childprice" cssClass="txt" maxLength="30"></s:textfield></td>
								<td class="form-label"><label>房差</label></td>
								<td class="form-content"><s:textfield name="qlyline.roompricediff" id="roompricediff" cssClass="txt" maxLength="30"></s:textfield></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>参团性质</label></td>
					<td class="form-content"><s:radio id="joinattribute" cssClass="" name="qlyline.joinattribute" list="#{'参团游':'参团游','自由行':'自由行','半自助游':'半自助游','团队游':'团队游','自驾游':'自驾游'}" listKey="key" listValue="value" theme="simple"></s:radio></td>
					<td class="form-label"><label>目的地类型</label></td>
					<td class="form-content"><s:radio id="destinationtype" cssClass="" name="qlyline.destinationtype" list="#{'国内游':'国内游','周边游':'周边游','境外游':'境外游','港奥游':'港奥游','云南游':'云南游'}" listKey="key" listValue="value" theme="simple"></s:radio></td>
				</tr>
				<tr>
					<td colspan="4">
						<div class="e_city">
							<label>去程目的地</label>
							<div class="content_wrap">
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title"><s:textfield name="qlyline.placename" id="placename" cssClass="validate[required] txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="placenamemenuBtn" href="#" onclick="placenameshowMenu(); return false;">选择</a></li>
									</ul>
								</div>
							</div>
							<div id="placenamemenuContent" class="menuContent" style="display:none; position: absolute;">
								<ul id="treeplacename" class="ztree" style="margin-top:0; width:200px;"></ul>
							</div>
						</div>
						<div class="e_city">
							<label>回程出发地</label>
							<div class="content_wrap">
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title"><s:textfield name="qlyline.backplacename" id="backplacename" cssClass="validate[required] txt" maxLength="50" readonly="" style="width:140px;"></s:textfield>&nbsp;<a id="backplacenamemenuBtn" href="#" onclick="backplacenameshowMenu(); return false;">选择</a></li>
									</ul>
								</div>
							</div>
							<div id="backplacenamemenuContent" class="menuContent" style="display:none; position: absolute;">
								<ul id="treebackplacename" class="ztree" style="margin-top:0; width:200px;"></ul>
							</div>
						</div>
						<div class="e_city">
							<label>途经城市</label>
							<div class="content_wrap">
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title"><s:textfield name="qlyline.passcity" id="passcity" cssClass="txt" maxLength="50" readonly="" style="width:240px;"></s:textfield>&nbsp;<a id="passcitymenuBtn" href="#" onclick="passcityshowMenu(); return false;">选择</a></li>
									</ul>
								</div>
							</div>
							<div id="passcitymenuContent" class="menuContent" style="display:none; position: absolute;">
								<ul id="treepasscity" class="ztree" style="margin-top:0; width:200px;"></ul>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>线路主题</label></td>
					<td class="form-content" colspan="3"><s:checkboxlist id="linesubject" cssClass="txt" name="qlyline.linesubject" list="#{'高尔夫':'高尔夫','滑雪':'滑雪','蜜月':'蜜月','自驾':'自驾','红色之旅':'红色之旅','摄影':'摄影','夕阳红':'夕阳红','温泉':'温泉','亲子游':'亲子游','购物':'购物','主题乐园':'主题乐园','潜水':'潜水'}" listKey="key" listValue="value" value="%{linesubjectlist}" label="" theme="simple10" /></td>
				</tr>
				<tr>
				<td class="form-label"><label>经路性质</label></td>
					<td class="form-content" colspan="3"><s:checkboxlist id="attribute" cssClass="txt" name="qlyline.attribute" list="#{'推荐':'推荐','特价':'特价','热卖':'热卖','新品':'新品','团购':'团购','排序':'排序','体验':'体验'}" listKey="key" listValue="value" value="%{attributelist}" label="" theme="simple10" /></td>
				</tr>
				<tr>
					<td class="form-label"><label>节日活动</label></td>
					<td class="form-content" colspan="3"><s:checkboxlist id="festivalact" cssClass="txt" name="qlyline.festivalact" list="#{'春节':'春节','元旦节':'元旦节','清明节':'清明节','劳动节':'劳动节','儿童节':'儿童节','中秋节':'中秋节','国庆节':'国庆节','暑假':'暑假','寒假':'寒假'}" listKey="key" listValue="value" value="%{festivalactlist}" label="" theme="simple10" /></td>
				</tr>
				<tr>
					<td class="form-label"><label>提前报名</label></td>
					<td class="form-content"><s:textfield name="qlyline.sign" id="sign" cssClass="txt" maxLength="10"></s:textfield></td>
					<td class="form-label"><label>线路标准：</label></td>
					<td class="form-content"><s:radio id="linelevel" cssClass="" name="qlyline.linelevel" list="#{'大众':'大众','品质':'品质','高品':'高品','半纯玩':'半纯玩','纯玩':'纯玩'}" listKey="key" listValue="value" theme="simple"></s:radio></td>
				</tr>
				<!-- 
				<tr>
					<td class="form-label"><label>来程大交通</label></td>
					<td class="form-content"><s:radio id="comeviehcle" cssClass="" name="qlyline.comeviehcle" list="#{'飞机':'飞机','火车':'火车','大巴':'大巴','轮船':'轮船'}" listKey="key" listValue="value" theme="simple"></s:radio></td>
					<td class="form-label"><label>来程时间：</label></td>
					<td class="form-content"><s:checkboxlist id="viehcletime" cssClass="txt" name="qlyline.viehcletime" list="#{'上午':'上午','中午':'中午','下午':'下午','晚上':'晚上'}" listKey="key" listValue="value" value="%{viehcletimelist}" label="" theme="simple10" /></td>
				</tr>
				<tr>
					<td class="form-label"><label>来程大交通</label></td>
					<td class="form-content"><s:radio id="backviehcle" cssClass="" name="qlyline.backviehcle" list="#{'飞机':'飞机','火车':'火车','大巴':'大巴','轮船':'轮船'}" listKey="key" listValue="value" theme="simple"></s:radio></td>
					<td class="form-label"><label>回程时间：</label></td>
					<td class="form-content"><s:checkboxlist id="backviehcletime" cssClass="txt" name="qlyline.backviehcletime" list="#{'上午':'上午','中午':'中午','下午':'下午','晚上':'晚上'}" listKey="key" listValue="value" value="%{backviehcletimelist}" label="" theme="simple10" /></td>
				</tr>
				 -->
				<tr>
					<td class="form-label"><label></label></td>
					<td colspan="3" class="zc">
						<ul>
							<li><label>几天：</label></li>
							<li><s:textfield name="qlyline.daynum" id="daynum"  maxLength="20" cssClass="txt"></s:textfield></li>
						</ul>
						<ul>
							<li><label>几晚：</label></li>
							<li><s:textfield name="qlyline.nightnum" id="nightnum"  maxLength="20" cssClass="txt"></s:textfield></li>
						</ul>
						<ul>
							<li><label>自助设计：</label></li>
							<li><s:radio id="isown" cssClass="" name="qlyline.isown" list="#{'0':'不支持','1':'支持'}" listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
						<ul>
							<li><label>状态：</label></li>
							<li><s:radio id="state" cssClass="" name="qlyline.state" list="#{'0':'未审核','1':'已审核'}" listKey="key" listValue="value" theme="simple"></s:radio></li>
						</ul>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>线路主图</label></td>
					<td colspan="3">
					<div style="float:left;">
						<div class="fieldset flash" id="fupjourneypic">
							<span class="legend">上传文件</span>
						</div>
						<div style="padding-left: 5px;">
							<span id="sbphjourneypic"></span>
							<input id="bcjourneypic" type="button" value="取消上传"  onclick="cancelQueue(swfujourneypic);" style="display:none" />
						</div>
					</div>
					<div id="oldjourneypic" class="showview">
						<s:iterator value="ilist" status="stat" id="did">
							<dl  id="olddl<s:property value="#stat.index"/>">
							   <dt><a class="item" href="javascript:void(0);"><img alt="图片" src="${did.vpicfix }" style="width: 120px; height: 82px;"/></a></dt>
							   <dd class="desc"><input type="hidden" name="vfiles" data-type="desc" value="${did.vpicfix }" /></dd>
							   <dd class="delete">
								<a title="删除" href="javascript:delimg('olddl<s:property value="#stat.index"/>','${did.id }','${did.vpicfix }');"><img title="删除" alt="删除"  src="https://source.qunarzz.com/site/images/travel/tts/delete.png" /></a>
							   </dd>
							</dl>
						</s:iterator>
					</div>
					<div id="showjourneypic" class="showview"></div></td>
				</tr>
				<tr>
					<td class="form-label"><label>推荐理由</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyline.commreason" id="commreason" cssClass="txt"  cssStyle="width:100%" rows="6"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>线路特色</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyline.introduce" id="introduce" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>费用包含</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyline.costinfo" id="costinfo" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>费用不含</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyline.costnothave" id="costnothave" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>温馨提示</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyline.attention" id="attention" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>预订须知</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyline.prebook" id="prebook" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>退订说明</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlyline.calbook" id="calbook" cssClass="textarea"  rows="10"></s:textarea>
					</td>
				</tr>
			</table>
					<ckeditor:replace replace="introduce" basePath="ckeditor/" />
					<ckeditor:replace replace="costinfo" basePath="ckeditor/" />
					<ckeditor:replace replace="costnothave" basePath="ckeditor/" />
					<ckeditor:replace replace="attention" basePath="ckeditor/" />
					<ckeditor:replace replace="prebook" basePath="ckeditor/" />
					<ckeditor:replace replace="calbook" basePath="ckeditor/" />
		    <p class="form-opt">
	       		<input type="submit" id="tj" class="btn" value="保 存" />&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="reset" id="cz" class="btn" value="重 置" />
	       </p>
		</s:form>
	</div>
	</div>
	
  </body>
</html>
