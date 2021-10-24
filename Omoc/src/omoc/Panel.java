package omoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener{

	Controller con;

	// 2차원 배열의 버튼 형식의 오목
	// 10*10
	
	boolean play = false;
	public static final int SIZE = 10;
	
	public int turn = 1;
	
	public JButton start;
	public JButton[][] bts;
	public JLabel printWin;

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
			this.play = true;
		}
		
		// 스타트 버튼
		start.setBounds(50, 100, 80, 50);
		start.setText("START");
		start.setFont( new Font("", Font.BOLD, 15) );
		start.setForeground(Color.black);
		start.setBackground(Color.GRAY);
		start.addActionListener(this);
		add(start);
		add( new Title() );
		setLabel();
		add(printWin);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp = (JButton)e.getSource();
		if(start == temp) {
			fieldInit();
			this.play = true;
			printWin.setText("");
		}
		
		if(this.play) {
			this.start.setText("RESTART");
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					// 색이 그레이면 바꿔줌, 중복방지
					if(bts[i][j] == temp && bts[i][j].getBackground() == Color.DARK_GRAY) {
						if(this.turn == 1) {
							bts[i][j].setBackground(Color.red);
							this.turn = 2;
						}
						else if(this.turn == 2) {
							bts[i][j].setBackground(Color.blue);
							this.turn = 1;
						}
					}
				}
			}
			
			// controller에서 일정 부분들 가져옴
			// ㄴ end() 는 int winner를 반환
			// ㄴ printWinner는 라벨을 받아서 출력해줌
			// ㄴ fieldInit으로 필드 초기화
			int checkWinner = con.end(bts, turn);
			if(checkWinner != -1) {
				con.printWinner(printWin, checkWinner);
				this.play = false;
				this.start.setText("RESTART");
				System.out.println("게임 클리어");
			}
		}
		else {
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					if(bts[i][j] == temp) {
						con.printRestart(printWin);
//						this.printWin.setText("RESTART");
					}
				}
			}
		}
	}
	
	// 필드만 재시작해주는 메서드
	public void fieldInit() {
		this.turn = 1;
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
	}
}
