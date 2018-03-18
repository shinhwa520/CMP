<%@ include file="../../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">

	<div class="row page-titles">
	     <div class="col-md-6 col-8 align-self-center">
	         <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="myClients" /></h3>
	     </div>
	</div>  
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<div>
						<a href="#" onclick="btnAddClicked();"><span class="label label-success pull-right" style="padding:5px 10px 5px 10px; font-size: 95%;"><spring:message code="add"/></span></a>
					</div>
					<table id="tblMain" class="table table-bordered table-striped">
						<thead>
							<tr>
			                    <th><b><spring:message code="name"/></b></th>
								<%--  註解
								<th>Gender</th>
								<th>Birthday</th>
								--%>
			                    <th><b><spring:message code="phoneNo"/></b></th>
			                    <th><b><spring:message code="email"/></b></th>
			                    <th><b><spring:message code="wechatID"/></b></th>
								<%--  註解
								<th>City</th>
								<th>Address</th>
								--%>
			                    <th><b><spring:message code="status"/></b></th>
								<th style="width: 255px;"></th>
			                    <th style="width: 50px;"><b><spring:message code="option"/></b></th>
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
<div class="modal fade bs-example-modal-lg" id="modal_Edit" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title"><spring:message code="edit"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="cust_id" id="cust_id" value="" />
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="cust_name"><spring:message code='name'/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="cust_name" name="cust_name" placeholder="<spring:message code='name'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
						<label><spring:message code='gender'/></label>
						<span style="position: relative; ">					 <input type="radio" name="gender" id="male" value="M"  /><spring:message code='male'/></span>
						<span style="position: relative; margin-left: 10px;"><input type="radio" name="gender" id="female" value="F"/><spring:message code='female'/></span>
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="birthday"><spring:message code='birthday'/></label>
	                  <input type="text" class="form-control" id="birthday" name="birthday" placeholder="<spring:message code='birthday'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="phone"><spring:message code='phoneNo'/></label>
	                  <input type="text" class="form-control" id="phone" name="phone" placeholder="<spring:message code='phoneNo'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="email"><spring:message code='email'/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="email" name="email" placeholder="<spring:message code='email'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="weChat"><spring:message code='wechatID'/></label>
	                  <input type="text" class="form-control" id="weChat" name="weChat" placeholder="<spring:message code='wechatID'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity1_id"><spring:message code="IDType"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <select id="identity1_id" name="identity1_id" class="form-control selectpicker m-b-5" data-style="btn-default">
	                  	<option value="0">=== <spring:message code="pleaseChoose"/> ===</option>
						<option value="1"><spring:message code="ID card"/></option>
						<option value="2"><spring:message code="passport"/></option>
						<option value="3"><spring:message code="ROCID"/></option>
	                  </select>
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                	<label for="identity1_code" ><spring:message code="IDNumber"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  	<input type="text" class="form-control" id="identity1_code" name="identity1_code" placeholder="<spring:message code='IDNumber'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                	<label for="identity1_name" ><spring:message code="IDName"/><span class="pull-right" style="color: red;">＊ </span></label>
	                 	<input type="text" class="form-control" id="identity1_name" name="identity1_name" placeholder="<spring:message code='IDName'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity2_id"><spring:message code="secondIDType"/></label>
	                  <select id="identity2_id" name="identity2_id" class="form-control selectpicker m-b-5" data-style="btn-default">
	                  	<option value="0">=== <spring:message code="pleaseChoose"/> ===</option>
						<option value="1"><spring:message code="ID card"/></option>
						<option value="2"><spring:message code="passport"/></option>
						<option value="3"><spring:message code="ROCID"/></option>
	                  </select>
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                	<label for="identity2_code" ><spring:message code="secondIDNo"/></label>
	                  <input type="text" class="form-control" id="identity2_code" name="identity2_code" placeholder="<spring:message code='secondIDNo'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                	<label for="identity2_name" ><spring:message code="secondIDName"/></label>
	                  <input type="text" class="form-control" id="identity2_name" name="identity2_name" placeholder="<spring:message code='secondIDName'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
                        <label for="city"><spring:message code='city'/></label>
                        <input type="text" class="form-control" id="city" name="city" placeholder="<spring:message code='city'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="census"><spring:message code="residentialAddress"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="census" name="census" placeholder="<spring:message code="residentialAddress"/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="address"><spring:message code='address'/></label>
	                  <input type="text" class="form-control" id="address" name="address" placeholder="<spring:message code='address'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="remark"><spring:message code='remarks'/></label>
	                  <input type="text" class="form-control" id="remark" name="remark" placeholder="<spring:message code='remarks'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="status"><spring:message code='status'/></label>
	                  <input type="text" class="form-control" id="status" name="status" readonly="true" >
	                </div>
	            </div>
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='close'/></button>
	        		<button type="button" class="btn btn-primary" id="btnProfileSave" onclick="btnSaveClicked();"><spring:message code='save'/></button>
				</div>
			</form>
		</div>
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit -->

<!--.燈箱 Edit_Demo -->
<div class="modal fade bs-example-modal-lg" id="modal_Edit_Demo" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title"><spring:message code="edit"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">
            <form role="form" id="formEdit_Demo" name="formEdit_Demo">
            	<input type="hidden" name="cust_id_Demo" id="cust_id_Demo" value="" />
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="cust_name"><spring:message code='name'/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="cust_name_Demo" name="cust_name_Demo" placeholder="<spring:message code='name'/>" value="Demo">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
						<label><spring:message code='gender'/></label>
						<span style="position: relative; ">					 <input type="radio" name="gender_Demo" id="male_Demo" value="M" checked="checked" /><spring:message code='male'/></span>
						<span style="position: relative; margin-left: 10px;"><input type="radio" name="gender_Demo" id="female_Demo" value="F"/><spring:message code='female'/></span>
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="birthday"><spring:message code='birthday'/></label>
	                  <input type="text" class="form-control" id="birthday_Demo" name="birthday_Demo" placeholder="<spring:message code='birthday'/>" value="2000-01-01">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="phone"><spring:message code='phoneNo'/></label>
	                  <input type="text" class="form-control" id="phone_Demo" name="phone_Demo" placeholder="<spring:message code='phoneNo'/>" value="3345678">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="email"><spring:message code='email'/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="email_Demo" name="email_Demo" placeholder="<spring:message code='email'/>" value="Demo@cmp.com">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="weChat"><spring:message code='wechatID'/></label>
	                  <input type="text" class="form-control" id="weChat_Demo" name="weChat_Demo" placeholder="<spring:message code='wechatID'/>" value="Demo666">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity1_id"><spring:message code="IDType"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <select id="identity1_id_Demo" name="identity1_id_Demo" class="form-control selectpicker m-b-5" data-style="btn-default">
	                  	<option value="0">=== <spring:message code="pleaseChoose"/> ===</option>
						<option value="1" selected="selected"><spring:message code="ID card"/></option>
						<option value="2"><spring:message code="passport"/></option>
						<option value="3"><spring:message code="ROCID"/></option>
	                  </select>
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity1_code" ><spring:message code="IDNumber"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="identity1_code_Demo" name="identity1_code_Demo" placeholder="<spring:message code='IDNumber'/>" value="Demo_IdNumber">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity1_name" ><spring:message code="IDName"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="identity1_name_Demo" name="identity1_name_Demo" placeholder="<spring:message code='IDName'/>" value="Demo_RealName">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity2_id"><spring:message code="secondIDType"/></label>
	                  <select id="identity2_id_Demo" name="identity2_id_Demo" class="form-control selectpicker m-b-5" data-style="btn-default">
	                  	<option value="0">=== <spring:message code="pleaseChoose"/> ===</option>
						<option value="1"><spring:message code="ID card"/></option>
						<option value="2"><spring:message code="passport"/></option>
						<option value="3"><spring:message code="ROCID"/></option>
	                  </select>
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity2_code" ><spring:message code="secondIDNo"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="identity2_code_Demo" name="identity2_code_Demo" placeholder="<spring:message code='secondIDNo'/>" value="Demo_IdNumber">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="identity2_name" ><spring:message code="secondIDName"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="identity2_name_Demo" name="identity2_name_Demo" placeholder="<spring:message code='secondIDName'/>" value="Demo_RealName">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
                        <label for="city"><spring:message code='city'/></label>
                        <input type="text" class="form-control" id="city_Demo" name="city_Demo" placeholder="<spring:message code='city'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="census"><spring:message code="residentialAddress"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="census_Demo" name="census_Demo" placeholder="<spring:message code="residentialAddress"/>" value="上海">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="address"><spring:message code='address'/></label>
	                  <input type="text" class="form-control" id="address_Demo" name="address_Demo" placeholder="<spring:message code='address'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="remark"><spring:message code='remarks'/></label>
	                  <input type="text" class="form-control" id="remark_Demo" name="remark_Demo" placeholder="<spring:message code='remarks'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="status"><spring:message code='status'/></label>
	                  <input type="text" class="form-control" id="status_Demo" name="status_Demo" readonly="true" >
	                </div>
	            </div>
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='close'/></button>
	        		<button type="button" class="btn btn-primary" id="btnProfileSave_Demo" ><spring:message code='save'/></button>
				</div>
			</form>
		</div>
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit_Demo -->

<!--.燈箱 File -->
<div class="modal fade bs-example-modal-lg" id="modal_File" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title"><spring:message code="file"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">
            <form role="form" id="formFile" name="formFile">
				<input type="hidden" name="fileType" id="queryFileType" value="" />
				<div class="box box-primary">
					<div class="box-header with-border">
						<b><font style="font-size: 1.5em;"><spring:message code='custFile'/>&nbsp;&nbsp;>&nbsp;&nbsp;<span id="custNameLabel"></span></font></b>
						<a href="#" onclick="btnAddFileClicked();"><span class="label label-success pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"><spring:message code='add'/></span></a>
						<span class="pull-right">&nbsp;</span>
						<a href="#" onclick="btnDeleteClicked();"><span class="label label-danger pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"><spring:message code='delete'/></span></a>
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
									<th style="width: 30px;"><spring:message code="option"/></th>
									<th style="width: 60px;"><input type="checkbox" id="delChkAll" /> <spring:message code="selectAll"/></th>
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

<!--.燈箱 File_Demo -->
<div class="modal fade bs-example-modal-lg" id="modal_File_Demo" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title"><spring:message code="file"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">
            <form role="form" id="formFile_Demo" name="formFile_Demo">
				<input type="hidden" name="fileType_Demo" id="queryFileType_Demo" value="" />
				<div class="box box-primary">
					<div class="box-header with-border">
						<b><font style="font-size: 1.5em;"><spring:message code='custFile'/>&nbsp;&nbsp;>&nbsp;&nbsp;<span id="custNameLabel_Demo">Demo</span></font></b>
						<a href="#" ><span class="label label-success pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code='add'/></span></a>
						<span class="pull-right">&nbsp;</span>
						<a href="#" ><span class="label label-info pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code='delete'/></span></a>
					</div>
					<div class="box-body no-padding">
						<table class="table table-striped" id="custFileMain_Demo" width="100%">
							<thead>
								<tr>
									<th>#</th>
									<th><spring:message code="fileName"/></th>
									<th><spring:message code="fileSize"/></th>
									<th><spring:message code="downloadTimes"/></th>
									<th><spring:message code="fileDescription"/></th>
									<th><spring:message code="updatedTime"/></th>
									<th style="width: 30px;"><spring:message code="option"/></th>
									<th style="width: 60px;"><input type="checkbox" id="delChkAll_Demo" /> <spring:message code="selectAll"/></th>
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
<!--/.燈箱 File_Demo -->

<!--.燈箱 Upload -->
<div class="modal fade bs-example-modal-lg" id="modal_Upload" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
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

	$('#identity1_id').val('0');
	$('#identity1_code').val('');
	$('#identity1_name').val('');
	$('#identity2_id').val('0');
	$('#identity2_code').val('');
	$('#identity2_name').val('');

	$('#city').val('');
	$('#census').val('');
	$('#address').val('');
	$('#remark').val('');
	$('#status').val('');

	$('#modal_Edit').modal();
}

//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	$('.form-group').removeClass('has-error');
	var _custId = btn.attr('custId');
	if("_demoId"==_custId) {
		$('#modal_Edit_Demo').modal();
		return false;
	}
	
	$.ajax({
			url : '${pageContext.request.contextPath}/channel/cust/getCustById/' + _custId,
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
					$('#status').val(resp.data.cust.status.name);
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
	var email = $('#email').val();
	var identity1_id = $('#identity1_id').val();
	var identity1_code = $('#identity1_code').val();
	var identity1_name = $('#identity1_name').val();
	var census = $('#census').val();
	
	//頁面輸入檢核
	$('.form-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	if (''==cust_name.trim()) {
		isError = true;
		$('#cust_name').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message code="error.mustName"/><br/>';
	}
	if (''==email.trim()) {
		isError = true;
		$('#email').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message code="error.mustEmail"/><br/>';
	}
	if (''==identity1_id.trim()) {
		isError = true;
		$('#identity1_id').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message code="error.mustIdentity1"/><br/>';
	}
	if (''==identity1_code.trim()) {
		isError = true;
		$('#identity1_code').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message code="error.mustIdentity1_code"/><br/>';
	}
	if (''==identity1_name.trim()) {
		isError = true;
		$('#identity1_name').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message code="error.mustIdentity1_name"/><br/>';
	}
	if (''==census.trim()) {
		isError = true;
		$('#census').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message code="error.mustCensus"/><br/>';
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
	var _custId = btn.attr('custId');
	if("_demoId"==_custId) {
		$('#modal_File_Demo').modal();
		return false;
	}
	$('#clickedCustId').val(_custId);
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
		"initComplete": function(settings, json) {
			console.log(json.recordsTotal);
			if(json.recordsTotal<1){
				tblMain.row.add({
					"name":"Demo",
					"phone":"3345678",
					"email":"Demo@cmp.com",
					"weChat":"Demo666",
					"status":{"sort":"3"},
					"id":"_demoId"
			    }).draw();
			}
		},
		"columns" : [
			{ "data" : "name" },
			{ "data" : "phone" },
			{ "data" : "email" },
			{ "data" : "weChat" },
			{ "data" : "status.sort" },
			{ "data" : "status.sort" }
		],
		"columnDefs" : [
			{
				"targets" : [4],
				"render" : function(data, type, row) {
							var i18n;

							switch(data) {
							    case 1:
							    	i18n = '<spring:message code="registered" />'
							        break;
							    case 2:
							    	i18n = '<spring:message code="bookingFeePaid" />'
							        break;
							    case 3:
							    	i18n = '<spring:message code="bookedAirTicket" />'
							        break;
							    case 4:
							    	i18n = '<spring:message code="visaIssued" />'
							        break;
							    case 5:
							    	i18n = '<spring:message code="bookedHotel" />'
							        break;
							    case 6:
							    	i18n = '<spring:message code="visited" />'
							        break;
							    case 7:
							    	i18n = '<spring:message code="contractsSigned" />'
							        break;
							    case 8:
							    	i18n = '<spring:message code="depositPaid" />'
							        break;
							    case 9:
							    	i18n = '<spring:message code="downPayment" />'
							        break;
							    case 10:
							    	i18n = '<spring:message code="loanApplication" />'
							        break;
							    case 11:
							    	i18n = '<spring:message code="progressPayments" />'
							        break;
							    case 12:
							    	i18n = '<spring:message code="settlement" />'
							        break;
							    default:
							    	i18n = 'N/A'
							} 
							
							return i18n;
						}
			},
			{
				"targets" : [5],
				"render" : function(data, type, row) {
							var sort = row['status'].sort;
							var html = '<div class="progress" style="width: 250px;">';
							html += '<div class="progress-bar bg-info active progress-bar-striped" role="progressbar" style="width: '+Math.round((sort/12)*100)+'%;height:15px;"></div>';
							html+='</div>';
							
							/*
							var html = '<div class="progress">';
							for(var i=0; i<12; i++){
								if(i<sort)
									html += '<li class="active"><a></a></li>';
								else
									html += '<li><a></a></li>';
							}
							html+='</div>';
							*/
							return html;
						}
			},
			{
				"targets" : 6,
				"data" : 'id',
				"render" : function(data, type, row) {
					return '<a href="#">'
							+'<span class="ti-pencil" style="margin-right:10px" custId="' + row['id'] + '" onclick="btnEditClicked($(this));" title="<spring:message javaScriptEscape="true" code="edit"/>"></span></a>'
							+'&nbsp;'
							+'<a href="#">'
							+'<span class="icon-folder-alt" style="margin-right:10px" custId="' + row['id'] + '" custName="' + row['name'] + '" onclick="btnFileClicked($(this));" title="<spring:message javaScriptEscape="true" code="file"/>"></span></a>';
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
							+'<span class="icon-cloud-download" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnDownloadClicked($(this));" title="<spring:message code="download"/>"></span></a>';
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

<style>
.modal-body form .box-body .form-group label {
    width: 12%;
}

.modal-body form .box-body .form-group .form-control{
	display: inline-block;
	width: 85%;
}
</style>
