<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/laydate/laydate.js"></script>

<SCRIPT type="text/javascript">
	$(function() {
	    //+展開 -收合
	    $("a").click(function() { 
	    	var _this= $(this).attr("href"); 
	    	if($(_this).css("display")=="none"){
	      		$(_this).slideDown();
	    	}else{
	       		$(_this).slideUp();
	    	} 
	  	 	return false; 
	 	}); 
	    
	    $('#delChkAll').click(function () {
		    $(':checkbox.delChkbox').prop('checked', this.checked);    
		});
	});
	
	function processData() {
	   // getting data
	   var data = CKEDITOR.instances.content.getData();
	   
	   BillboardForm.action = "${pageContext.request.contextPath}/manage/billboard/modify";
	   BillboardForm.submit();
  	}
	
	function doDelete() {
		BillboardForm.action = "${pageContext.request.contextPath}/manage/billboard/delete";
		BillboardForm.submit();
	}
	
</SCRIPT>

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
	    padding: 0.75rem;
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
<form:form method="POST" modelAttribute="BillboardForm" action="">

	<div class="box-body" id="billboard">
		<a href="#" onclick="btnAddClicked();"><span class="label label-success pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i>Add</span></a>
		<c:if test="${!BillboardForm.billboardList.isEmpty() }">
			<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#000000" class="table table-striped">
				<tr bgcolor="#CCCCCC">  <!--(主)標題 -->
				 	<th width="5%">#</th>
				   	<th width="70%">標題</th>
				    <th width="15%">公告時間</th>
				    <th width="5%">編輯</th>
				    <th width="5%">
				    	<input type="checkbox" id="delChkAll" onClick="doChkAll()" value="${vo.seqNo }" /> 全選
				    </th>
				</tr>
				<c:forEach var="vo" items="${ BillboardForm.billboardList }" varStatus="loop">
					<tr>  <!--(主)標題 -->
					 	<td width="5%">#${loop.count }</td>
					   	<td width="70%">
					   		<a href="#bill_${loop.count }">${vo.title }</a>
					   		<c:if test="${ BillboardForm.adminRole }">
					   			&nbsp;&nbsp;&nbsp;&nbsp;(生效期間: ${vo.beginDateStr } ${vo.beginTimeStr } ~ ${vo.endDateStr } ${vo.endTimeStr })
					   		</c:if>
					   	</td>
					    <td width="15%">${vo.updateTime }</td>
					    <td width="5%">
					    	<a href="#"><span class="label label-warning" style="width:70px; margin-right:10px" seqNo="${vo.seqNo }" onclick="btnEditClicked($(this));"><i class="fa fa-close" style="margin-right:5px"></i>Edit</span></a>
					    </td>
					    <td width="5%"><input type="checkbox" name="delChkbox" class="delChkbox" value="${vo.seqNo }"/></td>
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
					<tr>  <!--(主)標題 -->
					 	<td colspan="4">&nbsp;</td>
					   	<td>
					   		<a href="#" onclick="doDelete();"><span class="label label-info pull-right" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i>Delete</span></a>
					   	</td>
					</tr>
			</table>
		</c:if>
	</div>

</form:form>
<script>
	laydate.render({
		elem: '#date-input1' //指定元素
	   ,lang: 'en'
	});
	laydate.render({
		elem: '#date-input2' //指定元素
	   ,lang: 'en'
	});
	laydate.render({
	  	elem: '#time-input1'
	   ,type: 'time'
	   ,lang: 'en'
	}); 
	laydate.render({
	    elem: '#time-input2'
	   ,type: 'time'
	   ,lang: 'en'
	}); 
</script>

<!--.燈箱 Edit -->         
<div class="modal fade bs-example-modal-lg" id="modal_Edit" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">Edit</h4>
      	</div>
      	<div class="modal-body">                    
            <form role="form" id="formEdit" name="formEdit">
            	<input type="hidden" name="seqNo" id="seqNo" value="" />
	            <div class="box-body">
	            	<div class="form-group">
	                  <label for="title"><span class="pull-right" style="color: red;">＊ </span>標題</label>
	                  <input type="text" class="form-control" id="title" name="title" placeholder="輸入標題">
	                </div>                              
	            </div>                         
	            <div class="box-body">
	                <div class="form-group">
						<label>是否置頂</label>
						<span style="position: relative; ">					 <input type="radio" name="onTopChkbox" id="onTop_Yes" value="Y"  /> Y</span>
						<span style="position: relative; margin-left: 10px;"><input type="radio" name="onTopChkbox" id="onTop_No" value="N" /> N</span>
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
						<label>預設展開</label>
						<span style="position: relative; ">					 <input type="radio" name="isOpenedChkbox" id="isOpened_Yes" value="Y"  /> Y</span>
						<span style="position: relative; margin-left: 10px;"><input type="radio" name="isOpenedChkbox" id="isOpened_No" value="N" /> N</span>
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="beginTime">發佈時間</label>
	                  	<input id="date-input1" type="text" style="width: 90px;" name="beginDateStr" placeholder="年-月-日" />
		  				<input id="time-input1" type="text" style="width: 60px;" name="beginTimeStr" placeholder="時:分:秒" />
		  				<span style="color: red;">(留白表示永久有效；日期有選、時間未選則預設為當日00:00:00)</span>
	                </div>                              
	            </div>  
	            <div class="box-body">
	                <div class="form-group">
	                  <label for="beginTime">結束時間</label>
	                  	<input id="date-input2" type="text" style="width: 90px;" name="endDateStr" placeholder="年-月-日" />
		  				<input id="time-input2" type="text" style="width: 60px;" name="endTimeStr" placeholder="時:分:秒" />
		  				<span style="color: red;">(留白表示永久有效；日期有選、時間未選則預設為當日00:00:00)</span>
	                </div>                              
	            </div>
	            <div class="box-body">
	                <div class="form-group">
						<textarea name="contentTxt" id="contentTxt" rows="10" cols="80"></textarea>
	                </div>                              
	            </div>
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        		<button type="button" class="btn btn-primary" id="btnProfileSave" onclick="btnGustSaveClicked();">Save</button>
				</div>
			</form>
		</div>	
	</div><!-- /.modal-content -->
  </div>
</div>

<script>
	CKEDITOR.replace( 'contentTxt', {});
</script>
						
<!--/.燈箱 Edit -->
<script>
var tblMain;
var formAction;


//[Add] 進入modal_Edit編輯
function btnAddClicked() {
	formAction = 'modify';
	$('#seqNo').val('');
	$('#title').val('');
	$('input[name="onTopChkbox"]')[1].checked = true;
	$('input[name="isOpenedChkbox"]')[1].checked = true;
	$('#date-input1').val('');
	$('#time-input1').val('');
	$('#date-input2').val('');
	$('#time-input2').val('');
	CKEDITOR.instances.contentTxt.setData('');
	$('#contentTxt').val('');
	
	$('#modal_Edit').modal();
}
//[Edit] 進入modal_Edit編輯
function btnEditClicked(btn) {
	formAction = 'getBillboard';
	console.log(btn.attr('seqNo'));
	$.ajax({
			url : '${pageContext.request.contextPath}/manage/billboard/' + formAction,
			data : {seqNo: btn.attr('seqNo')},
			type : "POST",
			dataType : 'json',
			async: false,
			success : function(resp) {
				console.log(resp);				
				if (resp.code == "200") {
					$('#seqNo').val(btn.attr('seqNo'));
					$('#title').val(resp.data.billboard.title);
					
					if (resp.data.billboard.onTopChkbox == "Y") {
						$('input[name="onTopChkbox"]')[0].checked = true;
					} else {
						$('input[name="onTopChkbox"]')[1].checked = true;
					}
					
					if (resp.data.billboard.isOpenedChkbox == "Y") {
						$('input[name="isOpenedChkbox"]')[0].checked = true;
					} else {
						$('input[name="isOpenedChkbox"]')[1].checked = true;
					}
					
					$('#date-input1').val(resp.data.billboard.beginDateStr);
					$('#time-input1').val(resp.data.billboard.beginTimeStr);
					$('#date-input2').val(resp.data.billboard.endDateStr);
					$('#time-input2').val(resp.data.billboard.endTimeStr);
					CKEDITOR.instances.contentTxt.setData(resp.data.billboard.content);
					$('#modal_Edit').modal();
				} else {
					alert(resp.message);
				}
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
}

//[Save] modal_Edit >>按下Save 儲存
function btnGustSaveClicked() {
	formAction = 'modify';
	CKEDITOR.instances.contentTxt.updateElement();
	$.ajax({
		url : '${pageContext.request.contextPath}/manage/billboard/' + formAction,
		data : $('#formEdit').serialize(),
		type : "POST",
		dataType : 'json',
		async: false,
		success : function(resp) {
			console.log(resp);
			
			if (resp.code) {
				alert(resp.message);
				$('#modal_Edit').modal('hide');
				
				window.location.replace("${pageContext.request.contextPath}/manage/billboard/");
			} else {
				alert(resp);
			}
		},

		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}
</script>

</section>