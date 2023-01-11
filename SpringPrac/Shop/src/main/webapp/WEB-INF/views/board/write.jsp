<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Write</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	
	<h2>게시물 작성하기</h2>


	<form action="/board/write" method="post" enctype="multipart/form-data">
		<table class="table table-striped table-hover">
			<tr>
				<th>상품이미지</th>
				<td><input type="file" name="boardFile" class="form-control" multiple/></td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="boardTitle" class="form-control"/></td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td><input type="number" name="boardPrice" class="form-control"/></td>
			</tr>
			<tr>
				<th>상품재고</th>
				<td><input type="number" name="boardStock" class="form-control"/></td>

			</tr>
			<tr>
				<th>상품설명</th>
				<td><textarea rows="10" cols="30" name="boardContent" class="form-control"></textarea>
			</tr>
		</table>
		<div class="d-flex justify-content-center">
			<input type="submit" value="작성하기" class="btn btn-primary"/>
		</div>
	</form>


</body>
</html>