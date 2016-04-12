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
package com.travel.cas.site.register.action;

import com.travel.common.utils.ServletUtil;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.travel.cas.site.exception.AccountAlreadyExistException;
import com.travel.cas.site.exception.RegisterTicketExpire;
import com.travel.cas.site.member.model.User;
import com.travel.cas.site.member.service.UserService;

/**
 * Action to authenticate credential and retrieve a TicketGrantingTicket for
 * those credential. If there is a request for renew, then it also generates
 * the Service Ticket required.
 *
 * @author Scott Battaglia
 * @since 3.0.4
 */
public class RegisterViaFormAction {

    /** Authentication success result. */
    public static final String SUCCESS = "success";
    
    /** Error result. */
    public static final String ERROR = "error";
    
     /** register failure. */
    public static final String REGISTER_FAILURE="registerFailure";
    
   
     /**数据存储服务*/
    private UserService userService;


    /** Logger instance. **/
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    public final Event submit(final RequestContext context, final User user,
                              final MessageContext messageContext) throws Exception {
		
//    	MutableAttributeMap fs=context.getFlowScope();
    	
    	
    	
    	// Validate register ticket
		final String flowRegisterTicket = WebUtils.getRegisterTicketFromFlowScope(context);
				

		final String requestRegisterTicket = WebUtils.getRegisterTicketFromRequest(context);
		if (!flowRegisterTicket.equals(requestRegisterTicket)) {
			logger.warn("Invalid register ticket {}", requestRegisterTicket);
			messageContext.addMessage(new MessageBuilder().error().code("error.invalid.registerticket").build());
			
			throw new RegisterTicketExpire();//重复提交
			//return newEvent(ERROR);
		}

		// insert into db
		try {
			
			String curip= ServletUtil.getRemoteAddr(WebUtils.getHttpServletRequest(context));//ip地址
			userService.register(user,curip);
			return newEvent(SUCCESS);
		}catch (DuplicateKeyException ae) {
			
			logger.warn("Invalid register ticket {}", requestRegisterTicket);
			messageContext.addMessage(new MessageBuilder().error().code("error.account.alreadyExist").build());
			
			throw new AccountAlreadyExistException();//帐号已经存在
			//return newEvent(REGISTER_FAILURE, ae);
		}

    }


   private Event newEvent(final String id) {
        return new Event(this, id);
    }

    private Event newEvent(final String id, final Exception error) {
        return new Event(this, id, new LocalAttributeMap("error", error));
    }
  

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
