package omoc_Controller;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import omoc_Source.Nemo;

public class Controller{
	// 끝나면 play = false
	public int end(Nemo[][] stones, int turn){
		int size = stones.length;  // 어차피 10
		int check = -1;
		int winner = turn == 1? 2:1;
		
		Nemo tmp[][] = stones;

		// 가로
		for(int i=0; i<size; i++) {
			int cnt = 0;
			for(int j=0; j<size; j++) {
				if(tmp[i][j].id == winner) {
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
		for(int i=0; i<size; i++) {
			int cnt = 0;
			for(int j=0; j<size; j++) {
				if(tmp[j][i].id == winner) {
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
		for(int i=0; i<size; i++) {
			int cnt = 0;
			for(int j=0; j<size; j++) {
				if(tmp[i+j][j].id == winner) {
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
				if(i+j == size-1) break;
			}
		}
		// 왼오(위쪽)
		for(int i=1; i<size; i++) {
			int cnt = 0;
			for(int j=0; j<size; j++) {
				if(tmp[j][i+j].id == winner) {
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
				if(i+j == size-1) break;
			}
		}
		
		// 오왼_위
		for(int i=size-2; i>3; i--) {
			int n=0;
			int cnt = 0;
			for(int j=0; j<i+1; j++) {
				if(tmp[j][i-n].id == winner) {
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
			for(int j=size-1; j>=i; j--) {
				if(tmp[i+n][j].id == winner) {
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
}
