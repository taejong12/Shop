<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ include file="/WEB-INF/view/include/link.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 주문 내역</title>
<link rel="stylesheet" href="/css/layout/nav.css">
<link rel="stylesheet" href="/css/home.css">

</head>
<body>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

	      <div class="form-group">
               <div class="row" style="text-align: center;">
                  <h1 class="page-header" style="margin-bottom: 50px;">나의 주문 내역</h1>
                  <table class="table table-hover"
                     style="margin: auto; border: 1px solid black;">
                     <thead>
                        <tr>
		                    <th colspan="2" style="text-align: center;">상품명</th>
		                    <th>결제수량</th>
		                    <th>결제상태</th>
		                    <th>결제금액</th> 
		                    <th>결제일</th>
		                    <th>배송주소</th>
                 		</tr>   
		              </thead>
		              <tbody style="text-align: left;">
		              <c:choose>
		              	<c:when test="${empty orderInfo}">	              		
		              			<div>결제내역이 없습니다.</div>   
		              	</c:when>
		              	<c:otherwise>
	              			<c:forEach items="${orderInfo}" var="order">
								<tr>
									<td><img src="${order.alOpic}" referrerpolicy="no-referrer" /></td>
									<td>
										${order.alName}
									</td>
									<td>${order.orTotalAmount}</td>
									<td>
										${order.orPayStatus}
									</td>
									<td >
										<fmt:formatNumber value="${order.orTotalPrice}" pattern="#,### 원" />
									</td>
									<td>
										${order.orPayDate}
									</td>
									<td>
										${order.orRoadAddress}, ${order.orDetailAddress}
									</td>
								</tr>
							</c:forEach>
		              	</c:otherwise>
		              </c:choose>

               </tbody>
            </table>
 
   </div>
</div><!-- class=row -->

<%@ include file="/WEB-INF/view/include/nav.jsp" %>

<%@ include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>