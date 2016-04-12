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
<title><spring:message code="logo.title" />_<spring:message code="screen.welcome.instructions" /></title>
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
					<span><spring:message code="screen.welcome.instructions" /></span>
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
				
				<div id="login">
				  <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
				  	<form:errors path="*" id="msg" cssClass="errors" element="span" htmlEscape="false" />
				    <div class="row">
				      <label class="first" for="username"><spring:message code="screen.welcome.label.netid" /></label><spring:message
				      code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" /><form:input 
				      cssClass="" cssErrorClass="" id="username" size="" tabindex="1" accesskey="${userNameAccessKey}" 
				      path="username" autocomplete="off" htmlEscape="true" />
				    </div>
				    
				    <div class="row">
				    	<spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
				      <label class="first" for="password"><spring:message 
				      code="screen.welcome.label.password" /></label><form:password 
				      cssClass="" cssErrorClass="" id="password" size="" tabindex="2" path="password"  
				      accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
				    </div>
				    <div class="row row-check">
							<label class="first"></label><input id="rememberme" name="rememberme" value="true" tabindex="5"
								accesskey="<spring:message code="screen.welcome.label.rememberme.accesskey" />"
								type="checkbox" /><label for="rememberme" class="rememberme">
								<spring:message code="screen.welcome.label.rememberme" />
							</label><a class="forget" href="${retakePUrl}">忘记密码</a>
					</div>
				    
				    <div class="row btn-row">
				    	
				      <input type="hidden" name="lt" value="${loginTicket}" />
				      <input type="hidden" name="execution" value="${flowExecutionKey}" />
				      <input type="hidden" name="_eventId" value="submit" />
				      <label class="first" ></label><input class="btn-submit" name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4" type="submit" />
				    </div>
				    
				    <div class="row register-row"  >
			    		<div >
			    			还没有通行证？<a href="${registerUrl}">立即注册</a>
			    		</div>
				    </div>
				    
				  </form:form>
				  
					<div class="other-login">
						<p>其他帐号登录：</p>
						<span class="qq"><a href="javascript:void(0)">QQ</a></span>
						<span class="sina"><a href="javascript:void(0)">新浪</a></span>
						<span class="weixin"><a href="javascript:void(0)">微信</a></span>
					</div>
				  
				</div>
				  
				<div id="sidebar">
				  <div class="sidebar-content">
						<div>
				     		<img alt="" src="<c:url value="/images/creen_1.jpg"/>">
				     </div> 
				  </div>
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
					password : {
						required : true,
						password:false
					}
				},
				messages : {
					username : {
						required : "请输入手机号码"
					},
					password : {
						required : "请输入密码"
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
