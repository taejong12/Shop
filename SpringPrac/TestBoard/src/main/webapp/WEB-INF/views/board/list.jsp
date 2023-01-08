<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>

<a href="/">홈페이지</a>
<a href="/board/list">목록</a>

<h2>게시물 목록</h2>

<table border="1">

	<tr>
		<th>게시물넘버</th>
		<th>제목</th>
		<th>작성자</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.id}</td>
			<td><a href="/board/detail/${list.id}">${list.title}</a></td>
			<td>${list.writer}</td>
		</tr>
	</c:forEach>
	
</table>
<button type="button" onclick="location.href='/board/write';">작성하기</button>

</body>
</html>