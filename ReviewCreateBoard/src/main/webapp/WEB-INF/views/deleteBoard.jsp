<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script>
//로그인 폼 검사
$(function (){
	$('#deleteB').on('click',check);
});
function check(){
	var pw = $('#pw').val();
	
	if(pw.length < 3 || pw.length > 10){
		alert('비밀번호 입력하시오.');
		return;
	}
	$('#deleteCheck').submit();
	opener.location.href="./";
	self.close();
}

</script>
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