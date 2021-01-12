package com.aaronwallace.hotelspringmvc.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aaronwallace.hotelspringmvc.entity.Room;

public interface RoomRepo extends JpaRepository<Room, Long>{

	//get all occupied/unoccupied rooms
	@Query("from Room where occupied = :occ and hotel_id = :hotelId")
	List<Room> findOccupied(@Param("occ") boolean occupied, @Param("hotelId") long id);
	
	//get room by id
	@Query("from Room where id = :roomId")
	Room getRoomById(@Param("roomId") long id);
}
