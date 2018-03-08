<%@ include file="../../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><a href="${pageContext.request.contextPath}/channel/user/list"><spring:message code="myChannels"/></a>
		/<spring:message code="reward"/>
		&nbsp;＞<spring:message code="channel"/>:${ userName }</h3>
		
		
		<a href="#" onclick="btnSaveClicked();">
			<span class="label label-success pull-right" style="padding:5px 10px 5px 10px; font-size: 95%; margin-left: 10px;">
				<spring:message code="save"/>
			</span>
		</a>
		<a href="#" onclick="oneClickClicked();">
			<span class="label label-warning pull-right" style="padding:5px 10px 5px 10px; font-size: 95%; margin-left: 1px;">
				<spring:message code="oneClick"/>
			</span>
		</a>
		<input id="defaultCommission" class="pull-right" type="text" readonly="true" value="${defaultCommission }" size="2"/>
		<span class="pull-right" style="margin-right: 1px;"><spring:message code="defaultCommission"/></span>
	</div>
	<div class="box-body no-padding">
		<form role="form" id="formEdit" name="formEdit">
		<table class="table table-striped" id="tblLog">
			<c:if test="${!commissionList.isEmpty() }">
				<c:forEach var="vo" items="${ commissionList }">
					<tr>
						<td>
							<div class="box-body">
					        	<div class="form-group">
									<label for="productName"><spring:message code="productInfo"/></label>
									<input type="text" readonly="true" class="form-control" value="${vo.productInfo.productName }"/>
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
	var _userId = $('#userId').val();
	var hasError = false;
	
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
				successMessage(resp.message);
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

</style>