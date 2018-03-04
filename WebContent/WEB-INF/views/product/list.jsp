<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><spring:message code="productInfo" /></h3>
	</div>
	
	<form:form method="POST" modelAttribute="ProductForm" action="">
		<input type="hidden" name="productId" id="productId" value="" />
		
		<div id="ca-container" class="ca-container">
			<div class="ca-wrapper">
			
				<!-- 圖片區塊 -->
				<div class="ca-item">
					<div class="ca-item-main"> <img src="${pageContext.request.contextPath}/resources/product_2018_0001.jpg" alt="魅力大馬">
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
							<ul>
								<li><a href="#" onclick="btnKpiClicked(1);"><spring:message code="KPI" /></a></li>
								<li><a href="#" onclick="btnIntroClicked(1);"><spring:message code="downloadProductImageLink" /></a></li>
								<li><a href="#" onclick="btnDownloadClicked(1);"><spring:message code="downloadMarketingPoster" /></a></li>
							</ul>
						</div>
					</div>
				</div>
				
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
	function btnKpiClicked(productId) {
		var url = "${pageContext.request.contextPath}/channel/productInfo/viewKPI/" + productId;
		window.location.href = url;
	}
	
	//[btn_Intro]
	function btnIntroClicked(productId) {
		$.ajax({
			url : '${pageContext.request.contextPath}/channel/personalInfo/getDMUrl',
			data : {
				"productId" : productId
			},
			type : "POST",
			dataType : 'json',
			async: false,

			success : function(resp) {
				if (resp.code == '200') {
					if (resp.data.errorMsg != null) {
						alert(resp.data.errorMsg);
						
					} else {
						window.open(resp.data.channelUrl, '_blank');
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
	function btnDownloadClicked(productId) {
		var downloadUrl = "${pageContext.request.contextPath}/manage/file/downloadProducts?productId="+productId+"&fileType=PRODUCT&fromPage=product/list";
	    window.location.href = downloadUrl;
	}

</script>