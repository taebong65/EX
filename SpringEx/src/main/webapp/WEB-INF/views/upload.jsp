<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="uploadAction" method="post" enctype="multipart/form-data">
	<input type="file" name="uploadFile" multiple>
	<input type=submit value= "전송">
</form>	
</body>
</html>