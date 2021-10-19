package stage;

import java.util.Scanner;

import main.GameManager;

public class StageTitle extends Stage{
	// 진입부
	
	Scanner sc = new Scanner(System.in);
	
	@Override
	public void init() {
		while(true) {
			System.out.println("===== RPG 게임 =====");
			System.out.println("시작을 원하시면 [시작], 종료를 원하시면 [종료]를 입력하세요");
			String select = sc.next(); 
			if("시작".equals(select)) {
				GameManager.getInstance().nextStage = "Lobby";
				break;
			}
			else if("종료".equals(select)) {
				GameManager.getInstance().nextStage = "";
				break;
			}
		}
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return true;
	}
}
