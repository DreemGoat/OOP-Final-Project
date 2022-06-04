package source;

public class Stock { //Stock class file
	private String name;
	private int quantity;
	private int price;
	private int ID;
	public Stock(String name, int quantity, int price, int iD) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	
	@Override
	public String toString() {
		return "Stock [name=" + name + ", quantity=" + quantity + ", price=" + price + ", ID=" + ID + "]";
	}
	
}


