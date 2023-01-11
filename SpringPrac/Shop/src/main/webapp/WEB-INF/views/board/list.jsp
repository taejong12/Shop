<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>게시물 목록</h2>
	<table class="table table-striped table-hover">
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
				<td><img src="/upload/${boardList.storedBoardFile}" alt="이미지"></td>
				<td><a href="/board/detail/${boardList.boardNo}">${boardList.boardTitle}</a></td>
				<td>${boardList.boardPrice}원</td>
				<td>${boardList.boardStock}개</td>
				<td>${boardList.boardHits}회</td>
				<td>${boardList.boardCreateDate}</td>

			</tr>
		</c:forEach>
	</table>

	<button type="button" onclick="location.href='/board/write';">작성하기</button>

</body>
</html>