<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">User info.</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblMain">
			<thead>
				<tr>
					<th rowspan="2">Name</th>
					<th rowspan="2">Status</th>
					<th rowspan="2">Channel</th>
					<th rowspan="2">酬庸%</th>
					<th colspan="2">仲介渠道商</th>
					<th colspan="2">仲介客戶</th>
					<th colspan="2">成交量</th>
					<th rowspan="2" style="width: 100px;">Option</th>
				</tr>
				<tr>
					<th>預計</th>
					<th>實際</th>
					<th>預計</th>
					<th>實際</th>
					<th>預計</th>
					<th>實際</th>
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
			<h4 class="modal-title">Edit</h4>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="user_id" id="user_id" value="" />
		        <div class="box-body">
		        	<div class="form-group">
						<label for="user_name">Name<span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="user_name" id="user_name" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="account">Account<span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="account" id="account" readonly="true" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="password">Password<span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="password" id="password" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="phone">Phone<span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="phone" id="phone" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="email">Email<span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="email" id="email" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="weChat">WeChat<span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text"  class="form-control" name="weChat" id="weChat" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="status">Status</label>
						<select name="status" id="status" >
							<option value="1">登錄帳號</option>
							<option value="2">確認email</option>
							<option value="3">維護個資</option>
							<option value="4">提交提問</option>
							<option value="5">輸入上游</option>
							<option value="6">同意條款</option>
							<option value="6">註冊完成</option>
						</select>
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="user_name">Remark</label>
						<input type="text" class="form-control" name="remark" id="remark" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="user_name">Reward</label>
						<input type="text" class="form-control" name="reward" id="reward" readonly="true" />%
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<table style="width: 100%">
		        		<tr>
		        			<td style="width: 12%"><label>預計仲介渠道商</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="agent_user" id="agent_user" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 12%"><label>實際仲介渠道商</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="_agent_user" id="_agent_user" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        		<tr>
		        			<td style="width: 12%"><label>預計仲介客戶</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="agent_cust" id="agent_cust" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 12%"><label>實際仲介客戶</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="_agent_cust" id="_agent_cust" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        		<tr>
		        			<td style="width: 12%"><label>預計成交量</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="volume" id="volume" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 12%"><label>實際成交量</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="_volume" id="_volume" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        	</table>                             
		        </div>
	            
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        		<button type="button" class="btn btn-primary" id="btnProfileSave" onclick="btnSaveClicked();">Save</button>
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
								+'<i class="fa fa-close" style="margin-right:5px"></i>Edit</span></a>';
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
		errMsg += '！Name為必填<br/>';
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