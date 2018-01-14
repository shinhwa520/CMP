<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/taglib.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Test MyERP</title>
</head>
<body>

	Testing....
	
	<form:form method="POST" modelAttribute="LoginForm" action="/MyERP/login">
		<table>
			<tr>
				<td>Account:</td>
				<td><form:input path="account" id="account" /></td>
				<td><form:errors path="account" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" id="password" />
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Confirm" />
				</td>
			</tr>
		</table>
	</form:form>
	
	
</body>
</html>