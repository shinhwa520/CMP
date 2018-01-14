<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Source-Viewer</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.5 -->
  <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/4.4.0/css/font-awesome.min.css">
  -->
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/skin-blue.min.css">

   <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/iCheck/all.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.1.4 -->
<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/dist/js/app.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/jquery.pagination.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="${pageContext.request.contextPath}" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>VASC</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><img src="${pageContext.request.contextPath}/resources/dist/img/xEncoder_logo.png" class="user-image" alt="#" style="width:105px; height:37px;"></span>
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
                <a href="<c:url value="/logoutEcs" />" class="btn btn-default btn-flat">Sign out</a>
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
        <li class="header">MENU</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="<c:if test="${menuopen eq 'SOURCE'}">active</c:if> treeview">
        <a href="#">
            <i class="fa fa-file-video-o"></i> <span>Source</span><i class="fa fa-angle-left pull-right"></i>
            </a>
            <ul class="treeview-menu <c:if test="${menuopen eq 'SOURCE'}">menu-open</c:if>">
              <li <c:if test="${active eq 'SESSION_LIST' || active eq 'SESSION_DETAIL'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/source/session/list"><i class="fa fa-circle-o"></i> Source Upload</a></li>
              <!-- 
              <li <c:if test="${active eq 'SOURCE_LIST'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/source/list"><i class="fa fa-circle-o"></i> Source Viewer</a></li>
            	-->
            </ul>
        </li>
        <li class="<c:if test="${active eq 'NODE_LIST' || active eq 'NODE_SETTING'}">active</c:if> treeview">
          <a href="#">
            <i class="fa fa-server"></i> <span>Node</span> <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu <c:if test="${active eq 'NODE_LIST' || active eq 'NODE_SETTING'}">menu-open</c:if>">
            <li <c:if test="${active eq 'NODE_SETTING'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/node/setting"><i class="fa fa-circle-o"></i> Node Setting</a></li>
            <li <c:if test="${active eq 'NODE_LIST'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/node/list"><i class="fa fa-circle-o"></i> Node Status</a></li>
          </ul>
        </li>
       <li class="<c:if test="${active eq 'JOB_LIST'}">active</c:if> treeview">
         <a href="${pageContext.request.contextPath}/job/list/waiting">
         	<i class="fa fa-briefcase"></i> <span>Job</span></a>
         </li>
       <li>
        <a href="${pageContext.request.contextPath}/report/">
        	<i class="fa fa-pie-chart"></i> <span>Report</span></a>
        </li>
        
        <li>
        <a href="${pageContext.request.contextPath}/account/list"><i class="fa fa-user"></i> <span>User</span></a>
        </li>
        
        <li class="<c:if test="${active eq 'API_MAIN' || active eq 'API_MANAGE' }">active</c:if> treeview">
          <a href="#">
            <i class="fa fa-server"></i> <span>API</span> <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu <c:if test="${active eq 'API_MAIN' || active eq 'API_MANAGE' }">menu-open</c:if>">
            <li <c:if test="${active eq 'API_MAIN'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/retrieve"><i class="fa fa-circle-o"></i> 表單資料接收</a></li>
            <li <c:if test="${active eq 'API_MANAGE'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/views/retrieve/retrieve_manage"><i class="fa fa-circle-o"></i> 資料接收排程管理</a></li>
          </ul>
        </li>
         
        <li class="<c:if test="${active eq 'SETTING_DESTINATION' || active eq 'SETTING_PROFILE_PARAMETER' || active eq 'SETTING_PROFILE'|| active eq 'SETTING_PROFILE_GROUP' }">active</c:if> treeview">
          <a href="#">
            <i class="fa fa-server"></i> <span>Setting</span> <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu <c:if test="${active eq 'SETTING_DESTINATION' || active eq 'SETTING_PROFILE_GROUP' || active eq 'SETTING_PROFILE'}">menu-open</c:if>">
            <li <c:if test="${active eq 'SETTING_DESTINATION'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/setting/destination"><i class="fa fa-circle-o"></i> Destination</a></li>
            <li <c:if test="${active eq 'SETTING_PROFILE_PARAMETER'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/setting/profile_parameter"><i class="fa fa-circle-o"></i> Profile Parameter</a></li>
            <li <c:if test="${active eq 'SETTING_PROFILE'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/setting/profile"><i class="fa fa-circle-o"></i> Profile</a></li>
            <li <c:if test="${active eq 'SETTING_PROFILE_GROUP'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/setting/profile_group"><i class="fa fa-circle-o"></i> Profile Group</a></li>
          </ul>
        </li>
        
        <li>
        <a href="${pageContext.request.contextPath}/log/list"><i class="fa fa-user"></i> <span>Log</span></a>
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
      <h4></h4>
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
    <strong>Copyright &copy; 2015 <a href="#">VAS Creative Co.</a></strong> All rights reserved.
  </footer> 
</body>
</html>