<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><spring:message code="allCust"/></h3>
		<form id="formSearch" name="formSearch">
			<input type="text" id="keyword" name="keyword" placeholder="Search Keyword">
			<a href="#" onclick="doQuery();"><span class="label label-info" style="padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code="search"/></span></a>
			<span><spring:message code="allCust.search.info"/></span>
		</form>
	</div>
	<div class="box-body no-padding">
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
				{ "data" : "identity1_id" },
				{ "data" : "identity1_code" },
				{ "data" : "identity1_name" },
				{ "data" : "identity2_id" },
				{ "data" : "identity2_code" },
				{ "data" : "identity2_name" },
				{ "data" : "user.name" }
			],
			"columnDefs" : [ 
				{
					"targets" : 5,
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
					"targets" : 8,
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
					"targets" : 12,
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
	});

$('#birthday').datepicker({
    dateFormat: 'yy-mm-dd',
	yearRange: "-100:+0",
	changeYear: true
});


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
									+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message code="edit"/></span></a>';
						}
					}
				],
				select: true
			});
}
</script>