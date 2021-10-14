package zombie;

import unit.Unit;

// 쉴드 가짐 
// 필살기 가짐, 데미지x2
// ㄴ 기본 공격도 %화

public class Boss extends Zombie{

	int shield;
	
	public Boss(int who, int maxHp, int damage, int stand) {
		super(who, maxHp, damage, stand);
		this.shield = 100;
	}
	
	// UpStream 필요할 듯
	public void attack(Unit unit) {
		double ranPer = r.nextDouble(); // 0.0 ~ 0.9
		int damage = (int)(this.getDamage() * ranPer);
		System.out.printf("[보스가 %2d의 데미지로 공격했다]\n", damage);
		unit.substractHp(damage); // 용사
		System.out.println(unit.getHp());		
	}

	public void substractHp(int damage) {
		this.setHp(this.getHp() - damage);
		System.out.printf("보스: 으억![hp %d/%d]\n", this.getHp(), this.getMaxHp());		
	}


}
