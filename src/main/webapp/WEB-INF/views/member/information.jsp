<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Imformation</title>
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	
	<h2>회원정보</h2>
	<table class="table table-striped table-hover">
		<tr>
			<th>아이디</th>
			<td>${memberInformation.memberId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${memberInformation.memberName}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${memberInformation.memberEmail}</td>
		</tr>
		<tr>
			<th>생성일</th>
			<td>${memberInformation.memberCreateDate}</td>
		</tr>
		<tr>
			<th>수정일</th>
			<td>${memberInformation.memberUpdateDate}</td>
		</tr>
	</table>
	<a href="/cart/list/${sessionScope.memberNo}" class="btn btn-primary">장바구니 목록</a>
	<a href="/order/list/${sessionScope.memberNo}" class="btn btn-primary">주문내역 목록</a>
	<a href="/member/updateCheck" class="btn btn-primary">수정하기</a>
	<a href="/member/deleteCheck" class="btn btn-primary">탈퇴하기</a>

	<script type="text/javascript">
		const redirectAlert = "${msg}";
		if(redirectAlert == "memberUpdateSuccess"){
			alert("회원정보가 수정되었습니다.");
		}
		
		
	</script>

</body>
</html>