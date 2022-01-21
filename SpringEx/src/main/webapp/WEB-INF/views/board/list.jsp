<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../resources/css/all.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/sb-admin-2.css">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link href="../resources/css/dataTables.bootstrap4.css" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js">
    </script>
    <script type="text/javascript" src="../resources/js/list.js">
    </script>
</head>
<body>
	<h1>게시판 목록 리스트</h1>
	
	<c:if test="${login!=null}">
		<div class="form-control bg-light border-0 small"><a href="/board/write"  class="page-link">글쓰기</a></div>
	</c:if>
	<div class="card-body">
       <div class="table-responsive">
           <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
               <thead>
                   <tr>
                       <th>게시판번호</th>
                       <th>제목</th>
                       <th>작성자</th>
                       <th>작성일</th>
                       <th>조회수</th>
                       <th>좋아요</th>
                   </tr>
               </thead>
               <tbody>
               		<c:forEach items="${list}" var="board">
						<tr>
						    <td>${board.bno}</td>
						    <td><a href="/board/detail?bno=${board.bno}">${board.title}</a></td>
						    <td>${board.writer}</td>
						    <td>${board.regdate}</td>
						    <td>${board.cnt}</td>
						    <td>${board.good}</td>
						</tr>
                   </c:forEach>
               </tbody>
           </table>
           <form id="actionForm" action="/board/list" method="get">
           <div class="form-control bg-light border-0 small">
           		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
           		<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
	           <select name="search">
	           		<option value="T">제목</option>
	           		<option value="C">내용</option>
	           		<option value="W">작성자</option>
	           		<option value="TC">제목 + 내용</option>
	           		<option value="TCW">제목 + 내용 + 작성자</option>
	           </select>
	           <input type="text" name="keyword" value="${pageMaker.cri.keyword}">
	           <input type="submit" value="검색">
		</div>
		</form>
       </div>
		<div id="dataTable_paginate" class="dataTables_paginate paging_simple_numbers">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li class="paginate_button page-item previous" id="dataTable_previous">
						<a href="${pageMaker.startPage-1}" class="page-link">이전</a>
					</li>
				</c:if>	
				<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
					<li class="paginate_button page-item">
						<a href="${num}" class="page-link">${num}</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next}">
					<li class="paginate_button page-item next" id="dataTable_next">
						<a href="${pageMaker.endPage+1}" class="page-link">다음</a>
					</li>
				</c:if>
			</ul>
			
		</div>
	</div>
</body>
</html>