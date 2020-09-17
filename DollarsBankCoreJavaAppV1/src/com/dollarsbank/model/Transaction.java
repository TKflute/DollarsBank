package com.dollarsbank.model;

import java.util.Date;

public class Transaction {

	//private long id;
	private String transactionType; // deposit, withdraw, or transfer
	private Date transactionDate;
	private double startingBalance; 
	private double endingBalance;

	public Transaction(String transactionType, Date transactionDate, double startingBalance, double endingBalance) {
		super();
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.startingBalance = startingBalance;
		this.endingBalance = endingBalance;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(double startingBalance) {
		this.startingBalance = startingBalance;
	}

	public double getEndingBalance() {
		return endingBalance;
	}

	public void setEndingBalance(double endingBalance) {
		this.endingBalance = endingBalance;
	}

	@Override // Update this to print Transaction history how want to display
	public String toString() {
		return "Transaction [transactionType=" + transactionType + ", transactionDate=" + transactionDate
				+ ", startingBalance=" + startingBalance + ", endingBalance=" + endingBalance + "]";
	}
	
}
