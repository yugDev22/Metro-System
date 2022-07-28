package com.metro.persistence;

import com.metro.bean.Station;

public interface StationDao {
	
	public Station searchStationById(int stationId);
	public int addStation(Station station);
}
