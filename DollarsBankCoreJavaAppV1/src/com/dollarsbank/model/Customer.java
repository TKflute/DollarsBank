package com.dollarsbank.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer {

	private String name;
	private String address;
	private String phoneNumber;
	private String userId;
	private String password;

	private HashMap<Long, Account> accounts;

	public Customer(String name, String address, String phoneNumber, String userId,
			String password, Account account) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.password = password;
		accounts = new HashMap<Long, Account>();
		accounts.put(account.getId(), account);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HashMap<Long, Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(HashMap<Long, Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", userId=" + userId + ", password=" + password + ", accounts=" + accounts + "]";
	}
	
	
}
