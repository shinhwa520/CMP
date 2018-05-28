<%@ include file="../../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><a href="${pageContext.request.contextPath}/channel/user/list"><spring:message code="myChannels"/></a>
		/<spring:message code="reward"/>
		&nbsp;＞&nbsp;<span class="badge badge-info" id="guide_step1_tag"><spring:message code="channel"/>&nbsp;:&nbsp; ${ userName }</span></h3>
		
		<button type="button" class="btn btn-success pull-right" id="btnPermissionSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
		<span class="pull-right">&nbsp;</span>
		<button type="button" class="btn label-warning pull-right" id="oneClickClicked" onclick="oneClickClicked();"><spring:message code="replaceBydefault"/></button>
		<span class="pull-right">&nbsp;</span>	
		<input id="defaultCommission" class="pull-right" type="text" readonly value="${defaultCommission }" size="2"/>
		<span class="pull-right" style="margin-right: 1px;"><spring:message code="defaultCommission"/></span>
	</div>
	<div class="modal_msg pull-right" style="display: none; width: 100%;" align="center"></div>
	<div class="box-body no-padding" id="guide_step2_tag">
		<form role="form" id="formEdit" name="formEdit">
		<input type="hidden" name="user_id" id="user_id" value="${userId }" />
		<table class="table table-striped" id="tblLog">
			<c:if test="${!commissionList.isEmpty() }">
				<c:forEach var="vo" items="${ commissionList }">
					<tr>
						<td>
							<div class="box-body">
					        	<div class="form-group">
									<label for="productName"><spring:message code="productInfo"/></label>
									<input type="text" readonly class="form-control" value="${vo.productInfo.productName }"/>
					            </div>                              
					        </div>
					        
						</td>
						<td>
							<div class="box-body">
					        	<div class="form-group">
									<label for="commissionPercent"><spring:message code="reward"/>%</label>
									<input type="text" id=${vo.id } class="form-control commissionPercent" value="${vo.commissionPercent }"/>
					            </div>                              
					        </div>
					        
						</td>
					</tr>
					
				</c:forEach>
			</c:if>
		</table>
		</form>
	</div>
</div>
</section>

<script>
var ori, results;
$(function() {
	var getResult = function () {
	    var result = [];
	    $('.commissionPercent').each(function () {
	        var $this = $(this), id = $this.attr('id'), val = $this.val();
	        result.push(id+"-"+val);
	    });
	    return result;
	};
	ori = getResult().join(',');
});

function btnSaveClicked(input) {
	$('.form-group').removeClass('has-error');
	var _userId = $('#user_id').val();
	var hasError = false;

    $('.commissionPercent').each(function () {
        if(!validateInt($(this).val())){
        	$(this).parents('.form-group').addClass('has-error');
        	hasError = true;
        };
    });

	if(hasError){
		errorMessage('<spring:message javaScriptEscape="true" code="error.commissionMustNo"/>');
		return false;
	}

	if("_demoId"==_userId) {
		return false;
	}
	
	var getResult = function () {
	    var result = [];
	    $('.commissionPercent').each(function () {
	        var $this = $(this), id = $this.attr('id'), val = $this.val();
	        result.push(id+"-"+val);
	    });
	    return result;
	};
	results = getResult().join(',');
	
	$.ajax({
		url : '${pageContext.request.contextPath}/channel/user/updateCommission',
		data : {
			ori: ori,
			results: results
        },
		type : "POST",
		dataType : 'json',
		async: false,
		success : function(resp) {
			console.log(resp);
			if (resp.code == '200') {
				successMsgModal(resp.message);
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

function oneClickClicked() {
	var _defaultCommission = $('#defaultCommission').val();
    $('.commissionPercent').each(function () {
        $(this).val(_defaultCommission);
    });
}


function validateInt(input) {
	var regExp = /^\d+$/;
	return regExp.test(input);
}
</script>
<style>
td .box-body{ padding-bottom: 2px; }
td .box-body .form-group{ margin-bottom: 2px; }
.badge{ 
	font-size: 16px;
	vertical-align: text-top; 
	border-radius: 5px;
}
form .box-body .form-group label {
    width: 15%;
}

form .box-body .form-group .form-control{
	display: inline-block;
	width: 80%;

</style>

	<script>
	    var showGuide = '${showGuide}';
	    var isHome = '${active}';
	    var title_commission = '<spring:message code="guide_tour_msg_23"/>'; //『我的渠道/佣金』导览说明
	    $(document).ready(function(){
	    //function showGuideTour() {
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
						
						if (key != 5) {
							reString += "<div class='popover-navigation'><button class='btn btn-default' data-role='prev'>« <spring:message code='prev' /></button><span data-role='separator'>|</span><button class='btn btn-default' data-role='next'><spring:message code='next' /> »</button>";
						}
						
						reString += "<button class='btn float-right' onclick='doCloseGuide();' aria-hidden='true'><i class='fa fa-ban'></i> <spring:message code='noShowAgain' /></button><button class='btn btn-default float-right' data-role='end'><spring:message code='endTour' /></button></div></div>";
							
						return reString;
					}
				,
				steps: [
	    	  		{
	    	    		element: "#guide_step1_tag",
	    	    		title: title_commission + " - 1/5",
	    	    		content: '<spring:message code="guide_tour_msg_24"/>', //显示当前佣金检视画面为哪位渠道商
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step2_tag",
	    	    		title: title_commission + " - 2/5",
	    	    		content: '<spring:message code="guide_tour_msg_25"/>', //呈显所有产品当前佣金％数;右方输入框可直接调整数值
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#defaultCommission",
	    	    		title: title_commission + " - 3/5",
	    	    		content: '<spring:message code="guide_tour_msg_26"/>', //提供一次性调整所有产品佣金％数;在此输入％数
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#oneClickClicked",
	    	    		title: title_commission + " - 4/5",
	    	    		content: '<spring:message code="guide_tour_msg_27"/>', //点击「带入预设值」一次性调整所有产品佣金％数
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#btnPermissionSave",
	    	    		title: title_commission + " - 5/5",
	    	    		content: '<spring:message code="guide_tour_msg_21"/>', //点击「保存」按钮储存修改值
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#myClients-tag",
	    	    		title: '<spring:message code="guide_tour_msg_28"/>', //『我的客户』导览说明
	    	    		content: '<spring:message code="guide_tour_msg_9"/>', //请点击功能选单进入下一阶段导览
	    	    		animation: false
	    	    		/*,
	    	    		onHidden: function() {
	    	    			$('#myClients-menu').get(0).click();
	    	    			return (new jQuery.Deferred()).promise();
	    	    	    }
	    	    		*/
	    	  		}
	    		]});
	
	    	// Initialize the tour
	    	tour.init();
	
	    	// Start the tour
	    	tour.start();
	    	//tour.restart();
	      }
	    });
	    //}
	</script>