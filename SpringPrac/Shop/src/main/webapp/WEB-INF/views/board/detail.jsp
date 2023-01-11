<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>상품 상세보기</h2>
	<table class="table table-striped table-hover">
		<c:if test="${boardDetailList.boardFileAttached == 1}">
			<tr>
				<th>상품이미지</th>
				<td>
					<img src="/upload/${boardDetailList.storedBoardFile}" alt="이미지" style="float: left;  margin-right: 20px; margin-bottom: 10px; width:200px; height:200px;">
				</td>
			</tr>
		</c:if>
		<tr>
			<th>상품명</th>
			<td>${boardDetailList.boardTitle}</td>
		</tr>
		<tr>
			<th>상품가격</th>
			<td>${boardDetailList.boardPrice}</td>
		</tr>
		<tr>
			<th>상품재고</th>
			<td>${boardDetailList.boardStock}</td>
		</tr>
		<tr>
			<th>상품조회수</th>
			<td>${boardDetailList.boardHits}</td>
		</tr>
		<tr>
			<th>상품등록일</th>
			<td>${boardDetailList.boardCreateDate}</td>
		</tr>
		<tr>
			<th>상품내용</th>
			<td>${boardDetailList.boardContent}</td>
		</tr>
	</table>
	
	<div class="d-flex justify-content-center">
		<button type="button" class="btn btn-primary"
			onclick="location.href='/cart/list/${sessionScope.memberNo}';">장바구니 추가</button>&nbsp
		<button type="button" class="btn btn-primary"
			onclick="location.href='/order/item/${boardDetailList.boardNo}';">주문하기</button>&nbsp
		<button type="button" class="btn btn-primary"
			onclick="location.href='/board/update/${boardDetailList.boardNo}';">수정</button>&nbsp
		<button type="button" class="btn btn-primary"
			onclick="location.href='/board/delete/${boardDetailList.boardNo}';">삭제</button>
	</div>

</body>
</html>