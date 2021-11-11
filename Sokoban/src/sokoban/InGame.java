package sokoban;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import data.Data;
import util.Util;

public class InGame extends Util implements Runnable{
	
	private Controller c = new Controller();

	private JLabel timer;
	private JLabel text;
	private JButton reset;
	
	public static int time;
	public static boolean run;
	
	
	public InGame() {
		setLayout(null);
		// 8x9 
		setBounds(0,0,600,477);
		
		init();
		c.setImage();
		c.setMap();
		text.setText(String.format("남은 상자 %d개", c.finishCnt));

		addKeyListener(this);
		setVisible(true);
	}
	
	private void init() {
		timer = new JLabel();
		text = new JLabel();
		reset = new JButton();

		timer.setBounds(600-150, 50, 100, 50);
		reset.setBounds(600-150, 150, 100, 50);
		text.setBounds(600-150, 200, 150, 150);
		
		reset.addKeyListener(this);
		reset.addActionListener(this);
		reset.setText("RESET");
		
		text.setFont( new Font("", Font.BOLD, 15) );
		timer.setHorizontalAlignment(JLabel.CENTER);

		add(timer);
		add(reset);
		add(text);
		
		run = true;

		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		// 나중에 게임 종료 예외처리 적용
		while(true) {
			if(run) {
				time++;							
			}
			try {
				Thread.sleep(1);
				timer.setText(String.format("%3d.%3d", time/1000,time/100));
			} catch (Exception e) {
			}
		}
	};

	@Override
	public void keyPressed(KeyEvent e) {
		if(run) {
			if(e.getKeyCode() == 38) c.up(); 	// 상 38
			if(e.getKeyCode() == 40) c.down(); // 하 40
			if(e.getKeyCode() == 37) c.left(); // 좌 37
			if(e.getKeyCode() == 39) c.right(); // 우 39
			c.goalControl(); // 골대원복 및 종료 체크		
			text.setText(String.format("남은 상자 %d개", c.finishCnt));
			// 만약 게임 종료시, 기록 출력
			if(!run) text.setText(String.format("<html>CLEAR!<br>(%d.%d)</html>", time/1000,time/100));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.reset) {
			c.reset();
			text.setText(String.format("남은 상자 %d개", c.finishCnt));
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0; i<c.SERO; i++) { // 세로
			for(int j=0; j<c.GARO; j++) { // 세로
				Data temp = Controller.map.get(i).get(j);
				if(temp.getImage() == c.wall) {
					g.drawImage(c.wall,temp.getX(), temp.getY(), null);
				}
				else if(temp.getImage() == c.player) {
					g.drawImage(c.player,temp.getX(), temp.getY(), null);
				}
				else if(temp.getImage() == c.box) {
					g.drawImage(c.box,temp.getX(), temp.getY(), null);
				}
				else if(temp.getImage() == c.goal) {
					g.drawImage(c.goal,temp.getX(), temp.getY(), null);
				}
				else if(temp.getImage() == c.finishBox) {
					g.drawImage(c.finishBox,temp.getX(), temp.getY(), null);
				}
				else if(temp.getImage() == c.field){
					g.drawImage(c.field, temp.getX(), temp.getY(), null);
				}
			}
		}
		repaint();
	}	
}
