<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Detail</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>
</head>
<body class="text-center">
	

	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>상품 상세보기</h2>
	<form>
		<table class="table table-striped table-hover">
			<c:if test="${itemDetailList.itemFileAttached == 1}">
				<tr>
					<th>상품이미지</th>
					<td>
						<img src="/upload/${itemDetailList.storedItemFile}" alt="이미지" style="float: left;  margin-right: 20px; margin-bottom: 10px; width:200px; height:200px;">
					</td>
				</tr>
			</c:if>
			<tr>
				<th>상품명</th>
				<td>${itemDetailList.itemTitle}</td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td>${itemDetailList.itemPrice}</td>
			</tr>
			<tr>
				<th>상품재고</th>
				<td>${itemDetailList.itemStock}</td>
			</tr>
			<tr>
				<th>상품조회수</th>
				<td>${itemDetailList.itemHits}</td>
			</tr>
			<tr>
				<th>상품등록일</th>
				<td>${itemDetailList.itemCreateDate}</td>
			</tr>
			<tr>
				<th>상품내용</th>
				<td>${itemDetailList.itemContent}</td>
			</tr>
			
			<tr>
				<th>상품수량</th>
				<td>
					<input type="hidden" class="itemStock" value="${itemDetailList.itemStock}"/>
					<input class="volumebtn btn btn-light" type="button" value=" + " onclick="itemAdd();">
					<input type="text" id="itemAmount" value="0" onkeyup="itemAmountChange(this);" onkeydown="if(event.keyCode=='13'){event.preventDefault();}"/>
					<input class="volumebtn btn btn-light" type="button" value=" - " onclick="itemDel();">
				</td>
			</tr>
			
			<tr>
				<th>상품총가격</th>
				<td id="itmePriceSum">0</td>
			</tr>
		</table>
	</form>
	
	<c:choose>
		<c:when test="${sessionScope.memberNo != null}">
			<div class="d-flex justify-content-center">
				<button type="button" class="btn btn-primary"
					onclick="cartItemCheck()">장바구니 추가</button>&nbsp
				<button type="button" class="btn btn-primary"
					onclick="location.href='/order/item/${itemDetailList.itemNo}';">주문하기</button>&nbsp
				<button type="button" class="btn btn-primary"
					onclick="location.href='/item/update/${itemDetailList.itemNo}';">수정</button>&nbsp
				<button type="button" class="btn btn-primary"
					onclick="itmeDeleteCheck()">삭제</button>
			</div>
		</c:when>
		<c:otherwise>
			<div class="d-flex justify-content-center">
				로그인시 이용 가능
			</div>
			<div class="d-flex justify-content-center">
				<button type="button" class="btn btn-primary"
					onclick="location.href='/member/login';">로그인</button>
			</div>
		</c:otherwise>
	</c:choose>
	<!-- 로그인시 그 전에 보던 페이지로 이동(할일) -->
	
	<script type="text/javascript">
	
		// 삭제 버튼 클릭시
		function itmeDeleteCheck(){
			if(confirm("정말 삭제하시겠습니까?")){				
				location.href='/item/delete/${itemDetailList.itemNo}';
				return false;
			}  else {
				return false;
			}
		}
		
		// 장바구니 버튼 클릭시
		function cartItemCheck(){
			
			if(document.getElementById("itemAmount").value <= 0){
				alert("수량을 선택해주세요.");
				return false;
			}
			
			const itemStock = $(".itemStock").val();
			
			if(document.getElementById("itemAmount").value > itemStock){
				alert("재고수량이 "+ itemStock +" 개 남았습니다.");
				return false;
			}
			
			
			const itemAmountValue = document.getElementById("itemAmount").value;
			
			const cartCheck ={
				"memberNo" : ${sessionScope.memberNo},
				"itemNo" : ${itemDetailList.itemNo}
			}
			
			// 장바구니 중복 체크
			$.ajax({
				type:"post",
				url:"/cart/check",
				data:cartCheck,
				success:function(data){
					console.log("data : "+data);
					console.log("cartNo : "+data.cartNo);
					console.log("itemNo : "+data.itemNo);
				
					const itemNo = "${itemDetailList.itemNo}";
					const memberNo = "${sessionScope.memberNo}";
					console.log("itemNo : "+itemNo);
						
					// 데이터 받아와서 멤버 넘버랑 상품 넘버 비교해서 장바구니 중복 확인하기
					if(data.itemNo == itemNo && data.memberNo == memberNo){
						if(confirm("이미 장바구니에 있습니다. 장바구니로 이동하시겠습니까?")){
							location.href='/cart/list/${sessionScope.memberNo}';
							return false;
						}  else {
							return false;
						}
					}
					
					const cartData ={
							"memberNo" : ${sessionScope.memberNo},
							"itemNo" : ${itemDetailList.itemNo},
							"cartItemAmount" : itemAmountValue
						}
					
					// 장바구니 추가
					$.ajax({
						type:"post",
						url:"/cart/item",
						data: cartData,
						success:function(data){
							console.log("data : "+data);
							console.log("cartNo : "+data.cartNo);
							console.log("itemNo : "+data.itemNo);

							if(confirm("장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?")){
								location.href='/cart/list/${sessionScope.memberNo}';
								return false;
							}  else {
								return false;
							}
						
						},
						error : function(){
							alert("장바구니 추가 ajax 실행 실패");
						}
						
					})		
					
				},
				error:function(){
					alert("장바구니 중복확인 ajax 실행 실패");
				}
			})
				
		}
	
		const redirectAlert = "${msg}";
		if(redirectAlert == "itemUpdateSuccess"){
			alert("상품이 수정되었습니다.");
		}
		
	</script>
	
	<script type="text/javascript">
		
		let itemAmount;
		let itemPrice;
		let itemPriceSum;
		
		// 상품 수량 증가
		function itemAdd(){
			itemAmount = document.getElementById("itemAmount");
			itemAmount.value ++ ;
			
			itemPrice = "${itemDetailList.itemPrice}";
			itemPriceSum = parseInt(itemAmount.value) * itemPrice;
			
			if(isNaN(itemPriceSum)){
				itemPriceSum=0;
			}
			
			document.getElementById('itmePriceSum').innerHTML=itemPriceSum;
			
		}
		
		// 상품 수량 감소
		function itemDel(){
			itemAmount = document.getElementById("itemAmount");
			itemPrice = "${itemDetailList.itemPrice}";
			
			if(itemAmount.value > 0) {
				itemAmount.value -- ;
				itemPriceSum = parseInt(itemAmount.value) * itemPrice;
				
				if(isNaN(itemPriceSum)){
					itemPriceSum=0;
				}
				
				document.getElementById('itmePriceSum').innerHTML=itemPriceSum;
				
			}
		}
		
		// 총 상품 수량
		function itemAmountChange(itemAmount){
			
			itemAmount.value=itemAmount.value.replace(/(^0+)/g, "").trim();
			itemAmount.value=itemAmount.value.replace(/[^0-9]/g, "").trim();
			
			itemAmount = document.getElementById("itemAmount");
			itemPrice = "${itemDetailList.itemPrice}";
			
			if(itemAmount.value<0){
				itemAmount.value=0;
			}
			
			itemPriceSum = parseInt(itemAmount.value) * itemPrice;
			
			if(isNaN(itemPriceSum)){
				itemPriceSum=0;
			}
			
			document.getElementById('itmePriceSum').innerHTML=itemPriceSum;
		}
	
	</script>
</body>
</html>