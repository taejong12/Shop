<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ include file="/WEB-INF/view/include/link.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/layout/nav.css">
<link rel="stylesheet" href="/css/home.css">

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js" type="text/javascript"></script>

</head>
<body>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
	<div class="orderGoods_div">
		<!-- 상품 종류 -->
		<div class="goods_kind_div">
			주문상품 <span class="goods_kind_div_kind"></span>종 <span class="goods_kind_div_count"></span>개
		</div>
		<!-- 상품 테이블 -->
		<table class="goods_subject_table">
			<colgroup>
				<col width="15%">
				<col width="45%">
				<col width="40%">
			</colgroup>
			<tbody>
				<tr>
					<th>이미지</th>
					<th>상품 정보</th>
					<th>판매가</th>
				</tr>
			</tbody>
		</table>
		<table class="goods_table">
			<colgroup>
				<col width="15%">
				<col width="45%">
				<col width="40%">
			</colgroup>					
			<tbody>
				<c:forEach items="${orderList}" var="ol">
					<tr>
						<td>
							<img src="${ol.alOpic}" referrerpolicy="no-referrer" />
						</td>
						<td>${ol.alName}</td>
						<td class="goods_table_price_td">
							<fmt:formatNumber value="${ol.alPrice}" pattern="#,### 원" /> | 수량 ${ol.caCount}개
							<br><fmt:formatNumber value="${ol.orTotalPrice}" pattern="#,### 원" />
							
							<input type="hidden" class="individual_alPrice_input" value="${ol.alPrice}">
							<input type="hidden" class="individual_caCount_input" value="${ol.caCount}">
							<input type="hidden" class="individual_orTotalPrice_input" value="${ol.alPrice * ol.caCount}">
							<input type="hidden" class="individual_alNum_input" value="${ol.alNum}">
						</td>
					</tr>							
				</c:forEach>

			</tbody>
		</table>
	</div>
	
	<div class="total_info_div">
		<!-- 가격 종합 정보 -->
		<div class="total_info_price_div">
			<ul>
				<li>
					<span class="price_span_label">상품 금액</span>
					<span class="totalPrice_span">0</span>원
				</li>
				<li>
					<span class="price_span_label">배송비</span>
					<span class="delivery_price_span">0</span>원
				</li>
																		
				<li class="price_total_li">
					<strong class="price_span_label total_price_label">최종 결제 금액</strong>
					<strong class="strong_red">
						<span class="finalTotalPrice_span">
						0
						</span>원
					</strong>
				</li>
			</ul>
		</div>
		<!-- 버튼 영역 -->
		<div class="total_info_btn_div">
			<a class="order_btn">결제하기</a>
		</div>
	</div>
	<h1>주문정보 확인</h1>
	
	
	<h3>사용자 아이디</h3>
	<div >${SPRING_SECURITY_CONTEXT.authentication.principal.user.username }</div>
	
	<h3>이름</h3>
	<div >${SPRING_SECURITY_CONTEXT.authentication.principal.user.nickname }</div>
	
	<hr>
	<h3>배송지입력</h3>
	
		<form id="moveForm" method="post" >
			<input type="hidden" name="orTotalAmount" value="${ol.orTotalAmount}">
			<input type="hidden" name="orPayStatus" value="결제완료">
			<input type="hidden" name="orTotalPrice" value="${ol.orTotalPrice}">
			<!-- 
			<input type="hidden" name="orPayDate" value="${orPayDate}">
			 -->

			<section class="address_search">
                 <div id="search_box">
                     <div>
                         <input type="hidden" id="deleveryAddress1" placeholder="우편번호" value="${BMaddress.address1 }" name="orPostNum" readonly>
                   		 <input type="text" value="${BMaddress.address2 }" 
                         		id="deleveryAddress2" readonly placeholder="배달 받으실 주소를 입력해 주세요" name="orRoadAddress" onclick="modifyAddress()"><br>     
                         <input type="text" id="deleveryAddress3" value="${BMaddress.address3 }"  name="orDetailAddress" placeholder="상세주소를 입력해주세요">                 		
                     </div>
 
                     <div class="search_btn">
                         <label for="search_btn">
                             <i class="fas fa-search"></i>
                         </label>
 
                         <input type="button" name="search" id="search_btn">
 
                     </div>
 					<%@ include file="/WEB-INF/view/include/modifyAddress.jsp" %>
                 </div>
            </section>
		
	
			<h2>결제방법</h2>
			
	<!-- 	사용할 회사명: kcp 버튼 -->
			<button  type="button" onclick="payment('kcp', 'test', 'card')">카드 결제</button>
	<!-- 		카드 결제 테스트 -->
	<!-- 		<button type="button" onClick="payment('kcp', 'real', 'card')"> kcp real payment</button> -->
	<!-- 		카드 결제 -->
			<button  type="button" onclick="payment('kcp', 'test', 'vbank')">무통장 입금</button>
	<!-- 		가상계좌 -->
	<!-- 		<button type="button" onClick="payment('inicis', 'test', 'card')"> inicis test 결제</button> -->
			
	<!-- 		<button type="button" onClick="payment('inicis', 'real', 'card')"> inicis real payment</button> -->
			
	<!-- 		<button type="button" onClick="payment('inicis', 'test', 'vbank')"> inicis test 무통장 입금</button> -->
			
		</form>


<%@ include file="/WEB-INF/view/include/nav.jsp" %>

<%@ include file="/WEB-INF/view/include/footer.jsp" %>

	<script>
$(document).ready(function(){
	
	/* 주문 조합정보란 최신화 */
	setTotalInfo();
	
});

/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
function setTotalInfo(){

	let totalPrice = 0;				// 총 가격
	let totalCount = 0;				// 총 갯수
	let totalKind = 0;				// 총 종류
	let deliveryPrice = 0;			// 배송비
	let finalTotalPrice = 0; 		// 최종 가격(총 가격 + 배송비)	
	
	$(".goods_table_price_td").each(function(index, element){
		// 총 가격
		totalPrice += parseInt($(element).find(".individual_orTotalPrice_input").val());
		// 총 갯수
		totalCount += parseInt($(element).find(".individual_caCount_input").val());
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
	$(".goods_kind_div_count").text(totalCount);
	// 총 종류
	$(".goods_kind_div_kind").text(totalKind);
	// 배송비
	$(".delivery_price_span").text(deliveryPrice.toLocaleString());	
	// 최종 가격(총 가격 + 배송비)
	$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());			
	
}
</script>

<script type="text/javascript">
	function payment(pg_provider, mode, payment_method){//제공자, 모드(테스트, 리얼) , 결제방식
		let totalPrice = 0;				// 총 가격
		let totalCount = 0;				// 총 갯수
		let totalKind = 0;				// 총 종류
		let deliveryPrice = 0;			// 배송비
		let finalTotalPrice = 0; 		// 최종 가격(총 가격 + 배송비)	
		
		$(".goods_table_price_td").each(function(index, element){
			// 총 가격
			totalPrice += parseInt($(element).find(".individual_orTotalPrice_input").val());
			// 총 갯수
			totalCount += parseInt($(element).find(".individual_caCount_input").val());
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
		
		IMP.init('imp73475673');//회사의 코드 --초기화
		var pg_mid;//pg 코드
		
		
		if(pg_provider == 'inicis'){ //결제사(pg) inicis회사
			
			if(mode=='test'){
				pg_mid = 'html5_inicis.INIpayTest';//테스트 코드
			} else {
				pg_mid = 'html5_inicis.MOI123123';//실제코드 -- 결제코드
			}
		} else if(pg_provider=='kcp'){
			if(mode=='test'){
				pg_mid = 'kcp.T0000'; // 상점아이디
			} else {
				pg_mid = 'kcp.IOfds34';
			}
		}
		
		// alert(pg_mid);
		let today = new Date();
		// 년도 getFullYear()

		let year = today.getFullYear(); 

		// 월 getMonth() (0~11로 1월이 0으로 표현되기 때문에 + 1을 해주어야 원하는 월을 구할 수 있다.)

		let month = today.getMonth() + 1

		// 일 getDate()

		let date = today.getDate(); // 일
		// 시 getHours()

		let hours = today.getHours();

		// 분 getMinutes()

		let minutes = today.getMinutes();

		/// 초 getSeconds()

		let seconds = today.getSeconds();
		const data = {
				pg: pg_mid, // 
				pay_method: payment_method, // 결제수단
				merchant_uid: "${SPRING_SECURITY_CONTEXT.authentication.principal.user.username}"+year+month+date+hours+minutes+seconds , // 주문 번호 -- DTO에서 sysdate로 설정
				name: "전통주 "+totalCount+"종", // 상품명 
				amount: finalTotalPrice, // 결제금액
				buyer_email: "${list.email}", //고객 이메일
				buyer_name: "${SPRING_SECURITY_CONTEXT.authentication.principal.user.username}", // 고객이름
				buyer_tel: "${list.phone}" //고객 전화번호
//				buyer_tel: "010-5060-7980", //고객 전화번호
		};
		console.log(finalTotalPrice);
		console.log(data);
			
		IMP.request_pay(data, response =>{ //위 데이터를 바탕으로 결제 요청
			 //alert('callback!: '+JSON.stringify(response)); //콜백함수
			
			//console.log(JSON.stringify(response));
			 
			var formValue = $('#moveForm').serialize();

			$.ajax({ //비동기로 처리
				url:"/payment/callback_receive", //컨트롤러에 설정
				method:"POST", //포스트 방식
				headers:{"Content-Type":"application/json"}, //제이슨타입
				data:JSON.stringify(response) //스트링타입으로 리스폰스
			}).success(function(data){ //끝나면 함수 시작		

				// alert("Please, Check your payment result page!!");
				//console.log(data.process_result);
				//console.log(data);
				//console.log(formValue+"dsadsa");
				
				if(data.process_result == '결제성공') {
					$(document).ready(function(){
						form($("input[name=alNum]").val());				
					});
					
					function form(){
						$.ajax({
							url :"/order/insert",
							data:formValue,
							success : function(data){
								alert('결제성공');
								location.href='/order/orderSuccess';
							}
						
						})
				
			
					}
					
	
					
				} else {
					alert('결제취소');
					
				}
				
				// location.href='/order/orderPage';
			
			
			}).error(function(error){
				alert("error");
			})
			
		});
	}
</script>
</body>
</html>