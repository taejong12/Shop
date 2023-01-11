<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Update</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>

	<h2>상품 수정하기</h2>
	
	<form action="/board/update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="boardNo" value="${boardUpdate.boardNo}" />
		<input type="hidden" name="boardHits" value="${boardUpdate.boardHits}" />
		
		
		<table class="table table-striped table-hover">
			<tr>
				<th rowspan="2">상품이미지</th>
				<td><img src="/upload/${boardUpdate.storedBoardFile}" alt="이미지" style="float: left;  margin-right: 20px; margin-bottom: 10px; width:200px; height:200px;"></td>
			</tr>
			<tr>
				<td>
					<input type="file" name="boardFile" class="form-control" multiple/>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" name="boardTitle" class="form-control" value="${boardUpdate.boardTitle}" />
				</td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td>
					<input type="number" name="boardPrice" class="form-control" value="${boardUpdate.boardPrice}" />
				</td>
			</tr>
			<tr>
				<th>상품재고</th>
				<td>
					<input type="number" name="boardStock" class="form-control" value="${boardUpdate.boardStock}" />
				</td>

			</tr>
			<tr>
				<th>상품설명</th>
				<td>
					<textarea rows="10" cols="30" name="boardContent" class="form-control">${boardUpdate.boardContent}</textarea>
				</td>
			</tr>
			<tr>
				<th>상품등록일</th>
				<td>${boardUpdate.boardCreateDate}</td>
			</tr>
			<tr>
				<th>상품수정일</th>
				<td>${boardUpdate.boardUpdateDate}</td>
			</tr>
		</table>
		<input type="submit" value="수정하기" class="btn btn-primary"/>
	</form>
</body>
</html>
