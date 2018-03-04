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
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons/2.0.1/css/ionicons.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/step_arrow.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/skin-blue.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/iCheck/all.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jqueryui/jquery-ui.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/buttons.dataTables.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/editor.dataTables.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/select.dataTables.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/carouselSlider/css/jquery.jscrollpane.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/carouselSlider/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/agileCarousel/agile_carousel.css">


	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/js/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/jqueryui/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.pagination.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-1.2.1.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/carouselSlider/js/jquery.contentcarousel.js"></script>
	<script src="${pageContext.request.contextPath}/resources/carouselSlider/js/jquery.easing.1.3.js"></script>
	<!-- <script src="${pageContext.request.contextPath}/resources/carouselSlider/js/jquery.mousewheel.js"></script>  -->
	<!-- <script src="${pageContext.request.contextPath}/resources/agileCarousel/agile_carousel.alpha.js"></script>  -->

	<style type="text/css">
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
        	var redirectPage = (window.location.pathname).replace("/CMP","");
            var url = '${pageContext.request.contextPath}/changeLanguage?langType='+lang+'&refresh='+redirectPage;
            window.location.href = url;
        }
	</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Main Header -->
	<header class="main-header">
	
	<!-- Logo -->
    <a href="${pageContext.request.contextPath}" class="logo">
		<!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>CMP</b></span>
		<!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b><spring:message code="cmp"/></b></span>
    </a>
	
	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" data-toggle="offcanvas" role="button">
		<img src="${pageContext.request.contextPath}/resources/images/588a64e0d06f6719692a2d10.png" class="box-shadow-menu" alt="">
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li>
					<a href="javascript:void(0)" onclick="doChangeLang('en_US')">
						English
					</a>
				</li>
				<li>
					<a href="javascript:void(0)" onclick="doChangeLang('zh_CN')">
						中文
					</a>
				</li>

				<!-- User Account Menu -->
				<li class="dropdown user user-menu">
					<!-- Menu Toggle Button -->
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<!-- The user image in the navbar-->
						<img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.png" class="user-image" alt="User Image">
						<!-- hidden-xs hides the username on small devices so only the image appears. -->
						<span class="hidden-xs">${username}</span>
					</a>
					<ul class="dropdown-menu">
						<!-- The user image in the menu -->
						<li class="user-header">
							<img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.png" class="img-circle" alt="User Image">
							<p>
								<small>${username}</small>
							</p>
						</li>
						<!-- Menu Body -->
						
						<!-- Menu Footer-->
						<li class="user-footer">
							<!-- 
							<div class="pull-left">
								<a href="#" class="btn btn-default btn-flat">Register</a>
							</div>
							-->
							<div class="pull-right">
								<a href="<c:url value="/logout" />" class="btn btn-default btn-flat"><spring:message code="signOut"/></a>
							</div>
						</li>
					</ul>
				</li>
				<!-- Control Sidebar Toggle Button -->
			
			</ul>
		</div>
	</nav>
	</header>
  
  
	<!-- Left side column. contains the logo and sidebar -->
	<aside id="main-sidebar" class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			
			<!-- Sidebar user panel (optional) -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.png" class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${username}</p>
					<!-- Status -->
					<a href="#"><i class="fa fa-circle text-success"></i><spring:message code="online" /></a>
				</div>
			</div>
			<!-- Sidebar Menu -->
			<ul class="sidebar-menu">
			
				<li class="<c:if test="${active eq 'INDEX'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/"> <span><span><spring:message code="home" /></span></a>
				</li>
				<li class="<c:if test="${active eq 'SHARE_FILE'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/share/file"> <span><spring:message code="shareResource" /></span></a>
				</li>
				<li class="<c:if test="${active eq 'PERSONAL_INFO'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/personalInfo/info"> <span><spring:message code="personalInfo" /></span></a>
				</li>
				<li class="<c:if test="${active eq 'PRODUCT_INFO'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/productInfo/list"> <span><spring:message code="productInfo" /></span></a>
				</li>
				<li class="<c:if test="${active eq 'MY_USER'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/user/list"> <span><spring:message code="myChannels" /></span></a>
				</li>
				<li class="<c:if test="${active eq 'MY_CUST'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/cust/list"> <span><spring:message code="myClients" /></span></a>
				</li>
				<sec:authorize access="hasAnyRole('ROLE_SU')">
					<li class="sidebar-item adminView" >
						<a href="${pageContext.request.contextPath}/admin/role/list"> <span><spring:message code="role" /></span></a>
					</li>
					<li class="sidebar-item adminView" >
						<a href="${pageContext.request.contextPath}/admin/status/list"> <span><spring:message code="status" /></span></a>
					</li>
					<li class="sidebar-item adminView" >
						<a href="${pageContext.request.contextPath}/admin/registration/list"> <span><spring:message code="registration" /></span></a>
					</li>
					<li class="<c:if test="${active eq 'API_MAIN' || active eq 'API_MANAGE' }">active</c:if> treeview adminView">
						<a href="#">
							<i class="fa fa-server"></i> <span><spring:message code="api" /></span> <i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu <c:if test="${active eq 'API_MAIN' || active eq 'API_MANAGE' }">menu-open</c:if>">
							<li <c:if test="${active eq 'API_MAIN'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/api"><spring:message code="receiveData" /></a></li>
							<li <c:if test="${active eq 'API_MAIN'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/api/list"><spring:message code="queryData" /></a></li>
							<li <c:if test="${active eq 'API_MANAGE'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/job/manage"><spring:message code="reciveDataSchedule" /></a></li>
						</ul>
					</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_SU','ROLE_ADMIN','ROLE_ASST')">
					<li class="<c:if test="${active eq 'ADMIN_USER' || active eq 'ADMIN_CUST' || active eq 'MANAGE_BILLBOARD' || active eq 'MANAGE_FILE' }">active</c:if> treeview adminView">
						<a href="#">
							<i class="fa fa-server"></i> <span><spring:message code="backstageMenagement"/></span> <i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu <c:if test="${active eq 'ADMIN_USER' || active eq 'ADMIN_CUST' || active eq 'MANAGE_BILLBOARD' || active eq 'MANAGE_FILE' }">menu-open</c:if>">

							<sec:authorize access="hasAnyRole('ROLE_SU','ROLE_ADMIN')">
							<li <c:if test="${active eq 'ADMIN_USER'}"> class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/user/list"><spring:message code="allChannels" /></a></li>
							</sec:authorize>
							
							<li <c:if test="${active eq 'ADMIN_CUST'}"> class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/cust/list"><spring:message code="allCust" /></a></li>
							<li <c:if test="${active eq 'MANAGE_BILLBOARD'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/manage/billboard"><spring:message code="maintainInfo" /></a></li>
							<li <c:if test="${active eq 'MANAGE_FILE'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/manage/file"><spring:message code="fileMaintain" /></a></li>
						</ul>
					</li>
				</sec:authorize>
			</ul>
			<!-- /.sidebar-menu -->
		</section>
    <!-- /.sidebar -->
	</aside>
  
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header)
		<section class="content-header">
		</section>
		 -->
		<!-- Main content
		<section class="content">
		</section>
		 -->
		<decorator:body />
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<div class="hidden_div" style="display: none">principal.authorities : <sec:authentication property="principal.authorities" /></div>
	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="pull-right hidden-xs">
			Ver.${versionCode}
		</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2018 <a href="#"><spring:message code="corpName"/></a></strong> <spring:message code="allRightsReserved"/>
	</footer> 
</body>
</html>