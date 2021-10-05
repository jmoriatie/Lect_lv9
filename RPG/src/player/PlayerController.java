package player;

import main.Main;
import shop.Item;

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
			if(selUnit >= 0 && selUnit < player.inventory.size()) {
				System.out.print("착용할 아이템 선택: ");
				int selItem = selectInt( Main.sc.next() ); 				
			}
			
		} else System.out.println("인벤토리가 비었습니다");
		
		return check;
	}
	// 아이템 판매
	public void sellItem() {
		// 아이템 가격만큼 더해줘
	}
	
	// 조회 메서드
	
	// 프린트하는 메서드 능력 등등등 프린트
//	public void printInventory() {
//		for(Item item : inventory) {
//			System.out.print(item.getName() + " "); 
//		}
//	}
}
