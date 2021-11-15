package com.carloliwanag.exam1.exception;

public class ParcelOverWeightException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 378671237218477571L;

	public ParcelOverWeightException(String message) {
		super(message);
	}
}
