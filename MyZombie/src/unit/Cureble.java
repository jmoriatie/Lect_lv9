package unit;

public interface Cureble { // 치료 가능한 유닛
	final int HEAL = 100;
	public void cure(Cureble cureUnit); // 본 유닛만 치료 가능
}
