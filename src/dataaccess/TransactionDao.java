package dataaccess;

import controller.DBInstance;

public class TransactionDao {

	//updates the transaction table
	public static void updateTransaction(int shareChoice, int shareQty, String accountNo, double total,
			int operationType) {
		String fetchUID = "select uid from user_account where acc_no = " + accountNo;
		int uid = getUID(fetchUID);
		String updateQuery = null;
		if (operationType == 1) {
			updateQuery = "insert into share_transaction(shareId,qty,total,transaction_type,uid)" + "values("
					+ shareChoice + "," + shareQty + "," + total + ",'buy'," + uid + ")";
		}
		if (operationType == 2) {
			updateQuery = "insert into share_transaction(shareId,qty,total,transaction_type,uid)" + "values("
					+ shareChoice + "," + shareQty + "," + total + ",'sell'," + uid + ")";
		}
		int updateTransactionStatus = DBInstance.getInstance().executeSQL(updateQuery);
		if (updateTransactionStatus >= 1) {
			System.out.println("\nTransaction updated...");
		} else {
			System.err.println("Something is worng in transaction Update");
		}

	}

	public static int getUID(String fetchUIDQuery) {

		return AccountDao.getUID(fetchUIDQuery);
	}

}
