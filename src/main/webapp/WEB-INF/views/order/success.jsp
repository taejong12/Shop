<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Success</title>

<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			if("${sessionScope.memberNo}" == null || "${sessionScope.memberNo}" == ""){
				location="/member/login";
			}
		});
	</script>
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	
	<h2>결제완료</h2>

	<button type="button" class="btn btn-primary"
			onclick="location.href='/order/list/${sessionScope.memberNo}';">주문내역</button>
	
</body>
</html>