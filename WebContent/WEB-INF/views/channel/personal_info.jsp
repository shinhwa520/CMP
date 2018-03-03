<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Personal Info.</h3>
	</div>
	<div class="box-body no-padding">
	<div id="message" style="display: none"></div>
	<form:form method="GET" modelAttribute="UserInfoForm">
        <div class="box-body">
        	<div class="form-group">
				<label for="user_name"><span class="pull-right" style="color: red;">＊ </span>Name</label>
				<form:input class="form-control" path="name" id="user_name" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="account"><span class="pull-right" style="color: red;">＊ </span>Account</label>
				<form:input class="form-control" path="account" id="account" readonly="true" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="password"><span class="pull-right" style="color: red;">＊ </span>Password</label>
				<form:input class="form-control" path="password" id="password" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="phone"><span class="pull-right" style="color: red;">＊ </span>Phone</label>
				<form:input class="form-control" path="phone" id="phone" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="phone"><span class="pull-right" style="color: red;">＊ </span>Email</label>
				<form:input class="form-control" path="email" id="email" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="phone"><span class="pull-right" style="color: red;">＊ </span>WeChat</label>
				<form:input class="form-control" path="weChat" id="weChat" />
            </div>                              
        </div>
        <%--  註解 
        <div class="box-body">
        	<div class="form-group">
				<label for="status">Status</label>
				<form:input readonly="true" class="form-control" path="statusName" id="statusName" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="channelUrl">Product</label>
				<form:input readonly="true" class="form-control" path="channelUrl" id="channelUrl" />
            </div>                              
        </div>
        <div class="box-body">
        	<table style="width: 100%">
        		<tr>
        			<td style="width: 12%"><label>預計仲介渠道商</label></td>
        			<td style="width: 38%"><form:input readonly="true" class="form-control" path="agent_user" id="agent_user" style="width: 80%; text-align:right;"/></td>
        			<td style="width: 12%"><label>實際仲介渠道商</label></td>
        			<td style="width: 38%"><form:input readonly="true" class="form-control" path="_agent_user" id="_agent_user" style="width: 80%; text-align:right;"/></td>
        		</tr>
        		<tr>
        			<td style="width: 12%"><label>預計仲介客戶</label></td>
        			<td style="width: 38%"><form:input readonly="true" class="form-control" path="agent_cust" id="agent_cust" style="width: 80%; text-align:right;"/></td>
        			<td style="width: 12%"><label>實際仲介客戶</label></td>
        			<td style="width: 38%"><form:input readonly="true" class="form-control" path="_agent_cust" id="_agent_cust" style="width: 80%; text-align:right;"/></td>
        		</tr>
        		<tr>
        			<td style="width: 12%"><label>預計成交量</label></td>
        			<td style="width: 38%"><form:input readonly="true" class="form-control" path="volume" id="volume" style="width: 80%; text-align:right;"/></td>
        			<td style="width: 12%"><label>實際成交量</label></td>
        			<td style="width: 38%"><form:input readonly="true" class="form-control" path="_volume" id="_volume" style="width: 80%; text-align:right;"/></td>
        		</tr>
        	</table>                             
        </div>
		--%>
		<div class="modal-footer">
       		<button type="button" class="btn btn-primary" id="btnProfileSave" onclick="btnSaveClicked();">Save</button>
		</div>
	</form:form>
	</div>
</div>
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
	
	//頁面輸入檢核
	$('.form-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	if (''==user_name.trim()) {
		isError = true;
		$('#user_name').parents('.form-group').addClass('has-error');
		errMsg += '！Name為必填<br/>';
	}
	if (''==account.trim()) {
		isError = true;
		$('#account').parents('.form-group').addClass('has-error');
		errMsg += '！Account為必填<br/>';
	}
	if (''==password.trim()) {
		isError = true;
		$('#password').parents('.form-group').addClass('has-error');
		errMsg += '！Password為必填<br/>';
	}
	if (''==phone.trim()) {
		isError = true;
		$('#phone').parents('.form-group').addClass('has-error');
		errMsg += '！Phone為必填<br/>';
	}
	if (''==email.trim()) {
		isError = true;
		$('#email').parents('.form-group').addClass('has-error');
		errMsg += '！Email為必填<br/>';
	}
	if (''==weChat.trim()) {
		isError = true;
		$('#weChat').parents('.form-group').addClass('has-error');
		errMsg += '！WeChat為必填<br/>';
	}
	
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
</script>