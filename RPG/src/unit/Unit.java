package unit;

import shop.Item;

public class Unit {
//	이름, 레벨, 파티중, 공격력, 방어력, 체력 , 아이템//무기,방어,장신//   
	
	private String name;
	private int level;
	boolean isParty;
	
	private int str;
	private int def;
	private int hp;
	
	private Item weapon;
	private Item armor;
	private Item accessory;
	
	public Unit(String name) {
		super();
		this.name = name;
		this.level = 1;
		this.isParty = false; 
		this.str = 10;
		this.def = 0;
		this.hp = 100;
		this.weapon = null;
		this.armor = null;
		this.accessory = null;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isParty() {
		return isParty;
	}

	public void setParty(boolean isParty) {
		this.isParty = isParty;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public Item getWeapon() {
		return weapon;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	public Item getArmor() {
		return armor;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public Item getAccessory() {
		return accessory;
	}

	public void setAccessory(Item accessory) {
		this.accessory = accessory;
	}
}
