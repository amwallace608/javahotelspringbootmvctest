package com.aaronwallace.hotelspringmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aaronwallace.hotelspringmvc.entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Long>{

	//get hotel by id
	@Query("from Hotel where id = :hotelId")
	Hotel getHotelById(@Param("hotelId")long id);
}
