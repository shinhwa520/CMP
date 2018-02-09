<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Cust info.2</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblLog" style="width: 100%">
			<thead>
				<tr>
					<th>＃</th>
					<th>name</th>
					<th>city</th>
					<th>phone</th>
					<th>user</th>
				</tr>
			</thead>
		</table>
	</div>




</div>

</section>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.editor.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.select.min.js"></script>
<script>
var tblLog;
var editor;

$(function() {
    editor = new $.fn.dataTable.Editor({
        table: $('#tblLog'),
        idSrc:  'id',
        fields: [{
            label: "id:",
            name: "id"
        },
        {
            label: "name:",
            name: "name"
        }, {
            label: "city:",
            name: "city"
        }, {
            label: "phone:",
            name: "phone"
        }, {
            label: "user_id:",
            name: "user.id"
        }]
    } );
	tblLog = $('#tblLog').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"dom": 'Bfrtip',
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/admin/cust/getCust4Admin.json',
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
			],
			select: true,
			buttons: [
				{ extend: "create", editor: editor },
				{ extend: "edit",   editor: editor },
				{ extend: "remove", editor: editor }
			]
		});
	});
</script>