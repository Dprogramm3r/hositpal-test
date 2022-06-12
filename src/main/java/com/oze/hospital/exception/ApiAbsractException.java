package com.oze.hospital.exception;

import org.springframework.http.HttpStatus;

public class ApiAbsractException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus code;
    
    public ApiAbsractException(String message, HttpStatus hp){
        super(message);
        this.code = hp;
    }

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}
    
    

    public ApiAbsractException(String message, Throwable hp){
        super(message, hp);
    }


}
