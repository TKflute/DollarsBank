package com.dollarsbank.utility;

import java.util.HashMap;
import java.util.List;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.Transaction;

public class ConsolePrinterUtility {

	// methods for displaying console menu
	DollarsBankController controller = new DollarsBankController();
	DataGeneratorStubUtility dataService = new DataGeneratorStubUtility();
	
	public void displayInitialMenu() {
		System.out.println("+---------------------------\n| DOLLARSBANK Welcomes You! |\n+---------------------------\n");
		System.out.println("Please choose a menu option below:\n");
		System.out.println("1. Create New Account\n2. Login\n3. Exit");
	}
	
	public void displayAccountCreation() {
		System.out.println("+---------------------------\n| Enter Details For New Account |\n+---------------------------\n");
		System.out.println("Customer Name:\n");
		String name = controller.takeInput();
		
		System.out.println("Customer Address:\n");
		String address = controller.takeInput();
		
		System.out.println("Customer Phone Number:\n");
		String phoneNumber = controller.takeInput();
		
		System.out.println("User Id:\n");
		String userId = controller.takeInput();
		
		System.out.println("Password (8 characters including upper, lower, and special):\n");
		String password = controller.takeInput();
		
		System.out.println("Choose an Account type:\n");
		System.out.println("1. Checking\n2. Savings");
		String accountType = controller.takeInput();
		
		System.out.println("Initial Deposit Amount:\n");
		String initialDeposit = controller.takeInput();
		
		Account newAccount = dataService.createAccount(accountType, initialDeposit);
		dataService.addCustomer(new Customer(name, address, phoneNumber, userId, password, newAccount));
		// TODO: write to file?
	}
	
	public void displayCustomerLogin() {
		
		System.out.println("+---------------------------\n| Enter Login Details |\n+---------------------------\n");
		System.out.println("User Id:\n");
		String userId = controller.takeInput();
		
		System.out.println("Password:\n");
		String password = controller.takeInput();
		
		if(dataService.verifyLogin(userId, password)){
			dataService.logIn(userId);
			displayWelcomeCustomerMenu();
		}else {
			System.out.println("Invalid login");
			displayExitScreen();
			controller.closeConnection();
		}
	
	}
	
	public void displayExitScreen() {
		
		System.out.println("Thank you for using DOLLARSBANK. Have a good day.");
	}
	
	public void displayInvalidChoice() {
		System.out.println("Invalid menu option");
	}
	
	public void displayWelcomeCustomerMenu() {
		
		System.out.println("+---------------------------\n| WELCOME Customer! |\n+---------------------------\n");
		System.out.println("How can we help you today?\n");
		System.out.println("1. Deposit Amount\n2. Withdraw Amount\n3. Funds Transfer\n"
				+ "4. View 5 Recent Transactions\n5. Display Customer Information\n6. Logout");
		
		String choice = controller.takeInput();
		
		switch(choice) {
		case "1":
			displayDepositMenu();
			determineNextInteraction();
			break;
		case "2":
			displayWithdrawMenu();
			determineNextInteraction();
			break;
		case "3":
			displayTransferMenu();
			determineNextInteraction();
			break;
		case "4":
			displayLastFiveTransactions();
			determineNextInteraction();
			break;
		case "5":
			displayCustomerInformation();
			determineNextInteraction();
			break;
		case "6":
			dataService.logOut();
			displayExitScreen();
			controller.closeConnection();
		}
		
	}
	
	public void displayDepositMenu() {
		
		System.out.println("Enter account id for deposit:\n");
		displayAccounts();
		
		String accountId = controller.takeInput();
		System.out.println("Enter deposit amount:\n");
		String depositAmt = controller.takeInput();
		
		Transaction deposit = dataService.makeDeposit(accountId, depositAmt);
		displayTransactionSummary(deposit);
	}
	
	public void displayWithdrawMenu() {
		
		System.out.println("Enter account id for withdraw:\n");
		displayAccounts();
		
		String accountId = controller.takeInput();
		System.out.println("Enter withdraw amount:\n");
		String withdrawAmt = controller.takeInput();
		
		Transaction withdrawl = dataService.makeWithdrawl(accountId, withdrawAmt);
		displayTransactionSummary(withdrawl);
	}
	
	public void displayCustomerInformation() {
		
		Customer loggedIn = dataService.getLoggedIn();
		System.out.println("Customer Details: " + loggedIn.toString());
		System.out.print("Account Details: ");
		displayAccounts();
	}
	
	public void displayTransferMenu() {
		
		System.out.println("Enter account ids for transfer:\n");
		displayAccounts();
		
		System.out.println("Enter transfer from id: ");
		String accountIdFrom = controller.takeInput();
		
		System.out.println("Enter transfer to id: ");
		String accountIdTo = controller.takeInput();
		
		System.out.println("Enter transfer amount:\n");
		String transferAmt = controller.takeInput();
		
		Transaction transfer = dataService.makeTransfer(accountIdFrom, accountIdTo, transferAmt);
		displayTransactionSummary(transfer);
		
	}
	
	public void displayAccounts() {
		
		HashMap<Long, Account> accounts = dataService.getCustomerAccounts();
		accounts.forEach((key, value) -> System.out.println(key + ", " + value.getClass()));
	}
	
	public void displayTransactionSummary(Transaction transaction) {
		
		System.out.println("Transaction Summary: " + transaction.toString());
	}
	
	public void displayLastFiveTransactions() {
		
		System.out.println("Enter account id for transaction history:\n");
		displayAccounts();
		
		String accountId = controller.takeInput();
		List<Transaction> lastFiveTransactions = dataService.fetchFiveLastTransactions(accountId);
		
		for(Transaction t : lastFiveTransactions) {
			System.out.println(t.toString()+ "\n");
		}
	}
	
	public void determineNextInteraction() {
		
		System.out.println("What would you like to do next?\n");
		System.out.println("1. Go Back to Customer Menu\n2. Exit");
		String choice = controller.takeInput();
		if(choice.contentEquals("1")) {
			displayWelcomeCustomerMenu();
		}else {
			dataService.logOut();
			displayExitScreen();
			controller.closeConnection();
		}
	}
}
