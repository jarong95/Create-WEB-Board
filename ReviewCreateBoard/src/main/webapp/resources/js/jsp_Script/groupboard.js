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
//////////////////글쓰기 js임////////////// 
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


//////////////////그룹 글 보여주기욤////////////////
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


function reply_submit(num){
	var re = $('#replytext'+num).val();
	if(re.length < 1 || re.length > 300){
		alert('댓글은 1글자 이상 300글자 미만으로 입력해  주세요.');
		return;
	}
	$('#reply_form'+num).submit();
}