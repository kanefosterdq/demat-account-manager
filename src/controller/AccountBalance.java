package controller;

import dataaccess.AccountDao;

public class AccountBalance {
	final static int MAX_WITHDRAW_TRIES = 3;

	public static double accountBalanceValidator() {
	
		System.out.println("\nDeposit amount");
		double balance = ScannerCtrl.getScannerInstance().nextDouble();
		
		
		if (balance < 1000) {
			System.err.println("\nMinimum deposit Rs.1000");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("\\nError occurred!");
			}
			accountBalanceValidator();
		} else {
			return balance;
		}

		return balance;
	}

	public static void depositOperation(double amount, String accNo) {
		
		double prevBalance = AccountInstance.getUserInstance().getBalance();
		AccountInstance.getUserInstance().setBalance(prevBalance + amount);

		// add updated balance to DB
		AccountDao.updateUserBalance(AccountInstance.getUserInstance().getBalance(), accNo);
		System.out.println(
				"\nUpdated account balance : Rs." + AccountInstance.getUserInstance().getBalance());

	}

	public static void depositMoney(String accNo) {

		System.out.println("\nDeposit amount");
		double toBeDeposited = ScannerCtrl.getScannerInstance().nextDouble();
		depositOperation(toBeDeposited, accNo);
		return;
	}

	
	public static void withdrawMoney(String accNo, double debitAmount) {

		double balance = AccountInstance.getUserInstance().getBalance();
		AccountInstance.getUserInstance().setBalance(balance - debitAmount);
		AccountDao.updateUserBalance(AccountInstance.getUserInstance().getBalance(), accNo);
		System.out.println("\nAmount  debited...");
		System.out.println("\nUpdated balance : " + AccountInstance.getUserInstance().getBalance());
	}

	// for withdrawal operation
	public static void getDebitDetails(String accNo) {

		double balance = AccountInstance.getUserInstance().getBalance();
		System.out.print("\nAccount balance : " + balance+"Rs\n");
		int tries = 0;
		while (tries < MAX_WITHDRAW_TRIES) {
			System.out.print("\nEnter amount : ");
			double debitAmount = ScannerCtrl.getScannerInstance().nextDouble();
			if (balance < debitAmount) {
				System.err.println("\nInsufficient balance");
				tries++;
			} else {
				withdrawMoney(accNo, debitAmount);
				break;
			}
		}
		if (tries == MAX_WITHDRAW_TRIES)
			System.err.println("\nMaximum tries are exhausted");
	}

}
