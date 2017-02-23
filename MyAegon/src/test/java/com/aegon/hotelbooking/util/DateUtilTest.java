package com.aegon.hotelbooking.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aegon.hotelbooking.exception.HotelBookingException;


public class DateUtilTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testConvertToDate_NullString() {
		
		exception.expect(HotelBookingException.class);
		exception.expectMessage("Start Date is empty");
		DateUtil.convertToDate("Start Date", null);
	}
	
	
	@Test
	public void testConvertToDate() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate expected = LocalDate.parse("14/07/2017", formatter);
		
		LocalDate actual = DateUtil.convertToDate("Start Date", "14/07/2017");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testConvertToDateInvalidFormat() {
		
		exception.expect(HotelBookingException.class);
		exception.expectMessage("Start Date - Invalid date format 14-07-2017 expected dd/mm/yyyy");
		DateUtil.convertToDate("Start Date", "14-07-2017");
	}
}
