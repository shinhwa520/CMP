<%@ include file="../../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><a href="${pageContext.request.contextPath}/channel/user/list"><spring:message code="myChannels"/></a>
		/<spring:message code="reward"/>
		&nbsp;＞&nbsp;<span class="badge badge-info"><spring:message code="channel"/>&nbsp;:&nbsp; ${ userName }</span></h3>
		
		<button type="button" class="btn btn-success pull-right" id="btnPermissionSave" onclick="btnSaveClicked();"><spring:message code="save"/></button>
		<span class="pull-right">&nbsp;</span>
		<button type="button" class="btn label-warning pull-right" id="oneClickClicked" onclick="oneClickClicked();"><spring:message code="replaceBydefault"/></button>
		<span class="pull-right">&nbsp;</span>	
		<input id="defaultCommission" class="pull-right" type="text" readonly value="${defaultCommission }" size="2"/>
		<span class="pull-right" style="margin-right: 1px;"><spring:message code="defaultCommission"/></span>
	</div>
	<div class="modal_msg pull-right" style="display: none; width: 100%;" align="center"></div>
	<div class="box-body no-padding">
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