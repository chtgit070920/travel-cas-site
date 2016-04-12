package com.travel.cas.site.retakeP.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;
import org.springframework.webflow.execution.FlowExecutionException;

import com.travel.cas.site.exception.AccountNotExistException;
import com.travel.cas.site.exception.ConversationExpire;
import com.travel.cas.site.exception.RetakePInputCodeError;
import com.travel.cas.site.exception.RetakePInputTicketExpire;
import com.travel.cas.site.exception.RetakePResetTicketExpire;
import com.travel.cas.site.exception.RetakePVerifyCodeError;
import com.travel.cas.site.exception.RetakePVerifyTicketExpire;
import com.travel.cas.site.exception.RetakePSendTicketExpire;

/**
 * description: 处理取回密码flow 异常
 *  				 当前处理方式的不足：需定义太多异常类
 * @author smart
 *
 */

public class RegtakePExceptionHandler extends TransitionExecutingFlowExecutionExceptionHandler{

	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public RegtakePExceptionHandler(){
	
		super.add(AccountNotExistException.class, "generateRetakePTicketInputTicket");//帐号不存在
		
		super.add(ConversationExpire.class, "generateRetakePTicketInputTicket");//会话失效 重新开始
		
		super.add(RetakePInputCodeError.class, "generateRetakePTicketInputTicket");//input>验证码错误 重新开始
		super.add(RetakePInputTicketExpire.class, "generateRetakePTicketInputTicket");//input>重复提交 重新开始
		
		super.add(RetakePSendTicketExpire.class, "generateRetakePTicketVerifyTicket");//send> 重复提交 重新verify
		
		super.add(RetakePVerifyTicketExpire.class, "generateRetakePTicketVerifyTicket");//verify>重复提交 重新verify 
		super.add(RetakePVerifyCodeError.class, "generateRetakePTicketVerifyTicket");//verify>验证码错误 重新verify 
		
		super.add(RetakePResetTicketExpire.class, "generateRetakePTicketVerifyTicket");//reset>重复提交 重新reset
	}
	
	
	@Override
	public boolean canHandle(FlowExecutionException exception) {
		return super.canHandle(exception);
	}

	@Override
	public void handle(FlowExecutionException exception,
			RequestControlContext context) {
		super.handle(exception, context);
	}

   
    
	
}
