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
        <spring:message code='realName' var="realName"/>
        
        <div class="well"><span class="pull-left" style="color: gray;"><spring:message code='accountInformation'/></span><br>
	        <div class="box-body">
	        	<div class="form-group">
					<label for="account"><span class="pull-right" style="color: red;">＊ </span><spring:message code="account"/></label>
					<form:input class="form-control" path="account" id="account" placeholder="${account}" />
					<span class="alert" style="display: inline-block"></span>
	            </div>
	        </div>
	        <div class="box-body">
	        	<div class="form-group">
					<label for="password"><span class="pull-right" style="color: red;">＊ </span><spring:message code="password"/></label>
					<form:password class="form-control" path="password" id="password" placeholder="${password}" />
					<span class="alert" style="display: inline-block"></span>
	            </div>                              
	        </div>
	        <div class="box-body">
	        	<div class="form-group">
					<label for="password"><span class="pull-right" style="color: red;">＊ </span><spring:message code='confirmPassword'/></label>
					<input type="password" class="form-control" id="password2" placeholder="${password}" />
					<span class="alert" style="display: inline-block"></span>
	            </div>                              
	        </div>
        </div>
        
        <div class="well"><span class="pull-left" style="color: gray;"><spring:message code='personalInformation'/> </span><br>
	        <div class="box-body">
	        	<div class="form-group">
					<label for="user_name"><span class="pull-right" style="color: red;">＊ </span><spring:message code="name"/></label>
					<form:input class="form-control" path="name" id="name" placeholder="${realName}" />
					<span class="alert" style="display: inline-block"></span>
	            </div>                              
	        </div>
	        <div class="box-body">
	        	<div class="form-group">
					<label for="phone"><span class="pull-right" style="color: red;">＊ </span><spring:message code="phoneNo"/></label>
					<form:input class="form-control" path="phone" id="phone" placeholder="${phoneNo}" />
					<span class="alert" style="display: inline-block"></span>
	            </div>                              
	        </div>
	        <div class="box-body">
	        	<div class="form-group">
					<label for="weChat"><span class="pull-right" style="color: red;">＊ </span><spring:message code="wechatID"/></label>
					<form:input class="form-control" path="weChat" id="weChat" placeholder="${wechatID}" />
					<span class="alert" style="display: inline-block"></span>
	            </div>
	        </div>
		</div>
		<input class="btn btn-lg btn-success btn-block" value="<spring:message code='confirm'/>" type="submit">
	</form:form>
</section>
<script>
var msg = '${message}';
$(function() {
	if(''!=msg) {
		errorMessage(msg);
	}
    $( "#password2" ).focusout(function(){
    	validatePassword2();
    });
});

//頁面輸入檢核
function validateInput() {
	$('.form-group').removeClass('has-error');
	$('.box-body .form-group .alert').text('');
	$('.box-body .form-group .alert').removeClass('alert-danger');
	var account = $('#account').val().trim();
	var password = $('#password').val().trim();
	var password2 = $('#password2').val().trim();
	var user_name = $('#name').val().trim();
	var phone = $('#phone').val().trim();
	var weChat = $('#weChat').val().trim();
	var errMsg = '';

	
	//account
	if (''==account) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustAccount"/>';
		$('#account').parents('.form-group').addClass('has-error');
		$('#account').parents('.form-group').children(".alert").text(errMsg);
		$('#account').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	} else if (!validateLength(account, 4, 12)){
		errMsg += '<spring:message code="inputLengthLimit"/> 4~12<spring:message code="characters"/>';
		$('#account').parents('.form-group').addClass('has-error');
		$('#account').parents('.form-group').children(".alert").text(errMsg);
		$('#account').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	}

	//password
	if (''==password) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPassword"/>';
		$('#password').parents('.form-group').addClass('has-error');
		$('#password').parents('.form-group').children(".alert").text(errMsg);
		$('#password').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	} else if (!validateLength(password, 4, 12)){
		errMsg += '<spring:message code="inputLengthLimit"/> 4~12<spring:message code="characters"/>';
		$('#password').parents('.form-group').addClass('has-error');
		$('#password').parents('.form-group').children(".alert").text(errMsg);
		$('#password').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	} else if (!validateEngInt(password)){
		errMsg += '<spring:message code="InputFormatError"/>(<spring:message code="enterEngAndNum"/>)';
		$('#password').parents('.form-group').addClass('has-error');
		$('#password').parents('.form-group').children(".alert").text(errMsg);
		$('#password').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	}

	if(password!=password2){
		errMsg += '<spring:message code="reConfirmPassword"/>';
		$('#password2').parents('.form-group').addClass('has-error');
		$('#password2').parents('.form-group').children(".alert").text(errMsg);
		$('#password2').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	}

	//user_name
	if (''==user_name) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustName"/>';
		$('#name').parents('.form-group').addClass('has-error');
		$('#name').parents('.form-group').children(".alert").text(errMsg);
		$('#name').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	} else if (!validateLength(user_name, 2, 20)){
		errMsg += '<spring:message code="inputLengthLimit"/> 2~20<spring:message code="characters"/>';
		$('#name').parents('.form-group').addClass('has-error');
		$('#name').parents('.form-group').children(".alert").text(errMsg);
		$('#name').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	}

	//phone
	if (''==phone) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPhoneNo"/>';
		$('#phone').parents('.form-group').addClass('has-error');
		$('#phone').parents('.form-group').children(".alert").text(errMsg);
		$('#phone').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	} else if (!validateLength(phone, 8, 20)){
		errMsg += '<spring:message code="inputLengthLimit"/> 8~20<spring:message code="characters"/>';
		$('#phone').parents('.form-group').addClass('has-error');
		$('#phone').parents('.form-group').children(".alert").text(errMsg);
		$('#phone').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	} else if (!validateInt(phone)){
		errMsg += '<spring:message code="InputFormatError"/>(<spring:message code="enterNumber"/>)';
		$('#phone').parents('.form-group').addClass('has-error');
		$('#phone').parents('.form-group').children(".alert").text(errMsg);
		$('#phone').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	}

	//weChat
	if (''==weChat) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustWechat"/>';
		$('#weChat').parents('.form-group').addClass('has-error');
		$('#weChat').parents('.form-group').children(".alert").text(errMsg);
		$('#weChat').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	} else if (!validateLength(weChat, 4, 20)){
		errMsg += '<spring:message code="inputLengthLimit"/> 4~20<spring:message code="characters"/>';
		$('#weChat').parents('.form-group').addClass('has-error');
		$('#weChat').parents('.form-group').children(".alert").text(errMsg);
		$('#weChat').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	}
}

function validateInt(input) {
	var regExp = /^\d+$/;
	return regExp.test(input);
}

function validateEngInt(input) {
	var regExp = /[^0-9]/g;
	return regExp.test(input);
}

function validateLength(input, lower, upper) {
	var inputLength = input.length;
	return (lower<=inputLength && inputLength<=upper);
}

function validatePassword2() {
	var password = $('#password').val().trim();
	var password2 = $('#password2').val().trim();
	if(password!=password2){
		$('#password2').parents('.form-group').addClass('has-error');
		$('#password2').parents('.form-group').children(".alert").text('<spring:message code="reConfirmPassword"/>');
		$('#password2').parents('.form-group').children(".alert").addClass('alert-danger');
		return false;
	}
}

	
</script>
<style>
.well {
	padding: 5px;
}
.box-body {
	padding-top: 1px;
    padding-bottom: 1px;
}
.box-body .form-group {
	padding: 0px;
	margin-bottom:0px;
}
.box-body .form-group label{
	width: 90px;
}
.box-body .form-group .form-control{
	display: inline-block;
	width: 450px;
}
.box-body .form-group .alert {
	width: 350px;
	margin-top: 0px;
	margin-bottom: 0px;
}
.box-body .form-group .alert-danger {
	padding-top: 9px;
    padding-bottom: 9px;
    margin-bottom: 5px;
    font-size: 16px;
}


.content .topic{
    max-width: 990px;
}
.content form {
	min-width: 300px;
    max-width: 990px;
	padding-top: 10px;
    padding-bottom: 10px;
}
</style>