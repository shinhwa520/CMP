<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic">please fill in your upstream account :</div>
	<form:form method="POST" onsubmit="return validateInput();" modelAttribute="UserInfoForm" action="${pageContext.request.contextPath}/registration/emailConfirm">
		<form:hidden path="userId" id="userId" />
		<form:input class="form-control" path="mailAddress" id="mailAddress" placeholder="Email"/>
		<form:errors class="form-control" path="mailAddress" cssClass="error" />
		<input class="btn btn-lg btn-success btn-block" value="Confirm" type="submit">
	</form:form>
</section>
<script>
	function validateInput() {
	  	var mailAddress = $('#mailAddress').val();
	  	if(mailAddress.trim()==''){
	  		errorMessage('請輸入Email');
		  	return false;
		}
	  	if(!validateEmail(mailAddress)){
	  		errorMessage('Email格式錯誤，請重新輸入！');
		  	return false;
		}
	}

	function validateEmail(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	}
</script>