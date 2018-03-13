<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<form:form method="GET" modelAttribute="UserInfoForm">
<div class="box-body"></div>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><spring:message code="personalInfo"/></h3>
	</div>
	<div class="box-body no-padding">
	<div id="message" style="display: none"></div>
	
        <div class="box-body">
        	<div class="form-group">
				<label for="user_name"><span class="pull-right" style="color: red;">＊ </span><spring:message code="name"/></label>
				<form:input class="form-control" path="name" id="user_name" readonly="true"/>
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="account"><span class="pull-right" style="color: red;">＊ </span><spring:message code="account"/></label>
				<form:input class="form-control" path="account" id="account" readonly="true" />
            </div>
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="password"><span class="pull-right" style="color: red;">＊ </span><spring:message code="password"/></label>
				<form:input class="form-control" path="password" id="password" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="phone"><span class="pull-right" style="color: red;">＊ </span><spring:message code="phoneNo"/></label>
				<form:input class="form-control" path="phone" id="phone" readonly="true"/>
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="email"><span class="pull-right" style="color: red;">＊ </span><spring:message code="email"/></label>
				<form:input class="form-control" path="email" id="email" readonly="true"/>
            </div>
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="weChat"><span class="pull-right" style="color: red;">＊ </span><spring:message code="wechatID"/></label>
				<form:input class="form-control" path="weChat" id="weChat" readonly="true"/>
            </div>
        </div>
        <!--
        <div class="box-body">
        	<div class="form-group">
				<label for="reward"><span class="pull-right" style="color: red;">＊ </span><spring:message code="defaultReward"/></label>
				<form:input class="form-control" path="reward" id="reward" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="status"><spring:message code="status"/></label>
				<form:input readonly="true" class="form-control" path="statusName" id="statusName" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="channelUrl"><spring:message code="tools"/></label>
				<form:input readonly="true" class="form-control" path="channelUrl" id="channelUrl" />
            </div>                              
        </div>
         -->
		<div class="modal-footer">
       		<button type="button" class="btn btn-primary" id="btnProfileSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
		</div>
	</div>
</div>
<div class="box-body"></div>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="personalKPI"/></h3>
    </div>
    <div class="box-body">
        <table style="width: 100%">
            <tr>
                <td style="width: 12%"><label><spring:message code="targetChannelsNo"/></label></td>
                <td style="width: 38%"><form:input readonly="true" class="form-control" path="agent_user" id="agent_user" style="width: 80%; text-align:right;"/></td>
                <td style="width: 12%"><label><spring:message code="accomplishedChannelsNo"/></label></td>
                <td style="width: 38%"><form:input readonly="true" class="form-control" path="_agent_user" id="_agent_user" style="width: 80%; text-align:right;"/></td>
            </tr>
            <tr>
                <td style="width: 12%"><label><spring:message code="targetTourNo"/></label></td>
                <td style="width: 38%"><form:input readonly="true" class="form-control" path="agent_cust" id="agent_cust" style="width: 80%; text-align:right;"/></td>
                <td style="width: 12%"><label><spring:message code="accomplishedTourNo"/></label></td>
                <td style="width: 38%"><form:input readonly="true" class="form-control" path="_agent_cust" id="_agent_cust" style="width: 80%; text-align:right;"/></td>
            </tr>
            <tr>
                <td style="width: 12%"><label><spring:message code="targetSalesNo"/></label></td>
                <td style="width: 38%"><form:input readonly="true" class="form-control" path="volume" id="volume" style="width: 80%; text-align:right;"/></td>
                <td style="width: 12%"><label><spring:message code="accomplishedSalesNo"/></label></td>
                <td style="width: 38%"><form:input readonly="true" class="form-control" path="_volume" id="_volume" style="width: 80%; text-align:right;"/></td>
            </tr>
        </table>
    </div>
</div>
</form:form>
</section>
<script>
//按下Save 儲存
var formAction = 'update';
function btnSaveClicked() {
	var user_name = $('#user_name').val();
	var account = $('#account').val();
	var password = $('#password').val();
	var phone = $('#phone').val();
	var email = $('#email').val();
	var weChat = $('#weChat').val();
	var reward = $('#reward').val();
	//頁面輸入檢核
	$('.form-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	if (''==user_name.trim()) {
		isError = true;
		$('#user_name').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustName"/><br/>';
	}
	if (''==account.trim()) {
		isError = true;
		$('#account').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustAccount"/><br/>';
	}
	if (''==password.trim()) {
		isError = true;
		$('#password').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPassword"/><br/>';
	}
	if (''==phone.trim()) {
		isError = true;
		$('#phone').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPhoneNo"/><br/>';
	}
	if (''==email.trim()) {
		isError = true;
		$('#email').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustEmail"/><br/>';
	}
	if (''==weChat.trim()) {
		isError = true;
		$('#weChat').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustWechat"/><br/>';
	}
	/*
	if (!validateInt(reward)) {
		isError = true;
		$('#reward').parents('.form-group').addClass('has-error');
		errMsg += '！Reward必須為數字<br/>';
	}
	*/
	
	//頁面輸入檢核Error
	if(isError){
		errorMessage(errMsg);
		return false;
	}

	
	$.ajax({
		url : '${pageContext.request.contextPath}/channel/personalInfo/' + formAction,
		data : $('#UserInfoForm').serialize(),
		type : "POST",
		dataType : 'json',
		async: false,
		success : function(resp) {
			console.log(resp);
			alert(resp.message);
		},

		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}

function validateInt(input) {
	var regExp = /^\d+$/;
	return regExp.test(input);
}
</script>
<style>
.box-primary .box-body .box-body{ padding-bottom: 2px; }
.modal-footer { padding-top: 5px; padding-bottom: 5px; }
</style>