package unit;

import java.util.ArrayList;

import main.Main;

public class GuildController {
	public static GuildController instance =  new GuildController();
	
	private ArrayList<Unit> guild;
	private ArrayList<Unit> party;
	
	private GuildController() {
		guild = new ArrayList<Unit>();
		party = new ArrayList<Unit>();
	}

// ============ 전체 관리 메뉴 메서드 ======================
// ============ 길드 관련 메서드 ======================

	// 길드원 모집
	
	// 인덱스 => 길드원
	public Unit getGuildUnit(int idx) {
		Unit guildUnit = null;
		if(!guild.isEmpty()) {
			if(idx >= 0 && idx < guild.size()) {
				guildUnit = guild.get(idx);
			}
		} 
		return guildUnit;
	}
	
	// 길드 유닛 이름 => 길드원
	public Unit getGuildUnit(String unitName) {
		Unit guildUnit = null;
		if(!guild.isEmpty()) {
			for(Unit u : guild) {
				if(u.getName() == unitName) {
					guildUnit = u;
				}
			}
		} 
		return guildUnit;
	}

	// guild size
	public int sizeOfGuild() {
		return this.guild.size();
	}
	
	// print all guild
	public void printAllGuild() {
		System.out.println("====== 길드 유닛 =======");
		for(Unit u : guild) {
			// 아이템 파워 통합 출력
			System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(), u.getHp(),u.isParty());
			System.out.printf("[hp: %d][str: %d][def: %d]\n\n", (u.getHp()+u.getAccessory().getPower()), (u.getStr()+u.getWeapon().getPower()), (u.getDef()+u.getArmor().getPower()));
	
		}
	}
	
	// print one unit
	public void printOneUnit() {
		System.out.println("====== 유닛 조회 =======");
		printAllGuild();
		System.out.print("조회할 길드 유닛 번호 입력: ");
		int selUnit = selectInt( Main.sc.next() ); 
		if(getGuildUnit(selUnit) != null) {
			Unit u = getGuildUnit(selUnit);
			// 아이템 착용 여부 체크 
			if(u.getWeapon() == null && u.getArmor() == null && u.getAccessory() == null) { // 아이템 없는 경우
				System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(), u.getHp(),u.isParty());
				System.out.printf("[hp: %d][str: %d][def: %d]\n", u.getHp(), u.getStr(), u.getDef());
				System.out.printf("[weapon: 없음][armor: 없음][acc: 없음]\n\n");
			}
			else {
				System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(), u.getHp(),u.isParty());
				System.out.printf("[hp: %d + %d][str: %d + %d][def: %d + %d]\n", u.getHp(),u.getAccessory().getPower(), u.getStr(), u.getWeapon().getPower(), u.getDef(), u.getArmor().getPower());
				System.out.printf("[weapon: %s][armor: %s][acc: %d]\n\n", u.getWeapon().getName(), u.getArmor().getName(), u.getAccessory().getName());
			}
			
		} else System.out.println("[다시 확인 후 선택하세요]");
	}
// ============ 파티 관련 메서드 ======================
	// 파티원 셋팅
	public void addPartyUnit() {
		if(!guild.isEmpty()) {
			System.out.print("파티원으로 추가하고자 하는 길드원을 선택하세요: ");
			
		} else System.out.println("[길드원을 먼저 모집하세요]");
	}
	
	public void deletePartyUnit() {
		
	}
	
	public void changePartyUnit() {
		
	}
		
	// 인덱스 => 파티원
	public Unit getPartydUnit(int idx) {
		Unit partyUnit = null;
		if(!party.isEmpty()) {
			if(idx >= 0 && idx < party.size()) {
				partyUnit = party.get(idx);
			}  
		} 
		return partyUnit;
	}
	// 파티 유닛 이름 => 파티원
	public Unit getPartyUnit(String unitName) {
		Unit partyUnit = null;
		if(!party.isEmpty()) {
			for(Unit u : party) {
				if(u.getName() == unitName) {
					partyUnit = u;
				}
			}
		} 
		return partyUnit;
	}
	
	// party size
	public int sizeOfParty() {
		return this.party.size();
	}
	
	// print all party
	public void printAllParty() {
		System.out.println("====== 파티 유닛 =======");
		for(Unit u : party) {
			// 아이템 파워 통합 출력
			System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(), u.getHp(),u.isParty());
			System.out.printf("[hp: %d][str: %d][def: %d]\n\n", (u.getHp()+u.getAccessory().getPower()), (u.getStr()+u.getWeapon().getPower()), (u.getDef()+u.getArmor().getPower()));
		}
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
}
