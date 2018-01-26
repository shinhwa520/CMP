<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>

<section class="content">
	<div class="topic">Login</div>
	<form name='f' method='POST'>
		<input class="form-control" type='text' name='username' placeholder="Account"/>
		<input class="form-control" type='password' name='password' placeholder="Password"/>
		<input class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="登入" />
		<input class="btn btn-lg btn-success btn-block" type="button" name="registrationBtn" value="註冊" onclick="doRegistration()"/>
	</form>
</section>

<script type="text/javascript">

	function doRegistration() {
		window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/email';
	}
</script>