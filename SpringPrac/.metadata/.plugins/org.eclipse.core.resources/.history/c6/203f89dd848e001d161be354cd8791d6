<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<form action="/board/list/update" method="post">
		<input type="hidden" value="${list.id}" name="test_board_no">
		<table>
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>내용</th>
			</tr>
			<tr>
				<td><input type="text" value="${list.title}" name="title"/></td>
				<td><input type="text" value="${list.writer}" name="writer"/></td>
				<td><input type="date" value="${list.regdate}" name="regdate"/></td>
				<td><input type="text" value="${list.content}" name="content"/></td>
			</tr>
		</table>
		<input type="submit" value="수정하기"/>
	</form>
	<button type="button" onclick="location.href='/board/list';">목록</button>
</body>
</html>     
