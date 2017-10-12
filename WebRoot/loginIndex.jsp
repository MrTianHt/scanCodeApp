<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="lkscn" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginIndex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
      function login(){
          var password = $("#password").val();
          /* $("#password").val(hex_md5(password)); */
          $("#password").val(password);
          $("#login-forms").submit();
      }

</script>
  </head>

  <body>
    <form method="post" action="${lkscn}/user/login.do" id="login-forms">
        	<label for="loginName">用户名</label>
        	<input id="loginName" name="loginName" type="text" placeholder="输入您的用户名" class="span3">
        	<label for="passwore">密码</label>
        	<input id="password" type="password" name="password" placeholder="输入您的密码" class="span3"><br>
        	<input type="submit" class="btn btn-primary btn-block" value="登       录">
	 </form> 
  </body>
</html>
