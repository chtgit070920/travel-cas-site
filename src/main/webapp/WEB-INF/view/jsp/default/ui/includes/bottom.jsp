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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

      </div> <!-- END #content -->
      
		</div><!-- END #wrap -->
    </div> <!-- END #container -->
    
    
    
      <div class="footer">
        <div id="copyright" align="center">
          <p><spring:message code="copyright" /></p>
          <!-- <p>Powered by <a href="http://www.jasig.org/cas">Jasig Central Authentication Service</a></p> -->
        </div>
      </div>
    
    <script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-ui-1.10.3.min.js"/>"></script>
    
    <%-- 
        JavaScript Debug: A simple wrapper for console.log 
        See this link for more info: http://benalman.com/projects/javascript-debug-console-log/
    --%>
    <script type="text/javascript" src="<c:url value="/js/ba-debug.min.js"/>"></script>
    
    <spring:theme code="cas.javascript.file" var="casJavascriptFile"  />
    <script type="text/javascript" src="<c:url value="${casJavascriptFile}" />"></script>
  </body>
</html>
