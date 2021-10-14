package hero;

import unit.Unit;

// 보스처럼 공격, 확률적으로 크리티컬 구현해보기
// 물약 있음
//ㄴ 기본 공격도 %화

public class Hero extends Unit {
	
	// 싱글톤 의미가 있을까?
	private static Hero instance;
	
	public int potion = 5; // 물약
	
	private Hero(int who, int hp, int damage, int stand) {
		super(who, hp, damage, stand);
	}
	// 싱글톤에 의한 instance화
	public static Hero getInstance(int who, int hp, int damage, int stand) {
		instance = new Hero(who, hp, damage, stand);
		return instance;
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
