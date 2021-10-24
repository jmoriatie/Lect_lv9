package omoc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Title extends JLabel{
	
	public Title() {
		setBounds(50 ,50 ,80 ,20);
		setFont( new Font("", Font.BOLD, 15) );
		setText("[ Omoc ]");
		setForeground(Color.black);
		setHorizontalAlignment(CENTER);
		setVisible(true);
	}
}
