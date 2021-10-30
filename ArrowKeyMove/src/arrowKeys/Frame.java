package arrowKeys;

import javax.swing.JFrame;

public class Frame extends JFrame{

	Frame(){
		setLayout(null);
		setBounds(0, 0, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Board());
		
		setVisible(true);
	}
}
