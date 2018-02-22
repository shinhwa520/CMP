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
					<th>option</th>
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
				"url" : '${pageContext.request.contextPath}/admin/user/getUser4Admin.json',
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
				{ "data" : "status.name" },
				{ "data" : "phone" },
				{ "data" : "email" },
				{ "data" : "channel" }
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
				},
				{
					"targets" : 8,
					"data" : 'id',
					"render" : function(data, type, row) {
						return '<a href="${pageContext.request.contextPath}/admin/user/cust?userId='+row['id']+'">' 
								+ '<span class="label label-info" style="margin-right:10px" userId="' + row['id'] + '" ">' 
								+ '<i class="fa fa-pencil" style="margin-right:5px"></i>view cust.</span></a>'
								+'<a href="#">'
								+'<span class="label label-warning" style="margin-right:10px" userId="' + row['id'] + '" onclick="btnEditClicked($(this));">'
								+'<i class="fa fa-close" style="margin-right:5px"></i>Edit</span></a>';
					}
				}
			],
			select: true
		});
	});
</script>