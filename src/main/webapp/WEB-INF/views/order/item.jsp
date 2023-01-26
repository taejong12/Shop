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

	<h3>상품정보</h3>
	<table class="table table-striped table-hover">
		<tr>
			<th>상품이미지</th>
			<th>상품명</th>
			<th>상품가격</th>
			<th>선택한갯수</th>
			<th>상품총가격</th>

		</tr>
		
		<tr>
			<td><img src="/upload/${itemDetailOrderList.storedItemFile}"
				alt="이미지" style="width: 100px; height: 100px;"></td>
			<td>${itemDetailOrderList.itemTitle}</td>
			<td>${itemDetailOrderList.itemPrice}원</td>
			<td>${itemDetailOrderList.cartItemAmount}개</td>
			<td class="cartOrderPrice">
				${itemDetailOrderList.itemTotalPrice}원
				<input type="hidden" class="itemPrice" value="${itemDetailOrderList.itemPrice}">
				<input type="hidden" class="itemTotalCount" value="${itemDetailOrderList.cartItemAmount}"> 
				<input type="hidden" class="itemTotalPrice" value="${itemDetailOrderList.itemTotalPrice}">
				<input type="hidden" class="itemNo" value="${itemDetailOrderList.itemNo}">
				<input type="hidden" class="itemPayStatus" value="결제완료">
			</td>
		</tr>
		
	</table>

	<br />

	<h3>회원정보</h3>
	<table class="table table-striped table-hover">
		<tr>
			<th>아이디</th>
			<td>${memberInfo.memberId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${memberInfo.memberName}</td>
		</tr>	
		<tr>
			<th>이메일</th>
			<td>${memberInfo.memberEmail}</td>
		</tr>
	</table>

	<br />
	
	<!-- 가격 종합 -->
	<div class="detailright">

		<h3>결제정보</h3>

		<div>총 상품 가격</div>
		<div>
			<p>
				<span class="totalPrice_span">0</span>원
			</p>
		</div>

		<div>배송비</div>
		<div>
			<p>
				<span class="deliveryPrice_span">0</span>원
			</p>
		</div>

		<div>총 주문 상품수</div>
		<div>
			<span class="totalKind_span">0</span>종 
			<span class="totalCount_span">0</span>개
		</div>

		<hr>

		<div>
			<div>최종 결제 금액</div>
			<div class="strong_red">
				<span class="finalTotalPrice_span"> 0 </span>원
			</div>
		</div>
	</div>
	
	<br />
	
	<!-- 결제 버튼 -->
	<button type="button" class="btn btn-primary" id="cartOrderBtn">결제하기</button>
		
	<!-- 결제 form -->
	<form action="/order/success/${sessionScope.memberNo}" class="cartOrder" ></form>
		
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			// 주문 정보 최신화
			setTotalInfo();
		});
	
		// 상품 총 가격 정보
		function setTotalInfo(){
		
			let totalPrice = 0;				// 총 가격
			let totalCount = 0;				// 총 갯수
			let totalKind = 0;				// 총 종류
			let deliveryPrice = 0;			// 배송비
			let finalTotalPrice = 0; 		// 최종 가격(총 가격 + 배송비)	
			
			// 총 주문 가격 정보(총 가격, 총 갯수, 배송비, 종류)
			$(".cartOrderPrice").each(function(index, element){
				
				// 총 가격
				totalPrice += parseInt($(element).find(".itemTotalPrice").val());
				
				// 총 갯수
				totalCount += parseInt($(element).find(".itemTotalCount").val());
				
				// 총 종류
				totalKind += 1;
			});	
		
			/* 배송비 결정 */
			if(totalPrice >= 30000){
				deliveryPrice = 0;
			} else if(totalPrice == 0){
				deliveryPrice = 0;
			} else {
				deliveryPrice = 3000;	
			}
			
			finalTotalPrice = totalPrice + deliveryPrice;	
			
			
			/* 값 삽입 */
			// 총 가격
			$(".totalPrice_span").text(totalPrice.toLocaleString());
			// 총 갯수
			$(".totalCount_span").text(totalCount);
			// 총 종류
			$(".totalKind_span").text(totalKind);
			// 배송비
			$(".deliveryPrice_span").text(deliveryPrice.toLocaleString());	
			// 최종 가격(총 가격 + 배송비)
			$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());			
			
		}
	
		// 결제하기 버튼 클릭시
		$("#cartOrderBtn").click(function(){
			
			let form_contents ='';
			let orderNumber = 0;
			
			$(".cartOrderPrice").each(function(index, element){
				
				// 상품 넘버
				let itemNo = $(element).find(".itemNo").val();
				
				// 상품 총 갯수
				let cartItemAmount = $(element).find(".itemTotalCount").val();
				
				// 상품 총 가격
				let itemTotalPrice = $(element).find(".itemTotalPrice").val();
				
				// 결제상태
				let itemPayStatus = $(element).find(".itemPayStatus").val();
				
				let itemNo_input =  "<input name='orders[" + orderNumber + "].itemNo' type='hidden' value='" + itemNo + "'>";
				form_contents += itemNo_input;
				
				let cartItemAmount_input = "<input name='orders[" + orderNumber + "].cartItemAmount' type='hidden' value='" + cartItemAmount + "'>";
				form_contents += cartItemAmount_input;
				
				let itemTotalPrice_input = "<input name='orders[" + orderNumber + "].itemTotalPrice' type='hidden' value='" + itemTotalPrice + "'>";
				form_contents += itemTotalPrice_input;
				
				let itemPayStatus_input = "<input name='orders[" + orderNumber + "].itemPayStatus' type='hidden' value='" + itemPayStatus + "'>";
				form_contents += itemPayStatus_input;
				
				orderNumber += 1;
				
			});
			
			$(".cartOrder").html(form_contents);
			$(".cartOrder").submit();
			
		});
	</script>
	
</body>
</html>