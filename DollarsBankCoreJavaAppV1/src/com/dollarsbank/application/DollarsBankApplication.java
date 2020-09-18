package com.dollarsbank.application;

import com.dollarsbank.controller.DollarsBankController;

public class DollarsBankApplication {

	public static void main(String[] args) {
		
		//Scanner input = new Scanner(System.in);
		DollarsBankController controller = new DollarsBankController();
		controller.mainMenuInteraction();
		//input.close();
	}

}
