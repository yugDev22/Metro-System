package com.metro.service;

import com.metro.bean.Passenger;

public interface PassengerService {

	public Passenger searchPassengerById(int id);

	public int addNewPassenger(Passenger passenger);

	public int updatingPassengerDetails(Passenger passenger);
}
