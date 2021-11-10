package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import util.MyUtil;

public class Field extends MyUtil implements Runnable{
	
	Random rn = new Random();
	
	public final int PLAYERCNT = 5;
	public final int FINALLINE = 650;
	
	private ArrayList<Horse> hs;
	private ArrayList<Image> is;
	private ArrayList<Integer> rankFinish;
	private HashMap<Integer, String> rank;
	private String[] hColor = {"빨강이","주황이","노랑이","초록이","파랑이"};
	
	private JButton startButton;
	private JButton endButton;
	private JLabel text;
	private int[] light = {700/2 - 50, 10, 20,20};
	private Color c;
	private JLabel timer;
	
	private boolean play;
	
	// Runnable
	private int ms;
	
	public Field() {
		setLayout(null);
		setBounds(0,0,700,500);
		
		init();
		setHorses();
		setTimer();
		
		setVisible(true);
	}

	private void init() {
		hs = new ArrayList<Horse>();
		rankFinish = new ArrayList<Integer>();
		rank = new HashMap<Integer, String>();
		is = new ArrayList<Image>();
		startButton = new JButton();
		endButton = new JButton();
		text = new JLabel();
		
		text.setBounds(90, 20, 200, 400);
		text.setVerticalAlignment(JLabel.TOP);
//		text.setFont(new Font("", Font.BOLD, 15));
		add(text);
		
		startButton.setBounds(700/2 - 70, 500-80, 60, 40);
		endButton.setBounds(700/2 + 10, 500-80, 60, 40);
		
		startButton.setText("start");
		endButton.setText("exit");
		
		startButton.addActionListener(this);
		endButton.addActionListener(this);
		
		add(startButton);
		add(endButton);
		
		c = Color.red;
		
		// Runnable 쓰레드
		Thread t = new Thread(this);
		t.start();
	}
	
	private void setHorses() {
		int y = 50;
		System.out.println("[Load Horses]");
		for(int i=0; i<PLAYERCNT; i++) {
			Horse horse = new Horse(50, y, 60, 60); // x, y, width, height
			System.out.println("image/horse" +(i+1)+ ".png");
			hs.add(horse);
			
			// 지속적으로 움직여야하는 영역
			Image im = new ImageIcon("image/horse" +(i+1)+ ".png").getImage().getScaledInstance(horse.getWidth(), horse.getHeight(), Image.SCALE_SMOOTH);
			is.add(im);

			y += horse.getHeight() + 10;
		}
	}
	
	private void end() {
		if(rank.size() == this.PLAYERCNT) {
			System.out.println("종료");
			this.play = false;
			
			String rankingText = "";
			for(int i=0; i<this.rankFinish.size(); i++) {
				for(int hores : rank.keySet()) {
					if(rankFinish.get(i) == hores) {
						System.out.println(rankFinish.get(i) + " : " + hores);
						rankingText += String.format("<html>%d등 %d번말(기록: %s)<br>", (i+1), hores+1, rank.get(hores) );
						break;
					}
				}
				if(i == PLAYERCNT-1) rankingText += "</html>"; // 마지막에 닫는 태그
			}
			text.setText(rankingText);
			System.out.println(rankingText);
			c = Color.red;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(int i=0; i<PLAYERCNT; i++) {
			Horse hh = hs.get(i);
			Image iic = is.get(i);
			g.drawImage(iic, hh.getX() ,hh.getY(),null);
			
			try {
				Thread.sleep(2);
			}catch (Exception e) {}
		}
		// 결승선
		g.drawLine(FINALLINE, 10, FINALLINE, 450);
		
		g.setColor(c);
		g.drawRoundRect(light[0],light[1], light[2], light[3], light[2], light[3]);		
		g.fillRoundRect(light[0],light[1], light[2], light[3], light[2], light[3]);		
		g.drawRoundRect(light[0]+30,light[1], light[2], light[3], light[2], light[3]);		
		g.fillRoundRect(light[0]+30,light[1], light[2], light[3], light[2], light[3]);		
		g.drawRoundRect(light[0]+60,light[1], light[2], light[3], light[2], light[3]);		
		g.fillRoundRect(light[0]+60,light[1], light[2], light[3], light[2], light[3]);		
		
		g.setColor(Color.gray);
		g.drawLine(50, 110, FINALLINE, 110);
		g.drawLine(50, 110 + 70, FINALLINE, 110 + 70);
		g.drawLine(50, 110 + 140, FINALLINE, 110 + 140);
		g.drawLine(50, 110 + 210, FINALLINE, 110 + 210);
		g.drawLine(50, 110 + 280, FINALLINE, 110 + 280);

		
//		// 끝날때까지 특정한 말만 이동			
		if(this.play) {
			end(); // 다 그리고 엔드조건 실시
//			쓰레드를 입혔을 경우 크기 = 속도 
			int rNum = rn.nextInt(this.PLAYERCNT);
			Horse h = hs.get(rNum);
			
			if(h.getX()+h.getWidth() >= FINALLINE) {
				boolean check = true;
				h.setX(this.FINALLINE - h.getWidth());
				for(int i : rank.keySet()) {
					if(i == rNum)
						check = false;
				}
				if(check) { 
					System.out.println(rNum+"번말: " + String.format("%3d.%3d", this.ms/1000, this.ms/100));
					rank.put(rNum, String.format("%3d.%3d(%s)", this.ms/1000, this.ms/100, hColor[rNum]));
					rankFinish.add(rNum);
				}
			}
			else {
				h.setX( h.getX() + 10);
			}
		}
		
		repaint();
	}

//	Runnable ----------------
	private void setTimer() {
		timer = new JLabel();
		timer.setBounds(10,10,100,50);
		this.timer.setText("ready");
		add(timer);
	}
	
	@Override
	public void run() {
		while(true) {
			if(this.play) {
				this.ms ++;
				this.timer.setText(String.format("%3d.%3d", this.ms/1000, this.ms/100));
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
//	---------------------
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			setStart();
		}
		if(e.getSource() == endButton) {
			Race.getInstance().end();
		}
	}
	private void setStart() {
		System.out.println("시작");
		// 신호색
		c = Color.green;
		// 말들 초기화
		hs.clear();
		is.clear();
		setHorses();
		// 랭크 초기화
		rank.clear();
		rankFinish.clear();
		// 텍스트 초기화
		text.setText("");
		// 시작
		this.play = true;
		this.ms = 0;
	}
	
}
