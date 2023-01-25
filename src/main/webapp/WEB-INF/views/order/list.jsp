<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Order List</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>
	
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>주문내역 목록</h2>
	
	<table>
		<tr>
			<th>주문번호</th>
			<th>주문아이디</th>
			<th>주문날짜</th>
			<th>주문상품</th>
			<th>상품갯수</th>
			<th>결제가격</th>
			<th>결제상태</th>
			<th>결제취소</th>			
		</tr>
		<c:forEach items="">
			<tr>
				<th>주문번호</th>
				<th>주문아이디</th>
				<th>주문날짜</th>
				<th>주문상품</th>
				<th>상품갯수</th>
				<th>결제가격</th>
				<th>결제상태</th>
				<th>결제취소</th>		
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>