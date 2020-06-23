<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<html>
<head>
<title>Home</title>
<script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
<link href="resources/css/header.css" rel="stylesheet" />
<style type="text/css">
* {box-sizing: border-box;}
body {
	font-family: Verdana, sans-serif;
	height: 627.200;
	width: 1503.200;}


.header .searchbar a {
	color: #aaa;
	font-weight: 700;
	margin: 0 15px 0 15px;
	font-size: 1.4em;
}
/* -------------------------------그룹피드임ㅁ미당-------------------------------- */
/* ----------------------------글쓰기 폼 css끝------------------------------- */
* {
  outline:none;
	border:none;
	margin:0px;
	padding:0px;
}

#paper {
	color:#333;
	font-size:20px;
}
#margin {
	margin-left:12px;
	margin-bottom:20px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	-o-user-select: none;
	user-select: none; 
}
#text {
	overflow:hidden;
	background-color:#FFF;
	color:#33333;
	font-weight:normal;
	font-size:18px;
	resize:none;
	padding: 30 30 30 30;
	background-color: #f6f6f6;
}
.replytext {
	overflow:hidden;
	background-color:#FFF;
	color:#33333;
	font-weight:normal;
	font-size:13px;
	resize:none;
	padding: 10 10 10 10;
	background-color: #f6f6f6;
}
#title {
	background-color:transparent;
	border-bottom:3px solid #f6f6f6;
	border-top:none;
	border-left:none;
	border-right:none;
	color:#33333;
	font-size:20px;
	height:28px;
	font-weight:bold;
	width:320px;
}

#wrapper {
	width:800;
	height:auto;
	margin-left:auto;
    margin-right: 0px;
	margin-top:24px;
	margin-bottom:100px;
}
.btn-default {
  background: #007fff none repeat scroll 0 0;
  border: 1px solid #007fff;
  border-radius: 20px;
  color: #ffffff;
  height: 40px;
  padding: 0 22px;
  transition: all 0.3s ease 0s;
  font-size: 16px;
  font-weight: 500;
  text-transform: uppercase;
  padding-bottom: 11px;
  padding-top: 11px;
  text-decoration: none;
  float: right;
  margin-top: 10px;
}
.btn-default:hover {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: 1px solid #007fff;
  color: #007fff;
}
/* ----------------------------글쓰기 폼 css끝------------------------------- */
.feedall {
  position: relative;
  max-width: 800px;
  margin: auto;
  top:200;
  z-index: 1;
}
.item {
  display: flex;
  max-width: 1000px;
  margin: 0 auto;
}
/* -------------------------------그룹피드임ㅁ미당-------------------------------- */

/* 그룹 글에 댓글 등등 보여주기욤 */

.reDel {
	text-align: right;
	width: 20;
}

.boardDel {
	text-align: right;
	width: 20;
}

.boardUpdate {
	text-align: right;
	width: 20;
}

.writedate {
	text-align: right;
	font-size: 12px;
	width: 100;
}

.flip{
  padding: 5px;
  text-align: center;
  background-color: #007fff;
  color: white;
}
.flip2{
  padding: 5px;
  text-align: center;
  background-color: #e100ff;
  color: white;
}
.panel, .panel2{
  padding: 5px;
  text-align: center;
  background-color: #f6f6f6;
  display: none;
}


</style>


</head>
<body>

<!-- ///////////////////////////////////////////피드임!!!!!!/////////////////////////////////////////////// -->
<!-- ///////////////////글쓰기 js임////////////// -->

<!-- ///////////////////////////////////헤더임///////////////////////////////////////// -->
<div class="header" id="header">
	<div class="headerIn">
		<h1 style="left: 40;"><a href="./"><img alt="" src="resources/img/archivelogo22.png" height="100"></a></h1>
	
		<!-- sign in , sign up , menu-->
			<table class="navbar" style="width: 1050px"><tr><td style="width: 802px;">
			<ul class="menu">
			</ul>				<td>

					<nav class="main-nav2" style="margin-right: 0px;width: 200;float: right;">
						<table><tr><td style="width: 88px;padding-left: 0px;padding-bottom: 0px;padding-right: 0px;padding-top: 0px;">
							<ul class="userBar">
								<li style="margin-left: 0px;">
									<a href="logout" style="width:102; margin-left: 0px;margin-right: 0px;padding-left: 16px;border-top-width: 0px;" class="ub-logout">Log out</a>
								</li>
							</ul>
						</td><td style="width: 93px;padding-left: 0px;padding-bottom: 0px;padding-right: 0px;padding-top: 0px;">
							<ul class="userBar">
								<li style="margin-left: 0px;">
									<a href="update" style="margin-left: 0px;margin-right: 0px;padding-left: 16px;border-top-width: 0px;" class="ub-lib" >Account</a>
								</li>
							</ul>
						</td>
						<c:if test="${delete_id == null}">
							<td style="width: 93px;padding-left: 0px;padding-bottom: 0px;padding-right: 0px;padding-top: 0px;">
							<ul class="userBar">
								<li style="margin-left: 0px;">
									<a href="delete" style="margin-left: 0px;margin-right: 0px;padding-left: 16px;border-top-width: 0px;" class="ub-lib" >Delete</a>
								</li>
							</ul>
							</td>
						</c:if>
						<c:if test="${delete_id != null}">
							<td style="width: 93px;padding-left: 0px;padding-bottom: 0px;padding-right: 0px;padding-top: 0px;">
							<ul class="userBar">
								<li style="margin-left: 0px;">
									<a href="javascript:recovery()" style="margin-left: 0px;margin-right: 0px;padding-left: 16px;border-top-width: 0px;" class="ub-lib" >Recovery</a>
								</li>
							</ul>
							</td>
						</c:if>
						</tr></table>
						<script type="text/javascript">
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
					</nav>
				</td>
			</tr>
		</table>
	</div>
</div>
		
<!-- ///////////////////글쓰기 js임////////////// -->
<script type="text/javascript">
$(document).ready(function(){
	  $('#title').focus();
	    $('#text').autosize();
	});
	
function writeForm(){
	var title_board = $('#title').val();
	var content_board = $('#text').val();
	if(title_board.length < 3 || title_board.length > 21){
		alert('제목을 4글자 이상 20글자 이하로 써주세요.');
		return;
	}
	if(content_board.length < 3 || title_board.length > 500){
		alert('제목을 4글자 이상 300글자 이하로 써주세요.');
		return;
	}
	$('#paper').submit();
	
}
</script>
<div class="feedall">
<!-- /////////////////////////////////글쓰기이므!!!!!!!!!!!!!/////////////////////////// -->

<div id="wrapper">
	<form id="paper" method="post" action="write" style="width: 800px" enctype="multipart/form-data">
		<input type="hidden" name="boardnum" value=""  id="updateBoardVO">
		<input type="hidden" name="fileContinue" value="" id="continueFile">
		<input type="hidden" name="id" value="${log_id }">
		<div id="margin">Title: <input id="title" type="text" name="title" style="border-radius:0px;"></div>
		<textarea placeholder="Enter something funny." id="text" name="content" rows="4" style="overflow: hidden; word-wrap: break-word; resize: none; height: 160px; width: 800px;"></textarea>  		
		<div id="updateFile"><input type="file" id="file" name="upload1" size="30" accept=".gif,.jpg,.jpeg,.png"></div>
		<a id="sendBoard" class="btn-default" href="javascript:writeForm()"><i class="flaticon-shop"></i>send</a>
	</form>
</div>

<!-- /////////////////////////////////글쓰기끝!!!!!!!!!!!!/////////////////////////// -->
<!-- //////////////////그룹 글 보여주기욤//////////////// -->

<script> 
$(document).ready(function(){
  $(".flip").click(function(event){
	  var e = $(event.target).attr('id');
	  var i = 'panel'+e;
    $('#'+i).slideToggle("slow");
  });
  $(".flip2").click(function(event){
	  var e = $(event.target).attr('id');
	  var i = 'panel2'+e;
    $('#'+i).slideToggle("slow");
  });
});
var fileText = $('#updateFile').html();
function checkUpdateBoard(bnum, file){
	if(file.length != 0){
		 if(!confirm("파일을 삭제하거나 수정 추가를 하려면 확인을 첨부파일을 유지하려면 취소를 눌러주세요.?")){
			 $('#updateFile').html('');
			 $('#continueFile').val('yes');
		 }
		 else{
			 $('#continueFile').val('no');
		 }
	}
	else{
		 $('#continueFile').val('no');
	 }

	$.ajax({
		url : 'updateBoard'
		, type : 'get'
		, data : {bnum : bnum}
		, dataType : 'json'
		, success : function(e){
			$('#title').val(e.title);
			$('#text').val(e.content);
			$('#updateBoardVO').val(bnum);
			$('#sendBoard').attr('href','javascript:updateBoard_send()');
			$('#paper').attr('action', 'updateBoard');
			$('#title').focus();
		}
	}); 
}
function deleteBoard(num){
	window.open('deleteBoard?bnum='+num,'js4','width=600, height=300, left=0, top=0');
}

function updateBoard_send(){
	var title_board = $('#title').val();
	var content_board = $('#text').val();
	if(title_board.length < 3 || title_board.length > 21){
		alert('제목을 4글자 이상 20글자 이하로 써주세요.');
		return;
	}
	if(content_board.length < 3 || title_board.length > 500){
		alert('제목을 4글자 이상 300글자 이하로 써주세요.');
		return;
	}
	$('#paper').submit();
}

/* function updateBoard_send(num){
	var title_board = $('#title').val();
	var content_board = $('#text').val();
	if(title_board.length < 3 || title_board.length > 21){
		alert('제목을 4글자 이상 20글자 이하로 써주세요.');
		return;
	}
	if(content_board.length < 3 || title_board.length > 500){
		alert('제목을 4글자 이상 300글자 이하로 써주세요.');
		return;
	}
	$.ajax({
		url : 'groupUpdate'
		, type : 'post'
		, data : {title : title_board, content : content_board, bnum_group : num}
		, success : function(){
			alert('글이 수정되었습니다.');
			location.href = "";
		}
	});
} */
function likeC(num, i){
	$.ajax({
		url : 'like'
		, type : 'get'
		, data : {bnum : num}
		, dataType : 'text'
		, success : function(e){
			$('#likeCheck'+i).html(e);			
		}
	})
}

</script>
<!-- //////////////////그룹 글 보여주기욤//////////////// -->



 <c:if test="${list_board != null && list_board.size() >= 1}">
  <c:forEach begin="0" end="${list_board.size()-1}" var="i">

<div class="item" style="margin: 20 0 20 0">
  <div class="image" style="margin: 0 20 0 0">
    <div style="width: 120">
      
      <table>
      <tr><td align="center">
      <img src="resources/img/ps.png" width="80"/>
      </td></tr>
      <tr><td align="center">
      <span><b>${list_board[i].id}</b></span>
      </td></tr>
      <tr><td align="center">
      <span id="likeCheck${i}"><b>${list_board[i].likenum}</b></span>
      </td></tr>      
      <tr><td align="center">
      <a href="javascript:likeC(${list_board[i].boardnum}, ${i})"><img alt="" src="resources/img/devsign3_10527.jpg" width="15"></a>
      </td></tr>      
      </table>
    </div>
  </div>
  <div class="details">
    <div>
      <table><tr><td style="width: 650px">
      <table style="width: 650px"><tr>
      <td><h3>${list_board[i].title}</h3></td>
      <td class="writedate">${list_board[i].inputdate }</td>
      <td><td class="boardUpdate" width="20">
      
      <c:if test="${list_board[i].id.equals(log_id)}">
      <a href="javascript:checkUpdateBoard(${list_board[i].boardnum}, '${list_board[i].filesavename }')"><img alt="" src="resources/img/penpen.png" width="15"></a>
      </c:if>
      </td><!-- 수정 -->
      
      
      <td><td class="boardDel" width="20">
      <c:if test="${list_board[i].id.equals(log_id)}">
      <a href="javascript:deleteBoard(${list_board[i].boardnum })"><img alt="" src="resources/img/xxxx.png" width="15"></a>
      </c:if>
      </td><!-- 삭제 -->
      
      
      </tr></table>
      <c:if test="${list_board[i].filename != null}">
      	<div class="flip2" id="fp2${i}">첨부파일이 있습니다. 보시겠습니까?</div>
      	<div id="panel2fp2${i}" class="panel2">
		<a href="download?boardnum=${list_board[i].boardnum}&number=1">
		<img alt="${list_board[i].filename}" src="imgfile/${list_board[i].filesavename}" title="${list_board[i].filename}">
		</a>
		</div>
	  </c:if>
      <p style="width: 650">
      	 ${list_board[i].content}
      </p>
      <div class="flip" id="fp${i}">Click see all comments</div>
      <div id="panelfp${i}" class="panel">
      	
      	<c:forEach items="${reply}" var="listRE">
      	<c:if test="${list_board[i].boardnum == listRE.bnum}">
      	 
      	<table style="margin: 10"><tr><td width="130" valign="top">
      	<b>${listRE.id}</b>
      	</td><td width="500">
		${listRE.content }
      	</td>
      	<td class="reDel" width="20">
      	
      	<c:if test="${listRE.id == log_id }">
      	
      	<a href="deleteRE?replynum=${listRE.replynum}">
      	<img alt="" src="resources/img/xxxx.png" width="15"></a>
      	
      	</c:if>
      	
      	</td></tr></table>
      	
      	</c:if>
      	</c:forEach>
      	
      </div>
      <form id="reply_form${i}" method="POST" action="reply" style="margin-top: 20">
      	<input type="hidden" name="bnum" value="${list_board[i].boardnum }">
		<textarea placeholder="Comments.." id="replytext${i}" class="replytext" name="content" rows="4" style="overflow: hidden; word-wrap: break-word; resize: none; height: 50px; width: 650px;"></textarea>  
		<a class="btn-default" href="javascript:reply_submit(${i})"></i>comments</a>
	  </form>
      </td></tr></table>
     
    </div>
  </div>
  <br>
</div>
</c:forEach>
</c:if>

</div>

<script type="text/javascript">
function reply_submit(num){
	var re = $('#replytext'+num).val();
	if(re.length < 1 || re.length > 300){
		alert('댓글은 1글자 이상 300글자 미만으로 입력해  주세요.');
		return;
	}
	$('#reply_form'+num).submit();
}

</script>


</body>
</html>
