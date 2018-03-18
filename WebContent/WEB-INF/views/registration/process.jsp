<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="../../common/taglib.jsp" %>

<div class="page-wrapper">
	<div class="container-fluid">
		
		<!-- vertical wizard -->
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body wizard-content ">
					
						<h4 class="card-title">Step wizard</h4>
						<h6 class="card-subtitle">You can find the <a href="http://www.jquery-steps.com" target="_blank">offical website</a></h6>
						<form action="#" class="tab-wizard vertical wizard-circle">
						
						
							<!-- Step 1 -->
							<h6>Personal Info</h6>
							<section>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label for="shortDescription1"><spring:message code='enterEmail'/>:</label><br>
											<input type="email" class="form-control" id="emailAddress1"> 
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<input class="btn btn-primary pull-right" value="<spring:message code='confirm'/>" type="button" onclick="nextStep();">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label for="shortDescription1"><spring:message code='enterVerificationCode'/>:</label><br>
											<spring:message code='verificationCode' var="verificationCode"/>
											<input type="text" class="form-control" id="verificationCode" placeholder="${verificationCode}"> 
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<input class="btn btn-primary pull-right" value="<spring:message code='confirm'/>" type="submit">
											<input class="btn btn-secondary pull-right" value="<spring:message code='reacquireVerificationCode'/>" type="button" onclick="reGenToken(this);">
										</div>
									</div>
								</div>
								<div style="display: none"><a href="#next" class="nextStep"></a></div>
							</section>
							
							
							<!-- Step 2 -->
							<h6>Job Status</h6>
							<section>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="jobTitle1">Job Title :</label>
											<input type="text" class="form-control" id="jobTitle1"> </div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="videoUrl1">Company Name :</label>
											<input type="text" class="form-control" id="videoUrl1">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label for="shortDescription1">Job Description :</label>
											<textarea name="shortDescription" id="shortDescription1" rows="6" class="form-control"></textarea>
										</div>
									</div>
								</div>
							</section>
							<!-- Step 3 -->
							
							
							<h6>Interview</h6>
							<section>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="int1">Interview For :</label>
											<input type="text" class="form-control" id="int1"> </div>
										<div class="form-group">
											<label for="intType1">Interview Type :</label>
											<select class="custom-select form-control" id="intType1" data-placeholder="Type to search cities" name="intType1">
												<option value="Banquet">Normal</option>
												<option value="Fund Raiser">Difficult</option>
												<option value="Dinner Party">Hard</option>
											</select>
										</div>
										<div class="form-group">
											<label for="Location1">Location :</label>
											<select class="custom-select form-control" id="Location1" name="location">
												<option value="">Select City</option>
												<option value="India">India</option>
												<option value="USA">USA</option>
												<option value="Dubai">Dubai</option>
											</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="jobTitle2">Interview Date :</label>
											<input type="date" class="form-control" id="jobTitle2">
										</div>
										<div class="form-group">
											<label>Requirements :</label>
											<div class="m-b-10">
												<label class="custom-control custom-radio">
													<input id="radio5" name="radio" type="radio" class="custom-control-input">
													<span class="custom-control-label">Employee</span>
												</label>
												<label class="custom-control custom-radio">
													<input id="radio6" name="radio" type="radio" class="custom-control-input">
													<span class="custom-control-label">Contract</span>
												</label>
											</div>
										</div>
									</div>
								</div>
							</section>
							
							
							<!-- Step 4 -->
							<h6>Remark</h6>
							<section>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="behName1">Behaviour :</label>
											<input type="text" class="form-control" id="behName1">
										</div>
										<div class="form-group">
											<label for="participants1">Confidance</label>
											<input type="text" class="form-control" id="participants1">
										</div>
										<div class="form-group">
											<label for="participants1">Result</label>
											<select class="custom-select form-control" id="participants1" name="location">
												<option value="">Select Result</option>
												<option value="Selected">Selected</option>
												<option value="Rejected">Rejected</option>
												<option value="Call Second-time">Call Second-time</option>
											</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="decisions1">Comments</label>
											<textarea name="decisions" id="decisions1" rows="4" class="form-control"></textarea>
										</div>
										<div class="form-group">
											<label>Rate Interviwer :</label>
										</div>
									</div>
								</div>
							</section>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>
<script>

	function validateInput() {
	  	var mailAddress = $('#email').val();
	  	if(mailAddress.trim()==''){
	  		errorMessage('<spring:message code="error.enterEmail"/>');
		  	return false;
		}
	  	if(!validateEmail(mailAddress)){
	  		errorMessage('<spring:message code="error.emailFormat"/>');
		  	return false;
		}
	}

	function validateEmail(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	}

	function nextStep() {
		alert("1111");
		var href = $('.nextStep').attr('href');
		window.location.href = href;
	}
</script>
<style>
.form-group .btn{
    border: 0px;
    margin-left: 3px;
}
.form-group .btn-primary{
    background: #009efb;
}
.actions{
    
}
</style>
