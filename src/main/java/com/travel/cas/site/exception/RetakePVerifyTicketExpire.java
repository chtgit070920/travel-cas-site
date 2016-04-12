package com.travel.cas.site.exception;

/**
 * @description 会话失效异常，用于存储于会话中的数据因会话失效而人为抛出
 * @author smart
 */
public class RetakePVerifyTicketExpire  extends Exception {

	
	private static final long serialVersionUID = -8019384905520924572L;
	
	
    public RetakePVerifyTicketExpire() {
        super();
    }

    
    public RetakePVerifyTicketExpire(String msg) {
        super(msg);
    }

  
    public RetakePVerifyTicketExpire(String message, Throwable cause) {
        super(message, cause);
    }

    
    public RetakePVerifyTicketExpire(Throwable cause) {
        super(cause);
    }

}
