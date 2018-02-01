<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>CMP</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons/2.0.1/css/ionicons.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/skin-blue.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/iCheck/all.css">
	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/js/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.pagination.js"></script>
	<style type="text/css">
	
		.adminView{
		    display:none;
		}
	
	</style>
	<script>
		var userRole = '${userRole}';
		$(function() {
			if('ADMIN'==userRole) {
				$('.adminView').show();
			}
		});
		
		function successMessage(message) {
			var msg = $('#message');
			msg.addClass('alert-info');
			msg.append(message);
			msg.fadeIn();
			setTimeout(function(){
				msg.fadeOut();
			}, 3000);
		}
		function errorMessage(message) {
			var msg = $('#message');
			$(window).scrollTop(msg.offset().top);
			msg.addClass('alert-danger');
			msg.append(message);
			
			msg.fadeIn();
			setTimeout(function(){
				msg.fadeOut();
			}, 3000);
		}
	</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Main Header -->
	<header class="main-header">
	
	<!-- Logo -->
    <a href="${pageContext.request.contextPath}" class="logo">
		<!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><i><b>CMP</b></i></span>
		<!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><i><b>CMP-渠道商管理平台</b></i></span>
    </a>
	
	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
			
				<!-- User Account Menu -->
				<li class="dropdown user user-menu">
				<!-- Menu Toggle Button -->
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<!-- The user image in the navbar-->
					<img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
					<!-- hidden-xs hides the username on small devices so only the image appears. -->
					<span class="hidden-xs">${username}</span>
				</a>
				<ul class="dropdown-menu">
					<!-- The user image in the menu -->
					<li class="user-header">
						<img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
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
							<a href="<c:url value="/logout" />" class="btn btn-default btn-flat">Sign out</a>
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
	<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			
			<!-- Sidebar user panel (optional) -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${username}</p>
					<!-- Status -->
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			
			<!-- Sidebar Menu -->
			<ul class="sidebar-menu">
				<li class="<c:if test="${active eq 'MY_USER'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/user/list"><i class="fa fa-user"></i> <span>我的渠道商</span></a>
				</li>
				<li class="<c:if test="${active eq 'MY_CUST'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/cust/list"><i class="fa fa-user"></i> <span>我的客戶</span></a>
				</li>
				
				<li class="<c:if test="${active eq 'ADMIN_USER'}">active</c:if> sidebar-item adminView" >
					<a href="${pageContext.request.contextPath}/admin/user/list"><i class="fa fa-user"></i> <span>USER</span></a>
				</li>
				<li class="<c:if test="${active eq 'ADMIN_CUST'}">active</c:if> sidebar-item adminView">
					<a href="${pageContext.request.contextPath}/admin/cust/list"><i class="fa fa-user"></i> <span>CUST</span></a>
				</li>
				<li class="<c:if test="${active eq 'ADMIN_ROLE'}">active</c:if> sidebar-item adminView">
					<a href="${pageContext.request.contextPath}/admin/role/list"><i class="fa fa-user"></i> <span>ROLE</span></a>
				</li>
				<li class="<c:if test="${active eq 'ADMIN_STATUS'}">active</c:if> sidebar-item adminView">
					<a href="${pageContext.request.contextPath}/admin/status/list"><i class="fa fa-user"></i> <span>STATUS</span></a>
				</li>
				<li class="<c:if test="${active eq 'ADMIN_REGISTRATION'}">active</c:if> sidebar-item adminView">
					<a href="${pageContext.request.contextPath}/admin/registration/list"><i class="fa fa-user"></i> <span>REGISTRATION</span></a>
				</li>
				<li class="<c:if test="${active eq 'API_MAIN' || active eq 'API_MANAGE' }">active</c:if> treeview adminView">
					<a href="#">
						<i class="fa fa-server"></i> <span>API</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu <c:if test="${active eq 'API_MAIN' || active eq 'API_MANAGE' }">menu-open</c:if>">
						<li <c:if test="${active eq 'API_MAIN'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/api"><i class="fa fa-circle-o"></i> 表單資料接收</a></li>
						<li <c:if test="${active eq 'API_MAIN'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/api/list"><i class="fa fa-circle-o"></i> 表單資料查詢</a></li>
						<li <c:if test="${active eq 'API_MANAGE'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/job/manage"><i class="fa fa-circle-o"></i> 資料接收排程管理</a></li>
					</ul>
				</li>
			</ul>
			<!-- /.sidebar-menu -->
		</section>
    <!-- /.sidebar -->
	</aside>
  
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		</section>
		<!-- Main content -->
		<section class="content">
			<decorator:body />
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
  
	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="pull-right hidden-xs">
			Ver.${versionCode}
		</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2015 <a href="#">Ftech Co.</a></strong> All rights reserved.
	</footer> 
</body>
</html>