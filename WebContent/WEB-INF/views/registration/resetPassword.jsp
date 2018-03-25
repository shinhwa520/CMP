<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>
<div class="page-wrapper">
	<div class="container-fluid">
		
		<!-- vertical wizard -->
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body wizard-content ">
					
						<h4 class="card-title"><spring:message code='resetPassword'/></h4>
						<form:form  modelAttribute="UserInfoForm" action="#" class="tab-wizard vertical wizard-circle">
						<form:hidden path="userId" id="userId" />


							<h6><spring:message code='createAccount'/></h6>
							<section>
							<spring:message code='password' var="password"/>
					        	<div class="card">
									<h5 class="card-header"><spring:message code='accountInformation'/></h5>
									<div class="row card-body">
										<div class="col-md-12">
											<div class="form-group">
												<label for="password"><spring:message code="password"/> :</label>
												<input type="password" class="form-control" id="password" placeholder="${password}" maxlength="12" >
												<span class="alert_txt"></span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label for="password"><spring:message code="confirmPassword"/> :</label>
												<input type="password" class="form-control" id="password2" placeholder="${password}" maxlength="12" >
												<span class="alert_txt"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<input class="btn btn-primary pull-right" value="<spring:message code='confirm'/>" type="button" onclick="userInfoConfirm();">
										</div>
									</div>
								</div>
							</section>
						</form:form >
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>
<script>
$(function() {
	$( "#password2" ).focusout(function(){
		validatePassword2();
	});
});

function userInfoConfirm() {
	cleanErrAlert();
	var password = $('#password').val().trim();
	var password2 = $('#password2').val().trim();
	if(!validateUserInfo(password, password2)) return false;
	
	$.ajax({
		url : '${pageContext.request.contextPath}/registration/passwordConfirm?userId=' + $('#userId').val()
		 																	+ '&password=' + password,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	alert(data.message);
    			setTimeout(function(){
    				window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/login';
    			}, 2000);
            } else {
            	alert(data.message);
            }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}
//清空錯誤提示
function cleanErrAlert() {
	$('.form-control').removeClass('input-has-error');
	$('.form-group .alert_txt').text('');
	$('.form-group .alert_txt').removeClass('text-danger');
}

//頁面輸入檢核
function validateUserInfo(password, password2) {
	var errMsg = '';
	//password
	if (''==password) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPassword"/>';
		$('#password').addClass('input-has-error');
		$('#password').focus();
		$('#password').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateLength(password, 4, 12)){
		errMsg += '<spring:message code="inputLengthLimit"/> 4~12<spring:message code="characters"/>';
		$('#password').addClass('input-has-error');
		$('#password').focus();
		$('#password').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateEngInt(password)){
		errMsg += '<spring:message code="InputFormatError"/>(<spring:message code="enterEngAndNum"/>)';
		$('#password').addClass('input-has-error');
		$('#password').focus();
		$('#password').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}

	if(password!=password2){
		errMsg += '<spring:message code="reConfirmPassword"/>';
		$('#password2').addClass('input-has-error');
		$('#password2').focus();
		$('#password2').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password2').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}
	return true;
}

function validatePassword2() {
	var password = $('#password').val().trim();
	var password2 = $('#password2').val().trim();
	if(password!=password2){
		$('#password2').addClass('input-has-error');
		$('#password2').focus();
		$('#password2').parents('.form-group').children(".alert_txt").text('<spring:message code="reConfirmPassword"/>');
		$('#password2').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}
}

function validateEngInt(input) {
	var regExp = /[^0-9]/g;
	return regExp.test(input);
}

function validateLength(input, lower, upper) {
	var inputLength = input.length;
	return (lower<=inputLength && inputLength<=upper);
}
</script>

<style>
.actions{
    display:none !important;
}
.steps{
    display:none !important;
}
.form-group .btn{
    border: 0px;
    margin-left: 3px;
}
.form-group .btn-primary{
    background: #009efb;
}
.form-group .input-has-error {
    border-color: #f62d51;
}
.card-has-error {
    border-style: solid;
    border-width: medium;
    border-color: #f62d51;
}
.card {
    margin-bottom: 10px;
}
body{
    background-image:url(${pageContext.request.contextPath}/resources/assets/images/background/GtqDqDn.jpg);
    background-repeat:no-repeat;
    background-position: bottom;
    background-size: cover;
}
.page-wrapper{
    background-image:url(${pageContext.request.contextPath}/resources/assets/images/background/GtqDqDn_m.jpg);
    background-repeat:no-repeat;
    background-position: bottom;
    background-size: cover;
}
</style>