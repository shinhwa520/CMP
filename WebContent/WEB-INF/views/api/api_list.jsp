<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Api info.</h3>
		<table>
			<tr>
				<td>網站名稱:&nbsp;</td>
				<td>
					<select id="webName" name="webName">
						<option value="">=== 請選擇 ===</option>
						<option value="MAKA">MAKA</option>
					</select>
				</td>
				<td>
					<input type="button" name="query" value="查詢" onclick="doQuery()" />
				</td>
			</tr>
		</table>
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
	var editor; // use a global for the submit and return data rendering in the examples
	
	$(document).ready(function() {
	    editor = new $.fn.dataTable.Editor( {
	        "ajax": "../php/checkbox.php",
	        "table": "#tblLog",
	        "fields": [ {
	                label:     "操作:",
	                name:      "active",
	                type:      "checkbox",
	                separator: "|",
	                options:   [
	                    { label: '', value: 1 }
	                ]
	            }, {
	                label: "網站名稱:",
	                name:  "web_name"
	            }, {
	                label: "#:",
	                name:  "seq_no"
	            }, {
	                label: "MAKA ID:",
	                name:  "maka_id"
	            }, {
	                label: "H5描述:",
	                name:  "description"
	            }
	        ]
	    } );
	} );
	
	//$(document).ready(function() {
	function doQuery() {
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
			buttons: [
	            { extend: "create", editor: editor },
	            { extend: "edit",   editor: editor },
	            { extend: "remove", editor: editor }
	        ],
	        order: [[ 1, 'asc' ]],
	        ajax : {
				url : '${pageContext.request.contextPath}/api/getApiData.json',
				type : 'POST',
				data : function(d) {
					d.webName = $("#webName").val();
				}
			},
			columns : [
				{ data : "webName" },
				{ data : "detailSeqNo" },
				{ data : "makaId" },
				{ data : "detailDescription" },
				{
	                data:   "userActive",
	                render: function ( data, type, row ) {
	                    if ( data == 'Y' ) {
	                        return '<input type="checkbox" class="editor-active">';
	                    }
	                    return 'N/A';
	                },
	                className: "dt-body-center"
	            }
			]
	    } );
	}
	//} );
</script>