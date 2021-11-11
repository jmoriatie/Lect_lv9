package sokoban;

import javax.swing.JFrame;

public class Sokoban extends JFrame {
	
	private static Sokoban instance = new Sokoban();
	
	private Sokoban() {
		super("Sokoban");
		setLayout(null);
		// 8x9 
		setBounds(100,100,600,477);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(new InGame());
		
		setVisible(true);
		revalidate();
	}
	
	public static Sokoban getInstance() {
		return instance;
	}
	
	public void end() {
		System.out.println("종료");
		this.dispose();
	}
}
