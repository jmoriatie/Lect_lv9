package player;

import java.util.ArrayList;

import shop.Item;

public class Player {
	// 플레이어 이름(식별), 돈 (player) 포함:  Inventory-Item
	private String playerName; // log기능으로 진행
	private int money;
	private ArrayList<Item> inventory;
	
	public Player(String playerName) {
		this.playerName = playerName;
		this.money = 10000;
		this.inventory = new ArrayList<Item>();
	}

	public String getPlayerName() {
		return this.playerName;
	}
	
	public int getMoney() {
		return this.money;
	}

	public ArrayList<Item> getInventory() {
		return this.inventory;
	}
	
	public Item getInventoryOne(int index) {
		return this.inventory.get(index);
	}
	
	// 아이템 받아 추가
	public void setInventory(Item item) {
		this.inventory.add(item);
	}
	
	// 돈 더하기 빼기
	public void plusMoney(int howMuch) {
		this.money += howMuch;
	}
	public void substractMoney(int howMuch) {
		this.money -= howMuch;
	}

//	public void setMoney(int money) {
//	this.money = money;
//}

}
