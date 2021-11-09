package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import util.MyUtil;

public class Field extends MyUtil{
	
	Random rn = new Random();
	
	public final int PLAYERCNT = 5;
	public final int FINALLINE = 650;
	
	private ArrayList<Horse> hs;
	private ArrayList<Image> is;
	private ArrayList<Integer> rank;
	
	JButton startButton;
	JButton endButton;
	JLabel text;

	boolean play;
	
	public Field() {
		setLayout(null);
		setBounds(0,0,700,500);
		
		init();
		setHorses();
		
		setVisible(true);
	}

	private void init() {
		hs = new ArrayList<Horse>();
		rank = new ArrayList<Integer>();
		is = new ArrayList<Image>();
		startButton = new JButton();
		endButton = new JButton();
		text = new JLabel();
		
		text.setBounds(700/2-300, 500/2-20, 500, 20);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setFont(new Font("", Font.BOLD, 15));
		add(text);
		
		startButton.setBounds(700/2 - 70, 500-80, 60, 40);
		endButton.setBounds(700/2 + 10, 500-80, 60, 40);
		
		startButton.setText("start");
		endButton.setText("exit");
		
		startButton.addActionListener(this);
		endButton.addActionListener(this);
		
		add(startButton);
		add(endButton);
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
			String ranking = "";
			for(int i=0; i<PLAYERCNT; i++) {
				ranking += String.format("%d등 %d번말",(i+1),(rank.get(i)+1) );
				if(i != PLAYERCNT-1) ranking += " / ";
			}
			text.setText(ranking);
			System.out.println(ranking);
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
		
		// 결승점
		g.drawLine(FINALLINE, 10, FINALLINE, 450);
				
//		// 끝날때까지 특정한 말만 이동			
		if(this.play) {
			end();
			
//			쓰레드를 입혔을 경우 크기 = 속도 
			int rNum = rn.nextInt(this.PLAYERCNT);
			Horse h = hs.get(rNum);
			
			if(h.getX()+h.getWidth() >= FINALLINE) {
				boolean check = true;
				h.setX(this.FINALLINE - h.getWidth());
				for(int i : rank) {
					if(i == rNum)
						check = false;
				}
				if(check) 
					rank.add(rNum);
			}
			else {
				h.setX( h.getX() + 10);
			}
		}
		
		repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			System.out.println("시작");
			// 말들 초기화
			hs.clear();
			is.clear();
			setHorses();
			// 랭크 초기화
			rank.clear();
			// 텍스트 초기화
			text.setText("");
			// 시작
			this.play = true;
		}
		if(e.getSource() == endButton) {
			Race.getInstance().end();
		}
		
	}
	
}
