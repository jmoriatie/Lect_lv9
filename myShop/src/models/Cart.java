package models;

public class Cart {
	private String userId;
	private Item item;

	public Cart() {
		
	}
	
	public Cart(String userId, Item item) {
		this.userId = userId;
		this.item = item;
	}

	public String getUserId() {
		return userId;
	}

	public Item getItem() {
		return item;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setItem(Item item) {
		this.item = item;
	}	
}
