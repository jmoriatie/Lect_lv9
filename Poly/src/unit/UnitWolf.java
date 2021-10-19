package unit;

public class UnitWolf extends Unit implements Monster{
	
	public UnitWolf() {
		this.name = "늑대";
	}
	
	@Override
	public void attack(Unit target) {
		super.attack(target);
	}
	
	@Override
	public void printUnit() {
		super.printUnit();
	}
	
	@Override
	public void skill(Unit unit) {
		super.skill(unit);
		unit.hp -= this.power * 2;
		System.out.printf("[%s]치명스킬, [%s] %d(%d*2)의 데미지를 입음\n", 
				this.name, unit.name, (this.power*2), this.power);
		checkTargetHp(unit);
	}
	
	@Override
	public void init(int maxHp, int power) {
		super.init(maxHp, power);
	}

}
