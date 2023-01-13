<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Update</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>상품 수정하기</h2>
	
	<form action="/item/update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="itemNo" value="${itemUpdate.itemNo}" />
		<input type="hidden" name="itemHits" value="${itemUpdate.itemHits}" />
		<input type="hidden" name="itemFileNo" value="${itemUpdate.itemFileNo}" />
		
		
		<table class="table table-striped table-hover">
			<tr>
				<th rowspan="2">상품이미지</th>
				<td><img src="/upload/${itemUpdate.storedItemFile}" alt="이미지" style="float: left;  margin-right: 20px; margin-bottom: 10px; width:200px; height:200px;"></td>
			</tr>
			<tr>
				<td>
					<input type="file" name="itemFile" class="form-control"/>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" name="itemTitle" class="form-control" value="${itemUpdate.itemTitle}" />
				</td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td>
					<input type="number" name="itemPrice" class="form-control" value="${itemUpdate.itemPrice}" />
				</td>
			</tr>
			<tr>
				<th>상품재고</th>
				<td>
					<input type="number" name="itemStock" class="form-control" value="${itemUpdate.itemStock}" />
				</td>

			</tr>
			<tr>
				<th>상품설명</th>
				<td>
					<textarea rows="10" cols="30" name="itemContent" class="form-control">${itemUpdate.itemContent}</textarea>
				</td>
			</tr>
			<tr>
				<th>상품등록일</th>
				<td>${itemUpdate.itemCreateDate}</td>
			</tr>
			<tr>
				<th>상품수정일</th>
				<td>${itemUpdate.itemUpdateDate}</td>
			</tr>
		</table>
		<input type="submit" value="수정하기" class="btn btn-primary"/>
	</form>
</body>
</html>
