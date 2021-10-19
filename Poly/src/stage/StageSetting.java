package stage;

import java.util.HashMap;
import java.util.Map;

public class StageSetting extends Stage {
	// 스테이지 생성 해주는장소 HashMap

	private static StageSetting instance = new StageSetting();
	
	private Map<String, Stage> stages = new HashMap<String, Stage>();
	
	private StageSetting() {
	}
	
	public static StageSetting getInstance() {
		return instance;
	}
	
	@Override
	public void init() {
		stages.put("Title", new StageTitle());
		stages.put("Battle", new StageBattle());
		stages.put("Lobby", new StageLobby());
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 스테이지 이름 받아서 실제 스테이지 반환
	public Stage getStage(String stageName) {
		Stage stage = null;
//		System.out.println("스테이지반환성공!!!"); // 확인용
		stage = stages.get(stageName);
		
		return stage;
	}
	
}
