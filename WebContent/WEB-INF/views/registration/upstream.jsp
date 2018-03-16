<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic"><spring:message code="fillParentChannel"/></div>
	<form:form method="POST" modelAttribute="UserInfoForm" action="${pageContext.request.contextPath}/registration/agreeAgreement">
		<form:hidden path="userId" id="userId" />
		<form:input class="form-control" path="channelAccount" id="channelAccount" placeholder="Account"/>
		<form:errors class="form-control" path="channelAccount" cssClass="error" />
		<input class="btn btn-lg btn-success btn-block" value="<spring:message code='confirm'/>" type="button" onclick="validateInput();">
	</form:form>
</section>
<script>
var msg = '${message}';
$(function() {
	if(''!=msg) {
		errorMessage(msg);
	}
});
//輸入檢核
function validateInput() {
  	var channelAccount = $('#channelAccount').val();
  	if(''==channelAccount.trim()){
  		errorMessage("<spring:message code='fillParentChannel'/>");
	  	return false;
	}

    $.ajax({
        url : '${pageContext.request.contextPath}/registration/agreeAgreement?userId=' + $('#userId').val()+'&channelAccount=' + channelAccount,
        type : "GET",
        dataType : 'json',
        async: false,
        contentType:"application/json;charset=utf-8",
        success : function(data) {
            if (data.status === 200) {
                alert("<spring:message code='gotMail'/>");
                setTimeout(function(){
                    window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/login';
                }, 2000);
            } else {
                alert();
                errorMessage("<spring:message code='error.noDataAutoChannel'/>");
            }
        },

        error : function(xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    });
}

</script>