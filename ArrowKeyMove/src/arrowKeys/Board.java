package arrowKeys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JPanel;

import source.Circle;

public class Board extends JPanel implements ActionListener, MouseListener{

	// 보드 만들고
	// 사각형 만들고
	// 화살표 버튼으로 만들고
	// 클릭하면, repaint => 조금씩 이동
	
	JButton up;
	JButton left;
	JButton down;
	JButton right;
	
	Circle circle;
	String cliking;
		
	Board(){
		setLayout(null);
		setBounds(0, 0, 700, 700);
		
		cliking = ""; // 지속적인 클릭움직임을 위해
		// 방향키
		setArrowKeys();
		// 동그라미 베이스
		setCircle();
		addMouseListener(this);
		setVisible(true);
	}
	
	private void setArrowKeys() {
		this.up = new JButton();
		this.left = new JButton();
		this.down = new JButton();
		this.right = new JButton();
		
		this.up.setBounds(700-150, 700-150, 50, 50);
		this.left.setBounds(700-200, 700-100, 50, 50);
		this.down.setBounds(700-150, 700-100, 50, 50);
		this.right.setBounds(700-100, 700-100, 50, 50);

		this.up.addMouseListener(this);
		this.left.addMouseListener(this);
		this.down.addMouseListener(this);
		this.right.addMouseListener(this);
		
		this.up.setText("▲");
		this.left.setText("◀");
		this.down.setText("▼");
		this.right.setText("▶");
		
		this.up.setBackground(Color.DARK_GRAY);
		this.left.setBackground(Color.DARK_GRAY);
		this.down.setBackground(Color.DARK_GRAY);
		this.right.setBackground(Color.DARK_GRAY);
		
		add(this.up);
		add(this.left);
		add(this.down);
		add(this.right);
	}
	
	private void setCircle() {
		// 동그라미로 연습
		circle = new Circle(10,10,100,100);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 느리게 
		try {
			Thread.sleep(30);
		} catch (Exception e) {
		}
		
		if(cliking.equals("up")) {
			circle.y--;
		}
		else if(cliking.equals("left")) {
			circle.x--;
		}
		else if(cliking.equals("down")) {
			circle.y++;
		}
		else if(cliking.equals("right")) {
			circle.x++;
		}
		
		// 동그라미로 그리기
		g.setColor(Color.BLACK); // 컬러를 먼저주면, 색 두개를 다르게 할 수도 있네
		g.drawRoundRect(circle.x, circle.y ,circle.width, circle.height, circle.width, circle.height);
		g.setColor(Color.PINK);
		g.fillRoundRect(circle.x, circle.y ,circle.width, circle.height, circle.width, circle.height);
		repaint();
	}
	
	private void setMove(JButton button) {
		// 버튼을 받아서, 변경
		if(button == this.up) {
			this.cliking = "up";
		}
		else if(button == this.left) {
			this.cliking = "left";
		}
		else if(button == this.down) {
			this.cliking = "down";
		}
		else if(button == this.right) {
			this.cliking = "right";
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("클릭");
		
//		JButton target = (JButton)e.getSource(); 
//		if(this.up == target) {
//			circle.y--;
//		}
//		else if(this.left == target) {
//			circle.x--;
//		}
//		else if(this.down == target) {
//			circle.y++;
//		}
//		else if(this.right == target) {
//			circle.x++;
//		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton target = (JButton)e.getSource(); 
		System.out.println("클-");
		setMove(target); // 받은 버튼을 보내줌
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("-릭");
		this.cliking = "";
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton)e.getSource(); 
		
//		if(this.up == target) {
//			circle.y--;
//		}
//		else if(this.left == target) {
//			circle.x--;
//		}
//		else if(this.down == target) {
//			circle.y++;
//		}
//		else if(this.right == target) {
//			circle.x++;
//		}
	}
}
