package player;

import java.util.ArrayList;

import main.Main;
import shop.Item;
import shop.ShopController;
import unit.GuildController;
import unit.Unit;

public class PlayerController {

	public static PlayerController instance = new PlayerController();

	public Player p1 = new Player("tester"); // 플레이어'들'이 있을 수 있지만, 여기선 1명(배열X)
	String logPlayer; // 로그정보

	public PlayerController() {
		this.logPlayer = p1.getPlayerName(); // 플레이어 한명 가정

		// 디버깅용 디폴트 아이템 3개 셋팅
//		this.setInventory( ShopController.instance.getFreeItem() );
//		this.setInventory( ShopController.instance.getFreeItem() );
//		this.setInventory( ShopController.instance.getFreeItem() );
	}
	public void InventoryMenu() {
		while(true) {
			System.out.println("========== 인벤토리 메뉴 ==========");
			System.out.println("플레이어가 가진 돈: " + this.getPlayer().getMoney() + "원");
			System.out.print("[1. 아이템 조회][2. 아이템 착용][3. 아이템 해제]\n[4. 아이템 판매][0. 뒤로가기] : ");	
			int sel = selectInt(Main.sc.next());
			if(sel == 1) {
				printAllInventory(p1);
			}
			else if(sel == 2) {
				giveItem(p1);
			}
			else if(sel == 3) {
				removeItem(p1);
			}
			else if(sel == 4) {
				sellItem(p1);
			}
			else if(sel == 0) {
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
		if(!player.getInventory().isEmpty()) {
			System.out.println("===== 아이템 착용 =====");
			System.out.print("[1) 길드][2) 파티] : ");
			int sel = selectInt(Main.sc.next());
			if(sel == 1) {
				if(GuildController.instance.sizeOfGuild() != 0) {
					GuildController.instance.printAllGuild(); // 길드원 정보 띄우기
					System.out.print("착용자 번호 선택: ");
					int selUnit = selectInt(Main.sc.next());
					if (selUnit >= 0 && selUnit < GuildController.instance.sizeOfGuild()) { // 유닛예외
						printItems(p1);
						Unit unit = GuildController.instance.getGuildUnit(selUnit);
						System.out.printf("\'[%s]\'착용할 아이템 선택: ",unit.getName());
						int selItem = selectInt(Main.sc.next()) -1;
						if (selItem >= 0 && selItem < player.sizeOfInventory()) { // 아이템 예외
							// 아이템 착용 부분
							Item item = player.getInventoryOne(selItem);
							if(item.getKind() == Item.WEAPON) {
								if(unit.getWeapon() != null) { // 이미 장비를 착용하고 있다면
									System.out.printf("[착용 중인\'%s\'은(는) 인벤토리로 이동합니다]\n", unit.getWeapon().getName());
									player.setInventory( unit.getWeapon() ); // 끼고 있던 장비 인벤토리로 이동
								}
								unit.setWeapon( item );
							}
							else if(item.getKind() == Item.ARMOR) {
								if(unit.getArmor() != null) { 
									System.out.printf("[착용 중인\'%s\'은(는) 인벤토리로 이동합니다]\n", unit.getArmor().getName());
									player.setInventory( unit.getArmor() ); 
								}
								unit.setArmor( item );
							}
							else if(item.getKind() == Item.ACCESSORY) {
								if(unit.getAccessory() != null) { 
									System.out.printf("[착용 중인\'%s\'은(는) 인벤토리로 이동합니다]\n", unit.getAccessory().getName());
									player.setInventory( unit.getAccessory() ); 
								}
								unit.setAccessory( item );
							}
							player.getInventory().remove( item ); // 인벤에선 삭제
							System.out.printf("[[%s]이(가) \'%s\'를 착용했습니다]\n", unit.getName(), item.getName());
						} else System.out.println("[아이템 번호를 확인하세요]");
					} else System.out.println("[길드원 인덱스를 확인하세요]");
				} else System.out.println("[길드원이 없습니다]");
			}
			else if(sel == 2) {
				if(GuildController.instance.sizeOfParty() != 0) {
					GuildController.instance.printAllParty();
					System.out.print("착용자 번호 선택: ");
					int selUnit = selectInt(Main.sc.next());
					if (selUnit >= 0 && selUnit < GuildController.instance.sizeOfParty()) { // 유닛예외
						printItems(p1);
						Unit unit = GuildController.instance.getPartydUnit(selUnit);
						System.out.printf("\'[%s]\'착용할 아이템 선택: ",unit.getName());
						int selItem = selectInt(Main.sc.next()) -1;
						if (selItem >= 0 && selItem < player.sizeOfInventory()) { 
							Item item = player.getInventoryOne(selItem);
							if(item.getKind() == Item.WEAPON) unit.setWeapon( item );
							else if(item.getKind() == Item.ARMOR) unit.setArmor( item );
							else if(item.getKind() == Item.ACCESSORY) unit.setAccessory( item );
							player.getInventory().remove( item ); // 인벤에선 삭제
							System.out.printf("[%s]이(가) \'%s\'를 착용했습니다\n", unit.getName(), item.getName());
						} else System.out.println("[아이템 번호를 확인하세요]");
					} else System.out.println("[파티원 인덱스를 확인하세요]");	
				} else System.out.println("[파티원이 없습니다]");
			}
		} else System.out.println("[인벤토리가 비었습니다]");
	}

	// 아이템 해제
	public void removeItem(Player player) {
		ArrayList<Unit> tmpUnitArr = new ArrayList<Unit> ();
		int size = GuildController.instance.sizeOfGuild() > GuildController.instance.sizeOfParty()? GuildController.instance.sizeOfGuild():GuildController.instance.sizeOfParty();
		System.out.println("===== 아이템 해제 =====");
		// 아이템 낀 유닛 저장
		for(int i=0; i<size; i++) { 
				Unit guildUnit =  GuildController.instance.getGuildUnit(i);// 각각의 유닛이 맞다면 => 길드유닛, 파티 유닛
				if(guildUnit != null) {
					if(guildUnit.getWeapon() != null || guildUnit.getArmor() != null || guildUnit.getAccessory() != null) {
						tmpUnitArr.add(guildUnit);
					}
				}
			
				Unit partyUnit = GuildController.instance.getPartydUnit(i);
				if(partyUnit != null) {
					if(partyUnit != null && partyUnit.getWeapon() != null || partyUnit.getArmor() != null || partyUnit.getAccessory() != null) {
						tmpUnitArr.add(partyUnit);
					}
				}
		}
		// 아래부터 별도의 tempArr
		if(!tmpUnitArr.isEmpty()) {
			System.out.println("[아이템을 착용중인 유닛]");
			int n = 1;
			for(Unit unit : tmpUnitArr) {
				System.out.printf("%d][%s]",n++,unit.getName());
				if(unit.getWeapon() != null) System.out.printf("[무기: %s(+%d)]",unit.getWeapon().getName(), unit.getWeapon().getPower());
				if(unit.getArmor() != null) System.out.printf("[방어구: %s(+%d)]",unit.getArmor().getName(), unit.getArmor().getPower()); 
				if(unit.getAccessory() != null) System.out.printf("[악세서리: %s(+%d)]",unit.getAccessory().getName(), unit.getAccessory().getPower());
				if(unit.isParty()) System.out.print(" #파티원\n");
				else System.out.print("\n");
			}
			System.out.print("아이템 착용을 해제할 유닛 선택: ");
			int selUnit = selectInt(Main.sc.next())-1;
			if(selUnit >= 0 && selUnit < tmpUnitArr.size()) {
				Unit thisU = tmpUnitArr.get(selUnit);
				// 출력
				if(thisU.getWeapon() != null) System.out.printf("[1. %s]", thisU.getWeapon().getName());
				else System.out.print("[1. x]");

				if(thisU.getArmor() != null) System.out.printf("[2. %s]", thisU.getArmor().getName());
				else System.out.print("[2. x]");
				
				if(thisU.getAccessory() != null) System.out.printf("[3. %s]\n", thisU.getAccessory().getName());
				else System.out.print("[3. x]\n");

				System.out.println("해제할 아이템 선택: ");
				int selItem = selectInt(Main.sc.next());
				if(selItem == 1) {
					if(thisU.getWeapon() != null) {
						System.out.printf("유닛 \'%s\'의 [%s]이(가)해제됐습니다]\n", thisU.getName(), thisU.getWeapon().getName());
						this.setInventory(thisU.getWeapon());
						thisU.setWeapon(null);
					} else System.out.println("[무기를 착용하고 있지 않습니다]");
				}
				else if(selItem == 2) {
					if(thisU.getArmor() != null) {
						System.out.printf("유닛 \'%s\'의 [%s]이(가)해제됐습니다]\n", thisU.getName(), thisU.getArmor().getName());
						this.setInventory(thisU.getArmor());
						thisU.setArmor(null);
					}else System.out.println("[방어구를 착용하고 있지 않습니다]");
				}
				else if(selItem == 3) {
					if(thisU.getAccessory() != null) {
						System.out.printf("유닛 \'%s\'의 [%s]이(가)해제됐습니다]\n", thisU.getName(), thisU.getAccessory().getName());
						this.setInventory(thisU.getAccessory());
						thisU.setAccessory(null);
					}else System.out.println("[악세서리를 착용하고 있지 않습니다]");
				}
			} else System.out.println("[유닛 번호를 확인하고 입력하세요]");
		} else System.out.println("[아이템을 착용한 유닛이 없습니다]");

		

	}

	// 아이템 판매
	public void sellItem(Player player) {
		// 아이템 가격만큼 더해줘
		System.out.println("===== 아이템 판매 =====");
		this.printAllInventory(player);
		
		System.out.print("판매할 아이템 인덱스 선택: ");
		int itemIdx = selectInt(Main.sc.next())-1;
		if(itemIdx >= 0 && itemIdx < this.getPlayer().sizeOfInventory()) {
			Item sellItem = this.getPlayer().getInventoryOne(itemIdx);
			if(this.getPlayer().removeItem(sellItem)) {
				System.out.printf("[\'[%s]\' 아이템이 판매되었습니다]\n", sellItem.getName());
				this.getPlayer().plusMoney(sellItem.getPrice());
			} else System.out.println("[판매하지 못했습니다]\n");
		}
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

	// 인벤토리 조회
	private void printAllInventory(Player player) {
		System.out.println("====== 전체 인벤토리 조회 ======");
		if(player.sizeOfInventory() != 0 ) {
			int n = 1;
			for(Item item : p1.getInventory()) {
				System.out.print(n++ + "] ");
				if(item.getKind() == Item.WEAPON) System.out.printf("[무기]");
				else if(item.getKind() == Item.ARMOR) System.out.printf("[갑옷]");
				else if(item.getKind() == Item.ACCESSORY) System.out.printf("[악세서리]");
				System.out.printf("[%s]", item.getName());
				if(item.getKind() == Item.WEAPON) System.out.printf("[공격력+%d]", item.getPower());
				else if(item.getKind() == Item.ARMOR) System.out.printf("[방어력+%d]",item.getPower());
				else if(item.getKind() == Item.ACCESSORY) System.out.printf("[체력+%d]",item.getPower());
				System.out.printf("[%d원]\n", item.getPrice());
			}
		}
		else System.out.println("[인벤토리가 비었습니다]");
	}
	
	// 인벤토리 조회 (가격 없는 ver)
	private void printItems(Player player) {
		System.out.println("====== 인벤토리 아이템 리스트 ======");
		if(player.sizeOfInventory() != 0 ) {
			int n = 1;
			for(Item item : p1.getInventory()) {
				System.out.print(n++ + "] ");
				if(item.getKind() == Item.WEAPON) System.out.printf("[무기]");
				else if(item.getKind() == Item.ARMOR) System.out.printf("[갑옷]");
				else if(item.getKind() == Item.ACCESSORY) System.out.printf("[악세서리]");
				System.out.printf("[%s]", item.getName());
				if(item.getKind() == Item.WEAPON) System.out.printf("[공격력+%d]", item.getPower());
				else if(item.getKind() == Item.ARMOR) System.out.printf("[방어력+%d]",item.getPower());
				else if(item.getKind() == Item.ACCESSORY) System.out.printf("[체력+%d]",item.getPower());
				System.out.print("\n");
			}
		}
		else System.out.println("[인벤토리가 비었습니다]");
	}
}
