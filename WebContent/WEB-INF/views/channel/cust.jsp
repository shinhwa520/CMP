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
					<%--  註解 
					<th>Gender</th>
					<th>Birthday</th>
					--%>
					<th>Phone</th>
					<th>Email</th>
					<th>WeChat</th>
					<%--  註解 
					<th>City</th>
					<th>Address</th>
					--%>
					<th>Status</th>
					<th></th>
					<th style="width: 100px;">Option</th>
				</tr>
			</thead>
		</table>
		<input type="hidden" name="clickedCustId" id="clickedCustId" value="-1" />
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
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="status">Status</label>
	                  <input type="text" class="form-control" id="status" name="status" readonly="true" >
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

<!--.燈箱 File -->         
<div class="modal fade bs-example-modal-lg" id="modal_File" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">File</h4>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="formFile" name="formFile">
				<input type="hidden" name="fileType" id="queryFileType" value="" />
				<div class="box box-primary">
					<div class="box-header with-border">
						<b><font style="font-size: 1.5em;">客戶檔案&nbsp;&nbsp;>&nbsp;&nbsp;<span id="custNameLabel"></span></font></b>
						<a href="#" onclick="btnAddFileClicked();"><span class="label label-success pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i>Add</span></a>
						<span class="pull-right">&nbsp;</span>
						<a href="#" onclick="btnDeleteClicked();"><span class="label label-info pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i>Delete</span></a>
					</div>
					<div class="box-body no-padding">
						<table class="table table-striped" id="custFileMain" width="100%">
							<thead>
								<tr>
									<th>#</th>
									<th>檔案名稱</th>
									<th>檔案大小</th>
									<th>下載次數</th>
									<th>檔案描述</th>
									<th>更新時間</th>
									<th style="width: 100px;">Option</th>
									<th style="width: 100px;"><input type="checkbox" id="delChkAll" /> 全選</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 File -->

<!--.燈箱 Upload -->         
<div class="modal fade bs-example-modal-lg" id="modal_Upload" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">Upload</h4>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="formUpload" name="formUpload" enctype="multipart/form-data">
            	<input type="hidden" name="seqNo" id="editSeqNo" value="" />
            	<input type="hidden" name="fileType" id="editFileType" value="CUSTOMER" />
            	<input type="hidden" name="isAdd" id="isAdd" value="Y" />
            	<input type="hidden" name="onTopChkbox" id="onTopChkbox" value="N" />
            	<input type="hidden" name="custId" id="custId" value="" />
            	
		        <div class="box-body">
		        	<div class="form-upload-group">
						<label for="fullFileName">檔案名稱<span class="pull-right" style="color: red;">＊ </span> </label>
            			<input type="text" class="form-control" name="fullFileName" id="editFullFileName" readonly="true" />
            			<input type="file" name="uploadFile" id="uploadFile" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-upload-group">
						<label for="phone">檔案描述<span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="fileDescription" id="editFileDescription" />
		            </div>                              
		        </div>
	            
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        		<button type="button" class="btn btn-primary" id="btnUpload" onclick="btnUploadClicked();">Upload</button>
				</div>
			</form>
			<br />

		    <!-- Bootstrap Progress bar -->
		    <div class="progress">
		      <div id="progressBar" class="progress-bar progress-bar-success" role="progressbar"
		        aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
		    </div>
		
		    <!-- Alert -->
		    <div id="alertMsg" style="color: red;font-size: 18px;"></div>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Upload -->

<script>
var tblMain;
var custFileMain;
var formAction;


//[Add] 進入modal_Edit編輯
function btnAddClicked() {
	formAction = 'create';
	$('.form-group').removeClass('has-error');
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
	$('.form-group').removeClass('has-error');
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
					//successMsgModal(resp.message);
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
		url : '${pageContext.request.contextPath}/channel/cust/' + formAction,
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

//[File] 進入modal_File
function btnFileClicked(btn) {
	$('#clickedCustId').val(btn.attr('custId'));
	$('#custId').val(btn.attr('custId'));
	$('#custNameLabel').text(btn.attr('custName'));
	$('#delChkAll').prop('checked', false);
	
	custFileMain.ajax.reload();	//重查資料
	$('#modal_File').modal();
	
}

//[Init.]
$(function() {
	//bsStep(3);
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
			{ "data" : "phone" },
			{ "data" : "email" },
			{ "data" : "weChat" },
			{ "data" : "status.name" },
			{ "data" : "status.sort" }
		],
		"columnDefs" : [ 
			{
				"targets" : [5],
				"render" : function(data, type, row) {
							var sort = row['status'].sort;
							console.log(sort);
							var html = '<ul class="nav nav-pills nav-justified step step-arrow">';
							for(var i=0; i<10; i++){
								if(i<sort)
									html += '<li class="active"><a></a></li>';
								else
									html += '<li><a></a></li>';
							}
							html+='</ul>';
							return html;
						}
			},
			{
				"targets" : 6,
				"data" : 'id',
				"render" : function(data, type, row) {
					return '<a href="#">'
							+'<span class="label label-warning" style="margin-right:10px" custId="' + row['id'] + '" onclick="btnEditClicked($(this));">'
							+'<i class="fa fa-close" style="margin-right:5px"></i>Edit</span></a>'
							+'&nbsp;'
							+'<a href="#">'
							+'<span class="label label-info pull-center" style="margin-right:10px" custId="' + row['id'] + '" custName="' + row['name'] + '" onclick="btnFileClicked($(this));">'
							+'<i class="fa fa-close" style="margin-right:5px"></i>File</span></a>';
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
	
	custFileMain = $('#custFileMain').DataTable(
	{
		"bFilter" : false,
		"ordering" : false,
		"info" : false,
		"serverSide" : true,
		"bLengthChange" : false,
		"ajax" : {
			"url" : '${pageContext.request.contextPath}/channel/cust/getCustFileById.json',
			"type" : 'GET',
			"data" : function(d) {
						d.custId = $('#clickedCustId').val()
					}
		},
		"columns" : [
			{ "data" : "dataSeq" },
			{ "data" : "fullFileName" },
			{ "data" : "fileSize", "render": function ( data, type, full, meta ) {
											      return data.format() + ' KB';
										    } },
			{ "data" : "downloadTimes" },
			{ "data" : "fileDescription" },
			{ "data" : "updateTime" }
		],
		"columnDefs": [
			{
				"targets" : 6,
				"data" : 'seqNo',
				"render" : function(data, type, row) {
					return '<a href="#">'
							+'<span class="label label-success pull-center" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnDownloadClicked($(this));">'
							+'<i class="fa fa-close" style="margin-right:5px"></i>Download</span></a>';
				}
			},
			{
				"targets" : 7,
				data:   "seqNo",
                render: function ( data, type, row ) {
                	return '<input type="checkbox" name="delChkbox" class="delChkbox" value="'+data+'">';
                },
                className: "dt-body-center"
			}
		],
		select: true
	});
	
	 $('#delChkAll').click(function () {
		    $(':checkbox.delChkbox').prop('checked', this.checked);    
		});
});

Number.prototype.format = function(n, x) {
    var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
    return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
};

//[Add] 進入modal_Edit編輯
function btnAddFileClicked() {
	formAction = 'upload';
	$('#editSeqNo').val('');
	$('#editFullFileName').val('');
	$('#uploadFile').val('');
	$('#uploadFile').show();
	$('#editFullFileName').hide();
	$('#editFileDescription').val('');

	$('#modal_Upload').modal();
}

//[Upload] modal_Upload >>按下Upload
function btnUploadClicked() {
	formAction = 'upload';
	
	var fileDesc = $('#editFileDescription').val();
	var uploadFile = $('#uploadFile').val();
	//頁面輸入檢核
	$('.form-upload-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	if (''==fileDesc.trim()) {
		isError = true;
		$('#editFileDescription').parents('.form-group').addClass('has-error');
		errMsg += '！Name為必填<br/>';
	}
	if (''==uploadFile.trim()) {
		isError = true;
		$('#uploadFile').parents('.form-group').addClass('has-error');
		errMsg += '！請選擇檔案<br/>';
	}

	if(isError){
		errorMsgModal(errMsg);
		return false;
	}
	
	// Get form
    var form = $('#formUpload')[0];
    var data = new FormData(form);
 
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '${pageContext.request.contextPath}/manage/file/' + formAction,
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        xhr: function(){
	        //Get XmlHttpRequest object
	         var xhr = $.ajaxSettings.xhr() ;
	        
	        //Set onprogress event handler 
	         xhr.upload.onprogress = function(event){
	          	var perc = Math.round((event.loaded / event.total) * 100);
	          	$('#progressBar').text(perc + '%');
	          	$('#progressBar').css('width',perc + '%');
	         };
	         return xhr ;
    	},
    	beforeSend: function( xhr ) {
    		//Reset alert message and progress bar
    		$('#alertMsg').text('');
    		$('#progressBar').text('');
    		$('#progressBar').css('width','0%');
	    },
	    
	    success : function(resp) {
			console.log(resp);
			
			if (resp.code == '200') {
				successMsgModal(resp.message);
				setTimeout(function(){
					$('#modal_Upload').modal('hide');
				}, 1000);
				
				if (custFileMain) {
					custFileMain.ajax.reload();
				}
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

function btnDeleteClicked() {
	formAction = "deleteAj";
	
	var seqNos = "";
	var haveOneChecked = false;
	$(':checkbox.delChkbox').each(function() {
	    if (this.checked) {
	    	haveOneChecked = true;
	    	seqNos += this.value;
	    	seqNos += ",";
	    }
	});
	
	if (!haveOneChecked) {
		alert("請至少選擇一項要刪除的檔案!");
		
	} else {
		$.ajax({
			url : '${pageContext.request.contextPath}/manage/file/' + formAction,
			data : {
					fileType: "CUSTOMER",
					seqNos: seqNos
				   },
			type : "POST",
			dataType : 'json',
			async: false,
			success : function(resp) {
				console.log(resp);
				
				if (resp.code == '200') {
					successMsgModal(resp.message);
					
					$('#delChkAll').prop('checked', false);
					if (custFileMain) {
						custFileMain.ajax.reload();
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
}

//[Download] 按下Download按鈕
function btnDownloadClicked(btn) {
	var downloadUrl = "${pageContext.request.contextPath}/manage/file/download?seqNo="+btn.attr('seqNo')+"&fileType="+btn.attr('fileType')+"&fromPage=channel/cust/list";
  	window.location.href = downloadUrl;
  	
  	setTimeout(function(){
  		if (custFileMain) {
  			custFileMain.ajax.reload();
  		}
  	}, 2000);
}


function bsStep(i) {
	$('.step').each(function() {
		var a, $this = $(this);
		if(i > $this.find('li').length) {
			console.log('您输入数值已超过步骤最大数量' + $this.find('li').length + '！！！');
			a=$this.find('li').length;
		} else if(i == undefined && $this.data('step') == undefined) {
			a = 1
		} else if(i == undefined && $this.data('step') != undefined) {
			a = $(this).data('step');
		} else {
			a = i
		}
		$(this).find('li').removeClass('active');
		$(this).find('li:lt(' + a + ')').addClass('active');
	})
}
</script>