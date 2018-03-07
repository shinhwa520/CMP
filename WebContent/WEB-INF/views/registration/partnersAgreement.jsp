<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic" style="max-width: 650px">合作伙伴委任協議</div>
	<form style="max-width: 650px">
		${agreement}
	</form>
</section>
<script>
	var msg = '${message}';
	$(function() {
		if(''!=msg) {
			errorMessage(msg);
		}
	});
	function validateInput() {
	  	var mailAddress = $('#mailAddress').val();
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