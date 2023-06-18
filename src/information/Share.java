package information;

public class Share {
	public int shareId;
	public String companyName;
	public int price;
	public int quantity;
	
	public Share() {
	
	}
	
	
	public Share(int shareId, String companyName, int price, int quantity) {
		super();
		this.shareId = shareId;
		this.companyName = companyName;
		this.price = price;
		this.quantity = quantity;
	}


	public int getShareId() {
		return shareId;
	}
	public void setShareId(int shareId) {
		this.shareId = shareId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
