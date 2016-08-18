<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>系统表 用户表</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<script type="text/javascript" src="source/js/custom/qlyuserinfoedit.js"></script>
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
		<div class="form-header"><label class="header-content">系统表 用户表</label></div>
		<s:form name="eidtForm" action="qlyuserinfo!save.do" method="post" theme="simple" >
				
			<table>
				<s:hidden name="qlyuserinfo.userid" id="userid"/>
				<tr>
					<td class="form-label"><label>登录名</label></td>
					<td class="form-content"><s:textfield name="qlyuserinfo.username" id="username" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>密码</label></td>
					<td class="form-content"><s:textfield name="qlyuserinfo.pwd" id="pwd" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>用户类型</label></td>
					<td class="form-content"><s:textfield name="qlyuserinfo.type" id="type" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>别名</label></td>
					<td class="form-content"><s:textfield name="qlyuserinfo.realname" id="realname" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>所属企业</label></td>
					<td class="form-content"><s:textfield name="qlyuserinfo.compid" id="compid" cssClass="txt"></s:textfield></td>
					<td class="form-label"><label>企业名</label></td>
					<td class="form-content"><s:textfield name="qlyuserinfo.cname" id="cname" cssClass="txt"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label>地区编号</label></td>
					<td class="form-content"><s:textfield name="qlyuserinfo.acode" id="acode" cssClass="txt" maxLength="20"></s:textfield></td>
					<td class="form-label"><label>所在地区</label></td>
					<td class="form-content"><s:textfield name="qlyuserinfo.cityname" id="cityname" cssClass="txt"></s:textfield></td>
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
