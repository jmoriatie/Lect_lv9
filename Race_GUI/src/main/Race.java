package main;

import javax.swing.JFrame;

public class Race extends JFrame{

	private static Race instance = new Race();

	private Race() {
		super("RACE");
		setLayout(null);
		setBounds(100,100, 700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Field());
		
		setVisible(true);
	}
	
	public static Race getInstance() {
		return instance;
	}
	
	public void end() {
		System.out.println("종료");
		this.dispose();	
	}
}
