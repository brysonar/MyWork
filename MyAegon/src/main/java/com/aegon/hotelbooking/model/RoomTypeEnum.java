package com.aegon.hotelbooking.model;

public enum RoomTypeEnum {

	SINGLE("Single"), 
	DOUBLE("Double");
	
	private String description;
	
	RoomTypeEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
