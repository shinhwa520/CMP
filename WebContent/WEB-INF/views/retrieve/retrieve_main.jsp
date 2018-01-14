<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/taglib.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

	<script type="text/javascript">
	
		function doRetrieve(apiDetailId) {
			alert(apiDetailId);	
		}
		
	</script>

</head>
<body>
API

	<form:form method="POST" modelAttribute="MsgForm" action="/cmp/retrieve/query">
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
					<input type="submit" value="查詢" />
				</td>
			</tr>
		</table>
	</form:form>
	<br />
	<c:if test="${!MsgForm.apiList.isEmpty() }">
		<table>
			<c:forEach var="vo" items="${ MsgForm.apiList }">
				<tr>
					<td>${vo.webName }</td>
					<td>${vo.detailSeqNo }</td>
					<td>${vo.parameterValues }</td>
					<td>${vo.detailDescription }</td>
					<td><input type="button" name="retrieveBtn" value="抓取資料" onclick="doRetrieve(${vo.apiDetailId})"/></td>
					<td><input type="checkbox" name="chkBox" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>