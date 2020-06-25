<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>
<title>[ ${blogId}님의 블로그 ]</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function() {
	$('#check').on('click', likeC);
	$('#ub').on('click', updateForm);
	$('#db').on('click', deleteForm);
});

function likeC(){
	var num = $('#bnum_read').val();
	$.ajax({
		url : 'like'
		, type : 'get'
		, data : {bnum : num}
		, dataType : 'text'
		, success : successForm
		, error : function(e){
			console.log(e);
		}
	})
}

function successForm(e){
	$('#likeCheck').html(e);
}

function updateForm(){
	var num = $('#bnum_read').val();
	location.href="updateBoard?bnum="+num;
}
function deleteForm(){
	var num = $('#bnum_read').val();
	if(confirm('삭제하시겠습니까?')){
		location.href="deleteBoard?bnum="+num;
	}
}
</script>
</head>
<body>
<input type="hidden"  value="${board.boardnum }" id="bnum_read">
	<table>
		<tr>
			<td class="title">ID</td><td>${board.id}</td>
		</tr>
		<tr>
			<td class="title">title</td><td>${board.title}</td>
		</tr>
		<tr>
			<td class="title">content</td><td>${board.content}</td>
		</tr>
		<tr>
			<td colspan="2">file</td>
		</tr>
		<c:if test="${board.filename != null}">
		<tr>
			<td colspan="2"><a href="download?boardnum=${board.boardnum}&number=1">
			<img alt="${board.filename}" src="imgfile/${board.filesavename}" title="${board.filename}" width="200" height="200">
			</a></td>
		</tr>
		</c:if>
		<%-- 
		<c:if test="${board.file2_title != null}">
		<tr>
			<td colspan="2"><a href="download?boardnum=${board.boardnum}&number=2">${board.file2_title }</a></td>
		</tr>
		</c:if>
		<c:if test="${board.file3_title != null}">
		<tr>
			<td colspan="2"><a href="download?boardnum=${board.boardnum}&number=3">${board.file3_title }</a></td>
		</tr>
		</c:if>
		<c:if test="${board.file4_title != null}">
		<tr>
			<td colspan="2"><a href="download?boardnum=${board.boardnum}&number=4">${board.file4_title }</a></td>
		</tr>
		</c:if>
		<c:if test="${board.file5_title != null}">
		<tr>
			<td colspan="2"><a href="download?boardnum=${board.boardnum}&number=5">${board.file5_title }</a></td>
		</tr>
		</c:if>
		 --%>
		<tr>
			<td colspan="2" class="center">
				<h3>추천수 <div id="likeCheck">${board.likenum}</div> <input type="button" value="추천하기" id="check"></h3> 
			</td>
		</tr>
		<c:if test="${log_id == board.id }">
		<tr>
			<td colspan="2" class="center">
				<input type="button" value="수정" id="ub"> <input type="button" value="삭제" id="db">
			</td>
		</c:if>
	</table>
	<form action="reply" method="post" id="replyForm">
		<input type="hidden" name="bnum" value="${board.boardnum }">
		<textarea rows="5" cols="100" name="content" id="replycontent"></textarea>
		<input type="button" id="replyButton" value="댓글달기">
	</form>
	
	<script type="text/javascript">
		$(function(){
			$('#replyButton').on('click', checkReply);
		});
		function checkReply(){
			var content = $('#replycontent').val();
			if(content.length == 0){
				alert('댓글을 입력하려면 더 길게 써주세요.');
				return;
			}
			$('#replyForm').submit();
		}
	</script>
	
	<c:if test="${reply != null }">
	<table>
		<c:forEach items="${reply}" var="list">
		<tr>
			<div id="update${list.replynum }">
				<td>${list.id}</td><td>${list.content}</td>
			</div>
			<c:if test="${list.id == log_id }">
			<td><a href="javascript:updateReply(${list.replynum}, ${board.boardnum}, '${list.content }')">수정</a></td>
			<td><a href="deleteRE?replynum=${list.replynum}&bnum=${board.boardnum}">삭제</a></td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	
	<script type="text/javascript">
		function updateReply(rnum, bnum, cont){
			var c = 'update'+rnum;
			var i = '<form action="updateRE" method="post" id="updateForm"><input type="hidden" name="id" value="${log_id}">';
			i += '<input type="hidden" name="replynum" value="';
			i += rnum;
			i += '"><input type="hidden" name="bnum" value="';
			i += bnum;
			i += '"><textarea rows="5" cols="100" name="content" id="updateContent">';
			i += cont;
			i += '</textarea><input type="button" value="수정하기" id="updateButton"></form>';
			
			$('#'+c).html(i);
			
			$('#updateButton').on('click', function(){
				var content = $('#updateContent').val();
				if(content.length == 0){
					alert('댓글을 입력하려면 더 길게 써주세요.');
					return;
				}
				$('#updateForm').submit();
			});
		}
	</script>
</body>
</html>
