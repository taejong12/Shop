<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav>
    <ul>
        <li><a href="/"></a></li>
        <li><a href="/store/search"></a></li>
        <li><a href="/cart/${SPRING_SECURITY_CONTEXT.authentication.principal.user.username }"></a></li>
        <li><a href="/orderList/${SPRING_SECURITY_CONTEXT.authentication.principal.user.username }"></a></li>
        <li><a href="/myPage"></a></li>
    </ul>
</nav>