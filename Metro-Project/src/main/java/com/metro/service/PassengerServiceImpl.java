package com.metro.service;

import com.metro.bean.Passenger;
import com.metro.persistence.PassengerDao;
import com.metro.persistence.PassengerDaoImpl;

public class PassengerServiceImpl implements PassengerService {
	
	private PassengerDao passengerDao=new PassengerDaoImpl();

	@Override
	public Passenger searchPassengerById(int id) {
		
		return passengerDao.searchPassenger(id);
	}

	@Override
	public int addNewPassenger(Passenger passenger) {
		if(passengerDao.addPassenger(passenger)>0)
			return 1;
		return 0;
	}

	@Override
	public int updatingPassengerDetails(Passenger passenger) {
		if(passengerDao.updatePassengerDetails(passenger)>0)
			return 1;
		return 0;
	}

}
