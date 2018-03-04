<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><spring:message code="KPI" /></h3>
	</div>
	<div class="box-body no-padding">
        <div class="box-body">
        	<table style="width: 100%">
        		<tr>
        			<td style="width: 12%"><label><spring:message code="targetChannelsNo" /></label></td>
        			<td style="width: 38%">${expectedAgentUser}</td>
        			<td style="width: 12%"><label><spring:message code="accomplishedChannelsNo" /></label></td>
        			<td style="width: 38%">${actualAgentUser}</td>
        		</tr>
        		<tr>
        			<td style="width: 12%"><label><spring:message code="targetTourNo" /></label></td>
        			<td style="width: 38%">${expectedAgentCust}</td>
        			<td style="width: 12%"><label><spring:message code="accomplishedTourNo" /></label></td>
        			<td style="width: 38%">${actualAgentCust}</td>
        		</tr>
        		<tr>
        			<td style="width: 12%"><label><spring:message code="targetSalesNo" /></label></td>
        			<td style="width: 38%">${expectedAgentVolume}</td>
        			<td style="width: 12%"><label><spring:message code="accomplishedSalesNo" /></label></td>
        			<td style="width: 38%">${actualAgentVolume}</td>
        		</tr>
        		<tr>
        			<td colspan="4"><hr></td>
        		</tr>
        		<tr>
        			<td style="width: 12%"><label><spring:message code="reward" /></label></td>
        			<td style="width: 38%">${reward} %</td>
        			<td style="width: 12%">&nbsp;</td>
        			<td style="width: 38%">&nbsp;</td>
        		</tr>
        	</table>
        </div>
	</div>
	<div class="box-footer" align="center">
	    <a href="#" onclick="btnBackClicked()"><span class="label label-success" style="width:70px; padding:5px 10px 5px 10px; font-size: 95%;"> <i class="fa  fa-plus" ></i><spring:message code="goBack" /></span></a>
	</div>
</div>
</section>

<script type="text/javascript">

	//[btn_KPI]
	function btnBackClicked() {
		var url = "${pageContext.request.contextPath}/channel/productInfo/list";
		window.location.href = url;
	}
</script>
