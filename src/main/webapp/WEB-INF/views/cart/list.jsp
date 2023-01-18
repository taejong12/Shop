<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>


	<h2>장바구니 목록</h2>

	<table class="table table-striped table-hover">
		<tr>
			<th>상품이미지</th>
			<th>상품명</th>
			<th>상품가격</th>
			<th>선택한갯수</th>
			<th>상품총가격</th>
			<th>삭제</th>
			
		</tr>
		<c:forEach items="${cartList.content}" var="cartList">
			<tr>
				<td><img src="/upload/${cartList.storedItemFile}" alt="이미지"
					style="width: 100px; height: 100px;"></td>
				<td><a href="/item/detail/${cartList.itemNo}">${cartList.itemTitle}</a></td>
				<td>${cartList.itemPrice}원</td>
				<td>${cartList.cartItemAmount}개</td>
				<td>${cartList.itemPrice * cartList.cartItemAmount}원</td>
				<td><button type="button">삭제</button></td>
			</tr>
		</c:forEach>
	</table>

	<!-- 페이징 영역 시작 -->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">

			<!-- 이전 -->
			<c:choose>
				<c:when test="${cartList.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/cart/list/${sessionScope.memberNo}?page=1&searchText=${param.searchText}">처음</a></li>
					<li class="page-item"><a class="page-link"
						href="/cart/list/${sessionScope.memberNo}?page=${cartList.number}&searchText=${param.searchText}">&larr;</a></li>
				</c:otherwise>
			</c:choose>

			<!-- 페이지 그룹 -->
			<c:forEach begin="${startPage}" end="${endPage}" var="i">
				<c:choose>
					<c:when test="${cartList.pageable.pageNumber+1==i}">
						<li class="page-item disabled"><a class="page-link"
							href="/cart/list/${sessionScope.memberNo}?page=${i}&searchText=${param.searchText}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/cart/list/${sessionScope.memberNo}?page=${i}&searchText=${param.searchText}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 -->
			<c:choose>
				<c:when test="${cartList.last}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/cart/list/${sessionScope.memberNo}?page=${cartList.number+2}&searchText=${param.searchText}">&rarr;</a></li>
					<li class="page-item"><a class="page-link"
						href="/cart/list/${sessionScope.memberNo}?page=${cartList.totalPages}&searchText=${param.searchText}">마지막</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- 페이징 영역 끝 -->
	
	<button type="button" class="btn btn-primary"
		onclick="location.href='/order/cartItem';">주문하기</button>
	
	<script type="text/javascript">
		const redirectAlert = "${msg}";
		if(redirectAlert == "itemWriteSuccess"){
			alert("상품이 등록되었습니다.");
		}
		
		if(redirectAlert == "itemDeleteSuccess"){
			alert("상품이 삭제되었습니다.");
		}

	</script>


</body>
</html>