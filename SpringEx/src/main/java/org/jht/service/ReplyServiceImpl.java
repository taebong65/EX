package org.jht.service;

import java.util.ArrayList;

import org.jht.domain.ReplyDTO;
import org.jht.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper rmapper;
	// 寃뚯떆�뙋 湲��벐湲� �꽕怨꾨맂寃껋쓣 援ы쁽
	public int write(ReplyDTO rdto) {
		// insert �꽦怨듭떆 ReplyMapper.java濡� 遺��꽣 1
		// insert �떎�뙣�떆 ReplyMapper.java濡� 遺��꽣 0
		// 媛믪쓣 由ы꽩諛쏅뒗�떎.
		return rmapper.write(rdto);
	}
	// �뙎湲� 紐⑸줉由ъ뒪�듃 �꽕怨꾨맂寃껋쓣 援ы쁽
	public ArrayList<ReplyDTO> list(int bno) {
		return rmapper.list(bno);
	}
	public ReplyDTO detail(int rno) {
		return rmapper.detail(rno);
	}
	
}
