<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Status info.</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblLog">
			<thead>
				<tr>
					<th>＃</th>
					<th>name</th>
					<th>type</th>
					<th>sort</th>
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


$(function() {
	tblLog = $('#tblLog').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/admin/status/getStatus4Admin.json',
				"type" : 'GET',
				"data" : function(d) {
					//d.customParam = 'testestert';
				}
			},
			"columns" : [
				{ "data" : "id" },
				{ "data" : "name" },
				{ "data" : "type" },
				{ "data" : "sort" }
			],
			select: true
		});
	});
</script>