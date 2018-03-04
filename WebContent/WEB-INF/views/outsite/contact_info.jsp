<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><spring:message code="contactUs"/></h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblMain">
			<thead>
				<tr>
					<th><spring:message code="name"/></th>
					<td>${userName}</td>
				</tr>
				<tr>
					<th><spring:message code="email"/></th>
					<td><a href="mailto:${userEmail}?Subject=test">${userEmail}</a></td>
				</tr>
				<tr>
					<th><spring:message code="wechatID"/></th>
					<td>${userWeChat}</td>
				</tr>
				<tr>
					<th><spring:message code="phoneNo"/></th>
					<td><span onclick="location.href='tel:${userPhone}'">${userPhone}</span></td>
				</tr>
			</thead>
		</table>
	</div>
</div>

</section>