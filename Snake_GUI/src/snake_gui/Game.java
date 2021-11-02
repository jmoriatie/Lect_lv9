package snake_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

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
	
	private String arrs[] = {"↑","↓","←","→"}; // 상하좌우
	
	private String move; // 초기화 고민
	
	public SnakePanel() {
		setLayout(null);
		setBounds(0, 0, 1000, 650);
		
		setGame();
		
		setVisible(true);
	}
	
	private void moveSnake(int dir) {
		// 아이템 먹었을 경우 예외
		// 템먹으면 꼬리 길어지는 거
		if(this.play) {
			Rect head = snake[0];
			
			if(dir == UP) {
				this.move = "up";
				if( (snake[0].getY() - 50)  >= 50 ) { // 이격사이즈
					moveTail(head);
					head.setY(head.getY() - 50);			
				}
			}
			else if(dir == DOWN) {
				this.move = "down";
				if( (head.getY() + 50)  <= 50+(50*9) ) { // 이격사이즈 + 맵Y 크기
					moveTail(head);
					head.setY(head.getY() + 50);
					
				}
			}
			else if(dir == LEFT) {
				this.move = "left";
				if( (head.getX() - 50)  >= 50 ) { 
					moveTail(head);
					head.setX(head.getX() - 50); // 머리이동			
				}
			}
			else if(dir == RIGHT) {
				this.move = "rigth";
				if( (head.getX() + 50) <= 50+(50*9) ) { 
					moveTail(head);
					head.setX(head.getX() + 50); // 머리이동			
				}
			}
			
			end(head);
		} else {
			System.out.println("리셋버튼 클릭 필요");
		}
	}
	
	private void moveTail(Rect head) {
		int x = head.getX();
		int y = head.getY();
		for(int i=1; i<this.size; i++) { // 꼬리들 이동
			int tmpX = snake[i].getX();
			int tmpY = snake[i].getY();
			snake[i].setX(x);
			snake[i].setY(y);
			x = tmpX;
			y = tmpY;
		}
		// 아이템 먹었을 때
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j].getX() == head.getX() && map[i][j].getY() == head.getY() && map[i][j].getC() == Color.red) {
					this.size++;
					Rect temp[] = snake;
					snake = new Rect[this.size];
					for(int k=0; k<temp.length; k++) {
						snake[k] = temp[k];
					}
					snake[this.size-1] = new Rect(x, y, 50, 50);
					
					map[i][j].setC(Color.white);
					break;
				}
			}
		}
	}
	
	// 죽음 
	private void end(Rect head){
		for(int i=1; i<this.size; i++) {
			if(head.getX() == snake[i].getX() && head.getY() == snake[i].getY()) {
				System.out.println("종료");
				this.play = false;
			}
		}
	}
	
	private void setGame() {
		
		Random rn = new Random();

		this.play = true;
		// 리셋버튼 ////////
		this.reset = new JButton();
		this.reset.setBounds(1000 - 150, 650 - 250,100, 50);
		this.reset.setFont( new Font("", Font.BOLD, 15) );
		this.reset.setBackground(Color.gray);
		this.reset.setText(arrs[0]);
		this.reset.addMouseListener(this);
		
		// 맵
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
		
		// 아이템
		itemCnt = 4;
		for(int i=0; i<this.itemCnt; i++) {
			int rNumY = rn.nextInt(this.map.length);
			int rNumX = rn.nextInt(this.map[0].length);
			// 중복처리
			if(map[rNumY][rNumX].getC() != Color.red) {
				map[rNumY][rNumX].setC(Color.red);
			}
			else i--;
		}
		
		// 뱀
		// ㄴ 머리 색 다르게
		this.size = 5;
		snake = new Rect[this.size];
		// 
		int ss = this.size;
		int xx = 100;
		int yy = 50;
		for(int i=0; i<this.size; i++) {
			snake[i] = new Rect(xx, yy*ss, 50, 50);
			snake[i].setC(Color.green);
			ss--;
		}
		snake[0].setC(Color.blue);
		
		// 버튼
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
		
		add(up);
		add(down);
		add(left);
		add(right);
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// 느리게 이동
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 맵
		// + 아이템
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				Rect temp = map[i][j];
				if(temp.getC() == Color.red) {
					g.setColor(temp.getC());
					g.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				}
				g.setColor(Color.black);
				g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
			}
		}
		
		// 뱀
		for(int i=0; i<this.size; i++) {
			Rect temp = snake[i];
			g.setColor(temp.getC());
			g.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
			g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int dir = e.getKeyCode();
		moveSnake(dir);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.move = "";
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
