<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/funtag.tld" prefix="ft" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	    <title>调整价格条件</title>
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
	    <script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
		<link rel="stylesheet" href="source/css/global.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/js/Validation-2.6.4/css/validation.css" type="text/css" ></link>
		<link rel="stylesheet" href="source/js/jquery1.8.2/themes/base/jquery.ui.all.css" type="text/css"></link>
		<link rel="stylesheet" href="source/js/jquery1.8.2/themes/base/jquery.ui.datepicker.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/custom/qlyregulatepriceconditionedit.js"></script>
		<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.core.js"></script>
		<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript" src="source/js/jquery1.8.2/ui/jquery.ui.datepicker.min.js"></script>
  		<script type="text/javascript" src="source/js/Validation-2.6.4/languages/validation-zh_CN.js"></script>
		<script type="text/javascript" src="source/js/Validation-2.6.4/validation.js"></script>
		<style type="text/css">
	  		.form{width:100%;}
	  		.form table{width:100%;} 
	  		.form-header{width:100%;}
	  		.form-label{width:140px;}
	  		.form-content{width:200px;}
	  	</style>
	    
	</head>
  	<body>
	<div class="wraper">
		<div id="form" class="form">
		<div class="form-header"><label class="header-content">调整价格条件</label></div>
		<s:form name="eidtForm" id="eidtForm" action="qlyregulatepricecondition!save.do" method="post" theme="simple" >
			<table class="form table">
				<s:hidden name="qlyregulatepricecondition.id" id="id"/>
				<s:hidden name="qlyregulatepricecondition.lineid" id="lineid"/>
				<tr>
					<td><label>调整分类</label></td>
					<td><s:select name="qlyregulatepricecondition.difftype"  id="difftype" list="#{'成人价':'成人价','儿童价':'儿童价','房差':'房差'}" listKey="key" listValue="value" ></s:select></td>
					<td><label>调整价格</label></td>
					<td><s:textfield name="qlyregulatepricecondition.price" id="price" ></s:textfield></td>
					<td><label>开始时间</label></td>
					<td><s:textfield name="qlyregulatepricecondition.startdate" id="startdate" ></s:textfield></td>
					<td><label>结束时间</label></td>
					<td><s:textfield name="qlyregulatepricecondition.enddate" id="enddate" ></s:textfield></td>
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
