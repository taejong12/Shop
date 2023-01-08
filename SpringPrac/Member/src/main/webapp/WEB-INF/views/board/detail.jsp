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
			<th>상품이미지</th>
			<th>상품명</th>
			<th>상품가격</th>
			<th>상품재고</th>
			<th>상품조회수</th>
			<th>상품등록일</th>
			<th>상품내용</th>
		</tr>

		<tr>
			<td>${boardDetailList.boardImage}</td>
			<td>${boardDetailList.boardTitle}</td>
			<td>${boardDetailList.boardPrice}</td>
			<td>${boardDetailList.boardStock}</td>
			<td>${boardDetailList.boardHits}</td>
			<td>${boardDetailList.boardCreateDate}</td>
			<td>${boardDetailList.boardContent}</td>
		</tr>
	</table>
	<button type="button"
		onclick="location.href='/board/update/${boardDetailList.boardNo}';">수정</button>
	<button type="button"
		onclick="location.href='/board/delete/${boardDetailList.boardNo}';">삭제</button>
	<button type="button" onclick="location.href='/board/list';">목록</button>

</body>
</html>