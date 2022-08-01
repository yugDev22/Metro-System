package com.metro.project;

import java.util.Scanner;

import com.metro.presentation.MetroPresentation;
import com.metro.presentation.MetroPresentationImpl;

public class App {
	
	public static void main(String[] args) {

		MetroPresentation metroPresentation=new MetroPresentationImpl();
		Scanner scanner=new Scanner(System.in);
		while(true) {
			metroPresentation.showMenu();
			System.out.println("Enter Choice ");
			int choice=scanner.nextInt();
			metroPresentation.performMenu(choice);
					
		}
	}
}
