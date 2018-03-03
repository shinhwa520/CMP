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
<div class="box box-primary" id="billboard" style="padding:15px 15px;">
	<b><font style="font-size: 1.5em;">最新公告</font>	</b>
	<div style="height:75vh; overflow-y:auto; ">
		<c:if test="${!IndexForm.billboardList.isEmpty() }">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table table-striped">
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
</section>
