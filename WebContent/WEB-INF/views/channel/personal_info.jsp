<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Personal Info.</h3>
	</div>
	<div class="box-body no-padding">
	<form:form method="GET" modelAttribute="UserInfoForm">
        <div class="box-body">
        	<div class="form-group">
				<label for="cust_name">Name</label>
				<form:input class="form-control" path="name" id="name" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="account">Account</label>
				<form:input class="form-control" path="account" id="account" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="password">Password</label>
				<form:input class="form-control" path="password" id="password" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="phone">Phone</label>
				<form:input class="form-control" path="phone" id="phone" />
            </div>                              
        </div>
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