<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성하기</title>
</head>
<body>
 <h2>게시물 작성하기</h2>
 
 
 	<form action="/board/write" method="post">
		<table>
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>내용</th>
			</tr>
			<tr>
				<td><input type="text" value="" name="title"/></td>
				<td><input type="text" value="" name="writer"/></td>
				<td><input type="text" value="" name="content"/></td>
			</tr>
		</table>
		<input type="submit" value="작성완료"/>
	</form>
	<button type="button" onclick="location.href='/board/list';">목록</button>
 
 
</body>
</html>