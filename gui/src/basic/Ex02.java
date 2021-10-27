package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// 최상위 컨테이너 : JFrame
// 컨테이너 : JPanel
// 컴포넌트 : JButton, JLabel, JTextField ...
// 리스너 : 컴포넌트에 리스너를 add하면, 이벤트 발생 시 -> 캐치 -> 처리

class Tictactoe extends JFrame{
	public Tictactoe() {
		super("TITLE NAME");
		
		setLayout(null);
		setBounds(100, 100, 800, 800);
		
		// 요걸 안쓰면 쓰레드가 종료되는 것이 아님
		// ㄴ 따라서, 필요시에 알람같은걸로 새 창을 띄우는 역할로도 사용 가능
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 간단 메세지를 띄우고 싶을 때
		// ㄴ 만들어쓰는걸 우선하며, 에러사항정도 간단하게 띄울 때만 사용
		JOptionPane.showMessageDialog(null, String.format("간단한 %s 를 출력합니다","메세지"));
		
		setBackground(Color.pink);
		
		// RGB 따와서 사용(색상 사이트 이용)
		setBackground( new Color(000, 000, 000));
		
		add( new Content() );
		
		setVisible(true);
		revalidate(); // 갱신 
	}
}

class Content extends JPanel implements ActionListener{
	
	private JButton[] map;
	private int[] mark;
	
	private int turn = 1;
	private int win;
	
	public Content() {
		setLayout(null);
		setBounds(0, 0, 800, 800);
		setBackground(Color.white);
		
		setHeader();
		setMap();
	}
	
	private void setHeader() {
		JLabel head = new JLabel("TicTecToe");
		head.setBounds(0,0, 800, 200);
		head.setFont(new Font("폰트이름", Font.BOLD, 40));
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setVerticalAlignment(JLabel.BOTTOM); // 설정한 200라인까지 내려오는거
		add(head);
	}

	private void setMap() {
		this.map = new JButton[9];
		this.mark = new int[9];
		
		int x = 800/2 - 300/2; // center에 맞추기 위해 (스타트 지점)
		int y = 800/2 - 300/2; // 어떻게 계산된건지
		for(int i=0; i<this.map.length; i++) {
			this.map[i] = new JButton();
			this.map[i].setBounds(x, y, 100, 100);
			this.map[i].setBackground(Color.DARK_GRAY);
			
			this.map[i].addActionListener(this);
			
			// on mac
			this.map[i].setOpaque(true); // 투명도
			this.map[i].setBorderPainted(false);
			
			
			add(this.map[i]);
			x+=100 + 1;
			if(i % 3 == 2) {
				x = 800/2 - 300/2;
				y += 100 +1;
			}
		
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		
		for(int i=0; i<this.map.length; i++) {
			if(target == this.map[i] && this.mark[i] == 0) {
				if(this.turn == 1) {
					target.setBackground(Color.black);
				}
				else {
					target.setBackground(Color.orange);
				}
				
				this.mark[i] = this.turn;
				checkWin();
				
				this.turn = this.turn == 1 ? 2:1;
			}
		}
		
	}

	

	private void checkWin() {
		this.win = this.win == 0 ? checkHori() : this.win;
		this.win = this.win == 0 ? checkVerti() : this.win;
		this.win = this.win == 0 ? checkDia() : this.win;
		this.win = this.win == 0 ? checkReverse() : this.win;
	}
	
	private int checkHori() {
		
		return 0;
	}
	
	private int checkVerti() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int checkDia() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int checkReverse() {
		// TODO Auto-generated method stub
		return 0;
	}
}

public class Ex02 {
	public static void main(String[] args) {
		Tictactoe ttt = new Tictactoe();
	}
}
