<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Api info.</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblLog" width="100%">
			<thead>
				<tr>
					<th style="width: 10%">網站名稱</th>
					<th style="width: 22%">#</th>
					<th style="width: 22%">MAKA ID</th>
					<th style="width: 22%">H5描述</th>
					<th style="width: 22%">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
</section>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
	    $('#tblLog').DataTable( {
	        columnDefs: [ {
	            orderable: false,
	            className: 'select-checkbox',
	            targets:   0
	        } ],
	        select: {
	            style:    'os',
	            selector: 'td:first-child'
	        },
	        order: [[ 1, 'asc' ]],
	        ajax : {
				url : '${pageContext.request.contextPath}/api/getApiData.json',
				type : 'GET',
				data : function(d) {
				}
			},
			columns : [
				{ data : "webName" },
				{ data : "detailSeqNo" },
				{ data : "makaId" },
				{ data : "detailDescription" }
			]
	    } );
	} );
</script>