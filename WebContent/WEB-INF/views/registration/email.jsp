<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic"><spring:message code='enterEmail'/></div>
	<form:form method="POST" onsubmit="return validateInput();" modelAttribute="UserInfoForm" action="${pageContext.request.contextPath}/registration/emailConfirm">
		<spring:message code='email' var="email"/>
		<form:input class="form-control" path="email" id="email" placeholder="${email}"/>
		<form:errors class="form-control" path="email" cssClass="error" />
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
	  	var mailAddress = $('#email').val();
	  	if(mailAddress.trim()==''){
	  		errorMessage('<spring:message code="error.enterEmail"/>');
		  	return false;
		}
	  	if(!validateEmail(mailAddress)){
	  		errorMessage('<spring:message code="error.emailFormat"/>');
		  	return false;
		}
	}

	function validateEmail(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	}
</script>