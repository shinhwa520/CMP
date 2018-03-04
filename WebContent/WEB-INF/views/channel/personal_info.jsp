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
				<form:input class="form-control" path="account" id="account" />
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
				<label for="status">Status</label>
				<form:input readonly="true" class="form-control" path="statusName" id="statusName" />
            </div>                              
        </div>
        <!-- 
        <div class="box-body">
        	<div class="form-group">
				<label for="channelUrl">Product</label>
				<form:input readonly="true" class="form-control" path="channelUrl" id="channelUrl" />
            </div>                              
        </div>
         -->

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