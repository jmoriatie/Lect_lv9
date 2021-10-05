package main;

import java.util.Scanner;

import player.PlayerController;
import shop.ShopController;
import unit.GuildController;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	
	PlayerController pc = PlayerController.instance;
	GuildController gc = GuildController.instance;
	ShopController shop = ShopController.instance;
	
	public static void mainGame() {
		while(true) {
			
		}
	}
	
	public static void main(String[] args) {
		mainGame();
	}
}
