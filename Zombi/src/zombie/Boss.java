package zombie;

import unit.Unit;

// 쉴드 가짐 
// 필살기 가짐, 데미지x2

public class Boss extends Zombie{

	int shield;
	
	public Boss(int who, int hp, int damage, int stand) {
		super(who, hp, damage, stand);
		this.shield = 100;
	}
	
	// UpStream 필요할 듯
	public void attack(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	public void substractHp(int damage) {
		// TODO Auto-generated method stub
		
	}

	public void plusHp(int damage) {
		// TODO Auto-generated method stub
		
	}

}
