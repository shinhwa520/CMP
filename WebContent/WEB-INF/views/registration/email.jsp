<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic">please fill in your email and press confirm :</div>
	<form:form method="POST" modelAttribute="EmailConfirmForm" action="${pageContext.request.contextPath}/registration/emailConfirm">
		<form:input class="form-control" path="mailAddress" id="mailAddress" placeholder="Email"/>
		<form:errors class="form-control" path="mailAddress" cssClass="error" />
		<input class="btn btn-lg btn-success btn-block" value="Confirm" type="submit">
	</form:form>
</section>