package com.aegon.hotelbooking.exception;

public class HotelBookingException extends RuntimeException {

	private static final long serialVersionUID = 7612624103505016247L;

	public HotelBookingException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelBookingException(String message) {
		super(message);
	}
}
