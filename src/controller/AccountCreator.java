package controller;

import dataaccess.AccountDao;
import information.Account;

public class AccountCreator {

	static Account user;
	
	//to create a user account in DB
	public static void createUserAccountInstance(String name, String accNo, String city, double balance, String password) {
		if(user==null) {
			user = new Account(name,accNo,city,balance,password);	
			AccountDao.addUserAccount(user);
			System.out.println("\nAccount  created.");
		}	
		
	}
}
