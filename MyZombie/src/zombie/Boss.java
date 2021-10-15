package zombie;

import java.util.Random;

import unit.CriticalHit;
import unit.Unit;

// 쉴드 가짐 
// 필살기 가짐, 데미지x2
// ㄴ 기본 공격도 %화

public class Boss extends Zombie implements CriticalHit{

	private static Boss instance;
	
	private int shield;
	private int maxShield;

	private Boss(int who, int maxHp, int damage, int stand) {
		super(who, maxHp, damage, stand); // 좀비 싱글톤이 하면 안되나?
		this.shield = 100;
		this.maxShield = shield;
	}
	
	public static Boss getInstance(int who, int maxHp, int damage, int stand) {
		instance = new Boss(who, maxHp, damage, stand);
		return instance;
	}
	
	public void attack(Unit unit) {
		int ranNum = r.nextInt(3)+1; // 3분의 1확률 1나오면 크리티컬 데미지
		int damage;

		if(ranNum == 1) {
			damage = criticalHit();
			System.out.printf("[보스의 크리티컬 데미지!! %d(%d*1.5)]\n", criticalHit(), damage);

		}
		else {
			double ranPer = r.nextDouble(); // 0.0 ~ 0.9
			damage = criticalHit();
			System.out.printf("[보스가 %2d의 데미지로 공격했다]\n", damage);
		}
		unit.substractHp(damage);
	}

	public void substractHp(int damage) {
		if(shield > 0) { // 쉴드가 0이상일 때
			this.shield = this.shield - damage; // 쉴드를 깎아주고 
			damage = damage - this.shield; // 데미지도 쉴드의 양만큼 깎아줌
			if(damage <= 0) damage = 0;  // 데미지가 쉴드보다 작으면, 데미지는 0 처리
			else this.shield = 0; // 데미지가 쉴드보다 크면 쉴드는 0 처리
		}
		this.setHp(this.getHp() - damage);
		System.out.printf("보스: 으억![shield %d/%d][hp %d/%d]\n",this.shield ,maxShield, this.getHp(), this.getMaxHp());
		System.out.println();
	}

	@Override
	public int criticalHit() {
		int damage = (int)(this.getDamage() * 1.5);
		return damage;
	}


}
