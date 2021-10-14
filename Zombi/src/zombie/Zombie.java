package zombie;

import unit.Unit;

public class Zombie extends Unit {

	public Zombie(int who, int maxHp, int damage, int stand) {
		super(who, maxHp, damage, stand);
	}

	@Override
	public void attack(Unit unit) {
		double ranPer = r.nextDouble(); // 0.0 ~ 0.9
		int damage = (int)(this.getDamage() * ranPer);
		if(damage != 0) {
			System.out.printf("[좀비가 %2d의 데미지로 공격했다]\n", damage);
		} else {
			System.out.println("좀비의 공격이 빗나갔다");
		}
		unit.substractHp(damage); // 용사
	}

	@Override
	public void substractHp(int damage) {
		this.setHp(this.getHp() - damage);
		System.out.printf("좀비: 꾸엑![hp %d/%d]\n", this.getHp(), this.getMaxHp());
		System.out.println();
	}

}
