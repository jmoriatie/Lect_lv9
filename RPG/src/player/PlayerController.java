package player;

import main.Main;
import shop.Item;
import unit.GuildController;

public class PlayerController {

	public static PlayerController instance = new PlayerController();
	
	private Player p1 = new Player("tester"); // 플레이어'들'이 있을 수 있지만, 여기선 1명(배열X)
	String logPlayer; // 로그정보

	public PlayerController() {
		this.logPlayer = p1.getPlayerName(); // 플레이어 한명 가정
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
	
	// 플레이어 돌려주기 - 인벤토리, 돈 컨트롤
	public Player getPlayer() {
		return this.p1;
	}
	
	// 아이템 착용(길드원 대상)
	public boolean giveItem(Player player) {
		boolean check = false;
		if(!player.getInventory().isEmpty()) {
			System.out.print("착용자 번호 선택: ");
			int selUnit = selectInt( Main.sc.next() ); 
			// 길드원 정보 띄우기
			if(selUnit >= 0 && selUnit < GuildController.instance.sizeOfGuild()) {
				System.out.print("착용할 아이템 선택: ");
				int selItem = selectInt( Main.sc.next() ); 
				if(selItem >= 0 && selItem <player.sizeOfInventory()) {
					
				} else System.out.println("[아이템 번호를 확인하세요]");
			} else System.out.println("[길드 인덱스를 확인하세요]");
			
		} else System.out.println("[인벤토리가 비었습니다]");
		
		return check;
	}
	
	// 인벤토리에 아이템 추가 - 구입한 아이템 저장
	public void setInventory(Item item) {
		p1.getInventory().add(item);
	}
	// 인벤토리 아이템 삭제(아이템)
	public void removeInventory(String itemName) {
		for(Item i : p1.getInventory()) {
			if(i.getName().equals(itemName)) {
				p1.getInventory().remove(i);
				break;
			}
		}
	}
	// 인벤토리 아이템 삭제(인덱스)
	public void removeInventory(int index) {
		p1.getInventory().remove(index);
	}
	
	// 아이템 판매
	public void sellItem() {
		// 아이템 가격만큼 더해줘
	}
	
	// 인벤토리 아이템 얻어오기
	public Item getInventoryItem(String itemName) {
		Item item = null;
		
		for(Item i : p1.getInventory()) {
			if(i.getName().equals(itemName)) {
				item = i;
			}
		}
		return item;
	}

}
