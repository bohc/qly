<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>线路行程安排</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-2.3.min.js"></script>
		<script type="text/javascript" src="source/js/jbox2.3/jquery.jBox-zh-CN.js"></script>  
		<link id="skin" rel="stylesheet" href="source/js/jbox2.3/Default/jbox.css" ></link>
		<script type="text/javascript" src="source/js/custom/qlylinetraveledit.js"></script>
		<style type="text/css">
	  		.form{width:100%;} .form-label{width:100px;}
	  		.form table{width:100%;} .form-header{width:100%;}
	  		.zc ul{width: 30%;float: left;list-style-type:none}
	  		.zc ul li{float: left;list-style-type:none}
	  		ul.list li{list-style-type: none}
	  		.vehicle-show{width:100%}
	  	</style>
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">线路行程安排</label></div>
		<s:form id="eidtForm" name="eidtForm" action="qlylinetravel!save.do" method="post" theme="simple">
			<table class="grid-table" style="width:100%">
				<s:hidden name="qlylinetravel.id" id="id"></s:hidden>
				<s:hidden name="qlylinetravel.ltid" id="ltid"/>
				<s:hidden name="qlylinetravel.lineid" id="lineid"/>
				<s:hidden name="qlylinetravel.travelcode" id="travelcode"/>
				<s:hidden name="qlylinetravel.viewid" id="viewid"/>
				<s:hidden name="qlylinetravel.viewcontent" id="viewcontent"/>
				<s:hidden name="qlylinetravel.linename" id="linename"/>
				<tr>
					<td class="form-label"><label>线路名称</label></td>
					<td class="form-content" colspan="3" style="width:90%">
						${ qlylinetravel.linename}
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>交通工具类型</label></td>
					<td class="form-content"><s:checkboxlist id="vehicle" cssClass="" name="qlylinetravel.vehicle" list="#{'飞机':'飞机','汽车':'汽车','火车':'火车','巴士':'巴士','轮船':'轮船'}" listKey="key" listValue="value" theme="simple10"></s:checkboxlist></td>
					<td class="form-label"><label>第几天</label></td>
					<td class="form-content"><s:select id="daynum" cssClass="txt" name="qlylinetravel.daynum" list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16'}"></s:select></td>
				</tr>
				<tr>
					<td class="form-label"><label>行程标题</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlylinetravel.traveltitle" id="traveltitle" cssStyle="width:100%;"  rows="2" onclick="selectTravel('qlytravel!selectlist.do')"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>景区选择</label></td>
					<td class="form-content" colspan="3" style="width:90%">
						<input type="button" value="选择" id="btn_view_sel" onclick="selectView('qlyview!selectVewList.do')"/>
						<s:iterator value="mviews" id="vs" status="viewstat">
						<div>${vs.value}<input type="button" id="txt_view_${vs.key}" title="${vs.value}" value="删除" onclick="javascript:dele(this);"/></div>
						</s:iterator>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>交通添加</label></td>
					<td class="form-content" colspan="3" style="width:90%">
						<input type="button" value="选择" id="btn_viehcle_sel" onclick="showAddViehcle()"/>
						<table class="grid-table vehicle-show">
						<tbody>
							<tr id="tr-show" style="display : none">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><s:checkboxlist id="vtime" name="vtime" list="#{'上午':'上午','中午':'中午','下午':'下午','晚上':'晚上'}" listKey="key" listValue="value" value="{'晚上'}" theme="simple10"></s:checkboxlist></td>
								<td><input type="button" value="删除" onclick="javascript:dele(this);"/></td>
							</tr>
						<s:iterator value="mvehicles" id="vi" status="vistat">
							<tr style="display: table-row;" class="v-class">
								<td>${vi.id}</td>
								<td>${vi.fromcityname}</td>
								<td>${vi.tocityname}</td>
								<td>${vi.traficaltype}</td>
								<td>${vi.seattype}</td>
								<td>${vi.price}</td>
								<td>${vi.childrenprice}</td>
								<td>
								<s:if test="#vi.traficaltype=='飞机'">
									<s:checkboxlist id="vtime<s:property value='#vistat.index'/>" name="vtime" list="#{'上午':'上午','中午':'中午','下午':'下午','晚上':'晚上'}" listKey="key" listValue="value" value="plist" theme="simple10"></s:checkboxlist>
								</s:if>
								</td>
								<td><input type="button" value="删除" onclick="javascript:dele(this);"/></td>
							</tr>
						</s:iterator>
						</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td class="form-label"><label>酒店</label></td>
					<td class="form-content"><s:select id="hotel" cssClass="" name="qlylinetravel.hotel" list="#{'客栈':'-------客栈-------','农家院':'------农家院-----','二星或同等酒店':'二星或同等酒店','三星或同等酒店':'三星或同等酒店','四星或同等酒店':'四星或同等酒店','五星或同等酒店 ':'五星或同等酒店','六星或同等酒店 ':'六星或同等酒店','七星或同等酒店 ':'七星或同等酒店','八星或同等酒店 ':'八星或同等酒店','住在交通工具上 ':'住在交通工具上','酒店转机住宿':'酒店转机住宿','自理':'自理'}" listKey="key" listValue="value" theme="simple"></s:select></td>
					<td class="form-label"><label>住宿</label></td>
					<td class="form-content"><s:textfield name="qlylinetravel.stay" id="stay" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>用房特色</label></td>
					<td class="form-content" colspan="3"><s:checkboxlist id="innfeature" cssClass="txt" name="qlylinetravel.innfeature" list="#{'常规酒店':'常规酒店','海景酒店':'海景酒店','湖景酒店':'湖景酒店','温泉酒店':'温泉酒店','特色客栈':'特色客栈','公寓型':'公寓型','别墅型':'别墅型'}" listKey="key" listValue="value" value="%{innfeaturelist}" label="" theme="simple10" /></td>
				</tr>
				<tr>
					<td class="form-label"><label>说明</label></td>
					<td class="form-content" colspan="3" style="width:90%">
							<s:textarea name="qlylinetravel.mark" id="mark" cssClass="textarea"  rows="3" cssStyle="width:100%"></s:textarea>
					</td>
				</tr>
				
				<tr>
					<td class="form-label"><label>行程信息</label></td>
					<td class="form-content" colspan="3" style="width:90%" id="std">
						<table id="stable" style="display:none">
									<tr>
										<td class="form-label"><label>行程标题</label></td>
										<td class="form-content" colspan="3" style="width:90%">
												<div class="zc">
													<ul><li class="tratitle"></li></ul>
													<ul>
														<li><label>[自费：</label></li><li class="fee"></li>
														<li><label>购物：</label></li><li class="shoping"></li>
													</ul>
												</div>
										</td>
									</tr>
									<tr>
										<td class="form-label"><label>行程分类名称</label></td>
										<td class="form-content vtypename"></td>
										<td class="form-label"><label>三餐</label></td>
										<td class="form-content zc">
											<ul><li><label>早餐：</label></li><li class="breakfast"></li></ul>
											<ul><li><label>中餐：</label></li><li class="lunch"></li></ul>
											<ul><li><label>晚餐：</label></li><li class="supper"></li></ul>
										</td>
									</tr>
									<tr>
										<td class="form-label"><label>行程描述</label></td>
										<td class="form-content activity" colspan="3" style="width:90%"></td>
									</tr>
									<tr>
										<td class="form-label"><label>说明提示</label></td>
										<td class="form-content mark" colspan="3" style="width:90%"></td>
									</tr>
								</table>
							<s:iterator value="ilist" status="stat" id="did">
								<table id="o_stable">
									<tr>
										<td class="form-label"><label>行程标题</label></td>
										<td class="form-content" colspan="3" style="width:90%">
												<div class="zc">
													<ul><li>${did.traveltitle}&nbsp;(${did.travelcode})</li></ul>
													<ul>
														<li><label>[自费：</label></li><li>${did.selfexpense}&nbsp;</li>
														<li><label>购物：</label></li><li>${did.shop}]</li>
													</ul>
												</div>
										</td>
									</tr>
									<tr>
										<td class="form-label"><label>行程分类名称</label></td>
										<td class="form-content">${did.vtypename}</td>
										<td class="form-label"><label>行程编码</label></td>
										<td class="form-content zc">
											<ul><li><label>早餐：</label></li><li>${did.breakfast}</li></ul>
											<ul><li><label>中餐：</label></li><li>${did.lunch}</li></ul>
											<ul><li><label>晚餐：</label></li><li>${did.supper}</li></ul>
										</td>
									</tr>
									<tr>
										<td class="form-label"><label>活动安排</label></td>
										<td class="form-content" colspan="3" style="width:90%">${did.activity}</td>
									</tr>
									<tr>
										<td class="form-label"><label>说明提示</label></td>
										<td class="form-content" colspan="3" style="width:90%">${did.mark}</td>
									</tr>
								</table>
							</s:iterator>
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
