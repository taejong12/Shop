<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>게시물 목록</h2>
	<table border="1">
		<tr>
			<th>상품이미지</th>
			<th>상품명</th>
			<th>상품가격</th>
			<th>상품재고</th>
			<th>상품조회수</th>
			<th>상품등록일</th>
		</tr>
		<c:forEach items="${boardList}" var="boardList">
			<tr>
				<td>${boardList.boardImage}</td>
				<td><a href="/board/detail/${boardList.boardNo}">${boardList.boardTitle}</a></td>
				<td>${boardList.boardPrice}</td>
				<td>${boardList.boardStock}</td>
				<td>${boardList.boardHits}</td>
				<td>${boardList.boardCreateDate}</td>

			</tr>
		</c:forEach>
	</table>

	<button type="button" onclick="location.href='/board/write';">작성하기</button>

</body>
</html>