package com.metro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
	private int stationId;
	private String stationName;
	private int prevStationId;
	private int nextStationId;
}
