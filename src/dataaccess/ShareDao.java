package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import controller.DBInstance;
import controller.Tax;
import controller.TransactionCharge;

public class ShareDao {

	// fetch stocks from share table
	public static void fetchStockInfo() {
		String fetchStockQuery = "select * from share";
		ResultSet shareInfo = null;
		try {
			shareInfo = DBInstance.getInstance().executeRetrieveQuery(fetchStockQuery);
		} catch (Exception e) {
			System.err.println("Something went wrong!");
			e.printStackTrace();
		}
		ShareDao.printShares(shareInfo);
	}

	// print the fetched share details
	private static void printShares(ResultSet shareInfo) {
		System.out.println("ShareID\t\t\tName\t\t\tSharePrice");
		try {
			while (shareInfo.next()) {
				System.out.println(shareInfo.getString("shareId") + "\t\t\t" + shareInfo.getString("share_name")
						+ "\t\t\t" + shareInfo.getString("price"));
			}
		} catch (SQLException e) {
			System.err.println("Something went wrong!");
			e.printStackTrace();
		}

	}

	// fetch account number
	public static String getAccountNo(String accNo) {
		String accNoQuery = "select acc_no from user_account where acc_no = '" + accNo + "'";
		ResultSet accNoSet = DBInstance.getInstance().executeRetrieveQuery(accNoQuery);
		String accNoFetched = null;
		try {
			while (accNoSet.next()) {
				accNoFetched = accNoSet.getString("acc_no");
			}
		} catch (SQLException e) {
			System.err.println("Something went wrong!");
			e.printStackTrace();
		}
		return accNoFetched;
	}

	// fetch password
	public static String getPassword(String accNo) {
		String passwordQuery = "select pass from user_account where acc_no = '" + accNo + "'";
		ResultSet passwordSet = DBInstance.getInstance().executeRetrieveQuery(passwordQuery);
		String passwordFetched = null;
		try {
			while (passwordSet.next()) {
				passwordFetched = passwordSet.getString("pass");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passwordFetched;
	}

	public static double getTotal(int shareChoice, int shareQty) {
		String fetchTotal = "select (price*" + shareQty + ")as total from share where shareId = " + shareChoice;
		int total = 0;
		ResultSet totalSet = DBInstance.getInstance().executeRetrieveQuery(fetchTotal);
		try {
			while (totalSet.next()) {
				total = Integer.parseInt(totalSet.getString("total"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error occurred in buyOperation in ShareDaoImpl");
		}

		// totalWithTransactionCharge
		return total;
	}

	// to calculate the sum of transaction charge and security transfer tax
	public static double getTaxAndStt(double total) {
		double TC = TransactionCharge.calcTransactionCharge((int) total);
		double stt = Tax.calcSTT(total);
		return TC + stt;
	}

	public static double sellOperation(int shareChoice, int shareQty) {
		return 0;
	}

	// update share table after sell operation
	public static void updateShareAdd(int shareChoice, int shareQty) {
		int currQty = 0;
		String getCurrQtyQuery = "select qty from share where shareId = " + shareChoice;
		ResultSet set = DBInstance.getInstance().executeRetrieveQuery(getCurrQtyQuery);
		try {
			while (set.next()) {
				currQty = Integer.parseInt(set.getString("qty"));
			}
		} catch (SQLException e) {
			System.out.println("Error occurred in UpdateShareTable() - ShareDaoImpl");
			e.printStackTrace();
		}
		String updateQuery = "update share set qty = " + (currQty + shareQty);
		int updateStatus = DBInstance.getInstance().executeSQL(updateQuery);
		if (updateStatus >= 1) {
		} else {
			System.out.println("\nupdate status : " + updateStatus);
		}
	}

	// update share table after buy operation
	public static void updateShareDeduct(int shareChoice, int shareQty) {
		int currQty = 0;
		String getCurrQtyQuery = "select qty from share where shareId = " + shareChoice;
		ResultSet set = DBInstance.getInstance().executeRetrieveQuery(getCurrQtyQuery);
		try {
			while (set.next()) {
				currQty = Integer.parseInt(set.getString("qty"));
			}
		} catch (SQLException e) {
			System.out.println("Error occurred in UpdateShareTable() - ShareDaoImpl");
			e.printStackTrace();
		}

		String updateQuery = "update share set qty = " + (currQty - shareQty) + " where shareId = " + shareChoice;

		int updateStatus = DBInstance.getInstance().executeSQL(updateQuery);
		if (updateStatus >= 1) {
			System.out.println("\nShare table updated");
		} else {
			System.out.println("\nupdate status : " + updateStatus);
		}
	}
}
