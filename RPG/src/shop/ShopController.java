package shop;

import java.util.ArrayList;

import main.Main;
import player.PlayerController;

public class ShopController {
	public static ShopController instance =  new ShopController();

	private ArrayList<Item> itemList = new ArrayList<Item>();

	private ShopController() {
		Item temp = new Item();
		temp.kind = Item.WEAPON;
		temp.name = "나무검";
		temp.power = 3;
		temp.price = 1000;
		itemList.add(temp);

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
		temp.kind = Item.ACCESSORY;
		temp.name = "은반지";
		temp.power = 7;
		temp.price = 3000;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.ACCESSORY;
		temp.name = "금반지";
		temp.power = 17;
		temp.price = 6000;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.ACCESSORY;
		temp.name = "다이아반지";
		temp.power = 35;
		temp.price = 20000;
		itemList.add(temp);
	}
	
	private int selectInt(String input) {
		int select = -1;
		try {
			select = Integer.parseInt(input);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return select;
	}
	
	public void shopMenu() {
//		- 아이템 구입 (무기, 갑옷, 반지)
//		- 아이템 생성
		while(true) {
			System.out.println("========================= 상점 =========================");
			System.out.println("플레이어가 가진 돈: " + PlayerController.instance.getPlayer().getMoney() + "원");
			System.out.print("[1.아이템 목록][2. 아이템 조회][3.아이템 구입][4.아이템 생성][5.뒤로가기] : ");
			int sel = selectInt( Main.sc.next() ); 
			if(sel == 1) {
				printAllItem();
			}
			else if(sel == 2) {
				findItem();
			}
			else if(sel == 3) {
				buyItem();
			}
			else if(sel == 4) {
				createItem();
			}
			else if(sel == 4) {
				break;
			}

		}
	}

	// 아이템 출력
	private void printAllItem() {
		System.out.println("===== 아이템 목록 =====");
		if(!itemList.isEmpty()) {
			int n = 0;
			for(Item item : itemList) {
				System.out.print(n++ + "] ");
				if(item.getKind() == Item.WEAPON) System.out.printf("[종류: 무기]");
				else if(item.getKind() == Item.ARMOR) System.out.printf("[종류: 갑옷]");
				else if(item.getKind() == Item.ACCESSORY) System.out.printf("[종류: 악세서리]");
				System.out.printf("[이름: %s]", item.getName());
				if(item.getKind() == Item.WEAPON) System.out.printf("[능력: 공격력+%d]", item.getPower());
				else if(item.getKind() == Item.ARMOR) System.out.printf("[능력: 방어력+%d]",item.getPower());
				else if(item.getKind() == Item.ACCESSORY) System.out.printf("[능력: 체력+%d]",item.getPower());
				System.out.printf("[가격: %d원]\n", item.getPrice());
			}
		} else System.out.println("아이템이 없습니다");
	}

	// 아이템 찾기
	private void findItem() {
		System.out.println("===== 아이템 조회 =====");
		System.out.print("조회할 아이템명 입력: ");
		String input = Main.sc.next();
		Item item = null;
		for(Item i : itemList) {
			if(i.getName().equals(input)) {
				item = i;
			}
		}
		if(item != null) {
			if(item.getKind() == Item.WEAPON) System.out.printf("[무기]");
			else if(item.getKind() == Item.ARMOR) System.out.printf("[갑옷]");
			else if(item.getKind() == Item.ACCESSORY) System.out.printf("[악세서리]");
			System.out.printf("[%s]", item.getName());
			if(item.getKind() == Item.WEAPON) System.out.printf("[공격력+%d]", item.getPower());
			else if(item.getKind() == Item.ARMOR) System.out.printf("[방어력+%d]",item.getPower());
			else if(item.getKind() == Item.ACCESSORY) System.out.printf("[체력+%d]",item.getPower());
			System.out.printf("[%d원]\n", item.getPrice());
		}
		else System.out.println("없는 아이템 입니다");
	}
	
	private void buyItem() {
		printAllItem();
		System.out.print("구매를 원하는 아이템 번호 입력 : ");
		int sel = selectInt( Main.sc.next() ); 
		if(sel >= 0 && sel < itemList.size()) {
			// 인벤토리에 동일한 아이템 있을 때 구입 못하는거 구현 => 여기 말고 저기서 해야할 듯 PlyaerController
			PlayerController.instance.getPlayer().setInventory(itemList.get(sel)); // 인벤토리로 아이템 이동
			PlayerController.instance.getPlayer().substractMoney(itemList.get(sel).getPrice()); //  돈 빼기
			System.out.printf("[[%s] 구입 완료]\n", itemList.get(sel).getName());
		} else  System.out.println("[잘못된 아이템 인덱스]");
	}

	private void createItem() {
		// TODO Auto-generated method stub
		
	}
}
