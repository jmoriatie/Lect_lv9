package omoc_GUI;

import javax.swing.JFrame;


public class Frame extends JFrame{
		public Frame() {
			setLayout(null);
			setBounds(0, 0, 700, 700);
			setDefaultCloseOperation(EXIT_ON_CLOSE);

			add(new Board());

			setVisible(true);
		}
}
