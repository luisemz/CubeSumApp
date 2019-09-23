package com.example.cubesum.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CubeSumNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(CubeSumNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String storeDataNotFoundHandler(CubeSumNotFoundException ex) {
	    return ex.getMessage();
	}
}
