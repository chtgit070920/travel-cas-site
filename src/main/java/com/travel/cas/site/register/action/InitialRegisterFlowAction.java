package com.travel.cas.site.register.action;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class InitialRegisterFlowAction extends AbstractAction {
	

	@Override
	protected Event doExecute(RequestContext context) throws Exception {
		
		final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
		
//		 String targetService = request.getParameter(SimpleWebApplicationServiceImpl.getTargetServiceParam());
//	    String serviceToUse = StringUtils.hasText(targetService)? targetService : request.getParameter(SimpleWebApplicationServiceImpl.getServiceParam());
	    
//	   test 
//	   serviceToUse="http://localhost:3002/sso-client";
//	   serviceToUse=java.net.URLEncoder.encode(serviceToUse);
	   
	    
	   String queryString = request.getQueryString();
	   String loginUrl = request.getContextPath() + "/login" + (queryString != null ? "?" + queryString : "");
		context.getFlowScope().put("loginUrl", loginUrl);
		
//		context.getFlowScope().put("service", serviceToUse);
	   
	   return result("success");
	}

}
