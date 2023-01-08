<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제완료</title>
</head>
<body>
	
	<h1>결제가 완료되었습니다.</h1>
	<button type="button" onclick="location.href='/'">홈페이지</button>
	<button type="button" onclick="location.href='/orderList/${SPRING_SECURITY_CONTEXT.authentication.principal.user.username }'">주문내역확인</button>

</body>
</html>