<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" href="/css/include/header.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body>
<c:choose>
	<c:when test="${memberId eq null}">
		<div class="header_right">
			<a href="/member/login" class="btn btn-primary">로그인</a>
			<a href="/member/signUp" class="btn btn-outline-primary me-2">회원가입</a>
		</div>
	</c:when>
	<c:otherwise>
		<div class="header_right">
			${memberId}님, 환영합니다.
			<a href="/member/information" class="btn btn-primary">회원정보</a>
			<a href="/member/logout" class="btn btn-outline-primary me-2">로그아웃</a>
		</div>
	</c:otherwise>
</c:choose>

</body>
</html>