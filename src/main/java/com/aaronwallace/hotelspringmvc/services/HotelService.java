package com.aaronwallace.hotelspringmvc.services;

import java.util.List;

import com.aaronwallace.hotelspringmvc.entity.Hotel;
import com.aaronwallace.hotelspringmvc.entity.Room;

public interface HotelService {
	
	//get hotel(s)
	List<Hotel> getHotels();
	Hotel getHotelById(long id);
	List<Room> getReservations(long hotel_id);
	Room getRoomById(long id);
	
	//add room
	void addRoom(Room room);
	void updateHotel(Hotel hotel);
	
	//add reservation
	
	//cancel reservation
	
	

}
