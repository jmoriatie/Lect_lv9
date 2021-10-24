package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel implements ActionListener{
	
	public JButton start = new JButton();
	public JButton[] bts = new JButton[9]; 
	public JLabel finish = new JLabel();

	
	private final int SIZE = 50;
	
	private boolean run = false;
	private int turn = 1;
	
	public Panel() {
		setLayout(null);
		setBounds(0, 0, Frame.W,Frame.H);
		setBackground(Color.gray);
		setVisible(true);
		start.setBounds(Frame.W-370, Frame.H-350, 80, 40);
		start.addActionListener(this);
	
		start.setText("START");
		
		add(start);

//		start.setOpaque(true);
//		start.setBorderPainted(false);
		
		int x = 0;
		int y = 0;
		for(int i=0; i<bts.length; i++) {
			bts[i] = new JButton();
			bts[i].setBounds(Frame.W-400 + x, Frame.H-300 + y, SIZE, SIZE);
			bts[i].setOpaque(true);
			bts[i].setBorderPainted(false);
			bts[i].addActionListener(this);
			bts[i].setBackground(Color.white);	
			
			x += SIZE+1;
			if(i % 3 == 2) {
				x = 0;
				y += SIZE+1;
			}
			add(bts[i]);
		}
		add( new HeadTitle() );
		
		finish.setBounds(Frame.W-430 + x, Frame.H-240 + y, 200, 100);
		finish.setBackground(Color.gray);
		finish.setFont(new Font("", Font.BOLD, 15));
		finish.setForeground(Color.white);
		finish.setHorizontalAlignment(JLabel.CENTER);
		add(finish);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton)e.getSource();
		
		// 게임 진행
		if(run) {
			for(int i=0; i<bts.length; i++) {
				if(tmp == bts[i] && this.turn == 1) {
					if(bts[i].getBackground() == Color.white) {
						bts[i].setBackground(Color.blue);
					}
				}
				else if(tmp == bts[i] && this.turn == 2) {
					if(bts[i].getBackground() == Color.white) {
						bts[i].setBackground(Color.red);
					}
				}
			}
			control(tmp);
			if(end(bts)){
				init();
			}
		}
		
		// 스타트 == 초기화
		if(tmp == start) {
			init();
			start.setText("RESTART");
			finish.setText("");
		}
		
	}
	
	private void init() {
		this.turn = 1;
		this.run = true;
		for(int i=0; i<bts.length; i++) {
			bts[i].setBackground(Color.white);	
		}
	}
	
	private void control(JButton button) {
		if(this.turn == 1) this.turn++;
		else this.turn--;
	}
	
	private boolean end(JButton[] buttons) {
		boolean isEnd = false;
		
		int winner = this.turn == 1? 2:1;
		Color color = winner == 1? Color.blue : Color.red;
		
		// 가로
		int cnt;
		for(int i=0; i<bts.length; i+=3) {
			cnt = 0;
			for(int j=i; j<i+3; j++) {
				if(bts[j].getBackground() == color) {
					cnt++;	
				}
			}
			if(cnt == 3) {
				System.out.println("가로");
				isEnd = true;	
			}
		}
		
		// 세로
		for(int i=0; i<3; i++) {
			cnt = 0;
			for(int j=i; j<i+7; j+=3) {
				if(bts[j].getBackground() == color) {
					cnt++;	
				}
			}
			if(cnt == 3) {
				System.out.println("세로");
				isEnd = true;	
			}
		}
		
		// 왼오
		cnt = 0;
		for(int i=0; i<bts.length; i+=4) {
			if(bts[i].getBackground() == color) {
				cnt++;	
			}
		}
		if(cnt == 3) {
			System.out.println("왼오");
			isEnd = true;	
		}
	
		// 오왼 
		cnt = 0;
		for(int i=2; i<7; i+=2) {
			if(bts[i].getBackground() == color) {
				cnt++;	
			}
		}
		if(cnt == 3) {
			System.out.println("오왼");
			isEnd = true;	
		}
		
		// 종료
		if(isEnd) {
			System.out.println("플레이어" + winner + " 승리!");
			finish.setText("플레이어" + winner + " 승리!");
		}
		return isEnd;
	}
}
