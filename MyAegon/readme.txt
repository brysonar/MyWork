1) To run application run - HotelBookingStartUp - this start up spring boot and creates in memory JAVA DB database (Derby), tables and inserts some records

2) InitialDataLoad creates the initial records eg rooms etc. Note as this is simply a dataload script it is not unit tested

3) Note due to time constraints only very basic error handling and validation has been done. Mainly just happy path.

3) Tests

The restful controller is - HotelBookingRestfulServices

In a browser run the following

a) http://localhost:8080/aegon/hotelbooking/findRoomBookings/room/123

This will show the room booking for Room Number 123.

I have created in addition a general findRooms that will display all so that you can see the other room numbers which are (121, 122, 123, 124, 125)
http://localhost:8080/aegon/hotelbooking/findRooms


b) http://localhost:8080/aegon/hotelbooking/findCustomerBookings/customer/1

Search for customer booking by Customer ID

Given a helper findCustomers to show all customers so that you know the ID when you create a customer when booking a room
http://localhost:8080/aegon/hotelbooking/findCustomers


c) http://localhost:8080/aegon/hotelbooking/findRoomAvailability/room/123?startDate=11/07/2017&endDate=20/10/2017


Room Numbers are 121, 122, 123, 124, 125

The above url will show room availability. Below url will show message that no booking were found between the target dates

http://localhost:8080/aegon/hotelbooking/findRoomAvailability/room/123?startDate=12/07/2018&endDate=20/10/2018

The following url will show error message saying start date cannot be after end date
http://localhost:8080/aegon/hotelbooking/findRoomAvailability/room/123?startDate=12/07/2019&endDate=20/10/2018


d) Book room - I used chrome postman to test this. See screenshot postman_post.png in root directory of this project for details

POST, JSON

http://localhost:8080/aegon/hotelbooking/bookRoom


{"firstName":"Sarah","lastName":"Smith","mobile":"09803 234 256", 
"bookings":[{"startDateAsString":"15/07/2017","endDateAsString":"27/07/2017", "roomNumber":"122"}]}


Notes - each booking creates a new customer - there is no matching to search for existing customers




