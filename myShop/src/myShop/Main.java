package myShop;

import controller.Shop;

public class Main {
	public static void main(String[] args) {
		Shop shop = Shop.instance;
		shop.mainMenu();
	}
}
