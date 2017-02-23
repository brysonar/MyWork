package com.aegon.hotelbooking.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang.StringUtils;

import com.aegon.hotelbooking.exception.HotelBookingException;

public final class DateUtil {

	private DateUtil() {
		super();
	}

	public static LocalDate convertToDate(String label, String dateAsString) {

		if (StringUtils.isEmpty(dateAsString)) {
			throw new HotelBookingException(label + " is empty");
		}

		LocalDate date;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			date = LocalDate.parse(dateAsString, formatter);
		} catch (DateTimeParseException e) {
			String errMsg = label + " - Invalid date format " + dateAsString + " expected dd/mm/yyyy";
			throw new HotelBookingException(errMsg);
		}

		return date;
	}
}
