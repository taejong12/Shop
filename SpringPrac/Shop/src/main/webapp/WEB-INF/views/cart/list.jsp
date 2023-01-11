<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart List</title>
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	
	<h2>장바구니 목록</h2>
	
	<button type="button" class="btn btn-primary"
		onclick="location.href='/order/cartItem';">주문하기</button>
</body>
</html>