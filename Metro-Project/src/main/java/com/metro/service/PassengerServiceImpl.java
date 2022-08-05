package com.metro.service;

import com.metro.bean.Passenger;
import com.metro.persistence.PassengerDao;
import com.metro.persistence.PassengerDaoImpl;

public class PassengerServiceImpl implements PassengerService {
	
	private PassengerDao passengerDao=new PassengerDaoImpl();

	@Override
	public Passenger searchPassengerById(Integer id) {
		return passengerDao.searchPassenger(id);
	}

	@Override
	public Passenger addNewPassenger(Passenger passenger) {
		if(passengerDao.searchPassenger(passenger.getPassengerId())==null) {
			return passengerDao.addPassenger(passenger);
		}
		return null;
	}

	@Override
	public Passenger updatingPassengerDetails(Passenger passenger) {
		if(passengerDao.searchPassenger(passenger.getPassengerId())!=null) {
			return passengerDao.updatePassengerDetails(passenger);
		}
		return null;
	}

//	@Override
//	public Integer getPassengerId() {
//		Passenger passenger = passengerDao.getLastPassenger();
//		if(passenger!=null) {
//			return passenger.getPassengerId()+1;
//		}
//		return 2001;
//	}

}
