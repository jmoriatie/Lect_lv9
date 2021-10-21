package tictactoe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class HeadTitle extends JLabel {	
	public HeadTitle() {
		setBounds(0, Frame.H-400, Frame.W-50, 40);
		setText("틱택토게임");
		setFont(new Font("", Font.BOLD, 20));
		setForeground(Color.white);
		setHorizontalAlignment(CENTER);
		setVisible(true);
	}
}
