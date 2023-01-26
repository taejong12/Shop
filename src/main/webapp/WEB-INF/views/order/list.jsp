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
				<td>${orderList.orderNo}</td>
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
					<input type="hidden" class="orderNo" value="${orderList.orderNo}"/>
					<input type="hidden" class="itemNo" value="${orderList.itemNo}"/>
					<input type="hidden" class="itemCount" value="${orderList.cartItemAmount}"/>
					<input type="hidden" class="itemTotalPrice" value="${orderList.itemTotalPrice}"/>
					<c:if test="${orderList.itemPayStatus != '결제취소'}">
						<button type="button" class="btn btn-primary" id="orderCancel">결제취소</button>
					</c:if>
					<c:if test="${orderList.itemPayStatus == '결제취소'}">
						${orderList.orderItemUpdateDate}
					</c:if>
				</td>		
			</tr>
		</c:forEach>
	</table>
	
	<!-- 페이징 영역 시작 -->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">

			<!-- 이전 -->
			<c:choose>
				<c:when test="${orderList.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/order/list/${sessionScope.memberNo}?page=1">처음</a></li>
					<li class="page-item"><a class="page-link"
						href="/order/list/${sessionScope.memberNo}?page=${orderList.number}">&larr;</a></li>
				</c:otherwise>
			</c:choose>

			<!-- 페이지 그룹 -->
			<c:forEach begin="${startPage}" end="${endPage}" var="i">
				<c:choose>
					<c:when test="${orderList.pageable.pageNumber+1==i}">
						<li class="page-item disabled"><a class="page-link"
							href="/order/list/${sessionScope.memberNo}?page=${i}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/order/list/${sessionScope.memberNo}?page=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 -->
			<c:choose>
				<c:when test="${orderList.last}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/order/list/${sessionScope.memberNo}?page=${orderList.number+2}">&rarr;</a></li>
					<li class="page-item"><a class="page-link"
						href="/order/list/${sessionScope.memberNo}?page=${orderList.totalPages}">마지막</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- 페이징 영역 끝 -->

	
	<script type="text/javascript">
		$("[id^=orderCancel]").click(function(){
			
			// 반복문 처리
			if(confirm("정말 결제취소하시겠습니까?")){
				
				const orderNo = $(this).parent().find(".orderNo").val();
				const itemNo = $(this).parent().find(".itemNo").val();
				const cartItemAmount = $(this).parent().find(".itemCount").val();
				const itemTotalPrice = $(this).parent().find(".itemTotalPrice").val();
				
				const orderCancelData ={
					"memberNo"	:	${sessionScope.memberNo},
					"orderNo"	:	orderNo,
					"itemPayStatus"	:	"결제취소",
					"itemNo" : itemNo,
					"cartItemAmount" : cartItemAmount,
					"itemTotalPrice" : itemTotalPrice
				}
				
				console.log(orderCancelData);
				
				 $.ajax({
						url:"/order/cancel",
						type:"post",
						data:orderCancelData,
						success:function(data){
							if(data=="orderCancel"){
								alert("결제취소완료.");
								location.href="/order/list/"+"${sessionScope.memberNo}";
							} else {
								alert("결제취소실패.");
							}
						},
						error:function(){
							alert("주문내역 결제 취소 중 에러 발생");
						}
					}) 
				
				
			}
		});
		
	</script>
</body>
</html>