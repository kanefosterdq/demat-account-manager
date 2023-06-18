package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataaccess.ShareDao;
import dataaccess.TransactionDao;

public class TransactionInfo {


	public static void buyStocks(String accNo) {

		int operationType = 1; 
		TransactionInfo.showStocks();

		System.out.println("\nEnter the share_Id ");
		int shareChoice = ScannerCtrl.getScannerInstance().nextInt();

		System.out.println("\nQty");
		int shareQty = ScannerCtrl.getScannerInstance().nextInt();

		double total = getTotal(shareChoice, shareQty);
		double totalToBePaid = ShareDao.getTaxAndStt(total) + total;

		if (totalToBePaid <= AccountInstance.getUserInstance().getBalance()) {
			
			System.out.println("\nTotal to be paid = " + totalToBePaid + " Rs");
			
			ShareDao.updateShareDeduct(shareChoice, shareQty);

			TransactionInfo.addTransaction(shareChoice, shareQty,
			AccountInstance.getUserInstance().getAccountNo(), operationType);
		
			AccountBalance.withdrawMoney(accNo, total);
			
		} else {
			System.err.println("Insufficient balance...");
		}
	}

	
	private static void addTransaction(int shareChoice, int shareQty, String accountNo, int operationType) {
		TransactionDao.updateTransaction(shareChoice, shareQty, accountNo, getTotal(shareChoice, shareQty),
				operationType);
	}

	private static void showStocks() {
		ShareDao.fetchStockInfo();
		return;
	}

	
	private static double getTotal(int shareChoice, int shareQty) {
		return ShareDao.getTotal(shareChoice, shareQty);
	}

	public static void sellStocks(String accNo) {
		// get UID 
		int uid = AccountInfo.getUID(accNo);
		String myStocks = "select shareId,qty from share_transaction where uid = " + uid;
		ResultSet stockSet = DBInstance.getInstance().executeRetrieveQuery(myStocks);
		
		// printing the stocks 
		AccountInfo.printMyStocks(stockSet);
		System.out.println("Enter Share ID ");
		int stockId = ScannerCtrl.getScannerInstance().nextInt();
		System.out.println("Enter Qty ");
		int qty = ScannerCtrl.getScannerInstance().nextInt();
		String price = "select price from share where shareId = " + stockId;
		ResultSet priceSet = DBInstance.getInstance().executeRetrieveQuery(price);
		double stockPrice = -1;
		try {
			while (priceSet.next()) {
				stockPrice = Double.parseDouble(priceSet.getString("price"));
			}
		} catch (SQLException e) {
			System.out.println("Error occured!");
			e.printStackTrace();
		}
		double total = stockPrice * qty;
		double taxTC = ShareDao.getTaxAndStt(total);
		
		AccountBalance.depositOperation(total - taxTC, accNo);
		ShareDao.updateShareAdd(stockId, qty);

		TransactionInfo.addTransaction(stockId, qty,
				AccountInstance.getUserInstance().getAccountNo(), 2);

	}

	public static void viewTransactions(String accNo) {

		int uid = AccountInfo.getUID(accNo);
		String fetchTrans = "select * from share_transaction where uid = " + uid;
		ResultSet transSet = DBInstance.getInstance().executeRetrieveQuery(fetchTrans);
		System.out.println("ShareId   Qty    Total   Type  Time");
		try {
			while (transSet.next()) {
				;
				System.out.print(transSet.getString("shareId") + "        ");
				System.out.print(transSet.getString("qty") + "      ");
				System.out.print(transSet.getString("total") + "    ");
				System.out.print(transSet.getString("transaction_type") + "    ");
				System.out.print(transSet.getString("transaction_time") + "    ");
				System.out.println();

			}
		} catch (SQLException e) {
			System.out.println("Error occurred!");
			e.printStackTrace();
		}

	}
}
