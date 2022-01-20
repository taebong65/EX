/**
 * 
 */
$(document).ready(function(){
   
   //파일의 크리와 화장자를 검사하는 함수 선언
   //서버에서 설정해 놓은 파일크기 설정
   var maxSize=5242880;  //5MB
   //서버에서 허용 가능한 확장자 설정(정규식)
   var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
   
   function checkExtension(fileSize,fileName){
      var str ="";
      
      //사용자가 선택한 파일의 크기가 서버에서 설정해 놓은 파일의 크기보다 이상이면,
      if(fileSize>=maxSize){
         //str =+"파일 사이즈 초과";
         alert("파일 사이즈 초과");
         return false;
      }
      //사용자가 선택한 파일의 확장자가 서버에서 설정해 놓은 파일의 확장자와 일치하지 않으면
      if(regex.test(fileName)){
         //str =+"해당 종류의 파일은 업로드 할 수 없습니다.";
         alert("해당 종류의 파일은 업로드 할 수 없습니다.");
         return false;
      }
      //alert(str);
      return true;   
   }
   
   var formObj = $("form[role='form']")
   //글쓰기버튼을 클릭하면
     $("input[type='submit']").on("click",function(e){
    	 e.preventDefault();
    	 var str="";
    	 //li태그에 있는
    	 $("#uploadResult ul li").each(function(i,obj){
    		 console.log(obj);
    		 
    		 //data선택자를 이용하여 input태그의 value값으로 세팅
    		 /*
    		  * data함수
    		  * <spna>
    		  * $("span").data("age",13) = > <span data-age="13"> data함수 괄호안에 매개변수가 두개면 setter
    		  * <span data-age="13">
    		  * $("span").data("age") = > 13 data함수 괄호안에 매개변수가 하나면 getter
    		  * 
    		  * */
    		 str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+$(obj).data("filename")+"'>"
             str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+$(obj).data("uuid")+"'>"
             str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+$(obj).data("path")+"'>"
             str+="<input type='hidden' name='attachList["+i+"].image' value='"+$(obj).data("type")+"'>"

    	 })
    	 formObj.append(str).submit();  //추가할때 append 오버라이트 html
    	 
    	 
     })
   
   
   $("input[type='file']").on("change",function(e){
	   
	   
      alert("클릭");
      
      var formData = new FormData();
      var inputFile=$("input[name='uploadFile']");
      var files=inputFile[0].files;
      
      e.preventDefault();
      console.log(files);
      
      for(var i=0;i<files.length;i++){
         //파일크기가 이상하면 밑에있는거 하지마라
         if(!checkExtension(files[i].size,files[i].name)){
            //밑에거 하지마라
            return false;
         }
         formData.append("uploadFile",files[i]);
      }
      
      //formData 자채를 데이터로 전송하고, formData를 데이터로 전송할때에는 processData,contentType은 반드시 false여야 한다
      $.ajax({
         url:"/uploadAjaxAction",
         type:"post",
         data:formData,
         processData:false,
         contentType:false,
         success:function(result){     //사용자가 선택한 파일을 원하는 경로에 성공적으로 업로드 한 후 .......
            //이게 실행될려면 ReplyController에 HttpSt ok가 실행될때 실행된다
           
        	 console.log(result);
        	 
        	 showUploadedFile(result);
        	 
//        	 $("uploadDiv").html(cloneObj.html());
         }
      }) // $a.jax끝...
      
   }) 
      
})//$(document).ready(function() 끝



	//사용자가 선택한 파일을 원하는 경로에 성공적으로 업로드 한 후 웹브라우저에 파일을 띄워라에 대한 함수 선언(showUploadFile)
         function showUploadedFile(uploadresultArr){
		var str="";
	   $(uploadresultArr).each(function(i,obj){
		   console.log(obj);
		   var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName)

		 if(!obj.image){				//사용자가 업로드 한 파일의 타입이 이미지가 아니면(엑셀문서파일,피피티 파일)
			 
			// str+="<li><img src='/resources/img/attach.png'>"+obh.fileName+"</li>" 
			  str+="<li><a href='download?fileName="+fileCallPath+"'>"+obj.fileName+"</a></li>"
		 } else{			//사용자가 업로드 한 파일의 타입이 이미지 이면(.jpg, .png,.gif)
			 var fileCallPath=encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName)
//			 str+="<li data-path='"+obj.uploadPath+"'";
//			 str+="data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'>";
//			 str+="<img src='/display?fileName="+fileCallPath+"'></li>";
			 // str+="<li><img src='"+obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName+"'></li>"
			 console.log(fileCallPath);
			 //img태그를 사용해서 웹브라우저 이미지 출력....
			 str+="<li data-path='"+obj.uploadPath+"'";
			 str+="data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'>";
			 str+="<img src='/display?fileName="+fileCallPath+"'></li>";
		 } 			
		  
		   			
		 
	   });
	   $("#uploadResult ul").html(str)
   }
        
         
    
  
   
   
   
