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
		<input class="btn btn-lg btn-warning btn-block" value="<spring:message code='reacquireVerificationCode'/>" type="button" onclick="reGenToken(this);">
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
	
	function reGenToken(o) {
		$.ajax({
			url : '${pageContext.request.contextPath}/registration/reGenToken?userId=' + $('#userId').val(),
			type : "GET",
			dataType : 'json',
			async: false,
			contentType:"application/json;charset=utf-8", 
			success : function(data) {
                if (data.status === 200) {
                	successMessage("<spring:message code='verificationCodeHasBeenResent'/>");
                } else {
                    errorMessage(data.message);
                }
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
		time(o);
	}
	
	var wait=60; 
	function time(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");      
			o.value="<spring:message code='reacquireVerificationCode'/>"; 
			wait = 60; 
		} else {
			o.setAttribute("disabled", true); 
			o.value=wait+" <spring:message code='secondsCanBeResent'/>"; 
			wait--; 
			setTimeout(function() { 
				time(o) 
			}, 
			1000) 
		}
	}

</script>