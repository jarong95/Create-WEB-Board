$(function(){
	$('input:radio').on('click',function(){
		if($('#delF').is(':checked')){
			var a = '<input type="file" id="file" name="upload1" size="30" accept=".gif,.jpg,.jpeg,.png">';
			$('#fileText').html(a);
		}
		else{
			$('#fileText').html('');
		}
	});
});
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