/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.web.support;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.authentication.principal.WebApplicationService;
import org.jasig.cas.logout.LogoutRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.RequestContext;

import com.travel.cas.site.member.model.User;

/**
 * Common utilities for the site tier.
 *
 * @author Scott Battaglia

 * @since 3.1
 */
public final class WebUtils {
	
	private static Logger logger = LoggerFactory.getLogger(WebUtils.class);

    private WebUtils() {}

    /** Request attribute that contains message key describing details of authorization failure.*/
    public static final String CAS_ACCESS_DENIED_REASON = "CAS_ACCESS_DENIED_REASON";

    public static HttpServletRequest getHttpServletRequest(
        final RequestContext context) {
        Assert.isInstanceOf(ServletExternalContext.class, context
            .getExternalContext(),
            "Cannot obtain HttpServletRequest from event of type: "
                + context.getExternalContext().getClass().getName());

        return (HttpServletRequest) context.getExternalContext().getNativeRequest();
    }

    public static HttpServletResponse getHttpServletResponse(
        final RequestContext context) {
        Assert.isInstanceOf(ServletExternalContext.class, context
            .getExternalContext(),
            "Cannot obtain HttpServletResponse from event of type: "
                + context.getExternalContext().getClass().getName());
        return (HttpServletResponse) context.getExternalContext()
            .getNativeResponse();
    }

    public static WebApplicationService getService(
        final List<ArgumentExtractor> argumentExtractors,
        final HttpServletRequest request) {
        for (final ArgumentExtractor argumentExtractor : argumentExtractors) {
            final WebApplicationService service = argumentExtractor
                .extractService(request);

            if (service != null) {
                return service;
            }
        }

        return null;
    }

    public static WebApplicationService getService(
        final List<ArgumentExtractor> argumentExtractors,
        final RequestContext context) {
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        return getService(argumentExtractors, request);
    }

    public static WebApplicationService getService(
        final RequestContext context) {
        return (WebApplicationService) context.getFlowScope().get("service");
    }

    public static void putTicketGrantingTicketInRequestScope(
        final RequestContext context, final String ticketValue) {
        context.getRequestScope().put("ticketGrantingTicketId", ticketValue);
    }

    public static void putTicketGrantingTicketInFlowScope(
        final RequestContext context, final String ticketValue) {
        context.getFlowScope().put("ticketGrantingTicketId", ticketValue);
    }

    public static String getTicketGrantingTicketId(
        final RequestContext context) {
        final String tgtFromRequest = (String) context.getRequestScope().get("ticketGrantingTicketId");
        final String tgtFromFlow = (String) context.getFlowScope().get("ticketGrantingTicketId");

        return tgtFromRequest != null ? tgtFromRequest : tgtFromFlow;

    }

    public static void putServiceTicketInRequestScope(
        final RequestContext context, final String ticketValue) {
        context.getRequestScope().put("serviceTicketId", ticketValue);
    }

    public static String getServiceTicketFromRequestScope(
        final RequestContext context) {
        return context.getRequestScope().getString("serviceTicketId");
    }

    public static void putLoginTicket(final RequestContext context, final String ticket) {
        context.getFlowScope().put("loginTicket", ticket);
    }

    public static String getLoginTicketFromFlowScope(final RequestContext context) {
        // Getting the saved LT destroys it in support of one-time-use
        // See section 3.5.1 of http://www.jasig.org/cas/protocol
        final String lt = (String) context.getFlowScope().remove("loginTicket");
        return lt != null ? lt : "";
    }

    public static String getLoginTicketFromRequest(final RequestContext context) {
       return context.getRequestParameters().get("lt");
    }

    public static void putLogoutRequests(final RequestContext context, final List<LogoutRequest> requests) {
        context.getFlowScope().put("logoutRequests", requests);
    }

    public static List<LogoutRequest> getLogoutRequests(final RequestContext context) {
        return (List<LogoutRequest>) context.getFlowScope().get("logoutRequests");
    }
    
    
    //add by cht for captcha
    public static void putCapacha(final HttpServletRequest request, final String code) {
    	//System.out.println("insert into validcode"+code+"("+request.getSession().getId()+")");
    	logger.info("insert into validcode({}) for session({})",code,request.getSession().getId());
    	request.getSession().setAttribute("validcode",code);
    }
    public static String getCapachaFromSessionScope(final HttpServletRequest request) {
    	  String validcode=(String)request.getSession().getAttribute("validcode");
    	  request.getSession().removeAttribute("validcode");
    	  logger.info("remove validcode({}) from  session({})",validcode,request.getSession().getId());
    	  //System.out.println("remove  validcode"+validcode+"("+request.getSession().getId()+")");
        return validcode != null ? validcode : "";
    }
   
    
    
    //add by cht for register
    public static void putRegisterTicket(final RequestContext context, final String ticket) {
        context.getFlowScope().put("registerTicket", ticket);
    }
    public static String getRegisterTicketFromFlowScope(final RequestContext context) {
        final String rt = (String) context.getFlowScope().remove("registerTicket");
        return rt != null ? rt : "";
    }
    public static String getRegisterTicketFromRequest(final RequestContext context) {
       return context.getRequestParameters().get("rt");
    }
    //add by cht for retakeP
    public static void putRetakePTicketInput(final RequestContext context, final String ticket) {
        context.getFlowScope().put("retakePTicketInput", ticket);
    }
    public static String getRetakePTicketInputFromFlowScope(final RequestContext context) {
        final String rpti = (String) context.getFlowScope().remove("retakePTicketInput");
        return rpti != null ? rpti : "";
    }
    public static String getRetakePTicketInputFromRequest(final RequestContext context) {
       return context.getRequestParameters().get("rpti");
    }
    
    public static void putRetakePTicketVerify(final RequestContext context, final String ticket) {
        context.getFlowScope().put("retakePTicketVerify", ticket);
    }
    public static String getRetakePTicketVerifyFromFlowScope(final RequestContext context) {
        final String rptv = (String) context.getFlowScope().remove("retakePTicketVerify");
        return rptv != null ? rptv : "";
    }
    public static String getRetakePTicketVerifyFromRequest(final RequestContext context) {
       return context.getRequestParameters().get("rptv");
    }
    
    public static void putRetakePTicketReset(final RequestContext context, final String ticket) {
        context.getFlowScope().put("retakePTicketReset", ticket);
    }
    public static String getRetakePTicketResetFromFlowScope(final RequestContext context) {
        final String rptr = (String) context.getFlowScope().remove("retakePTicketReset");
        return rptr != null ? rptr : "";
    }
    public static String getRetakePTicketResetFromRequest(final RequestContext context) {
       return context.getRequestParameters().get("rptr");
    }
    
    public static void putRetakePTicketSend(final RequestContext context, final String ticket) {
        context.getFlowScope().put("retakePTicketSend", ticket);
    }
    public static String getRetakePTicketSendFromFlowScope(final RequestContext context) {
        final String rpts = (String) context.getFlowScope().remove("retakePTicketSend");
        return rpts != null ? rpts : "";
    }
    public static String getRetakePTicketSendFromRequest(final RequestContext context) {
       return context.getRequestParameters().get("rpts");
    }
    
  public static void putRetakePUser(final RequestContext context, final User user) {
	  context.getFlowScope().put("retakePUser",user);
   }
  public static User removeRetakePUserFromFlowScope(final RequestContext context) {
	  User user=(User)context.getFlowScope().remove("retakePUser");
     return user;
  }
  public static User getRetakePUserFromFlowScope(final RequestContext context) {
	  User user=(User)context.getFlowScope().get("retakePUser");
     return user;
  }
  public static void putRetakePYzm(final RequestContext context, final String yzm) {
      context.getFlowScope().put("retakePYzm", yzm);
  }
  public static String getRetakePYzmFromFlowScope(final RequestContext context) {
      final String yzm = (String) context.getFlowScope().remove("retakePYzm");
      return yzm != null ? yzm : "";
  }
  
}
