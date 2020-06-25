<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
</head>
<body>
	<form action="update" method="post">
	<table>
	<tr><td>
		ID</td><td> <input type="text" name="id" id="logId" readonly="readonly" value="${log_id}"></td></tr>
	<tr><td>PASSWORD</td><td><input type="password" name="password" id="password"></td></tr>
		<tr><td>PASSWORD_check</td><td><input type="password" name="password_check" id="password_check"></td></tr>
		<tr><td>NAME</td><td><input type="text" name="name" id="name" value="${user.name}"></td></tr>
		<tr><td>EMAIL</td><td><input type="email" name="email" id="email" value="${user.email}"></td></tr>
		<tr><td colspan="2" class="center"><input type="submit" value="가입"></tr></tr>
	</table>
	</form>
</body>
</html>