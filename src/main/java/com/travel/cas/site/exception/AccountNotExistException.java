package com.travel.cas.site.exception;

public class AccountNotExistException extends Exception{

	private static final long serialVersionUID = 1L;

	
	public AccountNotExistException() {
        super();
    }

    
    public AccountNotExistException(String msg) {
        super(msg);
    }

  
    public AccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    
    public AccountNotExistException(Throwable cause) {
        super(cause);
    }
}
