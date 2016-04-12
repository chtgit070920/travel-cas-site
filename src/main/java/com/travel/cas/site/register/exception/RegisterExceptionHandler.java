package com.travel.cas.site.register.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;
import org.springframework.webflow.execution.FlowExecutionException;

import com.travel.cas.site.exception.AccountAlreadyExistException;
import com.travel.cas.site.exception.RegisterTicketExpire;

/**
 * description: 处理取回密码flow 异常
 *  				 当前处理方式的不足：需定义太多异常类
 * @author smart
 *
 */

public class RegisterExceptionHandler extends TransitionExecutingFlowExecutionExceptionHandler{

	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public RegisterExceptionHandler(){
	
		super.add(RegisterTicketExpire.class, "generateRegisterTicket");//重复提交 >重新开始
		super.add(AccountAlreadyExistException.class, "generateRegisterTicket");//帐号已经存在>重新开始
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
