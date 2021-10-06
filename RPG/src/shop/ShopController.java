package shop;

import java.util.ArrayList;

import main.Main;
import player.PlayerController;

public class ShopController {
	public static ShopController instance =  new ShopController();


	private ArrayList<Item> shop = new ArrayList<Item>();

	private ShopController() {
		Item temp = new Item();
		temp.kind = Item.WEAPON;
		temp.name = "나무검";
		temp.power = 3;
		temp.price = 1000;
		shop.add(temp);

		temp = new Item();
		temp.kind = Item.WEAPON;
		temp.name = "철검";
		temp.power = 5;
		temp.price = 2000;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.WEAPON;
		temp.name = "레이피어";
		temp.power = 7;
		temp.price = 2500;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.ARMOR;
		temp.name = "티셔츠";
		temp.power = 1;
		temp.price = 300;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.ARMOR;
		temp.name = "가죽갑옷";
		temp.power = 4;
		temp.price = 800;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.ARMOR;
		temp.name = "강철갑옷";
		temp.power = 7;
		temp.price = 1500;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.RING;
		temp.name = "은반지";
		temp.power = 7;
		temp.price = 3000;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.RING;
		temp.name = "금반지";
		temp.power = 17;
		temp.price = 6000;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.RING;
		temp.name = "다이아반지";
		temp.power = 35;
		temp.price = 20000;
		itemList.add(temp);
	}
	
	public void shopMenu() {
//		- 아이템 구입 (무기, 갑옷, 반지)
//		- 아이템 생성
		while(true) {
			System.out.println("========================= 유닛 관리 =========================");
			System.out.println("플레이어가 가진 돈: " + PlayerController.instance.getPlayer().getMoney() + "원");
			System.out.print("[1.길드원 조회][2.길드원 영입][3.길드원 추방]\n[4.파티원조회][5.파티원 추가][6.파티원 삭제][7.뒤로가기] : ");
			int sel = selectInt( Main.sc.next() ); 
			if(sel == 1) {
				printAllGuild();
			}
			else if(sel == 2) {
				addGuildUnit();
			}
			else if(sel == 3) {
				deleteGuildUnit();
			}
			else if(sel == 4) {
				printAllParty();
			}
			else if(sel == 5) {
				addPartyUnit();
			}
			else if(sel == 6) {
				deletePartyUnit();
			}
			else if(sel == 7) {
				break;
			}
		}
	}
}
