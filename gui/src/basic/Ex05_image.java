package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Ex05Panel extends MyUtil{
	
	private JLabel label = new JLabel(); // new JLabel("test);
//	private JLabel image = new JLabel(); // new JLabel(new ImageIcon(""));
	
	private ImageIcon icon = new ImageIcon("images/사진.png");
	private int x = 100;
	
	// 이미지 크기 변경 => 이미지 객체로 크기변경 준다음에 재초기화
	// ㄴ .getScaledInstance(200, 200, Image.SCALE_SMOOTH)
	// ㄴ (가로200, 세로200, 옵션)
	// ㄴ Image.SCALE_SMOOTH : 크기변경 안하겠다
	// ㄴ Image.SCALE_AREA_AVERAGING : 스케일 안에다가 넣겠다
	private Image im = new ImageIcon("images/사진.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	private JLabel image2 = new JLabel(new ImageIcon(im));

	
	// 크기가 어떻게 움직이는지를 봐야해
	public Ex05Panel() {
		setLayout(null);
		setBounds(0,0,500,500);
		
		label.setBounds(0, 0, 500, 500);
		label.setText("test");
//		label.setVisible(true);
//		add(label); // 컨테이너 위에 add()메소드를 통해 -> 컴포넌트 추가 : 인덱스가 붙음( 추가하는 순서대로 )
		// ㄴ 앞에 있는 거에다가 ,1을 해주면 안됨 => 아웃바운드
		// ㄴ 여기까지는 인덱스 0밖에 없다
		
		add(image2);

		// images 폴더 만듦 -> 이미지 넣기 -> 경로 지정
//		image.setIcon(new ImageIcon("images/사진.png"));
//		image.setBounds(0, 0, 500, 500);
//		image.setVisible(true);
//		add(image , 0); // <- 우선순우 변경, 가장 앞으로와 동일
		
//		*사용 방법
//		drawPolyLine(int[], int[], int)
//		: x 좌표 배열, y좌표 배열, 점 개수

		
		// 인덱스 0, 1 중에 0으로 변경
		
		image2.setBounds(0, 0, 400, 400);
		image2.setBackground(Color.gray);
		add(image2);

		setVisible(true);
		
		
		// 컴포넌트 지우기 셋트
//		remove(image);
//		this.revalidate();
//		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
//		g.drawImage(icon.getImage(), x, 100, null); // 이미지, x, y, 디폴트크기 
		
//		try {
//			Thread.sleep(50);
//			x++;
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
		
		repaint();
	}
}

class Ex05Frame extends JFrame{
	public Ex05Frame() {
		super("image");
		setLayout(null);
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Ex05Panel());
		
		setVisible(true);
	}
}

public class Ex05_image {
	public static void main(String[] args) {
		Ex05Frame f = new Ex05Frame();
	}
}
