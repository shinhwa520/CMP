<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<%
	String monthDesc = request.getAttribute("monthDesc") != null ? request.getAttribute("monthDesc").toString() : "N/A";
	String kpiUserTarget = request.getAttribute("kpiUserTarget") != null ? request.getAttribute("kpiUserTarget").toString() : "0";
	String kpiUserActual = request.getAttribute("kpiUserActual") != null ? request.getAttribute("kpiUserActual").toString() : "0";
	String kpiVisitTarget = request.getAttribute("kpiVisitTarget") != null ? request.getAttribute("kpiVisitTarget").toString() : "0";
	String kpiVisitActual = request.getAttribute("kpiVisitActual") != null ? request.getAttribute("kpiVisitActual").toString() : "0";
	String kpiVolumeTarget = request.getAttribute("kpiVolumeTarget") != null ? request.getAttribute("kpiVolumeTarget").toString() : "0";
	String kpiVolumeActual = request.getAttribute("kpiVolumeActual") != null ? request.getAttribute("kpiVolumeActual").toString() : "0";
	String maxKpiUser = request.getAttribute("maxKpiUser") != null ? request.getAttribute("maxKpiUser").toString() : "0";
	String maxVisit = request.getAttribute("maxVisit") != null ? request.getAttribute("maxVisit").toString() : "0";
	String maxVolume = request.getAttribute("maxVolume") != null ? request.getAttribute("maxVolume").toString() : "0";
	String custMonthWord = request.getAttribute("custMonthWord") != null ? request.getAttribute("custMonthWord").toString() : "N/A";
	String custCountPerMonth = request.getAttribute("custCountPerMonth") != null ? request.getAttribute("custCountPerMonth").toString() : "0";
	String maxCustCount = request.getAttribute("maxCustCount") != null ? request.getAttribute("maxCustCount").toString() : "0";
	String openVisitCount = request.getAttribute("openVisitCount") != null ? request.getAttribute("openVisitCount").toString() : "0";
	String openSalonCount = request.getAttribute("openSalonCount") != null ? request.getAttribute("openSalonCount").toString() : "0";
%>
<script type="text/javascript">
    $(function() {
    	"use strict";
    	
        $('.grid-stack').gridstack({
            width: 12,
            alwaysShowResizeHandle: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent),
            resizable: {
                handles: 'e, se, s, sw, w'
            }
        });
        
        
     	// ============================================================== 
        // Download count - KPI渠道商量
        // ============================================================== 
        new Chartist.Bar('.kpi_user', {
            labels: [<%=monthDesc %>]	
            , series: [
            [<%=kpiUserTarget %>]
            , [<%=kpiUserActual %>]
          ]
        }, {
            high: <%=maxKpiUser %>
            , low: 0
            , showArea: true
            , seriesBarDistance: 10
            , fullWidth: true
            , plugins: [
            Chartist.plugins.tooltip()
          ]
            , axisX: {
                // On the x-axis start means top and end means bottom
                showGrid: true
            }
        , }, {});
        
     	// ============================================================== 
        // Download count - KPI成團量
        // ============================================================== 
        new Chartist.Bar('.kpi_visit', {
        	labels: [<%=monthDesc %>]
            , series: [
           	[<%=kpiVisitTarget %>]
            , [<%=kpiVisitActual %>]
          ]
        }, {
            high: <%=maxVisit %>
            , low: 0
            , showArea: true
            , seriesBarDistance: 10
            , fullWidth: true
            , plugins: [
            Chartist.plugins.tooltip()
          ]
            , axisX: {
                // On the x-axis start means top and end means bottom
                showGrid: true
            }
        , }, {});
        
     	// ============================================================== 
        // Download count - KPI成交量
        // ============================================================== 
        new Chartist.Bar('.kpi_volume', {
        	labels: [<%=monthDesc %>]
            , series: [
        	[<%=kpiVolumeTarget %>]
            , [<%=kpiVolumeActual %>]
          ]
        }, {
            high: <%=maxVolume %>
            , low: 0
            , showArea: true
            , seriesBarDistance: 10
            , fullWidth: true
            , plugins: [
            Chartist.plugins.tooltip()
          ]
            , axisX: {
                // On the x-axis start means top and end means bottom
                showGrid: true
            }
        , }, {});
     	
     	// ============================================================== 
        // User analytics - 客戶成長圖
        // ============================================================== 
        new Chartist.Line('.user-analytics', {
            labels: [<%=custMonthWord %>]
            , series: [
            [<%=custCountPerMonth %>]

          ]
        }, {
            high: <%=maxCustCount %>
            , low: 0
            , showArea: true
            , lineSmooth: Chartist.Interpolation.simple({
                divisor: 10
            })
            , fullWidth: true
            , chartPadding: 25
            , plugins: [
            Chartist.plugins.tooltip()
          ], // As this is axis specific we need to tell Chartist to use whole numbers only on the concerned axis
            axisY: {
                onlyInteger: true
                , offset: 20
                , labelInterpolationFnc: function (value) {
                    return (value / 1);
                }
            }
        , }, {});
        
    });
 	
 	function gotoVisit() {
 		var url = '${pageContext.request.contextPath}/visit/tour/23';
 		window.location.href = url;
 	}
 	
 	function gotoSalon() {
 		var url = '${pageContext.request.contextPath}/salon/list/23';
 		window.location.href = url;
 	}
 	
</script>

<section class="content">

	<!-- Row -->
	<div class="row">
	    <div class="col-12">
	        <div class="card">
	            <div class="card-body">
	                <div class="grid-stack" data-gs-width="12" data-gs-animate="yes">
	                    <div class="grid-stack-item" data-gs-x="0" data-gs-y="0" data-gs-width="6" data-gs-height="3">
	                        <div class="grid-stack-item-content">
	                        	<ul class="list-inline pull-right">
                                    <li><h6 class="text-muted"><i class="fa fa-circle m-r-5 text-primary"></i><spring:message code="dashboard_target"/></h6> </li>
                                    <li><h6 class="text-muted"><i class="fa fa-circle m-r-5 text-success"></i><spring:message code="dashboard_actual"/></h6> </li>
                                </ul>
                                <h3 class="card-title">KPI-<spring:message code="targetChannelsNo"/></h3>
	                        	<div class="download-state chartist-chart kpi_user" style="height:160px"></div>
	                        </div>
	                    </div>
	                    <div class="grid-stack-item" data-gs-x="6" data-gs-y="0" data-gs-width="6" data-gs-height="3">
	                        <div class="grid-stack-item-content">
	                        	<ul class="list-inline pull-right">
                                    <li><h6 class="text-muted"><i class="fa fa-circle m-r-5 text-primary"></i><spring:message code="dashboard_target"/></h6> </li>
                                    <li><h6 class="text-muted"><i class="fa fa-circle m-r-5 text-success"></i><spring:message code="dashboard_actual"/></h6> </li>
                                </ul>
                                <h3 class="card-title">KPI-<spring:message code="targetTourNo"/></h3>
	                        	<div class="download-state chartist-chart kpi_visit" style="height:160px"></div>
	                        </div>
	                    </div>
	                    <div class="grid-stack-item" data-gs-x="0" data-gs-y="3" data-gs-width="6" data-gs-height="5.5">
	                        <div class="grid-stack-item-content">
	                        	<ul class="list-inline pull-right">
                                    <li><h6 class="text-muted"><i class="fa fa-circle m-r-5 text-primary"></i><spring:message code="dashboard_target"/></h6> </li>
                                    <li><h6 class="text-muted"><i class="fa fa-circle m-r-5 text-success"></i><spring:message code="dashboard_actual"/></h6> </li>
                                </ul>
                                <h3 class="card-title">KPI-<spring:message code="targetSalesNo"/></h3>
	                        	<div class="download-state chartist-chart kpi_volume" style="height:320px"></div>
	                        </div>
	                    </div>
	                    <div class="grid-stack-item" data-gs-x="6" data-gs-y="3" data-gs-width="6" data-gs-height="3">
	                        <div class="grid-stack-item-content">
                                <h3 class="card-title"><spring:message code="dashboard_cust_growth_curve"/></h3>
                                <div class="user-analytics chartist-chart" style="height:160px"></div>
                            </div>
	                    </div>
	                    <div class="grid-stack-item" data-gs-x="6" data-gs-y="6" data-gs-width="3" data-gs-height="2">
	                        <div class="grid-stack-item-content">
	                        	<h3 class="card-title"><spring:message code="registration"/>-<spring:message code="visitIng"/></h3>
	                        	<div class="text-primary"><font size="30px"><b>
	                        		<a href="#" onClick="gotoVisit()"><%=openVisitCount %></a>
	                        	</b></font></div>
	                        </div>
	                    </div>
	                    <div class="grid-stack-item" data-gs-x="9" data-gs-y="6" data-gs-width="3" data-gs-height="2">
	                        <div class="grid-stack-item-content">
								<h3 class="card-title"><spring:message code="salon"/>-<spring:message code="salonIng"/></h3>
								<div class="text-primary"><font size="30px"><b>
									<a href="#" onClick="gotoSalon()"><%=openSalonCount %></a>
								</b></font></div>
	                        	</div>
							</div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- Row -->

</section>


