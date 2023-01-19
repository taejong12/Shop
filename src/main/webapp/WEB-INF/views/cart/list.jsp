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
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>


	<h2>장바구니 목록</h2>
	<form>
		<table class="table table-striped table-hover">
			<tr>
				<th><input type="checkbox" name="selectAll" id="selectAll"/>전체선택</th>
				<th>상품이미지</th>
				<th>상품명</th>
				<th>상품가격</th>
				<th>선택한갯수</th>
				<th>상품총가격</th>
				<th>삭제</th>

			</tr>
			<c:forEach items="${cartList.content}" var="cartList">
				<tr>
					<td><input type="checkbox" name="cartCheckbox" class="cartCheckbox" data-cartNo="${cartList.cartNo}"></td>
					<td><img src="/upload/${cartList.storedItemFile}" alt="이미지"
						style="width: 100px; height: 100px;"></td>
					<td><a href="/item/detail/${cartList.itemNo}">${cartList.itemTitle}</a></td>
					<td>${cartList.itemPrice}원</td>
					<td>
						<div>
							<input type="hidden" name="itemNo" class="itemNo" value="${cartList.itemNo}" />
							<button type="button" class="cartItemPlus">+</button>
							<input type="text" name="cartItemAmount" class="cartItemAmount" value="${cartList.cartItemAmount}" />개
							<button type="button" class="cartItemReduction">-</button>
							<button type="button" class="cartItemAmountModify" data-cartno="${cartList.cartNo}">변경</button>
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

		<button type="button" class="btn btn-primary"
			onclick="location.href='/order/cartItem';">주문하기</button>
		<button type="button" class="btn btn-primary" id="cartCheckDelete">선택삭제</button>
	</form>
	
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
		
		$(".cartItemAmountModify").click(function(){
			
			if(confirm("변경하시겠습니까?")){
				
				
				const cartItemAmountModify = $(this).parent('div').find("input[name=cartItemAmount]").val();
				const itemNo = $(this).parent('div').find("input[class='itemNo']").val();
				const cartNo = $(this).data("cartno");
				
				console.log(cartItemAmountModify);
				console.log(itemNo);
				console.log(cartNo);
				
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
						alert("변경완료.");
						location.href="/cart/list/"+"${sessionScope.memberNo}";
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
		});
		
		// 전체가 아니면 전체선택 해제
		$(".cartCheckbox").click(function(){
			$("#selectAll").prop("checked", false);
		});
	
		
		// 선택 삭제
		$("#cartCheckDelete").click(function(){
			
			if(!$(".cartCheckbox").prop("checked")){
				alert("삭제하실 장바구니를 선택해주세요.");
				return false;
			}
			
			let confirmVal = confirm("정말 삭제하시겠습니까?");
			
			if(confirmVal){
				let checkArr = new Array();
				
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