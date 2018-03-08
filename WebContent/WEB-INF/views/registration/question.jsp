<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content" style="padding-bottom: 50px;">
	<div class="topic"><spring:message code='ansewerQuestions'/></div>
	<form:form method="POST" modelAttribute="UserInfoForm">
		<form:hidden path="userId" id="userId" />
		<form:hidden path="ans" id="ans" />
		<table>
		<c:if test="${!UserInfoForm.quesMap.isEmpty() }">
			<c:forEach var="vo" items="${ UserInfoForm.quesMap }">
				
				<tr>
					<td style="vertical-align:text-top;"><span style="color: blue;">${vo.key.sort }.</span></td>
					<td><span style="color: blue;">${vo.key.content }</span></td>
				</tr>
					<c:forEach var="detail" items="${ vo.value }">
					<tr>
						<td style="vertical-align:text-top;" >
							<input type="radio" name=${vo.key.id } value=${detail.sort } id=${detail.id } src="${vo.key.sort }" >
						</td>
						<td><label class="radio_label" for=${detail.id } id="label_${detail.sort }" >${detail.content }</label></td>
					</tr>
					</c:forEach>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
			</c:forEach>
		</c:if>
		</table>
		<div><input class="btn btn-lg btn-success btn-block" type="button" name="submitBtn" value="<spring:message code='finished'/>" onclick="doSubmit()"/></div>
		
	</form:form>
</section>


<script type="text/javascript">
	var ans = $('#ans').val();
	var ansArray = ans.split(',');
	var itemCount = ${UserInfoForm.quesMap.keySet().size()};
	var results;
	var resultArray;
	var resultCount;
	
	function doSubmit() {
		
		$('.radio_label').removeClass('alert-danger');
		var _userId = $('#userId').val();
		var hasError = false;
		var getResult = function () {
		    var result = [];
		    $('input:radio').each(function () {
		        var $this = $(this), id = $this.attr('id'), val = $this.val(), index = $this.attr('src');
		        if ($(this).prop('checked')) {
		            result.push(val);
		            if(val!=ansArray[index-1]){
		            	hasError = true;
		            	$('#label_'+id).addClass('alert-danger');
			        }
		        }
		    });
		    return result;
		};
		results = getResult().join(',');
		resultCount = results.split(',').length;

		//先檢核是否全都回答
		if(''==results || resultCount<itemCount){
			if(hasError){
				errorMessage("<spring:message code='error.mustAllCheck'/><br><spring:message code='notAllCorrect'/>");
				return false;
			}else{
				errorMessage("<spring:message code='error.mustAllCheck'/>");
				return false;
			}

		}
		
		if(results != ans){
			errorMessage("<spring:message code='notAllCorrect'/>");
			return false;
		}

		
		window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/upstream?userId='+_userId;
		/*
		$.ajax({
				url : '${pageContext.request.contextPath}/registration/ans?id=' + _userId + '&results=' +results,
				type : "GET",
				dataType : 'json',
				async: false,
				contentType:"application/json;charset=utf-8", 
				success : function(data) {
                    if (data.status === 200) {
                    	window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/upstream?userId='+_userId;                        
                    } else {
                        errorMessage(data.message);
                    }
				},

				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}
			});
		*/
	}

</script>
