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
				<div class="retakeP-step4"></div>
				
				<div  id="retakeP">
					<spring:message code="screen.welcome.label.result.success.retakeP.step4" /><a href="${loginUrl}">现在登录</a>
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
	
</body>
</html>
