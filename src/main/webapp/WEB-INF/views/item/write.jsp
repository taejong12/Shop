<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Write</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	
	<h2>게시물 작성하기</h2>


	<form action="/item/write" method="post" enctype="multipart/form-data"
			onsubmit="itemWriterForm(this); return false;">
		<table class="table table-striped table-hover">
			<tr>
				<th>상품명</th>
				<td><input type="text" name="itemTitle" class="form-control"/></td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td><input type="number" name="itemPrice" class="form-control"/></td>
			</tr>
			<tr>
				<th>상품재고</th>
				<td><input type="number" name="itemStock" class="form-control"/></td>

			</tr>
			<tr>
				<th>상품설명</th>
				<td><textarea rows="10" cols="30" name="itemContent" class="form-control"></textarea>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td><input type="file" name="itemFile" class="form-control" accept="image/*"/></td>
			</tr>
		</table>
		<div class="d-flex justify-content-center">
			<input type="submit" value="작성하기" class="btn btn-primary"/>
		</div>
	</form>

	<script type="text/javascript">
		function itemWriterForm(form){
			form.itemTitle.value = form.itemTitle.value.trim();
			if(form.itemTitle.value.length==0){
				alert('상품명을 입력해주세요.');
				form.itemTitle.focus();
				return false;
			}
			
			form.itemPrice.value = form.itemPrice.value.trim();
			if(form.itemPrice.value.length==0){
				alert('상품가격을 입력해주세요.');
				form.itemPrice.focus();
				return false;
			}
			
			form.itemStock.value = form.itemStock.value.trim();
			if(form.itemStock.value.length==0){
				alert('상품재고를 입력해주세요.');
				form.itemStock.focus();
				return false;
			}
			
			
			form.itemContent.value = form.itemContent.value.trim();
			if(form.itemContent.value.length==0){
				alert('상품설명을 입력해주세요.');
				form.itemContent.focus();
				return false;
			}
			
			form.itemFile.value = form.itemFile.value.trim();
			if(form.itemFile.value.length==0){
				alert('이미지를 선택해주세요.');
				form.itemFile.focus();
				return false;
			}
			
				
		}		
	</script>
			
	
</body>
</html>