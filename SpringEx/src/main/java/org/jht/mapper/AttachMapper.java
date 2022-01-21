package org.jht.mapper;

import java.util.ArrayList;

import org.jht.domain.AttachFileDTO;


public interface AttachMapper{
		//게시판 글쓰기 할때 파일과 관련되어 있는 attach테이블에 insert
		
		
		
		//게시판 상세페이지에 업로드된 이미지를 뿌리기 위한 데이터list
		
		public ArrayList<AttachFileDTO> fileList(int bno);

		public void insert(AttachFileDTO attach);
	} 

