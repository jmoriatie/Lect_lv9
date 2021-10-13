package hero;

import unit.Unit;

// 보스처럼 공격, 확률적으로 크리티컬 구현해보기
// 물약 있음
public class Hero extends Unit {

	public int potion = 5; // 물약

	public Hero(int who, int hp, int damage, int stand) {
		super(who, hp, damage, stand);
	}

	@Override
	public void attack(Unit unit) {
		// TODO Auto- method stub

	}

	@Override
	public void substractHp(int damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void plusHp(int damage) {
		// TODO Auto-generated method stub

	}

	
}
