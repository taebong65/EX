/**
 * 
 */
$(document).ready(function(){
	var actionForm = $("#actionForm");
	// 현재 페이지 번호를 클릭하면
	$(".paginate_button a").on("click",function(e){
		e.preventDefault();
		// form태그 안에 있는 pageNum 값을 가져와라
		actionForm.find("input[name='pageNum']").val($(this).attr("href"))
		// form태그 안에 있는 값을 controller로 보내라.
		actionForm.submit();
	});
	// 검색버튼을 클릭하면
	$("input[type='submit']").on("click",function(e){
		e.preventDefault();
		// pageNum의 값을 1로 설정
		actionForm.find("input[name='pageNum']").val("1")
		actionForm.submit();
	})
})