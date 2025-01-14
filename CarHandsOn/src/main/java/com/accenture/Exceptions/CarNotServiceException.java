package com.accenture.Exceptions;

public class CarNotServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	public CarNotServiceException() {
		super();
	}
	
	public CarNotServiceException(String mssg) {
		super(mssg);
	}
}
