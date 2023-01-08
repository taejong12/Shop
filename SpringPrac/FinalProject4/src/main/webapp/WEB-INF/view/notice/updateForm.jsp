<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write form</title>
</head>
<body>   
<form action="/notice/update" method="post">
         <input type="hidden" value="${dto.id}" name="id">
   제목: <input type="text" value="${dto.title}" name="title" size="100"/><br/>
   내용: <input type="text" value="${dto.content}" name="content" size="100"/><br/>
   작성자: <input type="text" value="${dto.writer}" name="writer" size="100"/><br/>   
   <input type="submit" value="수정"/><br/>
   <a href="/notice/list">목록</a>
</form>
</body>
</html>