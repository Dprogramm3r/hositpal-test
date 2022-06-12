package com.oze.hospital.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	 @ExceptionHandler(value = {ApiAbsractException.class})
	    public ResponseEntity<Object> handleApiRequestException(ApiAbsractException e){

	        ApiException apiException= new ApiException(e.getMessage(), e.getCode());
	            return new ResponseEntity<>(apiException, e.getCode());
	    }

}
