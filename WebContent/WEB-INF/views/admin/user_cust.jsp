<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><a href="${pageContext.request.contextPath}/admin/user/list"><spring:message code="user"/></a>/<spring:message code="customer"/></h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblLog">
			<thead>
				<tr>
					<th style="width: 10%">＃</th>
					<th style="width: 22%"><spring:message code="name"/></th>
					<th style="width: 22%"><spring:message code="city"/></th>
					<th style="width: 22%"><spring:message code="phoneNo"/></th>
					<th style="width: 22%"><spring:message code="user"/></th>
				</tr>
			</thead>
		</table>
	</div>
</div>
</section>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
<script>
var tblLog;
var userId = ${userId};

$(function() {
	tblLog = $('#tblLog').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/admin/user/getCustByUserId.json?userId='+userId,
				"type" : 'GET',
				"data" : function(d) {
					//d.customParam = 'testestert';
				}
			},
			"columns" : [
				{ "data" : "id" },
				{ "data" : "name" },
				{ "data" : "city" },
				{ "data" : "phone" },
				{ "data" : "user.id" }
			]
		});
	});
</script>