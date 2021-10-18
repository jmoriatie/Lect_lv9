package Unit;

import java.util.HashMap;
import java.util.Map;

abstract public class Unit {
	Map<String, Integer> skills = new HashMap<String, Integer>();
	
	public final String noSkill = "noSkill";
	public final String WarriarSkill = "critical"; // 맥스HP증가 
	public final String magicianSkill = "magic";
	public final String HealerSkill = ""; // 힐
	
	int hp;
	int maxHp;
	int power;
	String job;
	String name;
	String state = "노멀";
	
	Unit(){};
	Unit(String name , int maxHp , int power, String job){
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
		this.job = "없음";
		for(Object j : skills.keySet()) {
			if(j.equals(job)) {
				this.job = job;
			}
		}
	};
	protected void init(int maxHp , int power,  String job){ // 이름X
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
		this.job = job;
	};
	
	protected void init(String name , int maxHp , int power,  String job){ //이름 입력
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
		this.job = job;
	};
	
	protected void attack(Unit target) {
		target.hp -= power;
		System.out.printf("[%s]이[%s]에게 \'[%d]\'의 데미지를 입혔습니다.", name, target.name, power);
		System.out.printf("[]를 처치했습니다.");

		if(target.hp <= 0) {
			System.out.printf("[%s]를 처치했습니다!", target.name);
			target.hp = 0;
		}
	}
	protected void printUnit() {
		System.out.printf("[%s][%d/%d][%d]", name, hp, maxHp, power);
	}
	
	protected void skillWarriar() {
		if(this.job.equals("전사")) {
			
		}
	}
	protected void skillWizard() {
		if(this.job.equals("마법사")) {
			
		}
	}
	protected void skillPrist() {
		if(this.job.equals("성직자")) {
			
		}
	}

	
}
