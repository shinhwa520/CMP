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
			FileForm.action = "${pageContext.request.contextPath}/manage/referenceFile/delete";
			FileForm.queryFileType.value = "PRODUCT";
			FileForm.submit();
		}
	}
	
</SCRIPT>

<section class="content">
	<div class="row page-titles">
	     <div class="col-md-6 col-8 align-self-center">
	         <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="shareResource" /></h3>
	     </div>
	</div> 

	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<button type="button" class="btn btn-warning pull-right" id="btnAdd" onclick="btnAddClicked();"><spring:message code="add"/></button>
					<span class="pull-right">&nbsp;</span>
					<button type="button" class="btn btn-danger pull-right" id="btnDelete" onclick="doDelete();"><spring:message code="delete"/></button>
				</div>
				<div class="card-body">
					<form:form method="POST" modelAttribute="FileForm" action="">
						<input type="hidden" name="fileType" id="queryFileType" value="" />
						<table class="table table-striped" id="tblMain">
							<thead>
								<tr>
									<th>#</th>
									<th><spring:message code="productName"/></th>
									<th><spring:message code="fileName"/></th>
									<th><spring:message code="fileSize"/></th>
									<th><spring:message code="downloadTimes"/></th>
									<th><spring:message code="fileDescription"/></th>
									<!-- <th><spring:message code="updatedTime"/></th> -->
									<th style="width: 100px;"><spring:message code="option"/></th>
									<th style="width: 100px;"><input type="checkbox" id="delChkAll" />&nbsp;<spring:message code="selectAll"/></th>
								</tr>
							</thead>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
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
			<h4 class="modal-title"><spring:message code="edit"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="formEdit" name="formEdit" enctype="multipart/form-data">
            	<input type="hidden" name="seqNo" id="editSeqNo" value="" />
            	<input type="hidden" name="fileType" id="editFileType" value="PRODUCT" />
            	<input type="hidden" name="isAdd" id="isAdd" value="" />
            	<!-- <input type="hidden" name="productId" id="productId" value="28" /> -->
            	<input type="hidden" name="visitId" id="visitId" value="1" />
            	<input type="hidden" name="fileCategory" id="fileCategory" value="SCHEDULE" />
            	
            	<div class="box-body" id="productNameDiv" style="display:none">
		        	<div class="form-group">
		        		<label for="productName"><spring:message code="productName"/><span class="pull-right" style="color: red;">＊ </span> </label>
		        		<select class="form-control" style="width:80%" name="productId">
		        			<option value="1">8 Conlay - 康莱</option>
		        			<option value="2">Arte MK - 满家乐</option>
		        			<option value="3">DC Residensi - 白沙罗城豪华公寓</option>
		        			<option value="4">Opus - 奥普斯</option>
		        			<option value="5">Ritz-Carlton - 丽思卡尔顿</option>
		        			<option value="6">The Manor - 大马金豪</option>
		        			<option value="7">Avira Garden Terraces - 花园联排别墅</option>
		        			<option value="8">Haleton Towers - 聚富湾</option>
		        			<option value="9">Regalia Beachfront Residence - 皇庭海湾1号</option>
		        			<option value="10">18 East At Andaman - 安达曼海景公寓</option>
		        			<option value="11">Sunway - 双威依斯干达(文档)</option>
		        			<option value="12">Sunway - 双威依斯干达(图档)</option>
		        		</select>
		        	</div>
		        </div>
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
	        		<button type="button" class="btn btn-info" id="btnFileClose" data-dismiss="modal"><spring:message code="close"/></button>
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
				//"url" : '${pageContext.request.contextPath}/manage/referenceFile/getAllPublicFiles.json',
				"url" : '${pageContext.request.contextPath}/product/getProductFiles.json',
				//"type" : 'GET',
				"type" : 'POST',
				"data" : function(d) {
					d.isAdmin = true
				}
			},
			"columns" : [
				{ "data" : "dataSeq" },
				{ "data" : "productName" },
				{ "data" : "fullFileName" },
				{ "data" : "fileSize", "render": function ( data, type, full, meta ) {
												      return data.format() + ' KB';
											    } },
				{ "data" : "downloadTimes" },
				{ "data" : "fileDescription" }
				//{ "data" : "updateTime" }
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
						//return '<button class="btn btn-info waves-effect waves-light m-t-20" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onClick="btnDownloadClicked($(this))"><spring:message code="download"/></button>';
						return '<a href="#">'
								+'<span class="icon-cloud-download" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnDownloadClicked($(this))" title="<spring:message javaScriptEscape="true" code="download"/>"></span></a>'
								+'&nbsp;'
								+'<a href="#">'
								+'<span class="ti-pencil" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnEditClicked($(this));" title="<spring:message javaScriptEscape="true" code="edit"/>"></span></a>';
					}
				},
				{
					"targets" : 7,
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
	$('#editFileType').val('PRODUCT');
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
	
	$('#progressBar').text('');
	$('#progressBar').css('width','0%');
	
	$('#productNameDiv').show();
}

//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	$('#btnSave').show();
	$('#btnUpload').hide();
	$('#productNameDiv').hide();
	
	formAction = 'getFileInfo';
	console.log(btn.attr('seqNo'));
	$('.form-group').removeClass('has-error');
	$.ajax({
			url : '${pageContext.request.contextPath}/manage/referenceFile/' + formAction,
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
        url: '${pageContext.request.contextPath}/manage/referenceFile/' + formAction,
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
		url : '${pageContext.request.contextPath}/manage/referenceFile/' + formAction,
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
	var downloadUrl = "${pageContext.request.contextPath}/manage/referenceFile/download?seqNo="+btn.attr('seqNo')+"&fileType="+btn.attr('fileType')+"&fromPage=manage/referenceFile";
    window.location.href = downloadUrl;
    
    setTimeout(function(){
  		if (tblMain) {
  			tblMain.ajax.reload();
  		}
  	}, 2000);
}
</script>