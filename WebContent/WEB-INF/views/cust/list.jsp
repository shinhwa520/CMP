<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Cust info.</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblLog">
			<thead>
				<tr>
					<th style="width: 5%">＃</th>
					<th style="width: 25%">name</th>
					<th style="width: 15%">city</th>
					<th style="width: 11%">phone</th>
					<th style="width: 14%">create time</th>
					<th style="width: 8%">create by</th>
					<th style="width: 14%">update time</th>
					<th style="width: 8%">update by</th>
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
				"url" : '${pageContext.request.contextPath}/cust/getCust4Admin.json',
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
				{ "data" : "createDateStr" },
				{ "data" : "createBy" },
				{ "data" : "updateDateStr" },
				{ "data" : "updateBy" }
			]
		});
	}); 
</script>