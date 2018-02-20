<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">我的客戶</h3>
		<a href="#" onclick="btnAddClicked();"><span class="label label-success pull-right" style="padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i>Add</span></a>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblMain">
			<thead>
				<tr>
					<th>Name</th>
					<th>Gender</th>
					<th>Birthday</th>
					<th>Phone</th>
					<th>Email</th>
					<th>WeChat</th>
					<th>City</th>
					<th>Address</th>
					<th>Status</th>
					<th>Option</th>
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
      	<div class="modal-body">                    
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="cust_id" id="cust_id" value="" />
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="cust_name">Name</label>
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
	                  <label for="phone">Phone</label>
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
	                  <label for="city">City</label>
	                  <input type="text" class="form-control" id="city" name="city" placeholder="Enter City">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="address">Address</label>
	                  <input type="text" class="form-control" id="address" name="address" placeholder="Enter Address">
	                </div>                              
	            </div>
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        		<button type="button" class="btn btn-primary" id="btnProfileSave" onclick="btnGustSaveClicked();">Save</button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit -->
<script>
var tblMain;
var formAction;


//[Add] 進入modal_Edit編輯
function btnAddClicked() {
	formAction = 'create';
	$('#cust_id').val('');
	$('#cust_name').val('');
	$("input[name=gender][value='M']").attr('checked',true);
	$('#birthday').val('');
	$('#phone').val('');
	$('#email').val('');
	$('#weChat').val('');
	$('#city').val('');
	$('#address').val('');
	$('#status').val('');
	
	$('#modal_Edit').modal();
}
//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	console.log(btn.attr('custId'));
	$.ajax({
			url : '${pageContext.request.contextPath}/channel/cust/getCustById/' + btn.attr('custId'),
			data : '',
			type : "GET",
			dataType : 'json',
			async: false,

			success : function(resp) {
				console.log(resp);				
				if (resp.code == '200') {
					formAction = 'update';
					$('#cust_id').val(btn.attr('custId'));
					$('#cust_name').val(resp.data.cust.name);
					$("input[name=gender][value="+resp.data.cust.gender+"]").attr('checked',true);
					$('#birthday').val(resp.data.cust.birthdayStr);
					$('#phone').val(resp.data.cust.phone);
					$('#email').val(resp.data.cust.email);
					$('#weChat').val(resp.data.cust.weChat);
					$('#city').val(resp.data.cust.city);
					$('#address').val(resp.data.cust.address);
					$('#status').val(resp.data.cust.status);
					$('#modal_Edit').modal();
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
function btnGustSaveClicked() {
	$.ajax({
		url : '${pageContext.request.contextPath}/channel/cust/' + formAction,
		data : $('#formEdit').serialize(),
		type : "POST",
		dataType : 'json',
		async: false,
		success : function(resp) {
			console.log(resp);
			
			if (resp.code == '200') {
				alert(resp.message);
				$('#modal_Edit').modal('hide');
				
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

//[Init.]
$(function() {
	tblMain = $('#tblMain').DataTable(
	{
		"bFilter" : false,
		"ordering" : false,
		"info" : false,
		"serverSide" : true,
		"bLengthChange" : false,
		"ajax" : {
			"url" : '${pageContext.request.contextPath}/channel/cust/getCustByUserId.json',
			"type" : 'GET',
			"data" : function(d) {}
		},
		"columns" : [
			{ "data" : "name" },
			{ "data" : "gender" },
			{ "data" : "birthday" },
			{ "data" : "phone" },
			{ "data" : "email" },
			{ "data" : "weChat" },
			{ "data" : "city" },
			{ "data" : "address" },
			{ "data" : "status.name" }
		],
		"columnDefs" : [ 
			{
			"targets" : 2,
			"render" : function(data, type, row) {
				return (new Date(data)).Format("yyyy-MM-dd");
				}
			},
			{
				"targets" : 9,
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

	$('#birthday').datepicker({
        dateFormat: 'yy-mm-dd',
		yearRange: "-100:+0",
		changeYear: true
	});
});
</script>