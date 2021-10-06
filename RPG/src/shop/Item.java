package shop;

public class Item {
//	item(종류//무기, 방어구,  장신구//, 이름, 성능, 가격)
//			- 무기: 공격력
//			- 방어: 방어력
//			- 장신: 체력
	public static final int WEAPON = 1;
	public static final int ARMOR = 2;
	public static final int ACCESSORY = 3;
	
	int kind;
	String name;
	int power;
	int price;
	
	public Item() {
		
	}
	
	public Item(int kind, String name, int power, int price) {
		super();
		this.kind = kind;
		this.name = name;
		this.power = power;
		this.price = price;
	}
	
	public int getKind() {
		return kind;
	}
	public String getName() {
		return name;
	}
	public int getPower() {
		return power;
	}
	
	public int getPrice() {
		return price;
	}
}
