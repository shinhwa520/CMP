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
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" >
	<title>Sing On</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons/2.0.1/css/ionicons.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/skin-blue.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/iCheck/all.css">

	<!-- REQUIRED JS SCRIPTS -->
	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/js/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.pagination.js"></script>
	<script>
		function successMessage(message) {
		  	var msg = $('#message');
		  	msg.addClass('alert-info');
		  	msg.html(message);
	      
			msg.fadeIn();
			setTimeout(function(){
				msg.fadeOut();
			}, 1000);
		}
		function errorMessage(message) {
			var msg = $('#message');
			$(window).scrollTop(msg.offset().top);
			msg.addClass('alert-danger');
			msg.html(message);
	      
			msg.fadeIn();
			setTimeout(function(){
				msg.fadeOut();
			}, 1000);
		}
	</script>
	<style type="text/css">
		header nav.navbar-HF {
		    min-height: 41px;
		}

		nav.navbar-HF {
		    background-color: #555;
		    border-bottom: 1px solid #333;
		    color: #fff;
		}
		nav.navbar-HF .navbar-header .navbar-brand{
			font-size: 20px;
		    text-shadow: 2px 2px 0 #333;
		    color: #fff;
		    padding: 10px 0 0 10px;
		    height: auto;
		}
		nav.navbar-HF .navbar-header .appVersion{
		    color: #fff;
		    padding: 15px 0 0 15px;
		    float: left;
		}
		footer nav.navbar-HF{
			padding: 10px;
		    min-width: 980px;
		    height: 36px;
		    color: #fff;
		    background-color: #555;
		    border-top: 1px solid #333;
		    text-align: center;
		}
		form {
			min-width: 500px;
		    max-width: 500px;
		    padding: 15px;
		    margin: 0 auto;
		}
		form input.form-control {
			height: auto;
			margin-bottom: 5px;
		    padding: 10px;
		    font-size: 16px;
		}
		
		 #container{     
		 	margin-top: 40px;
			min-height: 600px;
		 }
		.alert{
			margin-top: 40px;
			display:none;
		}
		.topic{
			font-size: 28px;
			text-shadow: 1px 1px 0 #333;
			text-align: center;
		}
	</style>
</head>
<body>
	<header>
	<nav class="navbar navbar-HF navbar-fixed-top">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">CMP - 渠道商管理平台</a><span class="appVersion">Version: ${versionCode}</span>
		</div>
	</nav>
	</header>

	<div id="container">
    	<div id="message" class="alert" >&nbsp;</div>
    	<decorator:body />
	</div>
	
	
	<footer>
	<nav class="navbar navbar-HF navbar-fixed-bottom">
		<div>
			Powered by ShinhWa&nbsp;&copy;&nbsp;2018 <a href="#">ShinhWa</a> Co., Ltd. All Rights Reserved. <br />
		</div>
	</nav>
	</footer>
</body>
</html>