package information;

public class Account {
		
	public String userName;
	public String accountNo;
	public String password;

	public String city;

	public double balance ;

	public Account() {

	}

	public Account(String name, String accNo, String city, double balance2, String password) {
		this.userName = name;
		this.balance = balance2;
		this.city = city;
		this.password = password;
		this.accountNo = accNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
