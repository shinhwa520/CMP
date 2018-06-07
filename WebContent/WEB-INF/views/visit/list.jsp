<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<style>
	.tour-backdrop {
	  z-index: 19998;
	}
	
	.tour-step-backdrop {
	  z-index: 19999;
	}
	
	.tour-step-background {
	  z-index: 19999;
	}
	.popover[class*="tour-"] {
	  z-index: 19999;
	}
</style>
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
						<div class="ca-item-main" id="guide_step1_tag"> <img src="${pageContext.request.contextPath}/resources/product_2018_0002.jpg" alt="魅力大馬">
							<h3> 魅力大馬 </h3>
							<p> <span> 异域海岛 / 一带一路 / 第二家园 </span> </p>
							<div class="ca-more-wrap" id="guide_step2_tag"><a class="ca-more" href="#">more...</a></div>
						</div>
						<div class="ca-content-wrapper" id="guide_step3_tag">
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
									<li id="guide_step4_tag"><a href="#" onclick="btnIntroClicked(1,'JUMP');"><spring:message code="downloadProductImageLink" /></a></li>
									<li id="guide_step5_tag"><a href="#" onclick="btnDownloadClicked(1,'DM1',false);"><spring:message code="downloadMarketingPoster" />1</a></li>
									<li id="guide_step6_tag"><a href="#" onclick="btnDownloadClicked(1,'DM2',false);"><spring:message code="downloadMarketingPoster" />2</a></li>
									<li id="guide_step7_tag"><a href="#" onclick="btnDownloadPdfClicked(1);"><spring:message code="downloadPdf" /></a></li>
									<li id="guide_step8_tag"><a href="#" onclick="btnScheduleClicked(1);"><spring:message code="downloadSchedule" /></a></li>
								</ul>
								<ul>
									<li id="guide_step9_tag"><a href="#" onclick="btnIntroClicked(1,'COPY');"><spring:message code="copyProductImageLink" /></a></li>
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
		var downloadUrl = "${pageContext.request.contextPath}/manage/visitFile/downloadVisitFiles?visitId="+visitId+"&fileType=VISIT&fileCategory="+fileCategory+"&useZip="+useZip+"&fromPage=visit/list";
	    window.location.href = downloadUrl;
	}
	
	//[btn_Download_PDF]
	function btnDownloadPdfClicked(visitId) {
		var downloadUrl = "${pageContext.request.contextPath}/manage/visitFile/downloadVisitPdf?visitId="+visitId+"&fileType=VISIT&fileCategory=PDF&fromPage=visit/list";
	    window.location.href = downloadUrl;
	}
	
	//[btn_Download_Schedule]
	function btnScheduleClicked(visitId) {
		var downloadUrl = "${pageContext.request.contextPath}/manage/visitFile/downloadSchedule?visitId="+visitId+"&fileType=VISIT&fileCategory=SCHEDULE&fromPage=visit/list";
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

	<script>
	    var showGuide = '${showGuide}';
	    var isHome = '${active}';
	    var title_visit = '<spring:message code="guide_tour_msg_54"/>'; //『参团信息』导览说明
	    $(document).ready(function(){
	    //function showGuideTour() {
	      if("Y"===showGuide) {
			//showTip();
	    	// Instance the tour
			var tour = new Tour({
				backdrop: true,
				smartPlacement: true,
				storage: false,
				template: 
					function (key, value) { 
						var isNotLastStep = false;
						if (key != 9) {
							isNotLastStep = true
						}
						return getGuideTourTemplate(isNotLastStep);
					}
				,
				steps: [
	    	  		{
	    	    		element: "#guide_step1_tag",
	    	    		title: title_visit + " - 1/9",
	    	    		content: '<spring:message code="guide_tour_msg_55"/>', //呈显当前参团清单
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step2_tag",
	    	    		title: title_visit + " - 2/9",
	    	    		content: '<spring:message code="guide_tour_msg_56"/>', //点击「more」按钮展开更多资讯
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onNext: function() {
	    	    			$('.ca-more').click();
	    	    	        //return (new jQuery.Deferred()).promise();
	    	    	    }
	    	    		
	    	  		},
	    	  		{
	    	    		element: "#guide_step3_tag",
	    	    		title: title_visit + " - 3/9",
	    	    		content: '<spring:message code="guide_tour_msg_57"/>', //检视参团详细内容
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step4_tag",
	    	    		title: title_visit + " - 4/9",
	    	    		content: '<spring:message code="guide_tour_msg_58"/>', //点击检视客制化参团H5行销网页
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step5_tag",
	    	    		title: title_visit + " - 5/9",
	    	    		content: '<spring:message code="guide_tour_msg_59"/>', //点击下载客制化参团行销海报
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step6_tag",
	    	    		title: title_visit + " - 6/9",
	    	    		content: '<spring:message code="guide_tour_msg_59"/>', //点击下载客制化参团行销海报
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step7_tag",
	    	    		title: title_visit + " - 7/9",
	    	    		content: '<spring:message code="guide_tour_msg_60"/>', //点击下载客制化参团行销PDF
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step8_tag",
	    	    		title: title_visit + " - 8/9",
	    	    		content: '<spring:message code="guide_tour_msg_61"/>', //点击下载参团行程表
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step9_tag",
	    	    		title: title_visit + " - 9/9",
	    	    		content: '<spring:message code="guide_tour_msg_62"/>', //点击复制客制化参团H5行销网页网址
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#registration-tag",
	    	    		title: '<spring:message code="guide_tour_msg_63"/>', //『报团』导览说明
	    	    		content: '<spring:message code="guide_tour_msg_9"/>', //请点击功能选单进入下一阶段导览
	    	    		animation: false
	    	    		/*,
	    	    		onHidden: function() {
	    	    			$('#registration-menu').get(0).click();
	    	    		}
	    	    		*/
	    	  		}
	    		]});
	
	    	// Initialize the tour
	    	tour.init();
	
	    	// Start the tour
	    	tour.start();
	    	//tour.restart();
	      }
	    });
	    //}
	</script>