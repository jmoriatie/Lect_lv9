package unit;

import java.util.Random;
import java.util.Scanner;

public class Unit {
		
	protected int hp;
	protected int maxHp;
	protected int power;
	protected String name;
	protected String state = "노멀";
	
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
	
	
	protected void attack(Unit target) {
		target.hp -= power;
		System.out.printf("[%s]이[%s]에게 \'[%d]\'의 데미지를 입혔습니다.\n", name, target.name, power);

		checkTargetHp(target);
	}
	
	protected void printUnit() {
		System.out.printf("[%s][%d/%d][%d]\n", name, hp, maxHp, power);
	}
	
	//  Manager에서 유닛 없애기도 만들어야함
	protected void checkTargetHp(Unit target) {
		if(target.hp <= 0) {
			System.out.printf("[%s]를 처치했습니다!", target.name);
			target.hp = 0;
		}
	}
}
