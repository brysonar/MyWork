package com.aegon.hotelbooking.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aegon.hotelbooking.model.Booking;
import com.aegon.hotelbooking.model.Customer;
import com.aegon.hotelbooking.model.Room;
import com.aegon.hotelbooking.model.RoomTypeEnum;

@Named
public class InitialDataLoad {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Repository repository;
	
	@Transactional
	public void loadData() {

		loadRoom1();
		loadRoom2();
		loadRoom3();
		loadRoom4();
		loadRoom5();

		loadCustomer();
	}

	public void loadCustomer() {
        
		LOG.debug("Loading initial customer");
		Room room = repository.findRoomBookingsByRoomNumber(123);
		
		Customer customer = new Customer();
		customer.setFirstName("Jim");
		customer.setLastName("Donaldson");
		customer.setMobile("09803 234 256");

		Booking booking = new Booking();
		booking.setCustomer(customer);
		booking.setRoom(room);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = LocalDate.parse("11/07/2017", formatter);
		LocalDate endDate = LocalDate.parse("15/07/2017", formatter);

		booking.setStartDate(startDate);
		booking.setEndDate(endDate);
		
		Set<Booking> bookings = new HashSet<>();
		 bookings.add(booking);
		
		customer.setBookings(bookings);

		em.persist(customer);
		em.persist(booking);
	}

	public void loadRoom1() {

		Room r = new Room();
		r.setRoomNumber(121);
		r.setRoomType(RoomTypeEnum.SINGLE);
		r.setCost(100);
		em.persist(r);
	}

	public void loadRoom2() {

		Room r = new Room();
		r.setRoomNumber(122);
		r.setRoomType(RoomTypeEnum.SINGLE);
		r.setCost(100);
		em.persist(r);
	}

	public void loadRoom3() {

		Room r = new Room();
		r.setRoomNumber(123);
		r.setRoomType(RoomTypeEnum.DOUBLE);
		r.setCost(200);
		em.persist(r);
	}

	public void loadRoom4() {

		Room r = new Room();
		r.setRoomNumber(124);
		r.setRoomType(RoomTypeEnum.DOUBLE);
		r.setCost(200);
		em.persist(r);
	}

	public void loadRoom5() {

		Room r = new Room();
		r.setRoomNumber(125);
		r.setRoomType(RoomTypeEnum.DOUBLE);
		r.setCost(200);
		em.persist(r);
	}
}
