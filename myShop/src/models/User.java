package models;

public class User {
	private String id;
	private int money;
	
	public User(String id, int money) {
		this.id = id;
		this.money = money;
	}

	public String getId() {
		return id;
	}
	
	public int getMoney() {
		return this.money;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
