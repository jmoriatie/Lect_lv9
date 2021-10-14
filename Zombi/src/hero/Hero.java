package hero;

import unit.Unit;

// 보스처럼 공격, 확률적으로 크리티컬 구현해보기
// 물약 있음
//ㄴ 기본 공격도 %화

public class Hero extends Unit{
	
	private static Hero instance; // 인스턴스
	
	public int potion = 5; // 물약
	
	private Hero(int who, int maxHp, int damage, int stand) {
		super(who, maxHp, damage, stand);
	}
	
	// 싱글톤 인스턴스
	public static Hero getInstance(int who, int maxHp, int damage, int stand) {
		instance = new Hero(who, maxHp, damage, stand);
		return instance;
	}
	
	@Override
	public void attack(Unit unit) {
		int ranNum = r.nextInt(3)+1; // 3분의 1확률 1나오면 크리티컬 데미지
		int damage;
		if(ranNum == 1) {
			damage = (int)(this.getDamage() * 1.5);
			System.out.printf("[영웅의 크리티컬 데미지!! %2d*1.5]\n", damage);
		}
		else {
			double ranPer = r.nextDouble(); // 0.0 ~ 0.9
			damage = (int)(this.getDamage() * ranPer);
			System.out.printf("[영웅이 %2d의 데미지로 공격했다]\n", damage);
		}
		unit.substractHp(damage); // 적군
	}

	@Override
	public void substractHp(int damage) {
		this.setHp(this.getHp() - damage);
		System.out.printf("영웅: 아야![hp %d/%d]\n", this.getHp(), this.getMaxHp());
		System.out.println();
	}
	
	public boolean cure() {
		boolean check = true;
		if(getHp() == getMaxHp()) {
			System.out.println("[체력이 가득차있습니다]");
			check = false;
		}		
		else if (this.potion != 0) {
			this.potion--;
			if(this.getHp() + 100 > this.getMaxHp()) this.setHp(this.getMaxHp());
			else this.setHp(this.getHp() + 100);
			System.out.printf("[포션을 마셨다 hp+100][남은 포션: %d개][%d/%d]\n", this.potion, getHp(), getMaxHp());
		} else {
			check = false;
			System.out.println("[포션이 없습니다]");
		}
		
		return check;
	}


	
}
