package com.aegon.hotelbooking;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.aegon.hotelbooking.repository.InitialDataLoad;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class HotelBookingStartUp {

	private static final Logger LOG = LoggerFactory.getLogger(HotelBookingStartUp.class);

	@Inject
	private InitialDataLoad initialDataLoad;
	
	public static void main(String[] args) throws Exception {
		LOG.info("Starting Hotel Booking Application");
		SpringApplication.run(HotelBookingStartUp.class, args);
	}

	@PostConstruct
	private void loadData() {
		LOG.info("Initial Data Load");
		initialDataLoad.loadData();
	}
}
