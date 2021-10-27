package omoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Timer extends JLabel implements Runnable {
	// 10초 주고 못두면 턴 넘어가도록
	// ㄴ 패널에서 컨트롤
	public int sec = 5;
	
	public Timer() {
		setBounds(20 ,200 ,200 ,20);
		setFont( new Font("", Font.BOLD, 14) );
		setText(String.format("[%2d초]", sec));
		setForeground(Color.black);
		setHorizontalAlignment(CENTER);
		setVisible(true);
	}

	@Override
	public void run() {
		while(true) {
			setText(String.format("[ %2d초 ]", sec));
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(Panel.play) {
				sec--;
				if(sec == 0) {
					sec = 5;
					changeAction();
				}
			}
		}
	}
	
	// 자동 말놓기
	// ㄴ 이런것들은 상위 클래스(Frame 같은..)에서 클래스 다 모아서 한꺼번에 처리하는
	// ㄴ 방법이 가장 나이스 할 것 같음
	public void changeAction() {
		Random r = new Random();
		while(true) {
			int rIdx1 = r.nextInt(10);
			int rIdx2 = r.nextInt(10);
			if(Panel.turn == 1 && Panel.bts[rIdx1][rIdx2].getBackground() == Color.DARK_GRAY) {
				Panel.bts[rIdx1][rIdx2].setBackground(Color.red);
				Panel.turn = 2;
				break;
				
			}
			else if(Panel.turn == 2 && Panel.bts[rIdx1][rIdx2].getBackground() == Color.DARK_GRAY) {
				Panel.bts[rIdx1][rIdx2].setBackground(Color.blue);
				Panel.turn = 1;
				break;
			}
		}
		Panel.con.printTurn(Panel.turnLabel, Panel.turn);
		int checkWinner = Panel.con.end(Panel.bts, Panel.turn);
		if(checkWinner != -1) {
			Panel.con.printWinner(Panel.printWin, checkWinner);
			Panel.play = false;
			Panel.start.setText("RESTART");
			System.out.println("게임 클리어");
		}
	}
}
