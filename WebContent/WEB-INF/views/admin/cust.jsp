<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Cust info.</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblMain">
			<thead>
				<tr>
					<th>Name</th>
					<th>Gender</th>
					<th>Birthday</th>
					<th>Email</th>
					<th>Census</th>
					<th>身分證件1</th>
					<th>證件號碼</th>
					<th>證件姓名</th>
					<th>身分證件2</th>
					<th>證件號碼</th>
					<th>證件姓名</th>
					<th>User</th>
					<th style="width: 100px;">Option</th>
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
            	<input type="hidden" name="cust_id" id="cust_id" value="" />
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="cust_name">Name<span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="cust_name" name="cust_name" placeholder="Enter Name">
	                </div>                              
	            </div>                         
	            <div class="box-body">
	                <div class="form-group">
						<label>Gender</label>
						<span style="position: relative; ">					 <input type="radio" name="gender" id="male" value="M"  />男</span>
						<span style="position: relative; margin-left: 10px;"><input type="radio" name="gender" id="female" value="F"/>女</span>
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="birthday">Birthday</label>
	                  <input type="text" class="form-control" id="birthday" name="birthday" placeholder="Enter Birthday">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="phone">Phone<span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter Phone">
	                </div>                              
	            </div>  
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="email">Email</label>
	                  <input type="text" class="form-control" id="email" name="email" placeholder="Enter Email">
	                </div>                              
	            </div>  
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="weChat">WeChat</label>
	                  <input type="text" class="form-control" id="weChat" name="weChat" placeholder="Enter WeChat">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity1_id">身分證件1</label>
	                  <select id="identity1_id" name="identity1_id">
	                  	<option value="0">=== 請選擇 ===</option>
						<option value="1">身分證</option>
						<option value="2">護照</option>
						<option value="3">台胞證</option>
	                  </select>
	                  <label for="identity1_code" style="text-align: right;" >證件號碼</label>
	                  <input type="text" id="identity1_code" name="identity1_code" >
	                  <label for="identity1_name" style="text-align: right;" >證件姓名</label>
	                  <input type="text" id="identity1_name" name="identity1_name" >
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity2_id">身分證件2</label>
	                  <select id="identity2_id" name="identity2_id">
	                  	<option value="0">=== 請選擇 ===</option>
						<option value="1">身分證</option>
						<option value="2">護照</option>
						<option value="3">台胞證</option>
	                  </select>
	                  <label for="identity2_code" style="text-align: right;" >證件號碼</label>
	                  <input type="text" id="identity2_code" name="identity2_code" >
	                  <label for="identity2_name" style="text-align: right;" >證件姓名</label>
	                  <input type="text" id="identity2_name" name="identity2_name" >
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="city">City</label>
	                  <input type="text" class="form-control" id="city" name="city" placeholder="Enter City">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="census">Census</label>
	                  <input type="text" class="form-control" id="census" name="census" placeholder="Enter Census">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="address">Address</label>
	                  <input type="text" class="form-control" id="address" name="address" placeholder="Enter Address">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="remark">Remark</label>
	                  <input type="text" class="form-control" id="remark" name="remark" placeholder="Enter Remark">
	                </div>                              
	            </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="status">Status</label>
						<select name="status" id="status" >
							<option value="0">=== 請選擇 ===</option>
							<option value="1">已登记</option>
							<option value="2">已收团费</option>
							<option value="3">已订机票</option>
							<option value="4">已办签证</option>
							<option value="5">已订酒店</option>
							<option value="6">已参观</option>
							<option value="7">签约</option>
							<option value="8">已付订金</option>
							<option value="9">支付首付</option>
							<option value="10">贷款申请</option>
							<option value="11">完成付款</option>
							<option value="12">已结算佣</option>
						</select>
		            </div>                              
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

<script>
var tblMain;
var formAction = 'update';
//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	console.log(btn.attr('custId'));
	$('.form-group').removeClass('has-error');
	$.ajax({
			url : '${pageContext.request.contextPath}/admin/cust/getCustById/' + btn.attr('custId'),
			data : '',
			type : "GET",
			dataType : 'json',
			async: false,

			success : function(resp) {
				console.log(resp);				
				if (resp.code == '200') {
					
					$('#cust_id').val(btn.attr('custId'));
					$('#cust_name').val(resp.data.cust.name);
					$("input[name=gender][value="+resp.data.cust.gender+"]").attr('checked',true);
					$('#birthday').val(resp.data.cust.birthdayStr);
					$('#phone').val(resp.data.cust.phone);
					$('#email').val(resp.data.cust.email);
					$('#weChat').val(resp.data.cust.weChat);
					
					$('#identity1_id').val(resp.data.cust.identity1_id);
					$('#identity1_code').val(resp.data.cust.identity1_code);
					$('#identity1_name').val(resp.data.cust.identity1_name);
					$('#identity2_id').val(resp.data.cust.identity2_id);
					$('#identity2_code').val(resp.data.cust.identity2_code);
					$('#identity2_name').val(resp.data.cust.identity2_name);

				
					$('#city').val(resp.data.cust.city);
					$('#census').val(resp.data.cust.census);
					$('#address').val(resp.data.cust.address);
					$('#remark').val(resp.data.cust.remark);
					$('#status').val(resp.data.cust.status.sort);
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
	var cust_name = $('#cust_name').val();
	var phone = $('#phone').val();
	//頁面輸入檢核
	$('.form-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	if (''==cust_name.trim()) {
		isError = true;
		$('#cust_name').parents('.form-group').addClass('has-error');
		errMsg += '！Name為必填<br/>';
	}
	if (''==phone.trim()) {
		isError = true;
		$('#phone').parents('.form-group').addClass('has-error');
		errMsg += '！Phone為必填<br/>';
	}

	if(isError){
		errorMsgModal(errMsg);
		return false;
	}
	
	
	
	$.ajax({
		url : '${pageContext.request.contextPath}/admin/cust/' + formAction,
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

$(function() {
	tblMain = $('#tblMain').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/admin/cust/getCust4Admin.json',
				"type" : 'GET',
				"data" : function(d) {
					//d.customParam = 'testestert';
				}
			},
			"columns" : [
				{ "data" : "name" },
				{ "data" : "gender" },
				{ "data" : "birthday" },
				{ "data" : "email" },
				{ "data" : "census" },
				{ "data" : "identity1Str" },
				{ "data" : "identity1_code" },
				{ "data" : "identity1_name" },
				{ "data" : "identity2Str" },
				{ "data" : "identity2_code" },
				{ "data" : "identity2_name" },
				{ "data" : "user.name" }
			],
			"columnDefs" : [ 
				{
					"targets" : 12,
					"data" : 'id',
					"render" : function(data, type, row) {
						return '<a href="#">'
								+'<span class="label label-warning" style="margin-right:10px" custId="' + row['id'] + '" onclick="btnEditClicked($(this));">'
								+'<i class="fa fa-close" style="margin-right:5px"></i>Edit</span></a>';
					}
				}
			],
			select: true
		});
	});

$('#birthday').datepicker({
    dateFormat: 'yy-mm-dd',
	yearRange: "-100:+0",
	changeYear: true
});
</script>