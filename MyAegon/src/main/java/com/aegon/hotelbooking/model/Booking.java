package com.aegon.hotelbooking.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.aegon.hotelbooking.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "booking")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@bookingId")
@JsonInclude(Include.NON_NULL)
public class Booking implements Serializable {

	private static final long serialVersionUID = -4945617710200536780L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	private Customer customer;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "roomId", referencedColumnName = "roomId")
	private Room room;
	
	@Transient
	private Integer roomNumber; 
	
	private LocalDate startDate;
	private LocalDate endDate;

	@Transient
	private String startDateAsString;
	
	@Transient
	private String endDateAsString;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public void populateDatesFromStrings() {
		
		this.startDate = DateUtil.convertToDate("Start Date", startDateAsString);
		this.endDate = DateUtil.convertToDate("End Date", endDateAsString);
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public String getStartDateAsString() {
		return startDateAsString;
	}
	public void setStartDateAsString(String startDateAsString) {
		this.startDateAsString = startDateAsString;
	}
	public String getEndDateAsString() {
		return endDateAsString;
	}
	public void setEndDateAsString(String endDateAsString) {
		this.endDateAsString = endDateAsString;
	}	
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (bookingId != other.bookingId)
			return false;
		return true;
	}


}
