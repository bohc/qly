<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>登录页面</title>
	<script type="text/javascript" src="source/js/jquery1.8.2/core/jquery-1.8.2.min.js"></script>
	<style type="text/css">
		*{padding:0px; margin:0px; font-family:微软雅黑; font-size:13px; color:#000333;}
		body{width:100%; height:100%;}
		#input ul{padding:0px; margin:0px; list-style-type:none;}
		#input ul li{height:35px; line-height:35px; width:363px;}
		#input ul .form-content{width:260px; height:30px;}
		#input label{display:inline-block; text-align:right; font-size:18px; color:#084572; width:80px; padding-right:20px;}
		#tip span{color:red;;}
		.btn{background:url(source/img/login/login.png); width:58px; height:84px; border:0px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
		  //initFrame();
		 $('#submit').mouseover(function () {
				$(this).css("background", "url(./source/img/login/login-press.png)").css("cursor","pointer");
			}).mouseout(function () {
				$(this).css("background", "url(./source/img/login/login.png)").css("cursor","");
			});
		});
		
		$(window).resize(function() {
			initFrame();
		});
		
		function initFrame(){
			var height = ($(document.body).height() - 616) / 2;
			$("#login").css('margin-top',height);
		}
	</script>
</head>
 <body onload="initFrame()">
	<div class="wraper" style="background:url(source/img/login/body-bg.png) repeat; text-align:center; height:100%; overflow:auto;">
	<div id="login" class="login" style="background:url(source/img/login/background.png) repeat; width:1007px; height:616px; margin:0 auto;">
		<div style="height:209px; line-height:209px; background:url(source/img/login/title.png) no-repeat 93.5px 0px;"></div>
		<div style="background:url(source/img/login/login-background.png) no-repeat; width:1007px; height:334px;">
			<div style="width:517px; height:120px; float:right;">
				<s:form action="login!loginv.do" id="loginform" name="loginform" theme="simple">
				<div id="input" style="padding:0px; margin:0px; margin-top:115px; float:left; width:363px; text-align:left;">
					<ul>
						<li>
							<label>用户名 </label><s:textfield id="vusername" name="vusername" cssClass="form-content" />
						</li>
						<li>
							<label>密码 </label><s:password id="vpassword" name="vpassword" cssClass="form-content" />
						</li>
						<li style="padding-top:5px;">
								<span style="float:left;"><label>验证码 </label></span>
								<span style="float:left;"><s:textfield id="rerand" name="rerand" cssClass="form-content" cssStyle="width:120px;" /></span>
								<span style="float:left; margin-left:10px;"><img id="imgrand" src="randImage?l=<%=System.currentTimeMillis() %>" class="form-content" style="width:120px; cursor:pointer; border:0px;" alt="点击更换" title="点击更换" /></span>
						</li>
						<li>
							<span id="tip" style="color:red; font-weight:bold; font-size:14px;"><s:actionerror/></span>
					    </li>
					</ul>
				</div>
				<div style="padding:0px; margin:0px; margin-top:140px; float:left; width:129px; text-align:left; margin-left:15px;">
					<input type="submit" id="submit" class="btn" value="" />
				</div>
				</s:form>
				<div style="clear:both;"></div>
			</div>
			<div style="clear:both;"></div>
		</div>
	  </div>
	</div>
 </body>
</html>