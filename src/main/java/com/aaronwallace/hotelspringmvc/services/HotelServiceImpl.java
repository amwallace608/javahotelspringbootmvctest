package com.aaronwallace.hotelspringmvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaronwallace.hotelspringmvc.entity.Hotel;
import com.aaronwallace.hotelspringmvc.entity.Room;
import com.aaronwallace.hotelspringmvc.repos.HotelRepo;
import com.aaronwallace.hotelspringmvc.repos.RoomRepo;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	HotelRepo hotelRepo;
	
	@Autowired
	RoomRepo roomRepo;

	@Override
	public List<Hotel> getHotels() {
		List<Hotel> hotels = hotelRepo.findAll();
		if(hotels.isEmpty()) {
			//no hotels in DB
			System.out.println("No hotels found in DB");
			return null;
		} else {
			return hotels;
		}
	}

	@Override
	public Hotel getHotelById(long id) {
		Hotel hotel = hotelRepo.getHotelById(id);
		
		return hotel;
	}

	@Override
	public void addRoom(Room room) {
		roomRepo.save(room);
	}

	@Override
	public List<Room> getReservations(long hotel_id) {
		List<Room> reserv = roomRepo.findOccupied(true, hotel_id);
		if(reserv.isEmpty()) {
			//no occupied rooms/no reservations
			System.out.println("No reservations found in DB for hotel: " + hotel_id);
			return null;
		} else {
			return reserv;
		}
	}

	@Override
	public Room getRoomById(long id) {
		Room room = roomRepo.getRoomById(id);
		return room;
	}

	@Override
	public void updateHotel(Hotel hotel) {
		hotelRepo.save(hotel);
	}
	
}
