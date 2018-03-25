<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CMP</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/wizard/steps.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/icheck/skins/all.css" rel="stylesheet">
    <!-- Typehead CSS -->
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/typeahead.js-master/dist/typehead-min.css" rel="stylesheet">
    <!--alerts CSS -->
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="${pageContext.request.contextPath}/resources/css/colors/blue.css" id="theme" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jquery-3.3.1.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-HF">
		<div class="navbar-header">
			<span class="navbar-brand"><spring:message code="cmp"/></span><br />
		</div>
	</nav>
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
	<decorator:body />
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
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
    <!-- icheck -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/icheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/icheck/icheck.init.js"></script>
    <!-- Typehead Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/typeahead.js-master/dist/typeahead.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/typeahead.js-master/dist/typeahead-init.js"></script>
    <!--Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/moment/min/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/wizard/jquery.steps.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/wizard/jquery.validate.min.js"></script>
    <!-- Sweet-Alert  -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/wizard/steps.js"></script>
    <script type="text/javascript">
	$('.div_scroll').slimScroll({
		height: '420px'
	});
	$('.agreement_scroll').slimScroll({
		height: '360px'
	});
    </script>
    <!-- ============================================================== -->
    <!-- Style switcher -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>
<style>
nav.navbar-HF{
    background-color: #555;
    opacity: .9;
}
nav.navbar-HF .navbar-header{
    width: 100%;
    margin-left: 10px;
    color: #ffffff;
    background-color: #555;
}
</style>
</html>

