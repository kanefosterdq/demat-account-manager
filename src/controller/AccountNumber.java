package controller;

import java.util.Random;

public class AccountNumber {
	public static String generateAccountNumber() {
		Random rand = new Random();
		String accNo = "1000"+rand.nextInt(10000);
		return accNo;
	}
}
