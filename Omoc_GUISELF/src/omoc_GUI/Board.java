package omoc_GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import omoc_Controller.Controller;
import omoc_Source.Nemo;

public class Board extends JPanel implements MouseListener, ActionListener{

	private Controller con = new Controller();
	private Nemo[][] map = new Nemo[10][10];

	private JButton reset;
	private JLabel message;	
	
	private boolean play = true;
	private int turn  = 1;
	private boolean resetOn = false; 
	
	public Board() {
		setLayout(null);
		setBounds(0, 0, 700, 700);
		
		setReset();
		add(reset);
		
		setMessage();
		add(message);
		
		addMouseListener(this); // 마우스 리스너 패널에 추가
		
		setMap();
	}
	
	// 가이드라인
	private void setMap() {
		int x = 700/2 - 50*10/2;
		int y = 700/2 - 50*10/2;
		
		for(int i = 0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = new Nemo(x, y, 50, 50);
				x+=50;
			}
			x = 700/2 - 50*10/2;
			y += 50;
		}	
	}
	
	// 리셋버튼
	private void setReset() {
		reset = new JButton();
		reset.setBounds(700/2 - 60, 10, 80, 50);
		reset.setBackground(getBackground());
		reset.setText("RESET");

		reset.addActionListener(this);
		
		reset.setVisible(true);
	}
	
	// 메세지 라벨
	private void setMessage() {
		message = new JLabel();
		message.setBounds(0, 625, 700, 50);
		message.setBackground(getBackground());
		message.setFont(new Font("", Font.BOLD, 15));
		message.setForeground(Color.black);
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setVisible(true);
		
		String color = this.turn == 1? "BLACK":"WHITE";
		message.setText(String.format("PLAYER%d 차례[%s]", this.turn,color));
		message.setForeground(Color.DARK_GRAY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(resetOn) {
			System.out.println("리셋 온!");
			super.paintComponent(g);
			resetOn = false;	
		}
		
		// 가이드라인 그리기
//		for(int i = 0; i<map.length; i++) {
//			for(int j=0; j<map[i].length; j++) {
//				Nemo guid = map[i][j];
//				g.setColor(Color.pink);
//				g.drawRect(guid.x, guid.y, guid.width, guid.height);
//			}
//		}
		
		// 일반 맵
		for(int i = 0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				Nemo nemo = map[i][j];
				Nemo field = new Nemo(nemo.x- nemo.width/2, nemo.y-nemo.height/2, nemo.width, nemo.height);
				
				g.setColor(Color.black);
				g.drawRect(field.x, field.y, field.width, field.height);
				
				// 밑줄
				if(i == map.length-1) {
					g.drawRect(field.x, field.y + field.height, field.width, field.height);
				}
				// 오른쪽줄
				if(j == map[i].length-1) {
					g.drawRect(field.x + field.width, field.y, field.width, field.height);
				}
				// 마지막 하나
				if(i == map.length-1 && j == map[i].length-1) {
					g.drawRect(field.x + field.width, field.y + field.height, field.width, field.height);
				}
			}
		}
		
		// 바둑돌
		for(int i = 0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				Nemo stone = map[i][j];
				// 좌표(x,y) 크기(w,h), 라운드 크기(w,h)
				if(stone.click) {
					// 클릭될때 저장된 아이디에 따라 색을 바꿔준다
					if(stone.id == 1) g.setColor(Color.black);
					else if(stone.id == 2) g.setColor(Color.white);
					
					g.drawRoundRect(stone.x+10, stone.y+10, stone.width-20, stone.height-20, stone.width, stone.height);
					g.fillRoundRect(stone.x+10, stone.y+10, stone.width-20, stone.height-20, stone.width, stone.height);					
				}
			}
		}
		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("클릭!");
		
		if(this.play) {
			// 클릭 발생지점 좌표값
			int x = e.getX();
			int y = e.getY();
			
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[i].length; j++) {
					Nemo stone = map[i][j];
					
					if(!stone.click &&
							x >= stone.x+10 && x < stone.x+10 + stone.width &&
							y >= stone.y+10 && y < stone.y+10 + stone.height) {
						
						if(this.turn == 1) {
							stone.id = 1;
							this.turn++;
						}
						else if(this.turn == 2) {
							stone.id = 2;
							this.turn--;						
						}
						stone.click = true;
					}
				}	
			}
			
			
			String color = this.turn == 1? "BLACK":"WHITE";
			message.setText(String.format("PLAYER%d 차례[%s]", this.turn,color));
			message.setForeground(Color.DARK_GRAY);
			
			int winner = con.end(this.map, this.turn);
			if(winner != -1) {
				message.setText(String.format("[PLAYER%d 승리!]", winner));
				this.play = false;
			}

			
		}else {
			message.setText("리셋 버튼을 클릭하세요");
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton)e.getSource();
		
		if(this.reset == target) {
			this.play = true;
			for(int i = 0; i<map.length; i++) {
				for(int j=0; j<map[i].length; j++) {
					Nemo stone = map[i][j];
					stone.id = -1;
					stone.click = false;
				}
			}
			System.out.println("리셋!");
			this.turn = 1;
			String color = this.turn == 1? "BLACK":"WHITE";
			message.setText(String.format("PLAYER%d 차례[%s]", this.turn,color));
			message.setForeground(Color.DARK_GRAY);
			
			this.resetOn = true;
		}
	}
	
	
}
