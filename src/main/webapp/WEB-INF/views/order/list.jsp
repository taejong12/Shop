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

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>주문내역 목록</h2>
	
	<table class="table table-striped table-hover">
		<tr>
			<th>주문번호</th>
			<th>상품이미지</th>
			<th>상품명</th>
			<th>상품가격</th>			
			<th>상품갯수</th>
			<th>결제가격</th>
			<th>주문아이디</th>
			<th>주문날짜</th>
			<th>결제상태</th>
			<th>결제취소</th>			
		</tr>
		<c:forEach items="${orderList.content}" var="orderList">
			<tr>
				<td id="orderNo">${orderList.orderNo}</td>
				<td>
					<img src="/upload/${orderList.storedItemFile}" alt="이미지"
						style="width: 100px; height: 100px;">
				</td>
				<td>${orderList.itemTitle}</td>
				<td>${orderList.itemPrice}</td>
				<td>${orderList.cartItemAmount}</td>
				<td>${orderList.itemTotalPrice}</td>
				<td>${orderList.memberId}</td>
				<td>${orderList.orderItemCreateDate}</td>		
				<td>${orderList.itemPayStatus}</td>		
				<td>
					<button type="button" class="btn btn-primary" id="orderCancel">결제취소</button>
				</td>		
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		$("#orderCancel").click(function(){
			
			if(confirm("정말 결제취소하시겠습니까?")){
				
				
				const orderNo =	
				
				const orderCancelData ={
					"memberNo"	:	${sessionScope.memberNo},
					"orderNo"	:	orderNo,
					"itemPayStatus"	:	"결제취소"
				}
				
			}
		});
		
	</script>
</body>
</html>