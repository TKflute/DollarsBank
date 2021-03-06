package com.dollarsbank.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.CheckingAccount;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.SavingsAccount;
import com.dollarsbank.model.Transaction;

public class DataGeneratorStubUtility {

	// business logic for processing user transactions, account creation, and login
	private HashMap<String, Customer> validCustomers = new HashMap<String, Customer>();
	private Customer loggedIn = null;
	
	public Customer getLoggedIn() {
		return loggedIn;
	}

	public Account createAccount(String accountType, String initialDeposit) {
		Account newAccount = null;
		switch(accountType) {
		case "1":
			newAccount = new CheckingAccount(Double.parseDouble(initialDeposit));
			break;
		case "2":
			newAccount = new SavingsAccount(Double.parseDouble(initialDeposit));
			break;
		}
		
		return newAccount;
	}
	
	public void addCustomer(Customer newCustomer) {
		validCustomers.put(newCustomer.getUserId(), newCustomer);
	}
	
	public boolean verifyLogin(String userId, String password) {
		
		if(validCustomers.containsKey(userId)) {
			return validCustomers.get(userId).getPassword().equals(password);
		}
		return false;
	}
	
	public void logIn(String userId) {
		loggedIn = validCustomers.get(userId);
	}
	
	public HashMap<Long, Account> getCustomerAccounts(){
		return loggedIn.getAccounts();
	}
	
	public Account getAccount(String accountId) {
		
		return loggedIn.getAccounts().get(Long.parseLong(accountId));
	}
	
	public Transaction makeDeposit(String accountId, String depositAmt) {
		
		Double amount = 0.0;
		try{
			amount = Double.parseDouble(depositAmt);
			if(amount <= 0) {
				throw new IllegalArgumentException();
			}
		}catch(NumberFormatException e) {
			
			System.out.println("Illegal deposit amount");
		}catch(IllegalArgumentException e) {
			
			System.out.println("Deposit amount must be greater than 0");
		}
		
		Account account = getAccount(accountId);
		Double startingBalance = account.getBalance();
		
		Double endingBalance = startingBalance + amount;
		account.setBalance(endingBalance);
		
		Transaction deposit = new Transaction("Deposit", new Date(), startingBalance, endingBalance);
		account.getTransactionHistory().addFirst(deposit);
		
		return deposit;
	}

	public Transaction makeWithdrawl(String accountId, String withdrawAmt) {
		
		Double amount = 0.0;
		try{
			amount = Double.parseDouble(withdrawAmt);
			if(amount <= 0) {
				throw new IllegalArgumentException();
			}
		}catch(NumberFormatException e) {
			
			System.out.println("Illegal deposit amount");
		}catch(IllegalArgumentException e) {
			
			System.out.println("Deposit amount must be greater than 0");
		}
		
		Account account = getAccount(accountId);
		Double startingBalance = account.getBalance();
		if(amount > startingBalance) {
			throw new IllegalArgumentException("Withdraw amount cannot exceed account balance");
		}
		
		Double endingBalance = startingBalance - amount;
		account.setBalance(endingBalance);
		
		Transaction withdrawl = new Transaction("Withdrawl", new Date(), startingBalance, endingBalance);
		account.getTransactionHistory().addFirst(withdrawl);
		
		return withdrawl;
	}

	public Transaction makeTransfer(String accountIdFrom, String accountIdTo, String transferAmt) {
		
		Account accountFrom = getAccount(accountIdFrom);
		Account accountTo = getAccount(accountIdTo);
		
		Double amount = Double.parseDouble(transferAmt);
		if(amount <= 0 || amount > accountFrom.getBalance()) {
			throw new IllegalArgumentException("Illegal transfer amount. Must be positive value and less than starting account balance.");
		}
		
		accountFrom.setBalance(accountFrom.getBalance() - amount);
		
		Double startingBalance = accountTo.getBalance();
		Double endingBalance = startingBalance + amount;
		accountTo.setBalance(endingBalance);
		
		Transaction transfer = new Transaction("Transfer", new Date(), startingBalance, endingBalance);
		accountTo.getTransactionHistory().addFirst(transfer);
		
		return transfer;
	}
	
	public List<Transaction> fetchFiveLastTransactions(String accountId){
		
		Account account = getAccount(accountId);
		return account.getFiveLastTransactions();
	}
	
	public void logOut() {
		
		loggedIn = null;
	}
		
}
