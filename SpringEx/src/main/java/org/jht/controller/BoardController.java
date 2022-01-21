package org.jht.controller;

import java.util.ArrayList;

import org.jht.domain.AttachFileDTO;
import org.jht.domain.BoardDTO;
import org.jht.domain.Criteria;
import org.jht.domain.PageDTO;
import org.jht.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("board")
public class BoardController {
   //상속이 아닌 포함관계( ex> BoardService svc= new BoardService();
   @Autowired
   private BoardService service;
   
   //글쓰기 화면으로
   @GetMapping("write")
   public void write() {
      System.out.println("board/write");
   }
   
   //글쓰기 완료 버튼을 클릭하면
   @PostMapping("write")
   public String writePost(BoardDTO board) {
      service.write(board);
      System.out.println("write post......"+board);

//      이렇게하면 DB를 거쳐갈 수 없으므로
//      return "board/list";
//      redirect에는 @RequestMapping의 value값을 입력
      return "redirect:/board/list";
   }
   
   //게시판 목록 리스트
   @GetMapping("list")
   public void list(Criteria cri,Model model) {
      System.out.println("board/list");
      model.addAttribute("list", service.list(cri));
      //list 의 매개변수로 PageDTO 할 필요없어 new 선언해서 시용가능
      int total=service.getTotalCount(cri);
      model.addAttribute("pageMaker",new PageDTO(cri,total));
   }
   
   //게시판 목록 리스트에서 제목을 클릭하면
   @GetMapping("detail")
   public void detail(BoardDTO board, Model model) {
      model.addAttribute("detail", service.detail(board));
   }
   //게시판 상세페이지에서 이미지를 출력하기 위한 select된 결과를 javascript로
   @GetMapping(value="fileList/{bno}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
   public ResponseEntity<ArrayList<AttachFileDTO>> fileList(@PathVariable int bno){
      System.out.println("fileList");
      return new ResponseEntity<>(service.fileList(bno),HttpStatus.OK);
   }
   
   
   
   //글수정 화면
   @GetMapping("modify")
   public void modify(BoardDTO board,Model model) {
      System.out.println("board/modify");
      model.addAttribute("detail", service.detail(board));
   }
   
   //글수정 버튼
   @PostMapping("modify")
   public String modifyPost(BoardDTO board,RedirectAttributes rttr) {
      System.out.println("postmodify");
      //update
      service.modify(board);
      rttr.addAttribute("bno", board.getBno());
      //controller에서 bno값이 필요해서 추가했다 bno에 넣어줘라 get으로 불러온값을
      return "redirect:/board/detail";
   }

   //글삭제 버튼
   @GetMapping("remove")
   public String remove(BoardDTO board, Model model) {
      System.out.println("board/delete");
      //delete
      service.remove(board);
      return "redirect:/board/list";
   }

}