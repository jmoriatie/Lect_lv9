package main;

import java.util.Scanner;

import player.PlayerController;
import shop.ShopController;
import unit.GuildController;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	
	private static PlayerController pc = PlayerController.instance;
	private static GuildController gc = GuildController.instance;
	private static ShopController shop = ShopController.instance;
	
	public static void mainGame() {
		while(true) {
			System.out.println("========== RPG GAME ==========");
			System.out.print("[1. 유닛관리][2. ][3. ][4. 종료]");
			String input = sc.next();
			int select = -1;
			try {
				select = Integer.parseInt(input);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			if(select ==  1) { // 유닛관리
				gc.unitMenu();
			}
			else if(select == 2) { // 상점
				
			}
			else if(select == 3) { // 인벤토리
				
			}
			else if(select == 4) { // 종료
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		mainGame();
	}
}
