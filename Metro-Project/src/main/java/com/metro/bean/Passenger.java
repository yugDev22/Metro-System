package com.metro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passenger {
	private int passengerId;
	private String passengerName;
	private String passengerPhoneNumber;
	private String passengerEmail;
	private int age;
}
