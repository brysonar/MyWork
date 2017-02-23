package com.aegon.hotelbooking.repository;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.aegon.hotelbooking.model.Booking;
import com.aegon.hotelbooking.model.Customer;
import com.aegon.hotelbooking.model.Room;

@Named
public class Repository {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Room> findRooms() {

		TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r", Room.class);
		List<Room> results = query.getResultList();
		return results;
	}

	@Transactional
	public Room findRoomBookingsByRoomNumber(int roomNumber) {

		TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r left join fetch r.bookings WHERE r.roomNumber = :roomNumber", Room.class);
		query.setParameter("roomNumber", roomNumber);
		Room room = query.getSingleResult();
		
		return room;
	}
	
	@Transactional
	public List<Customer> findCustomers() {

		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
		List<Customer> results = query.getResultList();
		return results;
	}
	
	@Transactional
	public Customer findCustomerById(int customerId) {

		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c left join fetch c.bookings where c.customerId = :customerId", Customer.class);
		query.setParameter("customerId", customerId);
		return query.getSingleResult();
	}
	
	@Transactional
	public List<Booking> findBookings() {

		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b", Booking.class);
		List<Booking> results = query.getResultList();
		return results;
	}
	
	@Transactional
	public List<Booking> findBookings(int roomNumber, LocalDate startDate, LocalDate endDate) {

		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b join b.room r where r.roomNumber = :roomNumber and :startDate < b.endDate or :endDate < b.startDate", Booking.class);
		query.setParameter("roomNumber", roomNumber);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<Booking> results = query.getResultList();
		return results;
	}
	
	@Transactional
	public void saveCustomerBooking(Customer customer) {
		
		em.persist(customer);
		for (Booking booking : customer.getBookings()) {
			
			booking.setCustomer(customer);
			booking.setRoom(findRoomBookingsByRoomNumber(booking.getRoomNumber()));
			booking.populateDatesFromStrings();
			
			em.persist(booking);
		}
		
		
	}
	
}
