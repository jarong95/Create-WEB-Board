<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script src="resources/js/jsp_Script/deleteBoard.js"></script>
</head>
<body>
	<form action="deleteBoard" method="post" id="deleteCheck">
	PASSWORD
	<input type="password" name="password" id="pw">
	<input type="hidden" name="bnum" value="${bnum}"> 
	<input type="button" value="삭제" id="deleteB">
	</form>
</body>
</html>