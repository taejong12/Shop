<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>

<a href="/">홈페이지</a>
<table width="500" cellpadding="0" cellspacing="0" border="1">
   <tr>
      <td>No.</td>
        <td>제목</td>
        <td>내용</td>        
        <td>작성자</td>
        <td>등록일자</td>
        <td>조회 수</td>
        <td>수정</td>
        <td>삭제</td>
    </tr>
         <c:forEach items="${list}" var="dto">
    <tr>
         <td>${dto.id}</td>
         <td>${dto.title}</td>
         <td><a href="/notice/view2?id=${dto.id}">${dto.content}</a></td>
         <td>${dto.writer}</td>
         <td>${dto.regdate}</td>
         <td>${dto.visitCount}</td>
         <td><a href="/notice/updateView?id=${dto.id}">❤</a></td>
         <td><a href="/notice/delete?id=${dto.id}">◎</a></td>
    </tr>
    </c:forEach>
</table>

	
<p><a href="/notice/write">작 성</a><p>
</body>
</html>