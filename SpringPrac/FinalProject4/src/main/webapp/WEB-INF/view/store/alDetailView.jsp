<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/*  div{
text-align: center;
}
.inline{
display: inline-block;
position: relative;
float:right;

}  */
div{}
.detailright{	  
  
  float:right;
}
</style>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" 
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" 
	crossorigin="anonymous">
</script>

</head>
<body>
<script type="text/javascript">

var sell_price; 
var amount; 
 
function init () { 
	console.log("init 함수 실행");
    sell_price = document.form.sell_price.value; 
    amount = document.form.orTotalAmount.value; 
    document.form.sum.value = sell_price; 
    console.log(orTotalAmount);
    console.log(sell_price);        
    change(); 
} 
 
function add () { 
	console.log("add 함수 실행");
    hm = document.form.orTotalAmount; 
    sell_price = document.form.sell_price.value;
    hm.value ++ ; 
    // console.log("alDTO.alPrice은"+document.form.sell_price.value);
    // console.log("alDTO alPrice는 "+"{alDTO.alPrice}");
    var sum_ = parseInt(hm.value) * sell_price;
	console.log("hm.value는 "+parseInt(hm.value));
	console.log("sell_price는"+parseInt(sell_price));
	if (isNaN(sum_)) sum_ = 0;
  document.getElementById("my_sum").value=sum_;
} 
 
function del() { 
	console.log("del 함수 실행");
    hm = document.form.orTotalAmount; 
    sell_price = document.form.sell_price.value;
        if (hm.value > 0) { 
        	hm.value -- ; 
        	
            var sum_ = parseInt(hm.value) * sell_price;
        	console.log(parseInt(hm.value));
        	console.log(parseInt(sell_price));
        	if (isNaN(sum_)) sum_ = 0;
          document.getElementById("my_sum").value=sum_;
        } 
} 
 
function change(obj){ 
	obj.value = obj.value.replace(/[^0-9]/g, "");
	console.log("change 함수 실행");
	hm = document.form.orTotalAmount.value; 
    sell_price = document.form.sell_price.value;
    console.log("change의 hm은 "+hm);
    console.log("change의 sell_price은 "+sell_price);
        if (hm.value < 0) { 
            hm.value = 0; }
    var sum_ = parseInt(hm) * sell_price; 
    console.log("change의 sum_은 "+ sum_);
    if (isNaN(sum_)) sum_ = 0;
  document.getElementById("my_sum").value=sum_; 

} 
function onlyNumber(obj) {
    obj.value = obj.value.replace(/[^0-9]/g, "");
  }


function checkNumber(event) {
	  if(event.key >= 0 && event.key <= 9) {
	    return true;
	  }
	  
	  return false;
	}

window.addEventListener("keydown", e => {
	  const key = document.getElementById(e.key);
	  if (key) change();
	});
window.addEventListener("keyup", e => {
	  const key = document.getElementById(e.key);
	  if (key) change();
	});


var sweetS ="${alDTO.flDanmat}"; 
var sinmatS ="${alDTO.flSinmat}"; 
var ssunmatS ="${alDTO.flSsunmat}"; 
var bodyS ="${alDTO.flBody}";

console.log("단맛은 "+sweetS);
console.log("신맛은 "+sinmatS);
console.log("쓴맛은 "+ssunmatS);
console.log("농도는 "+bodyS);

var arrTaste = [sweetS,sinmatS,ssunmatS,bodyS];
/* 가장 큰 값을 찾고 그 값을 가진 맛을 찾아서 출력한다. 향상된 for문 */
 
var maxTaste = Math.max.apply(null,arrTaste);
/* var found[] = arrTaste.findIndex(e=> e==maxTaste);
arrTaste */

console.log("가장 큰 값은 "+maxTaste);
let found = arrTaste.findIndex(e => e == maxTaste);
 
var arrT=[];
window.onload = function tastePrint(){	
for(tanum in arrTaste){
	if(maxTaste==arrTaste[tanum]){
		console.log("최고로 높은 인덱스는"+tanum);
		if(tanum==0){
			console.log("단맛 출력");
				
				console.log("단맛 출력2");
				document.getElementById("imgSw1").src="${fdDTO.sweet1}";
				document.getElementById("imgSw2").src="${fdDTO.sweet2}";
				document.getElementById("imgSw3").src="${fdDTO.sweet3}";
				
		}if(tanum==1){
			console.log("짠맛 출력");
		
				document.getElementById("imgSi1").src="${fdDTO.salty1}";
				document.getElementById("imgSi2").src="${fdDTO.salty2}";
				document.getElementById("imgSi3").src="${fdDTO.salty3}";
				
		}if(tanum==2){
			console.log("신맛 출력");
			
				document.getElementById("imgSs1").src="${fdDTO.sour1}";
				document.getElementById("imgSs2").src="${fdDTO.sour2}";
				document.getElementById("imgSs3").src="${fdDTO.sour3}";
				
		}if(tanum==3){
			console.log("쓴맛 출력");
			
				document.getElementById("imgBo1").src="${fdDTO.density1}";
				document.getElementById("imgBo2").src="${fdDTO.density2}";
				document.getElementById("imgBo3").src="${fdDTO.density3}";
		}
		}		
	}
}
/* arrT 배열에 tanum을 각각 추가하고 arrTaste의 tanum에 해당하는 인덱스 그림을 출력한다.*/

        window.document.oncontextmenu = new Function("return false");
        window.document.onselectstart = new Function("return false");
        window.document.ondragstart = new Function("return false");
        
</script>

<div>
<div class="detailright" align="right">

<form action="/orderForm" name="form" method="post" enctype="multipart/form-data">
	<input type="hidden" name="userName" value="${SPRING_SECURITY_CONTEXT.authentication.principal.user.username}" />
	<input type="hidden" name="alOpic" value="${alDTO.alOpic}" />
	<input type="hidden" name="alNum" value="${alDTO.alNum}" />
	상품명:<input type=text name="alName" value="${alDTO.alName}" readonly></input><br/>
	 <input type=hidden ondragstart='return false'  name="sell_price" value="${alDTO.alPrice}">
	수량: <input type="text" name="orTotalAmount" id="amount" value="0" size="3" onkeydown="change(this);" onkeyup = "change(this);"><br/>
	1개 추가하기 : <input type="button" value=" + " onclick="add();">
	1개 줄이기<input type="button" value=" - " onclick="del();"><br/>
	<input type="hidden" name="sum" size="11" readonly>
	총 금액 : <input type="number" id="my_sum" name="orTotalPrice" value="0" readonly/>원<br/>
	<c:choose>
		<c:when test="${not empty SPRING_SECURITY_CONTEXT.authentication.principal.user.username }">
			<input type="submit" id="payment" value="결제하기" /> 
		</c:when>
		<c:otherwise>
			<button type="button" onclick="location.href='/login';" >로그인하기</button>
		</c:otherwise>
	</c:choose>
	
</form>

<button class="btn_cart">장바구니 추가</button> 


</div>

<img id="imgSw1" src="" />
<img id="imgSw2" src="" />
<img id="imgSw3" src="" />

<img id="imgSi1" src="" />
<img id="imgSi2" src="" />
<img id="imgSi3" src="" />

<img id="imgSs1" src="" />
<img id="imgSs2" src="" />
<img id="imgSs3" src="" />

<img id="imgBo1" src="" />
<img id="imgBo2" src="" />
<img id="imgBo3" src="" />


<c:choose>
	<c:when test="${alDTO.alNum eq '10281'}">
<%@ include file="/WEB-INF/view/detailinclude/backja.jsp" %>
</c:when>
	<c:when test="${alDTO.alNum eq '10360'}">
<%@ include file="/WEB-INF/view/detailinclude/donghak.jsp" %>
</c:when>
	<c:when test="${alDTO.alNum eq '10075'}">
<%@ include file="/WEB-INF/view/detailinclude/yeopo.jsp" %>
</c:when>

</c:choose>
 </div>
 
 <form id="infoForm" action="/orderForm" method="get">
 	<input type="hidden" id="alNum" name="alNum" value='<c:out value="${pageInfo.alNum}"/>'>
 	<input type="hidden" name="pageNum" '<c:out value="${cri.pageNum }"/>'>
	<input type="hidden" name="amount" '<c:out value="${cri.amount }"/>'>
 </form>
 
 
<script>    
let form = $("#infoForm");

$("#payment").on("click", function(e){
	form.attr("action", "/orderForm");
	form.submit();
});


const form ={
    	id : '${SPRING_SECURITY_CONTEXT.authentication.principal.user.username}',
    	alNum : '${alDTO.alNum}',
    	caCount : ''
}
    	$(".btn_cart").on("click", function(e){
    		form.caCount = $("#amount").val();
    		console.log(form.id);
    		console.log(form.alNum);
    		console.log(form.caCount);
    		$.ajax({
    			url: '/cart/add',
    			type: 'POST',
    			data: form,
    			success: function(result){	
    				cartAlert(result);
    			}
    		})
    	});
    	
    	function cartAlert(result){
    		if(result == '0'){
    			alert("장바구니에 추가를 하지 못하였습니다.");
    		} else if(result == '1'){
    			alert("장바구니에 추가되었습니다.");
    		} else if(result == '2'){
    			alert("장바구니에 이미 추가되어져 있습니다.");
    		} else if(result == '5'){
    			alert("로그인이 필요합니다.");	
    		}
    	}
</script>
 </body>
</html>