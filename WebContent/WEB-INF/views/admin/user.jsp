<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">User info.</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblLog">
			<thead>
				<tr>
					<th>＃</th>
					<th>name</th>
					<th>account</th>
					<th>password</th>
					<th>status</th>
					<th>phone</th>
					<th>email</th>
					<th>channel</th>
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
				"url" : '${pageContext.request.contextPath}/admin_user/getUser4Admin.json',
				"type" : 'GET',
				"data" : function(d) {
					//d.customParam = 'testestert';
				}
			},
			"columns" : [
				{ "data" : "id" },
				{ "data" : "name" },
				{ "data" : "account" },
				{ "data" : "password" },
				{ "data" : "status.id" },
				{ "data" : "phone" },
				{ "data" : "email" },
				{ "data" : "channel" },
			],
			"columnDefs": [
				{
					"targets": [7],
					"render": function (data, type, row) {
						var html = '';
						if (row["channel"] != null) {
							html += row["channel"].id;
						}
						
						return html;
					}
				}
			]
		});
	});
</script>