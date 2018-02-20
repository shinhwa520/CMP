<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jqueryui/jquery-ui.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/buttons.dataTables.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/editor.dataTables.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/1.10.10/css/select.dataTables.css">
	
	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/js/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/jqueryui/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.pagination.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/datatables/1.10.10/js/dataTables.select.min.js"></script>
	<style type="text/css">
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
				<li class="<c:if test="${active eq 'PERSONAL_INFO'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/personalInfo/info"><i class="fa fa-user"></i> <span>個人資訊</span></a>
				</li>
				<li class="<c:if test="${active eq 'MY_USER'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/user/list"><i class="fa fa-user"></i> <span>我的渠道商</span></a>
				</li>
				<li class="<c:if test="${active eq 'MY_CUST'}">active</c:if> sidebar-item">
					<a href="${pageContext.request.contextPath}/channel/cust/list"><i class="fa fa-user"></i> <span>我的客戶</span></a>
				</li>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ASST','ROLE_VEN')">
				<li class="<c:if test="${active eq 'ADMIN_USER'}">active</c:if> sidebar-item adminView" >
					<a href="${pageContext.request.contextPath}/admin/user/list"><i class="fa fa-user"></i> <span>USER</span></a>
				</li>				
				<li class="<c:if test="${active eq 'ADMIN_CUST'}">active</c:if> sidebar-item adminView">
					<a href="${pageContext.request.contextPath}/admin/cust/list"><i class="fa fa-user"></i> <span>CUST</span></a>
				</li>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
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
  
	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="pull-right hidden-xs">
			Ver.${versionCode}
		</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2018 <a href="#">Fede tech Co.</a></strong> All rights reserved.
	</footer> 
</body>
</html>