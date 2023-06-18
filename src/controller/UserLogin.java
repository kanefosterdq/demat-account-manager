package controller;

import dataaccess.ShareDao;
import information.Account;

public class UserLogin {
	static Account user;

	static final int LOGIN_TRIES = 3;

	public static void showMenu(String accNo) {

		while (true) {
			System.out.println("\nSelect the operation \n");
			System.out.println("0. Quit\n");
			System.out.println("1. Display Demat Account Details\n");
			System.out.println("2. Deposit Money\n");
			System.out.println("3. Withdraw Money\n");
			System.out.println("4. Buy Share\n");
			System.out.println("5. Sell Share\n");
			System.out.println("6. View Transaction Report\n");

			
			String userChoice = ScannerCtrl.getScannerInstance().next();
			switch (userChoice) {
			case "0":
				System.out.println("--------------Thank you ------------------");
				System.exit(0);
				break;
			case "1":
				AccountInfo.showUserAccountDetails(accNo);
				break;
			case "2":
				AccountBalance.depositMoney(accNo);
				break;
			case "3":
				AccountBalance.getDebitDetails(accNo);
				break;
			case "4":
				TransactionInfo.buyStocks(accNo);
				break;
			case "5":
				ShareDao.fetchStockInfo();
				TransactionInfo.sellStocks(accNo);
				break;
			case "6":
				TransactionInfo.viewTransactions(accNo);
				break;

			default:
				break;
			}
		}

	}

	public static void getCredentials() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Error found");
			e.printStackTrace();
		}

		ScannerCtrl.getScannerInstance().nextLine();
		System.out.println("Account Number  \n");
		String accNo = ScannerCtrl.getScannerInstance().nextLine();
		System.out.println("\nAccount Password  \n");
		String password = ScannerCtrl.getScannerInstance().nextLine().trim();

		validateCredentials(accNo, password);
		return;
	}

	private static void validateCredentials(String accNo, String password) {

		int login_tries = 0;
		while (login_tries < LOGIN_TRIES) {
			String accNoFetched = ShareDao.getAccountNo(accNo);
			String passwordFetched = ShareDao.getPassword(accNo);

			if (accNo.equals(accNoFetched) && password.equals(passwordFetched)) {
				System.out.println("Welcome !!!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Error occured!");
					System.out.println("Please try again after some time...");
				}

				break;

			} else {
				System.err.println("\nError occurred\n");
				login_tries++;
				getCredentials();
			}
		}
		if (login_tries == LOGIN_TRIES)
			System.err.println("3 attempts exhausted!");

		AccountInstance.setUserInstance(accNo);
		showMenu(accNo);
	}

}
