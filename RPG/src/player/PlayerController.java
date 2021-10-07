package player;

import main.Main;
import shop.Item;
import unit.GuildController;
import unit.Unit;

public class PlayerController {

	public static PlayerController instance = new PlayerController();

	private Player p1 = new Player("tester"); // 플레이어'들'이 있을 수 있지만, 여기선 1명(배열X)
	String logPlayer; // 로그정보

	public PlayerController() {
		this.logPlayer = p1.getPlayerName(); // 플레이어 한명 가정
	}
//	- 아이템 착용(길드원 대상)
//	- 아이템 판매
	
	public void InventoryMenu() {
		while(true) {
			System.out.println("========== 인벤토리 메뉴 ==========");
			System.out.print("[1. 아이템 착용][2. 아이템 해제][3. 아이템 판매][4. 뒤로가기] : ");	
			int sel = selectInt(Main.sc.next());
			if(sel == 1) {
				giveItem(p1);
			}
			else if(sel == 2) {
				
			}
			else if(sel == 3) {
				
			}
			else if(sel == 4) {
				break;
			}
		}
	}

	private int selectInt(String input) {
		int select = -1;
		try {
			select = Integer.parseInt(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return select;
	}

	// 플레이어 돌려주기 - 인벤토리, 돈 컨트롤
	public Player getPlayer() {
		return this.p1;
	}

	// 아이템 착용
	public void giveItem(Player player) {
		if (!player.getInventory().isEmpty()) {
			System.out.println("===== 아이템 착용 =====");
			System.out.println("[1) 길드][2) 파티] : ");
			int sel = selectInt(Main.sc.next());
			if(sel == 1) {
				System.out.print("착용자 번호 선택: ");
				int selUnit = selectInt(Main.sc.next());
				GuildController.instance.printAllGuild(); // 길드원 정보 띄우기
				if (selUnit >= 0 && selUnit < GuildController.instance.sizeOfGuild()) { // 유닛예외
					Unit unit = GuildController.instance.getGuildUnit(selUnit);
					System.out.print("착용할 아이템 선택: ");
					int selItem = selectInt(Main.sc.next());
					if (selItem >= 0 && selItem < player.sizeOfInventory()) { // 아이템 예외
						// 아이템 착용 부분
						Item item = player.getInventoryOne(selItem);
						if(item.getKind() == Item.WEAPON) unit.setWeapon( item );
						else if(item.getKind() == Item.ARMOR) unit.setArmor( item );
						else if(item.getKind() == Item.ACCESSORY) unit.setAccessory( item );
						player.getInventory().remove( item ); // 인벤에선 삭제
						System.out.printf("[%s]이(가) \'%s\'를 착용했습니다\n", unit.getName(), item.getName());
					} else System.out.println("[아이템 번호를 확인하세요]");
				} else System.out.println("[길드원 인덱스를 확인하세요]");
			}
			else if(sel == 2) {
				System.out.print("착용자 번호 선택: ");
				int selUnit = selectInt(Main.sc.next());
				GuildController.instance.printAllParty();
				if (selUnit >= 0 && selUnit < GuildController.instance.sizeOfParty()) { // 유닛예외
					Unit unit = GuildController.instance.getPartydUnit(selUnit);
					System.out.print("착용할 아이템 선택: ");
					int selItem = selectInt(Main.sc.next());
					if (selItem >= 0 && selItem < player.sizeOfInventory()) { 
						Item item = player.getInventoryOne(selItem);
						if(item.getKind() == Item.WEAPON) unit.setWeapon( item );
						else if(item.getKind() == Item.ARMOR) unit.setArmor( item );
						else if(item.getKind() == Item.ACCESSORY) unit.setAccessory( item );
						player.getInventory().remove( item ); // 인벤에선 삭제
						System.out.printf("[%s]이(가) \'%s\'를 착용했습니다\n", unit.getName(), item.getName());
					} else System.out.println("[아이템 번호를 확인하세요]");
				} else System.out.println("[파티원 인덱스를 확인하세요]");
			}
			

		} else System.out.println("[인벤토리가 비었습니다]");
	}

	// 아이템 해제(길드원 대상)
	

	// 아이템 판매
	public void sellItem() {
		// 아이템 가격만큼 더해줘
	}
	
//	========== 기본 메서드 ===============
	// 인벤토리에 아이템 추가 - 구입한 아이템 저장
	public void setInventory(Item item) {
		p1.getInventory().add(item);
	}

	// 인벤토리 아이템 삭제(아이템)
	public void removeInventory(String itemName) {
		for (Item i : p1.getInventory()) {
			if (i.getName().equals(itemName)) {
				p1.getInventory().remove(i);
				break;
			}
		}
	}

	// 인벤토리 아이템 삭제(인덱스)
	public void removeInventory(int index) {
		p1.getInventory().remove(index);
	}

	// 인벤토리 아이템 얻어오기
	public Item getInventoryItem(String itemName) {
		Item item = null;

		for (Item i : p1.getInventory()) {
			if (i.getName().equals(itemName)) {
				item = i;
			}
		}
		return item;
	}

}
