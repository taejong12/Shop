<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Detail</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>상품 상세보기</h2>
	<table class="table table-striped table-hover">
		<c:if test="${itemDetailList.itemFileAttached == 1}">
			<tr>
				<th>상품이미지</th>
				<td>
					<img src="/upload/${itemDetailList.storedItemFile}" alt="이미지" style="float: left;  margin-right: 20px; margin-bottom: 10px; width:200px; height:200px;">
				</td>
			</tr>
		</c:if>
		<tr>
			<th>상품명</th>
			<td>${itemDetailList.itemTitle}</td>
		</tr>
		<tr>
			<th>상품가격</th>
			<td>${itemDetailList.itemPrice}</td>
		</tr>
		<tr>
			<th>상품재고</th>
			<td>${itemDetailList.itemStock}</td>
		</tr>
		<tr>
			<th>상품조회수</th>
			<td>${itemDetailList.itemHits}</td>
		</tr>
		<tr>
			<th>상품등록일</th>
			<td>${itemDetailList.itemCreateDate}</td>
		</tr>
		<tr>
			<th>상품내용</th>
			<td>${itemDetailList.itemContent}</td>
		</tr>
	</table>
	
	<c:choose>
		<c:when test="${sessionScope.memberNo != null}">
			<div class="d-flex justify-content-center">
				<button type="button" class="btn btn-primary"
					onclick="cartItemCheck()">장바구니 추가</button>&nbsp
				<button type="button" class="btn btn-primary"
					onclick="location.href='/order/item/${itemDetailList.itemNo}';">주문하기</button>&nbsp
				<button type="button" class="btn btn-primary"
					onclick="location.href='/item/update/${itemDetailList.itemNo}';">수정</button>&nbsp
				<button type="button" class="btn btn-primary"
					onclick="itmeDeleteCheck()">삭제</button>
			</div>
		</c:when>
		<c:otherwise>
			<div class="d-flex justify-content-center">
				로그인시 이용 가능
			</div>
			<div class="d-flex justify-content-center">
				<button type="button" class="btn btn-primary"
					onclick="location.href='/member/login';">로그인</button>
			</div>
		</c:otherwise>
	</c:choose>
	<!-- 로그인시 그 전에 보던 페이지로 이동(할일) -->

	
	<script type="text/javascript">
		function itmeDeleteCheck(){
			if(confirm("정말 삭제하시겠습니까?")){				
				location.href='/item/delete/${itemDetailList.itemNo}';
			}  else {
				return false;
			}
		}
		
		function cartItemCheck(){
			if(confirm("장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?")){				
				location.href='/cart/list/${sessionScope.memberNo}';
			}  else {
				return false;
			}
		}
	
		const redirectAlert = "${msg}";
		if(redirectAlert == "itemUpdateSuccess"){
			alert("상품이 수정되었습니다.");
		}
	</script>
</body>
</html>