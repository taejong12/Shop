<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart List</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
	
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

	<h2>장바구니 목록</h2>
	
		<table class="table table-striped table-hover">
			<tr>
				<th><input type="checkbox" name="selectAll" id="selectAll" />전체선택</th>
				<th>상품이미지</th>
				<th>상품명</th>
				<th>상품가격</th>
				<th>선택한갯수</th>
				<th>상품총가격</th>
				<th>삭제</th>

			</tr>
			<c:forEach items="${cartList.content}" var="cartList">
				<tr>
					<td class="cartPrice">
						<input type="hidden" class="itemPrice" value="${cartList.itemPrice}">
						<input type="hidden" class="itemTotalCount" value="${cartList.cartItemAmount}"> 
						<input type="hidden" class="itemTotalPrice" value="${cartList.itemPrice * cartList.cartItemAmount}">
						<input type="hidden" class="itemNo" value="${cartList.itemNo}">
						<input type="hidden" class="cartNo" value="${cartList.cartNo}">						
						<input type="checkbox" name="cartCheckbox"
							class="cartCheckbox" data-cartNo="${cartList.cartNo}">
					</td>
					<td><img src="/upload/${cartList.storedItemFile}" alt="이미지"
						style="width: 100px; height: 100px;">
					</td>
					<td><a href="/item/detail/${cartList.itemNo}">${cartList.itemTitle}</a></td>
					<td>${cartList.itemPrice}원</td>
					<td>
						<div>
							<input type="hidden" class="itemStock" value="${cartList.itemStock}">
							<input type="hidden" name="itemNo" class="itemNo"
								value="${cartList.itemNo}" />
							<button type="button" class="cartItemPlus">+</button>
							<input type="text" name="cartItemAmount" class="cartItemAmount"
								value="${cartList.cartItemAmount}" />개
							<button type="button" class="cartItemReduction">-</button>
							<button type="button" class="cartItemAmountModify"
								data-cartno="${cartList.cartNo}">변경</button>
						</div>
					</td>
					<td>${cartList.itemPrice * cartList.cartItemAmount}원</td>
					<td>
						<button type="button" class="btn btn-primary"
							onclick="if(confirm('정말 삭제하시겠습니까?')) {
						location.href='/cart/delete/${cartList.cartNo}';
						} return false;">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</table>

		<!-- 페이징 영역 시작 -->
		<div class="text-xs-center">
			<ul class="pagination justify-content-center">

				<!-- 이전 -->
				<c:choose>
					<c:when test="${cartList.first}"></c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/cart/list/${sessionScope.memberNo}?page=1">처음</a></li>
						<li class="page-item"><a class="page-link"
							href="/cart/list/${sessionScope.memberNo}?page=${cartList.number}">&larr;</a></li>
					</c:otherwise>
				</c:choose>

				<!-- 페이지 그룹 -->
				<c:forEach begin="${startPage}" end="${endPage}" var="i">
					<c:choose>
						<c:when test="${cartList.pageable.pageNumber+1==i}">
							<li class="page-item disabled"><a class="page-link"
								href="/cart/list/${sessionScope.memberNo}?page=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="/cart/list/${sessionScope.memberNo}?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- 다음 -->
				<c:choose>
					<c:when test="${cartList.last}"></c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/cart/list/${sessionScope.memberNo}?page=${cartList.number+2}">&rarr;</a></li>
						<li class="page-item"><a class="page-link"
							href="/cart/list/${sessionScope.memberNo}?page=${cartList.totalPages}">마지막</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- 페이징 영역 끝 -->

		<!-- 가격 종합 -->
		<div class="content_total_section">
			<div class="total_wrap">
				<table>
					<tr>
						<td>총 상품 가격</td>
						<td><span class="totalPrice_span">0</span> 원</td>
					</tr>
					<tr>
						<td>배송비</td>
						<td><span class="deliveryPrice_span">0</span> 원</td>
					</tr>
					<tr>
						<td>총 주문 상품수</td>
						<td>
							<span class="totalKind_span">0</span>종 
							<span class="totalCount_span">0</span>개
						</td>
					</tr>
				</table>
				
				<hr>

				<table>
					<tr>
						<th>총 결제 예상 금액</th>
						<td><span class="finalTotalPrice_span">0</span> 원</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 주문버튼 -->
		<button type="button" class="btn btn-primary" id="cartOrderPageBtn">주문하기</button>
		
		<!-- 일괄삭제 버튼 -->
		<button type="button" class="btn btn-primary" id="cartCheckDelete">선택삭제</button>
		
		<!-- 주문 form -->
		<form action="/order/cartItem/${sessionScope.memberNo}" class="cartOrderForm" ></form>
			
			
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			// 종합 정보 섹션 정보 삽입
			setTotalInfo();
			
		});
		
		// 체크박스 선택 여부에 따른 종합 정보 변화
		$(".cartCheckbox").on("change", function(){
			
			// 총 주문 정보 세팅(총 가격, 총 갯수, 총 종류, 배송비, 최종 가격)
			setTotalInfo($(".cartPrice"));
		
		});
	
		// 주문하기 버튼 클릭시 발생
		$("#cartOrderPageBtn").click(function(){
			
			let cartCheckbox = $(".cartCheckbox");
			
			let num = 0;
			
			for(let i=0; i<cartCheckbox.length;i++){
				if(cartCheckbox[i].checked){
					num++;
				}
			}
			
			if(!num){
				alert("상품을 선택해주세요.");
				return false;
			}
			
			// 동적으로 생성할 <input> 태그 문자열 값이 저장될 변수 선언 및 초기화
			let form_contents ='';
			
			// <input>의 name값에 orders[0], orders[1], orders[2] 와 값이 index 값을 주어야 하는데 이러한 index값 역할을 할 orderNumber 변수를 선언하고 0으로 초기화합니다.  
			let orderNumber = 0;
			
			// 상품의 데이터가 저장된 <input> 값들을 감싸고 있는 <td> 태그 반복해서 접근하는 메서드를 추가합니다.
			$(".cartPrice").each(function(index, element){
				
				/*  
					is() 메서드는 선택한 요소 중 하나가 is( 선택한 Element )와 일치하는지 확인합니다.
					결과값은 참(true)/거짓(false)을 나타냅니다.
					체크여부 
				*/
				if($(element).find(".cartCheckbox").is(":checked") === true){
						
					// 변수를 선언하여 접근한 <td>태그 내부에 있는 <input> 태그의 값들로 초기화해줍니다.
					// 상품 넘버
					let itemNo = $(element).find(".itemNo").val();
					
					// 장바구니 총 갯수
					let cartItemAmount = $(element).find(".itemTotalCount").val();
					
					// 장바구니 넘버
					let cartNo = $(element).find(".cartNo").val();
					
					// 두 변수의 값과 index(each 메서드 첫 번째 인자) 값을 활용해서 서버로 전송되어야 할 <input> 태그를 문자열 값을 만들어내고 앞서 선언한 form_contents 변수에 문자열을 더해줍니다.
					let itemNo_input =  "<input name='orders[" + orderNumber + "].itemNo' type='hidden' value='" + itemNo + "'>";
					form_contents += itemNo_input;
					
					let cartItemAmount_input = "<input name='orders[" + orderNumber + "].cartItemAmount' type='hidden' value='" + cartItemAmount + "'>";
					form_contents += cartItemAmount_input;
					
					let cartNo_input = "<input name='orders[" + orderNumber + "].cartNo' type='hidden' value='" + cartNo + "'>";
					form_contents += cartNo_input;
					
					// 그리고 idnex값 역할을 하는 orderNumber는 다음 객체에 접근할 때 +1이 될 수 있도록 코드를 추가합니다.
					orderNumber += 1;
					
				}
				
			});
		
			// each 메서드를 벗어나서 form_contents 변수에 저장된 문자열(<input> 태그) 값을 주문 페이지 이동 <form> 태그 내부에 추가되도록 코드를 추가하고, <form> 태그를 서버로 전송합니다.
			$(".cartOrderForm").html(form_contents);
			$(".cartOrderForm").submit();
		
		});
		
		
		// 선택된 상품 총 정보
		function setTotalInfo(){
			
			let totalPrice = 0; // 총 가격
			let totalCount = 0; // 총 갯수
			let totalKind = 0; // 총 종류
			let deliveryPrice = 0; // 배송비
			let finalTotalPrice = 0; // 최종 가격(총 가격 + 배송비)
			
			// 총 주문 정보 세팅(배송비, 총 가격, 총 갯수, 종류, 최종 가격 )
			$(".cartPrice").each(function(index, element){
				
				// 체크여부
				if($(element).find(".cartCheckbox").is(":checked")=== true){
						
					// element == this
					
					// 선택된 상품의 총 가격
					totalPrice += parseInt($(element).find(".itemTotalPrice").val());
					
					// 선택된 상품의 총 갯수
					totalCount += parseInt($(element).find(".itemTotalCount").val());
					
					// 선택된 상품의 총 종류
					totalKind += 1;
				
				}
			
			});
			
			// 배송비 결정
			if(totalPrice >= 10000) {
				deliveryPrice = 0;
			} else if(totalPrice == 0){
				deliveryPrice = 0;
			} else {
				deliveryPrice = 3000;
			}
			
			// 최종 가격
			finalTotalPrice = totalPrice + deliveryPrice;
			
			// html 단으로 값 보내기 .text
			// 일반적인 숫자 형식이 아닌 통화형식으로 출력될 수 있도록 toLocaleString() 메서드 사용
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
		
	</script>

	<script type="text/javascript">
	
		// 수량 증가
		$(".cartItemPlus").click(function(){
			let cartItemAmount = $(this).parent('div').find("input[name=cartItemAmount]").val();
			$(this).parent("div").find("input[name=cartItemAmount]").val(++cartItemAmount);
		});
		
		// 수량 감소
		$(".cartItemReduction").click(function(){
			let cartItemAmount = $(this).parent('div').find("input[name=cartItemAmount]").val();
			if(cartItemAmount > 1 ){
				$(this).parent("div").find("input[name=cartItemAmount]").val(--cartItemAmount);
			}
		});
		
		// 변경 버튼 클릭시 발생
		$(".cartItemAmountModify").click(function(){
			
			if(confirm("변경하시겠습니까?")){
				
				// 클릭한 상품의 장바구니 총 갯수 값
				const cartItemAmountModify = $(this).parent('div').find("input[name=cartItemAmount]").val();
				
				// 클릭한 상품의 재고
				const itemStock = $(this).parent('div').find("input[class='itemStock']").val();
				
				// 클릭한 상품의 넘버
				const itemNo = $(this).parent('div').find("input[class='itemNo']").val();
				
				// 클릭한 상품의 장바구니 넘버
				const cartNo = $(this).data("cartno");
				
				if(cartItemAmountModify < 1){
					alert("1개 이상 선택해주세요.");
					return false;
				}
				
				if(cartItemAmountModify > itemStock){
					alert("재고수량이 "+ itemStock +" 개 남았습니다.");
					return false;
				}
				
				const cartItemAmountModifyData = {		
					"memberNo" : ${sessionScope.memberNo},
					"itemNo" : itemNo,
					"cartNo" : cartNo,
					"cartItemAmount" : cartItemAmountModify
				}
				
				 $.ajax({
					url:"/cart/amountModify",
					type:"post",
					data:cartItemAmountModifyData,
					success:function(data){
						if(data=="amountModify"){
							alert("변경완료.");
							location.href="/cart/list/"+"${sessionScope.memberNo}";
						} else {
							alert("변경실패.");
						}
					},
					error:function(){
						alert("장바구니 수량 변경 중 에러 발생");
					}
				}) 
			}
			
		});
	</script>

	<script type="text/javascript">
		
		// 전체 선택 및 해제
		$("#selectAll").click(function(){
			var chk = $("#selectAll").prop("checked");
			if(chk) {
				$(".cartCheckbox").prop("checked", true);
			} else {
				$(".cartCheckbox").prop("checked", false);
			}
			
			// 총 주문 정보 세팅(배송비, 총 가격, 총 갯수, 종류, 최종 가격 )
			setTotalInfo($(".cartCheckbox"));
			
		});
		
		// 전체가 아니면 전체선택 해제
		$(".cartCheckbox").click(function(){
			$("#selectAll").prop("checked", false);
		});
	
		// 선택 삭제
		$("#cartCheckDelete").click(function(){
			
			let cartCheckbox = $(".cartCheckbox");
			
			let num = 0;
			
			for(let i=0; i<cartCheckbox.length;i++){
				if(cartCheckbox[i].checked){
					num++;
				}
			}
			
			// if(!num) 은 if(num == 0) 과 같은 구문
			if(!num){
				alert("상품을 선택해주세요.");
				return false;
			}
			
			let confirmVal = confirm("정말 삭제하시겠습니까?");
			
			if(confirmVal){
				let checkArr = new Array();
				
				// 체크된 체크박스의 장바구니 넘버 값을 배열 담기
				$("input[class='cartCheckbox']:checked").each(function(){
					checkArr.push($(this).data("cartno"));
				});
				
				$.ajax({
					url:"/cart/delete",
					type:"post",
					data:{
						checkArr : checkArr
					},
					success : function(data){
						
						if(data == "checkDelete"){
							location.href="/cart/list/"+"${sessionScope.memberNo}";							
						} else{
							alert("삭제 실패.");
						}
					},
					error : function(){
						alert("장바구니 일괄 삭제 ajax 실행 실패");
					}
				});
				
			}
			
		});
		
		
	</script>

	<script type="text/javascript">
		const redirectAlert = "${msg}";
		if (redirectAlert == "cartDeleteSuccess") {
			alert("장바구니 삭제.");
		}
	</script>


</body>
</html>