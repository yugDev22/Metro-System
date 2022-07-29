package com.metro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	private String transactionId;
	private int cardId;
	private int boardingStationId;
	private int destinationStationId;
	private double fare;
	private LocalDateTime swipeInTime;
	private LocalDateTime swipeOutTime;

}
