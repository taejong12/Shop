<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member SignUp</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/idCheck.js"></script>

</head>
<body class="text-center">
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	
	<h2>회원가입</h2>
	<form action="/member/signUp" method="post"
		onsubmit="submitSignUpForm(this); return false;">
		<input type="hidden" name="memberRole" value="user" />
		<table class="table table-striped table-hover">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="memberId" id="memberId"
					placeholder="아이디를 입력해주세요." onkeyup="memberIdCheck()" class="form-control"/> <span
					id="memberIdCheckResult"></span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="memberPassword"
					id="memberPassword" placeholder="비밀번호를 입력해주세요." class="form-control"/></td>
			</tr>
			<tr>
				<th>비밀번호확인</th>
				<td><input type="password" name="memberConfirmPassword"
					id="memberConfirmPassword" placeholder="다시 한 번 입력해주세요." class="form-control"/></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName" id="memberName"
					placeholder="이름을 입력해주세요." class="form-control"/></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="memberEmail" id="memberEmail"
					placeholder="ex) test@test.com"
					pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*"
					onkeyup="memberEmailCheck()" class="form-control"/> <span id="memberEmailCheckResult"></span>
				</td>
			</tr>
			<!-- 	<tr>
				<th>전화번호</th>	
				<td><input type="tel" name="memberPhone" id="memberPhone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="010-1234-5678" required/></td>
			</tr> -->
		</table>
		<input class="btn-submit btn btn-primary" type="submit" value="회원가입완료" />
	</form>

	<script>
		function submitSignUpForm(form){
			form.memberId.value = form.memberId.value.trim();
			if(form.memberId.value.length==0){
				alert('아이디를 입력해주세요.');
				form.memberId.focus();
				return false;
			}
			
			if(form.memberId.value.length < 4){
				alert('아이디를 4자 이상 입력해주세요.');
				form.memberId.focus();
				return false;
			}
			
			if(isAlphaNumeric(form.memberId.value)==false){
				alert('아이디는 영문자, 소문자와 숫자만 사용할 수 있습니다.');
				form.memberId.focus();
				return false;
			}
			
			form.memberId.value=form.memberId.value.toLowerCase();
			
			
			form.memberPassword.value = form.memberPassword.value.trim();
			if(form.memberPassword.value.length==0){
				alert('비밀번호를 입력해주세요.');
				form.memberPassword.focus();
				return false;
			}
			
			if(form.memberPassword.value.length < 4){
				alert('비밀번호를 4자 이상 입력해주세요.');
				form.memberPassword.focus();
				return false;
			}
			
			form.memberConfirmPassword.value = form.memberConfirmPassword.value.trim();
			if(form.memberConfirmPassword.value.length==0){
				alert('비밀번호 확인을 입력해주세요.');
				form.memberConfirmPassword.focus();
				return false;
			}
			
			if(form.memberPassword.value != form.memberConfirmPassword.value){
				alert('비밀번호 확인이 일치하지 않습니다.');
				form.memberConfirmPassword.focus();
				return false;
			}
			
			form.memberName.value = form.memberName.value.trim();
			if(form.memberName.value.length==0){
				alert('이름를 입력해주세요.');
				form.memberName.focus();
				return false;
			}
			
			if(form.memberName.value.length < 3){
				alert('이름을 3자 이상 입력해주세요.');
				form.memberName.focus();
				return false;
			}
			
			form.memberEmail.value = form.memberEmail.value.trim();
			if(form.memberEmail.value.length==0){
				alert('이메일을 입력해주세요.');
				form.memberEmail.focus();
				return false;
			}
			
			form.submit();
		}
	</script>

	<script>
		const memberIdCheck = () => {
			const memberIdCheck = document.getElementById("memberId").value;
			const memberIdCheckResult = document.getElementById("memberIdCheckResult");
			
			console.log("입력값 : ", memberIdCheck);
			$.ajax({
				type:"POST",
				url:"/member/idCheck",
				data: {
					"memberId" : memberIdCheck 
				},
				success: function(res){
					console.log(res);
					if(memberIdCheck != res.memberId){
						
						if(memberIdCheck.length < 4){
							memberIdCheckResult.style.color="red";
							memberIdCheckResult.innerHTML ="4자 이상 입력해주세요.";
						}else{	
							memberIdCheckResult.style.color="green";
							memberIdCheckResult.innerHTML ="사용가능한 아이디입니다.";
						}
					} else {
						memberIdCheckResult.style.color="red";
						memberIdCheckResult.innerHTML ="이미 사용중인 아이디입니다.";
					}
					console.log("요청성공", res);
				},
				error: function(err) {
					console.log("에러발생", err);
				}
			});
		}
	
	</script>

	<script>
		const memberEmailCheck = () => {
			const memberEmailCheck = document.getElementById("memberEmail").value;
			const memberEmailCheckResult = document.getElementById("memberEmailCheckResult");
			console.log("입력값 : ", memberEmailCheck);
			$.ajax({
				type:"POST",
				url:"/member/emailCheck",
				data: {
					"memberEmail" : memberEmailCheck 
				},
				success: function(res){
					if(memberEmailCheck != res.memberEmail){
						memberEmailCheckResult.style.color="green";
						memberEmailCheckResult.innerHTML ="사용가능한 이메일입니다.";
					} else {
						memberEmailCheckResult.style.color="red";
						memberEmailCheckResult.innerHTML ="이미 사용중인 이메일입니다.";
					}
					console.log("요청성공", res);
				},
				error: function(err) {
					console.log("에러발생", err);
				}
			});
		}
	
	</script>
</body>
</html>