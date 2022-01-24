let t =document.querySelector("#target")
let id =document.querySelector("#id")
let pw =document.querySelector("#pw")
let pwc=document.querySelector("#pwc")
let idmsg =document.querySelector("#idmsg")
let pwmsg =document.querySelector("#pwmsg")
let pwmsg2=document.querySelector("#pwmsg2")






t.addEventListener("mouseout",function(e){
    e.preventDefault();


    if(id.value.length>=8){
        
        idmsg.innerHTML="GOOD";
        idmsg.style.color="blue";
    }else{
        idmsg.innerHTML="아이디의 길이가 맞지 않습니다.";
        idmsg.style.color="red";
        return false;
    }

    if(pw.value.length>=8){
        pwmsg.innerHTML="GOOD";
        pwmsg.style.color="blue";
    }else{
        pwmsg.innerHTML="비밀번호의 길이가 맞지 않습니다.";
        pwmsg.style.color="red";
        return false;
    }
    if(pw.value==pwc.value){
        alert("두 비밀번호가 맞습니다.")
        pwmsg2.innerHTML="GOOD";
        pwmsg2.style.color="blue";
        

    }else if(pw.value!=pwc.value){
        alert("두 비밀번호가 맞지않습니다.")
        pwmsg2.innerHTML="두 비밀번호가 맞지 않습니다.";
        pwmsg2.style.color="red";
        return false;
    }









})
