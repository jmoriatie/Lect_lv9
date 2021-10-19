package unit;

import java.util.Random;
import java.util.Scanner;

public class Unit {
		
	private Random rn = new Random();
	
	public int hp;
	public int maxHp;
	public int power;
	public String name;
	public String state = "노멀";
	
	public Unit(){};
	
	public Unit(String name , int maxHp , int power){
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
	};
	
	// 몬스터용
	public void init(int maxHp , int power){ // 이름X
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
	};
	
	
	public void attack(Unit target) {
		int damage = (int)( power * rn.nextDouble() );
		target.hp -= damage;
		System.out.printf("[%s]이(가)[%s]에게 \'[%d]\'의 데미지를 입혔습니다.\n", name, target.name, damage);

		checkTargetHp(target);
	}
	
	public void printUnit() {
		System.out.printf("[%s][%d/%d][%d]\n", name, hp, maxHp, power);
	}
	
	public void checkTargetHp(Unit target) {
		if(target.hp <= 0) {
			target.hp = 0;
		}
	}
	
	public void skill(Unit unit) {
		
	}
}
