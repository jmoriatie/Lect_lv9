package basic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;


class Rect{
	private int x,y,width,height;

	public Rect(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setMiddle(int x, int y) {
		this.x = (x + this.width) / 2;
		this.y = (y + this.height) / 2;
	}
}

class ExPanel extends MyUtil{
	
	private Rect rect = new Rect(100, 100, 100, 100);
	private Rect rect2 = new Rect(300, 100, 100, 100);
	
	public ExPanel() {
		setLayout(null);
		setBounds(0, 0, 500, 500);
		setBackground(new Color(255, 249, 182));
		addMouseMotionListener(this);
		
		setVisible(true);
	}	
	
	// MouseMotionListener 메서드
	@Override
	public void mouseDragged(MouseEvent e) {
		// 드래그 좌표 표시
		if (e.getX() >= rect.getX() && e.getX() <= rect.getX() + rect.getWidth() && 
				e.getY() >= rect.getY() && e.getY() <= rect.getY() + rect.getHeight()) {
			System.out.println("받: " + e.getX() + " : " + e.getY());
			rect.setX(e.getX() - (rect.getWidth()/2));
			rect.setY(e.getY() - (rect.getHeight()/2));
		}
		
		// 움직임
		// >>
		if(( rect.getX() + rect.getWidth() ) == rect2.getX() && 
				rect.getY() <= rect2.getY() + rect2.getHeight() && 
				rect.getY() + rect.getHeight() >= rect2.getY() ) {
			rect2.setX( rect.getX() + rect.getWidth() + 40 );
		}
		// <<
		else if(rect.getX() == rect2.getX() + rect2.getWidth() && 
				rect.getY() <= rect2.getY() + rect2.getHeight() && 
				rect.getY() + rect.getHeight() >= rect2.getY() ) {
			rect2.setX( rect.getX() - 40 - rect2.getWidth() );
		}
		else if(rect.getY() == rect2.getY() + rect2.getHeight() && 
				rect.getX() <= rect2.getX() + rect2.getWidth() && 
				rect.getX() + rect.getWidth() >= rect2.getX() ) {
			rect2.setY( rect.getY() - 40 - rect2.getHeight() );
		}
		else if(rect.getY() + rect.getHeight() == rect2.getY() && 
				rect.getX() <= rect2.getX() + rect2.getWidth() && 
				rect.getX() + rect.getWidth() >= rect2.getX() ) {
			rect2.setY( rect.getY() + rect.getHeight() + 40 );
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(255, 146, 146));
		g.fillRect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
		g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());

		g.setColor(new Color(255, 204, 210));
		g.fillRect(this.rect2.getX(), this.rect2.getY(), this.rect2.getWidth(), this.rect2.getHeight());
		g.drawRect(this.rect2.getX(), this.rect2.getY(), this.rect2.getWidth(), this.rect2.getHeight());
		
		
		
		repaint();
	}
}

class ExFrame extends JFrame{

	public ExFrame() {
		super("drag");
		setLayout(null);
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new ExPanel());
		
		setVisible(true);
		revalidate();
		
	}
}
	
public class Ex04{
	public static void main(String[] args) {
		ExFrame ef = new ExFrame();
	}
}

	

	

