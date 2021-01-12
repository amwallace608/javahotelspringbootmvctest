package com.aaronwallace.hotelspringmvc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;


@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String location;
	private int occupiedCnt;
	private int numOfRooms;
	private static final int NOT_FOUND = -1;
	
	@OneToMany(mappedBy="hotel", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Room> theRooms;
	

	//hotel methods
	
	//check if hotel is full method
	public boolean isFull() {
		if(occupiedCnt < numOfRooms) {
			//if occupied is less than total rooms, hotel not full
			return false;
		} else {
			//number of occupied rooms is = total rooms
			return true;
		}
	}
	
	//check if hotel is empty method
	public boolean isEmpty() {
		return occupiedCnt == 0 ? true : false;
	}
	
	/*
	//add room method
	public void addRoom(Room rm1) {
		
		//set hotel for room
		rm1.setHotel(this);
		//add to rooms array list
		theRooms.add(rm1);
		//increment number of rooms
		this.setNumOfRooms(getNumOfRooms() + 1);
		
	}*/
	
	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getOccupiedCnt() {
		return occupiedCnt;
	}

	public void setOccupiedCnt(int occupiedCnt) {
		this.occupiedCnt = occupiedCnt;
	}

	public int getNumOfRooms() {
		return numOfRooms;
	}

	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public List<Room> getTheRooms() {
		return theRooms;
	}

	public void setTheRooms(List<Room> theRooms) {
		this.theRooms = theRooms;
	}
	

}