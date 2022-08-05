package com.metro.persistence;

import com.metro.bean.Passenger;

public interface PassengerDao {

	public Passenger searchPassenger(Integer id);
	public Passenger addPassenger(Passenger passenger);
	public Passenger updatePassengerDetails(Passenger passenger);
	public Passenger getLastPassenger();
}
