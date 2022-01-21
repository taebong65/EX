package org.jht.service;

import org.jht.domain.MemberDTO;

public interface MemberService {
	
	//회원가입 하기 설계
		public void insert(MemberDTO mdto);
		
		
		//로그인 설계
		public MemberDTO login(MemberDTO mdto);
		
}
