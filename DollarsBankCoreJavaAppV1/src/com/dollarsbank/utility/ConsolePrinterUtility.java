package com.dollarsbank.utility;

import java.util.HashMap;

import java.util.List;

//import com.diogonunes.jcolor.*;
import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.Transaction;

public class ConsolePrinterUtility {

	// methods for displaying console menu
	DollarsBankController controller = new DollarsBankController();
	DataGeneratorStubUtility dataService = new DataGeneratorStubUtility();

	public void displayInitialMenu() {
		
//		String ansi = Ansi.generateCode(Attribute.RED_TEXT());
//		System.err.println(Ansi.colorize("Red text?", ansi));
	
		System.err.println("+---------------------------\n| DOLLARSBANK Welcomes You! |\n+---------------------------\n");
		System.err.println("Please choose a menu option below:\n");
		System.err.println("1. Create New Account\n2. Login\n3. Exit");
	}
	
	public void displayAccountCreation() {
		System.err.println("+---------------------------\n| Enter Details For New Account |\n+---------------------------\n");
		System.err.println("Customer Name:\n");
		String name = controller.takeInput();
		
		System.err.println("Customer Address:\n");
		String address = controller.takeInput();
		
		System.err.println("Customer Phone Number:\n");
		String phoneNumber = controller.takeInput();
		
		System.err.println("User Id:\n");
		String userId = controller.takeInput();
		
		System.err.println("Password (8 characters including upper, lower, and special):\n");
		String password = controller.takeInput();
		
		System.err.println("Choose an Account type:\n");
		System.err.println("1. Checking\n2. Savings");
		String accountType = controller.takeInput();
		
		System.err.println("Initial Deposit Amount:\n");
		String initialDeposit = controller.takeInput();
		
		Account newAccount = dataService.createAccount(accountType, initialDeposit);
		dataService.addCustomer(new Customer(name, address, phoneNumber, userId, password, newAccount));
		displayCustomerLogin();
		// TODO: write to file?
	}
	
	public void displayCustomerLogin() {
		
		System.err.println("+---------------------------\n| Enter Login Details |\n+---------------------------\n");
		System.err.println("User Id:\n");
		String userId = controller.takeInput();
		
		System.err.println("Password:\n");
		String password = controller.takeInput();
		
		if(dataService.verifyLogin(userId, password)){
			dataService.logIn(userId);
			displayWelcomeCustomerMenu();
		}else {
			System.err.println("Invalid login");
			displayExitScreen();
			controller.closeConnection();
		}
	
	}
	
	public void displayExitScreen() {
		
		System.err.println("Thank you for using DOLLARSBANK. Have a good day.");
	}
	
	public void displayInvalidChoice() {
		System.err.println("Invalid menu option");
	}
	
	public void displayWelcomeCustomerMenu() {
		
		System.err.println("+---------------------------\n| WELCOME Customer! |\n+---------------------------\n");
		System.err.println("How can we help you today?\n");
		System.err.println("1. Deposit Amount\n2. Withdraw Amount\n3. Funds Transfer\n"
				+ "4. View 5 Recent Transactions\n5. Add Another Account\n6. Display Customer Information\n7. Logout");
		
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
			displayAddAnAccount();
			determineNextInteraction();
			break;
		case "6":
			displayCustomerInformation();
			determineNextInteraction();
			break;
		case "7":
			dataService.logOut();
			displayExitScreen();
			controller.closeConnection();
		}
		
	}
	
	public void displayDepositMenu() {
		
		System.err.println("Enter account id for deposit:\n");
		displayAccounts();
		
		String accountId = controller.takeInput();
		System.err.println("Enter deposit amount:\n");
		String depositAmt = controller.takeInput();
		
		Transaction deposit = dataService.makeDeposit(accountId, depositAmt);
		displayTransactionSummary(deposit);
	}
	
	public void displayWithdrawMenu() {
		
		System.err.println("Enter account id for withdraw:\n");
		displayAccounts();
		
		String accountId = controller.takeInput();
		System.err.println("Enter withdraw amount:\n");
		String withdrawAmt = controller.takeInput();
		
		Transaction withdrawl = dataService.makeWithdrawl(accountId, withdrawAmt);
		displayTransactionSummary(withdrawl);
	}
	
	public void displayTransferMenu() {

		System.err.println("Enter account ids for transfer:\n");
		displayAccounts();

		System.err.println("Enter transfer from id: ");
		String accountIdFrom = controller.takeInput();

		System.err.println("Enter transfer to id: ");
		String accountIdTo = controller.takeInput();

		System.err.println("Enter transfer amount:\n");
		String transferAmt = controller.takeInput();

		Transaction transfer = dataService.makeTransfer(accountIdFrom, accountIdTo, transferAmt);
		displayTransactionSummary(transfer);

	}
	
	public void displayLastFiveTransactions() {

		System.err.println("Enter account id for transaction history:\n");
		displayAccounts();

		String accountId = controller.takeInput();
		List<Transaction> lastFiveTransactions = dataService.fetchFiveLastTransactions(accountId);

		for (Transaction t : lastFiveTransactions) {
			System.err.println(t.toString() + "\n");
		}
	}
	
	public void displayAddAnAccount() {
		
		System.err.println("Choose an Account type:\n");
		System.err.println("1. Checking\n2. Savings");
		String accountType = controller.takeInput();
		
		System.err.println("Initial Deposit Amount:\n");
		String initialDeposit = controller.takeInput();
		
		Account newAccount = dataService.createAccount(accountType, initialDeposit);
		dataService.getCustomerAccounts().put(newAccount.getId(), newAccount);
		
	}
	
	public void displayCustomerInformation() {
		
		Customer loggedIn = dataService.getLoggedIn();
		System.err.println("Customer Details:\n\t" + loggedIn.toString());
		System.err.print("Account Details:\n\t");
		displayAccounts();
	}
	
	
	
	public void displayAccounts() {
		
		HashMap<Long, Account> accounts = dataService.getCustomerAccounts();
		accounts.forEach((key, value) -> System.err.println(key + ", " + value.getClass().getSimpleName()));
	}
	
	public void displayTransactionSummary(Transaction transaction) {
		
		System.err.println("Transaction Summary: " + transaction.toString());
	}
	
	public void determineNextInteraction() {
		
		System.err.println("What would you like to do next?\n");
		System.err.println("1. Go Back to Customer Menu\n2. Exit");
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
