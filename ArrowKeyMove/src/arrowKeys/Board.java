package arrowKeys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JPanel;

import source.Circle;

public class Board extends JPanel implements ActionListener, MouseListener, KeyListener {

	// 보드 만들고
	// 사각형 만들고
	// 화살표 버튼으로 만들고
	// 클릭하면, repaint => 조금씩 이동

	JButton up;
	JButton left;
	JButton down;
	JButton right;

	Circle circle;
	Circle circleTwo;
	String cliking;

	Board() {
		setLayout(null);
		setBounds(0, 0, 700, 700);

		cliking = ""; // 지속적인 클릭움직임을 위해
		// 방향키
		setArrowKeys();
		// 동그라미 베이스
		setCircle();
		addMouseListener(this);
		addKeyListener(this);
		setVisible(true);
	}

	private void setArrowKeys() {
		this.up = new JButton();
		this.left = new JButton();
		this.down = new JButton();
		this.right = new JButton();

		this.up.setBounds(700 - 150, 700 - 150, 50, 50);
		this.left.setBounds(700 - 200, 700 - 100, 50, 50);
		this.down.setBounds(700 - 150, 700 - 100, 50, 50);
		this.right.setBounds(700 - 100, 700 - 100, 50, 50);

		this.up.addMouseListener(this);
		this.left.addMouseListener(this);
		this.down.addMouseListener(this);
		this.right.addMouseListener(this);

		this.up.addKeyListener(this);
		this.left.addKeyListener(this);
		this.down.addKeyListener(this);
		this.right.addKeyListener(this);

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
		circle = new Circle(10, 10, 100, 100);
		circleTwo = new Circle(10 + 120, 10, 100, 100);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics t = g;

		super.paintComponent(g);
		super.paintComponent(t);

		// 느리게
//		try {
//			Thread.sleep(30);
//		} catch (Exception e) {
//		}

		if (cliking.equals("up")) {
			circle.y--;
			if (circle.y == (circleTwo.y + circleTwo.height) && circle.x <= (circleTwo.x + circleTwo.width)
					&& (circle.x + circle.width) >= circleTwo.x) {
				circleTwo.y--;
			}
		} else if (cliking.equals("left")) {
			circle.x--;
			if (circle.x == (circleTwo.x + circleTwo.width) && circle.y <= (circleTwo.y + circleTwo.height)
					&& (circle.y + circle.height) >= circleTwo.y) {
				circleTwo.x--;
			}
		} else if (cliking.equals("down")) {
			circle.y++;
			if ((circle.y + circle.height) == circleTwo.y && circle.x <= (circleTwo.x + circleTwo.width)
					&& (circle.x + circle.width) >= circleTwo.x) {
				circleTwo.y++;
			}
		} else if (cliking.equals("right")) {
			circle.x++;

			if ((circle.x + circle.width) == circleTwo.x && circle.y <= (circleTwo.y + circleTwo.height)
					&& (circle.y + circle.height) >= circleTwo.y) {
				circleTwo.x++;
			}
		}

		// 동그라미로 그리기 (공 움직이는 예외에 어긋남..)
//		g.setColor(Color.BLACK); // 컬러를 먼저주면, 색 두개를 다르게 할 수도 있네
//		g.drawRoundRect(circle.x, circle.y ,circle.width, circle.height, circle.width, circle.height);
//		g.setColor(Color.PINK);
//		g.fillRoundRect(circle.x, circle.y ,circle.width, circle.height, circle.width, circle.height);
//
//		t.setColor(Color.BLACK); 
//		t.drawRoundRect(circleTwo.x, circleTwo.y ,circleTwo.width, circleTwo.height, circleTwo.width, circleTwo.height);
//		t.setColor(Color.DARK_GRAY);
//		t.fillRoundRect(circleTwo.x, circleTwo.y ,circleTwo.width, circleTwo.height, circleTwo.width, circleTwo.height);

		g.setColor(Color.PINK);
		g.drawRect(circle.x, circle.y, circle.width, circle.height);
		g.fillRect(circle.x, circle.y, circle.width, circle.height);

		t.setColor(Color.DARK_GRAY);
		t.drawRect(circleTwo.x, circleTwo.y, circleTwo.width, circleTwo.height);
		t.fillRect(circleTwo.x, circleTwo.y, circleTwo.width, circleTwo.height);

		repaint();
	}

	private void setMove(JButton button) {
		// 버튼을 받아서, 변경
		if (button == this.up) {
			this.cliking = "up";
		} else if (button == this.left) {
			this.cliking = "left";
		} else if (button == this.down) {
			this.cliking = "down";
		} else if (button == this.right) {
			this.cliking = "right";
		}
	}

	private void setMove(int key) {
		// 버튼을 받아서, 변경
		// 38(상), 37(좌), 40(하), 39(우)
		if (key == 38) { // 상
			this.cliking = "up";
		} else if (key == 37) { // 좌
			this.cliking = "left";
		} else if (key == 40) { // 하
			this.cliking = "down";
		} else if (key == 39) { // 우
			this.cliking = "right";
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton target = (JButton) e.getSource();
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
		// 필요없음
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 키보드는 누르고 있으면 계속 눌리는듯
		// 38(상), 37(좌), 40(하), 39(우)
		// ㄴ 그래도 통합 무브를 위해 그냥 setter를 변경하는 쪽으로
		System.out.println("눌렀따!!");
//		System.out.println(pressed);
		int pressed = e.getKeyCode();
		setMove(pressed);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("뗏다!");
		this.cliking = "";
	}
}
