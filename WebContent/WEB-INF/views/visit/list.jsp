<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">

	<div class="row page-titles">
	     <div class="col-md-6 col-8 align-self-center">
	         <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="visitInfo" /></h3>
	     </div>
	 </div>
	 
	<div class="row">
		<form:form method="POST" modelAttribute="VisitForm" action="">
			<input type="hidden" name="visitId" id="visitId" value="" />
			
			<div id="ca-container" class="ca-container">
				<div class="ca-wrapper">
				
					<!-- Start. 圖片區塊 -->
					<div class="ca-item">
						<div class="ca-item-main"> <img src="${pageContext.request.contextPath}/resources/product_2018_0002.jpg" alt="魅力大馬">
							<h3> 魅力大馬 </h3>
							<p> <span> 异域海岛 / 一带一路 / 第二家园 </span> </p>
							<div class="ca-more-wrap"><a class="ca-more" href="#">more...</a></div>
						</div>
						<div class="ca-content-wrapper">
							<div class="ca-content">
								<h6> 吉隆坡 / 新山 / 马六甲 &nbsp;&nbsp;五天四晚考察团 </h6>
								<a href="#" class="ca-close">close</a>
								<div class="ca-content-text">
									<p>
										<b>机场接送</b><br/>
										&nbsp;&nbsp;&nbsp;&nbsp;旅游巴士，专业导游接送机
									</p>
									<p>
										<b>星级食宿</b><br/>
										&nbsp;&nbsp;&nbsp;&nbsp;全程5星酒店住宿，当地特色餐招待
									</p>
									<p>
										<b>特别行程</b><br/>
										&nbsp;&nbsp;&nbsp;&nbsp;为您筛选最具投资价值的项目深入考察
									</p>
								</div>
								<%--
								<ul>
									<li><a href="#" onclick="btnKpiClicked(1);"><spring:message code="KPI" /></a></li>
								</ul>
								 --%>
								<ul>
									<li><a href="#" onclick="btnIntroClicked(1,'JUMP');"><spring:message code="downloadProductImageLink" /></a></li>
									<li><a href="#" onclick="btnDownloadClicked(1,'DM1',false);"><spring:message code="downloadMarketingPoster" />1</a></li>
									<li><a href="#" onclick="btnDownloadClicked(1,'DM2',false);"><spring:message code="downloadMarketingPoster" />2</a></li>
									<li><a href="#" onclick="btnDownloadPdfClicked(1);"><spring:message code="downloadPdf" /></a></li>
									<li><a href="#" onclick="btnScheduleClicked(1);"><spring:message code="downloadSchedule" /></a></li>
								</ul>
								<ul>
									<li><a href="#" onclick="btnIntroClicked(1,'COPY');"><spring:message code="copyProductImageLink" /></a></li>
								</ul>
								<br />
							</div>
						</div>
					</div>
					<!-- End. 圖片區塊 -->
					
				</div>
			</div>
		</form:form>	 
		 
		<div id="multiple_slides_visible">
		
		</div>
	</div>
	
</section>

<script type="text/javascript">

	$('#ca-container').contentcarousel({

		// speed for the sliding animation
		sliderSpeed : 500,

		// easing for the sliding animation
		sliderEasing: 'easeInOutQuart',	//easeOutExpo

		// speed for the item animation (open / close)
		itemSpeed   : 500,

		// easing for the item animation (open / close)
		itemEasing  : 'easeInOutCubic',

		// number of items to scroll at a time
		scroll  : 3

	});
	
	/*
	$.getJSON("${pageContext.request.contextPath}/channel/productInfo/getProductsData.json", function(data) {
        $(document).ready(function(){
            $("#multiple_slides_visible").agile_carousel({
                carousel_data: data,
                carousel_outer_height: 450, //最外圍框高度
                carousel_height: 444, //最外圍高度
                slide_height: 440,	//一張圖的高度
                carousel_outer_width: 400,
                slide_width: 420,	//一張圖的寬度
                number_slides_visible: 3,
                transition_time: 330,
                control_set_1: "previous_button,next_button",
                control_set_2: "group_numbered_buttons",
                persistent_content: "<p class='persistent_content'>產品清單</p>"       
            });
        });
    });
	*/
	
	var formAction;
	
	//[btn_KPI]
	function btnKpiClicked(visitId) {
		var url = "${pageContext.request.contextPath}/channel/productInfo/viewKPI/" + visitId;
		window.location.href = url;
	}
	
	//[btn_Intro]
	function btnIntroClicked(visitId, action) {
		$.ajax({
			url : '${pageContext.request.contextPath}/channel/personalInfo/getDMUrl',
			data : {
				"visitId" : visitId
			},
			type : "POST",
			dataType : 'json',
			async: false,

			success : function(resp) {
				if (resp.code == '200') {
					if (resp.data.errorMsg != null) {
						alert(resp.data.errorMsg);
						
					} else {
						if (action == 'JUMP') {
							window.open(resp.data.channelUrl, '_blank');
							
						} else if (action == 'COPY') {
							copyToClipboard(resp.data.channelUrl);
						}
					}
					//successMsgModal(resp.message);
				} else {
					alert(resp.message);
				}
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
	}
	
	//[btn_Download]
	function btnDownloadClicked(visitId, fileCategory, useZip) {
		var downloadUrl = "${pageContext.request.contextPath}/manage/file/downloadVisitFiles?visitId="+visitId+"&fileType=VISIT&fileCategory="+fileCategory+"&useZip="+useZip+"&fromPage=visit/list";
	    window.location.href = downloadUrl;
	}
	
	//[btn_Download_PDF]
	function btnDownloadPdfClicked(visitId) {
		var downloadUrl = "${pageContext.request.contextPath}/manage/file/downloadVisitPdf?visitId="+visitId+"&fileType=VISIT&fileCategory=PDF&fromPage=visit/list";
	    window.location.href = downloadUrl;
	}
	
	//[btn_Download_Schedule]
	function btnScheduleClicked(visitId) {
		var downloadUrl = "${pageContext.request.contextPath}/manage/file/downloadSchedule?visitId="+visitId+"&fileType=VISIT&fileCategory=SCHEDULE&fromPage=visit/list";
	    window.location.href = downloadUrl;
	}
	
	// Copies a string to the clipboard. Must be called from within an 
	// event handler such as click. May return false if it failed, but
	// this is not always possible. Browser support for Chrome 43+, 
	// Firefox 42+, Safari 10+, Edge and IE 10+.
	// IE: The clipboard feature may be disabled by an administrator. By
	// default a prompt is shown the first time the clipboard is 
	// used (per session).
	function copyToClipboard(text) {
		
	    if (window.clipboardData && window.clipboardData.setData) {
	        // IE specific code path to prevent textarea being shown while dialog is visible.
	        return clipboardData.setData("Text", text); 

	    } else if (document.queryCommandSupported && document.queryCommandSupported("copy")) {
	        var textarea = document.createElement("textarea");
	        textarea.textContent = text;
	        textarea.style.position = "fixed";  // Prevent scrolling to bottom of page in MS Edge.
	        document.body.appendChild(textarea);
	        textarea.select();
	        try {
	            return document.execCommand("copy");  // Security exception may be thrown by some browsers.
	        } catch (ex) {
	            console.warn("Copy to clipboard failed.", ex);
	            return false;
	        } finally {
	        	alert('<spring:message code="copySuccessMsg" />!! (' + textarea.value + ')');
	            document.body.removeChild(textarea);
	        }
	    }
	}

</script>