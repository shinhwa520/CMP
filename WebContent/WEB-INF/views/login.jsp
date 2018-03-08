<%@ include file="../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>

<section class="content">
	<div class="topic"><spring:message code="userLogin"/>
			<ul class="nav navbar-nav pull-right">
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
			</ul>
	</div>
	<form name='f' method='POST'>
		<input class="form-control" type='text' name='username' placeholder="<spring:message code='account'/>"/>
		<input class="form-control" type='password' name='password' placeholder="<spring:message code='password'/>"/>
		<input class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="<spring:message code='signIn'/>" />
		<input class="btn btn-lg btn-basic btn-block" type="button" name="registrationBtn" value="<spring:message code='signUp'/>" onclick="doRegistration()"/>
	</form>
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