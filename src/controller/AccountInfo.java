package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import information.Account;

public class AccountInfo {

	//get user details
	public static void getUserDetails() {
		System.out.println("\nName  ");
		String name = ScannerCtrl.getScannerInstance().next();
		ScannerCtrl.getScannerInstance().nextLine();
		System.out.println("\nCity  ");
		String city = ScannerCtrl.getScannerInstance().nextLine();
		String accNo = AccountNumber.generateAccountNumber();
		double balance = AccountBalance.accountBalanceValidator();
		String password = AccountPassword.passwordValidator();
		AccountCreator.createUserAccountInstance(name, accNo, city, balance, password);
	}

	//show user account details 
	public static void showUserAccountDetails(String accNo) {
		Account user = AccountInstance.getUserInstance();
		System.out.println("\nName            : " + user.getUserName());
		System.out.println("\nCity            : " + user.getCity());
		System.out.println("\nAccount Number  : " + user.getAccountNo());
		System.out.println("\nAccount balance : " + "Rs." + user.getBalance());
	}
	
	//gets user ID 
	public static int getUID(String accNo) {
		String UID = "select uid from user_account where acc_no = '" + accNo + "'";
		ResultSet set = DBInstance.getInstance().executeRetrieveQuery(UID);
		int uid = -1;
		try {
			while (set.next()) {
				uid = Integer.parseInt(set.getString("uid"));
			}
		} catch (SQLException e) {
			System.err.println("\\nError occurred!");
			e.printStackTrace();
		}
		return uid;

	}

	//printing shares
	public static void printMyStocks(ResultSet set) {
		System.out.println("\nShareID   Qty");
		try {
			while (set.next()) {
				System.out.println();
				System.out.println(set.getString("shareId") + "	  " + set.getString("qty"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.err.println("\nError occurred!");
			e.printStackTrace();
		}
		return;
	}

}
