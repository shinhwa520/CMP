<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
<br />
<div class="box box-primary">
	<div class="box-header with-border">
		<b><font style="font-size: 1.5em;"><spring:message code="shareResource"/></font></b>
	</div>
	<div class="box-body no-padding" >
		<table class="table table-striped" id="tblMain" width="100%">
			<thead>
				<tr>
					<th>#</th>
					<th><spring:message code="fileName"/></th>
					<th><spring:message code="fileSize"/></th>
					<th><spring:message code="downloadTimes"/></th>
					<th><spring:message code="fileDescription"/></th>
					<th><spring:message code="updatedTime"/></th>
					<th style="width: 100px;"><spring:message code="option"/></th>
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
								+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message javaScriptEscape="true" code="download"/></span></a>';
					}
				}
			],
			select: true
		});
	});

//[Download] 按下Download按鈕
function btnDownloadClicked(btn) {
	var downloadUrl = "${pageContext.request.contextPath}/manage/file/download?seqNo="+btn.attr('seqNo')+"&fileType="+btn.attr('fileType')+"&fromPage=share/file";
    window.location.href = downloadUrl;	
    
    setTimeout(function(){
  		if (tblMain) {
  			tblMain.ajax.reload();
  		}
  	}, 2000);
}
</script>