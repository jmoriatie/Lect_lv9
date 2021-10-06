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
			System.out.println("[1. 유닛관리]");
			String input = sc.next();
			int select = -1;
			try {
				select = Integer.parseInt(input);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			if(select ==  1) {
				gc.unitMenu();
			}
			else if(select == 2) {
				
			}
			else if(select == 3) {
				
			}
			else if(select == 4) {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		mainGame();
	}
}
