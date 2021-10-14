package unit;

import java.util.Random;

// Unit => Hero, Zombie(=> Boss)
// 위치(랜덤등장x), hp, damage

abstract public class Unit {
	
	private int who; // 식별번호
	private int hp;
	private int damage;
	private int maxHp;
	
	public int stand;
	public Random r; 
	// ㄴ Random과 관련하여 파생된 자식들은 여기로 Upstream되어서, 컨트롤 됨
	// ㄴ 따라서, 랜덤을 여기서 사용하는 것으로 볼 수 있음
	
	public Unit(int who, int maxHp, int damage, int stand) {
		this.who = who;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.damage = damage;
		this.stand = stand;
		this.r = new Random();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getWho() {
		return who;
	}
	
	abstract public void attack(Unit unit); // 데려다가 때리고
	abstract public void substractHp(int damage); // hp조정
	
	public int getDamage() {
		return damage;
	}

	public int getStand() {
		return stand;
	}

	public void setStand(int stand) {
		this.stand = stand;
	}

	public int getMaxHp() {
		return maxHp;
	}
}
