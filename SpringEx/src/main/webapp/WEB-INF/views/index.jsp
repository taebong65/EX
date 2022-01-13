<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %>
index.jsp파일입니다.<br>
<a href="/sample/member?id=abcd&pw=1234&name=정자바">회원가입</a>
<form action="/sample/memberDTO" method="post">
	<div>id:<input type="text" name="id"></div>
	<div>pw:<input type="password" name="pw"></div>
	<div>name:<input type="text" name="name"></div>
	<input type="submit" value="회원가입">
</form>
${yyyy}
</body>
</html>