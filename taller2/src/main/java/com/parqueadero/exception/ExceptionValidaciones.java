package com.parqueadero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ExceptionValidaciones extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExceptionValidaciones(String message) {
		
		super(message);
		
	}

}
