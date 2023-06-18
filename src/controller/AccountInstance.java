package controller;
import dataaccess.AccountDao;
import information.Account;

public class AccountInstance {
	
	static Account user ;

	public static void setUserInstance(String accNo) {
		
		if(user == null) {
			user = AccountDao.getUserObject(accNo);
		}

	}
	
	//Singleton design
	public static Account getUserInstance() {
			return user;
	}
}
