<%@ include file="../../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
	<div class="row page-titles">
	    <div class="col-md-6 col-8 align-self-center">
	        <h3 class="text-themecolor m-b-0 m-t-0"><spring:message code='sysMail'/></h3>
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
	                        	<!-- 
	                            <li id="composeOption" class="list-group-item" onclick="modalCompose();">
	                                <a href="javascript:void(0)"> <i class="mdi mdi-pen"></i> <spring:message code='compose'/> </a>
	                            </li>
	                            -->
	                            <li id="inboxOption" class="list-group-item active" onclick="viewInboxList();">
	                            	<a href="javascript:void(0)"><i class="mdi mdi-gmail"></i> <spring:message code='inbox'/> </a><span id="countUnread" class="badge badge-success ml-auto">0</span>
	                            </li>
	                            <li id="trashOption" class="list-group-item" onclick="viewTrashList();">
	                                <a href="javascript:void(0)"> <i class="mdi mdi-delete"></i> <spring:message code='trashCan'/> </a><span id="countNotAlive" class="badge badge-secondary ml-auto">0</span>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	                
	                <!-- mailbox_div ============================================================== -->
	                <div id="mailbox_div" class="col-xlg-10 col-lg-8 col-md-8">
	                    <div class="card-body">
	                        <button type="button" class="btn btn-secondary m-r-10 m-b-10 text-dark"  onclick="btnDeleteClicked();"><i class="mdi mdi-delete font-18"></i></button>
	                        <button type="button" class="btn btn-secondary m-r-10 m-b-10 text-dark"  onclick="btnReloadClicked();"><i class="mdi mdi-reload font-18"></i></button>
	                    </div>
	                    <div class="card-body p-t-0">
	                        <div class="card b-all shadow-none">
	                            <div class="inbox-center b-all table-responsive">
	                                <table id="tblMain" class="table table-hover no-wrap">
	                                    <thead>
	                                    </thead>
	                                </table>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                
	                <!-- detail_div ============================================================== -->
	                <div id="detail_div" class="col-xlg-10 col-lg-8 col-md-8">
	                    <div class="card-body">
	                        <button type="button" class="btn btn-secondary m-r-10 m-b-10 text-dark" onclick="btnDeleteOne();"><i class="mdi mdi-delete font-18"></i></button>
	                    </div>
                        <div class="card-body p-t-0">
                            <div class="card b-all shadow-none">
                            	<input type="hidden" id="dtlId" value="${sysMail.id }">
                                <div class="card-body">
                                    <h3 id="dtlSubject" class="card-title m-b-0">${sysMail.subject }</h3>
                                </div>
                                <div>
                                    <hr class="m-t-0">
                                </div>
                                <div class="card-body">
                                    <div class="d-flex m-b-40">
                                        <div class="p-l-10">
                                            From: <small id="dtlMailFrom" class="text-muted">${sysMail.mailFrom.role.name }</small>
                                        </div>
                                    </div>
                                    <p id="dtlContent" >${sysMail.content }</p>
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

<script>
var tblMain;
var targetMail = '${sysMail}';

//[Init.]
$(function() {
	if(''!=targetMail){
    	$('#dtlSubject').html(targetMail.subject);
    	$('#dtlMailFrom').html(targetMail.mailFrom.role.name);
    	$('#dtlContent').html(targetMail.content);
		viewDetail()
	}else{
		inbox();
		viewMailBox();
	}
	getMailInfo();
	$('#newContent').wysihtml5();
	$('#delChkAll').click(function () {
	    $(':checkbox.delChkbox').prop('checked', this.checked);
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
	$('#newContent').val('');
	$('#newContent').data("wysihtml5").editor.clear();
	$('#modal_Compose').modal();
}

function viewInboxList() {
	$('.list-group-item').removeClass('active');
	$('#inboxOption').addClass('active');
	inbox();
	getMailInfo();
	viewMailBox();
}

function viewTrashList() {
	$('.list-group-item').removeClass('active');
	$('#trashOption').addClass('active');
	trash();
	getMailInfo();
	viewMailBox();
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
	    $('input[name="delChkbox"]').each(function () {
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

function btnReloadClicked() {
	if($('#trashOption').hasClass('active')){
    	trash();
    	getMailInfo();
	}else{
		inbox();
    	getMailInfo();
	}
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
					"url" : '${pageContext.request.contextPath}/sysMail/listSysMailByUser.json',
					"type" : 'GET',
					"data" : function(d) {}
				},
				"columns" : [
					{ "data" : "id" },
					{ "data" : "mailFrom.role.name" },
					{ "data" : "subject" },
					{ "data" : "createTime" }
				],
				"columnDefs" : [
					{
						"targets" : 0,
						"data":   "id",
		                "render": function ( data, type, row ) {
		                	return '<div class="checkbox"><input type="checkbox" name="delChkbox" class="delChkbox" id="delChkbox_'+data+'" value="'+data+'"><label for="delChkbox_'+data+'"></label></div>';
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
		url : '${pageContext.request.contextPath}/sysMail/getSysMailInfo',
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
		url : '${pageContext.request.contextPath}/sysMail/sendSysMail?newSubject='+_newSubject
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
	$.ajax({
		url : '${pageContext.request.contextPath}/sysMail/sysMailboxDetail?sysMailId=' + mailId,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	$('#dtlId').val(mailId);
            	$('#dtlSubject').html(data.data.targetSysMail.subject);
            	$('#dtlMailFrom').html(data.data.targetSysMail.mailFrom.role.name);
            	$('#dtlContent').html(data.data.targetSysMail.content);
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
}

function deleteMail(results) {
	$.ajax({
		url : '${pageContext.request.contextPath}/sysMail/deleteSysMail?target=' + results,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	$('#trashOption').trigger('click');
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
		url : '${pageContext.request.contextPath}/sysMail/trashSysMail?target=' + results,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	$('#inboxOption').trigger('click');
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
					"url" : '${pageContext.request.contextPath}/sysMail/trash.json',
					"type" : 'GET',
					"data" : function(d) {}
				},
				"columns" : [
					{ "data" : "id" },
					{ "data" : "mailFrom.role.name" },
					{ "data" : "subject" },
					{ "data" : "createTime" }
				],
				"columnDefs" : [
					{
						"targets" : 0,
						"data":   "id",
		                "render": function ( data, type, row ) {
		                	return '<div class="checkbox"><input type="checkbox" name="delChkbox" class="delChkbox" id="delChkbox_'+data+'" value="'+data+'"><label for="delChkbox_'+data+'"></label></div>';
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
</script>
<style>
div.link {
	cursor: pointer;
}
</style>