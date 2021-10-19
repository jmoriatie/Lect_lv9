package stage;

import java.util.Scanner;

import main.GameManager;

public class StageLobby extends Stage{
	// 받아서 스테이지 이동해주는 역할 (메인메뉴)
	
	private Scanner sc = new Scanner(System.in);
	
	@Override
	public void init() {

	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String whichStage() {
		// String stageName 반환
		System.out.println("======= 로비 =======");
		System.out.print("[1]전투 [2]종료: ");
		int sel = select(sc.next()); 
		String nextStage = "";
				
		if(sel == 1) { // 전투
			nextStage = "Battle";
		}
		else if(sel == 2) { 
			nextStage = "";  // 종료도 될 수 있고, 미정이 될 수도 있고 
		}
		GameManager.getInstance().nextStage = nextStage; // 넥스트 스테이지 바꿔주고
		return nextStage;
	}
	
	private int select(String number) {
		int num = -1;
		try {
			num = Integer.parseInt(number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
}
