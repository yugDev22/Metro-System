package com.metro.persistence;

import com.metro.bean.Passenger;

public interface PassengerDao {

	public Passenger searchPassenger(int id);

	public int addPassenger(Passenger passenger);
	
	public int updatePassengerDetails(Passenger passenger);
}
