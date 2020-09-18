package com.dollarsbank.controller;

import java.util.Scanner;

import com.dollarsbank.utility.ConsolePrinterUtility;

public class DollarsBankController {

	// Take in all user input and map to appropriate service layer methods
	private Scanner input;
	
	public DollarsBankController() {
		this.input = new Scanner(System.in);
	}
	
	public void mainMenuInteraction() {
		// switch case w/ sub menu interaction choices mapped to sub interaction methods
		ConsolePrinterUtility consolePrinter = new ConsolePrinterUtility();
		consolePrinter.displayInitialMenu();
		String choice = takeInput();
		
		switch(choice) {
		case "1":
			consolePrinter.displayAccountCreation();
			//determineNextInteraction();
			break;
		case "2":
			consolePrinter.displayCustomerLogin();
			//determineNextInteraction();
			break;
		case "3":
			consolePrinter.displayExitScreen();
			closeConnection();
			break;
		default:
			consolePrinter.displayInvalidChoice();
			//determineNextInteraction();
			break;
		}
		
	}
	
	public String takeInput() {
		return input.nextLine().trim();
	}
	
	public void determineNextInteraction() {
		System.out.println("What would you like to do next?\n");
	}
	
	public void closeConnection() {
		input.close();
	}
}
