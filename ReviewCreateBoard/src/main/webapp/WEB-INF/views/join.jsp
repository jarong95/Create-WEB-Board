<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script src="resources/js/jsp_Script/join.js"></script>
</head>

<body>
<h1>[ Dunkey Blog 회원 가입 ]</h1>
<body>
	<form action="signup" method="post" id="joinForm">
		<table class="border">
			<tr>
				<td>ID<div id="check"></div></td>
				<td><input type="text" name="id" id="logId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="password_check" id="password_check"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td>
					<input type="email" name="email" id="email">
				</td>
			</tr>
			<tr>
				<td colspan="5" class="center">
					<input type="button" value="가입" id="joinB">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>