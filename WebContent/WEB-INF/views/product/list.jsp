<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><spring:message code="productInfo" /></h3>
	</div>
	<br />
	<div class="main">
		<header>
			<h1>吉隆坡  Kuala Lumpur</h1>
		</header>
		<!-- Elastislide Carousel -->
		<ul id="area_1" class="elastislide-list">
			<li><div align="center">康莱</div><div><a href="#" onClick="btnFileClicked(1)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_01.PNG" alt="image01" width="150px" height="150px" /></a></div></li>
			<li><div align="center">Arte满家乐</div><a href="#" onClick="btnFileClicked(2)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_02.PNG" alt="image02" width="150px" height="150px" /></a></li>
			<li><div align="center">白沙罗城豪华公寓</div><a href="#" onClick="btnFileClicked(3)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_03.PNG" alt="image03" width="150px" height="150px" /></a></li>
			<li><div align="center">奥普斯</div><a href="#" onClick="btnFileClicked(4)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_04.PNG" alt="image04" width="150px" height="150px" /></a></li>
			<li><div align="center">丽思卡尔顿</div><a href="#" onClick="btnFileClicked(5)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_05.PNG" alt="image05" width="150px" height="150px" /></a></li>
			<li><div align="center">大马金豪</div><a href="#" onClick="btnFileClicked(6)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_06.PNG" alt="image06" width="150px" height="150px" /></a></li>
		</ul>
	</div>
	<br />
	<div class="main">
		<header>
			<h1>新山 Johor</h1>
		</header>
		<ul id="area_2" class="elastislide-list">
			<li><div align="center">Avira花园联排别墅</div><a href="#" onClick="btnFileClicked(7)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_07.PNG" alt="image07" width="150px" height="150px" /></a></li>
		</ul>
	</div>
	<br />
	<div class="main">
		<header>
			<h1>马六甲 Malacca</h1>
		</header>
		<ul id="area_3" class="elastislide-list">
			<li><div align="center">聚富湾</div><a href="#" onClick="btnFileClicked(8)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_08.PNG" alt="image08" width="150px" height="150px" /></a></li>
			<li><div align="center">皇庭海湾1号</div><a href="#" onClick="btnFileClicked(9)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_09.PNG" alt="image09" width="150px" height="150px" /></a></li>
		</ul>
	</div>
	<br />
	<div class="main">
		<header>
			<h1>槟城 Penang</h1>
		</header>
		<ul id="area_4" class="elastislide-list">
			<li><div align="center">安达曼海景公寓</div><a href="#" onClick="btnFileClicked(10)"><img src="${pageContext.request.contextPath}/resources/elastislide/images/pic/P_10.PNG" alt="image10" width="150px" height="150px" /></a></li>
		</ul>
		<!-- End Elastislide Carousel -->
	</div>
	<br />
</div>
</section>

<!--.燈箱 File -->
<div class="modal fade bs-example-modal-lg" id="modal_File" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title"><spring:message code='file'/></h4>
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
									<th><spring:message code="downloadTimes"/></th>
									<th><spring:message code="fileDescription"/></th>
									<th><spring:message code="updatedTime"/></th>
									<th style="width: 100px;"><spring:message code="option"/></th>
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
									d.productId = $('#productId').val()
								}
					},
					"columns" : [
						{ "data" : "dataSeq" },
						{ "data" : "fullFileName" },
						{ "data" : "fileSize", "render": function ( data, type, full, meta ) {
														      return data.format() + ' KB';
													    } },
						{ "data" : "downloadTimes" },
						{ "data" : "fileDescription" },
						{ "data" : "updateTime" }
					],
					"columnDefs" : [
						{
							"targets" : 6,
							"data" : 'seqNo',
							"render" : function(data, type, row) {
								return '<a href="#">'
										+'<span class="label label-success pull-center" style="margin-right:10px" fileType="'+ row['fileType'] + '" seqNo="' + row['seqNo'] + '" onclick="btnDownloadClicked($(this));">'
										+'<i class="fa fa-close" style="margin-right:5px"></i><spring:message code="download"/></span></a>';
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
	}
	
	//[Download] 按下Download按鈕
	function btnDownloadClicked(btn) {
		var downloadUrl = "${pageContext.request.contextPath}/manage/file/download?seqNo="+btn.attr('seqNo')+"&fileType="+btn.attr('fileType')+"&fromPage=product/list";
	  	window.location.href = downloadUrl;

	  	setTimeout(function(){
	  		if (productFileMain) {
	  			productFileMain.ajax.reload();
	  		}
	  	}, 2000);
	}
</script>