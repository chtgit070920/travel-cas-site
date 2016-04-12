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
package com.travel.cas.site.retakeP.action;

import javax.servlet.http.HttpServletRequest;

import com.travel.common.utils.IdUtil;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.travel.cas.site.exception.AccountNotExistException;
import com.travel.cas.site.exception.ConversationExpire;
import com.travel.cas.site.exception.RetakePInputCodeError;
import com.travel.cas.site.exception.RetakePInputTicketExpire;
import com.travel.cas.site.exception.RetakePSendTicketExpire;
import com.travel.cas.site.exception.RetakePVerifyCodeError;
import com.travel.cas.site.exception.RetakePVerifyTicketExpire;
import com.travel.cas.site.member.model.Retake.Input;
import com.travel.cas.site.member.model.Retake.Reset;
import com.travel.cas.site.member.model.Retake.Verify;
import com.travel.cas.site.member.model.User;
import com.travel.cas.site.member.service.UserService;
import com.travel.cas.site.utils.SmsUtil;

/**
 * @author smart
 */
public class RetakePViaFormAction {

	
    /** Authentication success result. */
    public static final String SUCCESS = "success";
    
    /** Error result. */
    public static final String ERROR = "error";
    
     /**数据存储服务*/
    private UserService userService;

    /** Logger instance. **/
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    
     //1、验证帐号存在性
    public final Event inputSubmit(final RequestContext context,Input input, final MessageContext messageContext) throws Exception {

		final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
		
		//Validate  ticket
		final String flowTicket = WebUtils.getRetakePTicketInputFromFlowScope(context);
		final String requestTicket = WebUtils.getRetakePTicketInputFromRequest(context);
		if (!flowTicket.equals(requestTicket)) {
			logger.error("Invalid retakePInput ticket {}", requestTicket);
			messageContext.addMessage(new MessageBuilder().error().code("error.retakePInput.invalid.ticket").build());
			
			throw new RetakePInputTicketExpire();//重复提交
			//return newEvent(ERROR);
		}
		
		//validate validcode
		final String sessionValidcode=WebUtils.getCapachaFromSessionScope(request);
		final String requestValidcode=request.getParameter("validcode");
		if(!sessionValidcode.equalsIgnoreCase(requestValidcode)){
			logger.error("Invalid retakePInput validatcode {}", requestTicket);
			messageContext.addMessage(new MessageBuilder().error().code("error.retakePInput.invalid.validatcode").build());
			
			throw new RetakePInputCodeError();//验证码错误
			//return newEvent(ERROR);
		}
		
		//validate user exist or not
		WebUtils.removeRetakePUserFromFlowScope(context);
		
		User user=null;
		try{
			user=userService.getByLoginname(input.getUsername());
		}catch(EmptyResultDataAccessException e){
			logger.error("user {} is not exist",input.getUsername());
			messageContext.addMessage(new MessageBuilder().error().code("error.retakePInput.accountNotExist").build());
			throw new AccountNotExistException();//登录名不存在 
		}
		WebUtils.putRetakePUser(context, user);
		return newEvent(SUCCESS);
	}
    
    // 根据 
    public Event initVerifyForm(final RequestContext context,final MessageContext messageContext,Verify verify)throws Exception{
    	
    		User user=WebUtils.getRetakePUserFromFlowScope(context);
    		
    		//验证flowscop中的user是否存在
    		if(null==user)  {
    			logger.error("user in cur flowScope is not exist ");
    			messageContext.addMessage(new MessageBuilder().error().code("error.conversation.expire").build());
    			throw new ConversationExpire();//会话失效
    			//return  newEvent("userNotFound"/*,new ConversationExpire()*/);
    		}
        	
    		//验证send yzm 的ticket
    		if(null!=verify.getSend()){//用户点击发送验证码
    			final String flowTicket=WebUtils.getRetakePTicketSendFromFlowScope(context);
    			final String requestTicket=WebUtils.getRetakePTicketSendFromRequest(context);
    			if(!flowTicket.equalsIgnoreCase(requestTicket)){
    				logger.error("Invalid retakePSend ticket {}", requestTicket);
    				messageContext.addMessage(new MessageBuilder().error().code("error.retakePSend.invalid.ticket").build());
    				throw new RetakePSendTicketExpire();//重新获取验证码 重复提交
    				//return  newEvent("msgTicketExpire"/*,new TicketExpire()*/);
    			}else{//对于非点击验证码重新初始化
    				
    			}
    			String mobile=user.getMobile();
    	    	String yzm= IdUtil.randomLenNmberStr(6);
    	    	logger.info("yzm:{} for mobile:{}",yzm,mobile);
    	    	WebUtils.putRetakePYzm(context, yzm);
    	    	
    	    	SmsUtil.sendYzm(mobile, yzm);
    		}
        	verify.setMobile(hideMobile(user.getMobile()));
        	verify.setEmail(hideEmail(user.getEmail()));
        	
        	return newEvent(SUCCESS);
    }
    
    //2、验证第一步输入的用户名是否为当前操作用户
	public final Event verifySubmit(final RequestContext context,Verify verify, 
			 final MessageContext messageContext)
			throws Exception {

		try{
			//Validate  ticket
			final String flowTicket = WebUtils.getRetakePTicketVerifyFromFlowScope(context);
			final String requestTicket = WebUtils.getRetakePTicketVerifyFromRequest(context);
			if (!flowTicket.equals(requestTicket)) {
				logger.error("Invalid retakePVerify ticket {}", requestTicket);
				messageContext.addMessage(new MessageBuilder().error().code("error.retakePVerify.invalid.ticket").build());
				
				throw new RetakePVerifyTicketExpire();//重复提交
				//return newEvent(ERROR);
			}
			
			//Validate msgcode
			String flowMsgCode=WebUtils.getRetakePYzmFromFlowScope(context);
			String reqMsgCode=verify.getMsgcode();
			if (!reqMsgCode.equals(flowMsgCode)) {
				logger.error("Invalid retakePVerify msgcode {}", reqMsgCode);
				messageContext.addMessage(new MessageBuilder().error().code("error.retakePVerify.invalid.msgcode").build());
				throw new RetakePVerifyCodeError();//短信验证码有误
				//return newEvent(ERROR);
			}
			
			return newEvent(SUCCESS);
		}finally{
			
			  //切忌 一定要清理，否则当抛出异常将循环执行“generateRetakePTicketVerifyTicket state”-->“viewVerifyForm state”
			  //原因是：在“viewVerifyForm state”由于requestscope 中verify中已经存在,
			  //故<var name="verify" class="com.travel.cas.site.member.model.Retake.Verify" />中不再初始化，
			  //而是直接使用requestscope#verify，这将导致循环抛出RetakePSendTicketExpire异常。
			 //context.getRequestScope().remove("verify");
			
			 //更优秀做法是（现将以下设null 操作移植verifyRealSubmit state中了，故注掉）：
			// verify.setSend(null);
		}
			
	}

	//3、重置密码
	public final Event resetSubmit(final RequestContext context,Reset reset,
			final MessageContext messageContext)
			throws Exception {
		User user=WebUtils.getRetakePUserFromFlowScope(context);
		
		//验证flowscop中的user是否存在
		if(null==user)  {
			logger.error("user in cur flowScope is not exist ");
			messageContext.addMessage(new MessageBuilder().error().code("error.conversation.expire").build());
			throw new ConversationExpire();//将导向第一步
			//return  newEvent("userNotFound"/*,new ConversationExpire()*/);
		}
		
		userService.modifyPwd(user.getId(), reset.getNewpwd());
		return newEvent(SUCCESS);
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
	
	
	public static String hideEmail(String email){
		if(!StringUtils.hasText(email))  return "";
		String regex = "(\\w{3})(\\w+)(\\w{3})(@\\w+)";
		return email.replaceAll(regex, "$1**$3$4");
	}
	
	public static String hideMobile(String mobile){
		if(!StringUtils.hasText(mobile)) return "";
		String regex = "(\\w{3})(\\w+)(\\w{4})";
		return mobile.replaceAll(regex, "$1**$3");
	}
	
	public static void main(String args[]){
	    String email = "abcd1111111111fefabc@gamil.com";
	    String regex = "(\\w{3})(\\w+)(\\w{3})(@\\w+)";
	    System.out.println(email.replaceAll(regex, "$1**$3$4"));
	    
	    String mobile = "13721421878";
	    String regex1 = "(\\w{3})(\\w+)(\\w{4})";
	    System.out.println(mobile.replaceAll(regex1, "$1**$3"));
	}
	
}
