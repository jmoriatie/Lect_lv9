package unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends Unit{
	
	private Map<String, Integer> skills;

	public int stun; // 활동불가 (0:활동가능)
	public int stopSkill; // 스킬사용불가(0:스킬사용가능)
	public String job;
	public int skillCnt;
	
	public Player(String name , int maxHp , int power, String job) {
		super(name, maxHp, power);
		skills = new HashMap<String, Integer>();
		setSkill(); // 스킬 셋팅
		stun = 0; // 스턴지수 0
		stopSkill = 0; // 스킬사용불가 0
		this.job = "없음"; // 직업 기본 없음
		for(Object j : skills.keySet()) { // 직업에 있으면, job과 스킬 인덱스를 넣어주기
			if(j.equals(job)) {
				this.job = job;
			}
		}
	}
	
	@Override
	public void attack(Unit target) {
		super.attack(target);
	}
	
	// 스킬 셋팅
	private void setSkill() {
		skills.put("전사", 1);
		skills.put("마법사", 2);
		skills.put("성직자", 3);
		skills.put("없음", 0);
		skillCnt = 5;
	}
	
	// 전사스킬 => 최대체력 증가, 공격력 증가, 체력 전체 채우기
	public void warriarSkill() {
		if(this.job.equals("전사")) {
			if(skillCnt != 0) {
				if (stopSkill == 0) {
					this.maxHp += 50;
					this.power += 50;
					this.hp = this.maxHp;
					skillCnt--;
					System.out.printf("[전사스킬 사용!][잔여스킬: %d회]\n", this.skillCnt);
					System.out.printf("[%s 최대체력 +50, 공격력 +50, 체력 가득참][%d/%d]\n", this.name, this.hp, this.maxHp);
				}
				else {
					stopSkill--;
					System.out.printf("[스킬사용불가 상태이상, 턴을 넘어갑니다][%d턴 남음]\n", stopSkill);
				}
			} else System.out.println("[스킬포인트가 없습니다]");
	
		}
	}
	
	// 마법사 스킬 => 크리티컬
	public void wizardSkill(Unit target) {
		if(this.job.equals("마법사")) {
			if(skillCnt != 0) {
				if (stopSkill == 0) {
					int criticalHit = (int)(this.power * 2);
					target.hp -= criticalHit;
					skillCnt--;
					System.out.printf("[마법사스킬 사용!][잔여스킬: %d회]\n", this.skillCnt);
					System.out.printf("[크리티컬 히트 [%s]에게 %d(%d*2)의 데미지를 입혔습니다]\n", target.name, criticalHit, this.power);
					
					checkTargetHp(target);
				}
				else {
					stopSkill--;
					System.out.printf("[스킬사용불가 상태이상, 턴을 넘어갑니다][%d턴 남음]\n", stopSkill);
				}
			} else System.out.println("[스킬포인트가 없습니다]");
		} 
	}
	
	// 성직자 스킬 => 파티원 전체 힐
	public void pristSkill() {
		// 파티 가져오기
		ArrayList<Player> party = UnitManager.getInstance().party; 
		if (this.job.equals("성직자")) {
			if (skillCnt != 0) {
				if (stopSkill == 0) {
					skillCnt--;
					System.out.printf("[성직자스킬 사용!][잔여스킬: %d회]\n", this.skillCnt);
					System.out.printf("[파티원 전체 최대체력 회복]\n");
					for (Unit unit : party) {
						unit.hp = unit.maxHp;
						System.out.printf(" ㄴ [%s][%d/%d]\n", unit.name, unit.hp, unit.maxHp);
					}
				} else {
					stopSkill--;
					System.out.printf("[스킬사용불가 상태이상, 턴을 넘어갑니다][%d턴 남음]\n", stopSkill);
				}
			} else
				System.out.println("[스킬포인트가 없습니다]");
		}
	}
	
	
	@Override
	public void printUnit() {
		super.printUnit();
	}
	
	
}
