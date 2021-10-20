package stage;

import java.util.Random;
import java.util.Scanner;

import main.GameManager;
import unit.Player;
import unit.Unit;
import unit.UnitManager;

public class StageBattle extends Stage{
	// 전투 관련
	private Scanner sc = new Scanner(System.in);
	private UnitManager um = UnitManager.getInstance();
	private Random rn = new Random();
	
	boolean run;
	
	@Override
	public void init() {
				
	}
	
	@Override
	public boolean update() {
		this.run = end();
		if(this.run == true) {
			stageBattleRun();
		}
		GameManager.getInstance().nextStage = "Lobby";
		return true;
	}
	
	
	public void stageBattleRun() {
		view();
		playerTurn();
		System.out.println();
		monsterTurn();
		GameManager.getInstance().nextStage = "Lobby";
	}
	
	private void view() {
		System.out.println("===== 전투 스테이지 =====");
		System.out.println("===== 캐릭터 =====");
		printPlayer();
		System.out.println("===== 몬스터 =====");
		printMonster();
		System.out.println("====================");
	}
	
	private void printMonster() {
		for(int i=0; i<um.getMonstersSize(); i++) { 
			System.out.print("["+(i+1)+"]");
			um.getMonster(i).printUnit();
		}
	}
	
	private void printPlayer() {
		for(int i=0; i<um.getPartySize(); i++) {
			Player player = um.getPlayer(i);
			player.printUnit();
			System.out.printf("[잔여스킬: %d회]", player.skillCnt);
			System.out.print(" ㄴ [상태이상:");
			if(player.stun == 0 && player.stopSkill == 0) {
				System.out.print("없음");
			}
			if(player.stun != 0) {
				System.out.printf(" 스턴(%d턴) ", player.stun);
			}
			if(player.stopSkill != 0) {
				System.out.printf(" 스킬사용불가(%d턴) ",player.stopSkill);
			}
			System.out.print("]\n");
		}
	}
	
	private void playerTurn() {
		for(int i=0; i<um.getPartySize(); i++) {
			Player player = um.getPlayer(i);
			System.out.printf("[\'%s\'의 턴]\n", player.name);
			if(!checkStun(player)) {
				System.out.println("[1]공격 [2]스킬");
				int sel = select(sc.next());
				Unit target = null;
				if(sel == 1) { // 공격
					printMonster();
					System.out.print("타겟 선택: "); 
					target = um.getMonster( select(sc.next())-1 );
					player.attack(target);

				}
				else if(sel == 2) { // 스킬(캐릭터별 스킬)
					if(player.job.equals("전사")) { // 전사
						player.warriarSkill();
					}
					else if(player.job.equals("마법사")) { // 마법사
						printMonster();
						System.out.print("타겟 선택: "); 
						target = um.getMonster( select(sc.next())-1 );
						player.wizardSkill(target);
					}
					else if(player.job.equals("성직자")) { // 성직자
						player.pristSkill();
					}
					else if(player.job.equals("없음")) { // 없음
						System.out.println("[사용할 수 있는 스킬이 없습니다]");
					}
				}
				um.checkTarget(target);
				if(!end()) break;
			}
		}		
	}
	
	private void monsterTurn() {
		for(int i=0; i<um.getMonstersSize(); i++){
			int rIdx = rn.nextInt( um.getPartySize() );
			int skill = rn.nextInt(3); // 0스킬사용 3분의1 확률		
			Player player = um.getPlayer(rIdx);
			if(skill != 0) {
				um.getMonster(i).attack( player );
			}
			else {
				um.getMonster(i).skill(player);
			}
			um.checkPlayer(player);
			if(!end()) break;
		}
	}
	
	private boolean checkStun(Unit player) {
		boolean check = false;
		Player userUnit = (Player)player;
		if(userUnit.stun != 0) {
			System.out.printf("[스턴 상태이상, 턴을 넘어갑니다][%d턴 남음]\n", --userUnit.stun);
			check = true;
		}
		return check;
	}

	
	private int select(String number) {
		int num = -1;
		try {
			num = Integer.parseInt(number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	// 베틀 종료 메서드
	private boolean end() {
		boolean check = true;
		if(um.getPartySize() == 0) {
			System.out.println("[플레이어 전원 사망]");
			check = false;
		}
		if(um.getMonstersSize() == 0) {
			System.out.println("[몬스터를 모두 무찔렀습니다]");
			check = false;
		}
		return check;
	}
}
