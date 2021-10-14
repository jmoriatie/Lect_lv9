package hero;

import unit.Cureble;
import unit.Unit;

// 보스처럼 공격, 확률적으로 크리티컬 구현해보기
// 물약 있음
//ㄴ 기본 공격도 %화

public abstract class Hero extends Unit implements Cureble{
	
	// 추상클래스 싱글톤 안되나?
	public int potion = 5; // 물약
	
	public Hero(int who, int maxHp, int damage, int stand) {
		super(who, maxHp, damage, stand);
	}
	
	
	@Override
	public void attack(Unit unit) {
		double ranPer = r.nextDouble(); // 0.0 ~ 0.9
		int damage = (int)(this.getDamage() * ranPer);
		System.out.printf("[히어로가 %2d의 데미지로 공격했다]\n", damage);
		unit.substractHp(damage); // 용사
		System.out.println(unit.getHp());
	}

	@Override
	public void substractHp(int damage) {
		this.setHp(this.getHp() - damage);
		System.out.printf("좀비: 아야![hp %d/%d]\n", this.getHp(), this.getMaxHp());
	}
	
	@Override	// 인터페이스 오버라이드
	public boolean cure() {
		boolean check = true;
		if(getHp() == getMaxHp()) {
			System.out.println("[체력이 가득차있습니다]");
			check = false;
		}		
		else if (this.potion != 0) {
			this.potion--;
			this.setHp(this.getHp() + this.potion);
			System.out.printf("[포션을 마셨다 hp+100]\n[남은 포션: %d개][%d/%d]\n", this.potion, getHp(), getMaxHp());
		} else {
			check = false;
			System.out.println("[포션이 없습니다]");
		}
		
		return check;
	}


	
}
