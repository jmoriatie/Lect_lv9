package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

class P extends MyUtil{
	
	private Random rn = new Random();
	
	private ArrayList<Rect> rs;
	private Rect r;
	private int sX, sY;
	
	private JButton closeButton;
	private JButton reset;
	
	private boolean shift;
	
	public P() {
		this.r = new Rect();
		rs = new ArrayList<Rect>();
		r.setColor( new Color(rn.nextInt(255),rn.nextInt(255),rn.nextInt(255)) );
		rs.add(r);
		
		setLayout(null);
		setBounds(0, 0, 1000, 700);
		
		setFocusable(true); // requestFocusInWindow() 메서드를 위한 추가
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		setCloseButton();
		setResetButton();
		add(closeButton);
		add(reset);
		
		setVisible(true);
	}

	private void setResetButton() {
		reset = new JButton();
		reset.setBounds(1000-200, 700-160, 100, 50);
		reset.setText("RESET");
		reset.addKeyListener(this);
		reset.addActionListener(this);
	}
	
	private void setCloseButton() {
		closeButton = new JButton();
		closeButton.setBounds(1000-200, 700-100, 100, 50);
		closeButton.setText("CLOSE");
		closeButton.setHorizontalAlignment(JButton.CENTER);
		closeButton.addKeyListener(this);
		closeButton.addActionListener(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.r = new Rect();
		// 랜덤 색칠
		r.setColor( new Color(rn.nextInt(255),rn.nextInt(255),rn.nextInt(255)) );
		rs.add(r);
		
		// 누른장소
		this.sX = e.getX();
		this.sY = e.getY();
		
		// 시작
		r.setX(sX);
		r.setY(sY);	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int defX = 0;
		int defY = 0;

		// 시작점 보다, 변경점이 크면(사각형 오른쪽 그리기)
		if(this.sX < x) {
			r.setX(sX);
			r.setY(sY);
			if(!this.shift) { // 일반
				defX = x - sX;
				
				if(this.sY > y) { // 오위
					defY = sY - y;
					r.setY(y);
				}
				else if(this.sY < y) { // 오아
					defY = y - sY;
				}	
				
			}
			else { // 쉬프트 클릭시
				// y
				// y-height
				defX = x - sX;
				defY = y - sY;

				if(this.sY >= y) { // 쉬프트 + 오위
					if(defX >= -defY) { // 차이가 더 큰쪽 따라가기
						defY = -defX; // X의 차이(defX)가 더 크면 Y초기화
						y = sY - defX; // 타점 sY와의 차이만큼 조정
					}
					else { 
						defX = -defY;
					}
					r.setY(y);
					defY = -defY;
				}
				else if(this.sY < y) { // 쉬프트 + 오아
					if(defX >= defY) { // x가 더 크면 x따라서
						defY = defX;
						y = sY + defX; 
					}
					else { // 반대
						defX = defY;
					}
				}	
			}
			// 끝지점
			r.setWidth(defX);
			r.setHeight(defY);
		} 
		// 사각형 왼쪽 그리기
		else {
			if(!this.shift) { // 일반
				if(this.sY >= y) { // 왼위
					defX = sX - x;
					defY = sY - y;	
				}
				else if(this.sY < y) { // 왼아
					defX = sX - x;
					defY = y - sY;
					y = sY;
				}	
			}
			else { // 쉬프트 클릭시
				defX = sX - x; // 커짐
				if(this.sY >= y) { // 쉬왼위 - 많이가면, 더작음
					defY = sY - y; // 커짐
					if(defX > defY) { // x가 더 작으면 x따라
						defY = defX;
						y = sY - defX;
					}
					else { // 반대		
						defX = defY;
						x = sX - defY;
					}
				}
				else if(this.sY < y) { // 쉬왼아
					defY = sY - y; // 작아짐(마이너스)
					if(defX > -defY) { // x가 큰경우
						defY = defX;
						y = sY;
					}
					else { // y가 큰경우		
						defX = defY;
						x = sX + defY;
						y = sY;
						defX = -defX; // 크기는 +조정
						defY = -defY; // (defY는 원래 -)
					}
				}
			}
			
			r.setX(x);
			r.setY(y);
			
			// 끝지점
			r.setWidth(defX);
			r.setHeight(defY);
		}								
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<rs.size(); i++) {
			Rect temp = rs.get(i);
			g.setColor(temp.getColor());
			g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
			g.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
		}
		
		// keyListener에 대한 포커스 재요청
		requestFocusInWindow();
		
		// 삼각형 그리기
		// drawPolygon(int[], int[], int[]);
		// (x좌표의 배열, y좌표의 배열, 꼭지점 개수)
//		int[] xxx = {100,50,150}; // 좌측부터 위꼭지, 좌, 우
//		int[] yyy = {100,200,200}; // 좌측부터 위, 좌, 우
//		g.setColor(Color.red);
//		g.drawPolygon(xxx, yyy, 3);
		
		
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.isShiftDown() == true) {
			System.out.println("쉬프트 누름");
			this.shift = true;
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("쉬프트 뗌");
		this.shift = false;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.closeButton) {
			System.out.println("close");
			F.getInstance().end();;
		}
		if(e.getSource() == this.reset) {
			System.out.println("reset");
			this.rs.clear();
		}
	}
}

class F extends JFrame{
	
	private static F instance = new F();
	private P p = new P();
	
	private F() {
		super("PAINT");
		setLayout(null);
		setBounds(100, 100, 1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(p);
		
		setVisible(true);
		revalidate();
	}
	
	// 싱글톤
	public static F getInstance() {
		return instance;
	}
	
	public void end() {
		System.out.println("종료");
		this.dispose();	
	}
}

public class Ex04_Drag {
	public static void main(String[] args) {
		F.getInstance();
	}
}
