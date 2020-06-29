//로그인 폼 검사
$(function (){
	$('#logButton').on('click',check);
	$('#joinButton').on('click',checkJ);	
});
function check(){
	var id = $('#logId').val();
	var pw = $('#pw').val();
	
	if(id.length < 3 || id.length > 10){
		alert('ID 입력하시오.');
		return;
	}
	if(pw.length < 3 || pw.length > 10){
		alert('비밀번호 입력하시오.');
		return;
	}
	$('#log_form').submit();
}
function checkJ(){
	location.href="signup";
}