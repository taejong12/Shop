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
		function itmeDeleteCheck(){
			if(confirm("정말 삭제하시겠습니까?")){				
				location.href='/item/delete/${itemDetailList.itemNo}';
			}  else {
				return false;
			}
		}
		
		
		function cartItemCheck(){
			
			if(document.getElementById("itemAmount").value <= 0){
				alert("수량을 선택해주세요.");
				return false;
			}
			
			const itemAmountValue = document.getElementById("itemAmount").value;
			console.log("itemAmountValue : "+itemAmountValue);
			const cartData ={
					"memberNo" : ${sessionScope.memberNo},
					"itemNo" : ${itemDetailList.itemNo},
					"cartItemAmount" : itemAmountValue
			}
			
			console.log("cartItemAmount : "+cartData.itemAmount);
			
			$.ajax({
				type:"post",
				url:"/cart/item",
				data: cartData,
				success:function(data){
					console.log(data);
					
					if(confirm("장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?")){			
						location.href='/cart/list/${sessionScope.memberNo}';
					}  else {
						return false;
					}
				
				},
				error : function(){
					alert("장바구니 추가 ajax 실행 실패");
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
		
		function itemAdd(){
			itemAmount = document.getElementById("itemAmount");
			itemAmount.value ++ ;
			console.log(itemAmount.value);
			itemPrice = "${itemDetailList.itemPrice}";
			itemPriceSum = parseInt(itemAmount.value) * itemPrice;
			console.log(itemPriceSum);
			if(isNaN(itemPriceSum)){
				itemPriceSum=0;
			}
			document.getElementById('itmePriceSum').innerHTML=itemPriceSum;
			
		}
		
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
		
		function itemAmountChange(itemAmount){
			
			  if (itemAmount.keyCode === 13) {
				  itemAmount.preventDefault();
				  };
			
		    
			itemAmount.value=itemAmount.value.replace(/(^0+)[0-9]/g, "");
			itemAmount = document.getElementById("itemAmount");
			console.log(itemAmount.value);
			itemPrice = "${itemDetailList.itemPrice}";
			if(itemAmount.value<0){
				itemAmount.value=0;
			}
			itemPriceSum = parseInt(itemAmount.value) * itemPrice;
			console.log(itemPriceSum);
			if(isNaN(itemPriceSum)){
				itemPriceSum=0;
			} 
			document.getElementById('itmePriceSum').innerHTML=itemPriceSum;
		}
		
		
	</script>
</body>
</html>