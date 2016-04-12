package com.travel.cas.site.exception;

/**
 * @description 会话失效异常，用于存储于会话中的数据因会话失效而人为抛出
 * @author smart
 */
public class ConversationExpire  extends Exception {

	
	private static final long serialVersionUID = -8019384905520924572L;
	
	
    public ConversationExpire() {
        super();
    }

    
    public ConversationExpire(String msg) {
        super(msg);
    }

  
    public ConversationExpire(String message, Throwable cause) {
        super(message, cause);
    }

    
    public ConversationExpire(Throwable cause) {
        super(cause);
    }

}
