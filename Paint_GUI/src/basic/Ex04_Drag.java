package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

class P extends MyUtil{
	
	private ArrayList<Rect> rs;
	private Rect r;
	private int sX;
	private int sY;
	
	private JButton closeButton;
	
	private boolean shift;
	
	public P() {
		this.r = new Rect();
		rs = new ArrayList<Rect>();
		rs.add(r);
		
		setLayout(null);
		setBounds(0, 0, 1000, 700);
		
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		setCloseButton();
		add(closeButton);
		
		setVisible(true);
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
		rs.add(r);
		
		this.sX = e.getX();
		this.sY = e.getY();
		
		// 시작
		r.setX(sX);
		r.setY(sY);

//		System.out.print(r.getX() + " : ");
//		System.out.println(r.getY());		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int defX = 0;
		int defY = 0;

		if(!this.shift) { // 일반
			defX = x - sX;
			defY = y - sY;
		}
		else { // 쉬프트 클릭시
			if(x > y) { // x가 더 크면 x따라서
				defX = x - sX;
				defY = defX;
			}
			else { // 반대
				defY = y - sY;
				defX = defY;
			}
		}

		// 끝지점
		r.setWidth(defX);
		r.setHeight(defY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<rs.size(); i++) {
			g.setColor(Color.blue);
			Rect temp = rs.get(i);
			g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
		}
		
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
	}
}

class F extends JFrame{
	
	private static F instance = new F();
	private P p = new P();
	
	private F() {
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
