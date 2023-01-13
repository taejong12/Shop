<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Check</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body class="text-center">
	<form action="/member/update" method="get"
		onsubmit="updatePasswordCheck(this); return false;"
		style="margin:15%;">
		<h2>회원정보수정 비밀번호 검증</h2>
		<div class="form-group row" style="margin-left: 30%;">
			<label for="inputPassword" class="col-sm-2 col-form-label">비밀번호</label>
			<div class="col-sm-10">
				<input style="width: 40%;" type="password" class="form-control" id="memberPassword" name="memberPassword" placeholder="비밀번호를 입력하세요.">
			</div>
		</div>
		
		<input type="submit" value="확인"  class="btn btn-warning"/>

	</form>

	<script>
		function updatePasswordCheck(form){
			form.memberPassword.value = form.memberPassword.value.trim();
			if(form.memberPassword.value.length==0){
				alert('비밀번호를 입력해주세요.');
				form.memberPassword.focus();
				return false;
			}
			
			const memberPassword = <%=(String)session.getAttribute("memberPassword")%>
			console.log("memberPassword");
			
			if(form.memberPassword.value!=memberPassword){
				alert("비밀번호가 틀렸습니다.");
				return false;
			} 
	
			form.submit();
			
		}
	</script>
</body>
</html>