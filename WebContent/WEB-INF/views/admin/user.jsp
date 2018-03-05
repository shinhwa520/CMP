<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><spring:message code="userInfo"/></h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblMain">
			<thead>
				<tr>
					<th rowspan="2"><spring:message code='name'/></th>
					<th rowspan="2"><spring:message code='status'/></th>
					<th rowspan="2"><spring:message code='channels'/></th>
					<th rowspan="2"><spring:message code='reward'/>%</th>
					<th colspan="2"><spring:message code='channelsNo'/></th>
					<th colspan="2"><spring:message code='tourNo'/></th>
					<th colspan="2"><spring:message code='salesNo'/></th>
					<th rowspan="2" style="width: 100px;"><spring:message code='option'/></th>
				</tr>
				<tr>
					<th><spring:message code='target'/></th>
					<th><spring:message code='accomplished'/></th>
					<th><spring:message code='target'/></th>
					<th><spring:message code='accomplished'/></th>
					<th><spring:message code='target'/></th>
					<th><spring:message code='accomplished'/></th>
				</tr>
			</thead>
		</table>
	</div>
</div>
</section>

<!--.燈箱 Edit -->         
<div class="modal fade bs-example-modal-lg" id="modal_Edit" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title"><spring:message code='edit'/></h4>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="user_id" id="user_id" value="" />
		        <div class="box-body">
		        	<div class="form-group">
						<label for="user_name"><spring:message code='name'/><span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="user_name" id="user_name" maxlength="8"/>
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="account"><spring:message code='account'/><span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="account" id="account" readonly="true" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="password"><spring:message code='password'/><span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="password" id="password" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="phone"><spring:message code='phoneNo'/><span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="phone" id="phone" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="email"><spring:message code='email'/><span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="email" id="email" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="weChat"><spring:message code='wechatID'/><span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text"  class="form-control" name="weChat" id="weChat" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="status"><spring:message code='status'/></label>
						<select name="status" id="status" >
							<option value="1"><spring:message code='loginAccount'/></option>
							<option value="2"><spring:message code='confirmEmail'/></option>
							<option value="3"><spring:message code='maintainPersonalInfo'/></option>
							<option value="4"><spring:message code='submitTest'/></option>
							<option value="5"><spring:message code='enterUpstream'/></option>
							<option value="6"><spring:message code='agreeContract'/></option>
							<option value="6"><spring:message code='finishSignUp'/></option>
						</select>
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="user_name"><spring:message code='remark'/></label>
						<input type="text" class="form-control" name="remark" id="remark" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="user_name"><spring:message code='reward'/></label>
						<input type="text" class="form-control" name="reward" id="reward" readonly="true" />%
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<table style="width: 100%">
		        		<tr>
		        			<td style="width: 12%"><label><spring:message code="targetChannelsNo"/></label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="agent_user" id="agent_user" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 12%"><label><spring:message code="accomplishedChannelsNo"/></label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="_agent_user" id="_agent_user" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        		<tr>
		        			<td style="width: 12%"><label><spring:message code="targetTourNo"/></label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="agent_cust" id="agent_cust" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 12%"><label><spring:message code="accomplishedTourNo"/></label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="_agent_cust" id="_agent_cust" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        		<tr>
		        			<td style="width: 12%"><label><spring:message code="targetSalesNo"/></label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="volume" id="volume" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 12%"><label><spring:message code="accomplishedSalesNo"/></label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="_volume" id="_volume" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        	</table>                             
		        </div>
	            
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
	        		<button type="button" class="btn btn-primary" id="btnProfileSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit -->

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
<script>
var tblMain;
var formAction = 'update';

$(function() {
	tblMain = $('#tblMain').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/admin/user/getUser4Admin.json',
				"type" : 'GET',
				"data" : function(d) {
					//d.customParam = 'testestert';
				}
			},
			"columns" : [
				{ "data" : "name" },
				{ "data" : "status.name" },
				{ "data" : "channel" },
				{ "data" : "reward" },
				{ "data" : "agent_user" },
				{ "data" : "_agent_user" },
				{ "data" : "agent_cust" },
				{ "data" : "_agent_cust" },
				{ "data" : "volume" },
				{ "data" : "_volume" }
			],
			"columnDefs": [
				{
					"targets": [2],
					"render": function (data, type, row) {
						var html = '';
						if (row["channel"] != null) {
							html += row["channel"].name;
						}
						
						return html;
					}
				},
				{
					"targets" : 10,
					"data" : 'id',
					"render" : function(data, type, row) {
						return '<a href="#">'
								+'<span class="label label-warning" style="margin-right:10px" userId="' + row['id'] + '" onclick="btnEditClicked($(this));">'
								+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message javaScriptEscape="true" code="edit"/></span></a>';
					}
				}
			],
			select: true
		});
	});

//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	console.log(btn.attr('userId'));
	$('.form-group').removeClass('has-error');
	$.ajax({
			url : '${pageContext.request.contextPath}/admin/user/getUserById/' + btn.attr('userId'),
			data : '',
			type : "GET",
			dataType : 'json',
			async: false,

			success : function(resp) {
				console.log(resp);				
				if (resp.code == '200') {
					$('#user_id').val(btn.attr('userId'));
					$('#user_name').val(resp.data.user.name);
					$('#account').val(resp.data.user.account);
					$('#password').val(resp.data.user.password);
					$('#phone').val(resp.data.user.phone);
					$('#email').val(resp.data.user.email);
					$('#weChat').val(resp.data.user.weChat);
					$('#status').val(resp.data.user.status.sort);
					$('#remark').val(resp.data.user.remark);
					$('#reward').val(resp.data.user.reward);
					$('#agent_user').val(resp.data.user.agent_user);
					$('#_agent_user').val(resp.data.user._agent_user);
					$('#agent_cust').val(resp.data.user.agent_cust);
					$('#_agent_cust').val(resp.data.user._agent_cust);
					$('#volume').val(resp.data.user.volume);
					$('#_volume').val(resp.data.user._volume);
					
					$('#modal_Edit').modal();
					successMsgModal(resp.message);
				} else {
					alert(resp.message);
				}
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
}

//[Save] modal_Edit >>按下Save 儲存
function btnSaveClicked() {
	var user_name = $('#user_name').val();
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
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustName"/><br/>';
	}
	if (''==password.trim()) {
		isError = true;
		$('#password').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPassword"/><br/>';
	}
	if (''==phone.trim()) {
		isError = true;
		$('#phone').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPhontNo"/><br/>';
	}
	if (''==email.trim()) {
		isError = true;
		$('#email').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustEmail"/><br/>';
	}
	if (''==weChat.trim()) {
		isError = true;
		$('#weChat').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.MustWechat"/><br/>';
	}

	if(isError){
		errorMsgModal(errMsg);
		return false;
	}
	
	
	$.ajax({
		url : '${pageContext.request.contextPath}/admin/user/' + formAction,
		data : $('#formEdit').serialize(),
		type : "POST",
		dataType : 'json',
		async: false,
		success : function(resp) {
			console.log(resp);
			
			if (resp.code == '200') {
				successMsgModal(resp.message);
				setTimeout(function(){
					$('#modal_Edit').modal('hide');
				}, 2000);
				
				
				if (tblMain) {
					tblMain.ajax.reload();
				}
			} else {
				alert(resp);
			}
		},

		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}
</script>