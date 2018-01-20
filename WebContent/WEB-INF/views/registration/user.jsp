<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/taglib.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Info.</title>


</head>
<body>

<form:form method="POST" modelAttribute="UserInfoForm" action="${pageContext.request.contextPath}/registration/userInfo">
	<table>
		<tr>
			<td>name :</td>
			<td><form:input path="name" id="name" /></td>
		</tr>
		<tr>
			<td>account :</td>
			<td><form:input path="account" id="account" /></td>
		</tr>
		<tr>
			<td>password :</td>
			<td><form:input path="password" id="password" /></td>
		</tr>
		<tr>
			<td>phone :</td>
			<td><form:input path="phone" id="phone" /></td>
		</tr>
		<tr>
			<td>channelUrl :</td>
			<td><form:input path="channelUrl" id="channelUrl" /></td>
		</tr>
		<tr>
			<td><input value="Confirm" type="submit"></td>
		</tr>
	</table>
</form:form>
</body>
</html>