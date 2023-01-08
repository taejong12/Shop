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
<h2>게시물 수정하기</h2>
	<form action="/board/update/${update.id}" method="post">
		<table>
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>내용</th>
			</tr>
			<tr>
				<td>${update.title}<input type="text" name="title"/></td>
				<td>${update.writer}<input type="text" name="writer"/></td>
				<td>${update.content}<input type="text" name="content"/></td>
			</tr>
		</table>
		<input type="submit" value="수정완료"/>
	</form>
	<button type="button" onclick="location.href='/board/list';">목록</button>
</body>
</html>     
