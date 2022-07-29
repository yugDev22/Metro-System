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
		System.out.println("--Welcome to Metro System--");
		System.out.println("1. Show Transactions by card id");
		System.out.println("2. Add a new Transaction");
		System.out.println("3. Exit");
		
	}

	@Override
	public void performMenu(int choice) {
		Scanner scanner = new Scanner(System.in);
		switch(choice) {
		case 1:
			System.out.println("Enter card id to fetch all transactions of that card:");
			int cardId = scanner.nextInt();
			ArrayList<Transaction> arr = transactionService.getAllTransactionsByCardId(cardId);
			if(arr.size()==0) {
				System.out.println("No transactions found for card id:"+cardId);
			}
			else {
				for(Transaction transaction:arr) {
					System.out.println(transaction);
				}
			}
			break;
		case 2:
			System.out.println("Enter transaction id: ");
			String transId = scanner.nextLine();
			
			System.out.println("Enter card id: ");
			int mcardId = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Enter boarding station id: ");
			int boardingStationId = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Enter destination station id: ");
			int destinationStationId = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Enter fare: ");
			double fare = Double.parseDouble(scanner.nextLine());
			
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");
			
			System.out.println("Taking swipe in time from system ");
			LocalDateTime swipeInTime = LocalDateTime.now();
			//LocalDateTime swipeInTime = LocalDateTime.parse(scanner.nextLine(),formatter);
			
			System.out.println("Taking swipe out time from system ");
			LocalDateTime swipeOutTime = LocalDateTime.now();
			//LocalDateTime swipeOutTime = LocalDateTime.parse(scanner.nextLine(),formatter);
			Transaction newTransaction = new Transaction(transId, mcardId, boardingStationId, destinationStationId, fare, swipeInTime, swipeOutTime);
			int rows = transactionService.addNewTransaction(newTransaction);
			if(rows==0) {
				System.out.println("Unable to add");
				break;
			}
			System.out.println("Transaction added successfuly!");
			break;
		case 3:
			System.out.println("Thanks for using metro service!");
			System.exit(0);
		default:
			System.out.println("Please enter a valid choice");
			break;
		}
		
	}

}
