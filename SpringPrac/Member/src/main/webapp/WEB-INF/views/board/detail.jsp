<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	
		<tr>
			<td>${list.title}</td>
			<td>${list.writer}</td>
			<td>${list.regdate}</td>
		</tr>
	</table>
<button type="button" onclick="location.href='/boardupdate/${id}';">수정</button>
<button type="button" onclick="location.href='/board/list/delete?test_board_no=${list.test_board_no}';">삭제</button>
<button type="button" onclick="location.href='/board/list';">목록</button>



</body>
</html>