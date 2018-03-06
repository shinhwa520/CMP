<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic"><spring:message code="fillParentChannel"/></div>
	<form:form method="POST" modelAttribute="UserInfoForm" action="${pageContext.request.contextPath}/registration/agreement">
		<form:hidden path="userId" id="userId" />
		<form:input class="form-control" path="channelAccount" id="channelAccount" placeholder="Account"/>
		<form:errors class="form-control" path="channelAccount" cssClass="error" />
		<input class="btn btn-lg btn-success btn-block" value="<spring:message code='confirm'/>" type="submit">
	</form:form>
</section>
<script>

//不檢核, 暫定可不輸入
function validateInput() {
  	var channelAccount = $('#channelAccount').val();
  	if(''==channelAccount.trim()){
  		errorMessage("<spring:message code='fillParentChannel'/>");
	  	return false;
	}
}

</script>