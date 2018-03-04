<%@ include file="../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>

<section class="content">
	<div class="topic"><spring:message code="signIn"/></div>
	<form name='f' method='POST'>
		<input class="form-control" type='text' name='username' placeholder="<spring:message code='account'/>"/>
		<input class="form-control" type='password' name='password' placeholder="<spring:message code='password'/>"/>
		<input class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="<spring:message code='signIn'/>" />
		<input class="btn btn-lg btn-basic btn-block" type="button" name="registrationBtn" value="<spring:message code='signUp'/>" onclick="doRegistration()"/>
	</form>
</section>

<script type="text/javascript">

	function doRegistration() {
		window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/email';
	}

</script>