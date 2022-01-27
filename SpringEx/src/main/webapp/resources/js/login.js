/**
 * 
 */

$(document).ready(function(){
	
	
	
	$("#dologin").on("click",function(){
		if($("#loginid").val()==""){
			alert("아이디를 입력해주세요");
			$("#loginid").focus();
			return false;
		}
		if($("#loginpw").val()==""){
			alert("비밀번호를 입력해주세요");
			$("#loginpw").focus();
			return false;
		}
		
	})

})