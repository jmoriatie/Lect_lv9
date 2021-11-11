package sokoban;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import data.Data;

public class Controller {
	private Random rn = new Random();
	
	static ArrayList<ArrayList<Data>> map;
	static boolean[][] goals;
	
	// 박스, 벽, 골대 숫자
	// ㄴ BOXCNT = 골대 숫자로도 쓰자, 
	public final int BOXCNT = 3; // goalCnt와 동일
	public final int WALLCNT = 15;
	
	// 배열 수 조정 (현재 크기 50*50)
	public final int GARO = 8;
	public final int SERO = 9;
	
	// 이미지
	public Image field;
	public Image wall;
	public Image player;
	public Image box;
	public Image goal;
	public Image finishBox;
	
	// 플레이어 y, x
	public int s;
	public int g;
	
	public int finishCnt;
	
	public void setImage() {
		field = new ImageIcon("images/tile1.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		wall = new ImageIcon("images/tile2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		player = new ImageIcon("images/tile3.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		box = new ImageIcon("images/tile4.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		goal = new ImageIcon("images/tile5.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		finishBox = new ImageIcon("images/tile6.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	}	
	
	public void setMap() {
		map = new ArrayList<ArrayList<Data>>();
		goals = new boolean[SERO][GARO];
		finishCnt = this.BOXCNT;

		// 초기화: 맵 크기 셋팅
		int x = 0;
		int y = 0;
		for(int i=0; i<SERO; i++) { // 세로
			map.add( new ArrayList<Data>() );
			for(int j=0; j<GARO; j++) { // 세로
				Data temp = new Data(x, y, 50, 50);
				map.get(i).add(temp);
				x+=50;
			}
			x=0;
			y+=50;
		}
		int checkN = 0;
		int rIdx1 = 0;
		int rIdx2 = 0;
		
		System.out.println("[[[ SETTING ]]]");
		// 벽셋팅
		for(int i=0; i<WALLCNT; i++) {
			rIdx1 = rn.nextInt(SERO);
			rIdx2 = rn.nextInt(GARO);
			if(map.get(rIdx1).get(rIdx2).getName().equals("")) {
				map.get(rIdx1).get(rIdx2).setName("wall");
				map.get(rIdx1).get(rIdx2).setImage(wall);
				checkN++;
			}else i--;
		}
		System.out.println("[ 벽: " + checkN + "개 ]");
		checkN = 0;
		
		// 박스 셋팅
		for(int i=0; i<BOXCNT; i++) {
			rIdx1 = rn.nextInt(SERO-2)+1;
			rIdx2 = rn.nextInt(GARO-2)+1;
			// 사방검사 => 옆에 붙은 벽 1개까지만 허용
			int wallCnt = checkWall(rIdx1, rIdx2);

			if(wallCnt < 2 && map.get(rIdx1).get(rIdx2).getName().equals("") ) {
				map.get(rIdx1).get(rIdx2).setName("box");
				map.get(rIdx1).get(rIdx2).setImage(box);		
				checkN++;
			}else i--;
		}
		
		System.out.println("[ 상자: " + checkN + "개 ]");
		checkN = 0;
		
		// 플레이어 셋팅
		for(int i=0; i<1; i++) {
			rIdx1 = rn.nextInt(SERO-2)+1;
			rIdx2 = rn.nextInt(GARO-2)+1;
			
			// 사방검사 => 옆에 붙은 벽 1개까지만 허용
			int wallCnt = checkWall(rIdx1, rIdx2);
			if(wallCnt < 2 && map.get(rIdx1).get(rIdx2).getName().equals("")) {
				map.get(rIdx1).get(rIdx2).setName("player");
				map.get(rIdx1).get(rIdx2).setImage(player);	
				this.s = rIdx1;
				this.g = rIdx2;
				checkN++;
			}else i--;
		}
		
		System.out.println("[ 플레이어: " + checkN + "명 ]");
		checkN = 0;
		
		// 골 셋팅
		for(int i=0; i<BOXCNT; i++) {
			rIdx1 = rn.nextInt(SERO-2)+1;
			rIdx2 = rn.nextInt(GARO-2)+1;
			
			// 사방검사 => 옆에 붙은 벽 1개까지만 허용
			int wallCnt = checkWall(rIdx1, rIdx2);
			
			if(wallCnt < 2 && map.get(rIdx1).get(rIdx2).getName().equals("")) {
				map.get(rIdx1).get(rIdx2).setName("goal");
				map.get(rIdx1).get(rIdx2).setImage(goal);
				goals[rIdx1][rIdx2] = true; // 해당 인덱스 true
				checkN++;
			}else i--;
		}
		System.out.println("[ 골대: " + checkN + "개 ]");
		checkN = 0;
		
		// 필드셋팅
		int fieldCnt = (GARO*SERO) - (BOXCNT*2) - WALLCNT - 1; // 전체크기-(박스+골)-벽-플레이어
		for(int i=0; i<fieldCnt; i++) {
			rIdx1 = rn.nextInt(SERO);
			rIdx2 = rn.nextInt(GARO);
			if(map.get(rIdx1).get(rIdx2).getName().equals("")) {
				map.get(rIdx1).get(rIdx2).setName("field");
				map.get(rIdx1).get(rIdx2).setImage(field);
				checkN++;
			}else i--;
		}
		System.out.println("[ 필드: " + checkN + "개 ]");
		System.out.println("----------------");
	}
	
	private int checkWall(int rIdx1, int rIdx2) {
		int wallCnt = 0;
		if(map.get(rIdx1-1).get(rIdx2).getName().equals("wall")) wallCnt++;
		if(map.get(rIdx1+1).get(rIdx2).getName().equals("wall")) wallCnt++;
		if(map.get(rIdx1).get(rIdx2-1).getName().equals("wall")) wallCnt++;
		if(map.get(rIdx1).get(rIdx2+1).getName().equals("wall")) wallCnt++;
		return wallCnt;
	}
	
	public void up() {
		Data cur = map.get(s).get(g);
		Data fro;
		if(s-1 >= 0){ // 뺀값 아웃바운드
			fro = map.get(s-1).get(g); // 바꿀자리 담아놓고
			if(!fro.getName().equals("wall") && (fro.getName().equals("field") || fro.getName().equals("goal"))) { // 벽아닐 때만
				this.movePlayer(cur, fro);
				s--;
				System.out.println("위로 이동");
			}
			// 박스고, 박스이동지점이 0보다 크같, 박스이동지점이 필드일 경우만
			else if(s-2 >= 0 && (fro.getName().equals("box")) || fro.getName().equals("finishBox")) {
				Data fro2 = map.get(s-2).get(g);
				if(this.moveBox(cur, fro, fro2)) {
					s--;
					System.out.println("위쪽으로 박스이동");
				}
			}
		}
	}
	public void down() {
		Data cur = map.get(s).get(g);
		Data fro;
		if(s+1 < SERO){ // 뺀값 아웃바운드
			fro = map.get(s+1).get(g); // 바꿀자리 담아놓고
			if(!fro.getName().equals("wall") && (fro.getName().equals("field") || fro.getName().equals("goal"))) { // 벽아닐 때만
				this.movePlayer(cur, fro);
				s++;
				System.out.println("아래쪽으로 이동");
			}
			// 박스고, 박스이동지점이 0보다 크같, 박스이동지점이 필드일 경우만
			else if(s+2 < SERO && (fro.getName().equals("box")) || fro.getName().equals("finishBox")) {
				Data fro2 = map.get(s+2).get(g);
				if(this.moveBox(cur, fro, fro2)) {
					s++;
					System.out.println("아래쪽으로 박스이동");
				}
			}
		}
	}
	public void left() {
		Data cur = map.get(s).get(g);
		Data fro;
		if(g-1 >= 0){ // 뺀값 아웃바운드
			fro = map.get(s).get(g-1); // 바꿀자리 담아놓고
			if(!fro.getName().equals("wall") && (fro.getName().equals("field") || fro.getName().equals("goal"))) { // 벽아닐 때만
				this.movePlayer(cur, fro);
				g--;
				System.out.println("왼쪽으로 이동");
			}
			// 박스고, 박스이동지점이 0보다 크같, 박스이동지점이 필드일 경우만
			else if(g-2 >= 0 && (fro.getName().equals("box")) || fro.getName().equals("finishBox")) {
				Data fro2 = map.get(s).get(g-2);
				if(this.moveBox(cur, fro, fro2)) {
					g--;
					System.out.println("왼쪽으로 박스이동");
				}
			}
		}
	}
	public void right() {
		Data cur = map.get(s).get(g);
		Data fro;
		if(g+1 < GARO){ // 뺀값 아웃바운드
			fro = map.get(s).get(g+1); // 바꿀자리 담아놓고
			if(!fro.getName().equals("wall") && (fro.getName().equals("field") || fro.getName().equals("goal")) ) { // 벽아닐 때만
				this.movePlayer(cur, fro);
				g++;
				System.out.println("오른쪽으로 이동");
			}
			// 박스고, 박스이동지점이 0보다 크같, 박스이동지점이 필드일 경우만
			else if(g+2 < GARO && (fro.getName().equals("box")) || fro.getName().equals("finishBox") ) {
				Data fro2 = map.get(s).get(g+2);
				if(this.moveBox(cur, fro, fro2)) {
					g++;
					System.out.println("오른쪽으로 박스이동");
				}
			}
		}
	}
	
	public void movePlayer(Data cur, Data fro) {
		// 이미지, 이름만 변경
		fro.setName(cur.getName());
		fro.setImage(player);
		cur.setName("field");
		cur.setImage(field);
	}
	public boolean moveBox(Data cur, Data fro, Data fro2) {
		boolean check = false; // 이동가능 체크
		if(fro2.getName().equals("field") || fro2.getName().equals("goal")) { // 필드,골대일 때만 이동
			if(fro2.getName().equals("field")) {
				fro2.setName("box");
				fro2.setImage(box);
			}
			else if(fro2.getName().equals("goal")) {
				fro2.setName("finishBox");
				fro2.setImage(finishBox);
				System.out.println("[[[ GOALIN ]]]");
			}
			fro.setName("player");
			fro.setImage(player);
			cur.setName("field");
			cur.setImage(field);
			check = true;
		}
		return check;
	}
	
	public void goalControl() {
		// goals 골 별도저장 배열
		// 플레이어 좌표가 같을 때, 박스(피니쉬박스)아닐때
//		int finishCheck = this.BOXCNT;
		finishCnt = this.BOXCNT;
		for(int i=0; i<SERO; i++) {
			for(int j=0; j<GARO; j++) {
				if(goals[i][j]) {
					Data temp = map.get(i).get(j);
					// 골대 원상복귀
					if(!(i == this.s && j == this.g) && 
							!temp.getName().equals("finishBox") && 
							!temp.getName().equals("box")) {
						temp.setName("goal");
						temp.setImage(goal);
					}
					// 종료체크
					if(temp.getName().equals("finishBox")) {
						finishCnt--;
					}
				}
			}
		}
		// 종료체크
		if(finishCnt == 0) {	// BOXCNT == GOALCNT
			System.out.println("[[[ CLEAR ]]]");
			InGame.run = false;
		}else {
		}
	}
	
	public void reset() {
		System.out.println("[[[ RESET ]]]");
		map.clear();
		goals = new boolean[SERO][GARO];
		setMap();
		InGame.time = 0;
		InGame.run = true;
	}
}
