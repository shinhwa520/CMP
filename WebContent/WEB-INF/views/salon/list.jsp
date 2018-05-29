<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/laydate/laydate.js"></script>

<section class="content">

	<div class="row page-titles">
	    <div class="col-md-6 col-8 align-self-center">
	        <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="salon" />&nbsp;
				<button type="button" id="guide_step1_tag" class="btn btn-warning" onclick="btnAddClicked();" style="width:30%"><spring:message code='add'/></button>
		   	</h3>
	    </div>
	 </div>
	 
	 <!-- ============================================================== -->
     <!-- Start Page Content -->
     <!-- ============================================================== -->
     <div class="row">
         <div class="col-12">
             <div class="card">
                 <div class="card-body">
                    <div class="salon-wrapper row pricing-plan">
                    	<c:if test="${!SalonForm.salonList.isEmpty() }">
                    		<c:forEach var="vo" items="${ SalonForm.salonList }" varStatus="loop">
                    			<div class="salon-item col-md-4 col-xs-6 col-sm-12 no-padding">
		                            <div class="pricing-box">
		                                <div class="pricing-body b-l b-r">
		                                	<div class="pricing-header">
		                                		<c:if test="${vo.canModify}">
		                                			<button class="btn btn-info waves-effect waves-light m-t-20" onClick="btnEditClicked(${vo.salonId })"><spring:message code="edit"/></button>
		                                		</c:if>
	                                            <c:if test="${vo.status ne 23}">
	                                            	<button class="btn waves-effect waves-light m-t-20" style="width:50%;" disabled><spring:message code="joinGo"/></button>
	                                            </c:if>
	                                            <c:if test="${vo.status eq 23}">
	                                            	<button class="btn btn-success waves-effect waves-light m-t-20 btn-add" salonId="${vo.salonId }" style="width:50%"><spring:message code="joinGo"/></button>
	                                            </c:if>
	                                            <c:if test="${vo.canModify}">
	                                            	<button class="btn btn-danger waves-effect waves-light m-t-20" onClick="btnDeleteClicked(${vo.salonId }, '${vo.salonName }')"><spring:message code="delete"/></button>
	                                        	</c:if>
	                                        </div>
		                                    <div class="pricing-header">
		                                        <h2 class="text-center"><b>${vo.salonName }</b></h2>
		                                        <p class="uppercase">${vo.description }&nbsp;</p>
		                                    </div>
		                                    <div class="price-table-content">
		                                    	<!-- 狀態 -->
		                                        <div class="price-row"> 
	                                        	  	<h3 class="text-center">
	                                        	  		<b>
			                                        		<c:if test="${vo.status eq 22}">
			                                        			<span style="color:#c77f0a"><i class="icon-lock"></i> <spring:message code="salonNotOpen"/></span>
			                                        		</c:if>
			                                        		<c:if test="${vo.status eq 23}">
			                                        			<span style="color:#24d12a"><i class="icon-user-follow"></i> <spring:message code="salonIng"/></span>
			                                        		</c:if>
			                                        		<c:if test="${vo.status eq 24}">
			                                        			<span style="color:#dc3545"><i class="icon-close"></i> <spring:message code="salonStop"/></span>
			                                        		</c:if>
			                                        		<c:if test="${vo.status eq 25}">
			                                        			<span style="color:#00f"><i class="icon-check"></i> <spring:message code="salonGo"/></span>
			                                        		</c:if>
	                                        			</b>
	                                        		</h3> 
		                                        </div>
		                                    	<!-- 截止時間 -->
		                                    	<div class="price-row">
		                                        	<p><i class="icon-hourglass"></i> <spring:message code='postTime'/>:</p>
		                                        	<h4 class="text-center"><span style="color:blue">${vo.beginDate } ~ ${vo.endDate }</span>&nbsp;</h4>
		                                        </div>
		                                        <!-- 人數限制 -->
		                                        <div class="price-row">
		                                        	<p class="uppercase"><i class="icon-user"></i> <spring:message code='numberLimit'/>: ${vo.minMemberCount } ~ ${vo.maxMemberCount }</p>
		                                        	<h3 class="text-center">
		                                        		<c:if test="${vo.memberCount ne 0}">
		                                        			<a href="#" onClick="editCustInfo(${vo.salonId })" style="text-decoration: underline;">
		                                        				<span style="color:blue;font-size:1.5em">${vo.memberCount }</span>&nbsp;
		                                        				<span style="color:blue;"><spring:message code="member" /></span>
		                                        			</a> 
		                                        		</c:if>
		                                        		<c:if test="${vo.memberCount eq 0}">
		                                        			<span style="color:blue;font-size:1.5em">${vo.memberCount }</span>&nbsp;<span style="color:blue;"><spring:message code="member" /></span>
		                                        		</c:if>
		                                        		
		                                        	</h3>
		                                        </div>
		                                        <!-- 備註 -->
		                                        <div class="price-row"> 
		                                        	<p class="uppercase"><i class="icon-note"></i> <spring:message code='remarks'/>:</p>
		                                        	<h4 class="text-center">${vo.remark }&nbsp;</h4>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
                    		</c:forEach>
                    	</c:if>
                    	<c:if test="${SalonForm.salonList.isEmpty() }">
                    		<span id="noSalonTour_span"><spring:message code='noSalonTour'/></span>
                    	</c:if>
                    	<div class="visit-item col-md-4 col-xs-6 col-sm-12 no-padding" id="guide_demo_tour" style="display:none">
                            <div class="pricing-box">
                                <div class="pricing-body b-l b-r">
                                	<div class="pricing-header">
                               			<button id="guide_demo_editBtn" class="btn btn-info waves-effect waves-light m-t-20" onClick="btnEditClicked('demo')"><spring:message code="edit"/></button>
                                        <button id="guide_demo_joinBtn" class="btn btn-success waves-effect waves-light m-t-20 btn-add" salonId="demo" style="width:50%"><spring:message code="joinGo"/></button>
                                        <button id="guide_demo_delBtn" class="btn btn-danger waves-effect waves-light m-t-20" onClick="btnDeleteClicked('demo', 'demo')"><spring:message code="delete"/></button>
                                    </div>
                                    <div class="pricing-header">
                                        <h2 class="text-center"><b>DEMO</b></h2>
                                        <p class="uppercase">Demo description&nbsp;</p>
                                    </div>
                                    <div class="price-table-content">
                                    	<!-- 狀態 -->
                                        <div class="price-row"> 
                                       	  	<h3 class="text-center">
                                       	  		<b>
	                                        		<span style="color:#24d12a"><i class="icon-user-follow"></i> <spring:message code="visitIng"/></span>
                                       			</b>
                                       		</h3> 
                                        </div>
                                    	<!-- 截止時間 -->
                                    	<div class="price-row">
                                        	<p><i class="icon-hourglass"></i> <spring:message code='postTime'/>:</p>
                                        	<h4 class="text-center"><span style="color:blue">2018-05-01 ~ 2018-12-31</span>&nbsp;</h4>
                                        </div>
                                        <!-- 人數限制 -->
                                        <div class="price-row">
                                        	<p class="uppercase"><i class="icon-user"></i> <spring:message code='numberLimit'/>: 10 ~ 100</p>
                                        	<h3 class="text-center" id="guide_step7_tag">
                                        		<a href="#" onClick="editCustInfo('demo')" style="text-decoration: underline;">
                                      				<span style="color:blue;font-size:1.5em">1</span>&nbsp;
                                      				<span style="color:blue;"><spring:message code="member" /></span>
                                      			</a> 
                                        	</h3>
                                        </div>
                                        <!-- 備註 -->
                                        <div class="price-row"> 
                                        	<p class="uppercase"><i class="icon-note"></i> <spring:message code='remarks'/>:</p>
                                        	<h4 class="text-center">This is a remark for demo&nbsp;</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                 </div>
             </div>
         </div>
     </div>
     <!-- ============================================================== -->
     <!-- End PAge Content -->
     <!-- ============================================================== -->
	
	<div class="salon-cust-panel" style="display:none">
		<div class="salon-cust-panel-content col-md-8 col-xs-6 col-sm-12 no-padding">
			<div class="pricing-box">
				<div class="pricing-body b-l b-r">
					<div class="pricing-header">
						<button id="guide_demo_closeJoinBtn" class="btn btn-info waves-effect waves-light m-t-20" onClick="btnPanelCloseClicked()"><spring:message code="close"/></button>
		          		<button id="guide_demo_saveJoinBtn" class="btn btn-success waves-effect waves-light m-t-20" onClick="btnPanelSaveClicked(${vo.salonId })" style="width:30%"><spring:message code="save"/></button>
	                	<button class="btn btn-primary waves-effect waves-light m-t-20" onClick="btnPanelCheckAllClicked()"><spring:message code="selectAll"/></button>
	                </div>
	                <div class="price-table-content">
	                	<table id="tblMain" class="table table-bordered table-striped">
	                		<!-- 
	                		<colgroup id="guide_step5_tag">
						        <col>
						        <col>
						        <col>
						        <col>
						        <col>
						    </colgroup>
						    <colgroup id="guide_step6_tag">
						        <col>
						    </colgroup>
						     -->
							<thead>
								<tr>
				                    <th><b><spring:message code="name"/></b></th>
				                    <th><b><spring:message code="phoneNo"/></b></th>
				                    <th><b><spring:message code="email"/></b></th>
				                    <th><b><spring:message code="wechatID"/></b></th>
				                    <th><b><spring:message code="status"/></b></th>
				                    <th style="width: 70px;"><b><spring:message code="joinGo"/>?</b></th>
								</tr>
							</thead>
							<tbody>
								<tr id="guide_demo_joinCustPanel" style="display:none">
									<td>Demo</td>
									<td>123456</td>
									<td>Demo@cmp.com</td>
									<td>Demo</td>
									<td><spring:message code="bookedHotel" /></td>
									<td id="guide_step6_tag"><input type="checkbox" name="joinCust" value="demo" checked /></td>
								</tr>
							</tbody>
						</table>
						
						<input type="hidden" id="oriJoinCustIds" name="oriJoinCustIds" value="" />
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
			<h4 class="modal-title"><spring:message code="add"/> / <spring:message code="edit"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body" id="guide_step2_tag">                    
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="salonId" id="salonId" value="" />
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="salonName" class="control-label"><spring:message code="salonName"/><span class="pull-right" style="color: red;" id="guide_step3_tag">＊ </span></label>
	                  <input type="text" class="form-control" id="salonName" name="salonName" placeholder="<spring:message code="salonName"/>">
	                </div>                              
	            </div>
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="salonDescription" class="control-label"><spring:message code="description"/></label>
	                  <input type="text" class="form-control" id="salonDescription" name="salonDescription" placeholder="<spring:message code="description"/>">
	                </div>                              
	            </div>      
	            <div class="box-body">
	                <div class="form-group">
	                  	<label for="beginDate" class="control-label"><spring:message code='postTime'/><span class="pull-right" style="color: red;">＊ </span></label>
	                  	<input id="date-input-beginDate" class="form-control small" type="text" name="beginDate" placeholder="<spring:message code='yearMonthDate'/>" />
	                  	<label for="endDate" class="control-label"><spring:message code='endTime'/><span class="pull-right" style="color: red;">＊ </span></label>
	                  	<input id="date-input-endDate" class="form-control small" type="text" name="endDate" placeholder="<spring:message code='yearMonthDate'/>" />
	                </div>                              
	            </div>  
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="minMemberCount" class="control-label"><spring:message code="numberLimit"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control small" id="minMemberCount" name="minMemberCount">
	                  <label for="maxMemberCount" class="control-label">~<span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control small" id="maxMemberCount" name="maxMemberCount">
	                </div>                              
	            </div>
	            <div class="box-body">
		        	<div class="form-group">
						<label for="status" class="control-label"><spring:message code="status"/><span class="pull-right" style="color: red;">＊ </span></label>
						<select name="status" id="status" class="form-control selectpicker m-b-5" >
							<option value="">=== <spring:message code="pleaseChoose"/> ===</option>
							<option value="22"><spring:message code="salonNotOpen"/></option>
							<option value="23"><spring:message code="salonIng"/></option>
							<option value="24"><spring:message code="salonStop"/></option>
							<option value="25"><spring:message code="salonGo"/></option>
						</select>
		            </div>                              
		        </div>
		        <div class="box-body">
	                <div class="form-group">
	                  	<label for="remarks" class="control-label"><spring:message code='remarks'/></label>
	                	<input type="text" class="form-control" id="remarks" name="remarks" placeholder="<spring:message code='remarks'/>">
	                </div>
	            </div>
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" id="guide_step4_tag" data-dismiss="modal"><spring:message code="close"/></button>
	        		<button type="button" class="btn btn-success" id="btnProfileSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit -->

<!--.燈箱 Edit VistiDetail -->         
<div class="modal fade bs-example-modal-lg" id="modal_Edit_SalonDetail" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title"><spring:message code="edit"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="salonDetailForm" name="salonDetailForm">
            	<input type="hidden" name="custBySalonId" id="custBySalonId" value="" />
	            <div class="box-body">
	                <table id="tblMain_SalonDetail" class="table table-bordered table-striped" style="width:100%">
						<thead>
							<tr>
			                    <th style="width:15%"><b><spring:message code="channel"/></b></th>
			                    <th style="min-width:100px"><b><spring:message code="custName"/></b></th>
			                    <th><b><spring:message code="remarks"/></b></th>
			                    <th><b><spring:message code="status"/></b></th>
							</tr>
						</thead>
						<tbody>
							<tr id="guide_demo_tourDetail">
								<td>ABC</td>
								<td><span>Demo</span></td>
								<td><input type="text" class="form-control" name="remark" value=""/></td>
								<td>
									<select class="form-control" name="statusSort" style="width:120px;min-width:120px">
										<option value="5" selected><spring:message code="bookedHotel"/></option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>                           
	            </div>
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" id="guide_step9_tag" data-dismiss="modal"><spring:message code="close"/></button>
	        		<button type="button" class="btn btn-success" id="btnTourDetailSave" onclick="btnSalonDetailSaveClicked();"><spring:message code="save"/></button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit -->

<script type="text/javascript">
	var tblMain;
	var tblMain_SalonDetail;

	$(document).ready(function() {
		$(".btn-add").bind("click", function( e ) {
			$("#salonId").val($(this).attr('salonId'));
			var $item = $(this).closest("div.salon-item");
			
			var $wrapper = $(document).find('div.salon-wrapper');
			
			$wrapper.find('div.salon-item').not( $item ).fadeOut();
			$item.find('button.btn-add').hide();
			
			setTimeout(function(){
				if ($("#salonId").val() == 'demo') {
					$(".salon-wrapper").append($('.salon-cust-panel').html()).fadeIn('slow');
					$('#guide_demo_joinCustPanel').show();
					
					setTimeout(function(){
						showGuideTour2();
					}, 300);
					
				} else {
					findCustInfo();
				}
				
			}, 500);
			
			$("html, body").animate({ scrollTop: 0 }, "slow");
	  	});
		
		laydate.render({
			elem: '#date-input-beginDate' //指定元素
		   ,lang: 'en'
		});
		laydate.render({
			elem: '#date-input-endDate' //指定元素
		   ,lang: 'en'
		});
		laydate.render({
		  	elem: '#date-input-departureDate'
		   ,lang: 'en'
		}); 
		laydate.render({
		    elem: '#time-input-departureTime'
		   ,type: 'time'
		   ,lang: 'en'
		}); 
		laydate.render({
		  	elem: '#date-input-arrivalDate'
		   ,lang: 'en'
		}); 
		laydate.render({
		    elem: '#time-input-arrivalTime'
		   ,type: 'time'
		   ,lang: 'en'
		}); 
		laydate.render({
		  	elem: '#date-input-returnDate'
		   ,lang: 'en'
		}); 
		laydate.render({
		    elem: '#time-input-returnTime'
		   ,type: 'time'
		   ,lang: 'en'
		}); 
	});
	
	function findCustInfo() {
		if (tblMain) {
			tblMain.destroy();	//重查資料
		} 
		
		tblMain = $('#tblMain').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/channel/cust/getCustAndSalonByUserId.json',
				"type" : 'GET',
				"data" : function(d) {
					d.salonId = $("#salonId").val()
				}
			},
			"drawCallback": function( settings ) {
				$(".salon-wrapper").append($('.salon-cust-panel').html()).fadeIn('slow');
				
				var custIdArray = new Array();
				var idx = 0;
				var length = $('input[type=checkbox][name=joinCust]').length;
				$('input[type=checkbox][name=joinCust]').each(function(i, item) {
					if (idx == length/2) {
						return;
					}
					
					if (this.checked) {
						custIdArray.push($(item).val());
					}
					
					idx += 1;
				});
				
				$('#oriJoinCustIds').val(custIdArray.join(','));
		    },
			/*
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
			*/
			"columns" : [
				{ "data" : "name" },
				{ "data" : "phone" },
				{ "data" : "email" },
				{ "data" : "weChat" },
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
						if (row['joined']) {
							return '<input type="checkbox" name="joinCust" value="' + row['id'] + '" checked />';
						} else {
							return '<input type="checkbox" name="joinCust" value="' + row['id'] + '" />';
						}
					}
				}
			],
			select: true
		});
	}
	
	function btnPanelCloseClicked() {
		var $wrapper = $(document).find('div.salon-wrapper');
		$wrapper.find('div.salon-cust-panel-content').remove();
		$wrapper.find('div.salon-item').find('button.btn-add').show();
		$wrapper.find('div.salon-item').fadeIn();
	}
	
	function btnPanelSaveClicked() {
		var newJoinCustIds = new Array();
		var oriJoinCustIds = $('#oriJoinCustIds').val().split(',');
		var addCustIds = new Array();
		var deleteCustIds = new Array();
		
		var idx = 0;
		var length = $('input[type=checkbox][name=joinCust]').length;
		$('input[type=checkbox][name=joinCust]').each(function(i, item) {
			if (idx == length/2) {
				return;
			}
			
			if (this.checked) {
				newJoinCustIds.push($(item).val());
			}
			
			idx += 1;
		});
		
		if (newJoinCustIds.length == 0 && oriJoinCustIds.length == 0) {
			alert("請選擇");
			
		} else {
			//判斷哪些是要刪除的
			for (var i=0; i<oriJoinCustIds.length; i++) {
				
				var toDelete = true;
				for (var j=0; j<newJoinCustIds.length; j++) {
					
					if (oriJoinCustIds[i] == newJoinCustIds[j]) {
						toDelete = false;
						break;
					}
				}
				
				if (toDelete) {
					deleteCustIds.push(oriJoinCustIds[i]);
				}
			}
			
			//判斷哪些是要新增的
			for (var i=0; i<newJoinCustIds.length; i++) {
				
				var toAdd = true;
				for (var j=0; j<oriJoinCustIds.length; j++) {
					
					if (newJoinCustIds[i] == oriJoinCustIds[j]) {
						toAdd = false;
						break;
					}
				}
				
				if (toAdd) {
					addCustIds.push(newJoinCustIds[i]);
				}
			}
			
			if (deleteCustIds.length == 0 && addCustIds.length == 0) {
				alert('<spring:message code="error.noSelectMember"/>');
				
			} else {
				$.ajax({
					url : '${pageContext.request.contextPath}/salon/join',
					data : {
						'salonId' : $("#salonId").val(),
						'deleteCustIds' : deleteCustIds.join(','),
						'addCustIds' : addCustIds.join(',')
					},
					type : "POST",
					dataType : 'json',
					async: false,
					success : function(resp) {
						if (resp.code == '200') {
							successMsgModal("<spring:message code='success.update'/>");
							setTimeout(function(){
								$('#modal_Edit').modal('hide');
								//重新查詢
								window.location.href = '${pageContext.request.contextPath}/salon/list';
							}, 1000);
							
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
	}
	
	var checked = true;
	function btnPanelCheckAllClicked() {
		var idx = 0;
		var length = $('input[type=checkbox][name=joinCust]').length;
		$('input[type=checkbox][name=joinCust]').each(function(i, item) {
			if (idx == length/2) {
				return;
			}
			
			$(item).prop('checked', checked);
			
			idx += 1;
		});
		
		if (checked) {
			checked = false;
		} else {
			checked = true;
		}
	}
	
	var formAction = '';
	
	//[Add] 進入modal_Edit編輯
	function btnAddClicked() {
		formAction = 'save';
		$('.form-group').removeClass('has-error');
		$('#salonId').val('');
		$('#salonName').val('');
		$('#salonDescription').val('');
		$('#beginDate').val('');
		$('#endDate').val('');
		$('#minMemberCount').val('');
		$('#maxMemberCount').val('');
		$('#status').val('');
		$('#remarks').val('');

		$('#modal_Edit').modal();
		
		if("Y"===showGuide) {
			setTimeout(function() {
				showGuideTour();
			}, 500);
		}
	}
	
	//[Save] modal_Edit >>按下Save 儲存
	function btnSaveClicked() {
		var salonName = $('#salonName').val();
		var beginDate = $('#date-input-beginDate').val();
		var endDate = $('#date-input-endDate').val();
		var minMemberCount = $('#minMemberCount').val();
		var maxMemberCount = $('#maxMemberCount').val();
		var status = $('#status').val();
		
		//頁面輸入檢核
		$('.form-group').removeClass('has-error');
		var isError = false;
		var errMsg = '';
		if ('' == salonName.trim()) {
			isError = true;
			$('#salonName').parents('.form-group').addClass('has-error');
		} 
		if ('' == beginDate.trim()) {
			isError = true;
			$('#date-input-beginDate').parents('.form-group').addClass('has-error');
		} 
		if ('' == endDate.trim()) {
			isError = true;
			$('#date-input-endDate').parents('.form-group').addClass('has-error');
		} 
		if ('' == minMemberCount.trim()) {
			isError = true;
			$('#minMemberCount').parents('.form-group').addClass('has-error');
		} 
		if ('' == maxMemberCount.trim()) {
			isError = true;
			$('#maxMemberCount').parents('.form-group').addClass('has-error');
		} 
		if ('' == status.trim()) {
			isError = true;
			$('#status').parents('.form-group').addClass('has-error');
		}
		if(isError){
			errorMsgModal('<spring:message code="error.mustFillMustField"/><br/>');
			return false;
		}
		
		//人數上限不可低於下限
		if (Number(maxMemberCount) < Number(minMemberCount)) {
			$('#maxMemberCount').parents('.form-group').addClass('has-error');
			errorMsgModal('<spring:message code="error.peopleLowerThanLower"/><br/>');
			return false;
		}
		
		var currentDate = Date.parse((new Date()).toDateString());
		var bDate = Date.parse(beginDate);
		
		//生效起始日若晚於當下日期，狀態不可設定為「招人中」
		if ((currentDate < bDate) && (status == 23)) {
			$('#date-input-beginDate').parents('.form-group').addClass('has-error');
			$('#status').parents('.form-group').addClass('has-error');
			errorMsgModal('<spring:message code="error.startDateAndStatusNotMatch"/> 「<spring:message code="salonIng"/>」<br/>');
			return false;
		}
		
		$.ajax({
			url : '${pageContext.request.contextPath}/salon/save',
			data : $('#formEdit').serialize(),
			type : "POST",
			dataType : 'json',
			async: false,
			success : function(resp) {
				if (resp.code == '200') {
					successMsgModal("<spring:message code='success.update'/>");
					setTimeout(function(){
						$('#modal_Edit').modal('hide');
						//重新查詢
						window.location.href = '${pageContext.request.contextPath}/salon/list';
					}, 1000);
					
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
	
	//[Edit] 進入modal_Edit編輯
	function btnEditClicked(salonId) {
		$.ajax({
				url : '${pageContext.request.contextPath}/salon/getSalon',
				data : {salonId: salonId},
				type : "POST",
				dataType : 'json',
				async: false,
				success : function(resp) {
					if (resp.code == "200") {
						$('#salonId').val(salonId);
						$('#salonName').val(resp.data.salon.salonName);
						$('#salonDescription').val(resp.data.salon.description);
						$('#date-input-beginDate').val(resp.data.salon.beginDate);
						$('#date-input-endDate').val(resp.data.salon.endDate);
						$('#minMemberCount').val(resp.data.salon.minMemberCount);
						$('#maxMemberCount').val(resp.data.salon.maxMemberCount);
						$('#status').val(resp.data.salon.status).change();
						$('#remarks').val(resp.data.salon.remark);
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
	
	function btnDeleteClicked(salonId, salonName) {
		var choose = confirm("<spring:message code='confirmToDelete'/> 「"+salonName+"」?");
		
		if (choose) {
			$.ajax({
				url : '${pageContext.request.contextPath}/salon/delete/',
				data : {salonId: salonId},
				type : "POST",
				dataType : 'json',
				async: false,
				success : function(resp) {
					if (resp.code == '200') {
						alert("<spring:message code='success.delete'/>");
						//重新查詢
						window.location.href = '${pageContext.request.contextPath}/salon/list';

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
	
	function editCustInfo(salonId) {
		if (salonId == 'demo') {
			$('#modal_Edit_SalonDetail').modal();
			$('#guide_demo_tourDetail').show();
			
			setTimeout(function() {
				showGuideTour3();
			}, 500);
			
			return;
		}
		
		$('#custBySalonId').val(salonId);
		
		if (tblMain_SalonDetail) {
			tblMain_SalonDetail.destroy();	//重查資料
		} 
		
		tblMain_SalonDetail = $('#tblMain_SalonDetail').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/salon/getSalonDetails',
				"type" : 'POST',
				"data" : {
					'salonId' : salonId
				}
			},
			"drawCallback": function( settings ) {
				$('#modal_Edit_SalonDetail').modal();
		    },
			"columns" : [
				{ "data" : "userName" },
				{ "data" : "custName" }
				/*
				{ "data" : "visaStatus" },
				{ "data" : "accommodationSituation" },
				{ "data" : "amountReceived" },
				{ "data" : "remark" }
				*/
			],
			"columnDefs" : [
				{
					"targets" : [2],
					"render" : function(data, type, row) {
								return '<input type="text" class="form-control" name="remark" value="' + row['remark'] + '"/>'
									   + '<input type="hidden" name="custId" value="' + row['custId'] + '"/>';
							}
				},
				{
					"targets" : [3],
					"render" : function(data, type, row) {
								var ret = '<select class="form-control" name="statusSort" width="120" style="min-width:120px" />';
								ret += '<option value="1" ' + ((row['sort'] == 1) ? 'selected' : '') + '><spring:message code="registered"/></option>';
								ret += '<option value="2" ' + ((row['sort'] == 2) ? 'selected' : '') + '><spring:message code="bookingFeePaid"/></option>';
								ret += '<option value="3" ' + ((row['sort'] == 3) ? 'selected' : '') + '><spring:message code="bookedAirTicket"/></option>';
								ret += '<option value="4" ' + ((row['sort'] == 4) ? 'selected' : '') + '><spring:message code="visaIssued"/></option>';
								ret += '<option value="5" ' + ((row['sort'] == 5) ? 'selected' : '') + '><spring:message code="bookedHotel"/></option>';
								ret += '<option value="6" ' + ((row['sort'] == 6) ? 'selected' : '') + '><spring:message code="visited"/></option>';
								ret += '<option value="7" ' + ((row['sort'] == 7) ? 'selected' : '') + '><spring:message code="contractsSigned"/></option>';
								ret += '<option value="8" ' + ((row['sort'] == 8) ? 'selected' : '') + '><spring:message code="depositPaid"/></option>';
								ret += '<option value="9" ' + ((row['sort'] == 9) ? 'selected' : '') + '><spring:message code="downPayment"/></option>';
								ret += '<option value="10" ' + ((row['sort'] == 10) ? 'selected' : '') + '><spring:message code="loanApplication"/></option>';
								ret += '<option value="11" ' + ((row['sort'] == 11) ? 'selected' : '') + '><spring:message code="progressPayments"/></option>';
								ret += '<option value="12" ' + ((row['sort'] == 12) ? 'selected' : '') + '><spring:message code="settlement"/></option>';
								ret += '</select>';

								return ret;
							}
				},
			],
			select: true
		});
	}
	
	function btnSalonDetailSaveClicked() {
		$.ajax({
			url : '${pageContext.request.contextPath}/salon/saveDetail',
			data : $('#salonDetailForm').serialize(),
			type : "POST",
			dataType : 'json',
			async: false,
			success : function(resp) {
				if (resp.code == '200') {
					successMsgModal("<spring:message code='success.update'/>");
					setTimeout(function(){
						$('#modal_Edit_SalonDetail').modal('hide');
					}, 1000);
					
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

<style>
.modal-body form .box-body .form-group label {
    width: 15%;
}
.modal-body form .box-body .form-group .form-control{
	display: inline-block;
	width: 82%;
}
.modal-body form .box-body .form-group .small{
	display: inline-block;
	width: 33%;
}
</style>

	<script>
	    var showGuide = '${showGuide}';
	    var isHome = '${active}';
	    var title_salon = '<spring:message code="guide_tour_msg_76"/>'; //『活动』导览说明
	    $(document).ready(function(){
	      if("Y"===showGuide) {
	    	// Instance the tour
			var tour = new Tour({
				backdrop: true,
				smartPlacement: true,
				storage: false,
				template: 
					function (key, value) { 
						return getGuideTourTemplate(true);
					}
				,
				steps: [
	    	  		{
	    	    		element: "#guide_step1_tag",
	    	    		title: title_salon + " - 1/14",
	    	    		content: '<spring:message code="guide_tour_msg_77"/>', //点击「新增」按钮进入新增活动画面
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onShown: function() {
	    	    			$('.btn[data-role="next"]').removeClass("disabled");
	    	    			$('.btn[data-role="next"]').prop("disabled", false);
	    	    			$('.btn[data-role="next"]').on("click", function (e) {
	    	                    tour.end();
	    	                });
	    	    		},
	    	    		onHidden: function(tour) {
	    	    			btnAddClicked();
	    	    		}
	    	  		}
	    		]});
	
	    	// Initialize the tour
	    	tour.init();
	
	    	// Start the tour
	    	tour.start();
	    	//tour.restart();
	      }
	    });
	    
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
						return getGuideTourTemplate(true);
					}
				,
				steps: [
	    	  		{
	    	    		element: "#guide_step2_tag",
	    	    		title: title_salon + " - 2/14",
	    	    		content: '<spring:message code="guide_tour_msg_78"/>', //输入活动相关资料
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step3_tag",
	    	    		title: title_salon + " - 3/14",
	    	    		content: '<spring:message code="guide_tour_msg_33"/>', //星字号栏位为必填
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onNext: function() {
	    	    			$('#modal_Edit').animate({ scrollTop: $('#btnProfileSave').offset().top }, 500);
	    	    		}
	    	    		
	    	  		},
	    	  		{
	    	    		element: "#btnProfileSave",
	    	    		title: title_salon + " - 4/14",
	    	    		content: '<spring:message code="guide_tour_msg_21"/>', //点击「保存」按钮储存修改值
	    	    		placement: "top",
	    	    		animation: false,
	    	    		onHidden: function() {
	    	    			$('#guide_step4_tag').click();
	    	    			$('#noSalonTour_span').hide();
	    	    			$('#guide_demo_tour').show();
	    	    		}
	    	  		},
	    	  		{
	    	    		element: "#guide_demo_tour",
	    	    		title: title_salon + " - 5/14",
	    	    		content: '<spring:message code="guide_tour_msg_79"/>', //新增活动后呈显结果
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_demo_joinBtn",
	    	    		title: title_salon + " - 6/14",
	    	    		content: '<spring:message code="guide_tour_msg_80"/>', //点击「加入/取消」按钮选择客户入活动
	    	    		placement: "top",
	    	    		animation: false,
	    	    		onShown: function() {
	    	    			$('.btn[data-role="next"]').removeClass("disabled");
	    	    			$('.btn[data-role="next"]').prop("disabled", false);
	    	    			$('.btn[data-role="next"]').on("click", function (e) {
	    	                    tour.end();
	    	                });
	    	    		},
	    	    		onHidden: function() {
	    	    			$('#guide_demo_joinBtn').click();
	    	    			return (new jQuery.Deferred()).promise();
	    	    		}
	    	  		}
	    		]});
	
	    	// Initialize the tour
	    	tour.init();
	
	    	// Start the tour
	    	tour.start();
	    	//tour.restart();
	      }
	    }
		
		function showGuideTour2() {
	      if("Y"===showGuide) {
			//showTip();
	    	// Instance the tour
			var tour = new Tour({
				backdrop: true,
				smartPlacement: true,
				storage: false,
				template: 
					function (key, value) { 
						return getGuideTourTemplate(true);
					}
				,
				steps: [
	    	  		{
	    	    		element: "#tblMain",
	    	    		title: title_salon + " - 7/14",
	    	    		content: '<spring:message code="guide_tour_msg_68"/>', //呈显客户清单及其基本资料与当前所处状态
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step6_tag",
	    	    		title: title_salon + " - 8/14",
	    	    		content: '<spring:message code="guide_tour_msg_81"/>', //勾选或取消勾选表示加入或取消此客户入活动
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_demo_saveJoinBtn",
	    	    		title: title_salon + " - 9/14",
	    	    		content: '<spring:message code="guide_tour_msg_70"/>', //点击「保存」按钮储存此次修改
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onHidden: function() {
	    	    			$('#guide_demo_closeJoinBtn').click();
	    	    		}
	    	  		},
	    	  		{
	    	    		element: "#guide_step7_tag",
	    	    		title: title_salon + " - 10/14",
	    	    		content: '<spring:message code="guide_tour_msg_82"/>', //点击已入活动人数连结进入已入活动客户清单画面
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onShown: function() {
	    	    			$('.btn[data-role="next"]').removeClass("disabled");
	    	    			$('.btn[data-role="next"]').prop("disabled", false);
	    	    			$('.btn[data-role="next"]').on("click", function (e) {
	    	                    tour.end();
	    	                });
	    	    		},
	    	    		onHidden: function() {
	    	    			editCustInfo('demo');
	    	    			return (new jQuery.Deferred()).promise();
	    	    		}
	    	  		}
	    		]});
	
	    	// Initialize the tour
	    	tour.init();
	
	    	// Start the tour
	    	tour.start();
	    	//tour.restart();
	      }
	    }
		
		function showGuideTour3() {
	      if("Y"===showGuide) {
			//showTip();
	    	// Instance the tour
			var tour = new Tour({
				backdrop: true,
				smartPlacement: true,
				storage: false,
				template: 
					function (key, value) { 
						var isNotLastStep = false;
						if (key != 4) {
							isNotLastStep = true
						}
						return getGuideTourTemplate(isNotLastStep);
					}
				,
				steps: [
	    	  		{
	    	    		element: "#tblMain_SalonDetail",
	    	    		title: title_salon + " - 11/14",
	    	    		content: '<spring:message code="guide_tour_msg_83"/>', //检视已入活动客户相关资料并可进行修改
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#btnTourDetailSave",
	    	    		title: title_salon + " - 12/14",
	    	    		content: '<spring:message code="guide_tour_msg_21"/>', //点击「保存」按钮储存修改值
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onHidden: function() {
	    	    			$('#guide_step9_tag').click();
	    	    		}
	    	  		},
	    	  		{
	    	    		element: "#guide_demo_editBtn",
	    	    		title: title_salon + " - 13/14",
	    	    		content: '<spring:message code="guide_tour_msg_84"/>', //点击「编辑」按钮进入活动编辑画面，可修改相关资料与状态
	    	    		placement: "top",
	    	    		animation: false
	    	  		}
	    	  		,
	    	  		{
	    	    		element: "#guide_demo_delBtn",
	    	    		title: title_salon + " - 14/14",
	    	    		content: '<spring:message code="guide_tour_msg_85"/>', //点击「删除」按钮删除此活动
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#billboard-tag",
	    	    		title: '<spring:message code="guide_tour_msg_86"/>', //『系统公告』导览说明
	    	    		content: '<spring:message code="guide_tour_msg_9"/>', //请点击功能选单进入下一阶段导览
	    	    		animation: false
	    	  		}
	    		]});
	
	    	// Initialize the tour
	    	tour.init();
	
	    	// Start the tour
	    	tour.start();
	    	//tour.restart();
	      }
	    }
	</script>