package com.dollarsbank.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {

	private static long idCounter = 1234L;
	private long id;
	private double currentBalance;
	
	// using LinkedList so can easily add most recent transactions to top of list
	// (in a real account, would want to store whole history)
	private LinkedList<Transaction> transactionHistory; 
	
	public Account(double currentBalance) {
		super();
		this.id = idCounter++;
		this.currentBalance = currentBalance;
		transactionHistory = new LinkedList<Transaction>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBalance() {
		return currentBalance;
	}

	public void setBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public LinkedList<Transaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(LinkedList<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", currentBalance=" + currentBalance + ", transactionHistory=" + transactionHistory + "]";
	}
	
	// could maybe move this to DataGeneratorStubUtility
	public List<Transaction> getFiveLastTransactions(){
		
		// TODO: not sure if this will throw error if list has less than 5 elements
		List<Transaction> lastFiveTransactions = transactionHistory
											.stream()
								            .limit(5)
								            .collect(Collectors.toList());
		
		return lastFiveTransactions; 
	}
	
}
