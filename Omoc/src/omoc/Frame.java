package omoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame{

	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	
	private int width = dm.width;
	private int height = dm.height;
	static final int W = 1000;
	static final int H = 800;
	
	public Frame(String title) {
		super(title);
		setLayout(null);
		setBounds(this.width/2 - W/2, this.height/2 -H/2, W, H);
		setBackground(Color.gray);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		// 패널 더해주고
		// 타이틀 더해주고
		// 리셋, 가이드
		Panel Panel = new Panel();
		add(Panel  );
		
		Thread t = new Thread(Panel.timer);
		t.start();
	}
}
