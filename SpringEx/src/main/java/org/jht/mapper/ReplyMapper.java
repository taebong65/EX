package org.jht.mapper;

import java.util.ArrayList;

import org.jht.domain.ReplyDTO;

public interface ReplyMapper {
	// �뙎湲� �벐湲� �꽕怨�
	// insert �꽦怨듭떆 ReplyMapper.xml濡� 遺��꽣 1
	// insert �떎�뙣�떆 ReplyMapper.xml濡� 遺��꽣 0
	// 媛믪쓣 由ы꽩諛쏅뒗�떎.
	public int write(ReplyDTO rdto);
	
	public ArrayList<ReplyDTO> list(int bno);
	public ReplyDTO detail(int rno);

}
