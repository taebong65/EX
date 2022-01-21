package org.jht.service;

import org.jht.domain.MemberDTO;
import org.jht.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl  implements MemberService{
	//회원가입 하기  설계된 것을 구현 
	@Autowired
	private MemberMapper mmapper;
	public void insert(MemberDTO mdto) {
		
		mmapper.insert(mdto);
	}
	// 로그인 설계된것을 구현 
	
	public MemberDTO login(MemberDTO mdto) {
		
		return mmapper.login(mdto);
		}
	}
	

