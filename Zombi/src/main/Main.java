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

		Controller con = new Controller();
		
		int field[] = new int[10];
//		Unit(int who, int hp, int damage, int stand)
		con.setUnitOnField( new Hero(con.HERO, 300, 50, 1), field);
		con.setUnitOnField( new Zombie(con.ZOMBIE, 100, 30, 5), field);
		con.setUnitOnField( new Boss(con.BOSS, 300, 50, 9), field);
		
		while(true) {
			con.view(field);
			break;
		}
	}
}

class Controller{
	private Scanner sc = new Scanner(System.in);

	final int FIELD = 0;
	final int HERO = 1; 
	final int ZOMBIE = 2; 
	final int BOSS = 3; 
	
	// 유닛 셋팅
	// ㄴ 위치가 1일 때 -1해서 0인덱스 컨트롤
	// ㄴ 위치와 뷰의 동기화
	public void setUnitOnField(Unit unit, int field[]) {
		field[unit.getStand()-1] = unit.getWho();			
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
	public void move(Hero hero) {
		// 플레이어 좌표, 필드 좌표 같이 이동
		System.out.print("1]이동 2]물약 먹기 >>");
		int sel = sc.nextInt();
		
		if(sel == 1) { // 이동
			
		}
		else if(sel == 2) { // 물약 복용
			hero.potion--;
			hero.plusHp(100);
			System.out.printf("[물약을 먹었다! hp+100][%d/%d]\n", hero.getHp(), hero.getMaxHp());
		}
	}
}
