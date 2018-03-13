<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/laydate/laydate.js"></script>

<SCRIPT type="text/javascript">
	
	function doDelete() {
		var haveOneChecked = false;
		$(':checkbox.delChkbox').each(function() {
		    if (this.checked) {
		    	haveOneChecked = true;
		    	return false;
		    }
		});
		
		if (!haveOneChecked) {
			alert('<spring:message code="error.noSelectFile"/>');
			
		} else {
			FileForm.action = "${pageContext.request.contextPath}/manage/file/delete";
			FileForm.queryFileType.value = "VISIT";
			FileForm.submit();
		}
	}
	
</SCRIPT>

<section class="content">
	<div class="box-body"></div>

	<form:form method="POST" modelAttribute="FileForm" action="">
		<input type="hidden" name="fileType" id="queryFileType" value="" />
		<div class="box box-primary">
			<div class="box-header with-border">
				<b><font style="font-size: 1.5em;"><spring:message code="shareResource"/></font></b>
				<a href="#" onclick="btnAddClicked();"><span class="label label-success pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code="add"/></span></a>
				<span class="pull-right">&nbsp;</span>
				<a href="#" onclick="doDelete();"><span class="label label-info pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code="delete"/></span></a>
			</div>
			<div class="box-body no-padding">
				<table class="table table-striped" id="tblMain">
					<thead>
						<tr>
							<th>#</th>
							<th><spring:message code="fileName"/></th>
							<th><spring:message code="fileSize"/></th>
							<th><spring:message code="downloadTimes"/></th>
							<th><spring:message code="fileDescription"/></th>
							<th><spring:message code="updatedTime"/></th>
							<th style="width: 100px;"><spring:message code="option"/></th>
							<th style="width: 100px;"><spring:message code="edit"/></th>
							<th style="width: 100px;"><input type="checkbox" id="delChkAll" /><spring:message code="selectAll"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</form:form>
</section>

<script>
	laydate.render({
		elem: '#date-input1' //指定元素
	   ,lang: 'en'
	});
	laydate.render({
		elem: '#date-input2' //指定元素
	   ,lang: 'en'
	});
	laydate.render({
	  	elem: '#time-input1'
	   ,type: 'time'
	   ,lang: 'en'
	}); 
	laydate.render({
	    elem: '#time-input2'
	   ,type: 'time'
	   ,lang: 'en'
	}); 
</script>

<!--.燈箱 Edit -->         
<div class="modal fade bs-example-modal-lg" id="modal_Edit" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title"><spring:message code="edit"/></h4>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="formEdit" name="formEdit" enctype="multipart/form-data">
            	<input type="hidden" name="seqNo" id="editSeqNo" value="" />
            	<input type="hidden" name="fileType" id="editFileType" value="VISIT" />
            	<input type="hidden" name="isAdd" id="isAdd" value="" />
            	<input type="hidden" name="productId" id="productId" value="4" />
            	<input type="hidden" name="visitId" id="productId" value="1" />
            	<input type="hidden" name="fileCategory" id="fileCategory" value="SCHEDULE" />
            	
		        <div class="box-body">
		        	<div class="form-group">
						<label for="fullFileName"><spring:message code="fileName"/><span class="pull-right" style="color: red;">＊ </span> </label>
            			<input type="text" class="form-control" name="fullFileName" id="editFullFileName" readonly="true" />
            			<input type="file" name="uploadFile" id="uploadFile" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="phone"><spring:message code="fileDescription"/><span class="pull-right" style="color: red;">＊ </span></label>
						<input type="text" class="form-control" name="fileDescription" id="editFileDescription" />
		            </div>                              
		        </div>
		        <div class="box-body">
	                <div class="form-group">
						<label><spring:message code="isPin"/></label>
						<span style="position: relative; ">					 <input type="radio" name="onTopChkbox" id="onTop_Yes" value="Y"  /> Y</span>
						<span style="position: relative; margin-left: 10px;"><input type="radio" name="onTopChkbox" id="onTop_No" value="N" /> N</span>
	                </div>                              
	            </div>
		        <div class="box-body">
	                <div class="form-group">
	                  <label for="beginTime"><spring:message code="postTime"/></label>
	                  	<input id="date-input1" type="text" style="width: 90px;" name="beginDateStr" placeholder="<spring:message code='y-m-d'/>" />
		  				<input id="time-input1" type="text" style="width: 60px;" name="beginTimeStr" placeholder="<spring:message code='h-m-s'/>" />
		  				<span style="color: red;"><spring:message code="aliveTimeDesc"/></span>
	                </div>                              
	            </div>  
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="beginTime"><spring:message code="endTime"/></label>
	                  	<input id="date-input2" type="text" style="width: 90px;" name="endDateStr" placeholder="<spring:message code='y-m-d'/>" />
		  				<input id="time-input2" type="text" style="width: 60px;" name="endTimeStr" placeholder="<spring:message code='h-m-s'/>" />
		  				<span style="color: red;"><spring:message code="aliveTimeDesc"/></span>
	                </div>                              
	            </div>
	            
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" id="btnFileClose" data-dismiss="modal"><spring:message code="close"/></button>
	        		<button type="button" class="btn btn-primary" id="btnSave" onclick="btnSaveClicked();" style="display: none"><spring:message code="save"/></button>
	        		<button type="button" class="btn btn-primary" id="btnUpload" onclick="btnUploadClicked();" style="display: none"><spring:message code="upload"/></button>
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
<!--/.燈箱 Edit -->

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
<script>
	$(function() {
	    $('#delChkAll').click(function () {
		    $(':checkbox.delChkbox').prop('checked', this.checked);    
		});
	});
</script>
<script>
var tblMain;
var formAction = 'update';

Number.prototype.format = function(n, x) {
    var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
    return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
};

$(function() {
	tblMain = $('#tblMain').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				//"url" : '${pageContext.request.contextPath}/manage/file/getAllPublicFiles.json',
				"url" : '${pageContext.request.contextPath}/product/getProductFiles.json',
				//"type" : 'GET',
				"type" : 'POST',
				"data" : function(d) {
					//d.isAdmin = true
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
					"targets" : 0,
				    render: function (data, type, row, meta) {
				        return meta.row + meta.settings._iDisplayStart + 1;
				    }
				},
				{
					"targets" : 6,
					"data" : 'seqNo',
					"render" : function(data, type, row) {
						return '<a href="#">'
								+'<span class="label label-success pull-center" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnDownloadClicked($(this));">'
								+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message javaScriptEscape="true" code="download"/></span></a>';
					}
				},
				{
					
					"targets" : 7,
					"data" : 'seqNo',
					"render" : function(data, type, row) {
						return '<a href="#">'
								+'<span class="label label-warning" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnEditClicked($(this));">'
								+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message javaScriptEscape="true" code="edit"/></span></a>';
					}
				},
				{
					"targets" : 8,
					data:   "seqNo",
	                render: function ( data, type, row ) {
	                	return '<input type="checkbox" name="delChkbox" class="delChkbox" value="'+data+'" onclick="chkCheckAllBtn();">';
	                },
	                className: "dt-body-center"
				}
			],
			select: true
		});
	});

//[Add] 進入modal_Edit編輯
function btnAddClicked() {
	formAction = 'modify';
	$('#isAdd').val('Y');
	$('#editSeqNo').val('');
	$('#editFileType').val('VISIT');
	$('#editFullFileName').val('');
	$('#uploadFile').val('');
	$('#uploadFile').show();
	$('#editFullFileName').hide();
	
	$('#editFileDescription').val('');
	$('input[name="onTopChkbox"]')[1].checked = true;
	$('#date-input1').val('');
	$('#time-input1').val('');
	$('#date-input2').val('');
	$('#time-input2').val('');

	$('#modal_Edit').modal();
	
	$('#btnSave').hide();
	$('#btnUpload').show();
}

//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	$('#btnSave').show();
	$('#btnUpload').hide();
	
	formAction = 'getFileInfo';
	console.log(btn.attr('seqNo'));
	$('.form-group').removeClass('has-error');
	$.ajax({
			url : '${pageContext.request.contextPath}/manage/file/' + formAction,
			data : {
					fileType: btn.attr('fileType'),
					seqNo: btn.attr('seqNo')
				   },
			type : "POST",
			dataType : 'json',
			async: false,

			success : function(resp) {
				console.log(resp);		
				if (resp.code == '200') {
					$('#isAdd').val('N');
					$('#editSeqNo').val(resp.data.fileInfo.seqNo);
					$('#editFileType').val(resp.data.fileInfo.fileType);
					$('#editFullFileName').val(resp.data.fileInfo.fullFileName);
					
					$('#uploadFile').hide();
					$('#editFullFileName').show();
					
					$('#editFileDescription').val(resp.data.fileInfo.fileDescription);
					if (resp.data.fileInfo.onTopChkbox == "Y") {
						$('input[name="onTopChkbox"]')[0].checked = true;
					} else {
						$('input[name="onTopChkbox"]')[1].checked = true;
					}
					$('#date-input1').val(resp.data.fileInfo.beginDateStr);
					$('#time-input1').val(resp.data.fileInfo.beginTimeStr);
					$('#date-input2').val(resp.data.fileInfo.endDateStr);
					$('#time-input2').val(resp.data.fileInfo.endTimeStr);
					
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

//[Upload] modal_Upload >>按下Upload
function btnUploadClicked() {
	formAction = 'upload';
	
	var fileDesc = $('#editFileDescription').val();
	var uploadFile = $('#uploadFile').val();
	//頁面輸入檢核
	$('.form-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	if (''==fileDesc.trim()) {
		isError = true;
		$('#editFileDescription').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="mustName"/><br/>';
	}
	if (''==uploadFile.trim()) {
		isError = true;
		$('#uploadFile').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="mustSelectFile"/><br/>';
	}

	if(isError){
		errorMsgModal(errMsg);
		return false;
	}
	
	// Get form
    var form = $('#formEdit')[0];
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
    		$('#btnUpload').attr('disabled', true);
    		$('#btnFileClose').attr('disabled', true);
    		$('#uploadFile').attr('disabled', true);
	    },
	    
	    success : function(resp) {
			console.log(resp);
			
			if (resp.code == '200') {
				successMsgModal(resp.message);
				setTimeout(function(){
					$('#modal_Edit').modal('hide');
				}, 1000);
				
				if (tblMain) {
					tblMain.ajax.reload();
				}
			} else {
				alert(resp.message);
			}
			
			$('#btnUpload').attr('disabled', false);
			$('#btnFileClose').attr('disabled', false);
			$('#uploadFile').attr('disabled', false);
		},

		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
			
			$('#btnUpload').attr('disabled', false);
			$('#btnFileClose').attr('disabled', false);
			$('#uploadFile').attr('disabled', false);
		}
    });
}

//[Save] modal_Edit >>按下Save 儲存
function btnSaveClicked() {
	formAction = 'modify';
	var fileDesc = $('#editFileDescription').val();
	//頁面輸入檢核
	$('.form-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	if (''==fileDesc.trim()) {
		isError = true;
		$('#editFileDescription').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="mustName"/><br/>';
	}

	if(isError){
		errorMsgModal(errMsg);
		return false;
	}
	
	$.ajax({
		url : '${pageContext.request.contextPath}/manage/file/' + formAction,
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
				}, 1000);
				
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

//[Download] 按下Download按鈕
function btnDownloadClicked(btn) {
	var downloadUrl = "${pageContext.request.contextPath}/manage/file/download?seqNo="+btn.attr('seqNo')+"&fileType="+btn.attr('fileType')+"&fromPage=manage/file";
    window.location.href = downloadUrl;
    
    setTimeout(function(){
  		if (tblMain) {
  			tblMain.ajax.reload();
  		}
  	}, 2000);
}
</script>