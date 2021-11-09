package paintDev;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class P extends MyUtil{
	// 버튼 만들고 
	// 버튼 누르면 변경될 수 있도록 하고
	// 삼각형, 라인 설정하고
	// 라인은 그려질 때 지속 변경
	
//	private Random rn = new Random();
	
	private ArrayList<Rect> rs;
	private ArrayList<Poly> ps;
	private ArrayList<Line> ls;
	private ArrayList<JLabel> cs;
	private ArrayList<Round> rrs;
	
	private int color;
	private Color[] colors = {Color.black, Color.red, Color.pink, Color.blue, Color.green};
	private String[] colorsS = {"검정", "빨강", "핑크", "파랑", "초록"};
	
	private int x, y, startX, startY;
	
	private Rect r;
	private Poly p;
	private Line l;
	private Round rr;
	
	private JButton rectButton;
	private JButton polyButton;
	private JButton lineButton;
	private JButton roundButton;
	
	private JButton closeButton;
	private JButton reset;
	
	private String draw;
	private boolean shift;
	
	public P() {
		this.r = new Rect();
		rs = new ArrayList<Rect>();
		ps = new ArrayList<Poly>();
		ls = new ArrayList<Line>();
		rrs = new ArrayList<Round>();
		
		cs = new ArrayList<JLabel>();
		setLayout(null);
		setBounds(0, 0, 1000, 700);
		
		setFocusable(true); // requestFocusInWindow() 메서드를 위한 추가
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		setColorButtons();
		for(JLabel la : cs) {
			add(la);
		}

		setCloseButton();
		setResetButton();
		add(closeButton);
		add(reset);
		
		setDrawButtons();
		add(rectButton);
		add(polyButton);
		add(lineButton);
		add(roundButton);
		
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
	
	private void setColorButtons() {
		this.color = 0; // default color
		int x = 50;
		for(int i=0; i<colors.length; i++) {
			JLabel temp = new JLabel();
			temp.setBackground(colors[i]);
			temp.setBounds(x, 700-150, 50, 50);
			if(i == 0) {
				temp.setBackground(temp.getBackground());
				temp.setBounds(temp.getX(), temp.getY(), 50, 100);
			}
			cs.add(temp);
			x += 50;
		}
	}
	
	private void setDrawButtons() {
		rectButton = new JButton();
		polyButton = new JButton();
		lineButton = new JButton();
		roundButton = new JButton();
		
		rectButton.setBounds(50, 50, 50, 50);
		polyButton.setBounds(50, 100, 50, 50);
		lineButton.setBounds(50, 150, 50, 50);
		roundButton.setBounds(50, 200, 50, 50);
		
		rectButton.setFont(new Font("", Font.BOLD, 20));
		polyButton.setFont(new Font("", Font.BOLD, 20));
		lineButton.setFont(new Font("", Font.BOLD, 20));
		roundButton.setFont(new Font("", Font.BOLD, 20));
		
		rectButton.setForeground(Color.gray);
		polyButton.setForeground(Color.gray);
		lineButton.setForeground(Color.gray);
		roundButton.setForeground(Color.gray);

		rectButton.setText("■");
		polyButton.setText("▲");
		lineButton.setText("/");
		roundButton.setText("●");
		
		rectButton.addActionListener(this);
		polyButton.addActionListener(this);
		lineButton.addActionListener(this);
		roundButton.addActionListener(this);
		 
		this.draw = "rect"; // 디폴트, 사각형
		rectButton.setForeground(Color.black);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("마우스 누름");
		// 누른장소 => 변할 좌표
		this.x = e.getX();
		this.y = e.getY();

		// 고정하기 위한 좌표
		this.startX = x;
		this.startY = y;
		
		if(this.draw.equals("rect")) {
			this.r = new Rect();
			r.setX(startX);
			r.setY(startY);
			rs.add(r);	
			r.setColor(cs.get(this.color).getBackground() );
		}	
		if(this.draw.equals("round")) {
			this.rr = new Round();
			rr.setX(startX);
			rr.setY(startY);
			rrs.add(rr);	
			rr.setColor(cs.get(this.color).getBackground() );
		}	
		if(this.draw.equals("poly")) {
			this.p = new Poly();
			ps.add(p);
			p.setPoint(3);
			p.setColor(cs.get(this.color).getBackground());

			int xxx[] = {startX, startX, startX};
			int yyy[] = {startY, startY, startY};
			
			p.setXx(xxx);
			p.setYy(yyy);
		}
		if(this.draw.equals("line")) {
			this.l = new Line();
			ls.add(l);
			l.setColor(cs.get(this.color).getBackground());
			
			int xxx[] = {startX};
			int yyy[] = {startY};
			
			l.setPoint(1);
			l.setX(xxx);
			l.setY(yyy);			
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.x = startX;
		this.y = startY;

		int xx = e.getX();
		int yy = e.getY();
		
		// 크기 지정
		int width = Math.abs(xx - startX);
		int height = Math.abs(yy - startY);
		
		if(this.draw.equals("rect")) {
			if(shift) {
				width = height;
			}
			// 시작보다, 이동하는 좌표가 작으면, 
			// 그 해당 길이, 높이만큼 x,y 좌표도 함께 이동시켜준다
			// ㄴ 그렇지 않을 경우에는 그냥 그 위치에서 네모그려줌
			// ㄴ start랑 비교해주는 이유는 x,y는 아래에서 계속 변하기 때문
			if(this.startX > xx && width > 1) {
				x = startX - width; // 시작지점 - width
			}
			if(this.startY > yy && height > 1) {
				y = startY - height;
			}
			// 시작지점보다 클 경우 시작지점에 머무를 거고
			// 작을경우 조건문에 의해 변경됨 ( start X,Y는 고정 )
			r.setX(x);
			r.setY(y);
			
			// 실제 네모 넓이 조정
			r.setWidth(width);
			r.setHeight(height);
		}
		
		if(this.draw.equals("round")) {
			if(shift) {
				width = height;
			}
			
			if(this.startX > xx && width > 1) {
				x = startX - width; // 시작지점 - width
			}
			if(this.startY > yy && height > 1) {
				y = startY - height;
			}
			
			rr.setX(x);
			rr.setY(y);
			
			// 실제 네모 넓이 조정
			rr.setWidth(width);
			rr.setHeight(height);
		}
		
		if(this.draw.equals("poly")) {
			// 중간고정 
			// => 위, 아래 예외 하나 처리
			// => 좌, 우 동일 // 같은 크기로 변하기 
			// ㄴ start 기준 +, -
			// shift...

			if(shift) {
				width = height;
			}

			int xxx[] = {startX - width, startX, startX + width};
			int yyy[] = {startY + height, startY, startY + height};
			
			
			if(this.startY > yy) { // 스타트 지점이 더 아래
				int temp[] = {startY - height, startY, startY - height};
				yyy = temp;
			}
			p.setXx(xxx);
			p.setYy(yyy);
		}
		if(this.draw.equals("line")) {
			// 점마다 좌표가 있는거....???
			// 포인트 증가
			l.setPoint( l.getPoint() + 1 );
			int point  = l.getPoint();
			
			// 기존 배열 담아주기
			int tmpX[] = l.getX();
			int tmpY[] = l.getY();
			
			// 새로 방만들고
			int xxx[] = new int[point];
			int yyy[] = new int[point];
			
			// 새방에 담아주고
			for(int i=0; i<point-1; i++) {
				xxx[i] = tmpX[i];
				yyy[i] = tmpY[i];
			}

			// 마지막은 변경점
			xxx[point - 1] = xx; 
			yyy[point - 1] = yy;
			
			l.setX(xxx);
			l.setY(yyy);

			l.setColor(cs.get(this.color).getBackground());
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("마우스 뗌");
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		// 색변경
		for(JLabel b : cs) {
			if(x > b.getX() && x < b.getX() + b.getWidth() &&
			   y > b.getY() && y < b.getY() + b.getHeight()) {
				b.setBounds(b.getX(), b.getY(), 50, 100);
				this.color = cs.indexOf(b);
				System.out.println("색 : " + this.colorsS[cs.indexOf(b)]);
			}
			else {
				b.setBounds(b.getX(), b.getY(), 50, 50);
			}
		}		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// * 선 그리기
		// drawPolyLine(int[], int[], int)
		// : x 좌표 배열, y좌표 배열, 점 개수
		
		// 선
		for(int i=0; i<ls.size(); i++) {
			Line temp = ls.get(i);
			g.setColor(temp.getColor());
			g.drawPolyline(temp.getX(), temp.getY(), temp.getPoint());
		}
		
		// 네모
		for(int i=0; i<rs.size(); i++) {
			Rect temp = rs.get(i);
			g.setColor(temp.getColor());
			g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
			g.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
		}
		
		// 동그라미
		for(int i=0; i<rrs.size(); i++) {
			Round temp = rrs.get(i);
			g.setColor(temp.getColor());
			g.drawRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getWidth(), temp.getHeight());
			g.fillRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getWidth(), temp.getHeight());
		}
		
		//* 삼각형 그리기
		// drawPolygon(int[], int[], int);
		// (x좌표의 배열, y좌표의 배열, 꼭지점 개수)
//		int[] xxx = {100,50,150}; // 좌측부터 위꼭지, 좌, 우
//		int[] yyy = {100,200,200}; // 좌측부터 위, 좌, 우
//		g.setColor(Color.red);
//		g.drawPolygon(xxx, yyy, 3);
		
		// 세모
		for(int i=0; i<ps.size(); i++) {
			Poly temp = ps.get(i);
			g.setColor(temp.getColor());

			int xxx[] = temp.getXx();
			int yyy[] = temp.getYy();
			
			g.drawPolygon(xxx, yyy, p.getPoint());
			g.fillPolygon(xxx, yyy, p.getPoint());
		}
		
		for(JLabel la : cs) {
			g.drawRect(la.getX(), la.getY(), la.getWidth(), la.getHeight());
			g.setColor(la.getBackground());
			g.fillRect (la.getX(), la.getY(), la.getWidth(), la.getHeight());
		}
				
		// keyListener에 대한 포커스 재요청
		requestFocusInWindow();
				
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
			F.getInstance().end();
		}
		if(e.getSource() == this.reset) {
			System.out.println("reset");
			this.rs.clear();
			this.ps.clear();
			this.ls.clear();
			this.rrs.clear();
			
			this.draw = "rect";
			this.cs.clear();
			setColorButtons();
		}
		
		String temp = this.draw;
		// 뭐 그릴지 셋팅
		if(e.getSource() == this.rectButton) {
			System.out.println("네모");
			rectButton.setForeground(Color.black);
			this.draw = "rect";
		}
		if(e.getSource() == this.polyButton) {
			System.out.println("세모");
			polyButton.setForeground(Color.black);
			this.draw = "poly";
		}
		if(e.getSource() == this.lineButton) {
			System.out.println("라인");
			lineButton.setForeground(Color.black);
			this.draw = "line";
		}
		if(e.getSource() == this.roundButton) {
			System.out.println("동그라미");
			roundButton.setForeground(Color.black);
			this.draw = "round";
		}
		
		if(temp != this.draw) {
			if(temp.equals("rect") ) rectButton.setForeground(Color.gray);
			else if(temp.equals("poly") ) polyButton.setForeground(Color.gray);
			else if(temp.equals("line") ) lineButton.setForeground(Color.gray);
			else if(temp.equals("round") ) roundButton.setForeground(Color.gray);
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
