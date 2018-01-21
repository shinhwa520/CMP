<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">

	<script type="text/javascript" src="/cmp/dwr/engine.js"></script>
	<script type='text/javascript' src='/cmp/dwr/interface/MsgDwr.js'></script>
	<script type="text/javascript">
	
		function doQuery() {
			ApiForm.action = "${pageContext.request.contextPath}/api/query";
			ApiForm.submit();
		}
		
		function doRetrieve() {
			/*
			var para = ["111"];
			ApiDwr.doRetrieve(para, getRetrieve);
			*/
			ApiForm.action = "${pageContext.request.contextPath}/api/retrieve";
			ApiForm.submit();
		}
		
		function getRetrieve() {
			alert("after retrieve");
		}
		
		function checkIdCheckBox(index) {
			ApiForm.apiModelIds[index].checked = ApiForm.chkedApiUrls[index].checked;
		}
		
		function doCheckAll() {
			for (var i=0; i<ApiForm.chkedApiUrls.length; i++) {
				ApiForm.chkedApiUrls[i].checked = ApiForm.chkAll.checked;
				ApiForm.apiModelIds[i].checked = ApiForm.chkAll.checked;
			}
		}
		
	</script>
API

	<form:form method="POST" modelAttribute="ApiForm" action="" name="ApiForm">
		<table>
			<tr>
				<td>網站名稱:</td>
				<td>
					<select name="webName">
						<option value="">=== 請選擇 ===</option>
						<option value="MAKA">MAKA</option>
					</select>
				</td>
				<td>
					<input type="button" name="query" value="查詢" onclick="doQuery()" />
				</td>
			</tr>
		</table>
	
		<br />
		<c:if test="${!ApiForm.apiList.isEmpty() }">
			<table>
				<tr>
					<td>網站名稱</td>
					<td>序</td>
					<td>ID</td>
					<td>描述</td>
					<td>操作<input type="checkbox" name="chkAll" onclick="doCheckAll()"/></td>
				</tr>
				<c:forEach var="vo" items="${ ApiForm.apiList }" varStatus="loop">
					<tr>
						<td>${vo.webName }</td>
						<td>${loop.index+1 }</td>
						<td>${vo.parameterValues }</td>
						<td>${vo.detailDescription }</td>
						<td>
							<c:if test="${vo.userActive == 'Y' }">
								<input type="checkbox" name="chkedApiUrls" value="${vo.apiUrl }" onchange="checkIdCheckBox(${loop.index })"/>
								<input type="checkbox" name="apiModelIds" value="${vo.makaId }" style="display: none"/>
							</c:if>
							<c:if test="${vo.userActive == 'N' }">
								<input type="checkbox" name="chkedApiUrls" value="${vo.apiUrl }" onchange="checkIdCheckBox(${loop.index })" disabled="disabled"/>
								<input type="checkbox" name="apiModelIds" value="${vo.makaId }" style="display: none" disabled="disabled"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4">&nbsp;</td>
					<td>
						<input type="button" name="retrieve" value="抓取資料" onclick="doRetrieve()" />
					</td>
				</tr>
			</table>
		</c:if>
		
		<form:hidden path="apiMethodType" />
	</form:form>

</section>