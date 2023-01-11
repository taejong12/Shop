<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navbar</title>
<link rel="stylesheet" href="/css/include/navbar.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">

<style>
	#nav_background{
		background-color: #FCFFE7;
	}
	
	.nav-item a{
		color: black;
	}
</style>
</head>

<body>

	<div class="container">
		<nav id="nav_background" class="d-flex justify-content-center py-3">
			<ul class="nav nav-pills">
				<li class="nav-item">
					<a href="/" class="nav-link" aria-current="page">홈페이지</a>
				</li>
				<!-- <li class="nav-item"><a href="/board/list" class="nav-link">게시물목록</a></li> -->
				<li class="nav-item">
					<a href="/board/paging" class="nav-link">상품목록</a>
				</li>

				<%
					Long memberNo = (Long) session.getAttribute("memberNo");
					if (memberNo != null) {
				%>
					<li class="nav-item">
						<a href="/cart/list/${memberNo}" class="nav-link">장바구니</a>
					</li>
					<li class="nav-item">
						<a href="/order/list/${memberNo}" class="nav-link">주문내역</a>
					</li>
				<%
					}
				%>
			</ul>
		</nav>
	</div>
	
</body>
</html>