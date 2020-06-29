<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script src="resources/js/jsp_Script/home.js"></script>

</head>
<body>
<h1>[ 로그인 ]</h1>
<form method="post" action="login" id="log_form">
<table class="border">
	<tr>
		<td>
			ID
		</td>
		<td>
			<input type="text" name="id" id="logId">		
		</td>
	</tr>
	<tr>
		<td>
			Password
		</td>
		<td>
			<input type="password" name="password" id="pw">		
		</td>
	</tr>
	<tr>
		<td colspan="2" class="center">
			<input type="button" value="Login" id = "logButton">
		</td>
	</tr>
	<tr>
		<td colspan="2" class="center">
			<input type="button" value="Sign up" id = "joinButton">
		</td>
	</tr>
</table>
</form>


</body>
</html>