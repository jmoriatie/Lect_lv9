package main;

import java.util.Scanner;

import hero.Hero;
import unit.Unit;
import zombie.Boss;
import zombie.Zombie;

// 컨트롤 => 플레이어 이동, 선택, 종료
// 플레이어 1부터 시작
// 좀비 위치 5, 보스위치 9로 조정
// 10이 되면 끝나는 구조

public class Main {
	public static void main(String[] args) {

		Controller con = Controller.getInstance();
		
		con.playGame();
	}
}

class Controller{
	private Scanner sc = new Scanner(System.in);

	final int FIELD = 0;
	final int HERO = 1; 
	final int ZOMBIE = 2; 
	final int BOSS = 3; 
	Hero hero;
	Zombie zombie;
	Boss boss;
	
	int field[];
	
	// Controller instance => singleton 
	private static Controller instance = new Controller();

	private Controller() {}

	public static Controller getInstance() {
		return instance;
	}
	
	public void playGame() {
		field = new int[10];
//		Unit(int who, int hp, int damage, int stand)
		setUnitOnField( hero.getInstance(HERO, 300, 50, 1), field);
		setUnitOnField( new Zombie(ZOMBIE, 100, 30, 5), field);
		setUnitOnField( new Boss(BOSS, 300, 40, 9), field);
		
		while(end()) {
			view(field);
			move(field);
			break;
		}
	}
	
	
	// 유닛 셋팅
	// ㄴ 위치가 1일 때 -1해서 0인덱스 컨트롤
	// ㄴ 위치와 뷰의 동기화
	public void setUnitOnField(Unit unit, int field[]) {
		field[unit.getStand()-1] = unit.getWho();	
		if(unit.getWho() == HERO) hero = (Hero)unit;
		if(unit.getWho() == ZOMBIE) zombie = (Zombie)unit;
		if(unit.getWho() == BOSS) boss = (Boss)unit;
	}
	
	// 뷰
	public void view(int field[]) {
		for(int i=0; i<10; i++) {
			if(field[i] == HERO) System.out.print("옷");
			else if(field[i] == ZOMBIE) System.out.print("옷");
			else if(field[i] == BOSS) System.out.print("옷(Boss)");
			else if(field[i] == FIELD) System.out.print("_");
		}
		System.out.println(">>");
	}
	
	// 이동
	public void move(int field[]) {
		// 플레이어 좌표, 필드 좌표 같이 이동
		System.out.print("1]이동 2]물약 먹기 >>");
		int sel = sc.nextInt();
		
		if(sel == 1) { // 이동
			field[hero.getStand()] = FIELD;
			hero.setStand(hero.getStand()+1);
			field[hero.getStand()] = HERO;
			
			this.fight(field); // 싸우는지
			this.end(); // 이동>10, 플레이어 hp<0
		}
		else if(sel == 2) { // 포션 마시기
			if(hero.potion != 0) {
				hero.potion--;
				hero.plusHp(100);
				System.out.printf("[포션을 마셨다 hp+100]\n[포션: %d개][%d/%d]\n", hero.potion, hero.getHp(), hero.getMaxHp());				
			} else System.out.println("[포션이 없습니다]");
		}
	}
	
	// 싸우기
	public void fight(int field[]) {
		// 싸우고 나면, 히어로로 그대로, 아니면 게임오버
		// 합칠 수 있는 방법을 찾아보기
		if(hero.getStand() == zombie.getStand() || hero.getStand() == boss.getStand()) {
			// 적 UpCasting
			Unit enamy = null;
			if(hero.getStand() == zombie.getStand()) enamy = (Unit)zombie;
			else if (hero.getStand() == boss.getStand()) enamy = (Unit)boss;

			while(true) {
				
				// fight 
				hero.attack(enamy);
				enamy.attack(hero);
				
				if(hero.getHp() < 0) break; // 히어로 사망
				if(zombie.getHp() < 0) { // 좀비 죽임
					System.out.println("[좀비 토벌 완료]");
					break;
				}
			}
		}
		if(hero.getStand() == boss.getStand()) {
			
		}
		
	}
	
	// 종료조건 : 히어로 위치 10넘음, 히어로 죽음 
	// 이동 때 넣고, 싸울 때 넣고
	public boolean end() {
		boolean check = true;
		if(hero.getStand() > 10) {
			System.out.println("[게임 클리어]");
		}
		if(hero.getHp() < 0) {
			System.out.println("[플레이어 사망]");
		}
		return check;
	}
}
