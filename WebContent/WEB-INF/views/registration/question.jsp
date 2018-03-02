<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="topic">please answer the following questions :</div>
	<form:form method="POST" modelAttribute="UserInfoForm">
		<form:hidden path="userId" id="userId" />
		<c:if test="${!UserInfoForm.quesMap.isEmpty() }">
			<c:forEach var="vo" items="${ UserInfoForm.quesMap }">
				<span style="color: red;">＊ </span>${vo.key.content }<br/>
					<c:forEach var="detail" items="${ vo.value }">
						<input type="radio" name=${vo.key.id } value=${detail.id } id=${detail.id }> ${detail.content }<br/>
					</c:forEach>
					<br/>
			</c:forEach>
					<input class="btn btn-lg btn-success btn-block" type="button" name="submitBtn" value="提交答案  完成註冊" onclick="doSubmit()"/>
		</c:if>
	</form:form>
</section>


<script type="text/javascript">
	var itemCount = ${UserInfoForm.quesMap.keySet().size()};
	var results;
	var resultCount;
	
	function doSubmit() {
		var _userId = $('#userId').val();
		var getResult = function () {
		    var result = [];
		    $('input:radio').each(function () {
		        var $this = $(this), id = $this.attr('id');
		        if ($(this).prop('checked')) {
		            result.push(id);
		        }
		    });
		    return result;
		};
		results = getResult().join(',');
		resultCount = results.split(',').length;
		console.log("itemCount :" + itemCount);
		console.log("results :" + results);
		console.log("resultCount :" + resultCount);
		if(''==results || resultCount<itemCount){
			errorMessage("下列問題皆為必選，請再次確認後提交!");
			return false;
		}
		window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/upstream?userId='+_userId;
		/*
		$.ajax({
				url : '${pageContext.request.contextPath}/registration/ans?id=' + _userId + '&results=' +results,
				type : "GET",
				dataType : 'json',
				async: false,
				contentType:"application/json;charset=utf-8", 
				success : function(data) {
                    if (data.status === 200) {
                    	window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/registration/upstream?userId='+_userId;                        
                    } else {
                        errorMessage(data.message);
                    }
				},

				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}
			});
		*/
	}

</script>
