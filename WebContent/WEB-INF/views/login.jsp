<%@ include file="../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>

<section id="wrapper" class="login-register login-sidebar" >
  <div class="login-box card">
    <div class="card-body">
		<div class="topic" style="padding-top: 100px;"><h3><spring:message code="userLogin"/></h3>
				<ul class="nav navbar-nav pull-right">
					<li>
						<a href="javascript:void(0)" onclick="doChangeLang('en_US')">
							English
						</a>|
						<a href="javascript:void(0)" onclick="doChangeLang('zh_CN')">
							中文
						</a>
					</li>

				</ul>
		</div>
      <form name='f' method='POST'>
		<spring:message code='invalidAccountOrPassword' var="invalidAccountOrPassword"/>
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		      <font color="red">
		        <c:out value="${invalidAccountOrPassword}"/>.
		        <br/>
		      </font>
		</c:if>
		<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />
        <div class="form-group m-t-40">
          <div class="col-xs-12">
            <input class="form-control" type='text' name='username' placeholder="<spring:message code='account'/>"/>
            <span class="alert_txt"></span>
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12">
            <input class="form-control" type='password' name='password' placeholder="<spring:message code='password'/>"/>
            <span class="alert_txt"></span>
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-12">
            <a href="javascript:void(0)" id="to-recover" class="text-dark pull-right"><i class="fa fa-lock m-r-5"></i> <spring:message code="forgotPassword"/>?</a> </div>
        </div>
        <div class="form-group text-center m-t-20">
          <div class="col-xs-12">
            <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light" type="submit"><spring:message code='signIn'/></button>
          </div>
        </div>
        <div class="form-group m-b-0">
          <div class="col-sm-12 text-center">
            <p><spring:message code="haveNoAccount"/>? <a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/process" class="text-primary m-l-5"><b><spring:message code='signUp'/></b></a></p>
          </div>
        </div>
      </form>

      <form class="form-horizontal" id="recoverform" action="index.html">
		<div class="card">
		    <div class="card-body">
		        <div class="form-group ">
		          <div class="col-xs-12">
		            <h3><spring:message code='resetPassword'/></h3>
		            <p class="text-muted"><spring:message code='registeredEmail'/></p>
		          </div>
		        </div>
		        <div class="form-group ">
		          <div class="col-xs-12">
		          	<spring:message code='email' var="email"/>
		            <input class="form-control" id="emailAddress" type="text" placeholder="${email}">
		            <span class="alert_txt"></span>
		          </div>
		        </div>
		        <div class="form-group text-center m-t-20">
		          <div class="col-xs-12">
		            <button class="btn btn-primary btn-block text-uppercase waves-effect waves-light" type="button" onclick="recoverPassword();"><spring:message code='submit'/></button>
		          </div>
		        </div>
			</div>
        </div>
      </form>

      
    </div>
  </div>
</section>

<script type="text/javascript">
var msg = '${message}';
$(function() {
	if(''!=msg) {
		alert(msg);
	}
});
function doChangeLang(lang) {
	var url = '${pageContext.request.contextPath}/changeLanguage?langType='+lang+'&refresh='+window.location.pathname;
	window.location.href = url;
}

function recoverPassword() {
	cleanErrAlert();
	var _emailAddress = $('#emailAddress').val().trim();
	var errMsg = '';
	if(''==_emailAddress){
		errMsg += '<spring:message javaScriptEscape="true" code="error.enterEmail"/>';
		$('#emailAddress').addClass('input-has-error');
		$('#emailAddress').focus();
		$('#emailAddress').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#emailAddress').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}
  	if(!validateEmail(_emailAddress)){
		errMsg += '<spring:message javaScriptEscape="true" code="error.emailFormat"/>';
		$('#emailAddress').addClass('input-has-error');
		$('#emailAddress').focus();
		$('#emailAddress').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#emailAddress').parents('.form-group').children(".alert_txt").addClass('text-danger');
	  	return false;
	}
	
	$.ajax({
		url : '${pageContext.request.contextPath}/registration/recoverPassword?emailAddress=' + _emailAddress,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	alert(data.message);
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

function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

//清空錯誤提示
function cleanErrAlert() {
	$('.form-control').removeClass('input-has-error');
	$('.form-group .alert_txt').text('');
	$('.form-group .alert_txt').removeClass('text-danger');
}

</script>
<style>
#wrapper {
    background-image:url(${pageContext.request.contextPath}/resources/assets/images/background/GtqDqDn.jpg);
    background-repeat:no-repeat;
}

.form-group {
	margin-bottom: 20px;
}
</style>