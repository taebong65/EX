package org.jht.controller;

import org.jht.domain.BoardDTO;
import org.jht.domain.Criteria;
import org.jht.domain.PageDTO;
import org.jht.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	// priva  te BoardService service = new BoardService();
	private BoardService service;
	// 글쓰기 화면으로....
	@GetMapping("write")
	public void write() {
		System.out.println("board/write");
	}
	// 글쓰기 버튼을 클릭하면...
	@PostMapping("write")
	public String writePost(BoardDTO board) {
		service.write(board);
		System.out.println("write post....."+board);
		
		return "redirect:/board/list";
	}
	// 게시판 목록 리스트
	@GetMapping("list")
	public void list(Criteria cri,Model model) {
		System.out.println("board/list="+cri);
		model.addAttribute("list", service.list(cri));	
		int total=service.getTotalCount(cri);
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	// 게시판 목록 리스트에서 제목을 클릭하면....
	@GetMapping("detail")
	public void detail(BoardDTO board,Model model) {
		model.addAttribute("detail",service.detail(board));
	}
	// 글수정 화면으로....
	@GetMapping("modify")
	public void modify(BoardDTO board,Model model) {
		model.addAttribute("detail",service.detail(board));
		System.out.println("board/modify");
	}
	// 글수정 버튼을 클릭하면.....
	@PostMapping("modify")
	public String modifyPost(BoardDTO board,RedirectAttributes rttr) {
		System.out.println(board);
		//update
		service.modify(board);
		rttr.addAttribute("bno", board.getBno());
		return "redirect:/board/detail";
	}
	// 글삭제 버튼을 클릭하면.....
	@GetMapping("remove")
	public String remove(BoardDTO board) {
		System.out.println(board);
		service.remove(board);
		return "redirect:/board/list";
	}	
}





