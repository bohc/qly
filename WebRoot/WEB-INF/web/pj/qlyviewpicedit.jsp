<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>景点图片</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<link rel="stylesheet" href="source/js/jquery1.8.2/themes/base/jquery.ui.all.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/jquery1.8.2/themes/base/jquery.ui.datepicker.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlyviewpicedit.js"></script>
		<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.core.js"></script>
		<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.datepicker.min.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:100%;} .form-label{width:100px;}
	  		.form table{width:100%;} .form-header{width:100%;}
	  	</style>
	    
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">景点图片</label></div>
		<s:form name="eidtForm" action="qlyviewpic!save.do" method="post" theme="simple" >
				
			<table>
				<s:hidden name="qlyviewpic.vpid" id="vpid"/>
				<tr>
					<td class="form-label"><label>景点编号</label></td>
					<td class="form-content"><s:textfield name="qlyviewpic.viewid" id="viewid" cssClass="txt" maxLength="30"></s:textfield></td>
					<td class="form-label"><label>用户编号</label></td>
					<td class="form-content"><s:textfield name="qlyviewpic.userid" id="userid" cssClass="txt" maxLength="30"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>图片排列顺序</label></td>
					<td class="form-content"><s:textfield name="qlyviewpic.ordernum" id="ordernum" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>图片路径</label></td>
					<td class="form-content"><s:textfield name="qlyviewpic.picurl" id="picurl" cssClass="txt" maxLength="50"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>图片标题</label></td>
					<td class="form-content"><s:textfield name="qlyviewpic.title" id="title" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>创建日期</label></td>
					<td class="form-content"><s:textfield name="qlyviewpic.credate" id="credate" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>说明</label></td>
					<td class="form-content"><s:textfield name="qlyviewpic.mark" id="mark" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label></label></td>
					<td class="form-content"></td>
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
