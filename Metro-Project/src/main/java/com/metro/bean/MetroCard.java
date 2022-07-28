package com.metro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetroCard {
	private int cardId;
	private int passengerId;
	private double balance;
}
