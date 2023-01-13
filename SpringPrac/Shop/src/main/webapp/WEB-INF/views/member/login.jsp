<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Login</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>
</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	

	
		<h2>로그인</h2>
		<form action="/member/login" method="post"
			onsubmit="loginCheck(this); return false;">
			<table class="table table-striped table-hover">
				<tr>
					<th>아이디</th>
					<td><input type="text" class="form-control" name="memberId" /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" class="form-control" name="memberPassword" /></td>
				</tr>
			</table>
			<input type="submit" class="btn btn-primary" value="로그인" />
		</form>

	<script>
		const result = "${msg}";
		if(result === "signUpSuccess"){
			alert("회원가입이 완료되었습니다.");
		} 
	
		function loginCheck(form){
			form.memberId.value = form.memberId.value.trim();
			if(form.memberId.value.length==0){
				alert('아이디를 입력해주세요.');
				form.memberId.focus();
				return false;
			}
			
			form.memberPassword.value = form.memberPassword.value.trim();
			if(form.memberPassword.value.length==0){
				alert('비밀번호를 입력해주세요.');
				form.memberPassword.focus();
				return false;
			}
			
			$.ajax({
				type:"post",
				url:"/member/loginCheck",
				data:{"memberId" : form.memberId.value},
				success:function(data){
					console.log(data);
					
				/* 	console.log(form.memberId.value);
					console.log(form.memberPassword.value);
					
					console.log(data.memberId);
					console.log(data.memberPassword); */
					
					if(form.memberId.value == data.memberId && form.memberPassword.value==data.memberPassword){
						form.submit();
					} else {
						alert("아이디나 비밀번호가 일치하지 않습니다.");
					}
				},
				error : function(){
					alert("로그인 확인 ajax 실행 실패");
				}
				
			})
			
			
		}
	
	</script>
</body>
</html>