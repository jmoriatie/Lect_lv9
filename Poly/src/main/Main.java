package main;

public class Main {
	public static void main(String[] args) {

		GameManager g = GameManager.getInstance();
		
		while(true) {
			if(!g.stageChange()) break;
		}
		System.out.println("[시스템 종료]");
	}
}
