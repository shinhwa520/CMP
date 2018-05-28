<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
	<div class="row page-titles">
		<div class="col-md-6 col-8 align-self-center">
			<h3 class="text-themecolor m-b-0 m-t-0"><spring:message code="productInfo" /></h3>
		</div>
	</div>
	
	
	<spring:message code='reward' var="reward"/>
	<div class="row el-element-overlay" id="guide_step1_tag">
		<h4 class="text-themecolor m-b-0 m-t-0">
			<span style="padding-left: 15px;">
				吉隆坡 Kuala Lumpur
			</span>
		</h4>
		<!-- column -->
		<div class="col-12 m-t-5">
			<div class="card-deck">
				<!-- Card -->
			  	<div class="card">
			  		<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1" id="guide_step5_tag"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_01.PNG" alt="8 Conlay-康莱" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(1);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content" id="guide_step8_tag">
		                    <h4 class="box-title">8 Conlay<br/>康莱</h4><br/><small style="color: blue;">${reward} : ${commission_1}%</small>
	                    </div>
	            	</div>
			  	</div>
			  	<!-- Card -->
			  	<!-- Card -->
			  	<div class="card">
			  		<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_02.PNG" alt="Arte-满家乐" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(2);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">Arte<br/>满家乐</h4><br/><small style="color: blue;">${reward} : ${commission_2}%</small>
	                    </div>
		            </div>
			  	</div>
			  	<!-- Card -->
			  	<!-- Card -->
			  	<div class="card">
			  		<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_03.PNG" alt="DC Residensi-白沙罗城豪华公寓" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(3);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">DC Residensi<br/>白沙罗城豪华公寓</h4><br/><small style="color: blue;">${reward} : ${commission_3}%</small>
	                    </div>
		            </div>
			  	</div>
			  	<!-- Card -->
			  	<!-- Card -->
			  	<div class="card">
			  		<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_04.PNG" alt="Opus-奥普斯" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(4);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">Opus<br/>奥普斯</h4><br/><small style="color: blue;">${reward} : ${commission_4}%</small>
	                    </div>
		            </div>
			  	</div>
			  	<!-- Card -->
			  	<!-- Card -->
			  	<div class="card">
			  		<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_05.PNG" alt="Ritz-Carlton-丽思卡尔顿" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(5);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">Ritz-Carlton<br/>丽思卡尔顿</h4><br/><small style="color: blue;">${reward} : ${commission_5}%</small>
	                    </div>
		            </div>
			  	</div>
			  	<!-- Card -->
			  	<!-- Card -->
			  	<div class="card">
			  		<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_06.PNG" alt="The Manor-大马金豪" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(6);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">The Manor<br/>大马金豪</h4><br/><small style="color: blue;">${reward} : ${commission_6}%</small>
		                </div>
			        </div>
			  	</div>
			</div>
		</div>
		<br/>
		<!-- column -->
		<div class="col-12 m-t-5">
			<div class="card-deck">
			  	<!-- Card -->
			  	<div class="card">
			  		<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_11.PNG" alt="Sunway-双威依斯干达" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(11);"><i class="icon-docs"></i></a></li>
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(12);"><i class="icon-picture"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">Sunway<br/>双威依斯干达</h4><br/><small style="color: blue;">${reward} : ${commission_11}%</small>
	                    </div>
	            	</div>
			  	</div>
			  	<!-- Card -->
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			</div>
		</div>
	</div>
	<br />
	<div class="row el-element-overlay" id="guide_step2_tag">
		<h4 class="text-themecolor m-b-0 m-t-0">
			<span style="padding-left: 15px;">
				新山 Johor
			</span>
		</h4>
		<!-- column -->
		<div class="col-12 m-t-5">
			<div class="card-deck">
				<!-- Card -->
				<div class="card">
				  	<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_07.PNG" alt="Avira-花园联排别墅" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(7);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">Avira<br/>花园联排别墅</h4><br/><small style="color: blue;">${reward} : ${commission_7}%</small>
		                </div>
		            </div>
			  	</div>
			  	<!-- Card -->
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			</div>
		</div>
	</div>
	<br />
	<div class="row el-element-overlay" id="guide_step3_tag">
		<h4 class="text-themecolor m-b-0 m-t-0">
			<span style="padding-left: 15px;">
				马六甲 Malacca
			</span>
		</h4>
		<!-- column -->
		<div class="col-12 m-t-5">
			<div class="card-deck">
				<!-- Card -->
				<div class="card">
				  	<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_08.PNG" alt="Haleton Towers-聚富湾" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(8);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">Haleton Towers<br/>聚富湾</h4><br/><small style="color: blue;">${reward} : ${commission_8}%</small>
		                </div>
		            </div>
			  	</div>
			  	<!-- Card -->
			  	<!-- Card -->
				<div class="card">
				  	<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_09.PNG" alt="Regalia Beachfront Residence-皇庭海湾1号" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(9);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">Regalia Beachfront Residence<br/>皇庭海湾1号</h4><br/><small style="color: blue;">${reward} : ${commission_9}%</small>
		                </div>
		            </div>
			  	</div>
			  	<!-- Card -->
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			</div>
		</div>
	</div>
	<br />
	<div class="row el-element-overlay" id="guide_step4_tag">
		<span style="padding-left: 15px;">
			<h4 class="text-themecolor m-b-0 m-t-0">槟城 Penang</h4>
		</span>
		<!-- column -->
		<div class="col-12 m-t-5">
			<div class="card-deck">
				<!-- Card -->
				<div class="card">
				  	<div class="el-card-item">
		                <div class="el-card-avatar el-overlay-1"> <img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_10.PNG" alt="18 East At Andaman-安达曼海景公寓" />
		                    <div class="el-overlay">
		                        <ul class="el-info">
		                            <li><a class="btn default btn-outline" href="javascript:btnFileClicked(10);"><i class="icon-docs"></i></a></li>
		                        </ul>
		                    </div>
		                </div>
		                <div class="el-card-content">
		                    <h4 class="box-title">18 East At Andaman<br/>安达曼海景公寓</h4><br/><small style="color: blue;">${reward} : ${commission_10}%</small>
		                </div>
		            </div>
			  	</div>
			  	<!-- Card -->
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			  	<div class="card" style="background: #f2f7f8">&nbsp;</div>
			</div>
		</div>
	</div>
</section>

<!--.燈箱 File -->
<div class="modal fade bs-example-modal-lg" id="modal_File" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title"><spring:message code="file"/></h4>
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="guide_step7_tag">×</button>
      	</div>
		<div class="modal_msg" style="display: none"></div>
      	<div class="modal-body">
            <form role="form" id="formFile" name="formFile">
				<input type="hidden" name="fileType" id="queryFileType" value="PRODUCT" />
				<input type="hidden" name="productId" id="productId" value="" />
				<div class="box box-primary">
					<div class="box-body no-padding">
						<table class="table table-striped" id="productFileMain" width="100%">
							<thead>
								<tr>
									<th>#</th>
									<th><spring:message code="fileName"/></th>
									<th><spring:message code="fileSize"/></th>
									<!-- 
									<th><spring:message code="downloadTimes"/></th>
									<th><spring:message code="fileDescription"/></th>
									<th><spring:message code="updatedTime"/></th>
									 -->
									<th style="width: 50px;"><spring:message code="option"/></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div><!-- /.modal-content -->
  </div>
</div>
<!--/.燈箱 File -->

<script type="text/javascript">

	var productFileMain;

	Number.prototype.format = function(n, x) {
	    var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
	    return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
	};

	/*
	$.Elastislide.defaults = {
		// orientation 'horizontal' || 'vertical'
		orientation : 'horizontal',

		// sliding speed
		speed : 500,

		// sliding easing
		easing : 'ease-in-out',

		// the minimum number of items to show.
		// when we resize the window, this will make sure minItems are always shown
		// (unless of course minItems is higher than the total number of elements)
		minItems : 3,

		// index of the current item (left most item of the carousel)
		start : 0,

		// click item callback
		onClick : function( el, position, evt ) { return false; },
		onReady : function() { return false; },
		onBeforeSlide : function() { return false; },
		onAfterSlide : function() { return false; }
	};
	
	
	$( '#area_1' ).elastislide();
	$( '#area_2' ).elastislide();
	$( '#area_3' ).elastislide();
	$( '#area_4' ).elastislide();
	*/
	
	$(function() {
		productFileMain = $('#productFileMain').DataTable(
				{
					"bFilter" : false,
					"ordering" : false,
					"info" : false,
					"serverSide" : true,
					"bLengthChange" : false,
					"ajax" : {
						"url" : '${pageContext.request.contextPath}/product/getProductFiles.json',
						"type" : 'POST',
						"data" : function(d) {
									d.productId = $('#productId').val(),
									d.isAdmin = false
								}
					},
					"columns" : [
						{ "data" : "dataSeq" },
						{ "data" : "fullFileName" },
						{ "data" : "fileSize", "render": function ( data, type, full, meta ) {
														      return data.format() + ' KB';
													    } }
						/*
						{ "data" : "downloadTimes" },
						{ "data" : "fileDescription" },
						{ "data" : "updateTime" }
						*/
					],
					"columnDefs" : [
						{
							"targets" : 0,
						    render: function (data, type, row, meta) {
						        return meta.row + meta.settings._iDisplayStart + 1;
						    }
						},
						{
							"targets" : 3,
							"data" : 'seqNo',
							"render" : function(data, type, row) {
								return '<div id="guide_step6_tag" style="width:20px"><a href="#">'
										+'<span class="icon-cloud-download" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnDownloadClicked($(this));" title="<spring:message code="download"/>"></span></a>'
										+'</div>';
							}
						}
					],
					select: true
				});
	});

	//[File] 進入modal_File
	function btnFileClicked(productId) {
		$('#productId').val(productId);

		//if (productFileMain) {
		//	$('#productFileMain').DataTable().destroy();
		//}

		productFileMain.ajax.reload();	//重查資料

		$('#modal_File').modal();
		
		setTimeout(function() {
			showGuideTour();
		}, 500);
	}
	
	//[Download] 按下Download按鈕
	function btnDownloadClicked(btn) {
		var downloadUrl = "${pageContext.request.contextPath}/manage/productFile/download?seqNo="+btn.attr('seqNo')+"&fileType="+btn.attr('fileType')+"&fromPage=product/list";
	  	window.location.href = downloadUrl;

	  	setTimeout(function(){
	  		if (productFileMain) {
	  			productFileMain.ajax.reload();
	  		}
	  	}, 2000);
	}
</script>

	<script>
	    var showGuide = '${showGuide}';
	    var isHome = '${active}';
	    var title_product = '<spring:message code="guide_tour_msg_41"/>'; //『产品信息』导览说明
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
						var reString = "<div class='popover tour' style='min-width:500px;  max-width:555px;'><div class='arrow'></div><h3 class='popover-title'></h3><div class='popover-content'></div>";
							reString += "<div class='popover-navigation'><button class='btn btn-default' data-role='prev'>« <spring:message code='prev' /></button><span data-role='separator'>|</span><button class='btn btn-default' data-role='next'><spring:message code='next' /> »</button>";
							reString += "<button class='btn float-right' onclick='doCloseGuide();' aria-hidden='true'><i class='fa fa-ban'></i> <spring:message code='noShowAgain' /></button><button class='btn btn-default float-right' data-role='end'><spring:message code='endTour' /></button></div></div>";
							
						return reString;
					}
				,
				steps: [
	    	  		{
	    	    		element: "#guide_step1_tag",
	    	    		title: title_product + " - 1/9",
	    	    		content: '<spring:message code="guide_tour_msg_42"/>', //依地區分別呈顯產品列表 >> 吉隆坡
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step2_tag",
	    	    		title: title_product + " - 2/9",
	    	    		content: '<spring:message code="guide_tour_msg_43"/>', //依地區分別呈顯產品列表 >> 新山
	    	    		placement: "top",
	    	    		animation: false
	    	    		
	    	  		},
	    	  		{
	    	    		element: "#guide_step3_tag",
	    	    		title: title_product + " - 3/9",
	    	    		content: '<spring:message code="guide_tour_msg_44"/>', //依地區分別呈顯產品列表 >> 马六甲
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step4_tag",
	    	    		title: title_product + " - 4/9",
	    	    		content: '<spring:message code="guide_tour_msg_45"/>', //依地區分別呈顯產品列表 >> 槟城
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step5_tag",
	    	    		title: title_product + " - 5/9",
	    	    		content: '<spring:message code="guide_tour_msg_46"/>', //滑鼠移至产品图上呈显档案下载图示;点击图示进入档案下载画面
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onHidden: function() {
	    	    			btnFileClicked(1);
	    	    		},
	    	    		onShown: function() {
	    	    			$('.btn[data-role="next"]').removeClass("disabled");
	    	    			$('.btn[data-role="next"]').prop("disabled", false);
	    	    			$('.btn[data-role="next"]').on("click", function (e) {
	    	                    tour.end();
	    	                });
	    	    		}
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
	    
	    function showGuideTour() {
	      if("Y"===showGuide) {
			//showTip();
	    	// Instance the tour
			var tour = new Tour({
				backdrop: true,
				smartPlacement: true,
				storage: false,
				template: 
					function (key, value) { 
						var reString = "<div class='popover tour' style='min-width:500px;  max-width:555px;'><div class='arrow'></div><h3 class='popover-title'></h3><div class='popover-content'></div>";
						
						if (key != 4) {
							reString += "<div class='popover-navigation'><button class='btn btn-default' data-role='prev'>« <spring:message code='prev' /></button><span data-role='separator'>|</span><button class='btn btn-default' data-role='next'><spring:message code='next' /> »</button>";
						}
						
						reString += "<button class='btn float-right' onclick='doCloseGuide();' aria-hidden='true'><i class='fa fa-ban'></i> <spring:message code='noShowAgain' /></button><button class='btn btn-default float-right' data-role='end'><spring:message code='endTour' /></button></div></div>";
							
						return reString;
					}
				,
				steps: [
	    	  		{
	    	    		element: "#productFileMain",
	    	    		title: title_product + " - 6/9",
	    	    		content: '<spring:message code="guide_tour_msg_47"/>', //检视该产品提供下载的档案资料
	    	    		placement: "top",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#guide_step6_tag",
	    	    		title: title_product + " - 7/9",
	    	    		content: '<spring:message code="guide_tour_msg_48"/>', //点击云图示可下载档案
	    	    		placement: "bottom",
	    	    		animation: false
	    	    		
	    	  		},
	    	  		{
	    	    		element: "#guide_step7_tag",
	    	    		title: title_product + " - 8/9",
	    	    		content: '<spring:message code="guide_tour_msg_49"/>', //点击X图示关闭画面
	    	    		placement: "bottom",
	    	    		animation: false,
	    	    		onHidden: function() {
	    	    			$('#guide_step7_tag').click();
	    	    		}
	    	  		},
	    	  		{
	    	    		element: "#guide_step8_tag",
	    	    		title: title_product + " - 9/9",
	    	    		content: '<spring:message code="guide_tour_msg_50"/>', //呈显个人于此产品佣金％数
	    	    		placement: "bottom",
	    	    		animation: false
	    	  		},
	    	  		{
	    	    		element: "#referenceInfo-tag",
	    	    		title: '<spring:message code="guide_tour_msg_51"/>', //『参考信息』导览说明
	    	    		content: '<spring:message code="guide_tour_msg_9"/>', //请点击功能选单进入下一阶段导览
	    	    		animation: false
	    	    		/*,
	    	    		onHidden: function() {
	    	    			$('#referenceInfo-menu').get(0).click();
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
	    }
	</script>