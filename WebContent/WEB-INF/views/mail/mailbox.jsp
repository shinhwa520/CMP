<%@ include file="../../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="org.springframework.web.servlet.HandlerMapping" %>
<section class="content">
	<div class="row page-titles">
	    <div class="col-md-6 col-8 align-self-center">
	        <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code='emailApp'/></h3>
	    </div>
	</div>
	<!-- ============================================================== -->
	<!-- Start Page Content -->
	<!-- ============================================================== -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="card">
	            <div class="row">
	                <div class="col-xlg-2 col-lg-4 col-md-4">
	                    <div class="card-body inbox-panel">
	                        <ul class="list-group list-group-full">
	                            <li id="composeOption" class="list-group-item" onclick="modalCompose();">
	                                <a href="javascript:void(0)"> <i class="mdi mdi-pen"></i> <spring:message code='compose'/> </a>
	                            </li>
	                            <li id="inboxOption" class="list-group-item active" onclick="viewInboxList();">
	                            	<a href="javascript:void(0)"><i class="mdi mdi-gmail"></i> <spring:message code='inbox'/> </a><span id="countUnread" class="badge badge-success ml-auto">0</span>
	                            </li>
	                            <li id="savedOption" class="list-group-item" onclick="viewSavedList();">
	                            	<a href="javascript:void(0)"><i class="mdi mdi-inbox-arrow-down"></i> <spring:message code='saved'/><spring:message code='mail'/> </a><span id="countSaved" class="badge badge-info ml-auto">0</span>
	                            </li>
	                            <li id="trashOption" class="list-group-item" onclick="viewTrashList();">
	                                <a href="javascript:void(0)"> <i class="mdi mdi-delete"></i> <spring:message code='deleted'/><spring:message code='mail'/> </a><span id="countNotAlive" class="badge badge-secondary ml-auto">0</span>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	                
	                <!-- mailbox_div ============================================================== -->
	                <div id="mailbox_div" class="col-xlg-10 col-lg-8 col-md-8">
	                    <div class="card-body">
	                        <div class="btn-group m-b-10 m-r-10" role="group" aria-label="Button group with nested dropdown">
	                        	<button type="button" class="btn btn-secondary font-18 text-dark" onclick="triggerMenuOption();" title="<spring:message code='refresh'/>" ><i class="mdi mdi-reload"></i></button>
	                        	<button type="button" class="btn btn-secondary font-18 text-dark move2Inbox" onclick="btnInboxClicked()" title="<spring:message code='moveTo'/>&nbsp;<spring:message code='inbox'/>" ><i class="mdi mdi-gmail"></i></button>
	                            <button type="button" class="btn btn-secondary font-18 text-dark move2Saved" onclick="btnSaveClicked()" title="<spring:message code='save'/>" ><i class="mdi mdi-inbox-arrow-down"></i></button>
	                            <button type="button" class="btn btn-secondary font-18 text-dark" onclick="btnDeleteClicked();" title="<spring:message code='delete'/>" ><i class="mdi mdi-delete"></i></button>
	                        </div>
	                    </div>
	                    <div class="card-body p-t-0">
	                        <div class="card b-all shadow-none">
	                            <div class="inbox-center b-all table-responsive">
	                                <table id="tblMain" class="table table-hover no-wrap">
	                                    <thead>
	                                    	<tr>
	                                    		<th style="width: 5%; padding-top: 20px;">
	                                    			<div class="checkbox"><input type="checkbox" name="optChkAll" id="optChkAll" ><label for="optChkAll" class="badge badge-secondary ml-auto" style="font-size: 90%;"><spring:message code='selectAll'/></label></div>
	                                    		</th>
	                                    		<th></th>
	                                    		<th></th>
	                                    		<th></th>
	                                    	</tr>
	                                    </thead>
	                                </table>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                
	                <!-- detail_div ============================================================== -->
	                <div id="detail_div" class="col-xlg-10 col-lg-8 col-md-8">
	                    <div class="card-body">
	                    	<!-- 返回 -->
	                    	<button id="backBTN" type="button" class="btn btn-secondary m-r-10 m-b-10 text-dark" onclick="doBack();"><i class="mdi mdi-keyboard-return font-18"></i></button>
	                        <div class="btn-group m-b-10 m-r-10" role="group" aria-label="Button group with nested dropdown">
	                        	<!-- 回复 -->
	                        	<button type="button" class="btn btn-secondary font-18 text-dark" onclick="doReply();" title="<spring:message code='reply'/>" ><i class="mdi mdi-reply"></i></button>
	                        	<!-- 转寄 -->
	                            <button type="button" class="btn btn-secondary font-18 text-dark" onclick="doForward();" title="<spring:message code='forwarding'/>" ><i class="mdi mdi-share"></i></button>
	                            <!-- 移至收件夹 -->
	                            <button type="button" class="btn btn-secondary font-18 text-dark move2Inbox" onclick="btnInboxOne();" title="<spring:message code='moveTo'/>&nbsp;<spring:message code='inbox'/>" ><i class="mdi mdi-gmail"></i></button>
	                            <!-- 封存 -->
	                            <button type="button" class="btn btn-secondary font-18 text-dark move2Saved" onclick="btnSaveOne();" title="<spring:message code='save'/>" ><i class="mdi mdi-inbox-arrow-down"></i></button>
		                        <!--  删除 -->
		                        <button type="button" class="btn btn-secondary font-18 text-dark" onclick="btnDeleteOne();" title="<spring:message code='delete'/>" ><i class="mdi mdi-delete"></i></button>
	                        </div>
	                    </div>
                        <div class="card-body p-t-0">
                            <div class="card b-all shadow-none">
                            	<input type="hidden" id="dtlId" value="${mail.id }">
                            	<input type="hidden" id="dtlMailFromId" value="${mail.mailFrom.id }">
                            	<input type="hidden" id="dtlMailToName" value="${mail.mailTo.name }">
                            	<input type="hidden" id="dtlMailCreateTime" value="${mail.createTime }">
                                <div class="card-body">
                                    <h3 id="dtlSubject" class="card-title m-b-0">${mail.subject }</h3>
                                </div>
                                <div>
                                    <hr class="m-t-0">
                                </div>
                                <div class="card-body">
                                    <div class="d-flex m-b-40">
                                        <div class="p-l-10">
                                            From: <small id="dtlMailFromName" class="text-muted">${mail.mailFrom.name }</small>
                                        </div>
                                    </div>
                                    <p id="dtlContent" >${mail.content }</p>
                                </div>
                            </div>
                        </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- ============================================================== -->
	<!-- End PAge Content -->
	<!-- ============================================================== -->
</section>
<!--.燈箱 Compose -->

<spring:message code='subject' var="subject"/>
<spring:message code='enterText' var="enterText"/>
<div class="modal fade bs-example-modal-lg" id="modal_Compose" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
  <div class="modal-dialog modal-lg container">
	<div class="modal-content">
        <div class="row">
			<div class="col-md-12">
				<div class="card">
					<h5 class="card-header"><spring:message code='newMessage'/></h5>
				    <div class="card-body">
				        <div class="row">
							<div class="col-md-4">
								<div class="card">
								    <div class="card-body">
								        <h3 class="card-title"><spring:message code='to'/></h3>
								        <div class="form-group" id="mailToDiv"></div>
								    </div>
								</div>
							</div>
							<div class="col-md-8">
								<div class="card">
								    <div class="card-body">
								        <div class="form-group">
								            <input id="newSubject" class="form-control" placeholder="${subject}：">
								        </div>
								        <div class="form-group">
								            <textarea id="newContent" class="textarea_editor form-control" rows="15" placeholder="${enterText} ..."></textarea>
								        </div>
								        
								        <button type="button" class="btn btn-success m-t-20" onclick="sendMail();"><i class="fa fa-envelope-o"></i> <spring:message code='send'/></button>
								        <button class="btn btn-inverse m-t-20" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i> <spring:message code='discard'/></button>
								    </div>
							    </div>
							</div>
						</div>
				    </div>
			    </div>
			</div>
		</div>
	</div>
  </div>
</div>
<script>
var tblMain;

//[Init.]
$(function() {
	var mailId = '<%=((Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)).get("mailId")%>';
    if (mailId === 'null') {
		inbox();
		viewMailBox();
    }else{
    	$('#dtlId').val(mailId);
    	$('#dtlMailFromId').val(mailId);
    	$('#dtlMailToName').val(mailId);
    	$('#dtlMailCreateTime').val(mailId);
    	$('#dtlSubject').html('${targetMail_subject }');
    	$('#dtlMailFrom').html('${targetMail_mailFrom }');
    	$('#dtlContent').html('${targetMail_content }');
    	var status = '${targetMail_status }';
    	if('1'==status){
    		$('.list-group-item').removeClass('active');
    		$('#inboxOption').addClass('active');
    	}else if('0'==status){
    		$('.list-group-item').removeClass('active');
    		$('#trashOption').addClass('active');
        }else if('2'==status){
    		$('.list-group-item').removeClass('active');
    		$('#savedOption').addClass('active');
        }
		viewDetail();
    }

	getMailInfo();
	$('.move2Inbox').hide();
	$('.move2Saved').show();
	$('#newContent').wysihtml5();
	$('#optChkAll').click(function () {
	    $(':checkbox.optChkbox').prop('checked', this.checked);
	});
});

function viewMailBox() {
	$('#mailbox_div').show();
	$('#detail_div').hide();
}

function viewDetail() {
	$('#detail_div').show();
	$('#mailbox_div').hide();
}

function modalCompose() {
	$(':checkbox.mailToChkbox').prop('checked', false);
	$('#newSubject').val('');
	$('#newContent').data("wysihtml5").editor.clear();
	$('#modal_Compose').modal();
}

function doReply() {
	$(':checkbox.mailToChkbox').prop('checked', false);
	$('#newSubject').val('RE: '+$('#dtlSubject').html());
	var txt = "<br><br><br>------回复信件------"
		+ "<br>From:"+$('#dtlMailFromName').html()
		+ "<br>To:"+$('#dtlMailToName').val()
		+ "<br>Sent:"+ getFormattedDate(getProperDate($('#dtlMailCreateTime').val()))
		+ "<br>"+$('#dtlContent').html()
		; 
	$('#newContent').data("wysihtml5").editor.setValue(txt);
	var dtlMailFromId = $('#dtlMailFromId').val();
	$('#mailToChkbox_'+dtlMailFromId).prop('checked', true);
	$('#modal_Compose').modal();	
}

function doForward() {
	$(':checkbox.mailToChkbox').prop('checked', false);
	$('#newSubject').val('FW: '+$('#dtlSubject').html());
	var txt = "<br><br><br>------转寄信件------"
			+ "<br>From:"+$('#dtlMailFromName').html()
			+ "<br>To:"+$('#dtlMailToName').val()
			+ "<br>Sent:"+ getFormattedDate(getProperDate($('#dtlMailCreateTime').val()))
			+ "<br>"+$('#dtlContent').html()
			; 
	$('#newContent').data("wysihtml5").editor.setValue(txt);
	$('#modal_Compose').modal();
}


function viewInboxList() {
	$('.list-group-item').removeClass('active');
	$('#inboxOption').addClass('active');
	inbox();
	getMailInfo();
	viewMailBox();
	$('.move2Inbox').hide();
	$('.move2Saved').show();
	$(':checkbox#optChkAll').prop('checked', false);
}

function viewSavedList() {
	$('.list-group-item').removeClass('active');
	$('#savedOption').addClass('active');
	saved();
	getMailInfo();
	viewMailBox();
	$('.move2Inbox').show();
	$('.move2Saved').hide();
	$(':checkbox#optChkAll').prop('checked', false);
}

function viewTrashList() {
	$('.list-group-item').removeClass('active');
	$('#trashOption').addClass('active');
	trash();
	getMailInfo();
	viewMailBox();
	$('.move2Inbox').show();
	$('.move2Saved').show();
	$(':checkbox#optChkAll').prop('checked', false);
}

function getFormattedDate(d) {
	var dformat = d.getFullYear() + "/"
			+ ("00" + (d.getMonth() + 1)).slice(-2) + "/"
			+ ("00" + d.getDate()).slice(-2) + " "
			+ ("00" + d.getHours()).slice(-2) + ":"
			+ ("00" + d.getMinutes()).slice(-2);
	//+ ":" + ("00" + d.getSeconds()).slice(-2)
	return dformat;
}

function btnInboxOne() {
	var results = $('#dtlId').val();
	if(''==results) return false;
	inboxMail(results);
}

function btnSaveOne() {
	var results = $('#dtlId').val();
	if(''==results) return false;
	saveMail(results);
}

function btnDeleteOne() {
	var results = $('#dtlId').val();
	if(''==results) return false;
	
	if($('#trashOption').hasClass('active')){
		deleteMail(results);
	}else{
		trashMail(results);
	}
}

function btnDeleteClicked() {
	var getResult = function () {
	    var result = [];
	    $('input[name="optChkbox"]').each(function () {
	        var $this = $(this), id = $this.attr('id'), val = $this.val();
	        if ($(this).prop('checked')) {
	            result.push(val);
	        }
	    });
	    return result;
	};
	var results = getResult().join(',');
	if(''==results) return false;
	
	if($('#trashOption').hasClass('active')){
		deleteMail(results);
	}else{
		trashMail(results);
	}
}

function btnInboxClicked() {
	var getResult = function () {
	    var result = [];
	    $('input[name="optChkbox"]').each(function () {
	        var $this = $(this), id = $this.attr('id'), val = $this.val();
	        if ($(this).prop('checked')) {
	            result.push(val);
	        }
	    });
	    return result;
	};
	var results = getResult().join(',');
	if(''==results) return false;
	inboxMail(results);
}

function btnSaveClicked() {
	var getResult = function () {
	    var result = [];
	    $('input[name="optChkbox"]').each(function () {
	        var $this = $(this), id = $this.attr('id'), val = $this.val();
	        if ($(this).prop('checked')) {
	            result.push(val);
	        }
	    });
	    return result;
	};
	var results = getResult().join(',');
	if(''==results) return false;
	saveMail(results);
}

function inbox() {
	if (tblMain) {
		$('#tblMain').DataTable().destroy();
	}
	tblMain = $('#tblMain').DataTable(
			{
				"bFilter" : false,
				"ordering" : false,
				"info" : false,
				"serverSide" : true,
				"bLengthChange" : false,
				"ajax" : {
					"url" : '${pageContext.request.contextPath}/mail/listMailByUser.json',
					"type" : 'GET',
					"data" : function(d) {}
				},
				"columns" : [
					{ "data" : "id" },
					{ "data" : "mailFrom.name" },
					{ "data" : "subject" },
					{ "data" : "createTime" }
				],
				"columnDefs" : [
					{
						"targets" : 0,
						"data":   "id",
		                "render": function ( data, type, row ) {
		                	return '<div class="checkbox"><input type="checkbox" name="optChkbox" class="optChkbox" id="optChkbox_'+data+'" value="'+data+'"><label for="optChkbox_'+data+'"></label></div>';
		                }
					},
					{
						"targets" : 2,
						"data" : 'subject',
						"render" : function(data, type, row) {
							return '<div class="link" role="link" onclick="mailboxDetail('+row['id']+');">'+data+'</div>';
						}
					},
					{
						"targets" : 3,
						"data" : 'createTime',
						"render" : function(data, type, row) {
							return getFormattedDate(new Date(row['createTime']));
						}
					}
				],
				select: true
			});
}

function getMailInfo() {
	$.ajax({
		url : '${pageContext.request.contextPath}/mail/getMailInfo',
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	$('#mailToDiv').html('');
            	$.each(data.data.mailTo, function () {
            		addMailTo(this);
            	});
            	$('#countUnread').html(data.data.countUnread);
            	$('#countSaved').html(data.data.countSaved);
            	$('#countNotAlive').html(data.data.countNotAlive);
            } else {
            	alert(data.message);
            }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}

function addMailTo(vo) {
	var bodyHTML = '<div class="checkbox">';
	bodyHTML += '<input type="checkbox" name="mailToChkbox" class="mailToChkbox" id="mailToChkbox_'+vo.id+'"+ value="'+vo.id+'" >';
	bodyHTML += '<label for="mailToChkbox_'+vo.id+'">'+vo.name+'</label></div>';

	bodyHTML += '</div></div></div></div>';
	$('#mailToDiv').append(bodyHTML);
}

function sendMail() {
	var _newSubject = $('#newSubject').val();
	var _newContent = $('#newContent').val();
	var getResult = function () {
	    var result = [];
	    $('input[name="mailToChkbox"]').each(function () {
	        var $this = $(this), id = $this.attr('id'), val = $this.val();
	        if ($(this).prop('checked')) {
	            result.push(val);
	        }
	    });
	    return result;
	};
	var results = getResult().join(',');

	$.ajax({
		url : '${pageContext.request.contextPath}/mail/sendMail?newSubject='+_newSubject
															+ '&newContent=' + _newContent
															+ '&mailTo=' + results,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	$('#modal_Compose').modal('toggle');
            } else {
            	alert(data.message);
            }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}

function mailboxDetail(mailId) {
	window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/mail/mailbox/' + mailId;
	/*
	$.ajax({
		url : '${pageContext.request.contextPath}/mail/mailboxDetail?mailId=' + mailId,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	$('#dtlId').val(mailId);
            	$('#dtlMailFromId').val(data.data.targetMail.mailFrom.id);
            	$('#dtlMailToName').val(data.data.targetMail.mailTo.name);
            	$('#dtlMailCreateTime').val(data.data.targetMail.createTime);
            	$('#dtlSubject').html(data.data.targetMail.subject);
            	$('#dtlMailFromName').html(data.data.targetMail.mailFrom.name);
            	$('#dtlContent').html(data.data.targetMail.content);
            	viewDetail();
            } else {
            	alert(data.message);
            }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
	*/
}

function deleteMail(results) {
	$.ajax({
		url : '${pageContext.request.contextPath}/mail/deleteMail?target=' + results,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	triggerMenuOption();
            } else {
            	alert(data.message);
            }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}

function trashMail(results) {
	$.ajax({
		url : '${pageContext.request.contextPath}/mail/trashMail?target=' + results,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	triggerMenuOption();
            } else {
            	alert(data.message);
            }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}

function saveMail(results) {
	$.ajax({
		url : '${pageContext.request.contextPath}/mail/saveMail?target=' + results,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	triggerMenuOption();
            } else {
            	alert(data.message);
            }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}

function inboxMail(results) {
	$.ajax({
		url : '${pageContext.request.contextPath}/mail/inboxMail?target=' + results,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	triggerMenuOption();
            } else {
            	alert(data.message);
            }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}

function trash() {
	if (tblMain) {
		$('#tblMain').DataTable().destroy();
	}
	tblMain = $('#tblMain').DataTable(
			{
				"bFilter" : false,
				"ordering" : false,
				"info" : false,
				"serverSide" : true,
				"bLengthChange" : false,
				"ajax" : {
					"url" : '${pageContext.request.contextPath}/mail/trash.json',
					"type" : 'GET',
					"data" : function(d) {}
				},
				"columns" : [
					{ "data" : "id" },
					{ "data" : "mailFrom.name" },
					{ "data" : "subject" },
					{ "data" : "createTime" }
				],
				"columnDefs" : [
					{
						"targets" : 0,
						"data":   "id",
		                "render": function ( data, type, row ) {
		                	return '<div class="checkbox"><input type="checkbox" name="optChkbox" class="optChkbox" id="optChkbox_'+data+'" value="'+data+'"><label for="optChkbox_'+data+'"></label></div>';
		                }
					},
					{
						"targets" : 2,
						"data" : 'subject',
						"render" : function(data, type, row) {
							return '<div class="link" role="link" onclick="mailboxDetail('+row['id']+');">'+data+'</div>';
						}
					},
					{
						"targets" : 3,
						"data" : 'createTime',
						"render" : function(data, type, row) {
							return getFormattedDate(new Date(row['createTime']));
						}
					}
				],
				select: true
			});
}

function saved() {
	if (tblMain) {
		$('#tblMain').DataTable().destroy();
	}
	tblMain = $('#tblMain').DataTable(
			{
				"bFilter" : false,
				"ordering" : false,
				"info" : false,
				"serverSide" : true,
				"bLengthChange" : false,
				"ajax" : {
					"url" : '${pageContext.request.contextPath}/mail/saved.json',
					"type" : 'GET',
					"data" : function(d) {}
				},
				"columns" : [
					{ "data" : "id" },
					{ "data" : "mailFrom.name" },
					{ "data" : "subject" },
					{ "data" : "createTime" }
				],
				"columnDefs" : [
					{
						"targets" : 0,
						"data":   "id",
		                "render": function ( data, type, row ) {
		                	return '<div class="checkbox"><input type="checkbox" name="optChkbox" class="optChkbox" id="optChkbox_'+data+'" value="'+data+'"><label for="optChkbox_'+data+'"></label></div>';
		                }
					},
					{
						"targets" : 2,
						"data" : 'subject',
						"render" : function(data, type, row) {
							return '<div class="link" role="link" onclick="mailboxDetail('+row['id']+');">'+data+'</div>';
						}
					},
					{
						"targets" : 3,
						"data" : 'createTime',
						"render" : function(data, type, row) {
							return getFormattedDate(new Date(row['createTime']));
						}
					}
				],
				select: true
			});
}

function triggerMenuOption() {
	if($('#inboxOption').hasClass('active')){
		$('#inboxOption').trigger('click');
	}else if($('#savedOption').hasClass('active')){
		$('#savedOption').trigger('click');
	}else if($('#trashOption').hasClass('active')){
		$('#trashOption').trigger('click');
	}
}



function doBack() {
	triggerMenuOption();
}


function getProperDate(date) {
    if (date == null) return null;
    return new Date(+parseInt(date));
}

</script>
<style>
div.link {
	cursor: pointer;
}
</style>