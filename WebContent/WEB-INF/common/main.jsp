<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>CMP</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<meta name="description" content="">
    <meta name="author" content="">
    
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
    
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/prism/prism.css" rel="stylesheet">
    <!-- wysihtml5 CSS -->
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/html5-editor/bootstrap-wysihtml5.css" rel="stylesheet" />
    <!-- Dropzone css -->
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/dropzone-master/dist/dropzone.css" rel="stylesheet" type="text/css" />
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="${pageContext.request.contextPath}/resources/css/colors/blue-dark.css" id="theme" rel="stylesheet"> 
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
    
    <!-- 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons/2.0.1/css/ionicons.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/step_arrow.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/skin-blue.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/iCheck/all.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/buttons.dataTables.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/editor.dataTables.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/select.dataTables.css"> -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/carouselSlider/css/jquery.jscrollpane.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/carouselSlider/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/agileCarousel/agile_carousel.css">
	<!-- 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/elastislide/css/demo.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/elastislide/css/elastislide.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/elastislide/css/custom.css"> 
	-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jqueryui/jquery-ui.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap-select/bootstrap-select.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-tour-0.12.0/css/bootstrap-tour-standalone.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/gridstack/gridstack.css">
	
	<!-- chartist CSS -->
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/chartist-js/dist/chartist.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/chartist-js/dist/chartist-init.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/css-chart/css-chart.css" rel="stylesheet">
	
	<!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <!-- <script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script> -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="${pageContext.request.contextPath}/resources/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="${pageContext.request.contextPath}/resources/js/sidebarmenu.js"></script>
    <!--stickey kit -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/html5-editor/wysihtml5-0.3.0.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/html5-editor/bootstrap-wysihtml5.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/dropzone-master/dist/dropzone.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/carouselSlider/js/jquery.contentcarousel.js"></script>
	<script src="${pageContext.request.contextPath}/resources/carouselSlider/js/jquery.easing.1.3.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap-tour-0.12.0/js/bootstrap-tour-standalone.min.js"></script>
	<!-- 
	<style type="text/css">
		.alert{
			margin-top: 40px;
			display:none;
		}
		.box-shadow-menu{
			padding-top: 11px;
			height: 38px; 
			width: 28px;
		}
		.label{
			font-size: 95%;
		}
		.box-body .form-group label{
			width: 10%;
		}
		.box-body .form-group .form-control{
			display: inline-block;
			width: 88%;
		}
		.content{
			padding-top: 0px;
			padding-bottom: 0px;
		}
		.box-body{
			padding-top: 0px;
		}

		.box.box-primary{
			margin-bottom: 0px;
		}

		.step-arrow li a{
			height: 33px;
		}
	</style>
	 -->
	 
	<style type="text/css">
	.hide-menu{
		display:inline-block;
	}
	inline-block
	#tab-guide .tab-pane{
		padding:10px;
	}
	
	#tab-guide .tab-pane .div_scroll .card{
		border:2px #ccc solid;
		padding:5px;
	}
	
	#tab-guide .tab-pane .div_scroll .card .card-body{
		padding-top: 0px;
		padding-bottom: 5px;
	}
	
	#tab-guide .tab-pane .div_scroll .card .card-body img{
		width: auto;
		max-height: 100%;  
		max-width: 100%;
		border:2px #ccc solid;
		padding:5px;
	}
	</style>
	<script>
		Date.prototype.Format = function(fmt) { //author: meizz
	
		    var o = {
		        "M+": this.getMonth() + 1,
		        //月份
	
		        "d+": this.getDate(),
		        //日
	
		        "h+": this.getHours(),
		        //小时
	
		        "m+": this.getMinutes(),
		        //分
	
		        "s+": this.getSeconds(),
		        //秒
	
		        "q+": Math.floor((this.getMonth() + 3) / 3),
		        //季度
	
		        "S": this.getMilliseconds() //毫秒
	
		    };
		    if (/(y+)/.test(fmt)) {
		        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		    }
		    for (var k in o) {
		        if (new RegExp("(" + k + ")").test(fmt)) {
		            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		        }
		    }
		    return fmt;
		}
		
		function successMessage(message) {
			var msg = $('#message');
			$(document).scrollTop(0);
			if(msg.hasClass('alert-danger')) msg.removeClass('alert-danger');
			if(!msg.hasClass('alert-info')) msg.addClass('alert-info');
			msg.html(message);
			msg.fadeIn();
			setTimeout(function(){
				msg.fadeOut();
			}, 2000);
		}
		
		function errorMessage(message) {
			var msg = $('#message');
			$(document).scrollTop(0);
			if(msg.hasClass('alert-info')) msg.removeClass('alert-info');
			if(!msg.hasClass('alert-danger')) msg.addClass('alert-danger');
			msg.html(message);
			
			msg.fadeIn();
			setTimeout(function(){
				msg.fadeOut();
			}, 2000);
		}

		//[successMsgModal.]
		function successMsgModal(message) {
			var msg = $('.modal_msg');
			$('#modal_Edit').animate({ scrollTop: 0 }, 'slow');
			if(msg.hasClass('alert-danger')) msg.removeClass('alert-danger');
			if(!msg.hasClass('alert-info')) msg.addClass('alert-info');
			msg.html(message);
			$(msg).fadeIn();
			setTimeout(function(){
				$(msg).fadeOut();
			}, 2000);
		}

		//[errorMsgModal.]
		function errorMsgModal(message) {
			var msg = $('.modal_msg');
			$('#modal_Edit').animate({ scrollTop: 0 }, 'slow');
			if(msg.hasClass('alert-info')) msg.removeClass('alert-info');
			if(!msg.hasClass('alert-danger')) msg.addClass('alert-danger');
			msg.html(message);
			$(msg).fadeIn();
			setTimeout(function(){
				$(msg).fadeOut();
			}, 2000);
		}

        function doChangeLang(lang) {
            var url = '${pageContext.request.contextPath}/changeLanguage?langType='+lang+'&refresh='+window.location.pathname;
            window.location.href = url;
        }

        function contactUs() {
            var _subject = $('#contactSubject').val();
            var _content = $('#contactContent').val();
        	$.ajax({
        		url : '${pageContext.request.contextPath}/contact/contactUs?subject='+_subject
        															+ '&content=' + _content,
        		type : "GET",
        		dataType : 'json',
        		async: false,
        		contentType:"application/json;charset=utf-8", 
        		success : function(data) {
                    if (data.code === 200) {
                    	alert('<spring:message code="thankFeedback" />');
                    	$('#contactUsIcon').trigger('click');
                    } else {
                    	alert(data.message);
                    }
        		},
        		error : function(xhr, ajaxOptions, thrownError) {
        			alert(xhr.status);
        			alert(thrownError);
        		}
        	});
        }

        function showTip(){
        	$("html, body").animate({ scrollTop: 0 }, "slow");
        	
        	$("#navigationBlock").css("display", "block");
        	$("#navigationTip").css("display", "block");
        }

        function hideTip(){
        	$("#navigationBlock").hide();
        	$("#navigationTip").hide();
        }

        function doCloseGuide() {
        	hideTip();
        	$.ajax({
        		url : '${pageContext.request.contextPath}/channel/user/doCloseGuide',
        		type : "GET",
        		dataType : 'json',
        		async: false,
        		contentType:"application/json;charset=utf-8", 
        		success : function(data) {
                    if (data.code === 200) {

                    } else {
                    	alert(data.message);
                    }
        		},
        		error : function(xhr, ajaxOptions, thrownError) {
        			alert(xhr.status);
        			alert(thrownError);
        		}
        	});
        }

	</script>
</head>

<body class="fix-header fix-sidebar card-no-border">

<div id="navigationBlock" style="width: 100%; height: 100%; left: 0px; top: 0px; z-index: 999; background-color: rgb(0, 0, 0); opacity: 0.55; position: fixed; display: none;"></div>
<div id="navigationTip"   style="width: 100%; height: 100%; left: 0px; top: 0px; z-index: 1005; background-color: transparent; position: absolute; display: none;">
	<div class="container-fluid" style="padding: 25px 100px;">
		<div class="row">
			<div class="col-md-12">
	            <div class="card">
	            	
	                <div class="card-body">
	                	<button class="btn btn-inverse float-right" onclick="hideTip();" aria-hidden="true"><i class="fa fa-times"></i> <spring:message code='discard'/></button>
	                	<!-- <button class="btn float-right" onclick="doCloseGuide();" aria-hidden="true"><i class="fa fa-ban"></i> 不再顯示</button> -->
	                    <h4 class="card-title">平台功能导览</h4>
	                    <h6 class="card-subtitle">Platform Features Guide </h6>
	                <!-- Nav tabs -->
		                <div class="vtabs customvtab">
		                    <ul class="nav nav-tabs tabs-vertical" role="tablist">
								<li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#guide3" role="tab"><i class="icon-info"></i> 
									<span class="hide-menu"><spring:message code="guide_guide_0" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#dashboard3" role="tab"><i class="mdi mdi-gauge"></i> 
									<span class="hide-menu"><spring:message code="dashboard" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#billboard3" role="tab"><i class="mdi mdi-content-paste"></i> 
									<span class="hide-menu"><spring:message code="billboard" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#visitInfo3" role="tab"><i class="mdi mdi-table"></i> 
									<span class="hide-menu"><spring:message code="visitInfo" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#registration3" role="tab"><i class="mdi mdi-book-multiple"></i> 
									<span class="hide-menu"><spring:message code="registration" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#salon3" role="tab"><i class="mdi mdi-coffee"></i> 
									<span class="hide-menu"><spring:message code="salon" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#productInfo3" role="tab"><i class="mdi mdi-file"></i> 
									<span class="hide-menu"><spring:message code="productInfo" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#referenceInfo3" role="tab"><i class="mdi mdi-file"></i> 
									<span class="hide-menu"><spring:message code="referenceInfo" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#myChannels3" role="tab"><i class="mdi mdi-book-open-variant"></i> 
									<span class="hide-menu"><spring:message code="myChannels" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#myClients3" role="tab"><i class="mdi mdi-face"></i> 
									<span class="hide-menu"><spring:message code="myClients" /></span></a> </li>
									
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#sysMail3" role="tab"><i class="mdi mdi-message"></i> 
									<span class="hide-menu"><spring:message code="guide_sysMail_0" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#mail3" role="tab"><i class="mdi mdi-email"></i> 
									<span class="hide-menu"><spring:message code="guide_mail_0" /></span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#contact_us3" role="tab"><i class="mdi mdi-view-grid"></i> 
									<span class="hide-menu"><spring:message code="guide_contact_us_0" /></span></a> </li>
								<!--
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#my3" role="tab"><img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.png" alt="user" class="profile-pic" />
									<span class="hide-menu">个人中心</span></a> </li>
								<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#language3" role="tab"><i class="icon-globe"></i> 
									<span class="hide-menu">语系</span></a> </li>

								-->
		                    </ul>
	                   <!-- Tab panes -->
	                         <div id="tab-guide" class="tab-content" style="padding: 0px;">
	                         
	                             <!-- 导览 -->
	                             <div class="tab-pane p-20 active" id="guide3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_guide_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_guide_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/topbar/guide.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                         	 <!-- 仪表板 -->
	                             <div class="tab-pane p-20" id="dashboard3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_dashboard_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_dashboard_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/dashboard/dashboard_menu.JPG" /><br/>
												<code><spring:message code="guide_dashboard_2" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/dashboard/dashboard_kpi_user.JPG" /><br/>
												<code><spring:message code="guide_dashboard_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/dashboard/dashboard_kpi_visit.JPG" /><br/>
												<code><spring:message code="guide_dashboard_4" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/dashboard/dashboard_kpi_volume.JPG" /><br/>
												<code><spring:message code="guide_dashboard_5" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/dashboard/dashboard_cust.JPG" /><br/>
												<code><spring:message code="guide_dashboard_6" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/dashboard/dashboard_visit.JPG" /><br/>
												<code><spring:message code="guide_dashboard_7" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/dashboard/dashboard_salon.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                             <!-- 系统公告 -->
	                             <div class="tab-pane p-20" id="billboard3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_billboard_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_billboard_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/billboard/billboard_list.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                             <!-- 参团信息 -->
	                             <div class="tab-pane p-20" id="visitInfo3" role="tabpanel">
									<div class="div_scroll">
										<div class="card">
							            	<h4 class="card-header"><spring:message code="guide_visitInfo_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_visitInfo_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit/visit_list.JPG" /><br/>
					                         </div>
					                     </div>
                                      
										<div class="card">
					                     	<h4 class="card-header"><spring:message code="guide_visitInfo_2" /></h4>
											<div class="card-body">
												<code><spring:message code="guide_visitInfo_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit/visit_detailBtn.JPG" /><br/>
												<code><spring:message code="guide_visitInfo_4" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit/visit_h5.jpg" /><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit/visit_download1.jpg" /><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit/visit_download2.jpg" /><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit/visit_schedule.jpg" /><br/>
											</div>
										</div>
									</div>
	                             </div>
	                             
	                             <!-- 报团 -->
	                             <div class="tab-pane p-20" id="registration3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_registration_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_registration_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit_tour/visit_tour_list.JPG" /><br/>
					                         </div>
					                     </div>
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_registration_2" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_registration_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit_tour/visit_tour_list_newBtn.JPG" /><br/>
							                	<img src="${pageContext.request.contextPath}/resources/guide/visit_tour/visit_tour_new.JPG" /><br/>
							                	<code><spring:message code="guide_registration_4" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit_tour/visit_tour_new_saveBtn.JPG" /><br/>
					                         </div>
					                     </div>
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_registration_5" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_registration_6" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit_tour/visit_tour_list_joinBtn.JPG" /><br/>
							                	<code><spring:message code="guide_registration_7" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/visit_tour/visit_tour_join_saveBtn.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                             <!-- 沙龙 -->
	                             <div class="tab-pane p-20" id="salon3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_salon_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_salon_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/salon/salon_list.JPG" /><br/>
					                         </div>
					                     </div>
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_salon_2" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_salon_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/salon/salon_list_newBtn.JPG" /><br/>
							                	<code><spring:message code="guide_salon_4" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/salon/salon_new_saveBtn.JPG" /><br/>
					                         </div>
					                     </div>
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_salon_5" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_salon_6" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/salon/salon_list_joinBtn.JPG" /><br/>
							                	<code><spring:message code="guide_salon_7" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/salon/salon_join_saveBtn.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                             <!-- 产品信息 -->
	                             <div class="tab-pane p-20" id="productInfo3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_product_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_product_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/product/product_menu.JPG" /><br/>
					                         </div>
					                     </div>
					                     <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_product_2" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_product_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/product/product_down.JPG" /><br/>
												<code><spring:message code="guide_product_4" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/product/product_file.JPG" /><br/>
												<code><spring:message code="guide_product_5" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/product/product_download.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                             <!-- 參考信息 -->
	                             <div class="tab-pane p-20" id="referenceInfo3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_reference_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_reference_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/reference/reference_menu.JPG" /><br/>
					                         </div>
					                     </div>
					                     <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_reference_2" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_reference_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/reference/reference_down.JPG" /><br/>
												<code><spring:message code="guide_reference_4" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/reference/reference_file.JPG" /><br/>
												<code><spring:message code="guide_reference_5" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/reference/reference_download.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                             <!-- 我的渠道 -->
	                             <div class="tab-pane p-20" id="myChannels3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_myChannels_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_myChannels_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/user/user_list.jpg" /><br/>
					                         </div>
					                     </div>
					                     <div class="card">
					                     	<h4 class="card-header"><spring:message code="guide_myChannels_2" /></h4>
											<div class="card-body">
												<code><spring:message code="guide_myChannels_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/user/user_list_editBtn.jpg" /><br/>
												<code><spring:message code="guide_myChannels_4" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/user/user_edit_saveBtn.jpg" /><br/>
											</div>
										</div>
					                     <div class="card">
					                     	<h4 class="card-header"><spring:message code="guide_myChannels_5" /></h4>
											<div class="card-body">
												<code><spring:message code="guide_myChannels_6" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/user/user_list_commissionBtn.jpg" /><br/>
												<code><spring:message code="guide_myChannels_7" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/user/user_commission_defaultBtn.jpg" /><br/>
												<code><spring:message code="guide_myChannels_8" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/user/user_commission_saveBtn.jpg" /><br/>
											</div>
										</div>
                                      </div>
								</div>
								
								<!-- 我的客戶 -->
	                            <div class="tab-pane p-20" id="myClients3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_myClients_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_myClients_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/cust/cust_list.JPG" /><br/>
					                         </div>
					                     </div>
					                     <div class="card">
					                     	<h4 class="card-header"><spring:message code="guide_myClients_2" /></h4>
											<div class="card-body">
												<code><spring:message code="guide_myClients_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/cust/cust_list_editBtn.JPG" /><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/cust/cust_edit.JPG" /><br/>
												<code><spring:message code="guide_myClients_4" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/cust/cust_edit_save.JPG" /><br/>
											</div>
										</div>
					                     <div class="card">
					                     	<h4 class="card-header"><spring:message code="guide_myClients_5" /></h4>
											<div class="card-body">
												<code><spring:message code="guide_myClients_6" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/cust/cust_list_fileBtn.JPG" /><br/>
												<code><spring:message code="guide_myClients_7" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/cust/cust_file.JPG" /><br/>
												<code><spring:message code="guide_myClients_8" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/cust/cust_file_uploadBtn.JPG" /><br/>
											</div>
										</div>
                                      </div>
	                            </div>
	                            
	                             <!-- 系统通知 -->
	                             <div class="tab-pane p-20" id="sysMail3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_sysMail_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_sysMail_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/topbar/mail1.JPG" /><br/>
												<code><spring:message code="guide_sysMail_2" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/topbar/mail2.JPG" /><br/>
												<code><spring:message code="guide_sysMail_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/topbar/mail3.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                             <!-- 站内信 -->
	                             <div class="tab-pane p-20" id="mail3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_mail_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_mail_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/topbar/sysMail1.JPG" /><br/>
												<code><spring:message code="guide_mail_2" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/topbar/sysMail2.JPG" /><br/>
												<code><spring:message code="guide_mail_3" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/topbar/sysMail3.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                             
	                             <!-- 联系我们 -->
	                             <div class="tab-pane p-20" id="contact_us3" role="tabpanel">
									<div class="div_scroll">
							            <div class="card">
							            	<h4 class="card-header"><spring:message code="guide_contact_us_0" /></h4>
							                <div class="card-body">
							                	<code><spring:message code="guide_contact_us_1" /></code><br/>
												<img src="${pageContext.request.contextPath}/resources/guide/topbar/contact_us.JPG" /><br/>
					                         </div>
					                     </div>
                                      </div>
	                             </div>
	                            
	                         </div>
	                     </div>
                 </div>
             </div>
			</div>
		</div>
	</div>
</div>



    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <header class="topbar">
            <nav class="navbar top-navbar navbar-expand-md navbar-light">
                <!-- ============================================================== -->
                <!-- Logo -->
                <!-- ============================================================== -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/manage/billboard/list">
                        <!-- Logo icon -->
                        <b>
                            <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                            <span style="color: white">CMP</span>
                            <!-- 
                            <!-- Dark Logo icon 
                            <img src="${pageContext.request.contextPath}/resources/assets/images/logo-icon.png" alt="homepage" class="dark-logo" />
                            <!-- Light Logo icon 
                            <img src="${pageContext.request.contextPath}/resources/images/favicon.ico" alt="homepage" class="light-logo" />
                             -->
                        
                        <!--End Logo icon -->
                        <!-- Logo text -->
                        <span>
                         <span style="color: white">-信息服务联网</span>
                         <!-- 
                         <!-- dark Logo text 
                         <img src="${pageContext.request.contextPath}/resources/assets/images/logo-text.png" alt="homepage" class="dark-logo" />
                         <!-- Light Logo text    
                         <img src="${pageContext.request.contextPath}/resources/assets/images/logo-light-text.png" class="light-logo" alt="homepage" />
                         -->
                        </span> 
                        </b>
                    </a>
                </div>
                <!-- ============================================================== -->
                <!-- End Logo -->
                <!-- ============================================================== -->
                <div class="navbar-collapse">
                    <!-- ============================================================== -->
                    <!-- toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav mr-auto mt-md-0 ">
                        <!-- This is  -->
                        <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark" href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
                        <li class="nav-item"> <a class="nav-link sidebartoggler hidden-sm-down text-muted waves-effect waves-dark" href="javascript:void(0)"><i class="icon-arrow-left-circle"></i></a> </li>
                        <!-- ============================================================== -->
                        <!-- Comment -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown" id="sysMail-tag">
                            <a class="nav-link dropdown-toggle text-muted text-muted waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="mdi mdi-message"></i>
                                <c:if test="${!sysMailMap.listUnread.isEmpty() }"><div class="notify"> <span class="heartbit"></span> <span class="point"></span> </div></c:if>
                            </a>
                            <div class="dropdown-menu mailbox animated bounceInDown">
                                <ul>
                                    <li>
                                        <div class="drop-title">
                                        	<spring:message code="youHave" /> ${sysMailMap.countUnread} <spring:message code="newSysMSG" />
                                        	<a href='${pageContext.request.contextPath}/sysMail/sysMailbox' title="<spring:message code='moveTo'/><spring:message code='sysMail'/>" ><i class="mdi mdi-folder-move"></i></a>
                                        </div>
                                    </li>
                                    
                                    <li>
                                        <div class="message-center">
											<c:if test="${!sysMailMap.listUnread.isEmpty() }">
											<c:forEach var="vo" items="${ sysMailMap.listUnread }">
												<a href='${pageContext.request.contextPath}/sysMail/sysMailbox/${ vo.id }'>
													<div class="mail-contnet">
														<h5>${vo.mailFrom.name}</h5> <span class="mail-desc">${vo.subject}</span> <span class="time">${vo.createTime}</span> 
													</div>
												</a>
											</c:forEach>
											</c:if>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- End Comment -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- Messages -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown" id="mail-tag">
                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="" id="2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="mdi mdi-email"></i>
                                <c:if test="${!mailMap.listUnread.isEmpty() }"><div class="notify"> <span class="heartbit"></span> <span class="point"></span> </div></c:if>
                            </a>
                            <div class="dropdown-menu mailbox animated bounceInDown" aria-labelledby="2">
                                <ul>
                                    <li>
                                        <div class="drop-title">
                                        	<spring:message code="youHave" /> ${mailMap.countUnread} <spring:message code="newMSG" />
                                        	<a href='${pageContext.request.contextPath}/mail/mailbox' title="<spring:message code='moveTo'/><spring:message code='emailApp'/>" ><i class="mdi mdi-folder-move"></i></a>
                                        </div>
                                    </li>
                                    
                                    <li>
                                        <div class="message-center">
											<c:if test="${!mailMap.listUnread.isEmpty() }">
											<c:forEach var="vo" items="${ mailMap.listUnread }">
												<a href='${pageContext.request.contextPath}/mail/mailbox/${ vo.id }'>
													<div class="mail-contnet">
														<h5>${vo.mailFrom.name}</h5> <span class="mail-desc">${vo.subject}</span> <span class="time">${vo.createTime}</span> 
													</div>
												</a>
											</c:forEach>
											</c:if>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- End Messages -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- Messages -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown" id="contact_us-tag"> 
                        	<a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        		<i id="contactUsIcon" class="mdi mdi-view-grid"></i>
                        	</a>
                            
                            <div class="dropdown-menu mailbox animated bounceInDown" aria-labelledby="2">
                                <ul>
                                    <li>
                                        <div class="drop-title">
                                        	<spring:message code="contact_us" />
                                        </div>
                                    </li>
                                    
                                    <li>
			                            <div class="card">
			                                <div class="card-body">
				                                 <form>
				                                     <div class="form-group">
				                                         <input type="text" class="form-control" id="contactSubject" placeholder="<spring:message code="subject" />"> </div>
				                                     <div class="form-group">
				                                         <textarea class="form-control" id="contactContent" placeholder="<spring:message code="content" />" style="min-height: 88px;"></textarea>
				                                     </div>
				                                     <button type="button" class="btn btn-info" onclick="contactUs();"><spring:message code="submit" /></button>
				                                 </form>
			                                </div>
			                            </div>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- End Messages -->
                        <!-- ============================================================== -->
                    </ul>
                    <!-- ============================================================== -->
                    <!-- User profile and search -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav my-lg-0">
                        <li class="nav-item dropdown" id="member-dropdown">
                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.png" alt="user" class="profile-pic" /></a>
                            <div class="dropdown-menu dropdown-menu-right animated flipInY">
                                <ul class="dropdown-user">
                                    <li>
                                        <div class="dw-user-box">
                                            <div class="u-img"><img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.png" alt="user"></div>
                                            <div class="u-text">
                                                <h4>${username}</h4>
                                                <p class="text-muted">${email}</p></div>
                                        	</div>
                                    </li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="${pageContext.request.contextPath}/channel/personalInfo/info"><i class="ti-user"></i> My Profile</a></li>
                                    <li><a href='${pageContext.request.contextPath}/mail/mailbox'><i class="ti-email"></i> Inbox</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="<c:url value="/logout" />"><i class="fa fa-power-off"></i> Logout</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="icon-globe"></i></a>
                            <div class="dropdown-menu  dropdown-menu-right animated bounceInDown"> 
                            	<a class="dropdown-item" href="#" onclick="doChangeLang('zh_CN')"><i class="flag-icon flag-icon-cn"></i> 简体中文</a> 
                            	<a class="dropdown-item" href="#" onclick="doChangeLang('en_US')"><i class="flag-icon flag-icon-us"></i> English</a> 
                            </div>
                        </li>
                        <li class="nav-item dropdown" id="guide-dropdown">
                        	<a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="#" onclick="showTip(); return false;" aria-expanded="false"> 
                        		<i class="icon-info"></i>
                        	</a>
                        </li>
                        
                    </ul>
                </div>
            </nav>
        </header>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <aside class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                    	<li id="dashboard-tag">
                            <a href="${pageContext.request.contextPath}/dashboard"> <i class="mdi mdi-gauge"></i><span class="hide-menu"><spring:message code="dashboard" /></span></a>
                        </li>
                        <li id="billboard-tag">
                            <a href="${pageContext.request.contextPath}/manage/billboard/list"> <i class="mdi mdi-content-paste"></i><span class="hide-menu"><spring:message code="billboard" /></span></a>
                        </li>
                        <li id="visitInfo-tag">
                            <a href="${pageContext.request.contextPath}/visit/list"> <i class="mdi mdi-table"></i><span class="hide-menu"><spring:message code="visitInfo" /></span></a>
                        </li>
                       	<li id="registration-tag">
                            <a href="${pageContext.request.contextPath}/visit/tour"> <i class="mdi mdi-book-multiple"></i><span class="hide-menu"><spring:message code="registration" /></span></a>
                        </li>
                        <li id="salon-tag">
                            <a href="${pageContext.request.contextPath}/salon/list"> <i class="mdi mdi-coffee"></i><span class="hide-menu"><spring:message code="salon" /></span></a>
                        </li>
                        <li id="productInfo-tag">
                            <a href="${pageContext.request.contextPath}/product/list"> <i class="mdi mdi-file"></i><span class="hide-menu"><spring:message code="productInfo" /></span></a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/reference/list"> <i class="mdi mdi-file"></i><span class="hide-menu"><spring:message code="referenceInfo" /></span></a>
                        </li>
                        <li id="myChannels-tag">
                            <a href="${pageContext.request.contextPath}/channel/user/list"> <i class="mdi mdi-book-open-variant"></i><span class="hide-menu"><spring:message code="myChannels" /></span></a>
                        </li>
                        <li id="myClients-tag">
                            <a href="${pageContext.request.contextPath}/channel/cust/list"> <i class="mdi mdi-face"></i><span class="hide-menu"><spring:message code="myClients" /></span></a>
                        </li>
                        <sec:authorize access="hasAnyRole('ROLE_SU')">
                        	<li>
	                            <a href="${pageContext.request.contextPath}/admin/role/list"> <i class="mdi mdi-book-multiple"></i><span class="hide-menu"><spring:message code="role" /></span></a>
	                        </li>
	                        <li>
	                            <a href="${pageContext.request.contextPath}/admin/status/list"> <i class="mdi mdi-book-multiple"></i><span class="hide-menu"><spring:message code="status" /></span></a>
	                        </li>
	                        <li>
                            <a class="has-arrow " href="#" aria-expanded="false"><i class="mdi mdi-bullseye"></i><span class="hide-menu"><spring:message code="api" /></span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="${pageContext.request.contextPath}/api"><spring:message code="receiveData" /></a></li>
                                <li><a href="${pageContext.request.contextPath}/api/list"><spring:message code="queryData" /></a></li>
                                <li><a href="${pageContext.request.contextPath}/job/manage"><spring:message code="reciveDataSchedule" /></a></li>
                            </ul>
                        </li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('ROLE_SU','ROLE_ADMIN','ROLE_MA','ROLE_ASST')">
                        	<li>
	                            <a class="has-arrow " href="#" aria-expanded="false"><i class="mdi mdi-widgets"></i><span class="hide-menu"><spring:message code="backstageMenagement"/></span></a>
	                            <ul aria-expanded="false" class="collapse">
	                            	<sec:authorize access="hasAnyRole('ROLE_SU','ROLE_ADMIN')">
	                            		<li><a href="${pageContext.request.contextPath}/admin/user/list"><spring:message code="allChannels" /></a></li>
	                            	</sec:authorize>
	                                <li><a href="${pageContext.request.contextPath}/admin/cust/list"><spring:message code="allCust" /></a></li>
	                                <li><a href="${pageContext.request.contextPath}/manage/billboard"><spring:message code="maintainInfo" /></a></li>
	                                <li><a href="${pageContext.request.contextPath}/manage/productFile"><spring:message code="productFileMaintain" /></a></li>
	                                <li><a href="${pageContext.request.contextPath}/manage/referenceFile"><spring:message code="referenceFileMaintain" /></a></li>
	                            </ul>
	                        </li>
                        </sec:authorize>

                        <li class="nav-devider"></li>

                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </aside>
        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                
                <decorator:body />
                
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- footer -->
            <!-- ============================================================== -->
            <footer class="footer">
                Copyright © 2018 Fede tech Co. 版权所有
            </footer>
            <!-- ============================================================== -->
            <!-- End footer -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    
    <!-- ============================================================== -->
    <!-- This page plugins -->
    <!-- ============================================================== -->
    <!-- chartist chart -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/chartist-js/dist/chartist.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
    <!-- Chart JS -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/echarts/echarts-all.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/toast-master/js/jquery.toast.js"></script>
    <!-- Chart JS -->
    <script src="${pageContext.request.contextPath}/resources/js/dashboard1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/toastr.js"></script>
    
    <!-- This is data table -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables/jquery.dataTables.min.js"></script>
    
    <!-- 
	<script src="${pageContext.request.contextPath}/resources/elastislide/js/modernizr.custom.17475.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/js/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js"></script>
	 -->
	<script src="${pageContext.request.contextPath}/resources/jqueryui/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.pagination.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-1.2.1.js"></script>
	
	<!-- 
	<script src="${pageContext.request.contextPath}/resources/elastislide/js/jquery.elastislide.js"></script>
	<script src="${pageContext.request.contextPath}/resources/elastislide/js/jquerypp.custom.js"></script>
	 -->
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
	
	<!-- Draggable-portlet -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/jqueryui/jquery-ui.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/gridstack/lodash.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/gridstack/gridstack.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/gridstack/gridstack.jQueryUI.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/chartist-js/dist/chartist.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/sparkline/jquery.sparkline.min.js"></script>
	<!-- Chart JS -->
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/echarts/echarts-all.js"></script>
	<!-- Flot Charts JavaScript -->
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/flot/excanvas.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/flot/jquery.flot.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/flot/jquery.flot.time.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
	<!-- <script src="${pageContext.request.contextPath}/resources/js/widget-charts.js"></script> -->

    <!-- ============================================================== -->
    <!-- Style switcher -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
    <script>
    var showGuide = '${showGuide}';
    var isHome = '${active}';
    $(function() {
      if("Y"===showGuide && "INDEX"===isHome) {
		//showTip();
    	// Instance the tour
		var tour = new Tour({
			backdrop: true,
			smartPlacement: true,
			template: "<div class='popover tour' style='min-width:500px;  max-width:555px;'><div class='arrow'></div><h3 class='popover-title'></h3><div class='popover-content'></div>"
				+"<div class='popover-navigation'><button class='btn btn-default' data-role='prev'>« <spring:message code='prev' /></button><span data-role='separator'>|</span><button class='btn btn-default' data-role='next'><spring:message code='next' /> »</button>"
				+"<button class='btn float-right' onclick='doCloseGuide();' aria-hidden='true'><i class='fa fa-ban'></i> <spring:message code='noShowAgain' /></button><button class='btn btn-default float-right' data-role='end'><spring:message code='endTour' /></button></div></div>",
			steps: [
    	  		{
    	    		element: "#member-dropdown",
    	    		title: "<spring:message code='basicInformation' />",
    	    		content: "<spring:message code='guide_persionalInfo_1' />",
    	    		placement: "bottom"
    	  		},
    	  		{
    	    		element: "#myChannels-tag",
    	    		title: "<spring:message code='basicInformation' />",
    	    		content: "<spring:message code='guide_myChannels_1' />"
    	  		},
    	  		{
    	    		element: "#myClients-tag",
    	    		title: "<spring:message code='basicInformation' />",
    	    		content: "<spring:message code='guide_myClients_1' />"
    	  		},
    	  		{
    	    		element: "#productInfo-tag",
    	    		title: "<spring:message code='visitingBusiness' />",
    	    		content: "<spring:message code='guide_productInfo_1' />"
    	  		},
    	  		{
    	    		element: "#visitInfo-tag",
    	    		title: "<spring:message code='visitingBusiness' />",
    	    		content: "<spring:message code='guide_visitInfo_1' />"
    	  		},
    	  		{
    	    		element: "#registration-tag",
    	    		title: "<spring:message code='visitingBusiness' />",
    	    		content: "<spring:message code='guide_registration_1' />"
    	  		},
    	  		{
    	    		element: "#salon-tag",
    	    		title: "<spring:message code='visitingBusiness' />",
    	    		content: "<spring:message code='guide_salon_1' />"
    	  		},
    	  		{
    	    		element: "#dashboard-tag",
    	    		title: "<spring:message code='indicatorChart' />",
    	    		content: "<spring:message code='guide_dashboard_1' />"
    	  		},
    	  		{
    	    		element: "#billboard-tag",
    	    		title: "<spring:message code='platformRelated' />",
    	    		content: "<spring:message code='guide_billboard_1' />"
    	  		},
				{
					element: "#sysMail-tag",
    	    		title: "<spring:message code='platformRelated' />",
    	    		content: "<spring:message code='guide_sysMail_1' />",
    	    		placement: "bottom"
    	  		},
				{
					element: "#mail-tag",
    	    		title: "<spring:message code='platformRelated' />",
    	    		content: "<spring:message code='guide_mail_1' />",
    	    		placement: "bottom"
    	  		},
				{
					element: "#contact_us-tag",
    	    		title: "<spring:message code='platformRelated' />",
    	    		content: "<spring:message code='guide_contact_us_1' />",
    	    		placement: "bottom"
    	  		},
				{
					element: "#guide-dropdown",
    	    		title: "<spring:message code='platformRelated' />",
    	    		content: "<spring:message code='guide_guide_1' />",
    	    		placement: "bottom"
    	  		}
    	  		
    	  		
    		]});

    	// Initialize the tour
    	tour.init();

    	// Start the tour
    	//tour.start();
    	tour.restart();
      }
    });
    
    
   	$('.div_scroll').slimScroll({
   		height: '620px'
   	});
	</script>
	
</body>

</html>