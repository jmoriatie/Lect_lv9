package unit;

public class UnitBat extends Unit implements Monster{
	
	public UnitBat() {
		this.name = "박쥐";
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
		userUnit.stopSkill = 3;
		System.out.printf("[%s]침묵스킬 시전, [%d] 3턴간 스킬 사용 불가\n", this.name, userUnit.name);
	}
	
	@Override
	public void init(int maxHp, int power) {
		super.init(maxHp, power);
	}
}
