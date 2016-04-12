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
				<div class="retakeP-step1" style=""></div>
				
				<!-- form -->			
				<div  id="retakeP">
				  <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
				  	 <form:errors path="*" id="msg" cssClass="errors" element="span" htmlEscape="false" />
				    <div class="row">
				    	<spring:message code="screen.welcome.label.username.accesskey.retakeP.step1" var="userNameAccessKey" />
				      <label class="first" for="username"><spring:message 
				      code="screen.welcome.label.username.retakeP.step1" /></label><form:input 
				      cssClass="" cssErrorClass="" id="username" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />
				      
				    </div>
				    
				    <div class="row">
				   	<spring:message code="screen.welcome.label.validcode.accesskey.retakeP.step1" var="validcodeAccessKey" />
				      <label class="first" for="validcode"><spring:message 
				      code="screen.welcome.label.validcode.retakeP.step1" /></label><form:input 
				      cssClass="small" cssErrorClass="" 
				      id="validcode"  path="validcode"  
				      accesskey="${validcodeAccessKey}" 
				      htmlEscape="true" autocomplete="off" 
				       
				      />
				      <img alt=""  style="vertical-align: middle;" class="captcha">
				      <a class="captcha" href="javascript:void(0);" >换一张</a>
				    </div>
				    
				    <div class="row btn-row">
				      <input type="hidden" name="rpti" value="${retakePTicketInput}" />
				      <input type="hidden" name="execution" value="${flowExecutionKey}" />
				      <input type="hidden" name="_eventId" value="submit" />
				      <label class="first" ></label><input 
				      class="btn-submit" name="submit" 
				      accesskey="<spring:message code="screen.welcome.label.btn.accesskey.retakeP.step1" />" 
				      value="<spring:message code="screen.welcome.label.btn.retakeP.step1"/>"  type="submit" />
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
					username : {
						required : true
					},
					validcode : {
						required : true
					}
				},
				messages : {
					username : {
						required : "请输入用户名"
					},
					validcode : {
						required : "请输入验证码"
					}
				},
				errorPlacement : function(error, element) {
					error.appendTo(element.parent());
				}
			});
			
			
			 //点击切换验证码事件           
		    $('.captcha').click(function () {//生成验证码   
		    	$("img.captcha").hide().attr('src', '<c:url value="/captcha?"/>'+ Math.floor(Math.random()*100) ).fadeIn(); 
		      }); 
			 $('a.captcha').click();
			

		});
	</script>
		
</body>
</html>
