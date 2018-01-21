<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<section class="content">
<div class="box-body"></div>
<div class="box box-primary">
<h1>Spring Security - Custom UserDetailsService Example</h1>
<h2>${message}</h2>
<h2>${pageContext.request.contextPath}</h2>

<form action="${pageContext.request.contextPath}/logout" method="post">
	<input value="Logout" type="submit">
</form>
</div>
</section>