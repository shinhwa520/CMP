<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Personal Info.</h3>
	</div>
	<div class="modal-body">
	<form:form method="GET" modelAttribute="UserInfoForm">
        <div class="box-body">
        	<div class="form-group">
				<label for="cust_name">Name</label>
				<form:input class="form-control" path="name" id="name" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="cust_name">Account</label>
				<form:input class="form-control" path="account" id="account" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="cust_name">Password</label>
				<form:input class="form-control" path="password" id="password" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="cust_name">Phone</label>
				<form:input class="form-control" path="phone" id="phone" />
            </div>                              
        </div>
        <div class="box-body">
        	<div class="form-group">
				<label for="cust_name">Product</label>
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
</script>