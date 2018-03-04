<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic"><spring:message code="agreementTitle"/></div>
	<form:form method="POST" modelAttribute="UserInfoForm" action="${pageContext.request.contextPath}/registration/agreeAgreement">
		<form:hidden path="userId" id="userId" />
		<input class="btn btn-lg btn-info btn-block" type=button value="<spring:message code='partnerContract'/>" onclick="agreementPdf();" >
		<spring:message code="agreeContract"/>
		<input class="btn btn-lg btn-success btn-block" type="button" value="<spring:message code='agree'/>" onclick="agreeAgreement();" >
		<input class="btn btn-lg btn-danger btn-block" type="button" value="<spring:message code='disagree'/>" >
	</form:form>
</section>
<script>

var msg = '${message}';
$(function() {
	if(''!=msg) {
		errorMessage(msg);
	}
});
	function agreeAgreement() {
		$.ajax({
			url : '${pageContext.request.contextPath}/registration/agreeAgreement?userId=' + $('#userId').val(),
			type : "GET",
			dataType : 'json',
			async: false,
			contentType:"application/json;charset=utf-8", 
			success : function(data) {
                if (data.status === 200) {
                	successMessage(data.message);
        			setTimeout(function(){
        				window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/login';
        			}, 2000);
                } else {
                    errorMessage(data.message);
                }
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
	}

	function agreementPdf() {
		window.open("${pageContext.request.contextPath}/registration/agreementPdf");
	}
</script>