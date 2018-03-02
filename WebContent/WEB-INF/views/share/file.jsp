<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
<br />
<div class="box box-primary">
	<div class="box-header with-border">
		<b><font style="font-size: 1.5em;">共享資源</font></b>
	</div>
	<div class="box-body no-padding" >
		<table class="table table-striped" id="tblMain" width="100%">
			<thead>
				<tr>
					<th>#</th>
					<th>檔案名稱</th>
					<th>檔案大小</th>
					<th>下載次數</th>
					<th>檔案描述</th>
					<th>更新時間</th>
					<th style="width: 100px;">Option</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
</section>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
<script>
var tblMain;
var formAction = 'update';

Number.prototype.format = function(n, x) {
    var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
    return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
};

$(function() {
	tblMain = $('#tblMain').DataTable(
		{
			"bFilter" : false,
			"ordering" : false,
			"info" : false,
			"serverSide" : true,
			"bLengthChange" : false,
			"ajax" : {
				"url" : '${pageContext.request.contextPath}/share/file/getAllPublicFiles.json',
				"type" : 'GET',
				"data" : function(d) {
				}
			},
			"columns" : [
				{ "data" : "dataSeq" },
				{ "data" : "fullFileName" },
				{ "data" : "fileSize", "render": function ( data, type, full, meta ) {
												      return data.format() + ' KB';
											    } },
				{ "data" : "downloadTimes" },
				{ "data" : "fileDescription" },
				{ "data" : "updateTime" }
			],
			"columnDefs": [
				{
					"targets" : 6,
					"data" : 'seqNo',
					"render" : function(data, type, row) {
						return '<a href="#">'
								+'<span class="label label-success pull-center" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnDownloadClicked($(this));">'
								+'<i class="fa fa-close" style="margin-right:5px"></i>Download</span></a>';
					}
				}
			],
			select: true
		});
	});

//[Download] 按下Download按鈕
function btnDownloadClicked(btn) {
	var downloadUrl = "${pageContext.request.contextPath}/share/file/download?seqNo="+btn.attr('seqNo')+"&fileType="+btn.attr('fileType')+"&fromPage=share/file";
    window.location.href = downloadUrl;
}
</script>