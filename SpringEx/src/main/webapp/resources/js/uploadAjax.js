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
   
   
   $("input[type='submit']").on("click",function(){
     // alert("클릭");
      
      var formData = new FormData();
      var inputFile=$("input[name='uploadFile']");
      var files=inputFile[0].files;
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
         url:"uploadAjaxAction",
         type:"post",
         data:formData,
         processData:false,
         contentType:false,
         success:function(result){
            //이게 실행될려면 ReplyController에 HttpSt ok가 실행될때 실행된다
            alert("upload성공");
         },   // 통신이 정상적으로 성공했으면
           error:function(){
               
           } 
         
      })
   })
   
   
   
})//ready end