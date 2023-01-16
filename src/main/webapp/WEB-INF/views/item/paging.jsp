<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Paging</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>


	<h2>게시물 목록</h2>

	<form class="form-inline d-flex justify-content-end" method="GET" action="/item/paging">
		<div class="form-group mx-sm-3 mb-2">
			<input type="text" class="form-control" id="searchText" name="searchText" value="${param.searchText}">
		</div>
		<button type="submit" class="btn btn-light mb-2">검색</button>
	</form>


	<table class="table table-striped table-hover">
		<tr>
			<th>상품이미지</th>
			<th>상품명</th>
			<th>상품가격</th>
			<th>상품재고</th>
			<th>상품조회수</th>
			<th>상품등록일</th>
		</tr>
		<c:forEach items="${itemList.content}" var="itemList">
			<tr>
				<td><img src="/upload/${itemList.storedItemFile}" alt="이미지"
					style="width: 100px; height: 100px;"></td>
				<td><a href="/item/detail/${itemList.itemNo}">${itemList.itemTitle}</a></td>
				<td>${itemList.itemPrice}원</td>
				<td>${itemList.itemStock}개</td>
				<td>${itemList.itemHits}회</td>
				<td>${itemList.itemCreateDate}</td>
			</tr>
		</c:forEach>
	</table>


	<c:if test="${sessionScope.memberNo != null}">
		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-primary"
				onclick="location.href='/item/write';">작성하기</button>
		</div>
	</c:if>
	<!-- 페이징 영역 시작 -->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">

			<!-- 이전 -->
			<c:choose>
				<c:when test="${itemList.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/item/paging?page=1&searchText=${param.searchText}">처음</a></li>
					<li class="page-item"><a class="page-link"
						href="/item/paging?page=${itemList.number}&searchText=${param.searchText}">&larr;</a></li>
				</c:otherwise>
			</c:choose>

			<!-- 페이지 그룹 -->
			<c:forEach begin="${startPage}" end="${endPage}" var="i">
				<c:choose>
					<c:when test="${itemList.pageable.pageNumber+1==i}">
						<li class="page-item disabled"><a class="page-link"
							href="/item/paging?page=${i}&searchText=${param.searchText}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/item/paging?page=${i}&searchText=${param.searchText}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 -->
			<c:choose>
				<c:when test="${itemList.last}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/item/paging?page=${itemList.number+2}&searchText=${param.searchText}">&rarr;</a></li>
					<li class="page-item"><a class="page-link"
						href="/item/paging?page=${itemList.totalPages}&searchText=${param.searchText}">마지막</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- 페이징 영역 끝 -->
	
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