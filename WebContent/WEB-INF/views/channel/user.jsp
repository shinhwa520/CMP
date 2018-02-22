<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">我的渠道商</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblMain">
			<thead>
				<tr>
					<th rowspan="2">Name</th>
					<th rowspan="2">Phone</th>
					<th rowspan="2">Email</th>
					<th rowspan="2">Status</th>
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
						<label for="user_name">Name</label>
						<input type="text" readonly="true" class="form-control" name="user_name" id="user_name" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="phone">Phone</label>
						<input type="text" readonly="true" class="form-control" name="phone" id="phone" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="email">Email</label>
						<input type="text" readonly="true" class="form-control" name="email" id="email" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="status">Status</label>
						<input type="text" readonly="true" class="form-control" name="statusName" id="statusName" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<table style="width: 100%">
		        		<tr>
		        			<td style="width: 12%"><label>預計仲介渠道商</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" class="form-control" name="agent_user" id="agent_user" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 12%"><label>實際仲介渠道商</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="_agent_user" id="_agent_user" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        		<tr>
		        			<td style="width: 12%"><label>預計仲介客戶</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" class="form-control" name="agent_cust" id="agent_cust" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 12%"><label>實際仲介客戶</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" readonly="true" class="form-control" name="_agent_cust" id="_agent_cust" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        		<tr>
		        			<td style="width: 12%"><label>預計成交量</label></td>
		        			<td style="width: 38%" class="form-group"><input type="text" class="form-control" name="volume" id="volume" style="width: 80%; text-align:right;"/></td>
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
var formAction = 'updateKpi';

$(function() {
	tblMain = $('#tblMain').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/channel/user/getUserByChannelId.json',
				"type" : 'GET',
				"data" : function(d) {
					//d.customParam = 'testestert';
				}
			},
			"columns" : [
				
				{ "data" : "name" },
				{ "data" : "phone" },
				{ "data" : "email" },
				{ "data" : "status.name" },
				{ "data" : "agent_user" },
				{ "data" : "_agent_user" },
				{ "data" : "agent_cust" },
				{ "data" : "_agent_cust" },
				{ "data" : "volume" },
				{ "data" : "_volume" }
			],
			"columnDefs" : [ {
				"targets" : 10,
				"data" : 'id',
				"render" : function(data, type, row) {
					return '<a href="${pageContext.request.contextPath}/channel/user/cust?userId='+row['id']+'">' 
							+'<span class="label label-info" style="margin-right:10px" userId="' + row['id'] + '" ">' 
							+'<i class="fa fa-pencil" style="margin-right:5px"></i>view cust.</span></a>'
							+'<a href="#">'
							+'<span class="label label-warning" style="margin-right:10px" userId="' + row['id'] + '" onclick="btnEditClicked($(this));">'
							+'<i class="fa fa-close" style="margin-right:5px"></i>Edit KPI</span></a>';
				}
			} ],
			select: true
		});
	});

//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	console.log(btn.attr('userId'));
	$('.form-group').removeClass('has-error');
	$.ajax({
			url : '${pageContext.request.contextPath}/channel/user/getUserById/' + btn.attr('userId'),
			data : '',
			type : "GET",
			dataType : 'json',
			async: false,

			success : function(resp) {
				console.log(resp);				
				if (resp.code == '200') {
					$('#user_id').val(btn.attr('userId'));
					$('#user_name').val(resp.data.user.name);
					$('#phone').val(resp.data.user.phone);
					$('#email').val(resp.data.user.email);
					$('#statusName').val(resp.data.user.status.name);
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
	var agent_user = $('#agent_user').val();
	var agent_cust = $('#agent_cust').val();
	var volume = $('#volume').val();
	//頁面輸入檢核
	$('.form-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	if (!validateInt(agent_user)) {
		isError = true;
		$('#agent_user').parents('.form-group').addClass('has-error');
		errMsg += '！預計仲介渠道商必須為數字<br/>';
	}
	if (!validateInt(agent_cust)) {
		isError = true;
		$('#agent_cust').parents('.form-group').addClass('has-error');
		errMsg += '！預計仲介客戶必須為數字<br/>';
	}
	if (!validateInt(volume)) {
		isError = true;
		$('#volume').parents('.form-group').addClass('has-error');
		errMsg += '！預計成交量必須為數字<br/>';
	}
	

	if(isError){
		errorMsgModal(errMsg);
		return false;
	}
	
	
	$.ajax({
		url : '${pageContext.request.contextPath}/channel/user/' + formAction,
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

function validateInt(input) {
	var regExp = /^\d+$/;
	return regExp.test(input);
}

</script>