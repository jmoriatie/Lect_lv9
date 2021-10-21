package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
//import javax.swing.*; // swing이 가진 모든 클래스를 사용할 때 사용
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;


// GUI : Graphic User Interface
// ㄴ awt
// ㄴ swing : java 자체 사용 가벼움?

// swing package 안에 jframe
// JFrame : "컨테이너(Container)" 라고 부름
// ㄴ "컴포넌트(Component)"라고 부르는 요소들을 마치 스티커 처럼 붙여나갈 수 있음
// ㄴ add() 이용

// JFrame : 최상위 컨테이너
// JPanel : 컨테이너 <- 컨테이너(요소들을 붙여나가 add()하면서 완성함)
// JButton, JTextField, JLabel, JCheckBox ... 

class MyFrame extends JFrame{
	
	// awt활용한 것 => 사용자가 사용하는 스크린의 픽셀 사이즈 가져와줌
	// ㄴ Dimension? 사용자의 환경을 가져와주는 역할인것인가?
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = dm.width;
	private int heigth = dm.height;
	private final int W = 800;
	private final int H = 600;
			
			
	public MyFrame(String title) {
		// 부모 생성자를 활용한 프레임의 제목 설정(JFrame 내부 생성자)
		super(title); //  타이틀 만드는 것
//		setTitle(title); // 슈퍼생성자 없이도 해당 메서드로 사용 가능

		// 디폴트 레이아웃 구성을 삭제하는 메서드
		setLayout(null);
		
		// ---프레임 크기 설정---
		// setBounds(x, y, width, heigth) => 가로좌표, 세로좌표, 창넓이, 창크기
//		setBounds(100,100,800,600);
		// Dimension활용하여 화면 사용자의 스크린 중앙에 좌표잡기
		setBounds(this.width/2 - W/2, this.heigth/2 - H/2 ,W, H); 
		
		// 프레임의 종료 연산(명령)을 결정 >> (생략시, 프레임은 사라지지만 스레드가 종료되지X)
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 엑박 누르면 같이 종료되는 것
		// 프레임을 눈에 보이게 설정
		setVisible(true);
		
		// --- Panel 추가(2가지) ---
//		add(new MyPanel()); // 패널을 더해줌
		this.setContentPane(new MyPanel()); // add()와 같음
	}
}

// 컴포넌트 역할
// ㄴ Frame 위에 그림그리고 글쓴다고 생각하면 될 듯 => 그 중 하나인 Panel
// ActionListener : 귀를 기울이고 있다가, 반응이 있으면 캐치를 해서 실행(버튼 등의 반응)
class MyPanel extends JPanel implements ActionListener {

	// 버튼만들기
	// JButton
	JButton button = new JButton();
	
	// 버튼연습
	// 정방형으로 3*3 버튼의 나열
	final int SIZE = 50;
	JButton[] mapButton = new JButton[9];
	JTextField text = new JTextField();
	
	public MyPanel() {
		setBounds(0, 0,600,400);
		setBackground(Color.orange);
		// 디폴트 레이아웃 구성을 삭제하는 메서드
		setLayout(null);
		
//		this.button.setBounds(50,50,50,50);
		this.setVisible(true); // 패널 전체 보이게 해주기
		
//		add(this.button); // add() 꼭 해주기 => 단일버튼

		// map의 각 버튼 셋팅
		// 모두 -> panel 에 add
		int x = 0;
		int y = 0;
		for(int i=0; i<mapButton.length; i++) {
			mapButton[i] = new JButton();
			this.mapButton[i].setBounds(x, y, SIZE, SIZE);
			
			this.mapButton[i].setOpaque(true); // 투명도
			this.mapButton[i].setBorderPainted(false); // 외곽 없애기
			
			// ActionListener의 기능
			this.mapButton[i].addActionListener(this); // 특정 버튼에 액션(센서역할)을 달아주는 것
			add(this.mapButton[i]);
			
			
			x += SIZE+1; // 좌표 구분을 위해 띄어주기
			if(i % 3 == 2) {
				y += SIZE+1;
				x = 0;
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 액션을 처리할 수 있는 메서드 (센서를 달아준 객체에 대해... Button 등)
		JButton temp = (JButton)e.getSource(); // e.getSource() -> 이벤트가 발생한 객체를 가져옴 
		for(int i=0; i<mapButton.length; i++) {
			if(this.mapButton[i] == temp) {
				System.out.println(i + "번째 인덱스"); // 콘솔에 액션이 발생한 인덱스를 출력
				mapButton[i].setBackground(Color.blue);
			}
		}
		
	}
}

public class Ex_jframe {
	public static void main(String[] args) {
		MyFrame frame = new MyFrame("Greed IT");
	}
}
