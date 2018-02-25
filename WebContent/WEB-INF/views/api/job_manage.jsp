<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<script type="text/javascript" src="/cmp/dwr/engine.js"></script>
<script type='text/javascript' src='/cmp/dwr/interface/MsgDwr.js'></script>
<script type="text/javascript">

	function addJob() {
		JobForm.action = "${pageContext.request.contextPath}/job/add";
		JobForm.submit();
	}
	
	function pauseJob() {
		JobForm.action = "${pageContext.request.contextPath}/job/pause";
		JobForm.submit();
	}
	
	function resumeJob() {
		JobForm.action = "${pageContext.request.contextPath}/job/resume";
		JobForm.submit();
	}
	
	function deleteJob() {
		JobForm.action = "${pageContext.request.contextPath}/job/delete";
		JobForm.submit();
	}
	
</script>

<section class="content">
Job management

	<form:form method="POST" modelAttribute="JobForm" action="" name="JobForm">

		<input type="button" name="addJobBtn" value="新增JOB" onClick="addJob()" />
		<input type="button" name="addJobBtn" value="暫停JOB" onClick="pauseJob()" />
		<input type="button" name="addJobBtn" value="重啟JOB" onClick="resumeJob()" />
		<input type="button" name="addJobBtn" value="刪除JOB" onClick="deleteJob()" />
	
	</form:form>

<h2>${message}</h2>
</section>