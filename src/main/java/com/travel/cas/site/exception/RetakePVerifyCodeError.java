package com.travel.cas.site.exception;

/**
 * @description 会话失效异常，用于存储于会话中的数据因会话失效而人为抛出
 * @author smart
 */
public class RetakePVerifyCodeError  extends Exception {

	
	private static final long serialVersionUID = -8019384905520924572L;
	
	
    public RetakePVerifyCodeError() {
        super();
    }

    
    public RetakePVerifyCodeError(String msg) {
        super(msg);
    }

  
    public RetakePVerifyCodeError(String message, Throwable cause) {
        super(message, cause);
    }

    
    public RetakePVerifyCodeError(Throwable cause) {
        super(cause);
    }

}
