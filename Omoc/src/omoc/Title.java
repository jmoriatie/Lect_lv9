package omoc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Title extends JLabel{
	
	public Title() {
		setBounds(20 ,50 ,200 ,40);
		setFont( new Font("", Font.BOLD, 16) );
		setText("[ OMOC OMOC ]");
		setForeground(Color.black);
		setHorizontalAlignment(CENTER);
		setVisible(true);
	}
}
