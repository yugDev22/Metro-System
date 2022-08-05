package com.metro.service;

import java.util.ArrayList;

import com.metro.bean.Station;
import com.metro.persistence.StationDao;
import com.metro.persistence.StationDaoImpl;

public class StationServiceImpl implements StationService {
	StationDao stationDao = new StationDaoImpl();
	@Override
	public Station searchMetroStationById(int stationId) {
		return stationDao.searchStationById(stationId);
	}

	@Override
	public int addMetroStation(Station station) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Station> getAllStations() {
		return stationDao.getAllStations();
	}

}
