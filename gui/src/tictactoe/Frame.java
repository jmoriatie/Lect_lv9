package tictactoe;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

class Frame extends JFrame{
	
	Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = dm.width;
	private int heigth = dm.height;
	static final int W = 600;
	static final int H = 400;
	
	public Frame(String title) {
		super(title);
		setLayout(null); 
		setBounds(this.width/2 - W/2, this.heigth/2 - H/2 ,W, H); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		add( new Panel() );
	}
}
