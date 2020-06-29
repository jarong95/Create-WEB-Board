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