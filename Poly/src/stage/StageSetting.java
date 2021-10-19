package stage;

import java.util.HashMap;
import java.util.Map;

public class StageSetting extends Stage {
	// 셋팅 실제로 바꿔주는 매니저 역할
	// 스테이지 생성 해주는장소 HashMap
	// ㄴ 여기서 주면, 로비에서 바꿔서 돌려줘, 그럼 거기로 이동
	// ㄴ 다른 스테이지도 여기로 이동(스테이지 바꿔줌)
	
	private static StageSetting instance = new StageSetting();
	
	private Map<String, Stage> stage = new HashMap<String, Stage>();
	
	public String curStage = "";
	public String nextStage = "";
	
	private StageSetting() {
		stage.put("Title", new StageTitle());
		stage.put("Battle", new StageBattle());
		stage.put("Lobby", new StageLobby());
		
		curStage = "Title";
	}
	
	public static StageSetting getInstance() {
		return instance;
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
		Stage tmpStage = update();
	}
	
	
}
