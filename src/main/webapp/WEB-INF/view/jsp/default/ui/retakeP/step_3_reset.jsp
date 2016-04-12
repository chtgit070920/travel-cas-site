<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<!DOCTYPE html>

<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html >
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="logo.title" />_<spring:message code="screen.welcome.instructions.retakeP" /></title>
<spring:theme code="standard.custom.css.file" var="customCssFile" />
<link rel="stylesheet" href="<c:url value="${customCssFile}" />" />
<link rel="icon" href="<c:url value="/favicon.ico" />" type="image/x-icon" />
</head>
<body id="cas">

	<div class="header">
		<div id="title">
			<div class="wrap">
				<div id="logo">
					<img alt="<spring:message code="logo.title" />" src="<c:url value="/images/logo.png"/>">
					<span><spring:message code="screen.welcome.instructions.retakeP" /></span>
				</div>
				<div id="words" align="right">
					<span><spring:message code="logo.title.description" /></span>
				</div>
			</div>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<div class="wrap">
				
				<!-- 流程图 -->
				<div class="retakeP-step3"></div>
				
				<!-- form -->			
				<div  id="retakeP">
				  <form:form method="post" id="fm1" commandName="${commandName}"  htmlEscape="true">
				  	 <form:errors path="*" id="msg" cssClass="errors" element="span" htmlEscape="false" />
				    <div class="row">
				    	<spring:message code="screen.welcome.label.newpwd.accesskey.retakeP.step3" var="newpwdAccesskey"/>
				      <label class="first" for="newpwd"><spring:message code="screen.welcome.label.newpwd.retakeP.step3" /></label><form:password 
				      cssClass="" cssErrorClass="" id="newpwd" accesskey="${newpwdAccesskey }" path="newpwd" autocomplete="off" htmlEscape="true" />
				    </div>
				    
				    <div class="row">
				    	<spring:message code="screen.welcome.label.confirmpwd.accesskey.retakeP.step3"  var="confirmpwdAccesskey"/>
				      <label class="first" for="confirmpwd"><spring:message code="screen.welcome.label.confirmpwd.retakeP.step3" /></label><form:password 
				      cssClass="" cssErrorClass="" id="confirmpwd"  path="confirmpwd"  accesskey="${confirmpwdAccesskey }" htmlEscape="true" autocomplete="off" />
				    </div>
				    
				    <div class="row btn-row">
				      <input type="hidden" name="rptr" value="${retakePTicketReset}" />
				      <input type="hidden" name="execution" value="${flowExecutionKey}" />
				      <input type="hidden" name="_eventId" value="submit" />
				      <label class="first" ></label><input class="btn-submit" name="submit" 
				      accesskey="<spring:message code="screen.welcome.label.btn.accesskey.retakeP.step3" />" 
				      value="<spring:message code="screen.welcome.label.btn.retakeP.step3" />"  type="submit" />
				    </div>
				    
				  </form:form>
				  
				</div>
				

			</div>
			<!-- END #wrap -->

		</div>
		<!-- END #content -->
		
	</div>
	<!-- END #container -->



	<div class="footer">
		<div id="copyright" align="center">
			<div class="wrap">
				<p>
					<spring:message code="copyright" />
				</p>
			</div>
		</div>
	</div>
	
	
	

	<script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.10.3.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/ba-debug.min.js"/>"></script>
	<spring:theme code="cas.javascript.file" var="casJavascriptFile" />
	<script type="text/javascript" src="<c:url value="${casJavascriptFile}" />"></script>
	
	
	<script type="text/javascript" src="<c:url value="/js/jquery-validate-1.14.0.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/self.validate.expands.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/self.validate.messages.js"/>"></script>
	<link rel="stylesheet" href="<c:url value="/js/self.validate.css"/>" />

	<script type="text/javascript">
	$(document).ready(function() {
		

			//1、验证
			$("#fm1").validate({
				rules : {
					newpwd : {
						required : true,
						password:true
					},
					confirmpwd:{
						required : true,
						password:true,
						equalTo: "#newpwd"
					}
				},
				messages : {
					newpwd : {
						required : "请输入新密码"
					},
					confirmpwd : {
						required : "请输入确认密码",
						equalTo: "两次输入密码不一致"
					}
				},
				errorPlacement : function(error, element) {
					error.appendTo(element.parent());
				}
			});

		});
	</script>
		
</body>
</html>
