package main;

import stage.Stage;
import stage.StageLobby;
import stage.StageSetting;
import unit.UnitManager;

public class GameManager {
	// 스테이지 바꿔주는 매니저
	// ㄴ 여기서 주면, 로비에서 바꿔서 돌려줘, 그럼 거기로 이동
	// ㄴ 다른 스테이지도 여기로 이동(스테이지 바꿔줌)	// 이동
	
	// 싱글톤
	private static GameManager instance = new GameManager();
		
	// 가져다 쓸 인스턴스
	private StageSetting ss = StageSetting.getInstance();
	private UnitManager i = UnitManager.getInstance();

	private StageLobby sl = new StageLobby();
	
//	i.tempCheck();
	
	public String curStage = "";
	public String nextStage = "";
	

	private GameManager() {
		ss.init();
		nextStage = "Title"; // 일단 타이틀 부터 들어가
	}
	
	public static GameManager getInstance() {
		return instance;
	}
	
	public boolean stageChange() {
		// --- 확인부 ---
		if(this.nextStage.equals(this.curStage)) return true; // 그대로 유지

		// --- 실행부 ---
		Stage stage = ss.getStage(this.nextStage); // 스테이지 받음

		// 받아온 스테이지 컨트롤
		if(stage != null) {
			stage.init();
			stage.update(); // boolean 값
		}

		// --- 종료부 ---
		if(this.nextStage.equals("")) return false; // 종료

		this.curStage = this.nextStage; // 다음 스테이지 현재로 만들어주고
		this.nextStage = sl.whichStage(); // 다음 스테이지 변경해주고
		
		return true; // default 값
	}
	


}
