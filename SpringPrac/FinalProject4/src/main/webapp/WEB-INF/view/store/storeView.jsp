<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<div>
		<table>
			<thead>
				<tr>
					<th>#</th>
					<th>제품명</th>
					<th>이미지</th>
					<th>카테고리</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty dto }">
						<tr>
							<td colspan="9" align="center"><h3>등록된 내용이 없습니다.</h3></td>
						</tr>
					</c:when>
				<c:otherwise>
					<c:forEach items="${dto}" var="dto">
				<tr>
					<td>${dto.alNum }</td>
					<td><a href="alView?alNum=${dto.alNum}">${dto.alName }</a></td>
					<td><img src="${dto.alOpic}" referrerpolicy="no-referrer"/></td>
					<td>${dto.kiName }</td>
					<td>${dto.alPrice }</td>
				</tr>
					</c:forEach>
					</c:otherwise>
					</c:choose>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>