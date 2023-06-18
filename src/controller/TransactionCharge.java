package controller;

public class TransactionCharge {
	static double transactionChargePercent = 0.5; 

	//transaction charge
	public static double calcTransactionCharge(int total) {
		return total*(transactionChargePercent/100);		
	}
}
