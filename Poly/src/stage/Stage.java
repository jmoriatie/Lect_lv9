package stage;

public abstract class Stage {
	abstract public void init(); // 자체 초기화
	abstract public boolean update(); // 스테이지 업데이트(통합 기능부분)
}
