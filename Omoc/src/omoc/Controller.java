package omoc;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Controller{
	
	
	public int end(JButton[][] buttons, int turn){
		int check = -1;
		int winner = turn == 1? 2:1;
		Color col = winner == 1? Color.red: Color.blue;
		
		JButton tmp[][] = buttons; // 확인해가지고 winner

		// 가로
		for(int i=0; i<Panel.SIZE; i++) {
			int cnt = 0;
			for(int j=0; j<Panel.SIZE; j++) {
				if(tmp[i][j].getBackground() == col) {
					cnt++;
				}
				else {
					cnt=0;
				}
				if(cnt == 5) {
					System.out.println("가로");
					check = winner;
					break;
				}
			}
		}
		
		// 세로
		for(int i=0; i<Panel.SIZE; i++) {
			int cnt = 0;
			for(int j=0; j<Panel.SIZE; j++) {
				if(tmp[j][i].getBackground() == col) {
					cnt++;
				}
				else {
					cnt=0;
				}
				if(cnt == 5) {
					System.out.println("세로");
					check = winner;
					break;
				}
			}
		}
		
		// 왼오(아래쪽, 사선기준)
		for(int i=0; i<Panel.SIZE; i++) {
			int cnt = 0;
			for(int j=0; j<Panel.SIZE; j++) {
				if(tmp[i+j][j].getBackground() == col) {
//					System.out.println((i+j)+"/"+j+" p"+winner);
					cnt++;
				}
				else {
					cnt=0;
				}
				if(cnt == 5) {
					System.out.println("왼오_중간아래");
					check = winner;
					break;
				}
				if(i+j == Panel.SIZE-1) break;
			}
		}
		// 왼오(위쪽)
		for(int i=1; i<Panel.SIZE; i++) {
			int cnt = 0;
			for(int j=0; j<Panel.SIZE; j++) {
				if(tmp[j][i+j].getBackground() == col) {
//					System.out.println(j+"/"+(i+j)+" p"+winner);
					cnt++;
				}
				else {
					cnt=0;
				}
				if(cnt == 5) {
					System.out.println("왼오_위");
					check = winner;
					break;
				}
				if(i+j == Panel.SIZE-1) break;
			}
		}
		
		// 오왼_위
		for(int i=Panel.SIZE-2; i>3; i--) {
			int n=0;
			int cnt = 0;
			for(int j=0; j<i+1; j++) {
				if(tmp[j][i-n].getBackground() == col) {
//					System.out.println(j+"/"+(i-j)+" p"+winner);
					cnt++;
				}
				else {
					cnt=0;
				}
				if(cnt == 5) {
					System.out.println("오목!! 오왼_위");
					check = winner;
					break;
				}
				else n++;
			}
		}
		
		// 오왼_중간, 아래
		for(int i=0; i<6; i++) {
			int n = 0;
			int cnt = 0;
			for(int j=Panel.SIZE-1; j>=i; j--) {
				if(tmp[i+n][j].getBackground() == col) {
//					System.out.println((i+n)+"/"+j+" p"+winner);
					cnt++;
				}
				else {
					cnt=0;
				}
				if(cnt == 5) {
					System.out.println("오목!! 오왼_아래");
					check = winner;
					break;
				}
				else n++;
			}
		}
		return check;
	}
	
	
	public void printWinner(JLabel printWinLabel, int checkWinner) {
		printWinLabel.setText("[플레이어"+ checkWinner+ " 승리]");
	}
	
	
	public void printRestart(JLabel printWinLabel) {
		printWinLabel.setText("[RESTART 버튼을 클릭하세요]");
	}
	
	public void printStart(JLabel printWinLabel) {
		printWinLabel.setText("[START 버튼을 클릭하세요]");
	}
	
	public void printTurn(JLabel turnLabel, int turn) {
		turnLabel.setText("Player"+turn+" turn >>");
	}
	
}
