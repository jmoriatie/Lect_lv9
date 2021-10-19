package stage;

import unit.Player;
import unit.Unit;

public class StageBattle extends Stage{
	// 전투 관련
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean checkStun(Unit player) {
		boolean check = false;
		Player userUnit = (Player)player;
		if(userUnit.stun != 0) {
			userUnit.stun--;
			System.out.printf("[스턴 상태이상, 턴을 넘어갑니다][%d턴 남음]\n", userUnit.stun);
			check = true;
		}
		return check;
	}


}
