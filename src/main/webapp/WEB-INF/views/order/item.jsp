<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Item</title>
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
<body  class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<%@ include file="/WEB-INF/views/include/navbar.jsp" %>
	
	
	<h2>주문하기</h2>

	
	<br/>
	
	<h2>상품정보</h2>
	<div>
		상품이름 상품가격 상품내용 선택한 수량
	</div>
	
	<br/>
	
	<h2>배송정보</h2>
	<div>
		이름 전화번호 주소
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