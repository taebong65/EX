



$(document).ready(function(){
	

	$(".regist").on("click", function(){
		if($("#name").val()==""){
			alert("이름을 입력해주세요.");
			$("#name").focus();
			return false;
		}
		if($("#id").val()==""){
			alert("아이디를 입력해주세요.");
			$("#id").focus();
			return false;
		}
		if($("#pw").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#pw").focus();
			return false;
		}
		
	});

	
	
	
	
	$('#pwc').blur(function(){
		
		if($('#pw').val() == $('#pwc').val()){
			alert("비밀번호가 일치합니다.");
		}
		
		if($('#pw').val() != $('#pwc').val()){
			if($('#pwc').val()!=''){
				alert("비밀번호가 일치하지 않습니다.");
				$('#pwc').val('');
			   $('#pwc').focus();
			}
			
		}
	})
	

})