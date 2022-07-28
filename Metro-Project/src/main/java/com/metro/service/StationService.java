package com.metro.service;

import com.metro.bean.Station;

public interface StationService {

	public Station searchMetroStationById(int stationId);
	public int addMetroStation(Station station);
}
