package com.aegon.hotelbooking.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RoomAvailablity implements Serializable {

	private static final long serialVersionUID = 7983594382223300392L;

	private String message;
	private List<Booking> bookings;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	
}
