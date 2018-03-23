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
					
						<h4 class="card-title"><spring:message code='userRegistration'/></h4>
						<form:form  modelAttribute="UserInfoForm" action="#" class="tab-wizard vertical wizard-circle">
						<form:hidden path="userId" id="userId" />
						
<!------------------------- Step 1 [驗證信箱] ------------------------->
							<h6><spring:message code='verificationEmail'/></h6>
							<section>
							
								<!-- Step 1.1 [邮箱] ------------------------->
								<div class="row" id="div_email">
									<div class="col-md-12">
										<div class="form-group">
											<label for="emailAddress"><spring:message code='enterEmail'/>:</label><br>
											<spring:message code='email' var="email"/>
											<input type="email" class="form-control required" id="emailAddress"  placeholder="${email}">
											<span class="alert_txt"></span>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<input class="btn btn-primary pull-right" value="<spring:message code='confirm'/>" type="button" onclick="emailConfirm();">
										</div>
									</div>
								</div>
								
								<!-- Step 1.2 [验证码] ------------------------->
								<div class="row" id="div_token" style="display:none">
									<div class="col-md-12">
										<div class="form-group">
											<label for="verificationCode"><spring:message code='enterVerificationCode'/>:</label><br>
											<spring:message code='verificationCode' var="verificationCode"/>
											<input type="text" class="form-control" id="verificationCode" placeholder="${verificationCode}"> 
											<span class="alert_txt"></span>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<input class="btn btn-primary pull-right" value="<spring:message code='confirm'/>" type="button" onclick="tokenConfirm();">
											<input class="btn btn-secondary pull-right" value="<spring:message code='reacquireVerificationCode'/>" type="button" onclick="reGenToken(this);">
										</div>
									</div>
								</div>
							</section>
							
							
<!------------------------- Step 2 [創建帐号] ------------------------->
							<h6><spring:message code='createAccount'/></h6>
							<section>
					        <spring:message code='name' var="name"/>
					        <spring:message code='account' var="account"/>
					        <spring:message code='password' var="password"/>
					        <spring:message code='phoneNo' var="phoneNo"/>
					        <spring:message code='wechatID' var="wechatID"/>
					        <spring:message code='realName' var="realName"/>
					        <spring:message code='channelAccount' var="channelAccount"/>
				                <div class="row">
				                    <div class="col-12">
				                        <div class="card">
				                            <div class="card-body">
												<div class="row">
				                                    <div class="col-lg-12 col-md-12">
				                                        <div class="div_scroll">
					        	<!-- Step 2.1 [帐号资料] ------------------------->
					        	<div class="card">
									<h5 class="card-header"><spring:message code='accountInformation'/></h5>
									<div class="row card-body">
							        
										<div class="col-md-12">
											<div class="form-group">
												<label for="account"><spring:message code="account"/> :</label>
												<input type="text" class="form-control" id="account" placeholder="${account}" maxlength="12" >
												<span class="alert_txt"></span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label for="password"><spring:message code="password"/> :</label>
												<input type="password" class="form-control" id="password" placeholder="${password}" maxlength="12" >
												<span class="alert_txt"></span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label for="password"><spring:message code="confirmPassword"/> :</label>
												<input type="password" class="form-control" id="password2" placeholder="${password}" maxlength="12" >
												<span class="alert_txt"></span>
											</div>
										</div>
									</div>
								</div>
								
								<!-- Step 2.2 [个人资料] ------------------------->
					        	<div class="card">
									<h5 class="card-header"><spring:message code='personalInformation'/></h5>
									<div class="row card-body">
							        
										<div class="col-md-12">
											<div class="form-group">
												<label for="name"><spring:message code="name"/> :</label>
												<input type="text" class="form-control" id="name" placeholder="${realName}" maxlength="20" >
												<span class="alert_txt"></span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label for="phone"><spring:message code="phoneNo"/> :</label>
												<input type="text" class="form-control" id="phone" placeholder="${phoneNo}" maxlength="20" >
												<span class="alert_txt"></span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label for="weChat"><spring:message code="wechatID"/> :</label>
												<input type="text" class="form-control" id="weChat" placeholder="${wechatID}" maxlength="20" >
												<span class="alert_txt"></span>
											</div>
										</div>
									</div>
								</div>
								
								<!-- Step 2.3 [請輸入上游渠道商] ------------------------->
					        	<div class="card">
									<h5 class="card-header"><spring:message code='fillParentChannel'/></h5>
									<div class="row card-body">
										<div class="col-md-12">
											<div class="form-group">
												<label for="channelAccount"><spring:message code="channelAccount"/> :</label>
												<input type="text" class="form-control" id="channelAccount" placeholder="${channelAccount}" maxlength="20" >
												<span class="alert_txt"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<input class="btn btn-primary pull-right" value="<spring:message code='confirm'/>" type="button" onclick="userInfoConfirm();">
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
							</section>
							
							
<!------------------------- Step 3 [回答問題] ------------------------->
							<h6><spring:message code='answerQuestions'/></h6>
							<section>
				                <div class="row">
				                    <div class="col-12">
				                        <div class="card">
				                            <div class="card-body">
												<div class="row">
				                                    <div class="col-lg-12 col-md-12">
				                                        <div class="div_scroll">
				                                        	<div id="quesList"></div>
															<div class="row">
																<div class="col-md-12">
																	<div class="form-group">
																		<input class="btn btn-primary pull-right" value="<spring:message code='confirm'/>" type="button" onclick="checkAns();">
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

							</section>
							
							
<!------------------------- Step 4 [簽署合同] ------------------------->
							<h6><spring:message code='signingContract'/></h6>
							<section>
				                <div class="row">
				                    <div class="col-12">
				                        <div class="card">
				                            <div class="card-body">
				                                <!--<h4 class="card-title"></h4>--->
				                                <div class="row">
				                                    <div class="col-lg-12 col-md-12">
				                                        <div id="agreement_txt" class="agreement_scroll"></div>
				                                    </div>
				                                </div>
				                            </div>
				                        </div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<spring:message code="agreeContract"/>
											<input type="checkbox" class="check" id="iAgree" checked data-checkbox="icheckbox_square-blue">
											<label for="iAgree"><spring:message code='iAgree'/></label>
											<input class="btn btn-primary pull-right" value="<spring:message code='confirm'/>" type="button" onclick="agreeAgreement();">
										</div>
									</div>
								</div>
							</section>
						</form:form >
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>
<script>
var _ans, _ansArray,_quesMapKeySize;
function nextStep() {
	$('.actions ul a')[1].click();
}

function emailConfirm() {
	cleanErrAlert();
	var _emailAddress = $('#emailAddress').val().trim();
	var errMsg = '';
	if(''==_emailAddress){
		errMsg += '<spring:message javaScriptEscape="true" code="error.enterEmail"/>';
		$('#emailAddress').addClass('input-has-error');
		$('#emailAddress').focus();
		$('#emailAddress').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#emailAddress').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}
  	if(!validateEmail(_emailAddress)){
		errMsg += '<spring:message javaScriptEscape="true" code="error.emailFormat"/>';
		$('#emailAddress').addClass('input-has-error');
		$('#emailAddress').focus();
		$('#emailAddress').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#emailAddress').parents('.form-group').children(".alert_txt").addClass('text-danger');
	  	return false;
	}
	
	$.ajax({
		url : '${pageContext.request.contextPath}/registration/emailConfirm?emailAddress=' + _emailAddress,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	$('#userId').val(data.data.userId);
            	$('#div_token').show();
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

function tokenConfirm() {
	cleanErrAlert();
	if(''==$('#userId').val()){
		alert("<spring:message code='verifyEmailAgain'/>");
		return false;
	}
	var _verificationCode = $('#verificationCode').val().trim();
	var errMsg = '';
	if(''==_verificationCode){
		errMsg += '<spring:message javaScriptEscape="true" code="enterVerificationCode"/>';
		$('#verificationCode').addClass('input-has-error');
		$('#verificationCode').focus();
		$('#verificationCode').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#verificationCode').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}
	
	$.ajax({
		url : '${pageContext.request.contextPath}/registration/tokenConfirm?userId=' + $('#userId').val()
																		 + '&verificationCode=' + _verificationCode,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	nextStep();
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

function reGenToken(o) {
	if(''==$('#userId').val()){
		alert("<spring:message code='verifyEmailAgain'/>");
		return false;
	}
	$.ajax({
		url : '${pageContext.request.contextPath}/registration/reGenToken?userId=' + $('#userId').val(),
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	alert("<spring:message code='verificationCodeHasBeenResent'/>");
            } else {
            	alert(data.message);
            }
		},

		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
	time(o);
}

var wait=60; 
function time(o) {
	if (wait == 0) {
		o.removeAttribute("disabled");      
		o.value="<spring:message code='reacquireVerificationCode'/>"; 
		wait = 60; 
	} else {
		o.setAttribute("disabled", true); 
		o.value=wait+" <spring:message code='secondsCanBeResent'/>"; 
		wait--; 
		setTimeout(function() { 
			time(o) 
		}, 
		1000) 
	}
}

function userInfoConfirm() {
	cleanErrAlert();
	if(''==$('#userId').val()){
		alert("<spring:message code='verifyEmailAgain'/>");
		return false;
	}
	var account = $('#account').val().trim();
	var password = $('#password').val().trim();
	var password2 = $('#password2').val().trim();
	var userName = $('#name').val().trim();
	var phone = $('#phone').val().trim();
	var weChat = $('#weChat').val().trim();
	var channelAccount = $('#channelAccount').val().trim();
	if(!validateUserInfo(account, password, password2, userName, phone, weChat, channelAccount)) return false;
	
	$.ajax({
		url : '${pageContext.request.contextPath}/registration/userInfoConfirm?userId=' + $('#userId').val()
		 																	+ '&account=' + account
		 																	+ '&password=' + password
		 																	+ '&userName=' + userName
		 																	+ '&phone=' + phone
		 																	+ '&weChat=' + weChat
		 																	+ '&channelAccount=' + channelAccount,
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	_ans = data.data.ans;
            	_ansArray = _ans.split(',');
				_quesMapKeySize = data.data.quesMapKeySize;
            	$.each(data.data.quesMap, function () {
            		addQues(this);
            	});
                icheckfirstinit();
                iCheckcontrol.init();
                nextStep();
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

var quesRadioName = [];
function addQues(vo) {
	quesRadioName.push(vo[0].question.id);
	var bodyHTML = '<div class="row"><div class="col-md-12"><div class="card"><div class="card-body card-ques" id="cardBody_'+ vo[0].question.id +'">';
	bodyHTML += '<h4 class="card-title"><span style="color: blue;">'+ vo[0].question.sort +'. ';
	bodyHTML += vo[0].question.content +'</span></h4><br>';
	$.each(vo, function () {
		bodyHTML += addOption(this, bodyHTML);
	});
	bodyHTML += '</div></div></div></div>';
	$('#quesList').append(bodyHTML);
}

function addOption(detail) {
	var bodyHTML = '<input data-radio="iradio_square-blue" type="radio" class="check" name='+ detail.question.id +' value='+ detail.sort +' id='+ detail.id +' src="'+ detail.question.sort +'" >';
	bodyHTML += '<label class="radio_label" for='+ detail.id +' id="label_'+ detail.sort +'" >'+ detail.content +'</label><br>';
	return bodyHTML;
}

function checkAns() {
	if(''==$('#userId').val()){
		alert("<spring:message code='verifyEmailAgain'/>");
		return false;
	}

	//checkAns
	$('.card-ques').removeClass('card-has-error');
	var firstErr=20;
	//填錯的框起來
	var results;
	var resultCount;
	var hasError = false;
	var getResult = function () {
	    var result = [];
	    $('input:radio').each(function () {
	        var $this = $(this), id = $this.attr('id'), val = $this.val(), name = $this.attr('name'), index = $this.attr('src');
	        if ($(this).prop('checked')) {
	            result.push(val);
	            if(val!=_ansArray[index-1]){
	            	hasError = true;
	            	$('#cardBody_'+name).addClass('card-has-error');
	            	console.log('[firstErr]'+firstErr+'[name]'+name);
	            	console.log(name<firstErr);
	            	if(parseInt(name, 10)<parseInt(firstErr, 10)){
	            		firstErr = name;
	            	}
		        }
	        }
	    });
	    return result;
	};
	results = getResult().join(',');
	resultCount = results.split(',').length;
	//未填的框起來

	var checkAns;
	for(var i=quesRadioName.length-1; 0<=i; i--){
		checkAns = $('input:radio:checked[name="'+quesRadioName[i]+'"]').val()
		if(undefined===checkAns){
			$('#cardBody_'+quesRadioName[i]).addClass('card-has-error');
        	if(parseInt(quesRadioName[i], 10)<parseInt(firstErr, 10)){
        		firstErr=quesRadioName[i];
        	}
		}
	}
	console.log('[firstErr quesRadioName[i]]'+firstErr);
	

	console.log('[firstErr]'+firstErr);
	//檢核是否全都回答
	if(''==results || resultCount<_quesMapKeySize){
		if(hasError){
			alert("<spring:message code='error.mustAllCheck'/>"+"<spring:message code='notAllCorrect'/>");
			$('input:radio[name="'+firstErr+'"]')[0].focus();
			return false;
		}else{
			alert("<spring:message code='error.mustAllCheck'/>");
			$('input:radio[name="'+firstErr+'"]')[0].focus();
			return false;
		}

	}
	
	if(results != _ans){
		alert("<spring:message code='notAllCorrect'/>");
		$('input:radio[name="'+firstErr+'"]')[0].focus();
		return false;
	}

	
	$.ajax({
		url : '${pageContext.request.contextPath}/registration/getAgreement?userId=' + $('#userId').val(),
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	$('#agreement_txt').append(data.data.agreement);
            	nextStep();
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

function agreeAgreement() {
	if(!$('#iAgree').prop("checked")){
		alert("<spring:message code='tickAgree'/>");
		$('#iAgree').focus();
		return false;
	}	
	$.ajax({
		url : '${pageContext.request.contextPath}/registration/agreeAgreement?userId=' + $('#userId').val(),
		type : "GET",
		dataType : 'json',
		async: false,
		contentType:"application/json;charset=utf-8", 
		success : function(data) {
            if (data.code === 200) {
            	alert(data.message);
    			setTimeout(function(){
    				window.location.href = '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/login';
    			}, 2000);
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


//清空錯誤提示
function cleanErrAlert() {
	$('.form-control').removeClass('input-has-error');
	$('.form-group .alert_txt').text('');
	$('.form-group .alert_txt').removeClass('text-danger');
}

//頁面輸入檢核
function validateUserInfo(account, password, password2, userName, phone, weChat, channelAccount) {
	var errMsg = '';

	//account
	if (''==account) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustAccount"/>';
		$('#account').addClass('input-has-error');
		$('#account').focus();
		$('#account').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#account').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateLength(account, 4, 12)){
		errMsg += '<spring:message code="inputLengthLimit"/> 4~12<spring:message code="characters"/>';
		$('#account').addClass('input-has-error');
		$('#account').focus();
		$('#account').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#account').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}

	//password
	if (''==password) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPassword"/>';
		$('#password').addClass('input-has-error');
		$('#password').focus();
		$('#password').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateLength(password, 4, 12)){
		errMsg += '<spring:message code="inputLengthLimit"/> 4~12<spring:message code="characters"/>';
		$('#password').addClass('input-has-error');
		$('#password').focus();
		$('#password').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateEngInt(password)){
		errMsg += '<spring:message code="InputFormatError"/>(<spring:message code="enterEngAndNum"/>)';
		$('#password').addClass('input-has-error');
		$('#password').focus();
		$('#password').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}

	if(password!=password2){
		errMsg += '<spring:message code="reConfirmPassword"/>';
		$('#password2').addClass('input-has-error');
		$('#password2').focus();
		$('#password2').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password2').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}

	//userName
	if (''==userName) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustName"/>';
		$('#name').addClass('input-has-error');
		$('#name').focus();
		$('#name').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#name').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateLength(userName, 2, 20)){
		errMsg += '<spring:message code="inputLengthLimit"/> 2~20<spring:message code="characters"/>';
		$('#name').addClass('input-has-error');
		$('#name').focus();
		$('#name').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#name').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}

	//phone
	if (''==phone) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustPhoneNo"/>';
		$('#phone').addClass('input-has-error');
		$('#phone').focus();
		$('#phone').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#phone').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateLength(phone, 8, 20)){
		errMsg += '<spring:message code="inputLengthLimit"/> 8~20<spring:message code="characters"/>';
		$('#phone').addClass('input-has-error');
		$('#phone').focus();
		$('#phone').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#phone').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateInt(phone)){
		errMsg += '<spring:message code="InputFormatError"/>(<spring:message code="enterNumber"/>)';
		$('#phone').addClass('input-has-error');
		$('#phone').focus();
		$('#phone').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#phone').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}

	//weChat
	if (''==weChat) {
		errMsg += '<spring:message javaScriptEscape="true" code="error.mustWechat"/>';
		$('#weChat').addClass('input-has-error');
		$('#weChat').focus();
		$('#weChat').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#weChat').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateLength(weChat, 4, 20)){
		errMsg += '<spring:message code="inputLengthLimit"/> 4~20<spring:message code="characters"/>';
		$('#weChat').addClass('input-has-error');
		$('#weChat').focus();
		$('#weChat').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#weChat').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}

	//account
	if (''==channelAccount) {
		errMsg += '<spring:message javaScriptEscape="true" code="fillParentChannel"/>';
		$('#channelAccount').addClass('input-has-error');
		$('#channelAccount').focus();
		$('#channelAccount').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#channelAccount').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	} else if (!validateLength(channelAccount, 4, 12)){
		errMsg += '<spring:message code="inputLengthLimit"/> 4~12<spring:message code="characters"/>';
		$('#channelAccount').addClass('input-has-error');
		$('#channelAccount').focus();
		$('#channelAccount').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#channelAccount').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}
	return true;
}

function validatePassword2() {
	var password = $('#password').val().trim();
	var password2 = $('#password2').val().trim();
	if(password!=password2){
		$('#password2').addClass('input-has-error');
		$('#password2').focus();
		$('#password2').parents('.form-group').children(".alert_txt").text(errMsg);
		$('#password2').parents('.form-group').children(".alert_txt").addClass('text-danger');
		return false;
	}
}

function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function validateInt(input) {
	var regExp = /^\d+$/;
	return regExp.test(input);
}

function validateEngInt(input) {
	var regExp = /[^0-9]/g;
	return regExp.test(input);
}

function validateLength(input, lower, upper) {
	var inputLength = input.length;
	return (lower<=inputLength && inputLength<=upper);
}



</script>
<style>
.actions{
    display:none !important;
}
.form-group .btn{
    border: 0px;
    margin-left: 3px;
}
.form-group .btn-primary{
    background: #009efb;
}
.form-group .input-has-error {
    border-color: #f62d51;
}
.card-has-error {
    border-style: solid;
    border-width: medium;
    border-color: #f62d51;
}
.card {
    margin-bottom: 10px;
}
body{
    background-image:url(${pageContext.request.contextPath}/resources/assets/images/background/GtqDqDn.jpg);
    background-repeat:no-repeat;
    background-position: bottom;
    background-size: cover;
}
.page-wrapper{
    background-image:url(${pageContext.request.contextPath}/resources/assets/images/background/GtqDqDn_m.jpg);
    background-repeat:no-repeat;
    background-position: bottom;
    background-size: cover;
}
</style>
