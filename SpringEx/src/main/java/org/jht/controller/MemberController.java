package org.jht.controller;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.jht.domain.MemberDTO;
import org.jht.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService mservice;
	//회원가입 화면 이동
	
	@GetMapping("member")
	public void write() {
		System.out.println("board/write");
	} 
	@PostMapping("member")
	public void Postmember(MemberDTO mdto) {
		// 회원가입 화면을 통해 얻어진 데이터 insert
		mservice.insert(mdto);
	}
	
	//로그인 화면 이동 
	@GetMapping("login")
	public void login() {
		System.out.println("member/login");
	} 
	// 로그인 화면을 통해 얻어진 데이터를 활용하여 select
	
	@PostMapping("login")
	public String Postlogin(MemberDTO mdto,HttpSession session) {
		MemberDTO login=mservice.login(mdto);
		
		//MemberDTO에 있는 MemberDTO [id=abcd, password=1234, name=정자바]를 세션 영역에 login이라는 변수에 저장
		// 세션객체(session)에       login변수에, log값을 저장(setAttribute)
		session.setAttribute("login", login);
		
		
		 // session.invalidate(); 로그아웃
		
		// session영역에 login이라는 변수에 값이 있으면 로그인 된 채로
		
		if(session.getAttribute("login")!=null) {
			
			//main페이지로 이동 
			return "redirect:/";
			
		}else { //session 영역에 login이란느 변수에 값이 없으면(null)
			//다시 로그인 할 수 있게 로그인 페이지로 이동 
			
			return "redirect:/member/login";
		}
		// session영역에 login이라는 변수에 값이 없으면 (null)
		
		
	}
}
