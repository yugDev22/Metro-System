package com.metro.presentation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.metro.bean.Transaction;
import com.metro.service.MetroCardService;
import com.metro.service.MetroCardServiceImpl;
import com.metro.service.TransactionService;
import com.metro.service.TransactionServiceImpl;

public class MetroPresentationImpl implements MetroPresentation {
	
	TransactionService transactionService = new TransactionServiceImpl();
	MetroCardService metroCardService = new MetroCardServiceImpl();
	@Override
	public void showMenu() {
		
		
	}

	@Override
	public void performMenu(int choice) {
		
		
	}

}
