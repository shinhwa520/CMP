<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/laydate/laydate.js"></script>

<section class="content">

	<div class="row page-titles">
	    <div class="col-md-6 col-8 align-self-center">
	        <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="visitTour" />&nbsp;
	        <c:if test="${VisitForm.canAdd}">
				<button type="button" class="btn btn-warning" onclick="btnAddClicked();" style="width:30%"><spring:message code='add'/></button>
		   	</c:if>
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
                    <div class="visit-wrapper row pricing-plan">
                    	<c:if test="${!VisitForm.visitList.isEmpty() }">
                    		<c:forEach var="vo" items="${ VisitForm.visitList }" varStatus="loop">
                    			<div class="visit-item col-md-4 col-xs-6 col-sm-12 no-padding">
		                            <div class="pricing-box">
		                                <div class="pricing-body b-l b-r">
		                                	<div class="pricing-header">
		                                		<c:if test="${vo.canModify}">
		                                			<button class="btn btn-info waves-effect waves-light m-t-20" onClick="btnEditClicked(${vo.visitId })"><spring:message code="edit"/></button>
		                                		</c:if>
	                                            <c:if test="${vo.status ne 23}">
	                                            	<button class="btn waves-effect waves-light m-t-20" style="width:50%;" disabled><spring:message code="addMembers"/></button>
	                                            </c:if>
	                                            <c:if test="${vo.status eq 23}">
	                                            	<button class="btn btn-success waves-effect waves-light m-t-20 btn-add" visitId="${vo.visitId }" style="width:50%"><spring:message code="addMembers"/></button>
	                                            </c:if>
	                                            <c:if test="${vo.canModify}">
	                                            	<button class="btn btn-danger waves-effect waves-light m-t-20" onClick="btnDeleteClicked(${vo.visitId }, '${vo.visitName }')"><spring:message code="delete"/></button>
	                                        	</c:if>
	                                        </div>
		                                    <div class="pricing-header">
		                                        <h2 class="text-center"><b>${vo.visitName }</b></h2>
		                                        <p class="uppercase">${vo.description }&nbsp;</p>
		                                    </div>
		                                    <div class="price-table-content">
		                                    	<!-- 狀態 -->
		                                        <div class="price-row"> 
	                                        	  	<h3 class="text-center">
	                                        	  		<b>
			                                        		<c:if test="${vo.status eq 22}">
			                                        			<span style="color:#c77f0a"><i class="icon-lock"></i> <spring:message code="visitNotOpen"/></span>
			                                        		</c:if>
			                                        		<c:if test="${vo.status eq 23}">
			                                        			<span style="color:#24d12a"><i class="icon-user-follow"></i> <spring:message code="visitIng"/></span>
			                                        		</c:if>
			                                        		<c:if test="${vo.status eq 24}">
			                                        			<span style="color:#dc3545"><i class="icon-close"></i> <spring:message code="visitStop"/></span>
			                                        		</c:if>
			                                        		<c:if test="${vo.status eq 25}">
			                                        			<span style="color:#00f"><i class="icon-check"></i> <spring:message code="visitGo"/></span>
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
		                                        			<a href="#" onClick="editCustInfo(${vo.visitId })" style="text-decoration: underline;">
		                                        				<span style="color:blue;font-size:1.5em">${vo.memberCount }</span>&nbsp;
		                                        				<span style="color:blue;"><spring:message code="member" /></span>
		                                        			</a> 
		                                        		</c:if>
		                                        		<c:if test="${vo.memberCount eq 0}">
		                                        			<span style="color:blue;font-size:1.5em">${vo.memberCount }</span>&nbsp;<span style="color:blue;"><spring:message code="member" /></span>
		                                        		</c:if>
		                                        		
		                                        	</h3>
		                                        </div>
		                                        <!-- 出發日期 -->
		                                        <div class="price-row"> 
		                                        	<p class="uppercase"><i class="icon-calender"></i> <spring:message code='departureDate'/>:</p>
		                                        	<h4 class="text-center">${vo.departureDate } ${vo.departureTime }&nbsp;</h4>
		                                        </div>
		                                        <!-- 出發城市 -->
		                                        <div class="price-row"> 
		                                        	<p class="uppercase"><i class="icon-plane"></i> <spring:message code='departureCity'/>:</p>
		                                        	<h4 class="text-center">${vo.departureCity }&nbsp;</h4>
		                                        </div>
		                                        <!-- 到達日期 -->
		                                        <div class="price-row"> 
		                                        	<p class="uppercase"><i class="icon-calender"></i> <spring:message code='arrivalDate'/>:</p>
		                                        	<h4 class="text-center">${vo.arrivalDate } ${vo.arrivalTime }&nbsp;</h4>
		                                        </div>
		                                        <!-- 到達機場航站樓 -->
		                                        <div class="price-row"> 
		                                        	<p class="uppercase"><i class="icon-location-pin"></i> <spring:message code='arrivalAirportTerminal'/>:</p>
		                                        	<h4 class="text-center">${vo.arrivalAirportTerminal }&nbsp;</h4>
		                                        </div>
		                                        <!-- 返程日期 -->
		                                        <div class="price-row"> 
		                                        	<p class="uppercase"><i class="icon-calender"></i> <spring:message code='returnDate'/>:</p>
		                                        	<h4 class="text-center">${vo.returnDate } ${vo.returnTime }&nbsp;</h4>
		                                        </div>
		                                        <!-- 返程機場航站樓 -->
		                                        <div class="price-row"> 
		                                        	<p class="uppercase"><i class="icon-home"></i> <spring:message code='returnAirportTerminal'/>:</p>
		                                        	<h4 class="text-center">${vo.returnAirportTerminal }&nbsp;</h4>
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
                    	<c:if test="${VisitForm.visitList.isEmpty() }">
                    		<spring:message code='noVisitTour'/>
                    	</c:if>
                    </div>
                 </div>
             </div>
         </div>
     </div>
     <!-- ============================================================== -->
     <!-- End PAge Content -->
     <!-- ============================================================== -->
	
	<div class="visit-cust-panel" style="display:none">
		<div class="visit-cust-panel-content col-md-8 col-xs-6 col-sm-12 no-padding">
			<div class="pricing-box">
				<div class="pricing-body b-l b-r">
					<div class="pricing-header">
						<button class="btn btn-info waves-effect waves-light m-t-20" onClick="btnPanelCloseClicked()"><spring:message code="close"/></button>
		          		<button class="btn btn-success waves-effect waves-light m-t-20" onClick="btnPanelSaveClicked(${vo.visitId })" style="width:30%"><spring:message code="save"/></button>
	                	<button class="btn btn-primary waves-effect waves-light m-t-20" onClick="btnPanelCheckAllClicked()"><spring:message code="selectAll"/></button>
	                </div>
	                <div class="price-table-content">
	                	<table id="tblMain" class="table table-bordered table-striped">
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
      	<div class="modal-body">                    
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="visitId" id="visitId" value="" />
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="visitName" class="control-label"><spring:message code="visitName"/><span class="pull-right" style="color: red;">＊ </span></label>
	                  <input type="text" class="form-control" id="visitName" name="visitName" placeholder="<spring:message code="visitName"/>">
	                </div>                              
	            </div>
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="visitDescription" class="control-label"><spring:message code="description"/></label>
	                  <input type="text" class="form-control" id="visitDescription" name="visitDescription" placeholder="<spring:message code="description"/>">
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
	                  	<label for="departureDate" class="control-label"><spring:message code='departureDate'/></label>
	                  	<input id="date-input-departureDate" class="form-control small" type="text" name="departureDate" placeholder="<spring:message code='yearMonthDate'/>" />
	               		<label for="departureTime" class="control-label"><spring:message code='departureTime'/></label>
	               		<input id="time-input-departureTime" class="form-control small" type="text" name="departureTime" placeholder="<spring:message code='hourMinuteSecond'/>" />
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  	<label for="departureCity" class="control-label"><spring:message code='departureCity'/></label>
	                	<input type="text" class="form-control" id="departureCity" name="departureCity" placeholder="<spring:message code='departureCity'/>">
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  	<label for="arrivalDate" class="control-label"><spring:message code='arrivalDate'/></label>
	                  	<input id="date-input-arrivalDate" class="form-control small" type="text" name="arrivalDate" placeholder="<spring:message code='yearMonthDate'/>" />
	               		<label for="arrivalTime" class="control-label"><spring:message code='arrivalTime'/></label>
	               		<input id="time-input-arrivalTime" class="form-control small" type="text" name="arrivalTime" placeholder="<spring:message code='hourMinuteSecond'/>" />
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  	<label for="arrivalAirportTerminal" class="control-label"><spring:message code='arrivalAirportTerminal'/></label>
	                	<input type="text" class="form-control" id="arrivalAirportTerminal" name="arrivalAirportTerminal" placeholder="<spring:message code='arrivalAirportTerminal'/>">
	                </div>
	            </div>
	            
	            <div class="box-body">
	                <div class="form-group">
	                  	<label for="returnDate" class="control-label"><spring:message code='visitReturnDate'/></label>
	                  	<input id="date-input-returnDate" class="form-control small" type="text" name="returnDate" placeholder="<spring:message code='yearMonthDate'/>" />
	               		<label for="returnTime" class="control-label"><spring:message code='takeOffTime'/></label>
	               		<input id="time-input-returnTime" class="form-control small" type="text" name="returnTime" placeholder="<spring:message code='hourMinuteSecond'/>" />
	                </div>
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  	<label for="returnAirportTerminal" class="control-label"><spring:message code='visitReturnAirportTerminal'/></label>
	                	<input type="text" class="form-control" id="returnAirportTerminal" name="returnAirportTerminal" placeholder="<spring:message code='visitReturnAirportTerminal'/>">
	                </div>
	            </div>
	            <div class="box-body">
		        	<div class="form-group">
						<label for="status" class="control-label"><spring:message code="status"/><span class="pull-right" style="color: red;">＊ </span></label>
						<select name="status" id="status" class="form-control selectpicker m-b-5" >
							<option value="">=== <spring:message code="pleaseChoose"/> ===</option>
							<option value="22"><spring:message code="visitNotOpen"/></option>
							<option value="23"><spring:message code="visitIng"/></option>
							<option value="24"><spring:message code="visitStop"/></option>
							<option value="25"><spring:message code="visitGo"/></option>
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
	        		<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
	        		<button type="button" class="btn btn-success" id="btnProfileSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit -->

<!--.燈箱 Edit VistiDetail -->         
<div class="modal fade bs-example-modal-lg" id="modal_Edit_VisitDetail" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title"><spring:message code="edit"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">                    
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="visitId" id="visitId" value="" />
	            <div class="box-body">
	                <table id="tblMain_VisitDetail" class="table table-bordered table-striped" style="width:100%">
						<thead>
							<tr>
			                    <th style="width:15%"><b><spring:message code="channel"/></b></th>
			                    <th style="width:15%"><b><spring:message code="custName"/></b></th>
			                    <th><b><spring:message code="visaStatus"/></b></th>
			                    <th><b><spring:message code="accommodationSituation"/></b></th>
			                    <th><b><spring:message code="amountReceived"/></b></th>
			                    <th><b><spring:message code="remarks"/></b></th>
							</tr>
						</thead>
					</table>                           
	            </div>
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
	        		<button type="button" class="btn btn-success" id="btnProfileSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 Edit -->

<script type="text/javascript">
	var tblMain;
	var tblMain_VisitDetail;

	$(document).ready(function() {
		$(".btn-add").bind("click", function( e ) {
			$("#visitId").val($(this).attr('visitId'));
			var $item = $(this).closest("div.visit-item");
			
			var $wrapper = $(document).find('div.visit-wrapper');
			
			$wrapper.find('div.visit-item').not( $item ).fadeOut();
			$item.find('button.btn-add').hide();
			
			setTimeout(function(){
				findCustInfo();
				
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
				"url" : '${pageContext.request.contextPath}/channel/cust/getCustAndVisitByUserId.json',
				"type" : 'GET',
				"data" : function(d) {
					d.visitId = $("#visitId").val()
				}
			},
			"drawCallback": function( settings ) {
				$(".visit-wrapper").append($('.visit-cust-panel').html()).fadeIn('slow');
				
				var custIdArray = new Array();
				$('input[type=checkbox][name=joinCust]').each(function(i, item) {
					if (this.checked) {
						custIdArray.push($(item).val());
					}
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
		var $wrapper = $(document).find('div.visit-wrapper');
		$wrapper.find('div.visit-cust-panel-content').remove();
		$wrapper.find('div.visit-item').find('button.btn-add').show();
		$wrapper.find('div.visit-item').fadeIn();
	}
	
	function btnPanelSaveClicked() {
		var newJoinCustIds = new Array();
		var oriJoinCustIds = $('#oriJoinCustIds').val().split(',');
		var addCustIds = new Array();
		var deleteCustIds = new Array();
		
		$('input[type=checkbox][name=joinCust]').each(function(i, item) {
			if (this.checked) {
				newJoinCustIds.push($(item).val());
			}
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
					url : '${pageContext.request.contextPath}/visit/join',
					data : {
						'visitId' : $("#visitId").val(),
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
								window.location.href = '${pageContext.request.contextPath}/visit/tour';
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
		$('input[type=checkbox][name=joinCust]').each(function(i, item) {
			$(item).prop('checked', checked);
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
		$('#visitId').val('');
		$('#visitName').val('');
		$('#visitDescription').val('');
		$('#beginDate').val('');
		$('#endDate').val('');
		$('#minMemberCount').val('');
		$('#maxMemberCount').val('');
		$('#departureDate').val('');
		$('#departureTime').val('');
		$('#departureCity').val('');
		$('#arrivalDate').val('');
		$('#arrivalTime').val('');
		$('#arrivalAirportTerminal').val('');
		$('#returnDate').val('');
		$('#returnTime').val('');
		$('#returnAirportTerminal').val('');
		$('#status').val('');
		$('#remarks').val('');

		$('#modal_Edit').modal();
	}
	
	//[Save] modal_Edit >>按下Save 儲存
	function btnSaveClicked() {
		var visitName = $('#visitName').val();
		var beginDate = $('#date-input-beginDate').val();
		var endDate = $('#date-input-endDate').val();
		var minMemberCount = $('#minMemberCount').val();
		var maxMemberCount = $('#maxMemberCount').val();
		var status = $('#status').val();
		
		//頁面輸入檢核
		$('.form-group').removeClass('has-error');
		var isError = false;
		var errMsg = '';
		if ('' == visitName.trim()) {
			isError = true;
			$('#visitName').parents('.form-group').addClass('has-error');
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
			errorMsgModal('必填欄位未填<br/>');
			return false;
		}
		
		$.ajax({
			url : '${pageContext.request.contextPath}/visit/save',
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
						window.location.href = '${pageContext.request.contextPath}/visit/tour';
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
	function btnEditClicked(visitId) {
		$.ajax({
				url : '${pageContext.request.contextPath}/visit/getVisit',
				data : {visitId: visitId},
				type : "POST",
				dataType : 'json',
				async: false,
				success : function(resp) {
					if (resp.code == "200") {
						$('#visitId').val(visitId);
						$('#visitName').val(resp.data.visit.visitName);
						$('#visitDescription').val(resp.data.visit.description);
						$('#date-input-beginDate').val(resp.data.visit.beginDate);
						$('#date-input-endDate').val(resp.data.visit.endDate);
						$('#minMemberCount').val(resp.data.visit.minMemberCount);
						$('#maxMemberCount').val(resp.data.visit.maxMemberCount);
						$('#date-input-departureDate').val(resp.data.visit.departureDate);
						$('#time-input-departureTime').val(resp.data.visit.departureTime);
						$('#departureCity').val(resp.data.visit.departureCity);
						$('#date-input-arrivalDate').val(resp.data.visit.arrivalDate);
						$('#time-input-arrivalTime').val(resp.data.visit.arrivalTime);
						$('#arrivalAirportTerminal').val(resp.data.visit.arrivalAirportTerminal);
						$('#date-input-returnDate').val(resp.data.visit.returnDate);
						$('#time-input-returnTime').val(resp.data.visit.returnTime);
						$('#returnAirportTerminal').val(resp.data.visit.returnAirportTerminal);
						$('#status').val(resp.data.visit.status).change();
						$('#remarks').val(resp.data.visit.remark);
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
	
	function btnDeleteClicked(visitId, visitName) {
		var choose = confirm("<spring:message code='confirmToDelete'/> 「"+visitName+"」?");
		
		if (choose) {
			$.ajax({
				url : '${pageContext.request.contextPath}/visit/delete/',
				data : {visitId: visitId},
				type : "POST",
				dataType : 'json',
				async: false,
				success : function(resp) {
					if (resp.code == '200') {
						alert("<spring:message code='success.delete'/>");
						//重新查詢
						window.location.href = '${pageContext.request.contextPath}/visit/tour';

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
	
	function editCustInfo(visitId) {
		if (tblMain_VisitDetail) {
			tblMain_VisitDetail.destroy();	//重查資料
		} 
		
		tblMain_VisitDetail = $('#tblMain_VisitDetail').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/visit/getVisitDetails',
				"type" : 'POST',
				"data" : {
					'visitId' : visitId
				}
			},
			"drawCallback": function( settings ) {
				$('#modal_Edit_VisitDetail').modal();
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
								return '<input type="text" style="width:100px" name="visaStatus" value="' + row['visaStatus'] + '"/>';
							}
				},
				{
					"targets" : [3],
					"render" : function(data, type, row) {
								return '<input type="text" style="width:100px" name="accommodationSituation" value="' + row['accommodationSituation'] + '"/>';
							}
				},
				{
					"targets" : [4],
					"render" : function(data, type, row) {
								return '<input type="text" style="width:100px" name="amountReceived" value="' + row['amountReceived'] + '"/>';
							}
				},
				{
					"targets" : [5],
					"render" : function(data, type, row) {
								return '<input type="text" style="width:150px" name="remark" value="' + row['remark'] + '"/>';
							}
				},
			],
			select: true
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