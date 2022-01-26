<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../resources/css/member.css" rel="stylesheet" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../resources/js/member1.js"></script>
<meta charset="UTF-8">

    <title>회원가입</title>

</head>
<body>
    <div id="#wrap">
        <header id="header">
            
                <h1 class="page_tit">회원 가입</h1>
               
            

        </header>

        <div id="container">

            <div class="topArea">

                <h2 > 오설록에 오신것을 환영합니다. </h2>
                <p>오설록 회원가입을 위해서 아이디와 비밀번호를 입력해주세요 </p>


            </div>
            
            
           <form action="/member/member" method="post" id="target">
           
            <div class="inputForm">
                <input type="text" class="name" name="Name" id="name" placeholder="이름을 입력해주세요">
                
            </div>
            <div class="idInput">
                <label id="idmsg"></label>
                <br>
                <input type="text"  name="id"   id="id" placeholder="아이디(영문 또는 숫자)">
               
            </div>
   
            

            <div class="pwInput">
                
                <br>
                <input name="password" id="pw" type="password" placeholder="비밀번호(영문 소문자.숫자,특수문자 조합)">

            </div>
            <div class="pwConfirm">
               
                <br>
                <input type="password" id="pwc" placeholder="비밀번호 확인 ">
            </div>

            
            <div class="confirm">
                <input type="submit"  class="regist" value="회원가입 ">
            </div>
        </form>
    </div><!--#container-->
</div><!--#wrap-->
</body>
	

</html>