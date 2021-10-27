package oneToFifty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


// 게임 프레임 클래스
class OneFrame extends JFrame{

	OnePanel o = new OnePanel();
	
	public OneFrame() {
		super("OneToFifty");
		
		setLayout(null);
		setBounds(100, 100, 800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.white);
		
		add(o);
		multieThread();
		
		setVisible(true);
		revalidate();
	}
	
//	public void setTimer(int m, int s) {
//		o.setTimer(m, s);
//	}
	
	public void multieThread() {
		Thread t = new Thread(o);
		t.start();
	}
	
}

// 게임패널 클래스
class OnePanel extends JPanel implements ActionListener, Runnable{
		
	JButton[] map;
	JButton restart;
	int[] fir;
	int[] sec;
	boolean[] check;
	JLabel title;
	JLabel numLabel;
	JLabel time;
		
	int m;
	int s;
	
	private int gameNum;
	private boolean timeRun;
	
	public OnePanel(){
		setLayout(null);
		setBounds(0, 0, 800, 800);
		setBackground(new Color(255, 243, 228));
		
		setGameArr();
		setButton();
		setTitle();
		setTime();
		setReStart();
		setGameNumLabel();
		
		add(title);
		add(time);
		add(restart);
		add(numLabel);
		timeRun = true;
	}

	
	// 게임에 넣을 숫자 셋팅
	private void setGameArr() {
		// 숫자 배열 2개 만들어서 1~25, 26~50
		// 랜덤으로 섞어가지고
		// 하나식 텍스트로 셋팅
		fir = new int[25];
		sec = new int[25];
		check = new boolean[25];
		
		Random ran = new Random();
		
		for(int i=0; i<25; i++) {
			fir[i] = i+1;
			sec[i] = i +26;
		}
		
		// 믹스 부분
//		for(int i=0; i<100; i++) {
//			int rIdx1 = ran.nextInt(25);
//			int rIdx2 = ran.nextInt(25);
//			
//			int tempFirNum = fir[0];
//			int tempSecNum = sec[0];
//			
//			fir[0] = fir[rIdx1];
//			fir[rIdx1] = tempFirNum;
//
//			sec[0] = sec[rIdx2];
//			sec[rIdx2] = tempSecNum;
//		}
	}
	

	// 게임 버튼들
	private void setButton() {
		map = new JButton[25];

		int x = 800/2 - 400/2; 
		int y = 800/2 - 400/2;
		for(int i=0; i<25; i++) {
			map[i] = new JButton();

			map[i].setBounds(x, y, 80, 80);
			map[i].setBackground(new Color(245, 198, 165));
			map[i].setText(fir[i]+"");
			map[i].setFont(new Font("",Font.BOLD, 18) );
				
			map[i].addActionListener(this);
				
			map[i].setOpaque(true);
			map[i].setBorderPainted(false);
				x += 82;
				
			add(map[i]);
			
			if(i % 5 == 4) {
				x = 800/2 - 400/2;
				y += 82;
			}
		}
	}
	
	// 게임용 버튼 체인지
	private void changeButton(JButton button, int idx){
		if(map[idx].getText().equals( (this.gameNum+"") )) {
			check[idx] = true;
			System.out.println(check[idx]);
			
			System.out.println("gameNum: " + this.gameNum);
			
			if(this.gameNum < 26) {
				button.setBackground(new Color(255, 119, 119));		
				button.setText( sec[idx] + "" );
				button.setForeground(Color.BLACK);
			}else {
				button.setBackground(new Color(255, 243, 228));		
				button.setText("");
			}
			changeGameNum();
		}
	}
	// 버튼 체인지 기능
	private void changeGameNum() {
		this.gameNum++;
		numLabel.setText("Find Number! [ " +  this.gameNum + " ]");
	}
	
	// 타이틀 라벨
	private void setTitle() {
		title = new JLabel();
		title.setBounds(0, 40, 800, 200);
		title.setHorizontalAlignment( JLabel.CENTER );
		title.setVerticalAlignment( JLabel.TOP );
		title.setFont( new Font("", Font.BOLD, 30) );
		title.setForeground(new Color(81, 5, 15));
		title.setText("[ 1 to 50 ]");
		title.setVisible(true);
	}
	
	// 시간 라벨
	private void setTime() {
		time = new JLabel();
		time.setBounds(0, 10, 800, 160);
		time.setHorizontalAlignment( JLabel.CENTER );
		time.setFont( new Font("", Font.BOLD, 20) );
		time.setForeground(new Color(247, 136, 18));
		time.setVisible(true);
	}
	
	// 시간 셋팅
//	public void setTimer(int m, int s) {
//		this.m = m;
//		this.s = s;
//		time.setText(String.format("%2d :%2d", this.m, this.s));
//	}
	
	// 재시작 버튼
	private void setReStart() {
		restart = new JButton();
		restart.setBounds(365, 660, 80, 50);
		restart.setHorizontalAlignment( JLabel.CENTER );
		restart.setFont( new Font("", Font.TRUETYPE_FONT, 20) );
		restart.setText("재시작");
		restart.setBackground(new Color(195, 123, 137));
		restart.setForeground(new Color(195, 123, 137));
		restart.setVisible(true);
		restart.addActionListener(this);
	}
	
	// 게임넘버 라벨
	private void setGameNumLabel() {
		this.gameNum = 1;
		numLabel = new JLabel();
		numLabel.setBounds(0, 80, 800, 200);
		numLabel.setHorizontalAlignment( JLabel.CENTER );
		numLabel.setFont( new Font("", Font.BOLD, 20) );
		numLabel.setText("Find Number! [ " +  this.gameNum + " ]");
		numLabel.setForeground(new Color(8, 32, 50));
		numLabel.setVisible(true);
	}
	
	// 재시작 메서드
	public void restartGame() {
		setGameArr(); // 숫자, 체크 배열 다시 셋팅
		for(int i=0; i<25; i++) { // 맵에 다시 셋팅
			map[i].setBackground(new Color(245, 198, 165));
			map[i].setText(fir[i]+"");
		}
		this.gameNum = 1; // 게임번호 초기화
		numLabel.setText("Find Number! [ " +  this.gameNum + " ]");
		m = s = 0; // 0만들기
		this.timeRun = true;
//		run(); // 쓰레드 실행 
//		Time.getInstance().initTime(); // Time 클래스 시간 0초로 만들어주기
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton)e.getSource();
		
		if(target == restart) {
			restartGame();
		}
		
		System.out.println(target.getText());
		for(int i=0; i<map.length; i++) {
			if(target == map[i]) {
				changeButton(map[i], i);
			}
		}
		end();
	}
	
	// 종료 메서드
	private boolean end() {
		boolean check = false;
		// 시간, 맵, 체크배열, 게임번호
		if(this.gameNum == 51) {
			this.timeRun = false;
			Finish f = new Finish(this.m, this.s);
			restartGame();
		}
		return check;
	}


	@Override
	public void run() {
		// 다시들어오면 자동 초기화
		m = 0;
		s = 0;

		while(this.timeRun) {
			time.setText(String.format("%2d :%2d", this.m, this.s));
			try {
				Thread.sleep(1000);
				System.out.println("시간이 흐르는 중...");
			}catch(Exception e){
				e.printStackTrace();
			}
			this.s++;
			if(s == 60) {
				this.m++;
				this.s = 0;
			}
		}
	}	
}


// 종료 프레임, 패널 클래스
class Finish extends JFrame{
	
	JPanel p;
	JLabel l;
	JLabel t;
	JButton reStart;
	boolean isRestart;
//	int m;
//	int s;
	
	public Finish(int min, int sec) {
		super("finish!");
		setLayout(null);
		setBounds(100, 100, 300, 200);
		setBackground(Color.white);
		
		setPanel();
		setFinishTime(min, sec); // 시간 불러옴
		add(p);
		p.add(l);
		
		// 시간 불러오고
//		setTime(min, sec);
		p.add(t); // 패널에 더해주는 
		
		setVisible(true);
		revalidate();
	}
	
	private void setPanel() {
		p = new JPanel();
		l = new JLabel();
		// 패널
		p.setLayout(null);
		p.setBounds(0, 0, 300, 200);
		p.setBackground(Color.white);
		l.setText("게임종료!");
		l.setBounds(0, 40, 300, 200);
		l.setForeground(Color.black);
		l.setFont(new Font("", Font.BOLD, 15));
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setVerticalAlignment(JLabel.TOP);
	}
	
	public void setFinishTime(int min, int sec) {
		t = new JLabel();
		t.setBounds(100, 70 , 300, 100);
		t.setBackground(Color.DARK_GRAY);
		t.setForeground(Color.black);
		t.setFont(new Font("", Font.BOLD, 15));		
		t.setText(String.format("소요시간: %d분 %d초", min, sec));
	}
	
//	public void setTime(int min, int sec) {
//		this.m = min;
//		this.s = sec;		
//	}

}


// 별도의 스태틱 클래스로 조정해보기
//class Time{
//	private int m = 0; // 인스턴스가 static이면 변수 자체는 큰 의미 X
//	private int s = 0;
//	
//	private static Time instance = new Time();
//	
//	private Time() {
//		
//	}
//	
//	public void initTime() {
//		System.out.println("시간 초기화!");
//		m = 0;
//		s = 0;
//	}
//	
//	public static Time getInstance() {
//		return instance;
//	}
//	
//	public int getMin() {
//		return m;
//	}
//	public int getSec() {
//		return s;
//	}
//	public void setTime(int min, int sec) {
//		m = min;
//		s = sec;
//	}
//	public void plusTime() {
//		s++;
//		
//		if(s == 60) {
//			m++;
//			s = 0;
//		}
//	}
//}


//class TimeThread extends Thread{
//	
//	boolean run = true;
//	
//	int m = 0;
//	int s = 0;
//	
//	@Override
//	public void run() {
//		while(true) {
//			try {
//				sleep(1000);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			s++;
//			if(s == 60) {
//				m++;
//				s = 0;
//			}
//			// 종료조건도 만들어주자
//		}
//	}
//}


// 컨트롤러 클래스
class ControllerClass{	
	// 처음 불러오기용
	public ControllerClass() {
		
		// 쓰레드 사용해서 컨트롤러가 단순 불러오기용이 됨
		OneFrame OTF = new OneFrame();
		
		// ///////아래는 쓰레드 안썼을 떄 //////
		// 메인에서 와일돌려서 쓰레드
		// 돌리면서, 자기 값을 추정할 수 있게 하는 세터

//		while(true) {
//			// 계속 시간 Time 클래스에서 초기화
//			try {
//				Thread.sleep(1000); // 1초!
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			// 타임클래스에서 계속 가져와서 셋팅하기
//			OTF.setTimer(Time.getInstance().getMin(), Time.getInstance().getSec());
//			
//			// 시간 더해주기
//			Time.getInstance().plusTime();
//		}
	}	
}

// 메인
public class OneToFifty_previousVersion {
	public static void main(String[] args) {
		ControllerClass c = new ControllerClass();			

	}
}
