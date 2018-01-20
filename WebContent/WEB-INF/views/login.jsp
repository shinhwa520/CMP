<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMP</title>
	<script type="text/javascript">
	
		function doRegistration() {
			window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/email';
		}
		
	</script>
</head>
<body>
	<h1>Login</h1>
	<h2>${message}</h2>
	   
	<form name='f' method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="登入" /></td>
				<td><input type="button" name="registrationBtn" value="註冊" onclick="doRegistration()"/></td>
			</tr>
		</table>
	</form>
</body>
</html>