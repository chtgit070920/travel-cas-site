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
				<div class="retakeP-step2"></div>
				
				<!-- form -->			
				<div  id="retakeP">
				  <form:form method="post" id="fm1" commandName="${commandName}"  htmlEscape="true">
				  	 <form:errors path="*" id="msg" cssClass="errors" element="span" htmlEscape="false" />
				    <div class="row">
				    	<spring:message code="screen.welcome.label.type.accesskey.retakeP.step2" var="typeAccesskey"/> 
				      <label class="first" for="type"><spring:message code="screen.welcome.label.type.retakeP.step2" /></label><form:select
				      cssClass="" cssErrorClass="" id="type"  accesskey="${typeAccesskey}" path="type" autocomplete="off" htmlEscape="true" items="${verify.typeItems }">
				      </form:select>
				    </div>
				    <div class="row">
				      <label class="first" for="mobile"><spring:message code="screen.welcome.label.mobile.retakeP.step2" /></label>
				      ${verify.mobile}
				    </div>
				    
				     <div class="row" style="display:none;">
				      <label class="first" for="email"><spring:message code="screen.welcome.label.email.retakeP.step2" /></label>
				      	${verify.email}
				    </div>
				    
				    <div class="row">
				    	<spring:message code="screen.welcome.label.msgcode.accesskey.retakeP.step2" var="msgcodeAccesskey"/> 
				      <label class="first" for="msgcode"><spring:message code="screen.welcome.label.msgcode.retakeP.step2" /></label><form:input 
				      cssClass="small" cssErrorClass="" id="msgcode"  path="msgcode"  accesskey="${msgcodeAccesskey }" 
				      htmlEscape="true" autocomplete="off" /><div id="pass-button" 
				      class="timer ${verify.send=='true'?'timer-pass':'' }">发送验证码</div>
				    </div>
				    
				    <div class="row btn-row">
				      <input type="hidden" name="rptv" value="${retakePTicketVerify}" />
				      <input type="hidden" name="execution" value="${flowExecutionKey}" />
				      <input type="hidden" name="_eventId" value="submit" />
				      <label class="first" ></label><input 
				      class="btn-submit" name="submit" 
				      accesskey="<spring:message code="screen.welcome.label.btn.accesskey.retakeP.step2" />" 
				      value="<spring:message code="screen.welcome.label.btn.retakeP.step2"/>"  type="submit" />
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
					mobile : {
						required : true
					},
					email : {
						required : true
					},
					msgcode : {
						required : true
					}
				},
				messages : {
					mobile : {
						required : "请输入手机号码"
					},
					email : {
						required : "请输入邮箱"
					},
					msgcode : {
						required : "请输入短信码"
					}
				},
				errorPlacement : function(error, element) {
					error.appendTo(element.parent());
				}
			});
			
			//如果已发送验证码，则60s内不可再发送
			if($("#pass-button").hasClass('timer-pass')){
				cycle($('#pass-button'));
			}
			
			//2、短信码
		   $("#pass-button").click(function () {
			  if(!$(this).hasClass('timer-pass')){
				  
			  var form = $("<form action='' method='post'></form>");
			   $("<input type='hidden' name='_eventId'  value='yzm' />").appendTo(form); 
			   $("<input type='hidden' name='rpts'  value='${retakePTicketSend}' />").appendTo(form); 
			   $("<input type='hidden' name='execution'  value='${flowExecutionKey}' />").appendTo(form);
			   form.appendTo($("body"));
			   form.submit();
				//location.href=""+"?_eventId=yzm&execution=${flowExecutionKey}";
				  //console.log("");
				  //$.ajax({ 
					// type: "POST",
					 //dataType: "json",  
				    //contentType: "application/json",
					 //data:{"_eventId":"yzm","execution":"${flowExecutionKey}","ajaxSource":true},
					 //url: "${flowExecutionUrl}"
				 //});
			   }
		    }); 
			
			

		});
	
		var initSeconds=60;
		var seconds=initSeconds;
		function cycle(dom){
			if (seconds == 0) { 
		        $(dom).text("发送验证码");
		        $(dom).removeClass("timer-pass");
		        seconds=initSeconds;
		        return;
		    }else{
		    	 $(dom).text("重新发送("+seconds+")");
		    	 seconds--;
		    }
		   setTimeout(function() {cycle(dom)} ,1000) 
		}
	
		
	</script>
		
</body>
</html>
