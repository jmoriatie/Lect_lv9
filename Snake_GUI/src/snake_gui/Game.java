package snake_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import util.MyUtil;

class SnakePanel extends MyUtil{
	
	// 맵 만들기
	// 랜덤 아이템 배정
	// JButton 생성(key부터 달자)
	// (drawReect)map 만들고, 스네이크 이동
	// 아이템 먹으면, 길어지기
	// 몸통에 닿으면 사망
	// 리셋버튼

	private final int UP = 38;
	private final int DOWN = 40;
	private final int LEFT = 37;
	private final int RIGHT = 39;
	
	private Rect[][] map;
	
	private int size;
	private Rect[] snake;
	
	private int itemCnt;
	private boolean play;
	

	private JButton up;
	private JButton down;
	private JButton left;
	private JButton right;
	private JButton reset;
	private JLabel printM;
	
	private Rect warn;
	
	private String arrs[] = {"↑","↓","←","→"}; // 상하좌우
	
	
	public SnakePanel() {
		setLayout(null);
		setBounds(0, 0, 1000, 650);
		
		setGame();
		
		setVisible(true);
	}
	
	private void moveSnake(int dir) {
		// 아이템 먹었을 경우 예외
		// 템먹으면 꼬리 길어지는 거
		Rect head = snake[0];
		int x = head.getX();
		int y = head.getY();
		if(this.play) {
			if(dir == UP) {
				if( (snake[0].getY() - 50)  >= 50 ) { // 이격사이즈
					head.setY(head.getY() - 50);			
					for(int i=1; i<this.size; i++) { // 꼬리들 이동
						int tmpX = snake[i].getX();
						int tmpY = snake[i].getY();
						snake[i].setX(x);
						snake[i].setY(y);
						x = tmpX;
						y = tmpY;
					}
				}
			}
			else if(dir == DOWN) {
				if( (head.getY() + 50)  <= 50+(50*9) ) { // 이격사이즈 + 맵Y 크기
					head.setY(head.getY() + 50);
					for(int i=1; i<this.size; i++) { // 꼬리들 이동
						int tmpX = snake[i].getX();
						int tmpY = snake[i].getY();
						snake[i].setX(x);
						snake[i].setY(y);
						x = tmpX;
						y = tmpY;
					}
				}
			}
			else if(dir == LEFT) {
				if( (head.getX() - 50)  >= 50 ) { 
					head.setX(head.getX() - 50); 			
					for(int i=1; i<this.size; i++) { // 꼬리들 이동
						int tmpX = snake[i].getX();
						int tmpY = snake[i].getY();
						snake[i].setX(x);
						snake[i].setY(y);
						x = tmpX;
						y = tmpY;
					}
				}
			}
			else if(dir == RIGHT) {
				if( (head.getX() + 50) <= 50+(50*9) ) { 
					head.setX(head.getX() + 50); 		
					for(int i=1; i<this.size; i++) { // 꼬리들 이동
						int tmpX = snake[i].getX();
						int tmpY = snake[i].getY();
						snake[i].setX(x);
						snake[i].setY(y);
						x = tmpX;
						y = tmpY;
					}
				}
			}
			// 아이템 먹었을 때
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[i].length; j++) {
					if(map[i][j].getX() == head.getX() && 
						map[i][j].getY() == head.getY() &&
						String.valueOf(map[i][j].getC()).equals( String.valueOf( new Color(255, 146, 146)))) {					
						System.out.println("들: " + this.itemCnt);
						this.itemCnt--;
						System.out.println("나: " + this.itemCnt);

						this.size++;
						Rect temp[] = snake;
						snake = new Rect[this.size];
						for(int k=0; k<temp.length; k++) {
							snake[k] = temp[k];
						}
						snake[0].setC(new Color(206, 229, 208));;
						snake[this.size-1] = new Rect(x, y, 50, 50);
						
						map[i][j].setC(Color.white);
						break;
					}
				}
			}
			
			this.printM.setText(String.format("아이템 개수: %d개", this.itemCnt));
			end(head);
		} else {
			this.printM.setText("RESET 클릭");
			System.out.println("리셋버튼 클릭 필요");
		}
	}
	
	// 게임 종료
	private void end(Rect head){
		for(int i=1; i<this.size; i++) {
			if(head.getX() == snake[i].getX() && head.getY() == snake[i].getY()) {
				System.out.println("OVER");
				this.printM.setText("GAME OVER");
				this.play = false;
				break;
			}
		}
		
		if(this.itemCnt == 0) {
			System.out.println("CLEAR");
			this.printM.setText("GAME CLEAR");
			this.play = false;
		}
	}
	
	private void setGame() {
		this.play = true;
		setWarn();
		setMessage(); // 라벨
		setMap(); // 맵
		setSnake(); // 뱀
		setItem(); // 아이템
		setReset(); // 리셋버튼
		setButton(); // 버튼
		add(up);
		add(down);
		add(left);
		add(right);
		add(printM); // 라벨
		add(reset);
	}
	
	private void setWarn() {
		this.warn = new Rect(1000 - 220, 100, 150, 50);
	}
	
	private void setMessage() {
		this.printM = new JLabel();
		this.printM.setBounds(1000 - 220, 250, 150, 50);
		this.printM.setHorizontalAlignment(JLabel.CENTER);
		this.printM.setFont(new Font("", Font.ROMAN_BASELINE, 15));
		this.printM.setText(String.format("아이템 개수: %d개", this.itemCnt));
	}
	
	private void setReset() {
		this.reset = new JButton();
		this.reset.setBounds(1000 - 180, 650 - 250,100, 50);
		this.reset.setFont( new Font("", Font.BOLD, 15) );
		this.reset.setBackground(Color.gray);
		this.reset.setText("RESET");
		this.reset.setHorizontalAlignment(JButton.CENTER);
		this.reset.addMouseListener(this);
		this.reset.addKeyListener(this);
	}
	
	private void setMap() {
		map = new Rect[10][10];
		
		int x = 50;
		int y = 50;
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = new Rect(x, y, 50, 50);
				x += 50;
			}
			x = 50;
			y += 50;
		}
	}
	
	private void setItem() {
		Random rn = new Random();

		itemCnt = 7;
		String c = String.valueOf(new Color(255, 146, 146));
		for(int i=0; i<this.itemCnt; i++) {
			boolean check = true;
			int rNumY = rn.nextInt(this.map.length);
			int rNumX = rn.nextInt(this.map[0].length);
			// 중복처리			
			for(int j=0; j<this.size; j++) {
				if(map[rNumY][rNumX].getX() == snake[j].getX() &&
						map[rNumY][rNumX].getY() == snake[j].getY()) {
					System.out.println("뱀이랑 겹침");
					check = false;	
				}
			}
			
			if(String.valueOf(map[rNumY][rNumX].getC()).equals(c)) {
				check = false;
			}
			
			if(check) {
				map[rNumY][rNumX].setC(new Color(255, 146, 146));
			} else i--;
			
		}
		this.printM.setText(String.format("아이템 개수: %d개", this.itemCnt));
	}
	
	private void setSnake() {
		this.size = 5;
		snake = new Rect[this.size];
		// 
		int ss = this.size;
		int xx = 100;
		int yy = 50;
		for(int i=0; i<this.size; i++) {
			snake[i] = new Rect(xx, yy*ss, 50, 50);
			snake[i].setC(new Color(254, 210, 170));
			ss--;
		}
		snake[0].setC(new Color(206, 229, 208));
	}
	
	private void setButton() {
		// 위
		this.up = new JButton();
		this.up.setBounds(1000 - 150, 650 - 150, 50, 50);
		this.up.setFont( new Font("", Font.BOLD, 15) );
		this.up.setBackground(Color.gray);
		this.up.setText(arrs[0]);
		this.up.addKeyListener(this);
		// 아래
		this.down = new JButton();
		this.down.setBounds(1000 - 150, 650 - 100, 50, 50);
		this.down.setFont( new Font("", Font.BOLD, 15) );
		this.down.setBackground(Color.gray);
		this.down.setText(arrs[1]);
		this.down.addKeyListener(this);
		// 왼
		this.left = new JButton();
		this.left.setBounds(1000 - 200, 650 - 100, 50, 50);
		this.left.setFont( new Font("", Font.BOLD, 15) );
		this.left.setBackground(Color.gray);
		this.left.setText(arrs[2]);
		this.left.addKeyListener(this);
		// 오
		this.right = new JButton();
		this.right.setBounds(1000 - 100, 650 - 100, 50, 50);
		this.right.setFont( new Font("", Font.BOLD, 15) );
		this.right.setBackground(Color.gray);
		this.right.setText(arrs[3]);
		this.right.addKeyListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		// 맵
		// + 아이템
		g.setColor(new Color(243, 240, 215));
		g.fillRect(50, 50, 50*10, 50*10);
//		g.setColor(Color.gray);
//		g.drawRect(50, 50, 50*10, 50*10);
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				Rect temp = map[i][j];
				String c = String.valueOf( new Color(255, 146, 146) );
				
				if( String.valueOf(temp.getC()).equals(c) ) {
					g.setColor(temp.getC());
					g.fillRoundRect(temp.getX()+10, temp.getY()+10, temp.getWidth()-20, temp.getHeight()-20, temp.getWidth(), temp.getHeight());
					g.setColor(Color.CYAN);
					g.drawRoundRect(temp.getX()+10, temp.getY()+10, temp.getWidth()-20, temp.getHeight()-20, temp.getWidth(), temp.getHeight());
				}
				g.setColor(Color.black);
			}
		}
		
		// 뱀
		for(int i=0; i<this.size; i++) {
			Rect temp = snake[i];
			g.setColor(temp.getC());
			g.drawRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getWidth()-30, temp.getHeight()-30);
			g.fillRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getWidth()-30, temp.getHeight()-30);
		}
		
		g.drawRect(warn.getX(), warn.getY(), warn.getWidth(), warn.getHeight());
		if(this.itemCnt != 0) {
			this.warn.setC(Color.blue);
			g.setColor(this.warn.getC());
			g.fillRect(warn.getX(), warn.getY(), warn.getWidth(), warn.getHeight());
		}
		else {
			this.warn.setC(Color.red);
			g.setColor(this.warn.getC());
			g.fillRect(warn.getX(), warn.getY(), warn.getWidth(), warn.getHeight());
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("눌렀-");
	
		int dir = e.getKeyCode();

		moveSnake(dir);
		System.out.println("itemCnt : " + this.itemCnt);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {		
		System.out.println("-뗏");
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("클릭");
		JButton b = (JButton)e.getSource();
		if(reset == b) {
			System.out.println("리셋");
			this.printM.setText("");
			setMap(); // 맵
			setItem(); // 아이템
			setSnake(); // 뱀
			this.play = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("탁누름");
	}
}

public class Game extends JFrame{
	
	private SnakePanel panel = new SnakePanel();
	
	public Game() {
		super("SNAKE GAME");
		setLayout(null);
		setBounds(50, 50, 1000, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(panel);
		
		setVisible(true);
		revalidate();
	}
}
