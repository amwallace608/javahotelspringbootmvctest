package com.aaronwallace.hotelspringmvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "rooms", uniqueConstraints=@UniqueConstraint(columnNames= {"roomNum", "id"}))
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int roomNum;
	private String bedType;
	private double rate;
	private String occupantName;
	private char smoking;
	private boolean occupied;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	/*
	//default constructor
	public Room() {
		super();
	}

	//parameterized constructor
	public Room(int roomNum, String bedType, double rate, char smoking) {
		super();
		this.roomNum = roomNum;
		this.bedType = bedType;
		this.rate = rate;
		this.smoking = smoking;
		this.occupied = false;
	}*/

	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getOccupant() {
		//return occupant if occupied
		if(occupied)
			return occupantName;
		else
			return "Not Occupied";
	}

	public void setOccupantName(String occupantName) {
		this.occupantName = occupantName;
	}

	public char getSmoking() {
		return smoking;
	}

	public void setSmoking(char smoking) {
		this.smoking = smoking;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	//toString method - provide room details
	public String toString() {
		//format and populate details string
		String roomDetails = "Room Number: " + roomNum + "\n" + 
				"Occupant name: " + this.getOccupant() + "\n" + 
				"Smoking room: " + smoking + "\n"  + 
				"Bed Type: " + bedType + "\n" + 
				"Rate: " + rate + "\n";
		return roomDetails;
	}

	public String getOccupantName() {
		return occupantName;
	}
	
	
	
}