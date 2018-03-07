<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic"><spring:message code='enterVerificationCode'/></div>
	<form:form method="POST" onsubmit="return validateInput();" modelAttribute="UserInfoForm" action="${pageContext.request.contextPath}/registration/user">
		<form:hidden path="userId" id="userId" />
		<spring:message code='verificationCode' var="verificationCode"/>
		<form:input class="form-control" path="verificationCode" id="verificationCode" placeholder="${verificationCode}"/>
		<form:errors class="form-control" path="verificationCode" cssClass="error" />
		<input class="btn btn-lg btn-success btn-block" value="<spring:message code='confirm'/>" type="submit">
	</form:form>
</section>
<script>
	var msg = '${message}';
	$(function() {
		if(''!=msg) {
			errorMessage(msg);
		}
	});
	function validateInput() {
	  	var mailAddress = $('#verificationCode').val();
	  	if(mailAddress.trim()==''){
	  		errorMessage('<spring:message code="enterVerificationCode"/>');
		  	return false;
		}
	}

</script>