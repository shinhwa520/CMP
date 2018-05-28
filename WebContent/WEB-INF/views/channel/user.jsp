<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>

<section class="content">

	<div class="row page-titles">
	     <div class="col-md-6 col-8 align-self-center">
	         <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="myChannels" /></h3>
	     </div>
	 </div>
	 
	<div class="row">
		<div class="col-12">
			<div class="card">
				 <div class="card-body">
					<div class="table-responsive m-t-0">
						<table id="tblMain" class="table table-bordered table-striped">
							<colgroup id="guide_step1_tag">
						        <col>
						        <col>
						        <col>
						        <col>
						    </colgroup>
						    <colgroup id="guide_step2_tag">
						        <col>
						        <col>
						    </colgroup>
						    <colgroup id="guide_step3_tag">
						        <col>
						        <col>
						    </colgroup>
						    <colgroup id="guide_step4_tag">
						        <col>
						        <col>
						    </colgroup>
							<thead>
								<tr>
				                    <th rowspan="2"><b><spring:message code="name"/></b></th>
				                    <th rowspan="2"><b><spring:message code="phoneNo"/></b></th>
				                    <th rowspan="2"><b><spring:message code="email"/></b></th>
									<th rowspan="2"><b><spring:message code="wechatID"/></b></th>
				                    <th colspan="2"><b><spring:message code="channelsNo"/></b></th>
				                    <th colspan="2"><b><spring:message code="tourNo"/></b></th>
				                    <th colspan="2"><b><spring:message code="salesNo"/></b></th>
									<th rowspan="2" style="width: 70px;"><b><spring:message code="option"/></b></th>
								</tr>
								<tr>
									<th><b><spring:message code="target"/></b></th>
									<th><b><spring:message code="accomplished"/></b></th>
									<th><b><spring:message code="target"/></b></th>
									<th><b><spring:message code="accomplished"/></b></th>
									<th><b><spring:message code="target"/></b></th>
									<th><b><spring:message code="accomplished"/></b></th>
								</tr>
							</thead>
						</table>
					</div>
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
            	<input type="hidden" name="user_id" id="user_id" value="" />
		        <div class="box-body">
		        	<div class="form-group">
						<label for="user_name"><spring:message code="name"/></label>
						<input type="text" readonly class="form-control" name="user_name" id="user_name" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="remark"><spring:message code="remarks"/></label>
						<input type="text" class="form-control" name="remark" id="remark" />
		            </div>
		        </div>
		        <%--  註解
		        <div class="box-body">
		        	<div class="form-group">
						<label for="reward"><spring:message code="reward"/></label>
						<input type="text" class="form-control" name="reward" id="reward" />%
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
						<label for="email"><spring:message code="email"/></label>
						<input type="text" readonly="true" class="form-control" name="email" id="email" />
		            </div>                              
		        </div>
		        <div class="box-body">
		        	<div class="form-group">
						<label for="status"><spring:message code="status"/></label>
						<input type="text" readonly="true" class="form-control" name="statusName" id="statusName" />
		            </div>                              
		        </div>
		        --%>
		        <div class="box-body">
		        	<table style="width: 100%">
		        		<tr>
		        			<td style="width: 18%"><label><spring:message code="targetChannelsNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" class="form-control" name="agent_user" id="agent_user" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 18%"><label><spring:message code="accomplishedChannelsNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" readonly="true" class="form-control" name="_agent_user" id="_agent_user" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        		<tr>
		        			<td style="width: 18%"><label><spring:message code="targetTourNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" class="form-control" name="agent_cust" id="agent_cust" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 18%"><label><spring:message code="accomplishedTourNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" readonly="true" class="form-control" name="_agent_cust" id="_agent_cust" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        		<tr>
		        			<td style="width: 18%"><label><spring:message code="targetSalesNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" class="form-control" name="volume" id="volume" style="width: 80%; text-align:right;"/></td>
		        			<td style="width: 18%"><label><spring:message code="accomplishedSalesNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" readonly="true" class="form-control" name="_volume" id="_volume" style="width: 80%; text-align:right;"/></td>
		        		</tr>
		        	</table>                             
		        </div>
	            
				<div class="modal-footer">
	        		<button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal"><spring:message code="close"/></button>
	        		<button type="button" class="btn btn-success waves-effect text-left" id="btnKpiSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit -->

<!--.燈箱 Edit_Demo -->         
<div class="modal fade bs-example-modal-lg" id="modal_Edit_Demo" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg" id="guide_step6_tag">
    <div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title"><spring:message code="edit"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="formEdit_Demo" name="formEdit_Demo">
            	<input type="hidden" name="user_id_Demo" id="user_id_Demo" value="" />
		        <div class="box-body">
		        	<div class="form-group" id="guide_step7_tag">
						<label for="user_name"><spring:message code="name"/></label>
						<input type="text" readonly class="form-control" name="user_name_Demo" id="user_name_Demo" value="Demo" />
		            </div>                              
		        </div>
		        <div class="box-body" id="guide_step8_tag">
		        	<div class="form-group">
						<label for="remark"><spring:message code="remarks"/></label>
						<input type="text" class="form-control" name="remark_Demo" id="remark_Demo" value="Demo_Remark"/>
		            </div>
		        </div>
		        <div class="box-body">
		        	<table style="width: 100%">
		        		<tr id="guide_step9_tag">
		        			<td style="width: 18%"><label><spring:message code="targetChannelsNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" class="form-control" name="agent_user_Demo" id="agent_user_Demo" style="width: 80%; text-align:right;" value="0" /></td>
		        			<td style="width: 18%"><label><spring:message code="accomplishedChannelsNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" readonly class="form-control" name="_agent_user_Demo" id="_agent_user_Demo" style="width: 80%; text-align:right;" value="0" /></td>
		        		</tr>
		        		<tr id="guide_step10_tag">
		        			<td style="width: 18%"><label><spring:message code="targetTourNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" class="form-control" name="agent_cust_Demo" id="agent_cust_Demo" style="width: 80%; text-align:right;" value="0" /></td>
		        			<td style="width: 18%"><label><spring:message code="accomplishedTourNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" readonly class="form-control" name="_agent_cust_Demo" id="_agent_cust_Demo" style="width: 80%; text-align:right;" value="0" /></td>
		        		</tr>
		        		<tr id="guide_step11_tag">
		        			<td style="width: 18%"><label><spring:message code="targetSalesNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" class="form-control" name="volume_Demo" id="volume_Demo" style="width: 80%; text-align:right;" value="0" /></td>
		        			<td style="width: 18%"><label><spring:message code="accomplishedSalesNo"/></label></td>
		        			<td style="width: 32%" class="form-group"><input type="text" readonly class="form-control" name="_volume_Demo" id="_volume_Demo" style="width: 80%; text-align:right;" value="0" /></td>
		        		</tr>
		        	</table>                             
		        </div>
	            
				<div class="modal-footer">
	        		<button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal"><spring:message code="close"/></button>
	        		<button type="button" class="btn btn-success waves-effect text-left" id="btnProfileSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit_Demo -->

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
			"initComplete": function(settings, json) {
				//alert('..');
				if(json.recordsTotal<1){
					tblMain.row.add({
						"name":"Demo",
						"phone":"3345678",
						"email":"Demo@cmp.com",
						"weChat":"Demo666",
						"agent_user":"0",
						"_agent_user":"0",
						"agent_cust":"0",
						"_agent_cust":"0",
						"volume":"0",
						"_volume":"0",
						"id":"_demoId"
				    }).draw();
				};
				
				showGuideTour();
			},
			"columns" : [
				{ "data" : "name" },
				{ "data" : "phone" },
				{ "data" : "email" },
				{ "data" : "weChat" },
				{ "data" : "agent_user" },
				{ "data" : "_agent_user" },
				{ "data" : "agent_cust" },
				{ "data" : "_agent_cust" },
				{ "data" : "volume" },
				{ "data" : "_volume" }
			],
			"columnDefs" : [
				{
				"targets" : 10,
				"data" : 'id',
				"render" : function(data, type, row) {
					return '<div id="guide_step5_tag" style="float:left;width:20px">'
							+'<a href="#">'
							+'<span class="ti-pencil" style="margin-right:10px" userId="' + row['id'] + '" onclick="btnEditClicked($(this));" title="<spring:message code="edit"/>"></span></a>'
							+'</div>'
							+'<div style="float:left;width:5px">&nbsp;</div>'
							+'<div id="guide_step12_tag" style="float:left;width:20px">'
							+'<a href="#">'
							+'<span class="ti-money" style="margin-right:10px" userId="' + row['id'] + '" onclick="btnCommissionClicked($(this));" title="<spring:message code="reward"/>"></span></a>'
							+'</div>';
				}
			} ],
			select: true
		});
	
		// Order by the grouping
	    $('#tblMain tbody').on('click', 'tr.group', function() {
	        var currentOrder = table.order()[0];
	        if (currentOrder[0] === 2 && currentOrder[1] === 'asc') {
	            table.order([2, 'desc']).draw();
	        } else {
	            table.order([2, 'asc']).draw();
	        }
	    });
	});

//[Commission]
function btnCommissionClicked(btn) {
	var _userId = btn.attr('userId');
	window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/channel/user/getCommissionByUserId/' + _userId;
}



//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	$('.form-group').removeClass('has-error');
	var _userId = btn.attr('userId');
	if("_demoId"==_userId) {
		$('#modal_Edit_Demo').modal();
		
		setTimeout(function(){
			showGuideTour2();
		}, 500);
		
		return false;
	}
	
	$.ajax({
			url : '${pageContext.request.contextPath}/channel/user/getUserById/' + _userId,
			data : '',
			type : "GET",
			dataType : 'json',
			async: false,

			success : function(resp) {
				console.log(resp);				
				if (resp.code == '200') {
					$('#user_id').val(_userId);
					$('#user_name').val(resp.data.user.name);
					$('#remark').val(resp.data.user.remark);
					/*
					$('#reward').val(resp.data.user.reward);
					$('#phone').val(resp.data.user.phone);
					$('#email').val(resp.data.user.email);
					$('#statusName').val(resp.data.user.status.name);
					*/
					$('#agent_user').val(resp.data.user.agent_user);
					$('#_agent_user').val(resp.data.user._agent_user);
					$('#agent_cust').val(resp.data.user.agent_cust);
					$('#_agent_cust').val(resp.data.user._agent_cust);
					$('#volume').val(resp.data.user.volume);
					$('#_volume').val(resp.data.user._volume);
					
					$('#modal_Edit').modal();
					//successMsgModal(resp.message);
				} else {
					alert(resp.message);
				}
			},
			
			"initComplete": function(settings, json) {
				showGuideTour2();
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
}

//[Save] modal_Edit >>按下Save 儲存
function btnSaveClicked() {
	//var reward = $('#reward').val();
	var agent_user = $('#agent_user').val();
	var agent_cust = $('#agent_cust').val();
	var volume = $('#volume').val();
	//頁面輸入檢核
	$('.form-group').removeClass('has-error');
	var isError = false;
	var errMsg = '';
	/*
	if (!validateInt(reward)) {
		isError = true;
		$('#reward').parents('.form-group').addClass('has-error');
		errMsg += '！Reward必須為數字<br/>';
	}
	*/
	if (!validateInt(agent_user)) {
		isError = true;
		$('#agent_user').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.channelMustNo"/><br/>';
	}
	if (!validateInt(agent_cust)) {
		isError = true;
		$('#agent_cust').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.tourMustNo"/><br/>';
	}
	if (!validateInt(volume)) {
		isError = true;
		$('#volume').parents('.form-group').addClass('has-error');
		errMsg += '<spring:message javaScriptEscape="true" code="error.salesMustNo"/><br/>';
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
<style>
.modal-body form .box-body .form-group label {
    width: 12%;
}

.modal-body form .box-body .form-group .form-control{
	display: inline-block;
	width: 85%;
}
</style>

	<script>
	    var showGuide = '${showGuide}';
	    var isHome = '${active}';
	    var title_user = '<spring:message code="guide_tour_msg_8"/>'; //『我的渠道』导览说明
	    //$(document).ready(function(){
	    function showGuideTour() {
	      if("Y"===showGuide) {
			//showTip();
	    	// Instance the tour
			var tour = new Tour({
				backdrop: true,
				smartPlacement: true,
				storage: false,
				template: 
					function (key, value) { 
						var reString = "<div class='popover tour' style='min-width:500px;  max-width:555px;'><div class='arrow'></div><h3 class='popover-title'></h3><div class='popover-content'></div>";
							reString += "<div class='popover-navigation'><button class='btn btn-default' data-role='prev'>« <spring:message code='prev' /></button><span data-role='separator'>|</span><button class='btn btn-default' data-role='next'><spring:message code='next' /> »</button>";
							reString += "<button class='btn float-right' onclick='doCloseGuide();' aria-hidden='true'><i class='fa fa-ban'></i> <spring:message code='noShowAgain' /></button><button class='btn btn-default float-right' data-role='end'><spring:message code='endTour' /></button></div></div>";
							
						return reString;
					}
				,
				steps: [
	    	  		{
	    	    		element: "#guide_step1_tag",
	    	    		title: title_user + " - 1/13",
	    	    		content: '<spring:message code="guide_tour_msg_10"/>', //我的下层渠道商基本个人资料
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step2_tag",
	    	    		title: title_user + " - 2/13",
	    	    		content: '<spring:message code="guide_tour_msg_11"/>', //检视下层渠道商个人「KPI-渠道商量」达成状况
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step3_tag",
	    	    		title: title_user + " - 3/13",
	    	    		content: '<spring:message code="guide_tour_msg_12"/>', //检视下层渠道商个人「KPI-成团量」达成状况
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step4_tag",
	    	    		title: title_user + " - 4/13",
	    	    		content: '<spring:message code="guide_tour_msg_13"/>', //检视下层渠道商个人「KPI-成交量」达成状况
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step5_tag",
	    	    		title: title_user + " - 5/13",
	    	    		content: '<spring:message code="guide_tour_msg_14"/>', //点击铅笔图示进入渠道商基本资料编辑画面
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onHidden: function() {
	    	    			$('.ti-pencil').click();
	    	    			return (new jQuery.Deferred()).promise();
	    	    	    },
	    	    		onShown: function() {
	    	    			$('.btn[data-role="next"]').removeClass("disabled");
	    	    			$('.btn[data-role="next"]').prop("disabled", false);
	    	    			$('.btn[data-role="next"]').on("click", function (e) {
	    	                    tour.end();
	    	                });
	    	    		}
	    	  		}
	    		]});
	
	    	// Initialize the tour
	    	tour.init();
	
	    	// Start the tour
	    	tour.start();
	    	//tour.restart();
	      }
	   // });
	    }
	    
	    function showGuideTour2() {
		      if("Y"===showGuide) {
				//showTip();
		    	// Instance the tour
				var tour2 = new Tour({
					backdrop: true,
					smartPlacement: true,
					storage: false,
					template: 
						function (key, value) { 
							var reString = "<div class='popover tour' style='min-width:500px;  max-width:555px;'><div class='arrow'></div><h3 class='popover-title'></h3><div class='popover-content'></div>";
								reString += "<div class='popover-navigation'><button class='btn btn-default' data-role='prev'>« <spring:message code='prev' /></button><span data-role='separator'>|</span><button class='btn btn-default' data-role='next'><spring:message code='next' /> »</button>";
								reString += "<button class='btn float-right' onclick='doCloseGuide();' aria-hidden='true'><i class='fa fa-ban'></i> <spring:message code='noShowAgain' /></button><button class='btn btn-default float-right' data-role='end'><spring:message code='endTour' /></button></div></div>";
								
							return reString;
						}
					,
					steps: [
		    	  		{
		    	    		element: "#guide_step6_tag",
		    	    		title: title_user + " - 6/13",
		    	    		content: '<spring:message code="guide_tour_msg_15"/>', //渠道商基本资料编辑画面
		    	    		placement: "bottom",
		    	    		animation: false
		    	  		},
		    	  		{
		    	    		element: "#guide_step7_tag",
		    	    		title: title_user + " - 7/13",
		    	    		content: '<spring:message code="guide_tour_msg_16"/>', //渠道商姓名（不开放修改）
		    	    		placement: "bottom",
		    	    		animation: false
		    	  		},
		    	  		{
		    	    		element: "#guide_step8_tag",
		    	    		title: title_user + " - 8/13",
		    	    		content: '<spring:message code="guide_tour_msg_17"/>', //渠道商备注
		    	    		placement: "bottom",
		    	    		animation: false
		    	  		},
		    	  		{
		    	    		element: "#guide_step9_tag",
		    	    		title: title_user + " - 9/13",
		    	    		content: '<spring:message code="guide_tour_msg_18"/>', //订定或调整渠道商本月「KPI-渠道商量」目标值;右半部为当前达成状况
		    	    		placement: "bottom",
		    	    		animation: false
		    	  		},
		    	  		{
		    	    		element: "#guide_step10_tag",
		    	    		title: title_user + " - 10/13",
		    	    		content: '<spring:message code="guide_tour_msg_19"/>', //订定或调整渠道商本月「KPI-成团量」目标值;右半部为当前达成状况
		    	    		placement: "bottom",
		    	    		animation: false
		    	    			
		    	  		},
		    	  		{
		    	    		element: "#guide_step11_tag",
		    	    		title: title_user + " - 11/13",
		    	    		content: '<spring:message code="guide_tour_msg_20"/>', //订定或调整渠道商本月「KPI-成交量」目标值;右半部为当前达成状况
		    	    		placement: "bottom",
		    	    		animation: false
		    	  		},
		    	  		{
		    	    		element: "#btnProfileSave",
		    	    		title: title_user + " - 12/13",
		    	    		content: '<spring:message code="guide_tour_msg_21"/>', //点击「保存」按钮储存修改值
		    	    		placement: "bottom",
		    	    		animation: false,
		    	    		onNext: function() {
		    	    			$('.btn-danger').click();
		    	    	    }
		    	  		},
		    	  		{
		    	    		element: "#guide_step12_tag",
		    	    		title: title_user + " - 13/13",
		    	    		content: '<spring:message code="guide_tour_msg_22"/>', //点击钱符号图示进入渠道商产品佣金％数检视与调整画面
		    	    		placement: "bottom",
		    	    		animation: false,
		    	    		onHidden: function() {
		    	    			$('.ti-money').click();
		    	    			return (new jQuery.Deferred()).promise();
		    	    	    },
		    	    		onShown: function() {
		    	    			$('.btn[data-role="next"]').removeClass("disabled");
		    	    			$('.btn[data-role="next"]').prop("disabled", false);
		    	    			$('.btn[data-role="next"]').on("click", function (e) {
		    	                    tour.end();
		    	                });
		    	    		}
		    	  		}
		    		]});
		
		    	// Initialize the tour
		    	tour2.init();
		
		    	// Start the tour
		    	tour2.start();
		    	//tour.restart();
		      }
		   // });
		    }
	</script>