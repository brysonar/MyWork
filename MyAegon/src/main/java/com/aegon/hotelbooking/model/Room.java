package com.aegon.hotelbooking.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "room")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@roomId")
@JsonInclude(Include.NON_NULL)
public class Room implements Serializable {

	private static final long serialVersionUID = -7312683230852229108L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roomId;
	
	private int roomNumber;
	private RoomTypeEnum roomType;
	private int cost;
	 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	private Set<Booking> bookings;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomTypeEnum getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomTypeEnum roomType) {
		this.roomType = roomType;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}


}
