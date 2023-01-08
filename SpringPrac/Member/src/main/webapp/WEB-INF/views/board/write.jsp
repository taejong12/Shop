<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Write</title>
</head>
<body>
	<h2>게시물 작성하기</h2>


	<form action="/board/write" method="post">
		<table>
			<tr>
				<th>상품이미지</th>
				<td>이미지</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="boardTitle" /></td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td><input type="number" name="boardPrice" /></td>
			</tr>
			<tr>

				<th>상품재고</th>
				<td><input type="number" name="boardStock" /></td>

			</tr>
			<tr>
				<th>상품설명</th>
				<td><textarea rows="10" cols="30" name="boardContent"></textarea>
			</tr>
		</table>
		<input type="submit" value="작성하기" />
	</form>
	<button type="button" onclick="location.href='/board/list';">목록</button>


</body>
</html>