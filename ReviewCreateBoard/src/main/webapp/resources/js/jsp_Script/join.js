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
