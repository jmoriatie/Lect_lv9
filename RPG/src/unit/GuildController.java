package unit;

import java.util.ArrayList;
import java.util.Random;

import main.Main;

public class GuildController {
	public static GuildController instance =  new GuildController();
	private final int MAXGUILD = 10;
	private final int MAXPARTY = 4;
	private ArrayList<Unit> guild;
	private ArrayList<Unit> party;
	
	private GuildController() {
		guild = new ArrayList<Unit>();
		party = new ArrayList<Unit>();
	}

// ============ 전체 관리 메뉴 메서드 ======================
//	- 길드원 모집
//	- 길드원 삭제
//	- 파티원 추가
//	- 파티원 삭제
	public void unitMenu() {
		while(true) {
			System.out.println("========================= 유닛 관리 =========================");
			System.out.print("[1.길드원 조회][2.길드원 영입][3.길드원 추방][4.파티원조회][5.파티원 추가][6.파티원 삭제][7.뒤로가기] : ");
			int sel = selectInt( Main.sc.next() ); 
			if(sel == 1) {
				printAllGuild();
			}
			else if(sel == 2) {
				addGuildUnit();
			}
			else if(sel == 3) {
				deleteGuildUnit();
			}
			else if(sel == 4) {
				printAllParty();
			}
			else if(sel == 5) {
				addPartyUnit();
			}
			else if(sel == 6) {
				deletePartyUnit();
			}
			else if(sel == 7) {
				break;
			}
		}
	
	}
	
// ============ 길드 관련 메서드 ======================

	
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
		System.out.println("========= 길드 유닛 ==========");
		if(guild.isEmpty()) {
			System.out.println("[길드원을 영입하세요]");
		}
		else {
			for(Unit u : guild) {
				// 아이템 파워 통합 출력
				System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(), u.isParty());
				if(u.getWeapon() == null && u.getArmor() == null && u.getAccessory() == null) { // 아이템 없는 경우
					System.out.printf("[hp: %d][str: %d][def: %d]\n\n", u.getHp(), u.getStr(),u.getDef());
				}
				else {
					System.out.printf("[hp: %d][str: %d][def: %d]\n\n", (u.getHp()+u.getAccessory().getPower()), (u.getStr()+u.getWeapon().getPower()), (u.getDef()+u.getArmor().getPower()));
				}
			}
		}
	}
	
	// print one unit
	public void printOneUnit() {
		System.out.println("============ 유닛 조회 =============");
		printAllGuild();
		System.out.print("조회할 길드 유닛 번호 입력: ");
		int selUnit = selectInt( Main.sc.next() ); 
		if(getGuildUnit(selUnit) != null) {
			Unit u = getGuildUnit(selUnit);
			// 아이템 착용 여부 체크 
			if(u.getWeapon() == null && u.getArmor() == null && u.getAccessory() == null) { // 아이템 없는 경우
				System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(),u.isParty());
				System.out.printf("[hp: %d][str: %d][def: %d]\n", u.getHp(), u.getStr(), u.getDef());
				System.out.printf("[weapon: null][armor: null][acc: null]\n\n");
			}
			else {
				System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(), u.isParty());
				System.out.printf("[hp: %d + %d][str: %d + %d][def: %d + %d]\n", u.getHp(),u.getAccessory().getPower(), u.getStr(), u.getWeapon().getPower(), u.getDef(), u.getArmor().getPower());
				System.out.printf("[weapon: %s][armor: %s][acc: %d]\n\n", u.getWeapon().getName(), u.getArmor().getName(), u.getAccessory().getName());
			}
			
		} else System.out.println("[다시 확인 후 선택하세요]");
	}
	
	private void pause() {
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 길드원 모집=
	public void addGuildUnit() {
		if(guild.size() <= MAXGUILD) {
				Unit unit = randomUnit();
				System.out.printf("[\'유닛 %s이(가) 길드로 영입되었습니다!\']",unit.getName());
				System.out.printf("ㄴ [이름: %s][레벨: %d]\n",unit.getName(), unit.getLevel());
				System.out.printf("ㄴ [hp: %d][str: %d][def: %d]\n", unit.getHp(), unit.getStr(), unit.getDef());
				guild.add(unit);
				pause();
		} else System.out.println("[더 이상 길드원을 모집할 수 없습니다]");
	}
	
	// 길드원 랜덤 
	private Unit randomUnit() {
		String one[] = {"두", "이", "박", "최", "석"};
		String two[] = {"랑", "리", "테", "장", "고"};
		String thr[] = {"고", "듐", "리", "수", "대"};
		Random rn = new Random();
		String ranName = one[rn.nextInt(one.length)] + two[rn.nextInt(two.length)] + thr[rn.nextInt(thr.length)];
		return new Unit(ranName);
	}
	
	// 길드원 추방
	public void deleteGuildUnit() {
		if(!guild.isEmpty()) {
			System.out.print("추방할 길드원을 선택하세요: ");
			int selUnit = selectInt( Main.sc.next() ); 
			if(selUnit >= 0 && selUnit < guild.size()) {
				Unit unit = getPartydUnit(selUnit);
				System.out.printf("[\'유닛 %s이(가) 추방되었습니다!\']",unit.getName());
				guild.remove(unit);
				pause();
			} else System.out.println("[길드원 번호를 확인하세요]");
		} else System.out.println("[모집된 길드원이 없습니다]");
	}
	
// ============ 파티 관련 메서드 ======================
	// 파티원 셋팅
	public void addPartyUnit() {
		if(party.size() <= MAXPARTY) {
			if(!guild.isEmpty()) {
				System.out.print("파티원으로 추가하고자 하는 길드원을 선택하세요: ");
				int selUnit = selectInt( Main.sc.next() ); 
				if(selUnit >= 0 && selUnit < guild.size()) {
					Unit unit = getGuildUnit(selUnit);
					System.out.printf("[\'유닛 %s이(가) 파티로 추가되었습니다!\']",unit.getName());
					party.add(unit);
					guild.remove(unit);
					pause();
				} else System.out.println("[길드원 번호를 확인하세요]");
			} else System.out.println("[길드원을 먼저 모집하세요]");
		} else System.out.println("[더 이상 파티원을 추가할 수 없습니다]");
	}
	
	public void deletePartyUnit() {
		if(!party.isEmpty()) {
			System.out.print("제외할 파티원을 선택하세요: ");
			int selUnit = selectInt( Main.sc.next() ); 
			if(selUnit >= 0 && selUnit < party.size()) {
				Unit unit = getPartydUnit(selUnit);
				guild.add(unit);
				party.remove(unit);
				System.out.printf("[\'유닛 %s이(가) 파티에서 제외되었습니다!\']",unit.getName());
				pause();
			} else System.out.println("[파티원 번호를 확인하세요]");
		} else System.out.println("[파티원이 없습니다]");
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
		System.out.println("============ 파티 유닛 =============");
		if(guild.isEmpty()) {
			System.out.println("[파티원이 없습니다]");
		}
		else {
			for(Unit u : party) {
				if(u.getWeapon() == null && u.getArmor() == null && u.getAccessory() == null) { // 아이템 없는 경우
					System.out.printf("[hp: %d][str: %d][def: %d]\n\n", u.getHp(), u.getStr(),u.getDef());
				} // 예외처리 다시보기 !!!
				// 아이템 파워 통합 출력
				System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(), u.isParty());
				System.out.printf("[hp: %d][str: %d][def: %d]\n\n", (u.getHp()+u.getAccessory().getPower()), (u.getStr()+u.getWeapon().getPower()), (u.getDef()+u.getArmor().getPower()));
			}
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
