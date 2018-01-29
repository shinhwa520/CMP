<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic">please fill in your personal info. and press confirm :</div>
	<form:form method="POST" modelAttribute="UserInfoForm" onsubmit="return validateInput();" action="${pageContext.request.contextPath}/registration/userInfo">
		<form:hidden path="userId" id="userId" />
		<form:input class="form-control" path="name" id="name" placeholder="name"/>
		<form:input class="form-control" path="account" id="account" placeholder="Account"/>
		<form:input class="form-control" path="password" id="password" placeholder="Password"/>
		<form:input class="form-control" path="phone" id="phone" placeholder="Phone"/>
		<form:input class="form-control" path="channelUrl" id="channelUrl" placeholder="Channel URL"/>
		<input class="btn btn-lg btn-success btn-block" value="Confirm" type="submit">
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
	  		errorMessage('請輸入name');
	  		//alert('請輸入name');
		  	return false;
		}
	  	validateString = $('#account').val();
	  	if(validateString.trim()==''){
	  		errorMessage('請輸入account');
		  	return false;
		}
	  	validateString = $('#password').val();
	  	if(validateString.trim()==''){
	  		errorMessage('請輸入password');
		  	return false;
		}

		
	}
</script>