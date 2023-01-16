<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>홈페이지</h2>

	<script type="text/javascript">
		const redirectAlert = "${msg}";
		if(redirectAlert == "loginSuccess"){
			alert("로그인 완료.");
		}
		
		if(redirectAlert == "logoutSuccess"){
			alert("로그아웃 완료.")
		}
		
		if(redirectAlert == "memberDeleteSuccess"){
			alert("회원탈퇴 완료.")
		}
		
	</script>


</body>
</html>