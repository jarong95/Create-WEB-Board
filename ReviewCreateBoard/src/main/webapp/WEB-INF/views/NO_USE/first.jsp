<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script>
$(function() {
	$('#writeButton').on('click', writeform);
});

function writeform(){
	location.href="write";
}

function recovery(){
	$.ajax({
		url : 'recovery'
		, type : 'post'
		, success : function(e){
			alert('복구되었습니다.');
			location.href="./";
		}
	});
}


</script>
</head>

<body>
	<a href="logout">로그아웃</a><br>
	<a href="update">회원정보 수정</a><br>
	<c:if test="${delete_id == null}">
		<a href="delete">계정 탈퇴</a><br>
	</c:if>
	<c:if test="${delete_id}">
		<a href="javascript:recovery()">계정 복구</a><br>
	</c:if>
<table>
	<tr class="title">
		<th>
			번호
		</th>
		<th>
			제목
		</th>
		<th>
			좋아요
		</th>
		<th>
			아이디
		</th>
		<th>
			등록일
		</th>
		<th>
			삭제
		</th>
	</tr>
	<c:forEach items="${list_board}" var="list">
		<tr>
			<td>${list.boardnum}</td>
			<td><a href="read?bnum=${list.boardnum}">${list.title}</a></td>
			<td>${list.id}</td>
			<td>${list.likenum}</td>
			<td>${list.inputdate}</td>
			<td><c:if test="${log_id == list.id}"><a href="deleteBoard?bnum=${list.boardnum }">삭제</a></c:if></td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="3" class="center"><input type="button" value="글쓰기" id="writeButton"></td>
		</tr>
</table>
</body>
</html>
