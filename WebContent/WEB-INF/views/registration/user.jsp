<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic">please fill in your personal info. and press confirm :</div>
	<form:form method="POST" modelAttribute="UserInfoForm" onsubmit="return validateInput();" action="${pageContext.request.contextPath}/registration/userInfo">
		<form:hidden path="userId" id="userId" />
		<spring:message code='name' var="name"/>
		<spring:message code='account' var="account"/>
		<spring:message code='password' var="password"/>
		<spring:message code='phoneNo' var="phoneNo"/>
		<spring:message code='channelUrl' var="channelUrl"/>
		<form:input class="form-control" path="name" id="name" placeholder="${name}"/>
		<form:input class="form-control" path="account" id="account" placeholder="${account}"/>
		<form:input class="form-control" path="password" id="password" placeholder="${password}"/>
		<form:input class="form-control" path="phone" id="phone" placeholder="${phoneNo}"/>
		<form:input class="form-control" path="channelUrl" id="channelUrl" placeholder="${channelUrl}"/>
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
	  	var validateString = $('#name').val();
	  	if(validateString.trim()==''){
	  		errorMessage('<spring:message code="error.noFillName"/>');
	  		//alert('請輸入name');
		  	return false;
		}
	  	validateString = $('#account').val();
	  	if(validateString.trim()==''){
	  		errorMessage('<spring:message code="error.noFillAccount"/>');
		  	return false;
		}
	  	validateString = $('#password').val();
	  	if(validateString.trim()==''){
	  		errorMessage('<spring:message code="error.noFillPassword"/>');
		  	return false;
		}

		
	}
</script>