package unit;

import java.util.ArrayList;
import java.util.Random;

import main.Main;
import player.PlayerController;

public class GuildController {
	public static GuildController instance =  new GuildController();
	private final int MAXGUILD = 10;
	private final int MAXPARTY = 4;
	private boolean autoPartyAdd = true;
	private ArrayList<Unit> guild;
	private ArrayList<Unit> party;
	
	private GuildController() {
		guild = new ArrayList<Unit>();
		party = new ArrayList<Unit>();
//		tempAddUnit();
//		tempAddUnit();
//		tempAddUnit();
//		tempAddUnit();
//		tempAddUnit();
	}
	// 디버깅용 길드원 추가
	public void tempAddUnit() {
			if(guild.size() <= MAXGUILD) {
				Unit unit = randomUnit();
				System.out.printf("[%s 영입]",unit.getName());
				guild.add(unit);
					
				if(party.size() >= MAXPARTY) this.autoPartyAdd = false; // 파티 사이즈 같거나 넘으면
				else this.autoPartyAdd = true; // 그게 아니라면

				if(this.autoPartyAdd) {
					System.out.printf("[%s 파티로 이동]",unit.getName());
					unit.setParty(true);
					party.add(unit);
					guild.remove(unit);
				}
				System.out.print("\n");
			} 
			else System.out.println("[더 이상 길드원을 모집할 수 없습니다]");
	}
	
// ============ 전체 관리 메뉴 메서드 ======================
//	- 길드원 모집
//	- 길드원 삭제
//	- 파티원 추가
//	- 파티원 삭제
	public void unitMenu() {
		while(true) {
			System.out.println("========================= 유닛 관리 =========================");
			System.out.println("플레이어가 가진 돈: " + PlayerController.instance.getPlayer().getMoney() + "원");
			System.out.print("[1.길드원 조회][2.길드원 영입][3.길드원 추방]\n[4.파티원 조회][5.파티원 추가][6.파티원 제외][7. 파티원 교체][0.뒤로가기] : ");
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
				updatePartyUnit();;
			}
			else if(sel == 0) {
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
	
	// 유닛 통합 출력 메서드
	private void printUnitStatus(Unit u) {
		System.out.printf("[이름: %s][레벨: %d][파티원: %b]\n",u.getName(), u.getLevel(), u.isParty());
		// 장신구
		if(u.getAccessory() == null) System.out.printf("[hp: %d]", u.getHp());
		else System.out.printf("[hp: %d + %d]", u.getHp(), u.getAccessory().getPower() );
		
		// 무기
		if(u.getWeapon() == null) System.out.printf("[str: %d]", u.getStr());
		else System.out.printf("[str: %d + %d]", u.getStr(), u.getWeapon().getPower() );
		
		 // 방어구
		if(u.getArmor() == null) System.out.printf("[def: %d]\n", u.getDef());
		else System.out.printf("[def: %d + %d]\n", u.getDef(), u.getArmor().getPower() );
		
		if(u.getAccessory() != null || u.getWeapon() != null || u.getArmor() != null) {
			System.out.print("ㄴ[착용]");
			if(u.getAccessory() != null) System.out.printf("[%s: hp+%d]", u.getAccessory().getName(), u.getAccessory().getPower());
			if(u.getWeapon() != null) System.out.printf("[%s: str+%d]", u.getWeapon().getName(), u.getWeapon().getPower());
			if(u.getArmor() != null) System.out.printf("[%s: def+%d]", u.getArmor().getName(), u.getArmor().getPower());
			System.out.print("\n");			
		}
	}
	
	// print all guild
	public void printAllGuild() {
		System.out.println("========= 길드 유닛 ==========");
		if(guild.isEmpty()) {
			System.out.println("[길드원을 영입하세요]");
		}
		else {
			for(Unit u : guild) {
				System.out.println("#" + guild.indexOf(u));
				printUnitStatus(u);
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
			System.out.println("#" + guild.indexOf(u));
			printUnitStatus(u);
		} else System.out.println("[다시 확인 후 선택하세요]");
	}
	
	private void pause() {
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	// ================== 불러오기 사용 ============================
	// 길드, 파티 초기화
	public void initUnitArr() {
		if(!this.guild.isEmpty()) {
			this.guild = new ArrayList<Unit>();
		}
		if(!this.party.isEmpty()) {
			this.party = new ArrayList<Unit>();
		}
	}
	// 길원 셋팅
	public void setGuildUnit(Unit unit) {
		this.guild.add(unit);
	}
	// 파티원 셋팅
	public void setPartyUnit(Unit unit) {
		this.party.add(unit);
	}
	// ================== 길드 관련 ============================
	// 길드원 모집=
	public void addGuildUnit() {
		if(PlayerController.instance.getPlayer().checkMoneyPossible(1000)) {
			if(guild.size() <= MAXGUILD) {
				Unit unit = randomUnit();
				System.out.printf("[유닛 \'%s\'이(가) 길드로 영입되었습니다!]",unit.getName());
				System.out.printf("ㄴ [이름: %s][레벨: %d]\n",unit.getName(), unit.getLevel());
				System.out.printf("ㄴ [hp: %d][str: %d][def: %d]\n", unit.getHp(), unit.getStr(), unit.getDef());
				PlayerController.instance.getPlayer().substractMoney(1000); //  돈 빼기
				System.out.printf("[길드원 영입비 %d원 차감]\n", 1000);
				guild.add(unit);
				this.autoPartyAdd(unit);
				pause();
			} 
			else System.out.println("[더 이상 길드원을 모집할 수 없습니다]");
		} else System.out.println("[플레이어의 소지금이 부족합니다]");
	}
	
	// 길드원 랜덤 추출
	private Unit randomUnit() {
		String one[] = {"두", "이", "박", "최", "석", "둠", "댕"};
		String two[] = {"랑", "리", "테", "장", "고", "가", "댕"};
		String thr[] = {"고", "듐", "리", "수", "대", "이", "댕"};
		Random rn = new Random();
		String ranName = one[rn.nextInt(one.length)] + two[rn.nextInt(two.length)] + thr[rn.nextInt(thr.length)];
		return new Unit(ranName);
	}
	
	// 길드원 추방
	public void deleteGuildUnit() {
		if(!guild.isEmpty()) {
			printAllGuild();
			System.out.print("추방할 길드원을 선택하세요: ");
			int selUnit = selectInt( Main.sc.next() ); 
			if(selUnit >= 0 && selUnit < guild.size()) {
				Unit unit = getGuildUnit(selUnit);
				if(unit.getWeapon() != null|| unit.getArmor() != null || unit.getAccessory() != null) { // 아이템 착용중일 때 알람
					System.out.print("아이템을 착용중입니다, 추방하시겠습니까?[추방하려면 'y' 입력] : ");
					String sel = Main.sc.next();
					if("y".equals(sel)) {
						guild.remove(unit);
						pause();
						System.out.printf("[\'[%s]\'이(가) 추방되었습니다!]\n", unit.getName());
					}
				}
				else { // 아이템 없을 때 알림 없이 추방
					guild.remove(unit);
					pause();
					System.out.printf("[\'[%s]\'이(가) 추방되었습니다!]\n", unit.getName());
				}
			} else System.out.println("[길드원 번호를 확인하세요]");
		} else System.out.println("[모집된 길드원이 없습니다]");
	}
	
// ============ 파티 관련 메서드 ======================
	// 파티원 셋팅
	public void addPartyUnit() {
		if(party.size() < MAXPARTY) {
			if(!guild.isEmpty()) {
				printAllGuild();
				System.out.print("파티원으로 추가하고자 하는 길드원을 선택하세요: ");
				int selUnit = selectInt( Main.sc.next() ); 
				if(selUnit >= 0 && selUnit < guild.size()) {
					Unit unit = getGuildUnit(selUnit);
					System.out.printf("[\'[%s]\'이(가) 파티로 추가되었습니다!]\n",unit.getName());
					unit.setParty(true); // 파티 true 바꾸기 -> 전혀 이용 안한듯..
					party.add(unit);
					guild.remove(unit);
					pause();
				} else System.out.println("[길드원 번호를 확인하세요]");
			} else System.out.println("[길드원을 먼저 모집하세요]");
		} else System.out.println("[더 이상 파티원을 추가할 수 없습니다]");
	}
	
	public void deletePartyUnit() {
		if(!party.isEmpty()) {
			this.printAllParty();
			System.out.print("삭제할 파티원을 선택하세요: ");
			int selUnit = selectInt( Main.sc.next() ); 
			if(selUnit >= 0 && selUnit < party.size()) {
				Unit unit = getPartydUnit(selUnit);
				unit.setParty(false);
				guild.add(unit);
				party.remove(unit);
				System.out.printf("[\'[%s]\'이(가) 파티에서 제외되었습니다!, 제외된 유닛은 길드에서 재영입 가능합니다]\n",unit.getName());
				pause();
			} else System.out.println("[파티원 번호를 확인하세요]");
		} else System.out.println("[파티원이 없습니다]");
	}
	// 교체	
	public void updatePartyUnit() {
		if(!this.guild.isEmpty()) {
			if(!party.isEmpty()) {
				this.printAllParty();
				System.out.print("교체할 파티원을 선택하세요: ");
				int selPIdx = selectInt( Main.sc.next() ); 
				if(selPIdx >= 0 && selPIdx < party.size()) {
					this.printAllGuild();
					System.out.print("파티에 포함할 길드원을 선택하세요: ");
					int selGIdx = selectInt( Main.sc.next() ); 
					
					Unit unitP = getPartydUnit(selPIdx);
					unitP.setParty(false);
					guild.add(unitP);
					party.remove(unitP);
					
					Unit unitG = getGuildUnit(selGIdx);
					unitG.setParty(true);
					party.add(unitG);
					guild.remove(unitG);
					System.out.printf("[\'[%s]\'이(가) 파티에 추가되었습니다(\'[%s]\' 길드로 이동)]\n",unitG.getName(), unitP.getName());
					pause();
				} else System.out.println("[파티원 번호를 확인하세요]");
			} else System.out.println("[파티원이 없습니다]");
		} else System.out.println("[교체할 유닛이 없습니다, 길드원을 우선 영입하세요]");
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
		if(party.isEmpty()) {
			System.out.println("[파티원이 없습니다]");
		}
		else {
			for(Unit u : party) {
				System.out.println("#" + party.indexOf(u));
				printUnitStatus(u);
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
	
	// 자동 파티 추가
	private void autoPartyAdd(Unit unit) {
		if(party.size() >= MAXPARTY) this.autoPartyAdd = false; // 파티 사이즈 같거나 넘으면
		else this.autoPartyAdd = true; // 그게 아니라면

		if(this.autoPartyAdd) {
			System.out.printf("[\'[%s]\'이(가) 파티로 추가되었습니다!]\n",unit.getName());
			unit.setParty(true);
			party.add(unit);
			guild.remove(unit);
			
		}
	}
}
