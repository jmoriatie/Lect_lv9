package unit;

import java.util.ArrayList;
import java.util.Random;

public class UnitManager {
	// 플레이어 파티, 몬스터 파티
	private static UnitManager instance = new UnitManager();
	
	Random rn = new Random();
	
	ArrayList<Player> party;
	ArrayList<Monster> monsters;

	private UnitManager() {	
		setParty() ;
		setMonsters();
	}
	
	public static UnitManager getInstance() {
		return instance;
	}
	
	public void tempCheck() {
		// 플레이어 몬스터 세팅 잘 됐는지  
		// 유닛 가져오는 메서드 작동 잘 하는지
		for(Player p : party) {
			Player tmpP = getPlayer(p);
			tmpP.printUnit();
			System.out.println(tmpP.job);
		}
		int n = 0;
		for(Monster m : monsters) {
			Unit tmpM = getMonster(n++);
			tmpM.printUnit();
		}

	}
	
	private void setParty() {
		party = new ArrayList<Player>();
		party.add( new Player("야만전사", 500, 100, "전사") );
		party.add( new Player("간달프", 300, 200, "마법사") );
		party.add( new Player("열혈사제", 400, 50, "성직자") );
	}
	
	// 중복이름 확인 => 사용 안함
//	private boolean checkName(Player player) {
//		boolean check = true;
//		for(Player p : party) {
//			if(p.name.equals(player.name)) {
//				check = false;
//			}
//		}
//		return check;
//	}
	
	private void setMonsters() {
		monsters = new ArrayList<Monster>();
		
		for(int i=0; i<5; i++) { // 몬스터 5마리 랜덤 소환
			int rNum = rn.nextInt(3)+1; // 1,2,3
			createMonster(rNum);
		}
	}
	
	private void createMonster(int rNum) {
		if(rNum == 1) { // 박쥐
			UnitBat bat = new UnitBat();
			bat.init(200, 100);
			monsters.add( (Monster)bat ); // 마크인터페이스 Monster
		}
		if(rNum == 2) { // 오크
			UnitOrc orc = new UnitOrc();
			orc.init(500, 80);
			monsters.add( (Monster)orc );
		}
		if(rNum == 3) { // 늑대
			UnitWolf wolf = new UnitWolf(); // 생성
			wolf.init(300, 150); // hp, 공격력
			monsters.add( (Monster)wolf ); // 추가
		}
	}
	
	// 플레이어 반환 메서드(플에이어)
	public Player getPlayer(Player player){
		Player isIn = null;
		for(Player p : party) {
			if(p.name.equals(player.name)) {
				isIn = p;
			}
		}
		return isIn;
	}
	
	// 플레이어 반환 메서드(인덱스)
	public Player getPlayer(int idx){
		Player isIn = party.get(idx);
		return isIn;
	}
	
	// 몬스터 반환 메서드(인덱스)
	public Unit getMonster(int idx) {
		Unit moster = null;
		if(idx >=0 && idx < monsters.size()) {
			moster = (Unit)monsters.get(idx);
		}
		
		return moster;
	}
	
	// 몬스터 반환 메서드(유닛)
	public Unit getMonster(Unit monster) {
		Unit isMon = null;

		for(Monster mon : monsters) {
			Unit m = (Unit)mon;
			if(m.name.equals(monster.name)) {
				isMon = m;
			}
		}
		
		return isMon;
	}
	
	// 체크 후 아웃
	public void checkTarget(Unit unit) {
		if(unit != null && unit.hp == 0) {
			monsters.remove((Object)unit);
			System.out.printf("[[%s]이(가) 사망했습니다]\n", unit.name);
		}
	}

	public void checkPlayer(Player player) {
		if(player != null && player.hp == 0) {
			party.remove(player);
			System.out.printf("[플레이어의 유닛 [%s]이(가) 사망했습니다]\n", player.name);
		}
	}
	
	
	public int getPartySize() {
		return this.party.size(); 
	}
	public int getMonstersSize() {
		return this.monsters.size();
	}
	
	
}
