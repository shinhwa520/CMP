<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

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

	<div class="row page-titles">
	     <div class="col-md-6 col-8 align-self-center">
	         <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="newNotification" /></h3>
	     </div>
	 </div>
	 
	<div class="box box-primary" id="billboard" style="padding:15px 15px;">
		<div style="height:75vh; overflow-y:auto; ">
			<c:if test="${!IndexForm.billboardList.isEmpty() }">
				<table id="guide_step1_tag" class="table color-bordered-table info-bordered-table">
					<c:forEach var="vo" items="${ IndexForm.billboardList }" varStatus="loop">
						 <thead>
							<tr bgcolor="#CCCCCC">  <!--(主)標題 -->
							 	<th width="5%">#${loop.count }</th>
							   	<th width="75%" id="guide_step2_tag">
							   		<a class="slideOption" href="#bill_${loop.count }"><b><font color="white">${vo.title }</font></b></a>
							   		<c:if test="${vo.onTopChkbox eq 'Y' }">
							   			<img src="${pageContext.request.contextPath}/resources/images/on_top.gif" />
							   		</c:if>
							   	</th>
							    <th width="20%">${vo.updateTime }</th>
							</tr>
						 </thead>
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

	<script>
	    var showGuide = '${showGuide}';
	    var isHome = '${active}';
	    var title_billboard = '<spring:message code="guide_tour_msg_86"/>'; //『系统公告』导览说明
	    var title_common = '<spring:message code="guide_tour_msg_87"/>'; //『系统功能列』导览说明
	    $(document).ready(function(){
	      if("Y"===showGuide) {
	    	// Instance the tour
			var tour = new Tour({
				backdrop: true,
				smartPlacement: true,
				storage: false,
				template: 
					function (key, value) { 
						var reString = "<div class='popover tour' style='min-width:500px;  max-width:555px;'><div class='arrow'></div><h3 class='popover-title'></h3><div class='popover-content'></div>";
						
						if (key != 8) {
							reString += "<div class='popover-navigation'><button class='btn btn-default' data-role='prev'>« <spring:message code='prev' /></button><span data-role='separator'>|</span><button class='btn btn-default' data-role='next'><spring:message code='next' /> »</button>";
						}
						
						reString += "<button class='btn float-right' onclick='doCloseGuide();' aria-hidden='true'><i class='fa fa-ban'></i> <spring:message code='noShowAgain' /></button><button class='btn btn-default float-right' data-role='end'><spring:message code='endTour' /></button></div></div>";
							
						return reString;
					}
				,
				steps: [
	    	  		{
	    	    		element: "#guide_step1_tag",
	    	    		title: title_billboard + " - 1/2",
	    	    		content: '<spring:message code="guide_tour_msg_88"/>', //呈显当前系统公布栏清单
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step2_tag",
	    	    		title: title_billboard + " - 2/2",
	    	    		content: '<spring:message code="guide_tour_msg_89"/>', //点击标题文字可展开或收折明细内容
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
						element: "#menuIcon-tag",
	    	    		title: title_common + " - 1/7",
	    	    		content: '<spring:message code="guide_tour_msg_90"/>', //点击展开或收折左方选单
	    	    		placement: "bottom"
	    	  		},
					{
						element: "#sysMail-tag",
	    	    		title: title_common + " - 2/7",
	    	    		content: '<spring:message code="guide_tour_msg_91"/>', //点击检视系统通知信息
	    	    		placement: "bottom"
	    	  		},
					{
						element: "#mail-tag",
	    	    		title: title_common + " - 3/7",
	    	    		content: '<spring:message code="guide_tour_msg_92"/>', //点击检视站内信
	    	    		placement: "bottom"
	    	  		},
					{
						element: "#contact_us-tag",
	    	    		title: title_common + " - 4/7",
	    	    		content: '<spring:message code="guide_tour_msg_93"/>', //点击可联络系统管理员
	    	    		placement: "bottom"
	    	  		},
	    	  		{
						element: "#member-dropdown",
	    	    		title: title_common + " - 5/7",
	    	    		content: '<spring:message code="guide_tour_msg_94"/>', //点击检视个人基本资料及相关功能选单
	    	    		placement: "bottom"
	    	  		},
	    	  		{
						element: "#language-dropdown",
	    	    		title: title_common + " - 6/7",
	    	    		content: '<spring:message code="guide_tour_msg_95"/>', //点击可切换系统显示语言
	    	    		placement: "bottom"
	    	  		},
					{
						element: "#guide-dropdown",
	    	    		title: title_common + " - 7/7",
	    	    		content: '<spring:message code="guide_tour_msg_96"/>', //点击可查看系统功能导览说明
	    	    		placement: "bottom"
	    	  		}
	    		]});
	
	    	// Initialize the tour
	    	tour.init();
	
	    	// Start the tour
	    	tour.start();
	    	//tour.restart();
	      }
	    });
	</script>
