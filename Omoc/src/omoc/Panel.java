package omoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener{

	static Controller con;

	// 2차원 배열의 버튼 형식의 오목
	// 10*10
	
	static boolean play = false;
	public static final int SIZE = 10;
	
	static int p1Score;
	static int p2Score;
	
	static int turn = 1;
	
	static JButton start;
	static JButton[][] bts;
	static JLabel turnLabel;
	static JLabel printWin;
	static JLabel score;
	
	Timer timer;
	
	public Panel() {
		// 프레임 셋팅 
		con = new Controller();
		setLayout(null);
		setBounds(0, 0, 800 , 530);
		setBackground(Color.white);
		setVisible(true);
		
		// 올릴 것들 셋팅
		start = new JButton();
		bts = new JButton[SIZE][SIZE];
		printWin = new JLabel(); 
		turnLabel = new JLabel();
		timer = new Timer();
		score = new Score();
		
		init();
	}
	
	public void init() {
		// 버튼 초기화
		int x = 0;
		int y = 0;
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				bts[i][j] = new JButton();
				bts[i][j].setBounds(x+280, y+10, 50, 50);
				bts[i][j].setBackground(Color.DARK_GRAY);
				
				bts[i][j].setOpaque(true);
				bts[i][j].setBorderPainted(false);
				
				bts[i][j].addActionListener(this);
				
				add(bts[i][j]);
				x+=51;
			}
			x=0;
			y+=51;
			this.play = false;
		}
		
		// 스타트 버튼
		start.setBounds(50, 100, 80, 50);
		start.setText("START");
		start.setFont( new Font("", Font.BOLD, 15) );
		start.setForeground(Color.black);
		start.setBackground(Color.GRAY);
		start.addActionListener(this);
		setLabel();
		
		add(start);
		// 부수적인 것들
		add( new Title() );
		add(timer); // 마지막에 붙여 넣는다
		add(turnLabel);
		add(printWin);
		add(score);
		
		p1Score = 0;
		p2Score = 0;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp = (JButton)e.getSource();
				
		if(start == temp) {
			fieldInit();
			this.play = true;
			printWin.setText("");
			timer.sec = 5;
		}
		
		if(this.play) {
			this.start.setText("RESTART");
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					// 색이 그레이면 바꿔줌, 중복방지
					if(bts[i][j] == temp && bts[i][j].getBackground() == Color.DARK_GRAY) {
						if(this.turn == 1) {
							bts[i][j].setBackground(Color.red);
							p1Score += 10;
							this.turn = 2;
						}
						else if(this.turn == 2) {
							bts[i][j].setBackground(Color.blue);
							p2Score += 10;
							this.turn = 1;
						}
						timer.sec = 5;
					}
				}
			}
			
			// controller에서 일정 부분들 가져옴
			// ㄴ end() 는 int winner를 반환
			// ㄴ printWinner는 라벨을 받아서 출력해줌
			// ㄴ fieldInit으로 필드 초기화
			score.setText(String.format("[p1: %2d점 // p2: %2d점]", p1Score, p2Score));
			int checkWinner = con.end(bts, turn);
			if(checkWinner != -1) {
				con.printWinner(printWin, checkWinner);
				this.play = false;
				this.start.setText("RESTART");
				System.out.println("게임 클리어");
			}
		
			// 턴 표시
			con.printTurn(turnLabel, turn);
		}
		else if(!this.play && this.start.getText().equals("START")) {
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					if(bts[i][j] == temp) {
						con.printStart(printWin);
					}
				}
			}
		}
		else {
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					if(bts[i][j] == temp) {
						con.printRestart(printWin);
					}
				}
			}
		}
	}
	
	// 필드만 재시작해주는 메서드
	public void fieldInit() {
		this.turn = 1;
		p1Score = 0;
		p2Score = 0;
		score.setText(String.format("[p1: 0점 // p2: 0점]"));
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				bts[i][j].setBackground(Color.DARK_GRAY);
			}
		}
	}
	
	private void setLabel() {
		printWin.setBounds(50 ,400 ,250 ,20);
		printWin.setFont( new Font("", Font.BOLD, 15) );
		printWin.setForeground(Color.black);
		printWin.setVisible(true);
		
		turnLabel.setBounds(50 ,370 ,250 ,20);
		turnLabel.setFont( new Font("", Font.BOLD, 15) );
		turnLabel.setForeground(Color.PINK);
		turnLabel.setVisible(true);
		// 최초 셋팅
		con.printTurn(turnLabel, this.turn);
		con.printStart(printWin);
	}
	
	public static JButton[][] getButtons(){
		return bts;
	}
}
