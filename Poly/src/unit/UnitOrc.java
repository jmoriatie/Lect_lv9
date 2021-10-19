package unit;

public class UnitOrc extends Unit implements Monster{

	public UnitOrc() {
		this.name = "오크";
	}
	
	@Override
	protected void attack(Unit target) {
		super.attack(target);
	}
	
	@Override
	protected void printUnit() {
		super.printUnit();
	}
	
	public void skill(Unit unit) {
		Player userUnit = (Player)unit;
		userUnit.stun = 3;
		System.out.printf("[%s]스턴시전, [%d] 2턴간 행동불가\n", this.name, userUnit.name);
	}
	
	@Override
	public void init(int maxHp, int power) {
		super.init(maxHp, power);
	}
}
