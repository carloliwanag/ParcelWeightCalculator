package com.carloliwanag.exam1.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.carloliwanag.exam1.exception.ParcelOverWeightException;

@RestControllerAdvice
public class MaxWeightReachedAdvice {
	
	@ResponseBody
	@ExceptionHandler(ParcelOverWeightException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String maxWeightReached(ParcelOverWeightException e) {
		return e.getMessage();
	}
}
