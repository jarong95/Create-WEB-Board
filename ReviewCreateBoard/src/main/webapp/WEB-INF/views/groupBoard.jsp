<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<html>
<head>
<title>Home</title>
<script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
<link href="resources/css/header.css" rel="stylesheet" />
<link href="resources/css/backCSS.css" rel="stylesheet" />
<script type="text/javascript" src="resources/js/jsp_Script/groupboard.js"></script>
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
					</nav>
				</td>
			</tr>
		</table>
	</div>
</div>
		

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



 <c:if test="${list_board != null && list_board.size() >= 1}">
  <c:forEach begin="0" end="${list_board.size()-1}" var="i" >

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

</body>
</html>
