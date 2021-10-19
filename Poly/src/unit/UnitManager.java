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
	private boolean checkName(Player player) {
		boolean check = true;
		for(Player p : party) {
			if(p.name.equals(player.name)) {
				check = false;
			}
		}
		return check;
	}
	
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
			bat.init(100, 50);
			monsters.add( (Monster)bat ); // 마크인터페이스 Monster
		}
		if(rNum == 2) { // 오크
			UnitOrc orc = new UnitOrc();
			orc.init(200, 30);
			monsters.add( (Monster)orc );
		}
		if(rNum == 3) { // 늑대
			UnitWolf wolf = new UnitWolf(); // 생성
			wolf.init(150, 40); // hp, 공격력
			monsters.add( (Monster)wolf ); // 추가
		}
	}
	
	// 플레이어 반환 메서드
	public Player getPlayer(Player player){
		Player isIn = null;
		for(Player p : party) {
			if(p.name.equals(player.name)) {
				isIn = p;
			}
		}
		return isIn;
	}
	
	// 몬스터 반환 메서드
	public Unit getMonster(int idx) {
		Unit moster = null;
		if(idx >=0 && idx < monsters.size()) {
			moster = (Unit)monsters.get(idx);
		}
		
		return moster;
	}
}
