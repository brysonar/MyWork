package com.aegon.hotelbooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHanding { 

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleException(RuntimeException e) {

		LOG.error("Error: " + e.getMessage(), e);
		return new ResponseEntity<Object>(
		          "Error performing request: " +e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);		
	}

}
