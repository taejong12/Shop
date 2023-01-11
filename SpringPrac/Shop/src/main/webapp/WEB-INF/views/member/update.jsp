<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Information Update</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	
	<h2>회원정보 수정하기</h2>
	<form action="/member/update" method="post">
		<input type="hidden" name="memberNo" value="${memberUpdate.memberNo}" />
		<input type="hidden" name="memberId" value="${memberUpdate.memberId}" />
		<input type="hidden" name="memberEmail"
			value="${memberUpdate.memberEmail}" /> <input type="hidden"
			name="memberPassword" value="${memberUpdate.memberPassword}" /> <input
			type="hidden" name="memberRole" value="${memberUpdate.memberRole}" />

		<table class="table table-striped table-hover">
			<tr>
				<th>아이디</th>
				<td>${memberUpdate.memberId}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName"
					value="${memberUpdate.memberName}" class="form-control"/></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${memberUpdate.memberEmail}</td>
			</tr>
		</table>
		<input type="submit" value="수정완료" class="btn btn-primary"/>
	</form>
</body>
</html>