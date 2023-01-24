<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Cart Item</title>
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<%@ include file="/WEB-INF/views/include/navbar.jsp" %>
	
	
	<h2>주문하기</h2>
	
		<h3>상품정보</h3>
		<table class="table table-striped table-hover">
			<tr>
				<th>상품이미지</th>
				<th>상품명</th>
				<th>상품가격</th>
				<th>선택한갯수</th>
				<th>상품총가격</th>

			</tr>
			<c:forEach items="${cartOrderList}" var="cartOrderList">
				<tr>
					<td><img src="/upload/${cartOrderList.storedItemFile}" alt="이미지"
						style="width: 100px; height: 100px;">
					</td>
					<td>${cartOrderList.itemTitle}</td>
					<td>${cartOrderList.itemPrice}원</td>
					<td>${cartOrderList.cartItemAmount}개</td>
					<td>${cartOrderList.itemPrice * cartOrderList.cartItemAmount}원</td>
				</tr>
			</c:forEach>
		</table>
	
	<br/>

	
	<br/>
	
	<h3>회원정보</h3>
	<div>
		아이디 이름 이메일 
	</div>
	
	<br/>
	
	<div>
		총 결제금액 : ?
	</div>

	<br/>
	
	<button type="button" class="btn btn-primary"
			onclick="location.href='/order/success';">결제하기</button>
</body>
</html>