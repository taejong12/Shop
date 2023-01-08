<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view</title>
</head>
<body>
제목 : ${dto.title}<br/>
내용 : ${dto.content}<br/>
작성자 : ${dto.writer}<br/>
등록일자 : ${dto.regdate}<br/>
조회 수 : ${dto.visitCount}<br/>
<hr>
<p><a href="/notice/list">목록보기</a></p>
</body>
</html>