<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Paging</title>
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
		<c:forEach items="${boardList.content}" var="boardList">
			<tr>
				<td><img src="/upload/${boardDetailList.storedBoardFile}" alt="이미지"></td>
				<td><a href="/board/detail/${boardList.boardNo}">${boardList.boardTitle}</a></td>
				<td>${boardList.boardPrice}원</td>
				<td>${boardList.boardStock}개</td>
				<td>${boardList.boardHits}회</td>
				<td>${boardList.boardCreateDate}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="d-flex justify-content-end">
		<button type="button" class="btn btn-primary" onclick="location.href='/board/write';">작성하기</button>
	</div>

	<!-- 페이징 영역 시작 -->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">
		
			<!-- 이전 -->
			<c:choose>
				<c:when test="${boardList.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/board/paging">처음</a></li>
					<li class="page-item"><a class="page-link" href="/board/paging?page=${boardList.number}">&larr;</a></li>
				</c:otherwise>
			</c:choose>

			<!-- 페이지 그룹 -->
			<c:forEach begin="${startPage}" end="${endPage}" var="i">
				<c:choose>
					<c:when test="${boardList.pageable.pageNumber+1==i}">
						<li class="page-item disabled"><a class="page-link" href="/board/paging?page=${i}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="/board/paging?page=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
				<c:when test="${boardList.last}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/board/paging?page=${boardList.number+2}">&rarr;</a></li>
					<li class="page-item"><a class="page-link" href="/board/paging?page=${boardList.totalPages}">마지막</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- 페이징 영역 끝 -->


</body>
</html>