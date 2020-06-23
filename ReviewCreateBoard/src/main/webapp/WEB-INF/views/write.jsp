<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<title>글을 적어봅시다.</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function checkWriteForm(){
	var title = $('#title').val();
	var content = $('#content').val();
	
	if(title.length < 4){
		alert('제목을 더 길게 입력해 주세요.');
		return false;
	}
	if(content.length < 10){
		alert('내용을 더 길게 입력해 주세요.');
		return false;
	}
	return true;
}
</script>
</head>
<body>
<h1>[ 본 페이지는 글을 작성하는 페이지 입니다.]</h1>
<form action="./write" method="post" enctype="multipart/form-data"
	onsubmit="return checkWriteForm()"><!-- 파일 추가할때 enctype 필요 -->
	<table>
	<tr>
	<td>
	작성자
	</td>
	<td>${log_id}
<input type="hidden" name="id" value="${log_id }"></td>
</tr>
<tr><td>
글의 제목</td><td><input type="text" name="title" id="title" placeholder="제목을 입력해주세요."></td></tr>
<tr><td>글의 내용</td><td>
<textarea rows="20" cols="100" name="content" id="content" placeholder="내용을 입력해 주세요."></textarea></td></tr>
<tr><td>
첨부파일1</td><td><input type="file" id="file" name="upload1" size="30" accept=".gif,.jpg,.jpeg,.png"></td></tr>
<!-- 
<tr><td>첨부파일2</td><td><input type="file" id="file" name="upload2" size="30" accept=".gif,.jpg,.jpeg,.png,.doc,.docx, .hwp, .java, .css"></td></tr>
<tr><td>첨부파일3</td><td><input type="file" id="file" name="upload3" size="30" accept=".gif,.jpg,.jpeg,.png,.doc,.docx, .hwp, .java, .css"></td></tr>
<tr><td>첨부파일4</td><td><input type="file" id="file" name="upload4" size="30" accept=".gif,.jpg,.jpeg,.png,.doc,.docx, .hwp, .java, .css"></td></tr>
<tr><td>첨부파일5</td><td><input type="file" id="file" name="upload5" size="30" accept=".gif,.jpg,.jpeg,.png,.doc,.docx, .hwp, .java, .css"></td></tr>
 -->
<tr>
<td colspan="2" class="center">
<input type="submit" value="저장">
</td>
</tr>
</table>
</form>
</body>
</html>