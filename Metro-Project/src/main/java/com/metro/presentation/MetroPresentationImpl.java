package com.metro.presentation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.metro.bean.MetroCard;
import com.metro.bean.Passenger;
import com.metro.bean.Station;
import com.metro.bean.Transaction;
import com.metro.persistence.StationDao;
import com.metro.persistence.StationDaoImpl;
import com.metro.service.MetroCardService;
import com.metro.service.MetroCardServiceImpl;
import com.metro.service.PassengerService;
import com.metro.service.PassengerServiceImpl;
import com.metro.service.StationService;
import com.metro.service.StationServiceImpl;
import com.metro.service.TransactionService;
import com.metro.service.TransactionServiceImpl;

public class MetroPresentationImpl implements MetroPresentation {

	private PassengerService passengerService = new PassengerServiceImpl();
	private MetroCardService metroCardService = new MetroCardServiceImpl();
	private TransactionService transactionService = new TransactionServiceImpl();
	private StationService stationService = new StationServiceImpl();


	@Override
	public void showMenu() {
		System.out.println("-- Welcome to Metro System --");
		System.out.println("1. New Passenger");
		System.out.println("2. Existing Passenger");
		System.out.println("3. Exit");

	}

	@Override
	public void performMenu(int choice) {

		Scanner scanner = new Scanner(System.in);
		switch (choice) {
		case 1:
			System.out.println("Welcome new passenger, please register yourself.");
			System.out.println("Enter your choice: ");
			System.out.println("1. Register now");
			System.out.println("2. Go back");
			int internalChoice = scanner.nextInt();
			scanner.nextLine();
			boolean flag1=true;
			while(flag1) {
				switch(internalChoice) {
				case 1:
					Passenger newPassenger = new Passenger();
					System.out.println("Enter your Id:");
					newPassenger.setPassengerId(Integer.parseInt(scanner.nextLine()));
					System.out.println("Enter your name:");
					newPassenger.setPassengerName(scanner.nextLine());
					System.out.println("Enter your phone number:");
					newPassenger.setPassengerPhoneNumber(scanner.nextLine());
					System.out.println("Enter your email:");
					newPassenger.setPassengerEmail(scanner.nextLine());
					System.out.println("Enter your Age:");
					newPassenger.setAge(Integer.parseInt(scanner.nextLine()));
					Passenger passenger = passengerService.addNewPassenger(newPassenger);
					if(passenger!=null) {
						System.out.println("Passenger added succesfully!");
						System.out.println("Passenger Id:"+passenger.getPassengerId());
						System.out.println("Passenger name:"+passenger.getPassengerName());
						System.out.println("Generating Metro card for you.....");
						MetroCard newCard = new MetroCard(0,passenger.getPassengerId(),100.0);
						newCard = metroCardService.issueNewMetroCard(newCard);
						System.out.println("New MetroCard Generated for you "+passenger.getPassengerName()+"!");
						System.out.println("Card id: "+newCard.getCardId());
						System.out.println("you can add balance to your card now..");
						flag1=false;
						
					}
					else {
						System.out.println("Passenger with id "+newPassenger.getPassengerId()+" already exists!!");
					}
					break;
				case 2:
					flag1=false;
					break;
				default:
					System.out.println("Enter a valid choice!");
					break;
				}
			}
			break;
		case 2:
			System.out.println("Welcome Existing customer, enter your id");
			Integer id = Integer.parseInt(scanner.nextLine());
			Passenger passenger = passengerService.searchPassengerById(id);
			if(passenger!=null) {
				System.out.println("Enter your password: ");
				String pass=scanner.nextLine();
				if(pass.equals(passenger.getPassengerEmail())) {
				boolean flag=true;
				while(flag) {
					System.out.println(">>> Welcome "+passenger.getPassengerName()+" <<<");
					System.out.println("please enter your choice:");
					System.out.println("1. Show All cards");
					System.out.println("2. Issue New card");
					System.out.println("3. Swipe In / Swipe Out");
					System.out.println("4. Check Card Balance");
					System.out.println("5. Recharge Card");
					System.out.println("6. Show all transactions of a card");
					System.out.println("7. Back");
					choice = Integer.parseInt(scanner.nextLine());
					switch(choice) {
						case 1:
							System.out.println("-- All cards Issued to "+passenger.getPassengerName()+" --");
							for(MetroCard card:metroCardService.getAllCards(passenger.getPassengerId())) {
								System.out.println(card);
							}
						
							break;
						case 2:
							System.out.println("Issuing new card...");
							System.out.println("Enter balance to add: ");
							double bal = Double.parseDouble(scanner.nextLine());
							MetroCard nCard = metroCardService.issueNewMetroCard(new MetroCard(0, passenger.getPassengerId(), 100+bal));
							if(nCard!=null) {
								System.out.println("Card generated: ");
								System.out.println("Card ID: "+nCard.getCardId());
								System.out.println("Card balance: "+nCard.getBalance());
							}
							else {
								System.out.println("Unable to issue card, please check if entered details are valid..");
							}
							break;
						case 3:
							System.out.println("Select card: ");
							int i=0;
							ArrayList<MetroCard> cardArr = metroCardService.getAllCards(passenger.getPassengerId());
							for(MetroCard card: cardArr) {
								System.out.println((i+1)+". "+card.getCardId());
								i++;
							}
							if(cardArr.size()==0) {
								break;
							}
							Integer cardNo = Integer.parseInt(scanner.nextLine());
							while(cardNo>cardArr.size()||cardNo<=0) {
								System.out.println("Select valid card");
								cardNo = Integer.parseInt(scanner.nextLine());
							}
							MetroCard card0 = cardArr.get(cardNo-1);
							Integer cardID = card0.getCardId();
							//MetroCard mCard = metroCardService.searchMetroCardById(cardID);
							Transaction swipedIn = transactionService.alreadySwipedIn(cardID);
							if(swipedIn!=null) {
								Station sourceStation = stationService.searchMetroStationById(swipedIn.getBoardingStationId());
								System.out.println("-- swipe out --");
								System.out.println("Already swiped in at: "+sourceStation.getStationName()+" station");
								System.out.println("Swiped in at time: "+swipedIn.getSwipeInTime());
								System.out.println("Do you want to swipe out?");
								System.out.println("press 1 to swipe out, else 0");
								int currChoice = Integer.parseInt(scanner.nextLine());
								while(!(currChoice==0||currChoice==1)) {
									System.out.println("enter valid choice");
									System.out.println("press 1 to swipe out, else 0");
									currChoice = Integer.parseInt(scanner.nextLine());
								}
								if(currChoice==1) {
									System.out.println("Select station: ");
									ArrayList<Station> stationsArr = stationService.getAllStations();
									i=0;
									for(Station s:stationsArr) {
										System.out.println((i+1)+". "+s.getStationName());
										i++;
									}
									int dest = Integer.parseInt(scanner.nextLine());
									while(dest>stationsArr.size()||dest<=0) {
										System.out.println("enter valid choice");
										dest = Integer.parseInt(scanner.nextLine());
									}
									Station destination = stationsArr.get(dest-1);
									swipedIn.setDestinationStationId(destination.getStationId());
									swipedIn.setSwipeOutTime(LocalDateTime.now());
									Transaction swipedOut = transactionService.updateTransaction(swipedIn);
									if(swipedOut!=null) {
										System.out.println("Swiped out at: "+stationService.searchMetroStationById(swipedOut.getDestinationStationId()).getStationName());
										System.out.println("Fare charged: "+swipedOut.getFare());
									}
									else {
										System.out.println("Insufficient balance, please top up your card!");
									}
									
								}
							}
							else {
								System.out.println("-- Swipe In --");
								System.out.println("Select station: ");
								ArrayList<Station> stationsArr = stationService.getAllStations();
								i=0;
								for(Station s:stationsArr) {
									System.out.println((i+1)+". "+s.getStationName());
									i++;
								}
								int src = Integer.parseInt(scanner.nextLine());
								while(src>stationsArr.size()||src<=0) {
									System.out.println("enter valid choice");
									src = Integer.parseInt(scanner.nextLine());
								}
								Station source = stationsArr.get(src-1);
								
								Transaction trans = new Transaction(null, cardID, source.getStationId(), null, 0.0, LocalDateTime.now(), null);
								transactionService.addNewTransaction(trans);
								System.out.println("Successfully Swiped-In");
			
							}
							
							break;
						case 4:
							System.out.println("Select card: ");
							i=0;
							cardArr = metroCardService.getAllCards(passenger.getPassengerId());
							for(MetroCard card: cardArr) {
								System.out.println((i+1)+". "+card.getCardId());
								i++;
							}
							if(cardArr.size()==0) {
								break;
							}
							cardNo = Integer.parseInt(scanner.nextLine());
							while(cardNo>cardArr.size()||cardNo<=0) {
								System.out.println("Select valid card");
								cardNo = Integer.parseInt(scanner.nextLine());
							}
							MetroCard card1 = cardArr.get(cardNo-1);
							System.out.println("Balance of card "+card1.getCardId()+" is : "+card1.getBalance());
							break;
						case 5:
							System.out.println("Select card: ");
							i=0;
							cardArr = metroCardService.getAllCards(passenger.getPassengerId());
							for(MetroCard card: cardArr) {
								System.out.println((i+1)+". "+card.getCardId());
								i++;
							}
							if(cardArr.size()==0) {
								break;
							}
							cardNo = Integer.parseInt(scanner.nextLine());
							while(cardNo>cardArr.size()||cardNo<=0) {
								System.out.println("Select valid card");
								cardNo = Integer.parseInt(scanner.nextLine());
							}
							MetroCard card2 = cardArr.get(cardNo-1);
							System.out.println("Enter balance to add: ");
							bal = Double.parseDouble(scanner.nextLine());
							if(metroCardService.AddCardBalance(card2.getCardId(), bal)!=null) {
								System.out.println("Balance updated");
							}
							else {
								System.out.println("Unable to add!");
							}
							break;
						case 6:
							System.out.println("Select card: ");
							i=0;
							cardArr = metroCardService.getAllCards(passenger.getPassengerId());
							for(MetroCard card: cardArr) {
								System.out.println((i+1)+". "+card.getCardId());
								i++;
							}
							if(cardArr.size()==0) {
								break;
							}
							cardNo = Integer.parseInt(scanner.nextLine());
							while(cardNo>cardArr.size()||cardNo<=0) {
								System.out.println("Select valid card");
								cardNo = Integer.parseInt(scanner.nextLine());
							}
							
							ArrayList<Transaction> transactionArr = transactionService.getAllTransactionsByCardId(cardArr.get(cardNo-1).getCardId());
							for(Transaction t: transactionArr) {
								System.out.println(t);
							}
							break;
						case 7:
							System.out.println("Thank you for using this service");
							flag=false;
							break;
						default:
							System.out.println("Enter valid choice!");
					}
				}
				}
				else {
					System.out.println("Please re-enter your password!");
				}
				
			}
			else {
				System.out.println("Passenger with id "+id+" does not exist!");
			}
			
			
			break;
		case 3:
			System.out.println("Thank you for using Metro service");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid option!! Please select a valid option.");
		}

	}

}
