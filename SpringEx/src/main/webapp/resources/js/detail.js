/**
 * 
 */
//스코프를 다르게 설정해서 메모리관리함
var replyService=(function(){ //함수선언
   //댓글 쓰기를 하기위한 함수 선언, 매개변수 설정
   function add(reply,callback){
      console.log("add...")
      $.ajax({
         url:"/replies/new",
         type:"post",
         data:JSON.stringify(reply),   //호출에 쓰인 인수를 json파일 형식으로 고치기 //js의 값을 json 문자열로 변환
         contentType:"application/json; charset=UTF-8",
         success:function(result){   
            //통신이 정상적으로 성공했으면
            console.log(result);
            //callback함수 선언
            //만약에 callback이 있으면
            if(callback)
            //callback함수를 호출
               callback(result);
            
         },   
         error:function(){        //통신이 비정상적으로 처리가 되어 error가 있으면
            
         }
      })
   }
   
   //댓글 목록리스트를 위한 함수 선언
   function getList(param,callback){
//      param이 가리키는 것은 {bno:bno}이고 json 데이터로 들어오기 때문에
//      bno가 정확히 처리될 수 있게 변수 선언
      var bno=param.bno;
      console.log(bno);

      $.getJSON(
            "/replies/list/"+bno+".json",
            //위의 success 부분
            function(data){
               if(callback)
                  //list로 최종적으로 들어감
                  callback(data);
            }   
      )
   }
   //댓글 수정할때 댓글내용 가져오는 함수
   function reDetail(rno,callback){
      var rno=rno;
      $.getJSON(
         "/replies/"+rno+".json",
         function(data){
            if(callback){
               if(callback)
                  callback(data);
            }
         }
      )
   }
   
   
   //댓글 수정을 하기위한 함수
   function reupdate(reply,callback){
      $.ajax({
         url:"/replies/update",
         type:"put",
         data:JSON.stringify(reply),   //호출에 쓰인 인수를 json파일 형식으로 고치기 //js의 값을 json 문자열로 변환
         contentType:"application/json; charset=UTF-8",
         success:function(result){   
            if(callback)
               callback(result);
         },   
         error:function(){      
            
         }
      })
      
   }

   //댓글 삭제를 하기 위한 함수 선언
   function remove(reply,callback){
      $.ajax({
         url:"/replies/remove",
         type:"delete",
         data:JSON.stringify(reply),   //호출에 쓰인 인수를 json파일 형식으로 고치기 //js의 값을 json 문자열로 변환
         contentType:"application/json; charset=UTF-8",
         success:function(result){   
            if(callback)
               callback(result);
         },   
         error:function(){      
            
         }
      })
      
   }
   
   
   
   return {
      bbb:add,
      ccc:getList,
      reDetail:reDetail,
      reupdate:reupdate,
      remove:remove
      };
      
})()
$(document).ready(function(){
	//bno값 출력하기
	   var bno=$("#bno").html();

	
	//상세페이지가 시작되자마자 이미지를 출력하기 위한 ajax
	$.getJSON("/board/fileList/"+bno+".json",
	         function(data){ //BoardController에 있는 fileList를 통해 얻어진 select결과를 data에 저장한 후 
					//detail.jsp에 뿌리기
			console.log(data)
			var str="";
			$(data).each(function(i,obj){
				
				   $(uploadresultArr).each(function(i,obj){
					   console.log(obj);
					  

					 if(!obj.image){				//사용자가 업로드 한 파일의 타입이 이미지가 아니면(엑셀문서파일,피피티 파일)
						 var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName)
						// str+="<li><img src='/resources/img/attach.png'>"+obh.fileName+"</li>" 
						  str+="<li><a href='download?fileName="+fileCallPath+"'>"+obj.fileName+"</a></li>"
					 } else{			//사용자가 업로드 한 파일의 타입이 이미지 이면(.jpg, .png,.gif)
						 var fileCallPath=encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName)
//						 str+="<li data-path='"+obj.uploadPath+"'";
//						 str+="data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'>";
//						 str+="<img src='/display?fileName="+fileCallPath+"'></li>";
						 // str+="<li><img src='"+obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName+"'></li>"
						 console.log(fileCallPath);
						 //img태그를 사용해서 웹브라우저 이미지 출력....
						 str+="<li data-path='"+obj.uploadPath+"'";
						 str+="data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'>";
						 str+="<img src='/display?fileName="+fileCallPath+"'></li>"
					 } 			
					  
					   			
					 
				   })
			   
				   $("#uploadResult ul").html(str)
				})
			
	})
})




$(document).ready(function(){
   
   //상세페이지가 실행되면 댓글 글쓰기버튼 활성화
   $("#modalRegisterBtn").show();
   //상세페이지가 실행되면 댓글 글수정버튼 활성화
   $("#modalModBtn").show();
   //상세페이지가 실행되면 댓글 글삭제버튼 활성화
   $("#modalRemoveBtn").show();
   
   
   
   // 댓글쓰기 버튼
   $("#myModalLabel").on("click",function(e){
      //댓글 글수정버튼 비활성화
      $("#modalModBtn").hide();
      //댓글 글삭제버튼 비활성화
      $("#modalRemoveBtn").hide();
      //모달창을 띄워라
      //$(".modal").modal("show");
      
      $("input[name='rno']").val("")
      $("input[name='replyer']").val("")
      $("input[name='reply']").val("")
      
      
   })
   
   
   
   
   
   //게시판에 댓글 상시 출력
   showList();
   
   //detail.jsp가 실행되자마자 댓글목록 리스트 실행(bno 필요)
   //bno를 .json를 이용해 mapping하기 때문에 json타입으로 정의
   //detail.jsp에 있어야되므로 미리 한번 호출, 미리 메모리에 올려야하므로 $ready 안에 작성   
   function showList(){
      replyService.ccc({bno:bno},function(list){
         console.log(list);   
         
         var str="";
         for(let i=0; i<list.length; i++){
            //rno 추가작업 필요
            str+="<li data-rno='"+list[i].rno+"'><div><b>"+list[i].replyer+"</b></div>"
            str+="<div>"+list[i].reply+"</div>"
            str+="</li>"
         }
         
         $("#relist").html(str)
         
      });
   }

   
   
   
   
   //완료 버튼을 클릭하면
   $("#modalRegisterBtn").on("click",function(){
      //사용자가 입력한 댓글 내용을 저장
      var reply=$("input[name='reply']").val()
      //사용자가 입력한 댓글작성자를 저장
      var replyer=$("input[name='replyer']").val()
      // 글쓰기 버튼 비활성화.
      $("#modalRegisterBtn").show();
      
      //ajax로 보내고자하는 json타입                                                ,callback함수호출
      //콜백함수는 insert하고 난다음의 후조치
      replyService.bbb({reply:reply,replyer:replyer,bno:bno},
         function(result){ //callback함수 호출
            alert("insert 작업 : "+ result);
            //목록 리스트 처리
            showList();
      });
      //모달창을 숨겨라
      $(".modal").modal("hide");
      
   })
   
   //댓글내용을 클릭하면
   $("#relist").on("click","li",function(){
      
      
      var rno=$(this).data("rno");
      
      replyService.reDetail(rno,function(detail){
         
         $("input[name='rno']").val(detail.rno)
         $("input[name='replyer']").val(detail.replyer)
         $("input[name='reply']").val(detail.reply)
         
         
      })
      //완료 비활성화
      $("#modalRegisterBtn").hide();
      //글수정 버튼 비활성화
      $("#modalModBtn").show();
      //글삭제 버튼 비활성화
      $("#modalRemoveBtn").show();
      //모달창을 띄워라
      $(".modal").modal("show");
      
   })

   //글쓰기 내 댓글 수정 버튼을 클릭하면
   $("#modalModBtn").on("click",function(){
      //alert("aaa"); 연결확인

      var reply={rno:$("input[name='rno']").val(),reply:$("input[name='reply']").val()}
      console.log(reply);
      
      //앳글수정함수를 호출해서 처리
      replyService.reupdate(reply,function(update){
         // 롤백영역(update가 정상적으로 처리된 후 조치)
         
         //목록 리스트 처리
         showList();
         //모달창을 숨겨라
         $(".modal").modal("hide");
      })
      
      
   })
   
   
   //글쓰기 내 댓글삭제버튼을 클릭하면
   $("#modalRemoveBtn").on("click",function(){
      
      //alert("aaa"); 연결확인
      
      var reply={rno:$("input[name='rno']").val()}
      
      //댓글삭제함수를 호출해서 처리
      replyService.remove(reply,function(remove){
         
         //alert("delete 작업:"+remove)
         
         showList();
         
         $(".modal").modal("hide");
         
      })
      
      
      
   })
   
   
   
   
   
   
   
   
   

   
})   //$(document).ready(function(){ 끝...




