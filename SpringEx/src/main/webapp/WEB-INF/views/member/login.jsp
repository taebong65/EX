<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>오설록 로그인</title>
<link rel="stylesheet" href="../resources/css/login.css">
</head>


<body>

    <div id="wrap"> 
    	<div class="homeback">
            <a href="/"><img src="/resources/img/logo_m.png"></a>
        </div>
    
        <div class="headerBox">
            <h1>로그인</h1>
            <button class="btn_close">>닫기</button>

        </div><!--.headerBox-->
    
      
    <div id="container">
         <form action="/member/login" method="post">
		 	  <div id="login_box">
		 	  
           <input name ="id"  type="text" id="loginid" placeholder="아이디 입력"> 
           
           <input name="password" type="password" id="loginpw"  placeholder="비밀번호 입력(영문,숫자,특수문자 조합)">
            
           <input type="submit" class="btn btn-primary" value="Login" id="dologin">
            
          </div>
          
         
         </form>
    
     
                                              


        <labe class="id_saved"> <input type="checkbox">아이디 저장</labe>

          <ul class="etc_login">
            <li>
                <button type="button" id="mobile-login"><img src="/resources/img/btn_login_mobile.png" alt=""> <span> 휴대폰 <br> 로그인</span> </button>
            </li>
            <li>
                <button type="button" id="kakao-login"><img src="/resources/img/btn_login_ka.png"alt=""> <span>카카오 <br> 로그인</span></button>
            </li>
            <li>
                <button type="button" id="mobile-login"><img src="/resources/img/btn_login_na.png" alt=""> <span>네이버 <br> 로그인</span></button>
            </li>
            <li>
                <button type="button" id="mobile-login"><img src="/resources/img/btn_login_fb.png" alt=""> <span>페이스북</span> <br>로그인</button>
            </li>

        </ul><!--.etc_login-->

        <ul class="bottom_menu">
            <li>
                <p>
                    <a href="#">아이디 찾기</a>
                    <a href="#">비밀번호 찾기</a>
                    <a href="#">비회원 주문/조희</a>
                    
                </p>
                
            </li>
           
        </ul>

        
        <button class="join_membership">
            <span>아직 회원이 아니세요?</span>
            <em>
                	회원가입
            </em>
            <span class="arrow">

            </span>
        </button>
    </div><!--#container-->


    </div><!--#wrap-->
</body>
</html>