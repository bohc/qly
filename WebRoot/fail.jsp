<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录失败页面</title>
  </head>
  
  <body>
  <form action="logout.do" name="form1">
  </form>
    <script type="text/javascript">
    	alert("请你重新登录");
    	document.form1.target="_parent"
    	document.form1.submit();
    </script>
  </body>
</html>
