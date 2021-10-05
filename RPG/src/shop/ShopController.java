package shop;

import java.util.ArrayList;

public class ShopController {
	public static ShopController instance =  new ShopController();

	private ArrayList<Item> shop = new ArrayList<Item>();

	private ShopController() {
		
	}
}
