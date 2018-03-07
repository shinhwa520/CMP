<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic"><spring:message code='fillBasicInfo'/></div>
	<form:form method="POST" modelAttribute="UserInfoForm" onsubmit="return validateInput();" action="${pageContext.request.contextPath}/registration/userInfo">
		<form:hidden path="userId" id="userId" />

        <spring:message code='name' var="name"/>
        <spring:message code='account' var="account"/>
        <spring:message code='password' var="password"/>
        <spring:message code='phoneNo' var="phoneNo"/>
        <spring:message code='wechatID' var="wechatID"/>
        
		<span id="prompt" class="alert-danger hidePrompt" ><spring:message code='realName'/></span>
		<form:input class="form-control" path="name" id="name" placeholder="${name}" maxlength="8"/>
		<form:input class="form-control" path="account" id="account" placeholder="${account}"/>
		<form:input class="form-control" path="password" id="password" placeholder="${password}"/>
		<form:input class="form-control" path="phone" id="phone" placeholder="${phoneNo}"/>
		<form:input class="form-control" path="weChat" id="weChat" placeholder="${wechatID}"/>
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

    $( "#name" ).focusin(function(){
		$('#prompt').removeClass('hidePrompt');
		$('#prompt').addClass('showPrompt');
    });

    $( "#name" ).focusout(function(){
		$('#prompt').removeClass('showPrompt');
		$('#prompt').addClass('hidePrompt');
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
	  	validateString = $('#weChat').val();
	  	if(validateString.trim()==''){
	  		errorMessage('<spring:message code="error.noFillWechat"/>');
		  	return false;
		}
	}
</script>
<style>
.hidePrompt { display: none; }
.showPrompt { display: inline; }
</style>