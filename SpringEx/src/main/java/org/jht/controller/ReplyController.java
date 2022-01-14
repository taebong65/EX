package org.jht.controller;

import java.util.ArrayList;

import org.jht.domain.ReplyDTO;
import org.jht.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("replies")
public class ReplyController {
	@Autowired
	private ReplyService rservice;
	// �뙎湲��벐湲곕�� �븯湲� �쐞�븳 ReqeustMapping
	// Mapping�쓣 �븷�븣 �슦由ш� �썝�븯�뒗 �뜲�씠�꽣 ���엯�쓣 媛뺤젣�븿�쑝濡쒖뜥 �삤瑜섏긽�솴�쓣 以꾩씪 �닔 �엳�떎.
	// consumes : �뱾�뼱�삤�뒗 �뜲�씠�꽣 ���엯 �젙�쓽(�깮�왂媛��뒫)
	// produces : 諛섑솚�븯�뒗 �뜲�씠�꽣 ���엯 �젙�쓽(�깮�왂媛��뒫)
	// * �깮�왂�쓣 �븯寃� �릺硫�, �쎒釉뚮씪�슦��媛� �븣�븘�꽌 ���엯�쓣 �뙋�떒 *
	@PostMapping(value="new",consumes="application/json",produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyDTO rdto){
		System.out.println("ReplyDTO="+rdto);
		// insert �꽦怨듭떆 ReplyServiceImpl.java濡� 遺��꽣 1
		// insert �떎�뙣�떆 ReplyServiceImpl.java濡� 遺��꽣 0
		// 媛믪쓣 由ы꽩諛쏅뒗�떎.
		int result=rservice.write(rdto);
		System.out.println("result="+result);
		return result==1?new ResponseEntity<>("success",HttpStatus.OK)				// insert媛� �젙�긽�쟻�쑝濡� 泥섎━�릺�뿀�쓣 �븣
					    :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// insert媛� 鍮꾩젙�긽�쟻�쑝濡� 泥섎━�릺�뿀�쓣 �븣
	}
	@GetMapping(value="list/{bno}",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ArrayList<ReplyDTO>>getList(@PathVariable int bno){// @PathVariable : REST諛⑹떇�뿉�꽌 二쇰줈 �궗�슜.URL寃쎈줈�쓽 �씪遺�瑜� �뙆�씪誘명꽣 �궗�슜�븯怨좎옄�븷 �븣
		System.out.println(bno);
		return new ResponseEntity<>(rservice.list(bno),HttpStatus.OK);
	}
	
	@GetMapping(value="{rno}",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyDTO>getDetail(@PathVariable int rno){
	
	System.out.println(rno);
	return new ResponseEntity<>(rservice.detail(rno),HttpStatus.OK);
	}
	
	@PutMapping(value="update",consumes="application/json",produces={MediaType.TEXT_PLAIN_VALUE})
	
	public ResponseEntity<String> update(@RequestBody ReplyDTO rdto){
		
		System.out.println("rdto="+rdto);
		
		return rservice.update(rdto)==1?new ResponseEntity<>("success",HttpStatus.OK) 
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
	
	
	@DeleteMapping(value="remove",consumes="application/json",produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@RequestBody ReplyDTO rdto){
		
		return rservice.remove(rdto)==1?new ResponseEntity<>("success",HttpStatus.OK) 
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
