package com.travel.cas.site.exception;

public class AccountAlreadyExistException extends Exception{

	private static final long serialVersionUID = 1L;

	
	public AccountAlreadyExistException() {
        super();
    }

    
    public AccountAlreadyExistException(String msg) {
        super(msg);
    }

  
    public AccountAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    
    public AccountAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
