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
	<title>Sign In</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
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
			}, 2000);
		}
		function errorMessage(message) {
			var msg = $('#message');
			$(window).scrollTop(msg.offset().top);
			msg.addClass('alert-danger');
			msg.html(message);
	      
			msg.fadeIn();
			setTimeout(function(){
				msg.fadeOut();
			}, 2000);
		}
	</script>
	<style type="text/css">
		body {
			background: -webkit-linear-gradient(#555555, #FFFFFF);
			background: -o-linear-gradient(#555555, #FFFFFF);
			background: -moz-linear-gradient(#555555, #FFFFFF);
			background: linear-gradient(#555555, #FFFFFF);
		}
		header nav.navbar-HF {
		    min-height: 60px;
		    color: #fff;
		}
		nav.navbar-HF .navbar-header{
		    width: 100%;
		    margin-top: 10px;
		    margin-left: 50px;
		}
		nav.navbar-HF .navbar-header .navbar-brand{
			font-size: 20px;
			font-weight: bold;
		    text-shadow: 2px 2px 0 #000000;
		    color: #ffffff;
		    text-align: center;
		    height: auto;
		}
		nav.navbar-HF .navbar-header .appVersion{
			font-size: 10px;
		    color: #ffffff;
		}
		footer nav.navbar-HF{
			padding: 10px;
		    min-width: 300px;
		    max-width: 100%;
		    height: 36px;
		    color: #ffffff;
		    font-size: 10px;
		    background-color: #555;
		    border-top: 1px solid #333;
		    text-align: center;
		}
		form {
			min-width: 300px;
		    max-width: 450px;
		    padding: 30px;
		    margin: 0 auto;
			background-color: #FFFFFF;
		    border-bottom-left-radius: 6px;
		    border-bottom-right-radius: 6px;
		}
		form input.form-control {
			height: auto;
			margin-bottom: 5px;
		    padding: 10px;
		    font-size: 16px;
		    border-radius: 4px;
		}
		
		 #container{     
		 	margin-top: 60px;
			min-height: 600px;
		 }
		.alert{
			margin-top: 40px;
			display:none;
		}
		.topic{
			min-width: 300px;
		    max-width: 450px;
			min-height: 60px;
			max-height: 90px;
		    margin: 0 auto;
		    background: -webkit-linear-gradient(left top,#FFFFFF, #CCCCCC);
			background: -o-linear-gradient(bottom right,#FFFFFF, #CCCCCC);
			background: -moz-linear-gradient(bottom right,#FFFFFF, #CCCCCC);
			background: linear-gradient(to bottom right,#FFFFFF, #CCCCCC);
			font-size: 20px;
			text-shadow: 1px 1px 0 #CCCCCC;
			padding: 30px;
		    border-top-left-radius: 6px;
		    border-top-right-radius: 6px;
		}
	</style>
</head>
<body>
	<div id="container">
    	<div id="message" class="alert" >&nbsp;</div>
    	<decorator:body />
	</div>
</body>
</html>