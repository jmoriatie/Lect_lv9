package oneToFifty;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


// ======== 게임 프레임 클래스 ========
class OneFrame extends JFrame{

	OnePanel o = new OnePanel();
	
	public OneFrame() {
		super("OneToFifty");
		
		setLayout(null);
		setBounds(100, 100, 800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.white);
		
		add(o);
		
		setVisible(true);
		revalidate();

		multieThread();
	}
	
	public void multieThread() {
		Thread t = new Thread(o);
		t.start();
	}
	
}


// ======== 게임패널 클래스 ========
class OnePanel extends JPanel implements ActionListener, Runnable{
		
	JButton[] map;
	JButton restart;
	int[] fir;
	int[] sec;
	boolean[] check;
	JLabel title;
	JLabel numLabel;
	JLabel time;
		
	int m = 0;
	int s = 0;
	
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
		timeRun = false;
	}

	// 게임에 넣을 숫자 셋팅
	private void setGameArr() {
		fir = new int[25];
		sec = new int[25];
		check = new boolean[25];
		
		
		for(int i=0; i<25; i++) {
			fir[i] = i+1;
			sec[i] = i +26;
		}
		
		// 믹스 메서드 작동 on/off
//		mixGameArr();
	}

	// 게임 숫자들 믹스
	private void mixGameArr() {
		Random ran = new Random();

		for(int i=0; i<100; i++) {
		int rIdx1 = ran.nextInt(25);
		int rIdx2 = ran.nextInt(25);
		
		int tempFirNum = fir[0];
		int tempSecNum = sec[0];
		
		fir[0] = fir[rIdx1];
		fir[rIdx1] = tempFirNum;

		sec[0] = sec[rIdx2];
		sec[rIdx2] = tempSecNum;
	}
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
			
			this.gameNum++;
			numLabel.setText("Find Number! [ " +  this.gameNum + " ]");
		}
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
		time.setText(String.format("%2d :%2d", this.m, this.s));
		time.setHorizontalAlignment( JLabel.CENTER );
		time.setFont( new Font("", Font.BOLD, 20) );
		time.setForeground(new Color(247, 136, 18));
		time.setVisible(true);
	}
	
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
		
		// Thread 컨트롤
		m = s = 0; // 0만들기
		timeRun = false;
	}
		
	// 종료 메서드
	private boolean end() {
		boolean check = false;
		// 시간, 맵, 체크배열, 게임번호
		if(this.gameNum == 51) {
			Finish f = new Finish(this.m, this.s);
			timeRun = false;
		}
		return check;
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
				if(!timeRun) {
					timeRun = true; // 버튼누를 때 실행될 수 있도록
				}
				changeButton(map[i], i);
			}
		}
		end();
	}

	// 쓰레드로 시간 돌리는 메서드
	@Override
	public void run() {
		while(true) {
			time.setText(String.format("%2d :%2d", this.m, this.s));
			try {
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(timeRun) {
				this.s++;
				if(s == 60) {
					this.m++;
					this.s = 0;
				}
			}
		}
	}	
}


// ======== 종료 프레임 및 패널 클래스 ========
class Finish extends JFrame{
	
	JPanel p;
	JLabel l;
	JLabel t;
	JButton reStart;
	boolean isRestart;
	
	public Finish(int min, int sec) {
		super("finish!");
		setLayout(null);
		setBounds(100, 100, 300, 200);
		setBackground(Color.white);
		
		setPanel();
		setFinishTime(min, sec); // 시간 불러옴
		add(p);
		
		p.add(l);
		p.add(t); 
		
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

}


// ======== 메인  ========
public class OneToFifty {
	public static void main(String[] args) {
		OneFrame OTF = new OneFrame();

	}
}
