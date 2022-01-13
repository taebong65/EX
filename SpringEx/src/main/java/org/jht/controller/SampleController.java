package org.jht.controller;

import org.jht.domain.SampleMemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("sample")
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	//@RequestMapping(value="", method=RequestMethod.GET)
	@GetMapping("")
	public void basic(Model model) {
		logger.info("sample 실행됨.");
		// aaaa문자열을 abcd변수에 저장하여 sample.jsp에 보내기
		model.addAttribute("abcd", "aaaa");
	}
	//@RequestMapping(value="ex01", method=RequestMethod.GET)
	@GetMapping("ex01")
	public String basic1(Model model) {
		logger.info("sample/ex01 실행됨.");
		// bbbb문자열을 zzzz변수에 저장하여 ex01.jsp에 보내기
		model.addAttribute("zzzz", "bbbb");
		return "ex01";
	}
	//@RequestMapping(value="index",method=RequestMethod.GET)
	@GetMapping("index")
	public String index(Model mod) {
		// cccc문자열을 yyyy변수에 저장하여 index.jsp에 보내기
		mod.addAttribute("yyyy", "cccc");
		return "index";
	}
	//@RequestMapping(value="member",method=RequestMethod.GET)
	@GetMapping("member")
	public String member(String id, String pw, String name,Model model) {
		System.out.println("id="+id);
		System.out.println("pw="+pw);
		System.out.println("name="+name);
		
		// id값을 id변수에 저장하여 member.jsp에 보내기
		model.addAttribute("id", id);
		// pw을 pw변수에 저장하여 member.jsp에 보내기
		model.addAttribute("pw", pw);
		// name값을 name변수에 저장하여 member.jsp에 보내기
		model.addAttribute("name", name);
		
		return "member";
	}
	//@RequestMapping(value="memberDTO",method=RequestMethod.POST)
	@PostMapping("memberDTO")
	public String memberdto(SampleMemberDTO smd, Model model) {
		System.out.println("id="+smd.getId());
		System.out.println("pw="+smd.getPw());
		System.out.println("name="+smd.getName());
		
		// SampleMemberDTO값을 id변수에 저장하여 index.jsp에 보내기(난이도 상)
		model.addAttribute("id", smd);
		
		//return "memberDTO";
		//return "redirect:/";
		return "redirect:/sample/member";
	}
}
