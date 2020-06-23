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
<script>

//가입폼 확인
$(function (){
	$('#logId').on('keyup', checkID);
	$('#joinB').on('click',joinCheck);
});
function checkID(){
	var id = $('#logId').val();

	$.ajax({
		url : 'idcheck'
		, type : 'get'
		, dataType : 'text'
		, data : {id : id}
		, success : function(e){
			if(e == 1){
				$('#check').html('<p style="color : red">중복</p>');
			}
			else{
				$('#check').html('');
			}
		}
	});
}

function joinCheck(){
	var id = $('#logId').val();
	var pw = $('#password').val();
	var pw2 = $('#password_check').val();
	var name = $('#name').val();
	var mail = $('#email').val();
	
	if(id.length < 3 || id.length > 10){
		alert('ID는 3자에서 10자로 입력하시오.');
		return;
	}
	if(pw.length < 3 || pw.length > 10){
		alert('비밀번호는 3자에서 10자로 입력하시오.');
		return;
	}
	if(pw != pw2){
		alert('비밀번호가 일치하지 않습니다.');
		return;
	}
	if(name.length < 1){
		alert('이름을 반드시 입력하시오.');
		return;
	}
	if(mail.length == 0){
		alert('메일을 입력해주세요.');
		return;
	}
	if($('#check').length > 1){
		alert('중복');
		return;
	}
	$('#joinForm').submit();
}

</script>
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