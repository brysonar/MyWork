package com.aegon.hotelbooking;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.aegon.hotelbooking.exception.HotelBookingException;
import com.aegon.hotelbooking.model.Booking;
import com.aegon.hotelbooking.model.Customer;
import com.aegon.hotelbooking.model.Room;
import com.aegon.hotelbooking.model.RoomAvailablity;
import com.aegon.hotelbooking.repository.Repository;

@RunWith(MockitoJUnitRunner.class)
public class HotelBookingRestfulServiceTest {

	@InjectMocks
	private HotelBookingRestfulServices testee;

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Mock
	private Repository repository;

	@Test
	public void testFindRoomBookings() {

		int roomNumber = 123;
		Room room = new Room();

		Mockito.when(repository.findRoomBookingsByRoomNumber(roomNumber)).thenReturn(room);
		Room actual = testee.findRoomBookings(roomNumber);

		Assert.assertSame(room, actual);

		Mockito.verify(repository).findRoomBookingsByRoomNumber(roomNumber);
	}

	@Test
	public void testFindRooms() {

		List<Room> list = new ArrayList<>();
		Mockito.when(repository.findRooms()).thenReturn(list);

		List<Room> actual = testee.findRooms();
		Assert.assertSame(list, actual);

		Mockito.verify(repository).findRooms();
	}

	@Test
	public void testBookRoom() {

		Customer customer = new Customer();
		testee.bookRoom(customer);
		Mockito.verify(repository).saveCustomerBooking(customer);
	}

	@Test
	public void testFindCustomers() {

		List<Customer> list = new ArrayList<>();
		Mockito.when(repository.findCustomers()).thenReturn(list);
		List<Customer> actual = testee.findCustomers();
		Assert.assertSame(list, actual);

		Mockito.verify(repository).findCustomers();
	}

	@Test
	public void testFindCustomerBookings() {

		int customerId = 123;
		Customer customer = new Customer();
		Mockito.when(repository.findCustomerById(customerId)).thenReturn(customer);
		Customer actual = testee.findCustomerBookings(customerId);

		Assert.assertSame(customer, actual);

		Mockito.verify(repository).findCustomerById(customerId);
	}
	
	
	@Test
	public void testFindRoomAvailability_StartDateAfterEndDate() {
		
		
		int roomNumber = 123;
		String startDate = "12/09/2018";
		String endDate  = "12/10/2017";

		exception.expect(HotelBookingException.class);
		exception.expectMessage("Start Date cannot be after the End Date");
		testee.findRoomAvailability(roomNumber, startDate , endDate);
	}
	
	@Test
	public void testFindRoomAvailability_EmtpyList() {
		
		int roomNumber = 123;
		String startDate = "12/09/2017";
		String endDate  = "12/10/2017";
		
		List<Booking> list = new ArrayList<>();
		
		Mockito.when(repository.findBookings(Mockito.eq(roomNumber), Mockito.any(), Mockito.any())).thenReturn(list);

		RoomAvailablity actual = testee.findRoomAvailability(roomNumber, startDate , endDate);
		
		Assert.assertEquals("No bookings for room 123 were found between 12/09/2017 and 12/10/2017", actual.getMessage());
		Assert.assertTrue(actual.getBookings().isEmpty());
	}
	
	@Test
	public void testFindRoomAvailability() {
		
		int roomNumber = 123;
		String startDate = "12/09/2017";
		String endDate  = "12/10/2017";
		
		List<Booking> list = new ArrayList<>();
		Booking b = new Booking();
		list.add(b);
		
		Mockito.when(repository.findBookings(Mockito.eq(roomNumber), Mockito.any(), Mockito.any())).thenReturn(list);

		RoomAvailablity actual = testee.findRoomAvailability(roomNumber, startDate , endDate);
		
		Assert.assertEquals("The following bookings for room 123 were found between 12/09/2017 and 12/10/2017", actual.getMessage());
		Assert.assertSame(b, actual.getBookings().get(0));
	}
}
