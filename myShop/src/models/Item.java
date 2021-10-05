package models;

public class Item {
	private String category;
	private String itemName;
	private int price;

	public Item(String itemName, int price, String category) {
		this.category = category;
		this.itemName = itemName;
		this.price = price;
	}


	public String getCategory() {
		return category;
	}

	public String getItemName() {
		return itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
}
