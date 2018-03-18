<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">

	<div class="row page-titles">
	     <div class="col-md-6 col-8 align-self-center">
	         <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="allCust" /></h3>
	     </div>
	</div>  

	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
	
					<input type="text" id="keyword" name="keyword" placeholder="Search Keyword"/>
					<a href="#" onclick="doQuery();"><span class="label label-info" style="padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code="search"/></span></a>
		
					<span><spring:message code="allCust.search.info"/></span>
				</div>
			</div>
			<div class="modal_msg" style="display: none"></div>
			<div class="card">
				<div class="card-body">
					<table class="table table-striped" id="tblMain">
						<thead>
							<tr>
								<th><spring:message code="name"/></th>
								<!-- 
								<th><spring:message code="gender"/></th>
								<th><spring:message code="birthday"/></th>
								 -->
								<th><spring:message code="email"/></th>
								<th><spring:message code="residentialAddress"/></th>
								<th><spring:message code="IDType"/></th>
								<th><spring:message code="IDNumber"/></th>
								<th><spring:message code="IDName"/></th>
								<!--
								<th><spring:message code="secondIDType"/></th>
								<th><spring:message code="secondIDNo"/></th>
								<th><spring:message code="secondIDName"/></th>
								 -->
								<th><spring:message code="user"/></th>
								<th style="width: 100px;"><spring:message code="option"/></th>
							</tr>
						</thead>
					</table>
			        <input type="hidden" name="clickedCustId" id="clickedCustId" value="-1" />
				</div>
			</div>
		</div>
	</div>
	
</section>

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
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="cust_id" id="cust_id" value="" />
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="cust_name"><spring:message code="name"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="cust_name" name="cust_name" placeholder="<spring:message code="name"/>">
	                </div>                              
	            </div>                         
	            <div class="box-body">
	                <div class="form-group">
						<label><spring:message code="gender"/></label>
						<span style="position: relative; ">					 <input type="radio" name="gender" id="male" value="M"  /><spring:message code="male"/></span>
						<span style="position: relative; margin-left: 10px;"><input type="radio" name="gender" id="female" value="F"/><spring:message code="female"/></span>
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="birthday"><spring:message code="birthday"/></label>
	                  <input type="text" class="form-control" id="birthday" name="birthday" placeholder="<spring:message code="birthday"/>">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="phone"><spring:message code="phoneNo"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="phone" name="phone" placeholder="<spring:message code="phoneNo"/>">
	                </div>                              
	            </div>  
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="email"><spring:message code="email"/></label>
	                  <input type="text" class="form-control" id="email" name="email" placeholder="<spring:message code="email"/>">
	                </div>                              
	            </div>  
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="weChat"><spring:message code="wechatID"/></label>
	                  <input type="text" class="form-control" id="weChat" name="weChat" placeholder="<spring:message code="wechatID"/>">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity1_id"><spring:message code="IDType"/></label>
	                  <select id="identity1_id" name="identity1_id">
	                  	<option value="0">=== <spring:message code="pleaseChoose"/> ===</option>
						<option value="1"><spring:message code="ID card"/></option>
						<option value="2"><spring:message code="passport"/></option>
						<option value="3"><spring:message code="ROCID"/></option>
	                  </select>
	                  <label for="identity1_code" style="text-align: right;" ><spring:message code="IDNumber"/></label>
	                  <input type="text" id="identity1_code" name="identity1_code" >
	                  <label for="identity1_name" style="text-align: right;" ><spring:message code="IDName"/></label>
	                  <input type="text" id="identity1_name" name="identity1_name" >
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity2_id"><spring:message code="secondIDType"/></label>
	                  <select id="identity2_id" name="identity2_id">
	                  	<option value="0">=== <spring:message code="pleaseChoose"/> ===</option>
						<option value="1"><spring:message code="ID card"/></option>
						<option value="2"><spring:message code="passport"/></option>
						<option value="3"><spring:message code="ROCID"/></option>
	                  </select>
	                  <label for="identity2_code" style="text-align: right;" ><spring:message code="secondIDNo"/></label>
	                  <input type="text" id="identity2_code" name="identity2_code" >
	                  <label for="identity2_name" style="text-align: right;" ><spring:message code="secondIDName"/></label>
	                  <input type="text" id="identity2_name" name="identity2_name" >
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="city"><spring:message code="city"/></label>
	                  <input type="text" class="form-control" id="city" name="city" placeholder="<spring:message code="city"/>">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="census"><spring:message code="residentialAddress"/></label>
	                  <input type="text" class="form-control" id="census" name="census" placeholder="<spring:message code="residentialAddress"/>">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="address"><spring:message code="address"/></label>
	                  <input type="text" class="form-control" id="address" name="address" placeholder="<spring:message code="address"/>">
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="remark"><spring:message code="remarks"/></label>
	                  <input type="text" class="form-control" id="remark" name="remark" placeholder="<spring:message code="remarks"/>">
	                </div>                              
	            </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="status"><spring:message code="status"/></label>
						<select name="status" id="status" >
							<option value="0">=== <spring:message code="pleaseChoose"/> ===</option>
							<option value="1"><spring:message code="registered"/></option>
							<option value="2"><spring:message code="bookingFeePaid"/></option>
							<option value="3"><spring:message code="bookedAirTicket"/></option>
							<option value="4"><spring:message code="visaIssued"/></option>
							<option value="5"><spring:message code="bookedHotel"/></option>
							<option value="6"><spring:message code="visited"/></option>
							<option value="7"><spring:message code="contractsSigned"/></option>
							<option value="8"><spring:message code="depositPaid"/></option>
							<option value="9"><spring:message code="downPayment"/></option>
							<option value="10"><spring:message code="loanApplication"/></option>
							<option value="11"><spring:message code="progressPayments"/></option>
							<option value="12"><spring:message code="settlement"/></option>
						</select>
		            </div>                              
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

<!--.燈箱 File -->
<div class="modal fade bs-example-modal-lg" id="modal_File" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><spring:message code='file'/></h4>
            </div>
            <div class="modal_msg" style="display: none"></div>
            <div class="modal-body">
                <form role="form" id="formFile" name="formFile">
                    <input type="hidden" name="fileType" id="queryFileType" value="" />
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <b><font style="font-size: 1.5em;"><spring:message code='custFile'/>&nbsp;&nbsp;>&nbsp;&nbsp;<span id="custNameLabel"></span></font></b>
                            <a href="#" onclick="btnAddFileClicked();"><span class="label label-success pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code='add'/></span></a>
                            <span class="pull-right">&nbsp;</span>
                            <a href="#" onclick="btnDeleteClicked();"><span class="label label-info pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code='delete'/></span></a>
                        </div>
                        <div class="box-body no-padding">
                            <table class="table table-striped" id="custFileMain" width="100%">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th><spring:message code="fileName"/></th>
                                    <th><spring:message code="fileSize"/></th>
                                    <th><spring:message code="downloadTimes"/></th>
                                    <th><spring:message code="fileDescription"/></th>
                                    <th><spring:message code="updatedTime"/></th>
                                    <th style="width: 100px;"><spring:message code="option"/></th>
                                    <th style="width: 100px;"><input type="checkbox" id="delChkAll" /> <spring:message code="selectAll"/></th>
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
                <h4 class="modal-title"><spring:message code="upload"/></h4>
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
                            <label for="fullFileName"><spring:message code="fileName"/><span class="pull-right" style="color: red;">＊ </span> </label>
                            <input type="text" class="form-control" name="fullFileName" id="editFullFileName" readonly="true" />
                            <input type="file" name="uploadFile" id="uploadFile" />
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="form-upload-group">
                            <label for="phone"><spring:message code="fileDescription"/><span class="pull-right" style="color: red;">＊ </span></label>
                            <input type="text" class="form-control" name="fileDescription" id="editFileDescription" />
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
                        <button type="button" class="btn btn-primary" id="btnUpload" onclick="btnUploadClicked();"><spring:message code="upload"/></button>
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

<!--.燈箱 deleteCust -->
<div class="modal fade bs-example-modal-lg" id="modal_deleteCust" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title"><spring:message code="deleteCustomer"/></h4>
			</div>
			<div class="modal-body">
				<spring:message code="isDeleteCustomer"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
				<button type="button" class="btn btn-danger" id="btnDeleteCust" data-dismiss="modal" onclick="btnDeleteCustConfirm();"><spring:message code="delete"/></button>
			</div>
		</div><!-- /.modal-content -->
	</div>
</div>
<!--/.燈箱 deleteCust -->

<script>
var tblMain;
var custFileMain;
var formAction = 'update';

//绑定回车键执行查询
$('#keyword').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        //回车执行查询
        doQuery();
    }
});

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
		errMsg += '<spring:message code="error.mustName"/><br/>';	//！Name为必填
	}
	if (''==phone.trim()) {
		isError = true;
		$('#phone').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message code="error.mustPhoneNo"/><br/>';	//！Phone為必填
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

//[File] 進入modal_File
function btnFileClicked(btn) {
    $('#clickedCustId').val(btn.attr('custId'));
    $('#custId').val(btn.attr('custId'));
    $('#custNameLabel').text(btn.attr('custName'));
    $('#delChkAll').prop('checked', false);

    custFileMain.ajax.reload();	//重查資料
    $('#modal_File').modal();

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
				{ "data" : "email" },
				{ "data" : "census" },
				{ "data" : "identity1_id" },
				{ "data" : "identity1_code" },
				{ "data" : "identity1_name" },
				{ "data" : "user.name" }
			],
			"columnDefs" : [ 
				{
					"targets" : 3,
					"render" : function(data, type, row) {
						var i18n;

						switch(data) {
							case 1:
								i18n = '<spring:message code="ID card" />'
								break;
							case 2:
								i18n = '<spring:message code="passport" />'
								break;
							case 3:
								i18n = '<spring:message code="ROCID" />'
								break;
							default:
								i18n = 'N/A'
						} 
						
						return i18n;
					}
				},
				{
					"targets" : 7,
					"data" : 'id',
					"render" : function(data, type, row) {
						return '<a href="#">'
								+'<span class="label label-warning" style="margin-right:10px" custId="' + row['id'] + '" onclick="btnEditClicked($(this));">'
								+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message javaScriptEscape="true" code="edit"/></span></a>'
                                +'&nbsp;'
                                +'<a href="#">'
                                +'<span class="label label-info pull-center" style="margin-right:10px" custId="' + row['id'] + '" custName="' + row['name'] + '" onclick="btnFileClicked($(this));">'
                                +'<i class="fa fa-close" style="margin-right:5px"></i><spring:message javaScriptEscape="true" code="file"/></span></a>'
                        		+'&nbsp;'
                        		+'<a href="#" data-toggle="modal" data-target="#modal_deleteCust">'
                        		+'<span class="label label-danger pull-center" style="margin-right:10px" custId="' + row['id'] + '" custName="' + row['name'] + '" onclick="btnDeleteCustClicked($(this));">'
                        		+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message javaScriptEscape="true" code="delete"/></span></a>';
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
                            +'<i class="fa fa-close" style="margin-right:5px"></i><spring:message code="download"/></span></a>';
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

function doQuery() {
	_keyword  = $('#keyword').val();
	if (tblMain) {
		$('#tblMain').DataTable().destroy();
	}
	tblMain = $('#tblMain').DataTable(
			{
				"bFilter" : false,
				"ordering" : false,
				"info" : false,
				"serverSide" : true,
				"bLengthChange" : false,
				"ajax" : {
					"url" : '${pageContext.request.contextPath}/admin/cust/searchCust4Admin.json',
					"type" : 'GET',
					"data" : function(d) {
						//d.customParam = 'testestert';
						d.keyword = _keyword;
					}
				},
				"columns" : [
					{ "data" : "name" },
					{ "data" : "email" },
					{ "data" : "census" },
					{ "data" : "identity1_id" },
					{ "data" : "identity1_code" },
					{ "data" : "identity1_name" },
					{ "data" : "user.name" }
				],
				"columnDefs" : [ 
					{
						"targets" : 3,
						"render" : function(data, type, row) {
							var i18n;

							switch(data) {
								case 1:
									i18n = '<spring:message code="ID card" />'
									break;
								case 2:
									i18n = '<spring:message code="passport" />'
									break;
								case 3:
									i18n = '<spring:message code="ROCID" />'
									break;
								default:
									i18n = 'N/A'
							} 
							
							return i18n;
						}
					},
					{
						"targets" : 7,
						"data" : 'id',
						"render" : function(data, type, row) {
							return '<a href="#">'
									+'<span class="label label-warning" style="margin-right:10px" custId="' + row['id'] + '" onclick="btnEditClicked($(this));">'
									+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message code="edit"/></span></a>';
						}
					}
				],
				select: true
			});
}

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
        errMsg += '<spring:message javaScriptEscape="true" code="error.mustName"/><br/>';
    }
    if (''==uploadFile.trim()) {
        isError = true;
        $('#uploadFile').parents('.form-group').addClass('has-error');
        errMsg += '<spring:message javaScriptEscape="true" code="error.mustSelectFile"/><br/>';
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
        alert("<spring:message code='error.noSelectFile'/>");

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

//[Delete Cust] 按下Delete Cust按鈕
function btnDeleteCustClicked(btn) {
    formAction = "deleteCustAjax";
    $('#clickedCustId').val(btn.attr('custId'));
}

//[Confirm delete Cust] 按下确定删除客户按鈕
function btnDeleteCustConfirm() {
    formAction = "deleteCustAjax";

    $.ajax({
        url : '${pageContext.request.contextPath}/admin/cust/' + formAction,
        data : {
            custId: $('#clickedCustId').val()
        },
        type : "POST",
        dataType : 'json',
        async: false,
        success : function(resp) {
            console.log(resp);

            if (resp.code == '200' && tblMain) {
                $('#clickedCustId').val = "-1";
                successMsgModal(resp.message);

                if (tblMain.rows().count() > 1 || tblMain.page() < 1) {
                    tblMain.draw(false);
                } else {
                    tblMain.page('previous').draw('page');
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