package com.metro.project;

import java.util.Scanner;

import com.metro.presentation.MetroPresentation;
import com.metro.presentation.MetroPresentationImpl;

public class App {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MetroPresentation metroPresentation = new MetroPresentationImpl();
		while(true) {
			metroPresentation.showMenu();
			System.out.println("Enter your choice: ");
			int choice = scanner.nextInt();
			metroPresentation.performMenu(choice);
		}
	}
}
