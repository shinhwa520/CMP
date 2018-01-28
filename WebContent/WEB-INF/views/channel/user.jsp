<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">我的渠道商</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblLog">
			<thead>
				<tr>
					<th>＃</th>
					<th>name</th>
					<th>status</th>
					<th>phone</th>
					<th>email</th>
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
				"url" : '${pageContext.request.contextPath}/channel_user/getUserByChannelId.json',
				"type" : 'GET',
				"data" : function(d) {
					//d.customParam = 'testestert';
				}
			},
			"columns" : [
				{ "data" : "id" },
				{ "data" : "name" },
				{ "data" : "status.id" },
				{ "data" : "phone" },
				{ "data" : "email" }
			],
			"columnDefs" : [ {
				"targets" : 5,
				"data" : 'id',
				"render" : function(data, type, row) {
					return '<a href="${pageContext.request.contextPath}/channel_user/cust?userId='+row['id']+'">' 
							+ '<span class="label label-info" style="margin-right:10px" userId="' + row['id'] + '" ">' 
							+ '<i class="fa fa-pencil" style="margin-right:5px"></i>view cust.</span></a>';
				}
			} ]
		});
	});
</script>