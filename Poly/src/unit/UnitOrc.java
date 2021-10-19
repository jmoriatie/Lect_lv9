package unit;

public class UnitOrc extends Unit implements Monster{

	public UnitOrc() {
		this.name = "오크";
	}
	
	@Override
	public void attack(Unit target) {
		super.attack(target);
	}
	
	@Override
	public void printUnit() {
		super.printUnit();
	}
	
	public void skill(Unit unit) {
		super.skill(unit);
		Player userUnit = (Player)unit;
		userUnit.stun = 1;
		System.out.printf("[%s]스턴시전, [%s] 1턴간 행동불가\n", this.name, userUnit.name);
	}
	
	@Override
	public void init(int maxHp, int power) {
		super.init(maxHp, power);
	}
	
}
