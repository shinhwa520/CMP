<%@ include file="../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>

<section id="wrapper" class="login-register login-sidebar" >
  <div class="login-box card">
    <div class="card-body">
		<div class="topic" style="padding-top: 180px;"><strong><spring:message code="userLogin"/></strong>
				<ul class="nav navbar-nav pull-right">
					<li>
						<a href="javascript:void(0)" onclick="doChangeLang('en_US')">
							English
						</a>|
						<a href="javascript:void(0)" onclick="doChangeLang('zh_CN')">
							中文
						</a>
					</li>

				</ul>
		</div>
      <form name='f' method='POST'>
		<spring:message code='invalidAccountOrPassword' var="invalidAccountOrPassword"/>
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		      <font color="red">
		        <c:out value="${invalidAccountOrPassword}"/>.
		        <br/>
		      </font>
		</c:if>
		<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />
        <div class="form-group m-t-40">
          <div class="col-xs-12">
            <input class="form-control" type='text' name='username' placeholder="<spring:message code='account'/>"/>
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12">
            <input class="form-control" type='password' name='password' placeholder="<spring:message code='password'/>"/>
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-12">
            <a href="javascript:void(0)" id="to-recover" class="text-dark pull-right"><i class="fa fa-lock m-r-5"></i> Forgot pwd?</a> </div>
        </div>
        <div class="form-group text-center m-t-20">
          <div class="col-xs-12">
            <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light" type="submit"><spring:message code='signIn'/></button>
          </div>
        </div>
        <div class="form-group m-b-0">
          <div class="col-sm-12 text-center">
            <p>Don't have an account? <a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/email" class="text-primary m-l-5"><b><spring:message code='signUp'/></b></a></p>
          </div>
        </div>
      </form>
      
      <form class="form-horizontal" id="recoverform" action="index.html">
        <div class="form-group ">
          <div class="col-xs-12">
            <h3>Recover Password</h3>
            <p class="text-muted">Enter your Email and instructions will be sent to you! </p>
          </div>
        </div>
        <div class="form-group ">
          <div class="col-xs-12">
            <input class="form-control" type="text" required="" placeholder="Email">
          </div>
        </div>
        <div class="form-group text-center m-t-20">
          <div class="col-xs-12">
            <button class="btn btn-primary btn-lg btn-block text-uppercase waves-effect waves-light" type="submit">Reset</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</section>

<script type="text/javascript">

	function doRegistration() {
		window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/email';
	}
    function doChangeLang(lang) {
        var url = '${pageContext.request.contextPath}/changeLanguage?langType='+lang+'&refresh='+window.location.pathname;
        window.location.href = url;
    }

</script>
<style>
#wrapper{
    background-image:url(${pageContext.request.contextPath}/resources/assets/images/background/GtqDqDn.jpg);
    background-repeat:no-repeat;
}
</style>