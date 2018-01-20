<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/taglib.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Email Confirm</title>


</head>
<body>

<form:form method="POST" modelAttribute="EmailConfirmForm" action="${pageContext.request.contextPath}/registration/emailConfirm">
	<table>
		<tr>
			<td colspan="3">please input mail address and confirm :</td>
		</tr>
		<tr>
			<td><form:input path="mailAddress" id="mailAddress" /></td>
			<td><form:errors path="mailAddress" cssClass="error" /></td>
			<td><input value="Confirm" type="submit"></td>
		</tr>
	</table>
</form:form>
</body>
</html>