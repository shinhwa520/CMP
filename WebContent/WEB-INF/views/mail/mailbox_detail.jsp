<%@ include file="../../common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
	<div class="row page-titles">
	    <div class="col-md-6 col-8 align-self-center">
	        <h3 class="text-themecolor m-b-0 m-t-0">Email App</h3>
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
	                            <li class="list-group-item">
	                                <a href="javascript:void(0)"> <i class="mdi mdi-pen"></i> Compose </a>
	                            </li>
	                            <li class="list-group-item active"> 
	                            	<a href="javascript:void(0)"><i class="mdi mdi-gmail"></i> Inbox </a><span class="badge badge-success ml-auto">6</span>
	                            </li>
	                            <li class="list-group-item" onclick="">
	                                <a href="javascript:void(0)"> <i class="mdi mdi-delete"></i> Trash </a>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	                <div id="compose_div" class="col-xlg-10 col-lg-8 col-md-8">
	                    <div class="card-body">
	                        <div class="btn-group m-b-10 m-r-10" role="group" aria-label="Button group with nested dropdown">
	                            <button id="delChkAll" type="button" class="btn btn-secondary font-18 text-dark"><i class="mdi mdi-swap-horizontal"></i></button>
	                            <button type="button" class="btn btn-secondary font-18 text-dark"><i class="mdi mdi-share"></i></button>
	                        </div>
	                        <button type="button" class="btn btn-secondary m-r-10 m-b-10 text-dark"><i class="mdi mdi-delete font-18"></i></button>
	                        <button type="button" class="btn btn-secondary m-r-10 m-b-10 text-dark"><i class="mdi mdi-reload font-18"></i></button>
	                    </div>
                        <div class="card-body p-t-0">
                            <div class="card b-all shadow-none">
                                <div class="card-body">
                                    <h3 class="card-title m-b-0">${mail.subject }</h3>
                                </div>
                                <div>
                                    <hr class="m-t-0">
                                </div>
                                <div class="card-body">
                                    <div class="d-flex m-b-40">
                                        <div class="p-l-10">
                                            <small class="text-muted">From: ${mail.mailFrom.name }</small>
                                        </div>
                                    </div>
                                    <p>${mail.content }</p>
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
</script>
<style>

</style>