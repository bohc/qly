<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>null</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<link rel="stylesheet" href="source/js/zTree3/css/zTreeStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/zTree3/css/ztree_menu_select.css" type="text/css"></link>
		<link id="skin" rel="stylesheet" href="source/js/jbox2.3/Default/jbox.css" ></link>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="source/js/zTree3/core/jquery.ztree.excheck-3.5.min.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-2.3.min.js"></script>
		<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-zh-CN.js"></script>  
		<script type="text/javascript" src="source/js/custom/qlycitytocitypricecustomedit.js"></script>
		<style type="text/css">
	  		.form table{ display:inline-table;}
	  		.form-label{width:80px;}
	  		.txt,.pwd,.textarea{width:340px;} 
	  		.select{width:344px;}
	  		.textarea{height:60px;}
	  		.colspan3{width:840px;}
	  		ul.list{list-style-type: none;}
	  		ul.ztree{height:600px}
	  	</style>
	    <script type="text/javascript">
			var v;
			var fromcitynamezNodes =[
			<s:iterator value="fromcitynamelist" status="stat" id="did">
				<s:if test="#stat.index==0">{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:true}</s:if>
				<s:else>,{ id:"${did.acode}", pId:"${did.pid}", name:"${did.area}", open:false}</s:else>
			</s:iterator>];
		</script>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">线路自定义价添加</label></div>
				<s:hidden name="placeid" id="placeid"/>
				<s:hidden name="backplaceid" id="backplaceid"/>
				<s:hidden name="placename" id="placename"/>
				<s:hidden name="backplacename" id="backplacename"/>
		<s:form name="eidtForm" id="eidtForm" action="qlycitytocitypricecustom!saveprocedu.do" method="post" theme="simple" >
			<table class="grid-table">
				<s:hidden name="qlycitytocitypricecustom.id" id="id"/>
				<s:hidden name="qlycitytocitypricecustom.entime" id="entime"/>
				<s:hidden name="qlycitytocitypricecustom.enman" id="enman"/>
				<s:hidden name="qlycitytocitypricecustom.fromcityjm" id="fromcityjm"/>
				<s:hidden name="qlycitytocitypricecustom.fromcitypy" id="fromcitypy"/>
				<s:hidden name="qlycitytocitypricecustom.lineid" id="lineid"/>
				<s:hidden name="qlycitytocitypricecustom.linename" id="linename"/>
				<tr>
					<td class="form-label" style="width:14px"><label>出<br/>发<br/>城<br/>市<br/>名</label></td>
					<td class="form-content" style="width:150px;vertical-align: top">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title"><s:textfield name="qlycitytocitypricecustom.fromcityname" id="fromcityname" cssClass="txt" maxLength="50" readonly="" style="width:140px;display:none"></s:textfield></li>
								</ul>
							</div>
						</div>
						<div id="fromcitynamemenuContent" class="menuContent">
							<ul id="treefromcityname" class="ztree" style="margin-top:0; width:140px;"></ul>
						</div>
					</td>
					<td class="form-content" style="vertical-align: top">
						<table class="grid-table">
							<tr>
								<td class="form-label"><label>出发交通工具</label></td>
								<td class="form-content"   style="width:170px"><s:radio id="vehiclecome" name="vehiclecome" list="#{'飞机':'飞机','汽车':'汽车','火车':'火车','轮船':'轮船'}" listKey="key" listValue="value" value="'汽车'"  theme="simple"></s:radio></td>
								<td class="form-label" style="width:55px;"><label>出发时间</label></td>
								<td class="form-content"  style="width:210px"><span id="vehiclecomespan" style="display:none"><s:checkboxlist id="vehiclecometime" name="vehiclecometime" list="#{'上午':'上午','中午':'中午','下午':'下午','晚上':'晚上'}" listKey="key" listValue="value" theme="simple"></s:checkboxlist></span></td>
								<td class="form-label"  style="width:55px;"><label>座位级别</label></td>
								<td id="vehiclecomeseatshow" class="form-content"><s:radio id="vehiclecomeseattype" name="vehiclecomeseattype" list="#{'软座':'软座'}" listKey="key" listValue="value" value="'软座'"  theme="simple"></s:radio></td>
							</tr>
							<tr>
								<td class="form-label"><label>返回交通工具</label></td>
								<td><s:radio id="vehicleback" name="vehicleback" list="#{'飞机':'飞机','汽车':'汽车','火车':'火车','轮船':'轮船'}" listKey="key" listValue="value" value="'汽车'"  theme="simple"></s:radio></td>
								<td class="form-label"><label>出发时间</label></td>
								<td><span id="vehiclebackspan" style="display:none"><s:checkboxlist id="vehicle-back-time" name="vehiclebacktime" list="#{'上午':'上午','中午':'中午','下午':'下午','晚上':'晚上'}" listKey="key" listValue="value" theme="simple"></s:checkboxlist></span></td>
								<td class="form-label"><label>座位级别</label></td>
								<td id="vehiclebackseatshow"><s:radio id="vehiclebackseattype" name="vehiclebackseattype" list="#{'软座':'软座'}" listKey="key" listValue="value" value="'软座'"  theme="simple"></s:radio></td>
							</tr>
							<tr>
								<td class="form-label"><label>成人浮动</label></td>
								<td><s:textfield name="qlycitytocitypricecustom.price" id="price" value="0"></s:textfield></td>
								<td class="form-label"><label>儿童浮动</label></td>
								<td><s:textfield name="qlycitytocitypricecustom.childrenprice" id="childrenprice" value="0"></s:textfield></td>
								<td colspan="2">${placename}-->${backplacename}</td>
							</tr>
						</table>
							<table class="grid-table" style="margin-top: 1px;">
								<tr>
									<td><label>来程交通：</label><span id="from_city_count"></span></td>
									<td><label>回程交通</label></td>
								</tr>
								<tr>
									<td>
										<div style="height:440px; overflow:auto; ">
											<table class="grid-table vehicle-come-show">
												<tr id="tr-show" style="display : none">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
											</table>
										</div>
									</td>
									<td>
										<div style="height:440px; overflow:auto; ">
											<table class="grid-table vehicle-back-show"></table>
										</div>
									</td>
								</tr>
							</table>
					</td>
				</tr>
			</table>
		    <p class="form-opt">
	       		<input type="submit" id="tj" class="btn" value="保 存" />&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="reset" id="cz" class="btn" value="重 置" />
	       </p>
		</s:form>
	</div>
	</div>
	
  </body>
</html>
