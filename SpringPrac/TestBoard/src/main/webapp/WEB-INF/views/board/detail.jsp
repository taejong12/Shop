<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
		</tr>
		<tr>
			<td>${detail.title}</td>
			<td>${detail.writer}</td>
			<td>${detail.content}</td>
		</tr>
	</table>
	
<button type="button" onclick="location.href='/board/update/${detail.id}';">수정</button>
<button type="button" onclick="location.href='/board/delete/${detail.id}';">삭제</button>
<button type="button" onclick="location.href='/board/list';">목록</button>



</body>
</html>