package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DBInstance;
import information.Account;

public class AccountDao {

	static DB db = new DB();

	//add new user account
	public static void addUserAccount(Account user) {
		String addUser = "Insert into user_account(name,acc_no,city,balance,pass)" + "values('" + user.getUserName()
				+ "','" + user.getAccountNo() + "','" + user.getCity() + "'," + user.getBalance() + ",'"
				+ user.getPassword() + "')";
		System.out.println(addUser);

		System.out.println(db.executeSQL(addUser));
		System.out.println("\nAdded user to DB successfully");
	}

	public static Account getuserDetails() {

		return null;
	}

	public static Account getUserObject(String accNo) {
		Account user = new Account();
		String accObjQuery = "select * from user_account where acc_no = " + accNo;
		ResultSet set = db.executeRetrieveQuery(accObjQuery);
		try {
			while (set.next()) {
				user.setUserName(set.getString("name"));
				user.setAccountNo(set.getString("acc_no"));
				user.setBalance(Double.parseDouble(set.getString("balance")));
				user.setCity(set.getString("city"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	public static void updateUserBalance(double d, String accNo) {
		String updateUserQuery = "update user_account set balance = " + d + " where acc_no = " + accNo;

		DBInstance.getInstance().executeSQL(updateUserQuery);
		System.out.println("\nbalance updated...");
	}

	public static int getUID(String fetchUIDQuery) {
		ResultSet set = DBInstance.getInstance().executeRetrieveQuery(fetchUIDQuery);
		int uid = -1;
		try {
			while (set.next()) {
				uid = Integer.parseInt(set.getString("uid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uid;
	}

}
