package information;

public class Transaction {
	
	public int transactionID;
	public int type;
	public int qty;
	public double total;

	public Transaction() {

	}

	public Transaction(int transactionID, int type, int qty, double total) {
		super();
		this.transactionID = transactionID;
		this.type = type;
		this.qty = qty;
		this.total = total;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}


}
