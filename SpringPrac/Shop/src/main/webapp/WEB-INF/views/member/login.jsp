<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Login</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	
	<c:choose>
		<c:when test="${memberId eq null }">
			<h2>로그인</h2>
			<form action="/member/login" method="post">
				<table class="table table-striped table-hover">
					<tr>
						<th>아이디</th>
						<td><input type="text" class="form-control" name="memberId" /></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" class="form-control" name="memberPassword" /></td>
					</tr>
				</table>
				<input type="submit" class="btn btn-primary" value="로그인" />
			</form>
		</c:when>
		<c:otherwise>
			<div>
				${memberId}님, 환영합니다. <a href="/member/logout">로그아웃</a> <a href="/">회원정보</a>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>