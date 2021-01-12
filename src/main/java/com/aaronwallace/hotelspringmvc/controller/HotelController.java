package com.aaronwallace.hotelspringmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aaronwallace.hotelspringmvc.entity.Hotel;
import com.aaronwallace.hotelspringmvc.entity.Room;
import com.aaronwallace.hotelspringmvc.services.HotelService;

@Controller
public class HotelController {

	@Autowired
	HotelService hotelService;
	
	@RequestMapping("/")
	public String hotelHome(Model model) {
		//get hotel(s) from DB
		List<Hotel> hotels = hotelService.getHotels();
		
		Hotel hotel = hotels.get(0);
		
		//add hotel to model as attribute
		model.addAttribute("hotel", hotel);
		
		return "index";
	}
	
	@RequestMapping("/rooms/{hotelId}")
	public String hotelRooms(Model model, @PathVariable(name="hotelId") long hotelId) {
		
		//get hotel by ID
		Hotel hotel = hotelService.getHotelById(hotelId);
		//get rooms from hotel
		List<Room> rooms = hotel.getTheRooms();

		model.addAttribute("rooms", rooms);
		model.addAttribute("hotel", hotel);
		
		return "rooms";
	}
	
	@RequestMapping("/new_room/{hotelId}")
	public String addRoom(Model model, @PathVariable(name="hotelId") long hotelId) {
		//get hotel by id
		Hotel hotel = hotelService.getHotelById(hotelId);
		Room room = new Room();
		//add attribute to model
		model.addAttribute("hotel", hotel);
		model.addAttribute(room);
		
		return "new_room";
	}
	
	
	@RequestMapping(value="/save_room/{hotelId}", method = RequestMethod.POST)
	public String saveRoom(@ModelAttribute("room") Room room, @PathVariable(name="hotelId") long hotelId, Model model) {
		//get hotel by id
		Hotel hotel = hotelService.getHotelById(hotelId);
		//get hotel room list
		List<Room> rooms = hotel.getTheRooms();
		//set default occupant status properties for room
		room.setOccupantName(null);
		room.setOccupied(false);
		room.setHotel(hotel);
		//save room to hotel's room list
		rooms.add(room);
		//set hotel's room's list to updated list
		hotel.setTheRooms(rooms);
		hotel.setNumOfRooms(rooms.size());
		
		//update hotel
		hotelService.updateHotel(hotel);
		model.addAttribute("hotel", hotel);
		//return to to rooms page
		return "redirect:/rooms/{hotelId}";
	}
	
	@RequestMapping(value="/reservations/{hotelId}")
	public String viewReservations(Model model, @PathVariable(name="hotelId")long hotelId) {
		//get all occupied rooms in hotel w/ hotel Id from DB
		List<Room> reservations = hotelService.getReservations(hotelId);
		
		model.addAttribute("rooms", reservations);
		//model.addAttribute(hotelId);
		
		return "reservations";
	}
	
	@RequestMapping(value="/cancel_reservation/{id}")
	public String cancelReservation(@PathVariable(name="id") long roomId, Model model) {
		//get room by id
		Room room = hotelService.getRoomById(roomId);
		Hotel hotel = room.getHotel();
		
		//update reservation properties of room
		room.setOccupied(false);
		room.setOccupantName(null);
		
		//update room in DB
		hotelService.addRoom(room);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/reserve/{id}")
	public ModelAndView reserveRoom(@PathVariable(name="id")long roomId) {
		//create ModelAndView for page
		ModelAndView mav = new ModelAndView("reserve");
		//get Room from db
		Room room = hotelService.getRoomById(roomId);
		//add room to mav
		mav.addObject("room", room);
		
		//get hotel from room
		Hotel hotel = room.getHotel();
		//add hotel to mav
		mav.addObject("hotel", hotel);
		
		return mav;
	}
	
	@RequestMapping(value="/new_reservation/{hotelId}")
	public String makeReservation(@ModelAttribute("room") Room room, @PathVariable("hotelId") Long id, Model model) {
		//if room already occupied, redirect to reservation failed page
		if(room.isOccupied()) {
			return "redirect:/reservation_failed";
		} else {
			//get hotel by ID
			Hotel hotel = hotelService.getHotelById(id);
			//update room occupied status
			room.setOccupied(true);
			room.setHotel(hotel);
			
			List<Room> rooms = hotel.getTheRooms();
			//replace room in list
			for(int i = 0; i < rooms.size(); i++) {
				if(rooms.get(i).getRoomNum() == room.getRoomNum()) {
					room.setId(rooms.get(i).getId());
					rooms.set(i, room);
					hotel.setTheRooms(rooms);
				}
			}
			
			//update room in db
			//hotelService.addRoom(room);
			hotelService.updateHotel(hotel);
			
			model.addAttribute("hotel", hotel);
			
			return "redirect:/rooms/{hotelId}";
		}
	}
	
	@RequestMapping(value="/reservation_failed")
	public String reservationFail(Model model) {
		model.addAttribute("message", "Reservation failed: room is already booked");
		
		return "reservation_failed";
	}
}
