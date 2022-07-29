package com.metro.presentation;

import java.util.Scanner;

import com.metro.bean.Passenger;
import com.metro.service.PassengerService;
import com.metro.service.PassengerServiceImpl;

public class MetroPresentationImpl implements MetroPresentation {

	private PassengerService passengerService = new PassengerServiceImpl();

	@Override
	public void showMenu() {
		System.out.println("Please select your option :");
		System.out.println("1. Select Passengers by passenger Id");
		System.out.println("2. Add a new passenger");
		System.out.println("3. Update passenger details ");
		System.out.println("4. Exit");

	}

	@Override
	public void performMenu(int choice) {

		Scanner sc = new Scanner(System.in);
		switch (choice) {

		case 1:
			System.out.println("Enter the passenger id : ");
			int id = sc.nextInt();
			Passenger passenger = passengerService.searchPassengerById(id);
			if (passenger != null) {
				System.out.println(passenger);
			} else {
				System.out.println("Passenger with " + id + " does not exist!");
			}
			break;

		case 2:
			System.out.println("Enter the required details to add a new passenger");
			Passenger passenger1 = new Passenger();
			System.out.println("Enter the passenger Id : ");
			passenger1.setPassengerId(sc.nextInt());
			System.out.println("Enter the passenger name : ");
			passenger1.setPassengerName(sc.next());
			System.out.println("Enter the passenger phone number : ");
			passenger1.setPassengerPhoneNumber(sc.next());
			System.out.println("Enter the passenger email address : ");
			passenger1.setPassengerEmail(sc.next());
			System.out.println("Enter the passenger age");
			passenger1.setAge(sc.nextInt());
			if (passengerService.addNewPassenger(passenger1) == 1) {
				System.out.println("Passenger added successfully !");
			} else {
				System.out.println("Passenger can be added. Please check our details");
			}
			break;

		case 3:
			System.out.println("Please enter the details to be updated");
			System.out.println("Enter the passenger Id whose details should be updated : ");
			int pid1 = sc.nextInt();
			Passenger passenger2 = passengerService.searchPassengerById(pid1);
			if (passenger2 == null) {
				System.out.println("Passenger with " + pid1 + " does not exist!");
			} else {
				int id1=passenger2.getPassengerId();
				String pname=passenger2.getPassengerName();
				String phoneNo=passenger2.getPassengerPhoneNumber();
				String mail=passenger2.getPassengerEmail();
				int page=passenger2.getAge();
                
				System.out.println("Do you want to update passenger name? Type 1 to update and 0 to not update");
				if (sc.nextInt() == 1) {
					System.out.println("Please update the passenger name");
					passenger2.setPassengerName(sc.next());
				}
				System.out.println("Do you want to update your phone number? Type 1 to update and 0 to not update");
				if (sc.nextInt() == 1) {
					System.out.println("Please update the passenger phone number");
					passenger2.setPassengerPhoneNumber(sc.next());
				}
				System.out.println("Do you want to update your email id? Type 1 to update and 0 to not update");
				if (sc.nextInt() == 1) {
					System.out.println("Please give the updated the passenger email");
					passenger2.setPassengerEmail(sc.next());
				}
				System.out.println("Do you want to update your age ? Type 1 to update and 0 to not update");
				if (sc.nextInt() == 1) {
					System.out.println("Please give the updated passenger age : ");
					passenger2.setAge(sc.nextInt());
				}
				
			}
			if(passengerService.updatingPassengerDetails(passenger2)>0) {
				System.out.println("Passenger details updated successfully !");
			}
			else {
				System.out.println("Passenger details cannot be updated. Please try again.");
			}
			break;

		case 4:
			System.exit(0);
			break;
		default:
			System.out.println("Invalid option!! Please select a valid option.");
		}

	}

}
