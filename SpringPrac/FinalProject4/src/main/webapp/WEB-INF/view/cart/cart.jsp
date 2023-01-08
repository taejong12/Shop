<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>          
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>

<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>

<div class="content_subject"><span>장바구니</span></div>
			<!-- 장바구니 리스트 -->
			<div class="content_middle_section"></div>
			<!-- 장바구니 가격 합계 -->
			<!-- cartInfo -->
			<div class="content_totalCount_section">
				<!-- 체크박스 전체 여부 -->
				<div class="all_check_input_div">
					<input type="checkbox" class="all_check_input input_size_20" checked="checked"><span class="all_chcek_span">전체선택</span>
				</div>
				
				<table class="cart_table">
					
					<thead>
						<tr>
							<th class="td_width_1">선택</th>
							<th class="td_width_2">사진</th>
							<th class="td_width_3">상품명</th>
							<th class="td_width_4">가격</th>
							<th class="td_width_4">수량</th>
							<th class="td_width_4">합계</th>
							<th class="td_width_4">삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cartInfo}" var="ci">
							<tr>
								<td class="td_width_1 cart_info_td">
									<input type="checkbox" class="individual_cart_checkbox input_size_20" checked="checked">
									<input type="hidden" class="individual_alPrice_input" value="${ci.alPrice}">
									<input type="hidden" class="individual_caCount_input" value="${ci.caCount}">
									<input type="hidden" class="individual_totalPrice_input" value="${ci.alPrice * ci.caCount}">
									<input type="hidden" class="individual_alNum_input" value="${ci.alNum}">	 	
								</td>
								<td class="td_width_2"><img src="${ci.alOpic}" referrerpolicy="no-referrer" /></td>
								<td class="td_width_3">${ci.alName}</td>
								<td class="td_width_4 price_td">
									가격 : <fmt:formatNumber value="${ci.alPrice}" pattern="#,### 원" /><br>
									
									
								</td>
								<td class="td_width_4 table_text_align_center">
									<div class="table_text_align_center quantity_div">
										<input type="text" value="${ci.caCount}" class="quantity_input">	
										<button class="quantity_btn plus_btn">+</button>
										<button class="quantity_btn minus_btn">-</button>
									</div>
									<a class="quantity_modify_btn" data-caNum="${ci.caNum}">변경</a>
								</td>
								<td class="td_width_4 table_text_align_center">
									<fmt:formatNumber value="${ci.alPrice * ci.caCount}" pattern="#,### 원" />
								</td>
								<td class="td_width_4 table_text_align_center">
                           			<button class="delete_btn" data-caNum="${ci.caNum}">삭제</button>
                        		</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="list_table">
				</table>
			</div>
			<!-- 가격 종합 -->
			<div class="content_total_section">
				<div class="total_wrap">
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td>총 상품 가격</td>
										<td>
											<span class="totalPrice_span">0</span> 원
										</td>
									</tr>
									<tr>
										<td>배송비</td>
										<td>
											<span class="delivery_price">0</span>원
										</td>
									</tr>									
									<tr>
										<td>총 주문 상품수</td>
										<td><span class="totalKind_span"></span>종 <span class="totalCount_span"></span>병</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td></td>
										<td></td>
									</tr>
								</table>							
							</td>
						</tr>
					</table>
					<div class="boundary_div">구분선 넣을 부분</div>
					<table>
						<tr>
							<td>
								<table>
									<tbody>
										<tr>
											<td>
												<strong>총 결제 예상 금액</strong>
											</td>
											<td>
												<span class="finalTotalPrice_span">0</span> 원
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							
						</tr>
					</table>
				</div>
			</div>
			<!-- 구매 버튼 영역 -->
			<div>
				<button class="order_btn">주문하기</button>
			</div>
			
			<!-- 수량 조정 form -->
			<form action="/cart/update" method="post" class="quantity_update_form">
				<input type="hidden" name="caNum" class="update_caNum">
				<input type="hidden" name="caCount" class="update_caCount">
				<input type="hidden" name="id" value="${SPRING_SECURITY_CONTEXT.authentication.principal.user.username }">
			</form>
			
			<!-- 삭제 form -->
            <form action="/cart/delete" method="post" class="quantity_delete_form">
            	<input type="hidden" name="caNum" class="delete_caNum">
            	<input type="hidden" name="id" value="${SPRING_SECURITY_CONTEXT.authentication.principal.user.username }">
           </form>  
           <!--  구매 form -->
           <form action="/order/${SPRING_SECURITY_CONTEXT.authentication.principal.user.username }" method="get" class="order_form">
			</form> 
			
<script>
	$(document).ready(function(){
		/* 종합 정보 섹션 정보 삽입 */
		setTotalInfo();
	});
	
	/* 체크여부에따른 종합 정보 변화 */
	$(".individual_cart_checkbox").on("change", function(){
		/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
		setTotalInfo($(".cart_info_td"));
	});
	
	/* 체크박스 전체 선택 */
	$(".all_check_input").on("click", function(){

		/* 체크박스 체크/해제 */
		if($(".all_check_input").prop("checked")){
			$(".individual_cart_checkbox").attr("checked", true);
		} else{
			$(".individual_cart_checkbox").attr("checked", false);
		}
		
		/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
		setTotalInfo($(".cart_info_td"));	
		
	});
	
	function setTotalInfo(){
		/* 종합 정보 섹션 정보 삽입 */
		let totalPrice = 0;				// 총 가격
		let totalCount = 0;				// 총 갯수
		let totalKind = 0;				// 총 종류
		let deliveryPrice = 0;			// 배송비
		let finalTotalPrice = 0; 		// 최종 가격(총 가격 + 배송비)	
		
		$(".cart_info_td").each(function(index, element){
			if($(element).find(".individual_cart_checkbox").is(":checked") === true){	//체크여부
				
				// 총 가격
				totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
				// 총 갯수
				totalCount += parseInt($(element).find(".individual_caCount_input").val());
				// 총 종류
				totalKind += 1;
			}

		});	
		
		/* 배송비 결정 */
		if(totalPrice >= 30000){
			deliveryPrice = 0;
		} else if(totalPrice == 0){
			deliveryPrice = 0;
		} else {
			deliveryPrice = 3000;	
		}	
		
		/* 최종 가격 */
		finalTotalPrice = totalPrice + deliveryPrice;
		
		/* 값 삽입 */
		// 총 가격
		$(".totalPrice_span").text(totalPrice.toLocaleString());
		// 총 갯수
		$(".totalCount_span").text(totalCount);
		// 총 종류
		$(".totalKind_span").text(totalKind);
		// 배송비
		$(".delivery_price").text(deliveryPrice);	
		// 최종 가격(총 가격 + 배송비)
		$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());
	}
	
	/* 수량버튼 */
	$(".plus_btn").on("click", function(){
		let quantity = $(this).parent("div").find("input").val();
		$(this).parent("div").find("input").val(++quantity);
	});
	$(".minus_btn").on("click", function(){
		let quantity = $(this).parent("div").find("input").val();
		if(quantity > 1){
			$(this).parent("div").find("input").val(--quantity);		
		}
	});
	
	/* 수량 수정 버튼 */
	$(".quantity_modify_btn").on("click", function(){
		let caNum = $(this).data("canum");
		console.log(caNum);
		let caCount = $(this).parent("td").find("input").val();
		console.log(caCount);
		$(".update_caNum").val(caNum);
		$(".update_caCount").val(caCount);
		$(".quantity_update_form").submit();
		
	});
	
	/* 장바구니 삭제 버튼 */
	$(".delete_btn").on("click", function(e){
	   e.preventDefault();
	   let caNum = $(this).data("canum");
	   $(".delete_caNum").val(caNum);
	   $(".quantity_delete_form").submit();
	});
	
	/* 주문 페이지 이동 */	
	$(".order_btn").on("click", function(){
		
		let form_contents ='';
		let orderNumber = 0;
		
		$(".cart_info_td").each(function(index, element){
			
			if($(element).find(".individual_cart_checkbox").is(":checked") === true){	//체크여부
				
				let alNum = $(element).find(".individual_alNum_input").val();
				let caCount = $(element).find(".individual_caCount_input").val();
				
				let alNum_input = "<input name='orders[" + orderNumber + "].alNum' type='hidden' value='" + alNum + "'>";
				form_contents += alNum_input;
				
				let caCount_input = "<input name='orders[" + orderNumber + "].caCount' type='hidden' value='" + caCount + "'>";
				form_contents += caCount_input;
				
				orderNumber += 1;
				
			}
		});	

		$(".order_form").html(form_contents);
		$(".order_form").submit();
		
	});
</script>
</body>
</html>