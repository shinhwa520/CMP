<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<script>
	$(function(){
	    //+展開 -收合
	    $(".slideOption").click(function(){ 
	    	var _this= $(this).attr("href"); 
	    	if($(_this).css("display")=="none"){
	      		$(_this).slideDown();
	    	}else{
	       		$(_this).slideUp();
	    	} 
	  	 	return false; 
	 	}); 
	});
</script>

<style>
	.table {
	    width: 100%;
	    max-width: 100%;
	    margin-bottom: 1rem;
	    background-color: transparent;
	}
	thead {
	    display: table-header-group;
	    vertical-align: middle;
	    border-color: inherit;
	}
	tr {
	    display: table-row;
	    vertical-align: inherit;
	    border-color: inherit;
	}
	.table thead th {
	    vertical-align: bottom;
	    border-bottom: 2px solid #c2cfd6;
	}
	.table th, .table td {
	    vertical-align: top;
	    border-top: 1px solid #c2cfd6;
	}
	/*儲存格單列變色*/
	.table-striped tbody tr:nth-of-type(odd) {
	    background-color: rgba(0, 0, 0, 0.05);
	}

</style>
 
<section class="content">
<br />
<div class="box box-primary" id="billboard" style="padding:5px 5px;">
	<b><font style="font-size: 1.5em;">最新公告</font>	</b>
	<div style="height:45vh; overflow-y:auto; ">
		<c:if test="${!IndexForm.billboardList.isEmpty() }">
			<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#000000" class="table table-striped">
				<c:forEach var="vo" items="${ IndexForm.billboardList }" varStatus="loop">
					<tr bgcolor="#CCCCCC">  <!--(主)標題 -->
					 	<th width="5%">#${loop.count }</th>
					   	<th width="80%">
					   		<a class="slideOption" href="#bill_${loop.count }">${vo.title }</a>
					   		<c:if test="${vo.onTopChkbox eq 'Y' }">
					   			<img src="${pageContext.request.contextPath}/resources/images/on_top.gif" />
					   		</c:if>
					   	</th>
					    <th width="15%">${vo.updateTime }</th>
					</tr>
					<tr>
					 	<td colspan="9" class="content" bgcolor="#FFFFFF">
					 		<c:choose>
	            				<c:when test="${vo.isOpenedChkbox eq 'Y'}">
	            					<div id="bill_${loop.count }"  style="display:inline;">  <!--控制細項顯示與隱藏 -->
								      	${vo.content }
								    </div>
	            				</c:when>
	            				<c:otherwise>
	            					<div id="bill_${loop.count }"  style="display:none;">  <!--控制細項顯示與隱藏 -->
								      	${vo.content }
								    </div>
	            				</c:otherwise>
	            			</c:choose>
					   </td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>
<br/>
<div class="box box-primary">
	<div class="box-header with-border">
		<b><font style="font-size: 1.5em;">共享檔案</font></b>
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
				"url" : '${pageContext.request.contextPath}/manage/file/getAllPublicFiles.json',
				"type" : 'GET',
				"data" : function(d) {
					d.isAdmin = false
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
	var downloadUrl = "${pageContext.request.contextPath}/manage/file/download?seqNo="+btn.attr('seqNo')+"&fileType="+btn.attr('fileType');
    window.location.href = downloadUrl;
}
</script>