package com.aegon.hotelbooking;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aegon.hotelbooking.exception.HotelBookingException;
import com.aegon.hotelbooking.model.Booking;
import com.aegon.hotelbooking.model.Customer;
import com.aegon.hotelbooking.model.Room;
import com.aegon.hotelbooking.model.RoomAvailablity;
import com.aegon.hotelbooking.repository.Repository;
import com.aegon.hotelbooking.util.DateUtil;

/**
 * 
 * @author Alan Brysoon
 * 
 *         A GET message which will retrieve any bookings for a specific room ID
 *         A GET message which will retrieve any bookings for a specific customer ID 
 *         A GET message to determine the availability of a specific room during a given date range 
 *         A POST message to create a new booking.
 *         
 *         Lochside House has 5 rooms available to hire, 2 Single rooms charged at £100 per night and 3 double rooms at £200 per night.
 *
 */
@RestController
@RequestMapping("/hotelbooking")
public class HotelBookingRestfulServices {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Inject
	private Repository repository;
	
	@RequestMapping(value = "/findRoomBookings/room/{roomNumber}", method = RequestMethod.GET, produces = "application/json")
	public Room findRoomBookings(@PathVariable int roomNumber) {

		LOG.info("Find Room Bookings request for roomNumber: {}", roomNumber);
		return repository.findRoomBookingsByRoomNumber(roomNumber);
	}

	@RequestMapping(value = "/findRooms", method = RequestMethod.GET, produces = "application/json")
	public List<Room> findRooms() {

		LOG.info("Find Rooms");
		return repository.findRooms();
	}
	
	@RequestMapping(value ="/bookRoom", method = RequestMethod.POST, consumes = "application/json")
	public void bookRoom(@RequestBody Customer customer) {

		LOG.debug("Json for booking a room: " + customer);
		//TODO validate room is really available for the dates requested
		repository.saveCustomerBooking(customer);
	}
	
	@RequestMapping(value = "/findCustomers", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> findCustomers() {
		
		LOG.info("Find Customers request");
        return repository.findCustomers();
	}
	
	@RequestMapping(value = "/findCustomerBookings/customer/{customerId}", method = RequestMethod.GET, produces = "application/json")
	public Customer findCustomerBookings(@PathVariable int customerId) {
		
		LOG.info("Find Customer Bookings request for customerId: {}", customerId);
		return repository.findCustomerById(customerId);
	}

	@RequestMapping(value ="/findRoomAvailability/room/{roomNumber}", method = RequestMethod.GET, produces = "application/json")
	public RoomAvailablity findRoomAvailability(@PathVariable int roomNumber, @RequestParam(name="startDate", required=true) String startDate, @RequestParam(name="endDate", required=true) String endDate) {

		LOG.info("Find Room Availability request for room number: {}, start date: {}, end date: {}", roomNumber, startDate, endDate);
		
		LocalDate sDate = DateUtil.convertToDate("Start Date", startDate);
		LocalDate eDate = DateUtil.convertToDate("End Date", endDate);

		LOG.debug("Date validation: " + sDate.compareTo(eDate));
		if (sDate.compareTo(eDate) == 1) {
			throw new HotelBookingException("Start Date cannot be after the End Date");
		}
			
		List<Booking> list = repository.findBookings(roomNumber, sDate, eDate);

		RoomAvailablity roomAvailablity = new RoomAvailablity();
		String msg = "bookings for room " + roomNumber + " were found between " + startDate + " and " + endDate;	
		roomAvailablity.setMessage(list.isEmpty() ? "No " +msg :  "The following " + msg);
        roomAvailablity.setBookings(list);
		return roomAvailablity;	
	}
	
}
