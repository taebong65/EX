package org.jht.service;

import java.util.ArrayList;

import org.jht.domain.ReplyDTO;

public interface ReplyService {
	// �뙎湲� �벐湲� �꽕怨�
	public int write(ReplyDTO rdto);
	// �뙎湲� 紐⑸줉由ъ뒪�듃 �꽕怨�
	public ArrayList<ReplyDTO> list(int bno);
	//댓글 수정을 하기위해 댓글 내용 가져오기 
	public ReplyDTO detail(int rno);
}
