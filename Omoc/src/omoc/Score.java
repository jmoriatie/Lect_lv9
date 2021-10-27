package omoc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Score extends JLabel{
	
	public Score() {
		setBounds(20 ,250 ,200 ,40);
		setFont(new Font("", Font.ITALIC, 13));
		setForeground(Color.DARK_GRAY);
		setText(String.format("[p1: 0점 // p2: 0점]"));
		setHorizontalAlignment(CENTER);
		setVisible(true);
	}
	
}
